package WEB3D.service;

import WEB3D.controller.request.MapSolvedRequest;
import WEB3D.controller.request.ProblemRequest;
import WEB3D.controller.request.SolveRequest;
import WEB3D.domain.*;
import WEB3D.common.Utils;
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

import static WEB3D.common.Utils.isNumeric;

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
        if (!isNumeric(problemRequest.getStage()) || !isNumeric(problemRequest.getNumber())){
            result.put("message", "bad argument");
            return result;
        }
        int stage = Integer.parseInt(problemRequest.getStage());
        int number = Integer.parseInt(problemRequest.getNumber());
        Problem problem = problemRepository.findByStageAndNumber(stage, number);
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
        if (!isNumeric(solveRequest.getStage()) || !isNumeric(solveRequest.getNumber())){
            result.put("message", "bad argument");
            return result;
        }
        int stage = Integer.parseInt(solveRequest.getStage());
        int number = Integer.parseInt(solveRequest.getNumber());
        Problem problem = problemRepository.findByStageAndNumber(stage, number);
        List<Instruction> instructions = solveRequest.getInstructions();
        User user = userRepository.findByUsername(jwtTokenUtil.getUsernameFromToken(solveRequest.getToken()));
        List<Status> statusList = new ArrayList<>();
        try {
            statusList = Utils.execInstructions(problem, instructions);
        }
        catch (Exception e) {
            result.put("message", e.getMessage());
            return result;
        }
        Status finalStatus = statusList.get(statusList.size() - 1);
        if (finalStatus.getFinishStatusMsg().equals("success!")) {
            int steps = finalStatus.getSteps();
            Solution solution = new Solution(stage, number, steps, instructions.size(), instructions);
            user.addSolution(solution);
            userRepository.save(user);
            problem.addSolution(solution);
            problemRepository.save(problem);
            result.put("message", "success");
        }
        else
            result.put("message", finalStatus.getFinishStatusMsg());

        result.put("statusList", statusList);
        return result;
    }
}
