package WEB3D.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String user;
    private String time;
    private String msg;

    public Message(String user, String msg) {
        this.user = user;
        this.msg = msg;
        this.time = new Date().toString();
    }

    public Message(String msg) {
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getTime() {
        return time;
    }

    public String getMsg() {
        return msg;
    }

    public Message() {

    }
}
