package WEB3D.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int stage;
    private int number;
    private String title;

    private String description;

    private String input;
    private String output;

    //initial status of memory
    private String memory;

    @OneToMany(targetEntity = Instruction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Instruction> instructions = new ArrayList<>();

    @OneToMany(targetEntity = Solution.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Solution> solutions = new LinkedHashSet<>();

    public Problem() {
    }

    public Problem(int stage, int number, String title, String description, List<Instruction> instructions, String input, String output, String memory) {
        this.stage = stage;
        this.number = number;
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.input = input;
        this.output = output;
        this.memory = memory;
    }

    public int getStage(){return stage;}

    public int getNumber() {
        return number;
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

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public String getMemory() {
        return memory;
    }

    public void addSolution(Solution solution) {
        solutions.add(solution);
    }
}
