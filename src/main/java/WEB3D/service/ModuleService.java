package WEB3D.service;

import WEB3D.controller.request.UserDefineModuleRequest;
import WEB3D.domain.Module;
import WEB3D.domain.User;
import WEB3D.repository.ModuleRepository;
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
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public ModuleService(UserRepository userRepository, ModuleRepository moduleRepository) {
        this.userRepository = userRepository;
        this.moduleRepository = moduleRepository;
    }
    public Map<String, Object> userDefineModule(UserDefineModuleRequest request){
        Map<String, Object> result = new HashMap<>();
        String token = request.getToken();
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        if (user == null) {
            result.put("message", "User does not exist");
            return result;
        }
        //缺少与project的级联；
        Module newModule=new Module(request.getTitle(),request.getDescription());
        moduleRepository.save(newModule);
        result.put("message","success");
        return result;
    }
}
