package WEB3D.controller;

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

    @PostMapping("/module/create")
    public ResponseEntity<?> createModule(@RequestBody UserDefineModuleRequest request) {
        return ResponseEntity.ok(moduleService.moduleCreate(request));
    }

    @PostMapping("/module/update")
    public ResponseEntity<?> updateModule(@RequestBody UserDefineModuleRequest request) {
        return ResponseEntity.ok(moduleService.moduleUpdate(request));
    }

    @PostMapping("/module/name")
    public ResponseEntity<?> getModule(@RequestBody UserDefineModuleRequest request) {
        return ResponseEntity.ok(moduleService.moduleName(request));
    }

    @PostMapping("/solveModule")
    public ResponseEntity<?> problem(@RequestBody SolveModuleRequest request) {
        return ResponseEntity.ok(moduleService.solveModule(request));
    }
}
