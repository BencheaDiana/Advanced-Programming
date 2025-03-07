package studentalloc;

public class main {

	public static void main(String[] args) {
		Project p1 = new Project("P1", ProjectType.PRACTICAL);
        Project p2 = new Project("P3",ProjectType.PRACTICAL);
        Project p3 = new Project("P2",ProjectType.THEORETICAL);
        Project p4 = new Project("P4",ProjectType.THEORETICAL);
        
        Student s1 = new Student("Diana");
        s1.addPreferredProject(p1);
        s1.addPreferredProject(p2);
        Student s2 = new Student("Dragos");
        s2.addPreferredProject(p1);
        s2.addPreferredProject(p3);
        Student s3 = new Student("Alex");
        s3.addPreferredProject(p3);
        s3.addPreferredProject(p4);;
        Student s4 = new Student("Delia");
        s4.addPreferredProject(p1);
        s4.addPreferredProject(p4);
      
        s1.setAssignedProject(p2);
        s2.setAssignedProject(p1);
        s3.setAssignedProject(p3);
        s4.setAssignedProject(p4);
        
        // Print objects
        System.out.println("Projects:");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        
        System.out.println("\n Students:");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
    }

}
