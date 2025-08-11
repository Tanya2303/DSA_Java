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
        System.out.println();
        dogs d1 = new dogs();
        
        d1.color = "Brown";
        d1.legs = 4;
        d1.breed = "Labrador";
        System.out.println("Dog color: " + d1.color);
        System.out.println("Dog legs: " + d1.legs);
        System.out.println("Dog breed: " + d1.breed);
        d1.eat();
        d1.sleep();
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

class mammal extends Animal {
    int legs;

    void walk() {
        System.out.println("Walking...");
    }
}
class dogs extends mammal {
    String breed;

    void bark() {
        System.out.println("Barking...");
    }
}

class fish extends Animal {
    int fins;

    void swim() {
        System.out.println("Swimming...");
    }
}
