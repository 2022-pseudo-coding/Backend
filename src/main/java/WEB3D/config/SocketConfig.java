package WEB3D.config;

import WEB3D.security.jwt.JwtTokenUtil;
import WEB3D.service.JwtUserDetailsService;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Resource;


@Configuration
public class SocketConfig {
    //配置文件在application.yml
    @Value("${socketio.host}")
    private String host;

    @Value("${socketio.port}")
    private Integer port;

    @Value("${socketio.bossCount}")
    private int bossCount;

    @Value("${socketio.workCount}")
    private int workCount;

    @Value("${socketio.allowCustomRequests}")
    private boolean allowCustomRequests;

    @Value("${socketio.upgradeTimeout}")
    private int upgradeTimeout;

    @Value("${socketio.pingTimeout}")
    private int pingTimeout;

    @Value("${socketio.pingInterval}")
    private int pingInterval;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private JwtUserDetailsService jwtUserDetailsService;

    @Bean
    public SocketIOServer socketIOServer(){
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(host);
        config.setPort(port);
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);
        config.setRandomSession(true);
        //验证连接,token不通过返回false就连接失败
        config.setAuthorizationListener(handshakeData -> {
            String token = handshakeData.getSingleUrlParam("token");
            String username = jwtTokenUtil.getUsernameFromToken(token);
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            return jwtTokenUtil.validateToken(token, userDetails);
        });
        return new SocketIOServer(config);
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }

}
