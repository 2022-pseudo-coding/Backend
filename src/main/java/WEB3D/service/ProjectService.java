package WEB3D.service;

import WEB3D.common.Utils;
import WEB3D.controller.request.ProjectRequest;
import WEB3D.controller.request.SolveModuleRequest;
import WEB3D.domain.Problem;
import WEB3D.domain.Project;
import WEB3D.domain.User;
import WEB3D.repository.ProjectRepository;
import WEB3D.repository.UserRepository;
import WEB3D.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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


    public Map<String, Object> projectCreate(ProjectRequest projectRequest) {
        Map<String, Object> result = new HashMap<>();
        String token = projectRequest.getToken();
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        if (user == null) {
            result.put("message", "User does not exist");
            return result;
        }
        Project project = new Project(user.getId(), projectRequest.getTitle(), projectRequest.getDescription(),
                projectRequest.getInstructions(), projectRequest.getActions());
        projectRepository.save(project);
        user.addProject(project);
        userRepository.save(user);
        result.put("message", "Project created successfully");
        return result;
    }

    public Map<String, Object> project(ProjectRequest projectRequest) {
        Map<String, Object> result = new HashMap<>();
        String token = projectRequest.getToken();
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        if (user == null) {
            result.put("message", "User does not exist");
            return result;
        }
        Set<Project> projects = user.getProjects();
        for (Project project : projects) {
            result.put("message", project);
        }
        return result;
    }

    public Map<String, Object> solveProject(SolveModuleRequest request) {
        Map<String, Object> result = new HashMap<>();
        String input=String.join(";",request.getInput());
        String memory=String.join(";",request.getMemory());
        Problem problem=new Problem();
        problem.setMemory(memory);
        problem.setInput(input);
        result.put("statusList", Utils.execInstructionsForModule(problem,request.getInstructions()));
        return result;
    }
}
