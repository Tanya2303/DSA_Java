package Java_Oops;

public class Constructors {
    public static void main(String[] args) {
        Stu s1 = new Stu(); // Non-parameterized constructor
        Stu s2 = new Stu("Alice", 22); // Parameterized constructor
        System.out.println("Student 1: Name = " + s1.name + ", Age = " + s1.age);
        System.out.println("Student 2: Name = " + s2.name + ", Age = " + s2.age);   
        s1.name = "John"; // Setting name for s1
        s1.age = 20; // Setting age for s1
        Stu s3 = new Stu(s1.name, s1.age); // Using s1's data to create a new Student
        System.out.println("Student 3: Name = " + s3.name + ", Age = " + s3.age);
    }
}

class Stu{
    String name;
    int age;

    // Constructor
    public Stu(String name, int age) { // Parameterized constructor
        this.name = name;
        this.age = age;
    }
    // Non-parameterized constructor
    public Stu() {
        System.out.println("Default constructor called");
    }
}
