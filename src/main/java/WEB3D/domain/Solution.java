package WEB3D.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long problemId;

    private int steps;

    private int numInst;

    @ManyToMany(targetEntity = Instruction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Instruction> instructions = new ArrayList<>();

    public Solution() {
    }

    public Solution(Long problemId, int steps, int numInst, List<Instruction> instructions) {
        this.problemId = problemId;
        this.steps = steps;
        this.numInst = numInst;
        this.instructions = instructions;
    }

    public Long getProblemId() {
        return problemId;
    }

    public int getSteps() {
        return steps;
    }

    public int getNumInst() {
        return numInst;
    }

    public List<Instruction> getInstructions() {
        return new ArrayList<>(instructions);
    }
}
