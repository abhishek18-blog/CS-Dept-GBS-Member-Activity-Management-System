import java.util.Scanner;

public class CSTGBS {
    // Static member counter
    static int memberCount = 0;

    // Static method
    public static void displayMemberCount() {
        System.out.println("Total members created: " + memberCount);
    }

    // Base class with access modifiers
    static class Member {
        protected int id;        // protected
        public String role;      // public
        private String name;     // private

        Member(int id, String name, String role) {
            this.id = id;
            this.name = name;
            this.role = role;
            memberCount++;
        }

        public String getName() { return name; }

        public void getDetails() {
            System.out.println(role + ": ID=" + id + ", Name=" + getName());
        }
    }

    // Inheritance: Student
    static class Student extends Member {
        String course;
        int year;

        Student(int id, String name, String course) {
            this(id, name, course, 1);
        }
        Student(int id, String name, String course, int year) {
            super(id, name, "Student");
            this.course = course;
            this.year = year;
        }
        @Override
        public void getDetails() {
            System.out.println("Student: ID=" + id + ", Name=" + getName() + ", Course=" + course + ", Year=" + year);
        }
    }

    // Teaching Staff
    static class TeachingStaff extends Member {
        String subject;
        public TeachingStaff(int id, String name, String subject) {
            super(id, name, "Teaching Staff");
            this.subject = subject;
        }
        @Override
        public void getDetails() {
            System.out.println("Teaching Staff: " + getName() + " teaches " + subject);
        }
    }

    // NonTeachingStaff
    static class NonTeachingStaff extends Member {
        protected String department;
        public NonTeachingStaff(int id, String name, String department) {
            super(id, name, "Non-Teaching Staff");
            this.department = department;
        }
        @Override
        public void getDetails() {
            System.out.println("Non-Teaching Staff: " + getName() + ", Department: " + department);
        }
    }

    // Enums for Activities & Programs
    enum AcademicActivity { ISA, SEA, STUDY_TOUR, PLACEMENTS, WORKSHOP, SEMINAR }
    enum NonAcademicActivity { INFOFEST, PLATEAUNIA, PLAY_ON, SPORTS_DAY, CULTURAL_NIGHT }
    enum Program { BSC_CS, MSC_AI, MCA, MSC_DS, DIPLOMA_IT }

    // Abstract & Interface
    static abstract class Activity {
        String name;
        Activity(String name) { this.name = name; }
        abstract void perform();
    }

    interface Display {
        void displayInfo();
    }

    static class Academic extends Activity implements Display {
        Academic(String name) { super(name); }
        void perform() {
            System.out.println(name + " is an academic activity.");
        }
        public void displayInfo() {
            System.out.println("[Display] Academic Activity: " + name);
        }
    }

    static class NonAcademic extends Activity implements Display {
        NonAcademic(String name) { super(name); }
        void perform() {
            System.out.println(name + " is a non-academic activity.");
        }
        public void displayInfo() {
            System.out.println("[Display] Non-Academic Activity: " + name);
        }
    }

    // Workshop class as specialized Academic Activity
    static class Workshop extends Academic {
        String topic;
        String resourcePerson;
        Workshop(String topic, String resourcePerson) {
            super("WORKSHOP");
            this.topic = topic;
            this.resourcePerson = resourcePerson;
        }
        @Override
        void perform() {
            System.out.println("Workshop on " + topic + " by " + resourcePerson + " is being conducted.");
        }
        @Override
        public void displayInfo() {
            System.out.println("[Display] Workshop: " + topic + " | Resource Person: " + resourcePerson);
        }
    }

    // Message passing: Accept any Display object
    static void processDisplay(Display d) {
        d.displayInfo();
    }

    // Data for menu
    static Student[] students = {
        new Student(201, "Alice", "BSC_CS", 2),
        new Student(202, "Bob", "MSC_AI", 1)
    };
    static TeachingStaff[] teachers = {
        new TeachingStaff(301, "Dr. Smith", "DSA"),
        new TeachingStaff(302, "Dr. Desai", "AI")
    };
    static NonTeachingStaff[] nonTeachers = {
        new NonTeachingStaff(401, "Mr. Naik", "Library"),
        new NonTeachingStaff(402, "Ms. Rao", "Admin")
    };

    // Display menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- CST GBS Menu ---");
            System.out.println("1. Display member details");
            System.out.println("2. Display academic activities");
            System.out.println("3. Display non-academic activities");
            System.out.println("4. Display programs/courses");
            System.out.println("5. Demonstrate Workshop feature");
            System.out.println("6. Demonstrate Cultural Night feature");
            System.out.println("7. Show members counter");
            System.out.println("8. Object referencing demo");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1: // Member details
                    System.out.println("Students:");
                    for (Student s : students) s.getDetails();
                    System.out.println("Teaching Staff:");
                    for (TeachingStaff t : teachers) t.getDetails();
                    System.out.println("Non-Teaching Staff:");
                    for (NonTeachingStaff nt : nonTeachers) nt.getDetails();
                    break;
                case 2: // Academic activities
                    System.out.println("Academic Activities at CST, GBS:");
                    for (AcademicActivity act : AcademicActivity.values()) {
                        if (act == AcademicActivity.WORKSHOP) {
                            Workshop ws = new Workshop("Data Science Applications", "Prof. Desai");
                            ws.perform();
                            processDisplay(ws);
                        } else {
                            Academic a = new Academic(act.name());
                            a.perform();
                        }
                    }
                    break;
                case 3: // Non-academic activities
                    System.out.println("Non-Academic Activities at CST, GBS:");
                    for (NonAcademicActivity act : NonAcademicActivity.values()) {
                        NonAcademic na = new NonAcademic(act.name());
                        na.perform();
                        if (act == NonAcademicActivity.CULTURAL_NIGHT) {
                            processDisplay(na);
                        }
                    }
                    break;
                case 4: // Programs/Courses
                    System.out.println("Programs & Courses offered by CST, GBS:");
                    for (Program p : Program.values()) {
                        System.out.println("- " + p);
                    }
                    break;
                case 5: // Workshop feature
                    Workshop w = new Workshop("Machine Learning Best Practices", "Dr. Kamat");
                    w.perform();
                    w.displayInfo();
                    break;
                case 6: // Cultural Night feature
                    NonAcademic cnight = new NonAcademic("CULTURAL_NIGHT");
                    cnight.perform();
                    cnight.displayInfo();
                    break;
                case 7: // Members counter
                    displayMemberCount();
                    break;
                case 8: // Object referencing demo
                    Student s1 = students[0];
                    Student s3 = s1;
                    s1.year = 3;
                    System.out.println("s3.year (side effect via referencing): " + s3.year);
                    break;
                case 9:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 9);
        sc.close();
    }
}
