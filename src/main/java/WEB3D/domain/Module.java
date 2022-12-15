package WEB3D.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long creatorId;
    private String name;
    private String color;

    @OneToMany(targetEntity = Instruction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Instruction> instructions = new ArrayList<>();

    public Module(Long creatorId,String name, String color) {
        this.creatorId = creatorId;
        this.name = name;
        this.color= color;
    }

    public Module() {

    }


    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Long getCreatorId() {
        return creatorId;
    }
}
