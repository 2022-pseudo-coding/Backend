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
        //test
        Map<String, Object> result = new HashMap<>();
        List<Instruction> list = new ArrayList<>();
        list.add(new Instruction("1", "2", true, 0, 0));
        list.add(new Instruction("2", "3", true, 1, 10));
        Problem problem  = new Problem("title", "description", list);
        list.add(new Instruction("5", "6", true, 1, 10));
        problem.addSolution(new Solution("1", 10, 10, list));
        result.put("problem", problem);
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
