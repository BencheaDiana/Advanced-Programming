package studentalloc;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Project> preferredProjects;
    private Project assignedProject;
    
    public Student(String name) {
        this.name = name;
        this.preferredProjects = new ArrayList<>();
        this.assignedProject = null;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Project> getPreferredProjects() {
        return preferredProjects;
    }
    
    public void setPreferredProjects(List<Project> preferredProjects) {
        this.preferredProjects = preferredProjects;
    }
    
    public Project getAssignedProject() {
        return assignedProject;
    }
    
    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }
    
    public void addPreferredProject(Project project) {
        if (!preferredProjects.contains(project)) {
            preferredProjects.add(project);
        }
    }
    
    public boolean isProjectAcceptable(Project project) {
        return preferredProjects.contains(project);
    }
    
    @Override
    public String toString() {
        return "Student{" +
                " name='" + name + '\'' +
                ", preferredProjects=" + preferredProjects +
                ", assignedProject=" + assignedProject +
                '}';
    }
}
