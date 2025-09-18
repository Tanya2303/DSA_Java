# Complete Object-Oriented Programming Mastery Roadmap

## Stage 1: Fundamentals (Week 1-2)

### What is Object-Oriented Programming?

**Definition:** OOP is a programming paradigm that organizes code around objects (data) and methods (functions) rather than functions and logic. It models real-world entities as software objects that interact with each other.

### Why OOP Exists

**Historical Context:**
- **1960s-70s:** Procedural programming dominated (C, Pascal, COBOL)
- **Problems with large systems:** Code became unmanageable, hard to maintain, and difficult to reuse
- **1980s:** OOP emerged to solve these issues (Smalltalk, C++, later Java)

**Core Problems OOP Solves:**
1. **Code Reusability** - Write once, use many times
2. **Maintainability** - Easier to modify and debug
3. **Scalability** - Better for large, complex systems
4. **Real-world modeling** - Natural way to represent entities
5. **Team collaboration** - Better code organization for multiple developers

### Procedural vs Object-Oriented Programming

#### Procedural Programming Example (C-style):
```c
// Student data as separate variables
char student_name[50];
int student_age;
float student_gpa;

// Functions that operate on data
void display_student(char name[], int age, float gpa) {
    printf("Name: %s, Age: %d, GPA: %.2f\n", name, age, gpa);
}

void update_gpa(float* gpa, float new_gpa) {
    *gpa = new_gpa;
}
```

#### Object-Oriented Programming Example (Java):
```java
// Student as an object with data and methods together
public class Student {
    private String name;
    private int age;
    private float gpa;
    
    public Student(String name, int age, float gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }
    
    public void displayStudent() {
        System.out.println("Name: " + name + ", Age: " + age + ", GPA: " + gpa);
    }
    
    public void updateGpa(float newGpa) {
        this.gpa = newGpa;
    }
}
```

### Key Differences Table

| Aspect | Procedural | Object-Oriented |
|--------|------------|-----------------|
| **Focus** | Functions and procedures | Objects and classes |
| **Data Security** | Data is global, less secure | Data encapsulation, more secure |
| **Problem Approach** | Top-down approach | Bottom-up approach |
| **Code Reusability** | Limited reusability | High reusability through inheritance |
| **Maintenance** | Difficult for large programs | Easier maintenance |
| **Real-world Modeling** | Less natural | More natural |

### Fundamental OOP Concepts

**Class:** A blueprint or template for creating objects
```java
public class Car {  // This is a class
    // Properties (attributes)
    private String brand;
    private String model;
    private int year;
    
    // Methods (behaviors)
    public void start() {
        System.out.println("Car is starting...");
    }
}
```

**Object:** An instance of a class
```java
Car myCar = new Car();  // This creates an object
Car yourCar = new Car(); // This creates another object
```

**Key Terminology:**
- **Attribute/Field/Property:** Variables that store data
- **Method/Function:** Code that performs operations
- **Instance:** A specific object created from a class
- **Constructor:** Special method to initialize objects

---

## Stage 2: Core Principles (Week 3-6)

### 1. Encapsulation

**Definition:** Bundling data (attributes) and methods that operate on that data within a single unit (class), and restricting access to some components.

**Real-world Analogy:** A car's engine is encapsulated - you can use the car without knowing internal engine details. You interact through interfaces (steering wheel, pedals).

#### Java Implementation:
```java
public class BankAccount {
    // Private data - cannot be accessed directly
    private String accountNumber;
    private double balance;
    private String ownerName;
    
    // Constructor
    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }
    
    // Public methods - controlled access to data
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds");
            return false;
        }
    }
    
    // Read-only access to balance
    public double getBalance() {
        return balance;
    }
    
    // No setter for account number - it shouldn't change
    public String getAccountNumber() {
        return accountNumber;
    }
}
```

#### Python Implementation:
```python
class BankAccount:
    def __init__(self, account_number, owner_name, initial_balance):
        self._account_number = account_number  # Protected (convention)
        self._owner_name = owner_name
        self.__balance = initial_balance       # Private (name mangling)
    
    def deposit(self, amount):
        if amount > 0:
            self.__balance += amount
            print(f"Deposited: ${amount}")
        else:
            print("Invalid deposit amount")
    
    def withdraw(self, amount):
        if 0 < amount <= self.__balance:
            self.__balance -= amount
            print(f"Withdrawn: ${amount}")
            return True
        else:
            print("Invalid withdrawal amount or insufficient funds")
            return False
    
    @property
    def balance(self):
        return self.__balance
    
    @property
    def account_number(self):
        return self._account_number
```

**Benefits of Encapsulation:**
- **Data Protection:** Prevents unauthorized access
- **Controlled Access:** Validation in getters/setters
- **Internal Implementation Changes:** Can modify internals without affecting users
- **Code Maintainability:** Clear boundaries between components

### 2. Abstraction

**Definition:** Hiding complex implementation details while showing only essential features to the user.

**Real-world Analogy:** When you drive a car, you use abstractions - steering wheel, brake pedal. You don't need to know how the steering mechanism or braking system works internally.

#### Abstract Classes (Java):
```java
// Abstract class - cannot be instantiated directly
public abstract class Shape {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    // Abstract method - must be implemented by subclasses
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
    
    // Concrete method - can be inherited as-is
    public void displayColor() {
        System.out.println("Color: " + color);
    }
}

// Concrete implementation
public class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

public class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
    
    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}
```

#### Interfaces (Java):
```java
// Interface - pure abstraction
public interface Drawable {
    void draw();           // implicitly public and abstract
    void resize(double factor);
    
    // Default method (Java 8+)
    default void display() {
        System.out.println("Displaying the shape");
    }
    
    // Static method (Java 8+)
    static void printInfo() {
        System.out.println("This is a drawable object");
    }
}

public interface Colorable {
    void setColor(String color);
    String getColor();
}

// Class implementing multiple interfaces
public class Circle implements Drawable, Colorable {
    private double radius;
    private String color;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a circle with radius: " + radius);
    }
    
    @Override
    public void resize(double factor) {
        radius *= factor;
        System.out.println("Circle resized. New radius: " + radius);
    }
    
    @Override
    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String getColor() {
        return color;
    }
}
```

**Abstract Classes vs Interfaces:**

| Abstract Classes | Interfaces |
|------------------|------------|
| Can have concrete methods | All methods are abstract (except default/static in Java 8+) |
| Can have instance variables | Only constants (public static final) |
| Single inheritance | Multiple inheritance |
| Can have constructors | Cannot have constructors |
| Access modifiers allowed | All methods public by default |

### 3. Inheritance

**Definition:** A mechanism where a new class inherits properties and methods from an existing class.

**Real-world Analogy:** A child inherits traits from parents (eye color, height) but also has unique characteristics.

#### Single Inheritance (Java):
```java
// Base class (Parent/Superclass)
public class Animal {
    protected String name;
    protected int age;
    
    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
    
    public void makeSound() {
        System.out.println(name + " makes a sound");
    }
}

// Derived class (Child/Subclass)
public class Dog extends Animal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);  // Call parent constructor
        this.breed = breed;
    }
    
    // Method overriding
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
    
    // New method specific to Dog
    public void wagTail() {
        System.out.println(name + " is wagging tail");
    }
    
    // Method overloading
    public void play() {
        System.out.println(name + " is playing");
    }
    
    public void play(String toy) {
        System.out.println(name + " is playing with " + toy);
    }
}

public class Cat extends Animal {
    private boolean isIndoor;
    
    public Cat(String name, int age, boolean isIndoor) {
        super(name, age);
        this.isIndoor = isIndoor;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow! Meow!");
    }
    
    public void climb() {
        System.out.println(name + " is climbing");
    }
}
```

#### Multilevel Inheritance:
```java
public class Mammal extends Animal {
    protected boolean isWarmBlooded;
    
    public Mammal(String name, int age) {
        super(name, age);
        this.isWarmBlooded = true;
    }
    
    public void giveBirth() {
        System.out.println(name + " gives birth to live young");
    }
}

public class Dog extends Mammal {
    private String breed;
    
    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks");
    }
}
```

**Types of Inheritance:**
1. **Single:** One parent, one child
2. **Multilevel:** Chain of inheritance (A → B → C)
3. **Hierarchical:** Multiple children from one parent
4. **Multiple:** One child from multiple parents (not in Java, but in C++)
5. **Hybrid:** Combination of multiple types

### 4. Polymorphism

**Definition:** The ability of objects of different types to be treated as objects of a common base type, while still maintaining their specific behaviors.

**Real-world Analogy:** A person can be a student, employee, and citizen at the same time, behaving differently in each role.

#### Method Overriding (Runtime Polymorphism):
```java
public class PolymorphismDemo {
    public static void main(String[] args) {
        // Reference of parent type, objects of child types
        Animal[] animals = {
            new Dog("Buddy", 3, "Golden Retriever"),
            new Cat("Whiskers", 2, true),
            new Animal("Generic", 1)
        };
        
        // Polymorphic behavior - same method call, different implementations
        for (Animal animal : animals) {
            animal.makeSound();  // Calls overridden method based on actual object type
        }
        
        // Output:
        // Buddy barks: Woof! Woof!
        // Whiskers meows: Meow! Meow!
        // Generic makes a sound
    }
}
```

#### Method Overloading (Compile-time Polymorphism):
```java
public class Calculator {
    // Same method name, different parameters
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public String add(String a, String b) {
        return a + b;
    }
}
```

#### Interface-based Polymorphism:
```java
public interface PaymentProcessor {
    void processPayment(double amount);
}

public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        // Credit card specific logic
    }
}

public class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
        // PayPal specific logic
    }
}

public class BankTransferProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing bank transfer of $" + amount);
        // Bank transfer specific logic
    }
}

// Using polymorphism
public class PaymentService {
    public void makePayment(PaymentProcessor processor, double amount) {
        processor.processPayment(amount);  // Polymorphic call
    }
    
    public static void main(String[] args) {
        PaymentService service = new PaymentService();
        
        service.makePayment(new CreditCardProcessor(), 100.0);
        service.makePayment(new PayPalProcessor(), 75.0);
        service.makePayment(new BankTransferProcessor(), 200.0);
    }
}
```

