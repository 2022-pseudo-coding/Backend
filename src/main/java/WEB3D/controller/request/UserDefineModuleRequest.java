package WEB3D.controller.request;

public class UserDefineModuleRequest {
    private String token;
    private int projectNumber;
    private String title;
    private String description;

    public UserDefineModuleRequest(String token, int projectNumber, String title, String description) {
        this.token = token;
        projectNumber = projectNumber;
        this.title = title;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getToken() {
        return token;
    }

    public int getProjectNumber() {
        return projectNumber;
    }
}
