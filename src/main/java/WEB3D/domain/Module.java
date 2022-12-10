package WEB3D.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    @OneToMany(targetEntity = Problem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Problem> problems = new ArrayList<>();

    public Module(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Module() {

    }

    public List<Problem> getProblems() {
        return problems;
    }
    public void addModule(Problem module){
        this.problems.add(module);
    }
}
