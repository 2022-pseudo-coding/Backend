package WEB3D.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description;

    @OneToMany(targetEntity = Instruction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Instruction> instructions = new ArrayList<>();

    @OneToMany(targetEntity = Solution.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Solution> solutions = new LinkedHashSet<>();

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

    public void addSolution(Solution solution) {
        solutions.add(solution);
    }
}
