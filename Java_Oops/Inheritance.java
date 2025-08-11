package Java_Oops;

public class Inheritance {
     public static void main(String[] args) {

        // Single Inheritance
        Animal a = new Animal();
        a.eat();
        System.out.println();

        // Multilevel Inheritance
        Dog dog = new Dog();
        dog.eat(); // Animal
        dog.walk(); // Mammal
        dog.bark(); // Dog
        System.out.println();

        // Hierarchical Inheritance
        Cat cat = new Cat();
        cat.eat(); // Animal
        cat.walk(); // Mammal
        cat.meow(); // Cat
        System.out.println();

        // Multiple + Hybrid Inheritance via interfaces
        Fish fish = new Fish();
        fish.eat(); // Animal
        fish.finsCount(); // Fish
        fish.owner(); // Pet interface
        fish.swim(); // Swimmer interface
    }
    
}

// SINGLE INHERITANCE: One class inherits from one parent
class Animal {
    void eat() { System.out.println("Animal is eating."); }
}

// MULTILEVEL INHERITANCE: Chain of inheritance
class Mammal extends Animal {
    void walk() { System.out.println("Mammal is walking."); }
}
class Dog extends Mammal {
    void bark() { System.out.println("Dog is barking."); }
}

// HIERARCHICAL INHERITANCE: Multiple classes inherit from one parent
class Cat extends Mammal {
    void meow() { System.out.println("Cat is meowing."); }
}

// MULTIPLE INHERITANCE (Via INTERFACES)
interface Pet {
    void owner();
}
interface Swimmer {
    void swim();
}

// HYBRID INHERITANCE: Combination (class & interface)
class Fish extends Animal implements Pet, Swimmer { // Hybrid
    void finsCount() { System.out.println("Fish has fins."); }

    public void owner() { System.out.println("Fish has an owner."); }
    public void swim() { System.out.println("Fish is swimming."); }
}