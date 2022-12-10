package WEB3D.domain;

import javax.persistence.*;
import java.util.*;

public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToMany(targetEntity = Problem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Problem> problems = new ArrayList<>();
}