**Benefits of Polymorphism:**
- **Code Flexibility:** Same interface, different implementations
- **Extensibility:** Add new types without modifying existing code
- **Maintainability:** Changes in implementation don't affect client code
- **Code Reusability:** Write code that works with multiple types

---

## Stage 3: Practical Usage (Week 7-10)

### Design Patterns

Design patterns are reusable solutions to common problems in software design. They represent best practices evolved over time.

#### 1. Singleton Pattern
**Problem:** Ensure only one instance of a class exists
**Real-world Example:** Database connection, logger, configuration settings

```java
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connectionString;
    
    // Private constructor prevents external instantiation
    private DatabaseConnection() {
        connectionString = "jdbc:mysql://localhost:3306/mydb";
        System.out.println("Database connection created");
    }
    
    // Thread-safe singleton implementation
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    public void executeQuery(String query) {
        System.out.println("Executing: " + query);
    }
}

// Better thread-safe implementation (Bill Pugh Solution)
public class DatabaseConnection {
    private DatabaseConnection() {}
    
    private static class SingletonHelper {
        private static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }
    
    public static DatabaseConnection getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```

#### 2. Factory Pattern
**Problem:** Create objects without specifying exact classes
**Real-world Example:** Creating different types of vehicles based on user input

```java
// Product interface
public interface Vehicle {
    void start();
    void stop();
}

// Concrete products
public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car engine started");
    }
    
    @Override
    public void stop() {
        System.out.println("Car engine stopped");
    }
}

public class Motorcycle implements Vehicle {
    @Override
    public void start() {
        System.out.println("Motorcycle engine started");
    }
    
    @Override
    public void stop() {
        System.out.println("Motorcycle engine stopped");
    }
}

// Factory
public class VehicleFactory {
    public static Vehicle createVehicle(String type) {
        switch (type.toLowerCase()) {
            case "car":
                return new Car();
            case "motorcycle":
                return new Motorcycle();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}

// Usage
public class FactoryPatternDemo {
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.createVehicle("car");
        Vehicle bike = VehicleFactory.createVehicle("motorcycle");
        
        car.start();
        bike.start();
    }
}
```

#### 3. Observer Pattern
**Problem:** Notify multiple objects about state changes
**Real-world Example:** News subscription, stock price updates

```java
import java.util.*;

// Subject interface
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Observer interface
public interface Observer {
    void update(String news);
}

// Concrete subject
public class NewsAgency implements Subject {
    private List<Observer> observers;
    private String latestNews;
    
    public NewsAgency() {
        observers = new ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(latestNews);
        }
    }
    
    public void setNews(String news) {
        this.latestNews = news;
        notifyObservers();
    }
}

// Concrete observer
public class NewsChannel implements Observer {
    private String channelName;
    
    public NewsChannel(String channelName) {
        this.channelName = channelName;
    }
    
    @Override
    public void update(String news) {
        System.out.println(channelName + " received news: " + news);
    }
}

// Usage
public class ObserverPatternDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        
        NewsChannel cnn = new NewsChannel("CNN");
        NewsChannel bbc = new NewsChannel("BBC");
        
        agency.registerObserver(cnn);
        agency.registerObserver(bbc);
        
        agency.setNews("Breaking: Major scientific discovery!");
        agency.setNews("Sports: World Cup finals tonight!");
    }
}
```

### SOLID Principles

#### 1. Single Responsibility Principle (SRP)
**Definition:** A class should have only one reason to change (one responsibility).

**Bad Example:**
```java
// Violates SRP - multiple responsibilities
public class Employee {
    private String name;
    private double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    // Responsibility 1: Employee data management
    public String getName() { return name; }
    public double getSalary() { return salary; }
    
    // Responsibility 2: Salary calculation
    public double calculateTax() {
        return salary * 0.2;
    }
    
    // Responsibility 3: Database operations
    public void saveToDatabase() {
        System.out.println("Saving employee to database");
    }
    
    // Responsibility 4: Report generation
    public void generatePaySlip() {
        System.out.println("Generating pay slip for " + name);
    }
}
```

**Good Example:**
```java
// Follows SRP - each class has single responsibility
public class Employee {
    private String name;
    private double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public String getName() { return name; }
    public double getSalary() { return salary; }
}

public class TaxCalculator {
    public double calculateTax(Employee employee) {
        return employee.getSalary() * 0.2;
    }
}

public class EmployeeRepository {
    public void save(Employee employee) {
        System.out.println("Saving employee to database");
    }
}

public class PaySlipGenerator {
    public void generate(Employee employee) {
        System.out.println("Generating pay slip for " + employee.getName());
    }
}
```

#### 2. Open/Closed Principle (OCP)
**Definition:** Classes should be open for extension but closed for modification.

**Bad Example:**
```java
// Violates OCP - need to modify existing code for new shapes
public class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Rectangle) {
            Rectangle rectangle = (Rectangle) shape;
            return rectangle.getWidth() * rectangle.getHeight();
        } else if (shape instanceof Circle) {
            Circle circle = (Circle) shape;
            return Math.PI * circle.getRadius() * circle.getRadius();
        }
        // Need to modify this method for every new shape
        return 0;
    }
}
```

**Good Example:**
```java
// Follows OCP - can add new shapes without modifying existing code
public abstract class Shape {
    public abstract double calculateArea();
}

public class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
}

public class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.calculateArea();  // Polymorphism
    }
}

// Adding new shape doesn't require modifying existing code
public class Triangle extends Shape {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}
```

#### 3. Liskov Substitution Principle (LSP)
**Definition:** Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.

**Bad Example:**
```java
// Violates LSP
public class Bird {
    public void fly() {
        System.out.println("Bird is flying");
    }
}

public class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}

// This will break
public class BirdTest {
    public void makeBirdFly(Bird bird) {
        bird.fly();  // Will throw exception for Penguin
    }
}
```

**Good Example:**
```java
// Follows LSP
public abstract class Bird {
    public abstract void move();
}

public class FlyingBird extends Bird {
    public void fly() {
        System.out.println("Flying bird is flying");
    }
    
    @Override
    public void move() {
        fly();
    }
}

public class SwimmingBird extends Bird {
    public void swim() {
        System.out.println("Swimming bird is swimming");
    }
    
    @Override
    public void move() {
        swim();
    }
}

public class Eagle extends FlyingBird {
    // Can safely substitute FlyingBird
}

public class Penguin extends SwimmingBird {
    // Can safely substitute SwimmingBird
}
```

#### 4. Interface Segregation Principle (ISP)
**Definition:** No client should be forced to depend on methods it does not use.

**Bad Example:**
```java
// Violates ISP - fat interface
public interface Worker {
    void work();
    void eat();
    void sleep();
    void takeBreak();
}

// Robot doesn't need eat, sleep, takeBreak
public class Robot implements Worker {
    @Override
    public void work() {
        System.out.println("Robot working");
    }
    
    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robots don't eat");
    }
    
    @Override
    public void sleep() {
        throw new UnsupportedOperationException("Robots don't sleep");
    }
    
    @Override
    public void takeBreak() {
        throw new UnsupportedOperationException("Robots don't take breaks");
    }
}
```

**Good Example:**
```java
// Follows ISP - segregated interfaces
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public interface Sleepable {
    void sleep();
}

public interface Breakable {
    void takeBreak();
}

public class Human implements Workable, Eatable, Sleepable, Breakable {
    @Override
    public void work() {
        System.out.println("Human working");
    }
    
    @Override
    public void eat() {
        System.out.println("Human eating");
    }
    
    @Override
    public void sleep() {
        System.out.println("Human sleeping");
    }
    
    @Override
    public void takeBreak() {
        System.out.println("Human taking break");
    }
}

public class Robot implements Workable {
    @Override
    public void work() {
        System.out.println("Robot working");
    }
}
```

#### 5. Dependency Inversion Principle (DIP)
**Definition:** High-level modules should not depend on low-level modules. Both should depend on abstractions.

**Bad Example:**
```java
// Violates DIP - high-level class depends on low-level class directly
public class MySQLDatabase {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

public class UserService {
    private MySQLDatabase database;  // Direct dependency on concrete class
    
    public UserService() {
        this.database = new MySQLDatabase();  // Tight coupling
    }
    
    public void saveUser(String userData) {
        database.save(userData);
    }
}
```

**Good Example:**
```java
// Follows DIP - depends on abstraction
public interface Database {
    void save(String data);
}

public class MySQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
}

public class PostgreSQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to PostgreSQL: " + data);
    }
}

public class UserService {
    private Database database;  // Depends on abstraction
    
    // Dependency injection through constructor
    public UserService(Database database) {
        this.database = database;
    }
    
    public void saveUser(String userData) {
        database.save(userData);
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        // Can switch database implementations easily
        UserService service1 = new UserService(new MySQLDatabase());
        UserService service2 = new UserService(new PostgreSQLDatabase());
        
        service1.saveUser("User data");
        service2.saveUser("User data");
    }
}
```

### Best Practices

#### 1. Favor Composition over Inheritance
```java
// Instead of inheritance
public class Car extends Engine {
    // Car "is-a" Engine - doesn't make sense
}

// Use composition
public class Car {
    private Engine engine;  // Car "has-a" Engine - makes sense
    private Wheels wheels;
    
    public Car(Engine engine, Wheels wheels) {
        this.engine = engine;
        this.wheels = wheels;
    }
    
    public void start() {
        engine.start();
    }
}
```

#### 2. Program to Interfaces, not Implementations
```java
// Bad
ArrayList<String> names = new ArrayList<>();

// Good
List<String> names = new ArrayList<>();
```

#### 3. Use Meaningful Names
```java
// Bad
public class D {
    private int d;
    public void process(int x) { /* ... */ }
}

// Good
public class Employee {
    private int employeeId;
    public void calculateSalary(int hoursWorked) { /* ... */ }
}
```

---

