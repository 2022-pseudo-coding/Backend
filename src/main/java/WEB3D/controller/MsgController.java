package WEB3D.controller;

import WEB3D.domain.Message;
import WEB3D.security.jwt.JwtTokenUtil;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MsgController {

    public static Map<String, UUID> map = new ConcurrentHashMap<>();

    private final SocketIOServer server;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public MsgController(SocketIOServer server) {
        this.server = server;
    }

    @OnConnect
    public void onConnect(SocketIOClient client) {
        if (client != null) {
            //这个方法获取http://127.0.0.1:9090?room=1&token=123，中的room
            //room参数用于标识在同一个聊天室里的用户，后面可以给他们群发消息
            String room = client.getHandshakeData().getSingleUrlParam("room");
            String token = client.getHandshakeData().getSingleUrlParam("token");
            String username = jwtTokenUtil.getUsernameFromToken(token);
            client.joinRoom(room);
            map.put(username, client.getSessionId());

            Map<String, String> broadcast = new HashMap<>();
            broadcast.put("username", "123");
            broadcast.put("modelName", "1234");
            server.getRoomOperations(room).sendEvent("introduction", broadcast);
        } else {
//            log.error("客户端为空");
        }
    }

    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String token = client.getHandshakeData().getSingleUrlParam("token");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        map.remove(username);
        client.disconnect();
    }

    //接受消息后，给所有当前用户所在房间的客户端发送消息
    //这个方法的第三个参数接受一个对象，这里是message
    @OnEvent(value = "message")
    public void onEvent(SocketIOClient client, AckRequest ackRequest, Message message) {
        String msg = message.getMsg();
        String token = client.getHandshakeData().getSingleUrlParam("token");
        String user = jwtTokenUtil.getUsernameFromToken(token);
        //给同一个room的群发消息
        String room = client.getHandshakeData().getSingleUrlParam("room");
        server.getRoomOperations(room).sendEvent("message", new Message(user, msg));
    }
}
