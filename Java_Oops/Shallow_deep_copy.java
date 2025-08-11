package Java_Oops;

public class Shallow_deep_copy {
    public static void main(String[] args) {
        // Creating an original Student object
        Stud s1 = new Stud("Alice", 20, "password123");
        System.out.println("Original Student:");
        s1.display();

        // Shallow copy
        Stud s2 = s1.shallowCopy();
        System.out.println("Shallow Copied Student:");
        s2.display();

        // Deep copy
        Stud s3 = s1.deepCopy();
        System.out.println("Deep Copied Student:");
        s3.display();

        // Modifying the original object        
        s1.name = "Bob";
        s1.age = 21;
        s1.password = "newpassword456";

        System.out.println("After modifying original Student:");
        System.out.println("Original Student:");
        s1.display();
        System.out.println("Shallow Copied Student:");
        s2.display();
        System.out.println("Deep Copied Student:");
        s3.display();
    }
    
}
class Stud {
    String name;
    int age;
    String password;
    
    public Stud(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
    // Shallow copy constructor 
    public Stud shallowCopy() {
        return this;
    }
    // Deep copy constructor
    public Stud deepCopy() {
        return new Stud(this.name, this.age, this.password);
    }
    public void display() {
        System.out.println("Name: " + name + ", Age: " + age + ", Password: " + password);
    }   
}