## Stage 4: Common Pitfalls and Anti-patterns (Week 11-12)

### Common OOP Pitfalls

#### 1. God Object/Class
**Problem:** A class that knows too much or does too much.

**Example of God Class:**
```java
// Anti-pattern: God Class
public class OrderManager {
    // Order management
    private List<Order> orders;
    
    // Customer management
    private Map<String, Customer> customers;
    
    // Inventory management
    private Map<String, Integer> inventory;
    
    // Payment processing
    private PaymentGateway gateway;
    
    // Email service
    private EmailService emailService;
    
    // Reporting
    private ReportGenerator reportGenerator;
    
    // Does everything - violates SRP
    public void createOrder(String customerId, List<Product> products) {
        // Validate customer
        if (!customers.containsKey(customerId)) {
            throw new IllegalArgumentException("Customer not found");
        }
        
        // Check inventory
        for (Product product : products) {
            if (inventory.get(product.getId()) < product.getQuantity()) {
                throw new IllegalStateException("Insufficient inventory");
            }
        }
        
        // Create order
        Order order = new Order(customerId, products);
        orders.add(order);
        
        // Process payment
        gateway.processPayment(order.getTotal());
        
        // Update inventory
        for (Product product : products) {
            inventory.put(product.getId(), 
                inventory.get(product.getId()) - product.getQuantity());
        }
        
        // Send confirmation email
        emailService.sendOrderConfirmation(customerId, order);
        
        // Generate report
        reportGenerator.addOrderToReport(order);
    }
}
```

**Better Solution - Separate Responsibilities:**
```java
public class OrderService {
    private CustomerService customerService;
    private InventoryService inventoryService;
    private PaymentService paymentService;
    private EmailService emailService;
    private OrderRepository orderRepository;
    
    public OrderService(CustomerService customerService, 
                       InventoryService inventoryService,
                       PaymentService paymentService,
                       EmailService emailService,
                       OrderRepository orderRepository) {
        this.customerService = customerService;
        this.inventoryService = inventoryService;
        this.paymentService = paymentService;
        this.emailService = emailService;
        this.orderRepository = orderRepository;
    }
    
    public void createOrder(String customerId, List<Product> products) {
        Customer customer = customerService.getCustomer(customerId);
        inventoryService.checkAvailability(products);
        
        Order order = new Order(customerId, products);
        paymentService.processPayment(order.getTotal());
        inventoryService.updateInventory(products);
        
        orderRepository.save(order);
        emailService.sendOrderConfirmation(customer.getEmail(), order);
    }
}
```

#### 2. Anemic Domain Model
**Problem:** Objects that contain data but no behavior (just getters/setters).

**Anti-pattern Example:**
```java
// Anemic - just data holders
public class BankAccount {
    private String accountNumber;
    private double balance;
    
    // Only getters and setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}

// All logic in service classes
public class BankAccountService {
    public void withdraw(BankAccount account, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (account.getBalance() < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
    }
    
    public void deposit(BankAccount account, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        account.setBalance(account.getBalance() + amount);
    }
}
```

**Better Solution - Rich Domain Model:**
```java
public class BankAccount {
    private String accountNumber;
    private double balance;
    private AccountStatus status;
    
    public BankAccount(String accountNumber, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.status = AccountStatus.ACTIVE;
    }
    
    public void withdraw(double amount) {
        validateAmount(amount);
        validateAccountStatus();
        
        if (balance < amount) {
            throw new InsufficientFundsException("Cannot withdraw " + amount + 
                ", balance is " + balance);
        }
        
        balance -= amount;
    }
    
    public void deposit(double amount) {
        validateAmount(amount);
        validateAccountStatus();
        balance += amount;
    }
    
    public boolean canWithdraw(double amount) {
        return status == AccountStatus.ACTIVE && balance >= amount;
    }
    
    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
    
    private void validateAccountStatus() {
        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }
    }
    
    // Controlled access to data
    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
}
```

#### 3. Inappropriate Inheritance
**Problem:** Using inheritance when composition would be better.

**Anti-pattern Example:**
```java
// Bad: Square inherits from Rectangle
public class Rectangle {
    protected double width;
    protected double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    public void setWidth(double width) { this.width = width; }
    public void setHeight(double height) { this.height = height; }
    
    public double getArea() { return width * height; }
}

public class Square extends Rectangle {
    public Square(double side) {
        super(side, side);
    }
    
    // Problem: Square must maintain width == height
    @Override
    public void setWidth(double width) {
        this.width = width;
        this.height = width;  // Violates LSP
    }
    
    @Override
    public void setHeight(double height) {
        this.width = height;
        this.height = height;  // Violates LSP
    }
}

// This breaks Liskov Substitution Principle
public void testRectangle(Rectangle rectangle) {
    rectangle.setWidth(5);
    rectangle.setHeight(4);
    // Expected area: 20, but Square will give 16
    assert rectangle.getArea() == 20;  // Fails for Square
}
```

**Better Solution:**
```java
// Better: Use composition or separate hierarchy
public abstract class Shape {
    public abstract double getArea();
    public abstract double getPerimeter();
}

public class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double getArea() { return width * height; }
    
    @Override
    public double getPerimeter() { return 2 * (width + height); }
    
    // Getters only - immutable after construction
    public double getWidth() { return width; }
    public double getHeight() { return height; }
}

public class Square extends Shape {
    private double side;
    
    public Square(double side) {
        this.side = side;
    }
    
    @Override
    public double getArea() { return side * side; }
    
    @Override
    public double getPerimeter() { return 4 * side; }
    
    public double getSide() { return side; }
}
```

#### 4. Circular Dependencies
**Problem:** Classes depending on each other directly or indirectly.

**Anti-pattern Example:**
```java
// Circular dependency
public class Order {
    private Customer customer;
    
    public Order(Customer customer) {
        this.customer = customer;
        customer.addOrder(this);  // Circular reference
    }
}

public class Customer {
    private List<Order> orders;
    
    public Customer() {
        orders = new ArrayList<>();
    }
    
    public void addOrder(Order order) {
        orders.add(order);
        // What if order's customer is different?
    }
}
```

**Better Solution:**
```java
// Break circular dependency with a service layer
public class Order {
    private String customerId;  // Reference by ID, not object
    private List<OrderItem> items;
    private OrderStatus status;
    
    public Order(String customerId, List<OrderItem> items) {
        this.customerId = customerId;
        this.items = new ArrayList<>(items);
        this.status = OrderStatus.PENDING;
    }
    
    public String getCustomerId() { return customerId; }
    // Other getters...
}

public class Customer {
    private String customerId;
    private String name;
    private String email;
    
    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }
    
    // No direct reference to orders
}

public class OrderService {
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    
    public List<Order> getOrdersForCustomer(String customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
    
    public void createOrder(String customerId, List<OrderItem> items) {
        Customer customer = customerRepository.findById(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        
        Order order = new Order(customerId, items);
        orderRepository.save(order);
    }
}
```

#### 5. Feature Envy
**Problem:** A class that uses methods of another class excessively.

**Anti-pattern Example:**
```java
// Feature envy - CustomerService is envious of Customer's data
public class CustomerService {
    public double calculateDiscount(Customer customer, Order order) {
        double discount = 0;
        
        // Excessive use of customer's getters
        if (customer.getYearsAsCustomer() > 5) {
            discount += 0.1;
        }
        
        if (customer.getTotalPurchases() > 10000) {
            discount += 0.05;
        }
        
        if (customer.getCustomerType() == CustomerType.PREMIUM) {
            discount += 0.15;
        }
        
        return Math.min(discount, 0.3);
    }
}
```

**Better Solution:**
```java
// Move the behavior to where the data is
public class Customer {
    private int yearsAsCustomer;
    private double totalPurchases;
    private CustomerType customerType;
    
    public double calculateDiscount() {
        double discount = 0;
        
        if (yearsAsCustomer > 5) {
            discount += 0.1;
        }
        
        if (totalPurchases > 10000) {
            discount += 0.05;
        }
        
        if (customerType == CustomerType.PREMIUM) {
            discount += 0.15;
        }
        
        return Math.min(discount, 0.3);
    }
    
    // Getters for necessary data only
    public CustomerType getCustomerType() { return customerType; }
}

public class OrderService {
    public void applyDiscount(Customer customer, Order order) {
        double discount = customer.calculateDiscount();
        order.applyDiscount(discount);
    }
}
```

### Performance Anti-patterns

#### 1. Premature Optimization
**Problem:** Optimizing code before identifying actual performance bottlenecks.

**Anti-pattern Example:**
```java
// Over-engineered for no proven benefit
public class StringProcessor {
    private static final Map<String, String> cache = new ConcurrentHashMap<>();
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    public String processString(String input) {
        lock.readLock().lock();
        try {
            if (cache.containsKey(input)) {
                return cache.get(input);
            }
        } finally {
            lock.readLock().unlock();
        }
        
        // Simple operation that doesn't need caching
        String result = input.toUpperCase();
        
        lock.writeLock().lock();
        try {
            cache.put(input, result);
        } finally {
            lock.writeLock().unlock();
        }
        
        return result;
    }
}
```

**Better Approach:**
```java
// Start simple, optimize when needed
public class StringProcessor {
    public String processString(String input) {
        return input.toUpperCase();
    }
    
    // Add caching only if profiling shows it's needed
}
```

#### 2. Creating Unnecessary Objects
**Problem:** Creating objects in loops or frequently called methods.

**Anti-pattern Example:**
```java
// Creates new objects unnecessarily
public class DataProcessor {
    public void processData(List<String> data) {
        for (String item : data) {
            // New StringBuilder created in each iteration
            StringBuilder sb = new StringBuilder();
            sb.append("Processed: ").append(item);
            System.out.println(sb.toString());
        }
    }
    
    public String formatCurrency(double amount) {
        // New DecimalFormat created each time
        DecimalFormat formatter = new DecimalFormat("$#,##0.00");
        return formatter.format(amount);
    }
}
```

