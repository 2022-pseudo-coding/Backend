package WEB3D.controller.request;

import WEB3D.domain.Action;
import WEB3D.domain.Instruction;

import java.util.ArrayList;
import java.util.List;

public class ProjectRequest {
    private int id;
    private String token;
    private String title;
    private String description;
    private List<Instruction> instructions = new ArrayList<>();
    private List<Action> actions = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public List<Action> getActions() {
        return actions;
    }
}
