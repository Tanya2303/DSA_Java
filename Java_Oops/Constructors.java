package Java_Oops;

public class Constructors {
    public static void main(String[] args) {
        Student student1 = new Student("Alice", 21);
        System.out.println(student1.name + " is " + student1.age + " years old.");
    }
}

class Student{
    String name;
    int age;

    // Constructor
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Method to display student details
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}