**Better Solution:**
```java
public class DataProcessor {
    // Reuse objects
    private final StringBuilder sb = new StringBuilder();
    private final DecimalFormat currencyFormatter = new DecimalFormat("$#,##0.00");
    
    public void processData(List<String> data) {
        for (String item : data) {
            sb.setLength(0);  // Clear previous content
            sb.append("Processed: ").append(item);
            System.out.println(sb.toString());
        }
    }
    
    public String formatCurrency(double amount) {
        return currencyFormatter.format(amount);
    }
}
```

---

## Stage 5: Hands-on Practice (Week 13-16)

### Exercise 1: Library Management System
**Objective:** Design a complete library management system demonstrating all OOP principles.

**Requirements:**
1. Books, Members, Librarians
2. Book borrowing/returning
3. Fine calculations
4. Search functionality
5. Different member types with different privileges

**Step-by-Step Implementation:**

```java
// Step 1: Base classes and interfaces
public abstract class Person {
    protected String id;
    protected String name;
    protected String email;
    protected String phone;
    
    public Person(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}

public interface Searchable {
    boolean matches(String query);
}

public interface Borrowable {
    boolean isAvailable();
    void borrow();
    void returnItem();
}

// Step 2: Book hierarchy
public abstract class LibraryItem implements Searchable, Borrowable {
    protected String isbn;
    protected String title;
    protected String author;
    protected boolean isAvailable;
    
    public LibraryItem(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
    
    @Override
    public boolean matches(String query) {
        return title.toLowerCase().contains(query.toLowerCase()) ||
               author.toLowerCase().contains(query.toLowerCase()) ||
               isbn.contains(query);
    }
    
    @Override
    public boolean isAvailable() {
        return isAvailable;
    }
    
    @Override
    public void borrow() {
        if (!isAvailable) {
            throw new IllegalStateException("Item is not available");
        }
        isAvailable = false;
    }
    
    @Override
    public void returnItem() {
        isAvailable = true;
    }
    
    // Getters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    
    // Abstract methods for different item types
    public abstract int getBorrowDurationDays();
    public abstract double getFinePerDay();
}

public class Book extends LibraryItem {
    private String genre;
    private int pages;
    
    public Book(String isbn, String title, String author, String genre, int pages) {
        super(isbn, title, author);
        this.genre = genre;
        this.pages = pages;
    }
    
    @Override
    public int getBorrowDurationDays() {
        return 14;  // 2 weeks for regular books
    }
    
    @Override
    public double getFinePerDay() {
        return 0.50;  // 50 cents per day
    }
    
    public String getGenre() { return genre; }
    public int getPages() { return pages; }
}

public class ReferenceBook extends LibraryItem {
    private String subject;
    
    public ReferenceBook(String isbn, String title, String author, String subject) {
        super(isbn, title, author);
        this.subject = subject;
    }
    
    @Override
    public void borrow() {
        throw new UnsupportedOperationException("Reference books cannot be borrowed");
    }
    
    @Override
    public int getBorrowDurationDays() {
        return 0;  // Cannot be borrowed
    }
    
    @Override
    public double getFinePerDay() {
        return 0.0;
    }
    
    public String getSubject() { return subject; }
}

// Step 3: Member hierarchy
public abstract class Member extends Person {
    protected List<BorrowRecord> borrowHistory;
    protected double outstandingFines;
    
    public Member(String id, String name, String email, String phone) {
        super(id, name, email, phone);
        this.borrowHistory = new ArrayList<>();
        this.outstandingFines = 0.0;
    }
    
    public abstract int getMaxBooksAllowed();
    public abstract int getMaxBorrowDays();
    
    public void addBorrowRecord(BorrowRecord record) {
        borrowHistory.add(record);
    }
    
    public void addFine(double fine) {
        outstandingFines += fine;
    }
    
    public void payFine(double amount) {
        outstandingFines = Math.max(0, outstandingFines - amount);
    }
    
    public boolean canBorrow() {
        long currentBorrows = borrowHistory.stream()
            .filter(record -> !record.isReturned())
            .count();
        return currentBorrows < getMaxBooksAllowed() && outstandingFines == 0;
    }
    
    public List<BorrowRecord> getBorrowHistory() { return new ArrayList<>(borrowHistory); }
    public double getOutstandingFines() { return outstandingFines; }
}

public class RegularMember extends Member {
    public RegularMember(String id, String name, String email, String phone) {
        super(id, name, email, phone);
    }
    
    @Override
    public int getMaxBooksAllowed() {
        return 3;
    }
    
    @Override
    public int getMaxBorrowDays() {
        return 14;
    }
}

public class PremiumMember extends Member {
    public PremiumMember(String id, String name, String email, String phone) {
        super(id, name, email, phone);
    }
    
    @Override
    public int getMaxBooksAllowed() {
        return 10;
    }
    
    @Override
    public int getMaxBorrowDays() {
        return 30;
    }
}

// Step 4: Supporting classes
public class BorrowRecord {
    private String recordId;
    private String memberId;
    private String itemIsbn;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;
    
    public BorrowRecord(String recordId, String memberId, String itemIsbn, 
                       LocalDate borrowDate, LocalDate dueDate) {
        this.recordId = recordId;
        this.memberId = memberId;
        this.itemIsbn = itemIsbn;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.fine = 0.0;
    }
    
    public void returnBook(LocalDate returnDate, double fine) {
        this.returnDate = returnDate;
        this.fine = fine;
    }
    
    public boolean isReturned() {
        return returnDate != null;
    }
    
    public boolean isOverdue() {
        return !isReturned() && LocalDate.now().isAfter(dueDate);
    }
    
    // Getters
    public String getRecordId() { return recordId; }
    public String getMemberId() { return memberId; }
    public String getItemIsbn() { return itemIsbn; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public double getFine() { return fine; }
}

public class Librarian extends Person {
    private String employeeId;
    
    public Librarian(String id, String name, String email, String phone, String employeeId) {
        super(id, name, email, phone);
        this.employeeId = employeeId;
    }
    
    public String getEmployeeId() { return employeeId; }
}

// Step 5: Main Library System
public class LibraryManagementSystem {
    private Map<String, LibraryItem> items;
    private Map<String, Member> members;
    private Map<String, Librarian> librarians;
    private List<BorrowRecord> borrowRecords;
    private int nextRecordId;
    
    public LibraryManagementSystem() {
        this.items = new HashMap<>();
        this.members = new HashMap<>();
        this.librarians = new HashMap<>();
        this.borrowRecords = new ArrayList<>();
        this.nextRecordId = 1;
    }
    
    // Add items and members
    public void addItem(LibraryItem item) {
        items.put(item.getIsbn(), item);
    }
    
    public void addMember(Member member) {
        members.put(member.getId(), member);
    }
    
    public void addLibrarian(Librarian librarian) {
        librarians.put(librarian.getId(), librarian);
    }
    
    // Borrowing logic
    public BorrowRecord borrowItem(String memberId, String isbn) {
        Member member = members.get(memberId);
        LibraryItem item = items.get(isbn);
        
        if (member == null) {
            throw new IllegalArgumentException("Member not found");
        }
        
        if (item == null) {
            throw new IllegalArgumentException("Item not found");
        }
        
        if (!member.canBorrow()) {
            throw new IllegalStateException("Member cannot borrow (max books reached or outstanding fines)");
        }
        
        if (!item.isAvailable()) {
            throw new IllegalStateException("Item is not available");
        }
        
        // Create borrow record
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(
            Math.min(member.getMaxBorrowDays(), item.getBorrowDurationDays())
        );
        
        BorrowRecord record = new BorrowRecord(
            String.valueOf(nextRecordId++), memberId, isbn, borrowDate, dueDate
        );
        
        // Update states
        item.borrow();
        member.addBorrowRecord(record);
        borrowRecords.add(record);
        
        return record;
    }
    
    // Return logic with fine calculation
    public void returnItem(String recordId) {
        BorrowRecord record = borrowRecords.stream()
            .filter(r -> r.getRecordId().equals(recordId) && !r.isReturned())
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Borrow record not found"));
        
        LibraryItem item = items.get(record.getItemIsbn());
        Member member = members.get(record.getMemberId());
        
        LocalDate returnDate = LocalDate.now();
        double fine = calculateFine(record, returnDate, item);
        
        record.returnBook(returnDate, fine);
        item.returnItem();
        
        if (fine > 0) {
            member.addFine(fine);
        }
    }
    
    private double calculateFine(BorrowRecord record, LocalDate returnDate, LibraryItem item) {
        if (!returnDate.isAfter(record.getDueDate())) {
            return 0.0;  // No fine if returned on time
        }
        
        long daysOverdue = ChronoUnit.DAYS.between(record.getDueDate(), returnDate);
        return daysOverdue * item.getFinePerDay();
    }
    
    // Search functionality
    public List<LibraryItem> searchItems(String query) {
        return items.values().stream()
            .filter(item -> item.matches(query))
            .collect(Collectors.toList());
    }
    
    // Get overdue items
    public List<BorrowRecord> getOverdueItems() {
        return borrowRecords.stream()
            .filter(BorrowRecord::isOverdue)
            .collect(Collectors.toList());
    }
    
    // Member lookup
    public Member getMember(String memberId) {
        return members.get(memberId);
    }
    
    // Item lookup
    public LibraryItem getItem(String isbn) {
        return items.get(isbn);
    }
}
```

### Exercise 2: E-commerce System
**Objective:** Build an e-commerce system showcasing design patterns and SOLID principles.

