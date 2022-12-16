package WEB3D.controller.request;

import WEB3D.domain.Instruction;

import java.util.List;

public class SolveModuleRequest {
    private int id;
    private List<String> input;
    private List<String> memory;
    private List<Instruction> instructions;

    public SolveModuleRequest(int id, List<String> input, List<String> memory, List<Instruction> instructions) {
        this.id = id;
        this.input = input;
        this.memory = memory;
        this.instructions = instructions;
    }

    public int getId() {
        return id;
    }

    public List<String> getInput() {
        return input;
    }

    public List<String> getMemory() {
        return memory;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }
}
