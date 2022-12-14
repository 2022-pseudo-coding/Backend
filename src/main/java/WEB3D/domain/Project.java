package WEB3D.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Long creatorId;
    private String title;
    private String description;
    @OneToMany(targetEntity = Problem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Problem> problems = new ArrayList<>();

    public Project() {
    }


    public Project(Long creatorId, String title, String description) {
        this.creatorId = creatorId;
        this.title = title;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void addProblem(Problem problem) {
        this.problems.add(problem);
    }

    private int getLength() {
        return this.problems.size();
    }
}