```java
// Product hierarchy using Template Method pattern
public abstract class Product {
    protected String id;
    protected String name;
    protected double basePrice;
    protected String category;
    protected int stockQuantity;
    
    public Product(String id, String name, double basePrice, String category, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.category = category;
        this.stockQuantity = stockQuantity;
    }
    
    // Template method
    public final double calculateFinalPrice() {
        double price = basePrice;
        price = applyDiscount(price);
        price = addTax(price);
        price = addShipping(price);
        return price;
    }
    
    // Steps to be implemented by subclasses
    protected abstract double applyDiscount(double price);
    protected abstract double addTax(double price);
    protected abstract double addShipping(double price);
    
    public boolean isInStock(int quantity) {
        return stockQuantity >= quantity;
    }
    
    public void reduceStock(int quantity) {
        if (!isInStock(quantity)) {
            throw new IllegalStateException("Insufficient stock");
        }
        stockQuantity -= quantity;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getBasePrice() { return basePrice; }
    public String getCategory() { return category; }
    public int getStockQuantity() { return stockQuantity; }
}

public class Electronics extends Product {
    private int warrantyPeriod;
    
    public Electronics(String id, String name, double basePrice, int stockQuantity, int warrantyPeriod) {
        super(id, name, basePrice, "Electronics", stockQuantity);
        this.warrantyPeriod = warrantyPeriod;
    }
    
    @Override
    protected double applyDiscount(double price) {
        // 10% discount on electronics over $500
        return basePrice > 500 ? price * 0.9 : price;
    }
    
    @Override
    protected double addTax(double price) {
        return price * 1.08;  // 8% tax
    }
    
    @Override
    protected double addShipping(double price) {
        return price + 15.0;  // Flat shipping for electronics
    }
    
    public int getWarrantyPeriod() { return warrantyPeriod; }
}

public class Clothing extends Product {
    private String size;
    private String material;
    
    public Clothing(String id, String name, double basePrice, int stockQuantity, String size, String material) {
        super(id, name, basePrice, "Clothing", stockQuantity);
        this.size = size;
        this.material = material;
    }
    
    @Override
    protected double applyDiscount(double price) {
        // 15% discount on clothing over $100
        return basePrice > 100 ? price * 0.85 : price;
    }
    
    @Override
    protected double addTax(double price) {
        return price * 1.06;  // 6% tax
    }
    
    @Override
    protected double addShipping(double price) {
        return price + 8.0;  // Lower shipping for clothing
    }
    
    public String getSize() { return size; }
    public String getMaterial() { return material; }
}

// Strategy pattern for payment processing
public interface PaymentStrategy {
    void processPayment(double amount);
    boolean validatePayment();
}

public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    
    public CreditCardPayment(String cardNumber, String cardHolderName, 
                           String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    
    @Override
    public boolean validatePayment() {
        // Simplified validation
        return cardNumber != null && cardNumber.length() == 16 &&
               cvv != null && cvv.length() == 3;
    }
    
    @Override
    public void processPayment(double amount) {
        if (!validatePayment()) {
            throw new IllegalStateException("Invalid credit card information");
        }
        System.out.println("Processing credit card payment of $" + amount);
        // Integration with payment gateway would go here
    }
}

public class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;
    
    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public boolean validatePayment() {
        return email != null && email.contains("@") && password != null;
    }
    
    @Override
    public void processPayment(double amount) {
        if (!validatePayment()) {
            throw new IllegalStateException("Invalid PayPal credentials");
        }
        System.out.println("Processing PayPal payment of $" + amount);
        // PayPal API integration would go here
    }
}

// Observer pattern for order notifications
public interface OrderObserver {
    void onOrderCreated(Order order);
    void onOrderShipped(Order order);
    void onOrderDelivered(Order order);
    void onOrderCancelled(Order order);
}

public class EmailNotificationService implements OrderObserver {
    @Override
    public void onOrderCreated(Order order) {
        System.out.println("Email: Order " + order.getId() + " created for customer " + order.getCustomerId());
    }
    
    @Override
    public void onOrderShipped(Order order) {
        System.out.println("Email: Order " + order.getId() + " has been shipped");
    }
    
    @Override
    public void onOrderDelivered(Order order) {
        System.out.println("Email: Order " + order.getId() + " has been delivered");
    }
    
    @Override
    public void onOrderCancelled(Order order) {
        System.out.println("Email: Order " + order.getId() + " has been cancelled");
    }
}

public class SMSNotificationService implements OrderObserver {
    @Override
    public void onOrderCreated(Order order) {
        System.out.println("SMS: Order " + order.getId() + " created");
    }
    
    @Override
    public void onOrderShipped(Order order) {
        System.out.println("SMS: Your order " + order.getId() + " is on the way!");
    }
    
    @Override
    public void onOrderDelivered(Order order) {
        System.out.println("SMS: Order " + order.getId() + " delivered successfully");
    }
    
    @Override
    public void onOrderCancelled(Order order) {
        System.out.println("SMS: Order " + order.getId() + " cancelled");
    }
}

// Order management with State pattern
public enum OrderStatus {
    PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED
}

public class OrderItem {
    private Product product;
    private int quantity;
    private double priceAtTime;
    
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.priceAtTime = product.calculateFinalPrice();
    }
    
    public double getTotalPrice() {
        return priceAtTime * quantity;
    }
    
    // Getters
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getPriceAtTime() { return priceAtTime; }
}

public class Order {
    private String id;
    private String customerId;
    private List<OrderItem> items;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private double totalAmount;
    private PaymentStrategy paymentStrategy;
    private List<OrderObserver> observers;
    
    public Order(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
        this.observers = new ArrayList<>();
    }
    
    public void addItem(OrderItem item) {
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("Cannot modify confirmed order");
        }
        items.add(item);
        calculateTotal();
    }
    
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }
    
    private void notifyObservers() {
        switch (status) {
            case CONFIRMED:
                observers.forEach(observer -> observer.onOrderCreated(this));
                break;
            case SHIPPED:
                observers.forEach(observer -> observer.onOrderShipped(this));
                break;
            case DELIVERED:
                observers.forEach(observer -> observer.onOrderDelivered(this));
                break;
            case CANCELLED:
                observers.forEach(observer -> observer.onOrderCancelled(this));
                break;
        }
    }
    
    private void calculateTotal() {
        totalAmount = items.stream()
            .mapToDouble(OrderItem::getTotalPrice)
            .sum();
    }
    
    public void confirm() {
        if (status != OrderStatus.PENDING) {
            throw new IllegalStateException("Order already processed");
        }
        
        if (items.isEmpty()) {
            throw new IllegalStateException("Cannot confirm empty order");
        }
        
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment method not set");
        }
        
        // Process payment
        paymentStrategy.processPayment(totalAmount);
        
        // Reduce stock
        for (OrderItem item : items) {
            item.getProduct().reduceStock(item.getQuantity());
        }
        
        status = OrderStatus.CONFIRMED;
        notifyObservers();
    }
    
    public void ship() {
        if (status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Order must be confirmed before shipping");
        }
        status = OrderStatus.SHIPPED;
        notifyObservers();
    }
    
    public void deliver() {
        if (status != OrderStatus.SHIPPED) {
            throw new IllegalStateException("Order must be shipped before delivery");
        }
        status = OrderStatus.DELIVERED;
        notifyObservers();
    }
    
    public void cancel() {
        if (status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot cancel delivered order");
        }
        
        // Restore stock if order was confirmed
        if (status == OrderStatus.CONFIRMED || status == OrderStatus.SHIPPED) {
            for (OrderItem item : items) {
                Product product = item.getProduct();
                product.stockQuantity += item.getQuantity();  // Restore stock
            }
        }
        
        status = OrderStatus.CANCELLED;
        notifyObservers();
    }
    
    // Getters
    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public List<OrderItem> getItems() { return new ArrayList<>(items); }
    public OrderStatus getStatus() { return status; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
}

// Customer with Decorator pattern for membership benefits
public class Customer {
    private String id;
    private String name;
    private String email;
    private CustomerType type;
    
    public Customer(String id, String name, String email, CustomerType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.type = type;
    }
    
    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public CustomerType getType() { return type; }
}

public enum CustomerType {
    REGULAR, PREMIUM, VIP
}

// Abstract decorator for customer benefits
public abstract class CustomerDecorator {
    protected Customer customer;
    
    public CustomerDecorator(Customer customer) {
        this.customer = customer;
    }
    
    public abstract double applyDiscount(double amount);
    public abstract int getShippingDays();
    public abstract boolean hasFreeShipping();
}

public class RegularCustomerDecorator extends CustomerDecorator {
    public RegularCustomerDecorator(Customer customer) {
        super(customer);
    }
    
    @Override
    public double applyDiscount(double amount) {
        return amount;  // No discount
    }
    
    @Override
    public int getShippingDays() {
        return 7;
    }
    
    @Override
    public boolean hasFreeShipping() {
        return false;
    }
}

public class PremiumCustomerDecorator extends CustomerDecorator {
    public PremiumCustomerDecorator(Customer customer) {
        super(customer);
    }
    
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.95;  // 5% discount
    }
    
    @Override
    public int getShippingDays() {
        return 5;
    }
    
    @Override
    public boolean hasFreeShipping() {
        return amount > 50;  // Free shipping over $50
    }
}

public class VIPCustomerDecorator extends CustomerDecorator {
    public VIPCustomerDecorator(Customer customer) {
        super(customer);
    }
    
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.90;  // 10% discount
    }
    
    @Override
    public int getShippingDays() {
        return 2;
    }
    
    @Override
    public boolean hasFreeShipping() {
        return true;  // Always free shipping
    }
}

// Main E-commerce system using Facade pattern
public class ECommerceSystem {
    private Map<String, Product> products;
    private Map<String, Customer> customers;
    private Map<String, Order> orders;
    private List<OrderObserver> globalObservers;
    private int nextOrderId;
    
    public ECommerceSystem() {
        this.products = new HashMap<>();
        this.customers = new HashMap<>();
        this.orders = new HashMap<>();
        this.globalObservers = new ArrayList<>();
        this.nextOrderId = 1;
        
        // Add global observers
        globalObservers.add(new EmailNotificationService());
        globalObservers.add(new SMSNotificationService());
    }
    
    // Product management
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }
    
    public Product getProduct(String id) {
        return products.get(id);
    }
    
    public List<Product> searchProducts(String category) {
        return products.values().stream()
            .filter(p -> p.getCategory().equalsIgnoreCase(category))
            .collect(Collectors.toList());
    }
    
    // Customer management
    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }
    
    public Customer getCustomer(String id) {
        return customers.get(id);
    }
    
    // Order management - Facade method
    public Order createOrder(String customerId) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        
        Order order = new Order(String.valueOf(nextOrderId++), customerId);
        
        // Add global observers
        for (OrderObserver observer : globalObservers) {
            order.addObserver(observer);
        }
        
        orders.put(order.getId(), order);
        return order;
    }
    
    public void addItemToOrder(String orderId, String productId, int quantity) {
        Order order = orders.get(orderId);
        Product product = products.get(productId);
        
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }
        
        if (!product.isInStock(quantity)) {
            throw new IllegalStateException("Insufficient stock for product: " + product.getName());
        }
        
        OrderItem item = new OrderItem(product, quantity);
        order.addItem(item);
    }
    
    public void processOrder(String orderId, PaymentStrategy paymentStrategy) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        
        order.setPaymentStrategy(paymentStrategy);
        order.confirm();
    }
    
    public void shipOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        order.ship();
    }
    
    public void deliverOrder(String orderId) {
        Order order = orders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }
        order.deliver();
    }
    
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
    
    public List<Order> getCustomerOrders(String customerId) {
        return orders.values().stream()
            .filter(order -> order.getCustomerId().equals(customerId))
            .collect(Collectors.toList());
    }
}
```

