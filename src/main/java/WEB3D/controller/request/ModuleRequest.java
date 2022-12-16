package WEB3D.controller.request;

import WEB3D.repository.ModuleRepository;

public class ModuleRequest {
    private String token;
    private String name;

    public ModuleRequest(){

    }
    public ModuleRequest(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
