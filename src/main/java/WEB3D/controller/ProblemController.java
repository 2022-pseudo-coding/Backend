package WEB3D.controller;

import WEB3D.controller.request.MapSolvedRequest;
import WEB3D.controller.request.ProblemRequest;
import WEB3D.controller.request.SolveRequest;
import WEB3D.domain.Problem;
import WEB3D.security.jwt.JwtTokenUtil;
import WEB3D.service.AuthService;
import WEB3D.service.ProblemService;
import WEB3D.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemController {

    private final AuthService authService;
    private final UserService userService;
    private final ProblemService problemService;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public ProblemController(AuthService authService, UserService userService, ProblemService problemService, JwtTokenUtil jwtTokenUtil) {
        this.authService = authService;
        this.userService = userService;
        this.problemService = problemService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping("/problem")
    public ResponseEntity<?> problem(@RequestBody ProblemRequest request) {
        return ResponseEntity.ok(problemService.problem(request));
    }

    @GetMapping("/mapSolved")
    public ResponseEntity<?> mapSolved(@RequestBody MapSolvedRequest request) {
        return ResponseEntity.ok(problemService.mapSolved(request));
    }

    @PostMapping("/solve")
    public ResponseEntity<?> solve(@RequestBody SolveRequest request) {
        return ResponseEntity.ok(problemService.solve(request));
    }

}