### Exercise 3: Banking System with Advanced Features
**Objective:** Create a comprehensive banking system demonstrating advanced OOP concepts and design patterns.

```java
// Command Pattern for transactions
public interface TransactionCommand {
    void execute();
    void undo();
    String getTransactionId();
    double getAmount();
    LocalDateTime getTimestamp();
}

public class DepositCommand implements TransactionCommand {
    private BankAccount account;
    private double amount;
    private String transactionId;
    private LocalDateTime timestamp;
    private boolean executed;
    
    public DepositCommand(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
        this.transactionId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.executed = false;
    }
    
    @Override
    public void execute() {
        if (executed) {
            throw new IllegalStateException("Transaction already executed");
        }
        account.deposit(amount);
        executed = true;
    }
    
    @Override
    public void undo() {
        if (!executed) {
            throw new IllegalStateException("Cannot undo non-executed transaction");
        }
        account.withdraw(amount);
        executed = false;
    }
    
    // Getters
    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

public class WithdrawCommand implements TransactionCommand {
    private BankAccount account;
    private double amount;
    private String transactionId;
    private LocalDateTime timestamp;
    private boolean executed;
    
    public WithdrawCommand(BankAccount account, double amount) {
        this.account = account;
        this.amount = amount;
        this.transactionId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.executed = false;
    }
    
    @Override
    public void execute() {
        if (executed) {
            throw new IllegalStateException("Transaction already executed");
        }
        if (!account.canWithdraw(amount)) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal");
        }
        account.withdraw(amount);
        executed = true;
    }
    
    @Override
    public void undo() {
        if (!executed) {
            throw new IllegalStateException("Cannot undo non-executed transaction");
        }
        account.deposit(amount);
        executed = false;
    }
    
    // Getters
    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

public class TransferCommand implements TransactionCommand {
    private BankAccount fromAccount;
    private BankAccount toAccount;
    private double amount;
    private String transactionId;
    private LocalDateTime timestamp;
    private boolean executed;
    
    public TransferCommand(BankAccount fromAccount, BankAccount toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.transactionId = UUID.randomUUID().toString();
        this.timestamp = LocalDateTime.now();
        this.executed = false;
    }
    
    @Override
    public void execute() {
        if (executed) {
            throw new IllegalStateException("Transaction already executed");
        }
        if (!fromAccount.canWithdraw(amount)) {
            throw new InsufficientFundsException("Insufficient funds for transfer");
        }
        
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        executed = true;
    }
    
    @Override
    public void undo() {
        if (!executed) {
            throw new IllegalStateException("Cannot undo non-executed transaction");
        }
        toAccount.withdraw(amount);
        fromAccount.deposit(amount);
        executed = false;
    }
    
    // Getters
    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

// Account hierarchy with Template Method
public abstract class BankAccount {
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;
    protected AccountStatus status;
    protected List<TransactionCommand> transactionHistory;
    
    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
        this.status = AccountStatus.ACTIVE;
        this.transactionHistory = new ArrayList<>();
    }
    
    // Template method for transaction processing
    public final void processTransaction(TransactionCommand command) {
        validateTransaction(command);
        logTransaction(command);
        command.execute();
        updateTransactionHistory(command);
        notifyTransaction(command);
    }
    
    // Steps implemented by base class
    private void logTransaction(TransactionCommand command) {
        System.out.println("Logging transaction: " + command.getTransactionId());
    }
    
    private void updateTransactionHistory(TransactionCommand command) {
        transactionHistory.add(command);
    }
    
    // Steps to be implemented by subclasses
    protected abstract void validateTransaction(TransactionCommand command);
    protected abstract void notifyTransaction(TransactionCommand command);
    protected abstract double getMinimumBalance();
    protected abstract double getTransactionFee();
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }
        balance += amount;
    }
    
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Account is not active");
        }
        if (!canWithdraw(amount)) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        balance -= amount;
        balance -= getTransactionFee();  // Apply transaction fee
    }
    
    public boolean canWithdraw(double amount) {
        return status == AccountStatus.ACTIVE && 
               (balance - amount - getTransactionFee()) >= getMinimumBalance();
    }
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public double getBalance() { return balance; }
    public AccountStatus getStatus() { return status; }
    public List<TransactionCommand> getTransactionHistory() { 
        return new ArrayList<>(transactionHistory); 
    }
}

public class SavingsAccount extends BankAccount {
    private double interestRate;
    
    public SavingsAccount(String accountNumber, String accountHolderName, 
                         double initialBalance, double interestRate) {
        super(accountNumber, accountHolderName, initialBalance);
        this.interestRate = interestRate;
    }
    
    @Override
    protected void validateTransaction(TransactionCommand command) {
        // Savings account specific validations
        if (command instanceof WithdrawCommand && command.getAmount() > 1000) {
            throw new IllegalArgumentException("Daily withdrawal limit exceeded for savings account");
        }
    }
    
    @Override
    protected void notifyTransaction(TransactionCommand command) {
        System.out.println("Savings Account notification: Transaction " + 
                          command.getTransactionId() + " processed");
    }
    
    @Override
    protected double getMinimumBalance() {
        return 100.0;  // Minimum balance for savings account
    }
    
    @Override
    protected double getTransactionFee() {
        return 1.0;  // $1 transaction fee
    }
    
    public void applyInterest() {
        double interest = balance * (interestRate / 100) / 12;  // Monthly interest
        balance += interest;
        System.out.println("Interest applied: $" + interest);
    }
    
    public double getInterestRate() { return interestRate; }
}

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    
    public CheckingAccount(String accountNumber, String accountHolderName, 
                          double initialBalance, double overdraftLimit) {
        super(accountNumber, accountHolderName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }
    
    @Override
    protected void validateTransaction(TransactionCommand command) {
        // Checking account specific validations
        // Allow overdraft up to limit
    }
    
    @Override
    protected void notifyTransaction(TransactionCommand command) {
        System.out.println("Checking Account notification: Transaction " + 
                          command.getTransactionId() + " processed");
        
        if (balance < 0) {
            System.out.println("Warning: Account overdrawn. Current balance: $" + balance);
        }
    }
    
    @Override
    protected double getMinimumBalance() {
        return -overdraftLimit;  // Can go negative up to overdraft limit
    }
    
    @Override
    protected double getTransactionFee() {
        return balance < 0 ? 25.0 : 0.0;  // Overdraft fee
    }
    
    @Override
    public boolean canWithdraw(double amount) {
        return status == AccountStatus.ACTIVE && 
               (balance - amount - getTransactionFee()) >= getMinimumBalance();
    }
    
    public double getOverdraftLimit() { return overdraftLimit; }
}

// Exception classes
public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public enum AccountStatus {
    ACTIVE, INACTIVE, SUSPENDED, CLOSED
}

// Bank management system
public class BankingSystem {
    private Map<String, BankAccount> accounts;
    private List<TransactionCommand> allTransactions;
    private TransactionProcessor transactionProcessor;
    
    public BankingSystem() {
        this.accounts = new HashMap<>();
        this.allTransactions = new ArrayList<>();
        this.transactionProcessor = new TransactionProcessor();
    }
    
    public void createSavingsAccount(String accountNumber, String holderName, 
                                   double initialBalance, double interestRate) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account already exists");
        }
        
        SavingsAccount account = new SavingsAccount(accountNumber, holderName, 
                                                   initialBalance, interestRate);
        accounts.put(accountNumber, account);
    }
    
    public void createCheckingAccount(String accountNumber, String holderName, 
                                    double initialBalance, double overdraftLimit) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account already exists");
        }
        
        CheckingAccount account = new CheckingAccount(accountNumber, holderName, 
                                                     initialBalance, overdraftLimit);
        accounts.put(accountNumber, account);
    }
    
    public void deposit(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        DepositCommand command = new DepositCommand(account, amount);
        transactionProcessor.processTransaction(command);
        allTransactions.add(command);
    }
    
    public void withdraw(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        WithdrawCommand command = new WithdrawCommand(account, amount);
        transactionProcessor.processTransaction(command);
        allTransactions.add(command);
    }
    
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        BankAccount fromAccount = getAccount(fromAccountNumber);
        BankAccount toAccount = getAccount(toAccountNumber);
        
        TransferCommand command = new TransferCommand(fromAccount, toAccount, amount);
        transactionProcessor.processTransaction(command);
        allTransactions.add(command);
    }
    
    public void undoLastTransaction() {
        if (allTransactions.isEmpty()) {
            throw new IllegalStateException("No transactions to undo");
        }
        
        TransactionCommand lastTransaction = allTransactions.get(allTransactions.size() - 1);
        lastTransaction.undo();
        allTransactions.remove(allTransactions.size() - 1);
    }
    
    public BankAccount getAccount(String accountNumber) {
        BankAccount account = accounts.get(accountNumber);
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return account;
    }
    
    public List<TransactionCommand> getTransactionHistory(String accountNumber) {
        return getAccount(accountNumber).getTransactionHistory();
    }
    
    public void generateMonthlyStatement(String accountNumber) {
        BankAccount account = getAccount(accountNumber);
        System.out.println("\n=== Monthly Statement ===");
        System.out.println("Account: " + account.getAccountNumber());
        System.out.println("Holder: " + account.getAccountHolderName());
        System.out.println("Current Balance: $" + account.getBalance());
        System.out.println("\nTransaction History:");
        
        for (TransactionCommand transaction : account.getTransactionHistory()) {
            System.out.println(transaction.getTimestamp() + " - " + 
                             transaction.getClass().getSimpleName() + 
                             " - $" + transaction.getAmount());
        }
        System.out.println("========================\n");
    }
}

// Transaction processor using Chain of Responsibility
public class TransactionProcessor {
    public void processTransaction(TransactionCommand command) {
        try {
            command.execute();
            System.out.println("Transaction " + command.getTransactionId() + " processed successfully");
        } catch (Exception e) {
            System.err.println("Transaction failed: " + e.getMessage());
            throw e;
        }
    }
}
```

