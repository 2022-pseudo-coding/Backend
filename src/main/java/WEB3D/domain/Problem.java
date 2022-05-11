package WEB3D.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @OneToMany(targetEntity = Instruction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Instruction> instructions;

    @OneToMany(targetEntity = Solution.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Solution> solutions;

    public Problem() {
    }

    public Problem(String title, String description, List<Instruction> instructions) {
        this.title = title;
        this.description = description;
        this.instructions = instructions;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Instruction> getInstructions() {
        return new ArrayList<>(instructions);
    }

    public List<Solution> getSolutions() {
        return new ArrayList<>(solutions);
    }
}
