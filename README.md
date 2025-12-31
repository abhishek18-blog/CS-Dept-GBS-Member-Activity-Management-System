🎓 CST GBS: Academic Management System
This is a comprehensive Java-based console application designed to model the organizational structure of a college department. It focuses on the relationship between members (Students/Staff) and the various activities (Academic/Non-Academic) that define a campus ecosystem.

🛠 Project Architecture (The Honest Truth)
This project isn't just a menu; it is an implementation of Clean Code principles and Phase 3 Java concepts.

1. The Inheritance Hierarchy
      Instead of writing separate code for Students and Teachers, I used a Base-Class strategy. Every person is a Member.
      Member (Base): Handles common data like ID and Name.
      Student/Staff (Derived): Handles specific data like Courses or Subjects.
      Benefit: If I want to add a "Date of Birth" field later, I only have to add it to the Member class once, and everyone gets it automatically.

2. Abstraction vs. Interface
      I used both to show different types of "Contracts":
      Abstract Class (Activity): Used for things that are an activity (Inheritance).
      Interface (Display): Used for a behavior (Action). This allows any object (even unrelated ones) to be "Displayable" through the processDisplay method.

3. Static vs. Instance
       I implemented a static memberCount. This is honest memory management—it doesn't belong to a single student; it belongs to the entire school. This ensures that no matter how many objects are created, the count remains accurate across the whole system.
