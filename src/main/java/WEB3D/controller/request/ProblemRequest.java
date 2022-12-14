package WEB3D.controller.request;

public class ProblemRequest {
    private String projectId;
    private String stage;
    private String number;

    public ProblemRequest() {
    }

    public ProblemRequest(String projectId, String stage, String number) {
        this.projectId = projectId;
        this.stage = stage;
        this.number = number;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getStage() {
        return stage;
    }

    public String getNumber() {
        return number;
    }
}
