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

    private boolean canJump;

    private int referTo;

    private int jumpTo;

    public Instruction() {
    }

    public Instruction(String name, String color, boolean canJump, int referTo, int jumpTo) {
        this.name = name;
        this.color = color;
        this.canJump = canJump;
        this.referTo = referTo;
        this.jumpTo = jumpTo;
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

    public boolean isCanJump() {
        return canJump;
    }

    public int getReferTo() {
        return referTo;
    }

    public int getJumpTo() {
        return jumpTo;
    }
}
