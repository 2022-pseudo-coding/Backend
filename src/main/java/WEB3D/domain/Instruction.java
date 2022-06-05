package WEB3D.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String color;

    private int referTo;

    private int jumpTo;

    public Instruction() {
    }

    public Instruction(String name, String color, int referTo, int jumpTo) {
        this.name = name;
        this.color = color;
        this.referTo = referTo;
        this.jumpTo = jumpTo;
    }

    public Instruction(String name) {
        this.name = name;
        switch (name) {
            case "inbox" : {}
            case "outbox" : {
                this.color = "green";
                break;
            }
            case "copyfrom" : {}
            case "copyto" : {
                this.color = "red";
                break;
            }
            case "add" : {}
            case "sub" : {}
            case "bump+" : {}
            case "bump-" : {
                this.color = "orange";
                break;
            }
            case "jump" : {}
            case "jump_zero" : {}
            case "jump_neg" : {
                this.color = "blue";
                break;
            }
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getReferTo() {
        return referTo;
    }

    public int getJumpTo() {
        return jumpTo;
    }
}
