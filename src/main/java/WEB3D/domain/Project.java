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

    @OneToMany(targetEntity = Action.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Action> actions = new ArrayList<>();

    public Project() {
    }


    public Project(Long creatorId, String title, String description, List<Action> actions) {
        this.creatorId = creatorId;
        this.title = title;
        this.description = description;
        this.actions = actions;
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

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }
}
