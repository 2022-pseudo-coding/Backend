package WEB3D.service;

import WEB3D.common.Utils;
import WEB3D.controller.request.ProjectRequest;
import WEB3D.controller.request.SolveModuleRequest;
import WEB3D.domain.*;
import WEB3D.repository.InstructionRepository;
import WEB3D.repository.ModuleRepository;
import WEB3D.repository.ProjectRepository;
import WEB3D.repository.UserRepository;
import WEB3D.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModuleRepository moduleRepository;
    private final InstructionRepository instructionRepository;

    @Autowired
    public ProjectService(UserRepository userRepository, ProjectRepository projectRepository, ModuleRepository moduleRepository, InstructionRepository instructionRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.moduleRepository = moduleRepository;
        this.instructionRepository = instructionRepository;
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
                projectRequest.getActions());
        projectRepository.save(project);
        user.addProject(project);
        userRepository.save(user);
        result.put("message", "Project created successfully");
        return result;
    }

    public Map<String, Object> projectByUser(ProjectRequest projectRequest) {
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

    public Map<String, Object> projectById(ProjectRequest projectRequest) {
        Map<String, Object> result = new HashMap<>();
        String token = projectRequest.getToken();
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        if (user == null) {
            result.put("message", "User does not exist");
            return result;
        }
        Project project = projectRepository.getOne(projectRequest.getId());
        List<Action> actions = project.getActions();
        Instruction instruction = null;
        Module module = null;
        for (Action action : actions) {
            if (instructionRepository.existsById(action.getId())) {
                instruction = instructionRepository.getOne(action.getId());
                result.put("message", instruction);
            }
            if (moduleRepository.existsById(action.getId())) {
                module = moduleRepository.getOne(action.getId());
                result.put("message", module);
            }
        }
        return result;
    }


    public Map<String, Object> solveProject(SolveModuleRequest request) {
        Map<String, Object> result = new HashMap<>();
        String input = String.join(";", request.getInput());
        String memory = String.join(";", request.getMemory());
        Problem problem = new Problem();
        problem.setMemory(memory);
        problem.setInput(input);
        result.put("statusList", Utils.execInstructionsForModule(problem, request.getInstructions()));
        return result;
    }
}
