package project.mgmt.domain.model.project_mgmt.location;

public enum Location {
    CN("中国");

    private String name;

    public String getName() {
        return this.name;
    }

    Location(String name) {
        this.name = name;
    }
}
