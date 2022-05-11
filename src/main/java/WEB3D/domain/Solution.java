package WEB3D.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Problem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Problem problem;

    private int steps;

    private int numInst;

    @ManyToMany(targetEntity = Instruction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Instruction> insts;

    public Solution() {
    }

    public Solution(Problem problem, int steps, int numInst, List<Instruction> insts) {
        this.problem = problem;
        this.steps = steps;
        this.numInst = numInst;
        this.insts = insts;
    }

    public Problem getProblem() {
        return problem;
    }

    public int getSteps() {
        return steps;
    }

    public int getNumInst() {
        return numInst;
    }

    public List<Instruction> getInsts() {
        return new ArrayList<>(insts);
    }
}
