package WEB3D.controller.request;

import WEB3D.domain.Instruction;

import java.util.List;

public class UserDefineModuleRequest {
    private String token;
    private String name;
    private String color;
    private List<Instruction> instructions;

    public UserDefineModuleRequest(String token, String name, String color, List<Instruction> instructions) {
        this.name = name;
        this.color = color;
        this.instructions = instructions;
        this.token = token;
    }
    public UserDefineModuleRequest(){

    }
    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public String getColor() {
        return color;
    }
}
