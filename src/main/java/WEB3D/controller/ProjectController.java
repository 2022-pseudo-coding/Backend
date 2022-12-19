package WEB3D.controller;

import WEB3D.controller.request.ProjectRequest;
import WEB3D.controller.request.SolveModuleRequest;
import WEB3D.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/project/create")
    public ResponseEntity<?> projectCreate(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.projectCreate(request));
    }

    @PostMapping("/project/update")
    public ResponseEntity<?> projectUpdate(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.projectUpdate(request));
    }

    @PostMapping("/project/user")
    public ResponseEntity<?> projectByUser(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.projectByUser(request));
    }

    @PostMapping("/project/id")
    public ResponseEntity<?> projectById(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.projectById(request));
    }

    @PostMapping("/project/solve")
    public ResponseEntity<?> solveProject(@RequestBody SolveModuleRequest request) {
        return ResponseEntity.ok(projectService.solveProject(request));
    }
}
