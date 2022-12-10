package WEB3D.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long creatorId;
    private String title;
    private String description;
    @OneToMany(targetEntity = Module.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Module> modules = new ArrayList<>();

    public Project() {
    }


    public Project(Long creatorId, String title, String description) {
        this.creatorId = creatorId;
        this.title = title;
        this.description = description;
    }


    public Long getId() {
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

    public List<Module> getModules() {
        return modules;
    }

    public void addModule(Module module){
        this.modules.add(module);
    }

    private int getLength() {
        return this.modules.size();
    }
}