### Practical Challenges

#### Challenge 1: Design a University Management System
**Requirements:**
- Students, Professors, Courses, Enrollments
- Different types of courses (Lecture, Lab, Seminar)
- Grading system with different grade calculations
- Prerequisites for courses
- Academic calendar integration

#### Challenge 2: Build a Restaurant Management System
**Requirements:**
- Menu items, Orders, Tables, Staff
- Different pricing strategies (Happy hour, Group discounts)
- Kitchen management with order queues
- Billing with different payment methods
- Reservation system

#### Challenge 3: Create a Hospital Management System
**Requirements:**
- Patients, Doctors, Appointments, Medical Records
- Different types of medical staff with different specializations
- Equipment management
- Billing and insurance processing
- Emergency prioritization system

### Assessment Criteria

For each exercise, evaluate your implementation against these criteria:

**1. OOP Principles (25 points each = 100 points)**
- Encapsulation: Are fields properly encapsulated with controlled access?
- Abstraction: Are interfaces and abstract classes used appropriately?
- Inheritance: Is inheritance used correctly without violating LSP?
- Polymorphism: Are polymorphic behaviors implemented effectively?

**2. Design Patterns (20 points each = 100 points)**
- Singleton: Used appropriately for single-instance requirements
- Factory: Used for object creation when type is determined at runtime
- Observer: Used for event notification systems
- Strategy: Used for interchangeable algorithms
- Template Method: Used for common algorithm structure with varying steps

**3. SOLID Principles (20 points each = 100 points)**
- Single Responsibility: Each class has one clear responsibility
- Open/Closed: Classes are open for extension, closed for modification
- Liskov Substitution: Subclasses can replace superclasses safely
- Interface Segregation: Interfaces are specific and focused
- Dependency Inversion: Dependencies are on abstractions, not concretions

**4. Code Quality (100 points)**
- Naming conventions and readability
- Error handling and edge cases
- Performance considerations
- Code organization and structure
- Documentation and comments

**Total: 400 points**

### Final Project: Integrated Learning Management System

Combine all learned concepts to build a comprehensive Learning Management System that includes:

1. **User Management** (Students, Instructors, Administrators)
2. **Course Management** (Courses, Lessons, Assignments, Quizzes)
3. **Content Delivery** (Videos, Documents, Interactive Content)
4. **Assessment System** (Multiple question types, automated grading)
5. **Communication** (Messaging, Forums, Announcements)
6. **Analytics** (Progress tracking, Performance reports)
7. **Integration** (External tools, APIs, Payment processing)

This project should demonstrate mastery of all OOP concepts, design patterns, and best practices covered in this roadmap.

### Learning Timeline Summary

- **Week 1-2:** Fundamentals and basic concepts
- **Week 3-6:** Core OOP principles with extensive practice
- **Week 7-10:** Design patterns and SOLID principles
- **Week 11-12:** Anti-patterns and best practices
- **Week 13-16:** Hands-on projects and practical application
- **Week 17-20:** Final comprehensive project

### Recommended Study Schedule

**Daily (2-3 hours):**
- 1 hour: Reading and understanding concepts
- 1 hour: Coding practice and examples
- 30 minutes: Review and reflection

**Weekly:**
- Complete one major exercise
- Review previous week's concepts
- Take notes on key learnings
- Seek feedback on code from peers or mentors

**Monthly:**
- Complete a comprehensive project
- Assess progress against learning objectives
- Adjust study plan based on strengths/weaknesses
- Practice explaining concepts to others

This roadmap provides a structured path from OOP fundamentals to advanced application, ensuring deep understanding through progressive complexity and extensive hands-on practice.

---

## Additional Resources and Tools

### Essential Books (Progressive Reading Order)
1. **Beginner Level:**
   - "Head First Object-Oriented Analysis and Design" by Brett McLaughlin
   - "Object-Oriented Programming in Java" by Barry Burd
   - "Learning Object-Oriented Programming" by Gaston Hillar

2. **Intermediate Level:**
   - "Effective Java" by Joshua Bloch
   - "Clean Code" by Robert Martin
   - "Head First Design Patterns" by Freeman & Robson

3. **Advanced Level:**
   - "Design Patterns: Elements of Reusable Object-Oriented Software" (Gang of Four)
   - "Patterns of Enterprise Application Architecture" by Martin Fowler
   - "Domain-Driven Design" by Eric Evans

### Online Platforms and Courses
1. **Interactive Learning:**
   - Codecademy's Java/Python OOP courses
   - LeetCode's OOP-focused problems
   - HackerRank's Object-Oriented Programming domain

2. **Video Courses:**
   - Coursera's "Object Oriented Programming in Java" (UC San Diego)
   - edX's "Introduction to Object-Oriented Programming" (MIT)
   - Udemy's comprehensive OOP courses

3. **Practice Platforms:**
   - CodeWars (OOP kata challenges)
   - Exercism.io (mentored practice)
   - Project Euler (mathematical OOP problems)

### Development Tools and IDEs
1. **Java Development:**
   - IntelliJ IDEA (recommended for beginners)
   - Eclipse IDE
   - Visual Studio Code with Java extensions

2. **Python Development:**
   - PyCharm
   - Visual Studio Code with Python extensions
   - Jupyter Notebooks for experimentation

3. **Version Control and Collaboration:**
   - Git and GitHub
   - GitLab or Bitbucket
   - Code review tools

### Code Quality Tools
1. **Static Analysis:**
   - SonarQube for code quality metrics
   - CheckStyle for Java
   - Pylint for Python

2. **Testing Frameworks:**
   - JUnit for Java unit testing
   - Mockito for mocking dependencies
   - pytest for Python testing

3. **Documentation:**
   - Javadoc for Java
   - Sphinx for Python
   - UML diagramming tools (draw.io, Lucidchart)

---

## Advanced Topics for Continued Learning

### Architectural Patterns
Once you master basic OOP, explore these architectural patterns:

1. **Model-View-Controller (MVC)**
2. **Model-View-Presenter (MVP)**
3. **Model-View-ViewModel (MVVM)**
4. **Dependency Injection and Inversion of Control**
5. **Repository Pattern**
6. **Unit of Work Pattern**

### Modern OOP Concepts
1. **Functional Programming in OOP Languages**
   - Lambda expressions
   - Stream processing
   - Immutable objects

2. **Concurrent and Parallel Programming**
   - Thread-safe object design
   - Actor model
   - Async/await patterns

3. **Domain-Driven Design**
   - Aggregate patterns
   - Value objects
   - Domain services

---

## Career Integration Strategy

### Building a Professional Portfolio
1. **GitHub Projects:**
   - Clean, well-documented OOP projects
   - Progressive complexity demonstration
   - Open-source contributions

2. **Project Showcase:**
   - Library Management System
   - E-commerce Platform
   - Banking System
   - Game Development (Tic-tac-toe to Chess)
   - Web Application with MVC architecture

3. **Technical Blogging:**
   - Write about OOP concepts you've learned
   - Create tutorials for complex design patterns
   - Share refactoring experiences

### Interview Preparation Specific to OOP

#### Common OOP Interview Questions:

**Conceptual Questions:**
1. Explain the four pillars of OOP with real-world examples
2. What's the difference between composition and inheritance?
3. When would you use abstract classes vs interfaces?
4. Explain the Liskov Substitution Principle with code examples
5. What are the benefits and drawbacks of polymorphism?

**Design Questions:**
1. Design a parking lot system
2. Design a chess game
3. Design a social media platform
4. Design a file system
5. Design a cache system with different eviction policies

**Code Review Questions:**
1. Identify OOP violations in given code
2. Refactor procedural code to OOP
3. Optimize object design for performance
4. Implement design patterns from scratch

#### Technical Interview Practice:

