package WEB3D.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Instruction extends Action {

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

    public Instruction(String name, int to) {
        this(name);
        switch (name) {
            case "copyfrom": {
            }
            case "copyto": {
            }
            case "add": {
            }
            case "sub": {
            }
            case "bump+": {
            }
            case "bump-": {
                this.referTo = to;
                this.jumpTo = -1;
                break;
            }
            case "jump": {
            }
            case "jump_zero": {
            }
            case "jump_neg": {
                this.jumpTo = to;
                this.referTo = -1;
                break;
            }
            default: {
                this.jumpTo = -1;
                this.referTo = -1;
            }
        }
    }

    public Instruction(String name) {
        this.name = name;
        switch (name) {
            case "inbox": {
            }
            case "outbox": {
                this.color = "green";
                break;
            }
            case "copyfrom": {
            }
            case "copyto": {
                this.color = "red";
                break;
            }
            case "add": {
            }
            case "sub": {
            }
            case "bump+": {
            }
            case "bump-": {
                this.color = "orange";
                break;
            }
            case "jump": {
            }
            case "jump_zero": {
            }
            case "jump_neg": {
                this.color = "blue";
                break;
            }
        }
        this.jumpTo = -1;
        this.referTo = -1;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getReferTo() {
        return referTo;
    }

    public void setReferTo(int referTo) {
        this.referTo = referTo;
    }

    public int getJumpTo() {
        return jumpTo;
    }

    public void setJumpTo(int jumpTo) {
        this.jumpTo = jumpTo;
    }
}
