package WEB3D.service;

import WEB3D.controller.request.ProjectRequest;
import WEB3D.domain.Project;
import WEB3D.domain.User;
import WEB3D.repository.ProjectRepository;
import WEB3D.repository.UserRepository;
import WEB3D.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    public Map<String, Object> project(ProjectRequest projectRequest) {
        Map<String, Object> result = new HashMap<>();
        String token = projectRequest.getToken();
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        if (user == null) {
            result.put("message", "User does not exist");
            return result;
        }
        Project project = new Project(user.getId(),projectRequest.getTitle(),projectRequest.getDescription());
        projectRepository.save(project);
        return result;
    }
}
