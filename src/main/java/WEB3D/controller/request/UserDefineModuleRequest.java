package WEB3D.controller.request;

public class UserDefineModuleRequest {
    private String token;
    private int ProjectNumber;
    private String title;
    private String description;

    public UserDefineModuleRequest(String token, int projectNumber, String title, String description) {
        this.token = token;
        ProjectNumber = projectNumber;
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
        return ProjectNumber;
    }
}
