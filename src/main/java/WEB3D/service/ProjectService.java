package WEB3D.service;

import WEB3D.common.Utils;
import WEB3D.controller.request.ProjectRequest;
import WEB3D.controller.request.SolveModuleRequest;
import WEB3D.domain.*;
import WEB3D.repository.*;
import WEB3D.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectService {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ModuleRepository moduleRepository;
    private final ActionRepository actionRepository;

    @Autowired
    public ProjectService(UserRepository userRepository, ProjectRepository projectRepository, ModuleRepository moduleRepository, ActionRepository actionRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.moduleRepository = moduleRepository;
        this.actionRepository = actionRepository;
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
                new ArrayList<>());
        projectRepository.save(project);
        user.addProject(project);
        userRepository.save(user);
        result.put("message", "Project created successfully");
        result.put("id", project.getId());
        return result;
    }

    public Map<String, Object> projectUpdate(ProjectRequest projectRequest) {
        Map<String, Object> result = new HashMap<>();
        String token = projectRequest.getToken();
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        if (user == null) {
            result.put("message", "User does not exist");
            return result;
        }
        Project project = projectRepository.getOne(projectRequest.getId());
        List<Action> actions = new ArrayList<>();
        for (int i = 0; i < projectRequest.getIsModules().size(); i++) {
            if (projectRequest.getIsModules().get(i)) {
                Module module = moduleRepository.findByCreatorIdAndName(user.getId(),projectRequest.getModules().get(i).getName());
                Module newModule = new Module(null, module.getName(), module.getColor());
                for(Instruction instruction:module.getInstructions()){
                    newModule.addInstruction(new Instruction(instruction.getName(), instruction.getColor(), instruction.getReferTo(), instruction.getJumpTo()));
                }
                actions.add(newModule);
            } else {
                Instruction instruction = projectRequest.getInstructions().get(i);
                actions.add(instruction);
            }
        }
        project.setActions(actions);
        projectRepository.save(project);
        result.put("message", "Project updated successfully");
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
        List<Module> modules = moduleRepository.findAllByCreatorId(user.getId());
        if (modules == null) {
            result.put("modules", new ArrayList<Module>());
            result.put("message", "success");
            return result;
        }
        result.put("modules", modules);
        result.put("message", "success");
        result.put("projects", projects);
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
        result.put("project", project);

        result.put("modules", moduleRepository.findAllByCreatorId(user.getId()));
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
