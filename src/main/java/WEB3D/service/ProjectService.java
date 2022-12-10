package WEB3D.service;

import WEB3D.controller.request.ProjectRequest;
import WEB3D.domain.User;
import WEB3D.repository.UserRepository;
import WEB3D.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProjectService {

    private final UserRepository userRepository;

    @Autowired
    public ProjectService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return result;
    }
}
