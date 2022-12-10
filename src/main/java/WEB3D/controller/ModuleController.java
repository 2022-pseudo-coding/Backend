package WEB3D.controller;

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
}
