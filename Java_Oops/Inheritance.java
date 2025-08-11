package Java_Oops;

public class Inheritance {
    public static void main(String[] args) {
        fish f1 = new fish();
        f1.color = "Gold";
        f1.fins = 2;
        System.out.println("Fish color: " + f1.color);
        System.out.println("Fish fins: " + f1.fins);
        f1.eat();
        f1.sleep();
        f1.swim();
    }
    
}

class Animal {
    String color;

    void eat() {
        System.out.println("Eating...");
    }

    void sleep() {
        System.out.println("Sleeping...");
    }
}
class fish extends Animal {
    int fins;

    void swim() {
        System.out.println("Swimming...");
    }
}
