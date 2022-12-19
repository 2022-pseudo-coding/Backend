package WEB3D.service;

import WEB3D.common.Utils;
import WEB3D.controller.request.SolveModuleRequest;
import WEB3D.controller.request.UserDefineModuleRequest;
import WEB3D.domain.Module;
import WEB3D.domain.Problem;
import WEB3D.domain.User;
import WEB3D.repository.ModuleRepository;
import WEB3D.repository.ProjectRepository;
import WEB3D.repository.UserRepository;
import WEB3D.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ModuleService {
    private final UserRepository userRepository;
    private final ModuleRepository moduleRepository;
    private final ProjectRepository projectRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public ModuleService(UserRepository userRepository, ModuleRepository moduleRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.moduleRepository = moduleRepository;
        this.projectRepository = projectRepository;
    }

    public Map<String, Object> moduleCreate(UserDefineModuleRequest request) {
        Map<String, Object> result = new HashMap<>();
        String token = request.getToken();
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        if (user == null) {
            result.put("message", "User does not exist");
            return result;
        }
        Module findModule = moduleRepository.findByCreatorIdAndName(user.getId(), request.getName());
        if (findModule != null) {
            result.put("message", "moduleName " + request.getName() + " already exists");
            return result;
        } else if (Utils.isBasicInstruction(request.getName())) {
            result.put("message", "moduleName " + request.getName() + " conflicts with basic instruction ");
            return result;
        }
        Module newModule = new Module(user.getId(), request.getName(), "whatever");
        moduleRepository.save(newModule);
        result.put("message", "success");
        result.put("name", newModule.getName());
        return result;
    }

    public Map<String, Object> moduleUpdate(UserDefineModuleRequest request) {
        Map<String, Object> result = new HashMap<>();
        String token = request.getToken();
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        if (user == null) {
            result.put("message", "User does not exist");
            return result;
        }
        Module findModule = moduleRepository.findByCreatorIdAndName(user.getId(), request.getName());
        if (findModule == null) {
            result.put("message", "moduleName " + request.getName() + " does not exist");
            return result;
        }
        findModule.setInstructions(request.getInstructions());
        moduleRepository.save(findModule);
        result.put("message", "success");
        return result;
    }

    public Map<String, Object> moduleName(UserDefineModuleRequest request) {
        Map<String, Object> result = new HashMap<>();
        String token = request.getToken();
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        if (user == null) {
            result.put("message", "User does not exist");
            return result;
        }
        Module findModule = moduleRepository.findByCreatorIdAndName(user.getId(), request.getName());
        if (findModule == null) {
            result.put("message", "moduleName " + request.getName() + " does not exist");
            return result;
        }
        result.put("message", "success");
        result.put("module", findModule);
        return result;
    }

    public Map<String, Object> solveModule(SolveModuleRequest request) {
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
