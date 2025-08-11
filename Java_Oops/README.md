## ENCAPSULATION
Encapsulation is one of the four fundamental Object-Oriented Programming (OOP) concepts.
It is a mechanism that binds the data (attributes) and methods (functions) together in a single unit, known as a class. 
Encapsulation restricts direct access to some of the object's components and can prevent the accidental modification of data.
It is a protective barrier that keeps the data safe within the object and prevents outside code from directly accessing it. 

ENCAPSULATION can be defined as the bundling of data with the methods that operate on that data, restricting direct access to some of the object's components. 
It is a protective barrier that keeps the data safe within the object and prevents outside code from directly accessing it.
In the above code, we have used encapsulation to restrict direct access to the Pen's color and tipSize attributes by using methods to set and get their values. 
This ensures that the internal state of the Pen object can only be modified through its methods, maintaining control over how the data is accessed and modified.
In the Getters_Setters.java, we have used encapsulation to restrict direct access to the Pen's color and tipSize attributes by using methods to set and get their values. 
This ensures that the internal state of the Pen object can only be modified through its methods, maintaining control over how the data is accessed and modified.

## CONSTRUCTOR
A constructor is a special method that is called when an object is instantiated.
It is used to initialize the object's attributes and set up any necessary state for the object.
In Java, a constructor has the same name as the class and does not have a return type.
Constructors can be overloaded, meaning you can have multiple constructors with different parameters to create objects in different ways.
In the above code, we have defined a constructor for the Pen class that takes two parameters:
- `color`: The color of the pen.
- `tipSize`: The size of the pen's tip.
This constructor initializes the Pen object with the specified color and tip size when it is created.

## TYPES OF CONSTRUCTORS
There are three types of constructors in Java:
1. **Non-parameterized Constructor**: This constructor does not take any parameters and initializes the object with default values.
2. **Parameterized Constructor**: This constructor takes parameters to initialize the object with specific values.
3. **Copy Constructor**: This constructor creates a new object as a copy of an existing object, copying its attributes to the new object.

**CONSTRUCTOR OVERLOADING**
Constructor overloading is a feature in Java that allows a class to have more than one constructor with different parameter lists.
This allows you to create objects in different ways, depending on the parameters passed to the constructor.
In the above code, we have defined two constructors for the `Student` class:
- A parameterized constructor that takes `name` and `age` as parameters to initialize the `Student` object.
- A non-parameterized constructor that initializes the `Student` object with default values and prints a message indicating that the default constructor was called.
This allows us to create `Student` objects either with specific values or with default values, demonstrating constructor overloading.

## DECONSTRUCTOR
Java does not have a concept of destructors like some other programming languages (e.g., C++). 
In Java, memory management is handled by the garbage collector, which automatically deallocates memory for objects that are no longer reachable or referenced in the program.
However, you can define a method that can be called to perform cleanup operations before an object is no longer needed, but this is not a destructor in the traditional sense.
In Java, you can use the `finalize()` method to perform cleanup operations before an object is garbage collected, but it is not recommended to rely on it as it is deprecated in later versions of Java.
Instead, you should use try-with-resources or explicit resource management to ensure that resources are released properly.
In the above code, we have not defined a destructor because Java does not support destructors in the same way as languages like C++. 
Instead, Java relies on garbage collection to automatically manage memory and clean up objects that are no longer in use.
If you need to perform cleanup operations, you can define a method to do so, but it is not a destructor in the traditional sense.
In the above code, we have not defined a destructor because Java does not support destructors in the same way as languages like C++. 
Instead, Java relies on garbage collection to automatically manage memory and clean up objects that are no longer in use.
If you need to perform cleanup operations, you can define a method to do so, but it is not a destructor in the traditional sense.
In the above code, we have not defined a destructor because Java does not support destructors in the same way as languages like C++. 

## INHERITANCE
Inheritance is one of the four fundamental Object-Oriented Programming (OOP) concepts.
It allows a new class (subclass or derived class) to inherit properties and behaviors (attributes and methods) from an existing class (superclass or base class).
This promotes code reusability and establishes a hierarchical relationship between classes. 
In the above code, we have defined a `Pen` class that serves as the base class, and a `FountainPen` class that extends the `Pen` class.
The `FountainPen` class inherits the attributes and methods of the `Pen` class, allowing it to use the `color` and `tipSize` attributes and the `write()` method
defined in the `Pen` class.
The `FountainPen` class can also have its own additional attributes and methods, such as `inkType` and `refill()`, which are specific to fountain pens.
This demonstrates how inheritance allows us to create specialized classes that build upon existing classes, promoting code reuse and organization.
In the above code, we have defined a `Pen` class that serves as the base class, and a `FountainPen` class that extends the `Pen` class.

## TYPE OF INHERITANCE
There are several types of inheritance in Java:
1. **Single Inheritance**: A class inherits from one superclass.
this contains single base class and one derived class.
2. **Multiple Inheritance**: A class can inherit from multiple classes (not directly supported in Java, but can be achieved using interfaces).
3. **Multilevel Inheritance**: A class inherits from another class, which in turn inherits from another class, forming a chain of inheritance.
4. **Hierarchical Inheritance**: Multiple classes inherit from a single superclass.
5. **Hybrid Inheritance**: A combination of two or more types of inheritance (not directly supported in Java, but can be achieved using interfaces).

## POLYMORPHISM
Polymorphism is one of the four fundamental Object-Oriented Programming (OOP) concepts.
It allows objects of different classes to be treated as objects of a common superclass.
Polymorphism enables a single interface to represent different underlying forms (data types).
In Java, polymorphism can be achieved through method overriding and method overloading.
