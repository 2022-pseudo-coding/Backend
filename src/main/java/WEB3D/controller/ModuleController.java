package WEB3D.controller;

import WEB3D.controller.request.ModuleRequest;
import WEB3D.controller.request.SolveModuleRequest;
import WEB3D.controller.request.UserDefineModuleRequest;
import WEB3D.service.ModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModuleController {
    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping("/userDefineModule")
    public ResponseEntity<?> problem(@RequestBody UserDefineModuleRequest request) {
        return ResponseEntity.ok(moduleService.userDefineModule(request));
    }
    @PostMapping("/module")
    public ResponseEntity<?> problem(@RequestBody ModuleRequest request) {
        return ResponseEntity.ok(moduleService.module(request));
    }
    @PostMapping("/solveModule")
    public ResponseEntity<?> problem(@RequestBody SolveModuleRequest request) {
        return ResponseEntity.ok(moduleService.solveModule(request));
    }
}
