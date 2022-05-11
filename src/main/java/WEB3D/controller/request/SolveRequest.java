package WEB3D.controller.request;

import WEB3D.domain.Instruction;

import java.util.List;

public class SolveRequest {
    private String id;
    private String token;
    private List<Instruction> instructions;

    public SolveRequest() {
    }

    public SolveRequest(String id, String token, List<Instruction> instructions) {
        this.id = id;
        this.token = token;
        this.instructions = instructions;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }
}
