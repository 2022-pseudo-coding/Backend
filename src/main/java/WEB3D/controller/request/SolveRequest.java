package WEB3D.controller.request;

import WEB3D.domain.Instruction;

import java.util.List;

public class SolveRequest {
    private String id;
    private String token;
    private List<Instruction> insts;

    public SolveRequest(String id, String token, List<Instruction> insts) {
        this.id = id;
        this.token = token;
        this.insts = insts;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public List<Instruction> getInsts() {
        return insts;
    }
}
