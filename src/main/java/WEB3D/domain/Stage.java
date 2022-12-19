package WEB3D.domain;

import java.util.List;

public class Stage {
    private String name;
    private List<Problem> problems;

    public Stage(String name, List<Problem> problems){
        this.name = name;
        this.problems = problems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
    }

    public String getName() {
        return name;
    }

    public List<Problem> getProblems() {
        return problems;
    }
}
