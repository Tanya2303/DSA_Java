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