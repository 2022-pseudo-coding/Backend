package WEB3D.service;

import WEB3D.controller.request.MapSolvedRequest;
import WEB3D.controller.request.ProblemRequest;
import WEB3D.controller.request.SolveRequest;
import WEB3D.domain.Instruction;
import WEB3D.domain.Problem;
import WEB3D.domain.Solution;
import WEB3D.repository.InstructionRepository;
import WEB3D.repository.ProblemRepository;
import WEB3D.repository.SolutionRepository;
import WEB3D.repository.UserRepository;
import WEB3D.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProblemService {
    private final UserRepository userRepository;
    private final ProblemRepository problemRepository;
    private final SolutionRepository solutionRepository;
    private final InstructionRepository instructionRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public ProblemService(UserRepository userRepository, ProblemRepository problemRepository, SolutionRepository solutionRepository, InstructionRepository instructionRepository) {
        this.userRepository = userRepository;
        this.problemRepository = problemRepository;
        this.solutionRepository = solutionRepository;
        this.instructionRepository = instructionRepository;
    }

    public Map<String, Object> problem(ProblemRequest problemRequest) {
        Map<String, Object> result = new HashMap<>();
        Problem problem = problemRepository.findByStageAndNumber(problemRequest.getStage(), problemRequest.getNumber());
        if (problem != null) {
            result.put("problem", problem);
            result.put("message", "success");
        }
        else {
            result.put("message", "problem not found");
        }
        return result;
    }

    public Map<String, Object> mapSolved(MapSolvedRequest mapSolvedRequest) {
        Map<String, Object> result = new HashMap<>();

        return result;
    }

    public Map<String, Object> solve(SolveRequest solveRequest) {
        Map<String, Object> result = new HashMap<>();

        return result;
    }

}
