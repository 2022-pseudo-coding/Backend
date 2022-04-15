package WEB3D.controller;

import WEB3D.controller.request.LoginRequest;
import WEB3D.controller.request.RegisterRequest;
import WEB3D.security.jwt.JwtTokenUtil;
import WEB3D.service.AuthService;
import WEB3D.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class AuthController {

    private final AuthService authService;
    private final UserService readerService;
    private final JwtTokenUtil jwtTokenUtil;

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AuthService authService, UserService readerService, JwtTokenUtil jwtTokenUtil) {
        this.authService = authService;
        this.readerService = readerService;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        logger.debug("RegistrationForm: " + request.toString());
        return ResponseEntity.ok(readerService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.debug("LoginForm: " + request.toString());
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam String password,
                                            @RequestParam String rePassword,
                                            @RequestParam String token){
        logger.debug("Change password: User token = " + token);
        return ResponseEntity.ok(authService.changePassword(password,rePassword,token));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> checkToken(){
        logger.debug("Expire user token");
        return ResponseEntity.ok(new HashMap<>());
    }
}
