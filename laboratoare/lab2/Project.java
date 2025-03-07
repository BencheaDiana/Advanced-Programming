package studentalloc;


public class Project {
    private String id;
    private ProjectType type;
    
    public Project(String id, ProjectType type) {
        this.id = id;
        this.type = type;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public ProjectType getType() {
        return type;
    }
    
    public void setType(ProjectType type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", type=" + type +
                '}';
    }
}