```java
// Example: Design a Parking Lot System (Common Interview Question)

// Step 1: Identify entities and relationships
public enum VehicleType {
    MOTORCYCLE, CAR, TRUCK
}

public enum ParkingSpotType {
    MOTORCYCLE, COMPACT, LARGE
}

public enum ParkingSpotStatus {
    AVAILABLE, OCCUPIED, OUT_OF_ORDER
}

// Step 2: Create base classes and interfaces
public abstract class Vehicle {
    protected String licensePlate;
    protected VehicleType type;
    protected LocalDateTime parkingTime;
    
    public Vehicle(String licensePlate, VehicleType type) {
        this.licensePlate = licensePlate;
        this.type = type;
    }
    
    public abstract boolean canFitInSpot(ParkingSpot spot);
    
    // Getters
    public String getLicensePlate() { return licensePlate; }
    public VehicleType getType() { return type; }
    public LocalDateTime getParkingTime() { return parkingTime; }
    public void setParkingTime(LocalDateTime time) { this.parkingTime = time; }
}

public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate, VehicleType.CAR);
    }
    
    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getType() == ParkingSpotType.COMPACT || 
               spot.getType() == ParkingSpotType.LARGE;
    }
}

public class Motorcycle extends Vehicle {
    public Motorcycle(String licensePlate) {
        super(licensePlate, VehicleType.MOTORCYCLE);
    }
    
    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return true; // Motorcycles can fit anywhere
    }
}

public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, VehicleType.TRUCK);
    }
    
    @Override
    public boolean canFitInSpot(ParkingSpot spot) {
        return spot.getType() == ParkingSpotType.LARGE;
    }
}

// Step 3: Parking spot design
public class ParkingSpot {
    private String spotId;
    private ParkingSpotType type;
    private ParkingSpotStatus status;
    private Vehicle parkedVehicle;
    private int level;
    private int row;
    private int number;
    
    public ParkingSpot(String spotId, ParkingSpotType type, int level, int row, int number) {
        this.spotId = spotId;
        this.type = type;
        this.status = ParkingSpotStatus.AVAILABLE;
        this.level = level;
        this.row = row;
        this.number = number;
    }
    
    public boolean isAvailable() {
        return status == ParkingSpotStatus.AVAILABLE;
    }
    
    public boolean parkVehicle(Vehicle vehicle) {
        if (!isAvailable() || !vehicle.canFitInSpot(this)) {
            return false;
        }
        
        this.parkedVehicle = vehicle;
        this.status = ParkingSpotStatus.OCCUPIED;
        vehicle.setParkingTime(LocalDateTime.now());
        return true;
    }
    
    public Vehicle removeVehicle() {
        if (parkedVehicle == null) {
            return null;
        }
        
        Vehicle vehicle = parkedVehicle;
        parkedVehicle = null;
        status = ParkingSpotStatus.AVAILABLE;
        return vehicle;
    }
    
    // Getters
    public ParkingSpotType getType() { return type; }
    public ParkingSpotStatus getStatus() { return status; }
    public Vehicle getParkedVehicle() { return parkedVehicle; }
}

// Step 4: Parking level management
public class ParkingLevel {
    private int levelNumber;
    private List<ParkingSpot> spots;
    private Map<ParkingSpotType, List<ParkingSpot>> availableSpots;
    
    public ParkingLevel(int levelNumber) {
        this.levelNumber = levelNumber;
        this.spots = new ArrayList<>();
        this.availableSpots = new HashMap<>();
        initializeAvailableSpots();
    }
    
    private void initializeAvailableSpots() {
        for (ParkingSpotType type : ParkingSpotType.values()) {
            availableSpots.put(type, new ArrayList<>());
        }
    }
    
    public void addParkingSpot(ParkingSpot spot) {
        spots.add(spot);
        if (spot.isAvailable()) {
            availableSpots.get(spot.getType()).add(spot);
        }
    }
    
    public boolean parkVehicle(Vehicle vehicle) {
        // Try to find appropriate spot
        for (ParkingSpot spot : spots) {
            if (spot.isAvailable() && vehicle.canFitInSpot(spot)) {
                if (spot.parkVehicle(vehicle)) {
                    availableSpots.get(spot.getType()).remove(spot);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean removeVehicle(Vehicle vehicle) {
        for (ParkingSpot spot : spots) {
            if (spot.getParkedVehicle() == vehicle) {
                spot.removeVehicle();
                availableSpots.get(spot.getType()).add(spot);
                return true;
            }
        }
        return false;
    }
    
    public int getAvailableSpots(ParkingSpotType type) {
        return availableSpots.get(type).size();
    }
}

// Step 5: Pricing strategy (Strategy Pattern)
public interface PricingStrategy {
    double calculatePrice(Vehicle vehicle, LocalDateTime exitTime);
}

public class HourlyPricingStrategy implements PricingStrategy {
    private static final double HOURLY_RATE = 5.0;
    private static final double DAILY_MAX = 50.0;
    
    @Override
    public double calculatePrice(Vehicle vehicle, LocalDateTime exitTime) {
        long hours = ChronoUnit.HOURS.between(vehicle.getParkingTime(), exitTime);
        if (hours == 0) hours = 1; // Minimum 1 hour
        
        double price = hours * HOURLY_RATE;
        return Math.min(price, DAILY_MAX);
    }
}

// Step 6: Main parking lot system
public class ParkingLot {
    private String name;
    private List<ParkingLevel> levels;
    private PricingStrategy pricingStrategy;
    private Map<String, ParkingSpot> vehicleSpotMap;
    
    public ParkingLot(String name, int numLevels) {
        this.name = name;
        this.levels = new ArrayList<>();
        this.pricingStrategy = new HourlyPricingStrategy();
        this.vehicleSpotMap = new HashMap<>();
        
        // Initialize levels
        for (int i = 0; i < numLevels; i++) {
            levels.add(new ParkingLevel(i + 1));
        }
    }
    
    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            if (level.parkVehicle(vehicle)) {
                // Find the spot where vehicle was parked
                for (ParkingSpot spot : level.spots) {
                    if (spot.getParkedVehicle() == vehicle) {
                        vehicleSpotMap.put(vehicle.getLicensePlate(), spot);
                        break;
                    }
                }
                return true;
            }
        }
        return false;
    }
    
    public double removeVehicle(String licensePlate) {
        ParkingSpot spot = vehicleSpotMap.get(licensePlate);
        if (spot == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }
        
        Vehicle vehicle = spot.removeVehicle();
        vehicleSpotMap.remove(licensePlate);
        
        // Calculate price
        return pricingStrategy.calculatePrice(vehicle, LocalDateTime.now());
    }
    
    public void displayAvailability() {
        System.out.println("=== Parking Lot: " + name + " ===");
        for (int i = 0; i < levels.size(); i++) {
            ParkingLevel level = levels.get(i);
            System.out.println("Level " + (i + 1) + ":");
            System.out.println("  Motorcycle spots: " + level.getAvailableSpots(ParkingSpotType.MOTORCYCLE));
            System.out.println("  Compact spots: " + level.getAvailableSpots(ParkingSpotType.COMPACT));
            System.out.println("  Large spots: " + level.getAvailableSpots(ParkingSpotType.LARGE));
        }
    }
}
```

### Salary Negotiation with OOP Skills

**Junior Developer (0-2 years):**
- Base: $60,000 - $80,000
- With strong OOP skills: +10-15%

**Mid-level Developer (2-5 years):**
- Base: $80,000 - $120,000
- With design pattern expertise: +15-20%

**Senior Developer (5+ years):**
- Base: $120,000 - $180,000
- With architectural design skills: +20-25%

**Key Selling Points:**
- Clean, maintainable code writing ability
- System design and architecture skills
- Code review and mentoring capabilities
- Legacy code refactoring experience
- Design pattern implementation expertise

---

## Measuring Your Progress

### Monthly Self-Assessment Checklist

**Month 1-2: Foundations**
- [ ] Can explain OOP vs procedural programming
- [ ] Understand and implement basic encapsulation
- [ ] Create simple class hierarchies
- [ ] Use constructors and access modifiers correctly

**Month 3-4: Core Principles**
- [ ] Implement all four OOP pillars in projects
- [ ] Design interfaces and abstract classes appropriately
- [ ] Use polymorphism for flexible code design
- [ ] Understand when to use inheritance vs composition

**Month 5-6: Design Patterns & Principles**
- [ ] Implement 5+ design patterns from memory
- [ ] Apply SOLID principles in code design
- [ ] Refactor existing code to improve design
- [ ] Recognize and avoid common anti-patterns

**Month 7-8: Advanced Application**
- [ ] Complete complex projects demonstrating OOP mastery
- [ ] Design systems considering scalability and maintainability
- [ ] Mentor others on OOP concepts
- [ ] Contribute to open-source projects

### Portfolio Projects Progression

**Beginner Projects (Month 1-2):**
1. Simple calculator with operations as objects
2. Basic student management system
3. Library book catalog
4. ATM simulation

**Intermediate Projects (Month 3-4):**
1. Complete library management system
2. E-commerce shopping cart
3. Simple game (Tic-tac-toe, Hangman)
4. Bank account management with transactions

**Advanced Projects (Month 5-6):**
1. Multi-user banking system with design patterns
2. Restaurant management with complex workflows
3. Hospital management with role-based access
4. Game with AI players and complex rules

**Expert Projects (Month 7-8):**
1. Learning management system with plugins
2. Social media platform with real-time features
3. Distributed system with microservices design
4. Framework or library for other developers

---

## Community and Networking

### Online Communities
1. **Stack Overflow:** Ask questions and help others
2. **Reddit:** r/programming, r/java, r/Python
3. **Discord/Slack:** Programming communities
4. **LinkedIn:** Professional networking and sharing

### Local Communities
1. **Meetups:** Java User Groups, Python meetups
2. **Conferences:** OOPSLA, JavaOne, PyCon
3. **Code Reviews:** Participate in peer reviews
4. **Hackathons:** Apply OOP skills in time-constrained projects

### Contributing Back
1. **Open Source:** Contribute to existing projects
2. **Teaching:** Create tutorials or mentor others
3. **Speaking:** Present at meetups or conferences
4. **Writing:** Blog about your OOP journey and learnings

---

## Final Thoughts and Next Steps

Mastering Object-Oriented Programming is not just about learning syntax and patterns—it's about developing a mindset for creating maintainable, scalable, and elegant software solutions. The journey from understanding basic concepts to architecting complex systems requires consistent practice, continuous learning, and real-world application.

### Success Metrics
- **Technical:** Can design and implement complex systems using OOP principles
- **Professional:** Able to lead technical discussions and mentor others
- **Personal:** Confident in tackling new challenges and learning new technologies
- **Impact:** Creating software that solves real problems and provides value

### Continuous Learning Path
After mastering OOP, consider exploring:
1. **Software Architecture:** System design at scale
2. **Domain-Driven Design:** Business-focused software modeling  
3. **Clean Architecture:** Organizing code for long-term maintainability
4. **Microservices:** Distributed system design patterns
5. **Cloud Computing:** Modern deployment and scaling strategies

Remember: The goal isn't just to learn OOP concepts, but to become a thoughtful software developer who can create solutions that stand the test of time. Every line of code you write is an opportunity to practice these principles and improve your craft.

Good luck on your OOP mastery journey! 🚀