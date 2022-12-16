package WEB3D.controller;

import WEB3D.controller.request.ProjectRequest;
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

    @PostMapping("/projectCreate")
    public ResponseEntity<?> projectCreate(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.projectCreate(request));
    }
    @PostMapping("/project")
    public ResponseEntity<?> project(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(projectService.project(request));
    }
}