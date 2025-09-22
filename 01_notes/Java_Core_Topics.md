# Complete Java Core Topics Guide

## Table of Contents
1. [Generic Types and Wildcards](#1-generic-types-and-wildcards)
2. [Exception Handling](#2-exception-handling)
3. [Lambda Expressions and Streams](#3-lambda-expressions-and-streams)
4. [Collection Types Usage](#4-collection-types-usage)
5. [Custom Comparators](#5-custom-comparators)
6. [Primitive vs Wrapper Types](#6-primitive-vs-wrapper-types)
7. [String Manipulation with StringBuilder](#7-string-manipulation-with-stringbuilder)

---

## 1. Generic Types and Wildcards

### Core Concepts
Generics provide type safety at compile time by allowing you to parameterize types. They eliminate the need for casting and catch type errors early.

### Basic Syntax

```java
// Generic class
public class Box<T> {
    private T content;
    
    public void set(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
}

// Usage
Box<String> stringBox = new Box<>();
Box<Integer> intBox = new Box<>();
```

### Wildcards Explained

| Wildcard Type | Syntax | Use Case | Example |
|---------------|--------|----------|---------|
| **Unbounded** | `?` | When you don't care about type | `List<?>` |
| **Upper Bounded** | `? extends T` | Read-only operations | `List<? extends Number>` |
| **Lower Bounded** | `? super T` | Write operations | `List<? super Integer>` |

### Practical Examples

```java
// Upper bounded wildcard - PECS (Producer Extends)
public static double sum(List<? extends Number> numbers) {
    double total = 0.0;
    for (Number num : numbers) {
        total += num.doubleValue(); // Can read as Number
    }
    return total;
}

// Lower bounded wildcard - PECS (Consumer Super)
public static void addNumbers(List<? super Integer> list) {
    list.add(1);    // Can write Integer
    list.add(2);    // Can write Integer
    // Object obj = list.get(0); // Can only read as Object
}

// Usage examples
List<Integer> integers = Arrays.asList(1, 2, 3);
List<Double> doubles = Arrays.asList(1.1, 2.2, 3.3);

sum(integers); // Works
sum(doubles);  // Works

List<Number> numbers = new ArrayList<>();
addNumbers(numbers); // Works
```

### Generic Methods

```java
// Generic method syntax
public static <T> void swap(T[] array, int i, int j) {
    T temp = array[i];
    array[i] = array[j];
    array[j] = temp;
}

// Multiple type parameters
public static <T, U> void processData(T input, U processor) {
    // Process input using processor
}

// Bounded type parameters
public static <T extends Comparable<T>> T findMax(T[] array) {
    T max = array[0];
    for (T item : array) {
        if (item.compareTo(max) > 0) {
            max = item;
        }
    }
    return max;
}
```

### Common Pitfalls & Best Practices

#### ❌ Common Mistakes
```java
// Type erasure issues
List<String> strings = new ArrayList<>();
List<Integer> integers = new ArrayList<>();
// strings.getClass() == integers.getClass() // true!

// Cannot create generic arrays
// T[] array = new T[10]; // Compilation error

// Cannot use primitives
// List<int> numbers; // Error! Use List<Integer>
```

#### ✅ Best Practices
```java
// Use diamond operator (Java 7+)
Map<String, List<Integer>> map = new HashMap<>(); // Preferred
Map<String, List<Integer>> map2 = new HashMap<String, List<Integer>>(); // Verbose

// Use PECS principle
// Producer Extends, Consumer Super
public void copy(List<? extends T> source, List<? super T> destination) {
    for (T item : source) {
        destination.add(item);
    }
}

// Prefer generic methods over wildcards when possible
public static <T> void process(List<T> list) { } // Better
public static void process(List<?> list) { }     // Less flexible
```

### Interview Questions & Answers

**Q: What is type erasure in Java generics?**
A: Type erasure removes generic type information at runtime. Generic types exist only at compile time for type checking.

```java
// At compile time: List<String>
// At runtime: List (raw type)
List<String> strings = new ArrayList<>();
Class<?> clazz = strings.getClass(); // Returns class of ArrayList, not ArrayList<String>
```

**Q: When would you use `? extends` vs `? super`?**
A: Use `? extends` when you need to read from a collection (producer), and `? super` when you need to write to it (consumer). Remember PECS: Producer Extends, Consumer Super.

---

## 2. Exception Handling

### Core Concepts
Exceptions are events that disrupt normal program flow. Java uses try-catch-finally blocks to handle exceptions gracefully.

### Exception Hierarchy

```
Throwable
├── Error (System-level, don't catch)
│   ├── OutOfMemoryError
│   └── StackOverflowError
└── Exception
    ├── RuntimeException (Unchecked)
    │   ├── NullPointerException
    │   ├── IllegalArgumentException
    │   └── IndexOutOfBoundsException
    └── Checked Exceptions
        ├── IOException
        ├── SQLException
        └── ClassNotFoundException
```

### Basic Syntax

```java
// Basic try-catch
try {
    // Code that might throw exception
    int result = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero: " + e.getMessage());
} finally {
    // Always executes (cleanup code)
    System.out.println("Cleanup completed");
}
```

### Multiple Exception Handling

```java
// Multiple catch blocks
try {
    String str = null;
    int length = str.length(); // NullPointerException
    int[] arr = new int[5];
    arr[10] = 1; // ArrayIndexOutOfBoundsException
} catch (NullPointerException e) {
    System.out.println("Null pointer: " + e.getMessage());
} catch (ArrayIndexOutOfBoundsException e) {
    System.out.println("Array index: " + e.getMessage());
} catch (Exception e) {
    System.out.println("General exception: " + e.getMessage());
}

// Multi-catch (Java 7+)
try {
    // risky code
} catch (IOException | SQLException e) {
    System.out.println("IO or SQL error: " + e.getMessage());
}
```

### Try-with-Resources

```java
// Automatic resource management (Java 7+)
try (FileReader file = new FileReader("data.txt");
     BufferedReader buffer = new BufferedReader(file)) {
    
    return buffer.readLine();
} catch (IOException e) {
    System.out.println("File error: " + e.getMessage());
}
// Resources automatically closed, even if exception occurs
```

### Custom Exceptions

```java
// Custom checked exception
public class InsufficientFundsException extends Exception {
    private double amount;
    
    public InsufficientFundsException(double amount) {
        super("Insufficient funds: " + amount);
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
}

// Custom unchecked exception
public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String email) {
        super("Invalid email format: " + email);
    }
}

// Usage
public class BankAccount {
    private double balance;
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount);
        }
        balance -= amount;
    }
}
```

### Exception Handling Patterns

```java
// Pattern 1: Catch and log
public void processFile(String filename) {
    try {
        // Process file
    } catch (IOException e) {
        logger.error("Failed to process file: " + filename, e);
        // Don't rethrow if you can handle it
    }
}

// Pattern 2: Catch, log, and rethrow
public void processData() throws ProcessingException {
    try {
        // Process data
    } catch (SQLException e) {
        logger.error("Database error during processing", e);
        throw new ProcessingException("Processing failed", e);
    }
}

// Pattern 3: Try-catch with alternative flow
public String readConfigValue(String key) {
    try {
        return configFile.getProperty(key);
    } catch (IOException e) {
        logger.warn("Config file not accessible, using default");
        return getDefaultValue(key);
    }
}
```

### Best Practices Summary

| Practice | ✅ Do | ❌ Don't |
|----------|-------|----------|
| **Catch Specific** | `catch (FileNotFoundException e)` | `catch (Exception e)` |
| **Resource Cleanup** | Use try-with-resources | Manual cleanup in finally |
| **Exception Messages** | Provide meaningful context | Generic "Error occurred" |
| **Performance** | Handle expected cases normally | Use exceptions for control flow |
| **Logging** | Log at appropriate level | Swallow exceptions silently |

### Common Interview Questions

**Q: What's the difference between checked and unchecked exceptions?**
```java
// Checked - Must handle or declare
public void readFile() throws IOException {  // Must declare
    FileReader file = new FileReader("test.txt");
}

// Unchecked - Optional handling
public void divide(int a, int b) {
    int result = a / b; // May throw ArithmeticException, no declaration needed
}
```

**Q: When should you create custom exceptions?**
A: Create custom exceptions when you need domain-specific error handling, want to provide additional context, or need to distinguish between different error conditions in your application logic.

---

## 3. Lambda Expressions and Streams

### Core Concepts
Lambda expressions provide a concise way to represent anonymous functions. Streams enable functional-style operations on collections.

### Lambda Syntax Evolution

```java
// Traditional anonymous class
Comparator<String> traditional = new Comparator<String>() {
    @Override
    public int compare(String a, String b) {
        return a.compareTo(b);
    }
};

// Lambda expression
Comparator<String> lambda = (a, b) -> a.compareTo(b);

// Method reference
Comparator<String> methodRef = String::compareTo;
```

### Lambda Syntax Patterns

```java
// No parameters
Runnable task = () -> System.out.println("Hello");

// Single parameter (parentheses optional)
Consumer<String> print = s -> System.out.println(s);
Consumer<String> print2 = (s) -> System.out.println(s);

// Multiple parameters
BinaryOperator<Integer> add = (a, b) -> a + b;

// Block body
Consumer<List<String>> processor = list -> {
    list.sort(String::compareTo);
    list.forEach(System.out::println);
};

// Returning values
Function<String, Integer> length = s -> s.length();
Function<String, String> upper = s -> {
    String result = s.toUpperCase();
    return result;
};
```

### Common Functional Interfaces

| Interface | Method | Use Case | Example |
|-----------|---------|----------|---------|
| `Predicate<T>` | `test(T)` | Testing conditions | `s -> s.length() > 5` |
| `Function<T,R>` | `apply(T)` | Transforming data | `s -> s.toUpperCase()` |
| `Consumer<T>` | `accept(T)` | Side effects | `s -> System.out.println(s)` |
| `Supplier<T>` | `get()` | Providing values | `() -> new ArrayList<>()` |
| `BinaryOperator<T>` | `apply(T,T)` | Combining two values | `(a, b) -> a + b` |

### Stream Basics

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

// Create streams
Stream<String> stream1 = names.stream();
Stream<String> stream2 = Stream.of("a", "b", "c");
Stream<Integer> stream3 = Stream.iterate(0, n -> n + 2).limit(5); // 0,2,4,6,8
```

### Stream Operations Cheat Sheet

#### Intermediate Operations (Lazy)
```java
List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

// filter - select elements
words.stream()
     .filter(s -> s.length() > 5)  // ["banana", "cherry"]

// map - transform elements  
words.stream()
     .map(String::toUpperCase)     // ["APPLE", "BANANA", "CHERRY", "DATE"]

// flatMap - flatten nested structures
List<List<String>> nested = Arrays.asList(
    Arrays.asList("a", "b"),
    Arrays.asList("c", "d")
);
nested.stream()
      .flatMap(List::stream)       // ["a", "b", "c", "d"]

// distinct - remove duplicates
Stream.of(1, 2, 2, 3, 3, 4)
      .distinct()                  // [1, 2, 3, 4]

// sorted - sort elements
words.stream()
     .sorted()                     // ["apple", "banana", "cherry", "date"]

// limit/skip - pagination
words.stream()
     .skip(1)
     .limit(2)                     // ["banana", "cherry"]
```

#### Terminal Operations (Eager)
```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// collect - gather results
List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());

// forEach - side effects
numbers.stream()
       .forEach(System.out::println);

// reduce - combine elements
Optional<Integer> sum = numbers.stream()
    .reduce((a, b) -> a + b);
    
int sum2 = numbers.stream()
    .reduce(0, Integer::sum);

// find operations
Optional<Integer> first = numbers.stream()
    .filter(n -> n > 5)
    .findFirst();

// match operations
boolean anyEven = numbers.stream()
    .anyMatch(n -> n % 2 == 0);
    
boolean allPositive = numbers.stream()
    .allMatch(n -> n > 0);

// count
long count = numbers.stream()
    .filter(n -> n > 5)
    .count();
```

### Practical Stream Examples

```java
// Example 1: Processing employee data
class Employee {
    private String name;
    private String department;
    private double salary;
    
    // constructors, getters...
}

List<Employee> employees = Arrays.asList(
    new Employee("Alice", "Engineering", 95000),
    new Employee("Bob", "Sales", 65000),
    new Employee("Charlie", "Engineering", 85000)
);

// Find average salary by department
Map<String, Double> avgSalaryByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)
    ));

// Get names of high-paid engineers
List<String> highPaidEngineers = employees.stream()
    .filter(e -> e.getDepartment().equals("Engineering"))
    .filter(e -> e.getSalary() > 90000)
    .map(Employee::getName)
    .collect(Collectors.toList());
```

### Advanced Stream Operations

```java
// Collectors examples
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Join strings
String joined = names.stream()
    .collect(Collectors.joining(", ")); // "Alice, Bob, Charlie"

// Group by length
Map<Integer, List<String>> byLength = names.stream()
    .collect(Collectors.groupingBy(String::length));

// Partition by condition
Map<Boolean, List<String>> partition = names.stream()
    .collect(Collectors.partitioningBy(s -> s.length() > 4));

// Custom collector
String custom = names.stream()
    .collect(Collectors.reducing("", 
                                Function.identity(), 
                                (s1, s2) -> s1 + "-" + s2));
```

### Common Pitfalls & Best Practices

#### ❌ Common Mistakes
```java
// Stream reuse (streams are single-use)
Stream<String> stream = names.stream();
stream.filter(s -> s.startsWith("A")).collect(toList()); // OK
stream.filter(s -> s.startsWith("B")).collect(toList()); // ERROR!

// Side effects in stream operations
names.stream()
     .map(s -> {
         System.out.println(s); // Side effect in map - avoid!
         return s.toUpperCase();
     });

// Use forEach for side effects instead
names.stream()
     .map(String::toUpperCase)
     .forEach(System.out::println);
```

#### ✅ Best Practices
```java
// Chain operations efficiently
List<String> result = names.stream()
    .filter(Objects::nonNull)       // Filter first (reduces elements)
    .map(String::toLowerCase)       // Then transform
    .distinct()                     // Remove duplicates
    .sorted()                       // Finally sort
    .collect(Collectors.toList());

// Use method references when possible
names.stream().map(String::toUpperCase)    // Better
names.stream().map(s -> s.toUpperCase())   // Verbose

// Prefer specific collectors
names.stream().collect(Collectors.toList())     // Better
names.stream().collect(Collectors.toCollection(ArrayList::new)) // When you need specific type
```

### Interview Questions

**Q: What's the difference between map() and flatMap()?**
```java
List<String> sentences = Arrays.asList("Hello World", "Java Streams");

// map - transforms each element
List<String[]> words1 = sentences.stream()
    .map(s -> s.split(" "))  // Stream<String[]>
    .collect(toList());      // List<String[]> - nested!

// flatMap - flattens the result
List<String> words2 = sentences.stream()
    .flatMap(s -> Arrays.stream(s.split(" "))) // Stream<String>
    .collect(toList());      // List<String> - flat!
```

**Q: When should you use parallel streams?**
A: Use parallel streams for CPU-intensive operations on large datasets where the processing cost per element is significant. Avoid for I/O operations or small datasets due to overhead.

---

## 4. Collection Types Usage

### Collection Hierarchy Overview

```
Collection<E>
├── List<E> (Ordered, allows duplicates)
│   ├── ArrayList<E>
│   ├── LinkedList<E>
│   └── Vector<E>
├── Set<E> (No duplicates)
│   ├── HashSet<E>
│   ├── LinkedHashSet<E>
│   └── TreeSet<E>
└── Queue<E> (FIFO operations)
    ├── LinkedList<E>
    ├── ArrayDeque<E>
    └── PriorityQueue<E>

Map<K,V> (Key-value pairs)
├── HashMap<K,V>
├── LinkedHashMap<K,V>
├── TreeMap<K,V>
└── ConcurrentHashMap<K,V>
```

### When to Use Each Collection

| Collection Type | Use When | Time Complexity | Example Use Case |
|----------------|----------|-----------------|------------------|
| **ArrayList** | Random access, frequent reads | O(1) access, O(n) insert/delete | Dynamic arrays, caching |
| **LinkedList** | Frequent insertions/deletions | O(n) access, O(1) insert/delete | Queues, undo operations |
| **HashSet** | Fast lookups, no duplicates | O(1) average operations | Unique elements, membership testing |
| **TreeSet** | Sorted unique elements | O(log n) operations | Sorted data, range queries |
| **HashMap** | Key-value mapping, fast access | O(1) average operations | Caching, indexing |
| **TreeMap** | Sorted key-value pairs | O(log n) operations | Sorted maps, range operations |

### Detailed Comparisons

#### List Implementations
```java
// ArrayList - Best for random access
List<String> arrayList = new ArrayList<>();
arrayList.add("First");     // O(1) amortized
arrayList.get(0);           // O(1)
arrayList.add(0, "New");    // O(n) - shifts elements

// LinkedList - Best for frequent insertions/deletions
List<String> linkedList = new LinkedList<>();
linkedList.addFirst("First");   // O(1)
linkedList.addLast("Last");     // O(1)
linkedList.get(1000);           // O(n) - must traverse

// When to choose:
// ArrayList: More reads than writes, need random access
// LinkedList: More insertions/deletions, especially at beginning/middle
```

#### Set Implementations
```java
// HashSet - Fastest operations, no ordering
Set<String> hashSet = new HashSet<>();
hashSet.add("apple");       // O(1) average
hashSet.contains("apple");  // O(1) average

// LinkedHashSet - Maintains insertion order
Set<String> linkedSet = new LinkedHashSet<>();
// Slightly slower than HashSet but preserves order

// TreeSet - Sorted order, slower operations
Set<String> treeSet = new TreeSet<>();
treeSet.add("banana");      // O(log n)
treeSet.add("apple");       // Will be sorted: [apple, banana]
```

#### Map Implementations
```java
// HashMap - Fastest, no ordering guarantees
Map<String, Integer> hashMap = new HashMap<>();
hashMap.put("key", 1);      // O(1) average
hashMap.get("key");         // O(1) average

// LinkedHashMap - Maintains insertion or access order
Map<String, Integer> linkedMap = new LinkedHashMap<>();
Map<String, Integer> lruMap = new LinkedHashMap<>(16, 0.75f, true); // Access order

// TreeMap - Sorted by keys
Map<String, Integer> treeMap = new TreeMap<>();
treeMap.put("zebra", 1);
treeMap.put("apple", 2);    // Sorted: {apple=2, zebra=1}
```

### Practical Examples

```java
// Example 1: Choosing the right collection for different scenarios

// Scenario 1: Shopping cart (preserve order, allow duplicates)
List<Item> cart = new ArrayList<>();

// Scenario 2: User permissions (unique, fast lookup)
Set<Permission> permissions = new HashSet<>();

// Scenario 3: Word frequency counter
Map<String, Integer> wordCount = new HashMap<>();
for (String word : words) {
    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
}

// Scenario 4: Leaderboard (sorted by score)
TreeMap<Integer, String> leaderboard = new TreeMap<>(Collections.reverseOrder());

// Scenario 5: LRU Cache implementation
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    
    public LRUCache(int capacity) {
        super(16, 0.75f, true); // access-order
        this.capacity = capacity;
    }
    
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
```

### Collection Performance Comparison

```java
// Performance testing example
public class CollectionPerformance {
    public static void compareListOperations() {
        int size = 100000;
        
        // ArrayList vs LinkedList for random access
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();
        
        // Fill both lists
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        
        // Random access test
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.get(size / 2); // O(1)
        }
        long arrayListTime = System.nanoTime() - start;
        
        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.get(size / 2); // O(n)
        }
        long linkedListTime = System.nanoTime() - start;
        
        System.out.println("ArrayList: " + arrayListTime);
        System.out.println("LinkedList: " + linkedListTime);
        // ArrayList will be much faster for random access
    }
}
```

### Collection Utility Methods

```java
// Collections class utilities
List<String> list = Arrays.asList("c", "a", "b");

Collections.sort(list);                    // [a, b, c]
Collections.reverse(list);                 // [c, b, a]
Collections.shuffle(list);                 // Random order
Collections.binarySearch(list, "b");       // Binary search (list must be sorted)
Collections.frequency(list, "a");          // Count occurrences

// Unmodifiable collections
List<String> immutable = Collections.unmodifiableList(list);
Map<String, Integer> immutableMap = Collections.unmodifiableMap(map);

// Synchronized collections (thread-safe wrappers)
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());
```

### Best Practices

#### ✅ Best Practices
```java
// Use interfaces for declarations
List<String> list = new ArrayList<>();     // Good
ArrayList<String> list = new ArrayList<>(); // Avoid

// Initialize with capacity if size is known
List<String> list = new ArrayList<>(1000); // Avoids resizing

// Use isEmpty() instead of size() == 0
if (list.isEmpty()) { } // Better than size() == 0

// Use enhanced for-loop when index not needed
for (String item : list) {
    process(item);
}

// Use appropriate collection for the use case
Set<String> uniqueWords = new HashSet<>();  // For uniqueness
List<String> orderedWords = new ArrayList<>(); // For ordering
```

#### ❌ Common Mistakes
```java
// Using wrong collection type
List<String> list = new ArrayList<>();
if (!list.contains(item)) {  // O(n) operation!
    list.add(item);
}
// Should use Set instead

// Modifying collection while iterating
for (String item : list) {
    if (condition) {
        list.remove(item); // ConcurrentModificationException!
    }
}
// Use iterator.remove() or collect items to remove separately
```

### Interview Questions

**Q: When would you choose ArrayList over LinkedList?**
A: Use ArrayList when you need frequent random access to elements (get/set operations), as ArrayList provides O(1) access while LinkedList requires O(n) traversal. Use LinkedList when you frequently insert/delete elements at the beginning or middle of the list.

**Q: What's the difference between HashMap and TreeMap?**
A: HashMap provides O(1) average-case operations with no ordering, while TreeMap provides O(log n) operations with sorted key ordering. Use HashMap for fast lookups, TreeMap when you need sorted keys or range operations.

---

## 5. Custom Comparators

### Core Concepts
Comparators define custom ordering logic for objects. They're essential for sorting collections and implementing data structures like TreeSet and TreeMap.

### Basic Comparator Implementation

```java
// Method 1: Anonymous class (traditional)
Comparator<String> lengthComparator = new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return Integer.compare(s1.length(), s2.length());
    }
};

// Method 2: Lambda expression (preferred)
Comparator<String> lengthComp = (s1, s2) -> Integer.compare(s1.length(), s2.length());

// Method 3: Method reference
Comparator<String> lengthCompRef = Comparator.comparing(String::length);
```

### Comparator Return Values

```java
// Understanding return values:
// negative (<0): first object comes before second
// zero (0): objects are equal for sorting
// positive (>0): first object comes after second

public int compare(Person p1, Person p2) {
    if (p1.getAge() < p2.getAge()) return -1;  // p1 comes first
    if (p1.getAge() > p2.getAge()) return 1;   // p2 comes first  
    return 0;                                  // equal
}

// Better: Use Integer.compare()
public int compare(Person p1, Person p2) {
    return Integer.compare(p1.getAge(), p2.getAge());
}
```

### Comparator Factory Methods

```java
class Person {
    private String name;
    private int age;
    private double salary;
    // constructors, getters...
}

List<Person> people = Arrays.asList(
    new Person("Alice", 30, 75000),
    new Person("Bob", 25, 65000),
    new Person("Charlie", 35, 85000)
);

// comparing() - extract and compare by property
Comparator<Person> byAge = Comparator.comparing(Person::getAge);
Comparator<Person> byName = Comparator.comparing(Person::getName);
Comparator<Person> bySalary = Comparator.comparing(Person::getSalary);

// comparingInt(), comparingLong(), comparingDouble() - avoid boxing
Comparator<Person> byAgeOptimized = Comparator.comparingInt(Person::getAge);

// naturalOrder() and reverseOrder()
Comparator<String> natural = Comparator.naturalOrder();    // a, b, c
Comparator<String> reverse = Comparator.reverseOrder();    // c, b, a
```

### Chaining Comparators

```java
// thenComparing() - chain multiple comparators
Comparator<Person> multiSort = Comparator
    .comparing(Person::getAge)                    // Primary sort by age
    .thenComparing(Person::getName)               // Then by name
    .thenComparing(Person::getSalary, reverseOrder()); // Then by salary (descending)

// Usage
people.sort(multiSort);

// Equivalent verbose version
Comparator<Person> verbose = new Comparator<Person>() {
    @Override
    public int compare(Person p1, Person p2) {
        int ageCompare = Integer.compare(p1.getAge(), p2.getAge());
        if (ageCompare != 0) return ageCompare;
        
        int nameCompare = p1.getName().compareTo(p2.getName());
        if (nameCompare != 0) return nameCompare;
        
        return Double.compare(p2.getSalary(), p1.getSalary()); // Reverse
    }
};
```

### Null-Safe Comparators

```java
// Handle null values safely
List<String> listWithNulls = Arrays.asList("apple", null, "banana", null, "cherry");

// nullsFirst() - nulls come first
Comparator<String> nullsFirst = Comparator.nullsFirst(String::compareTo);
listWithNulls.sort(nullsFirst); // [null, null, "apple", "banana", "cherry"]

// nullsLast() - nulls come last
Comparator<String> nullsLast = Comparator.nullsLast(String::compareTo);
listWithNulls.sort(nullsLast); // ["apple", "banana", "cherry", null, null]

// Complex example with null handling
Comparator<Person> safeComparator = Comparator
    .nullsLast(                                    // Handle null Person objects
        Comparator.comparing(
            Person::getName, 
            Comparator.nullsFirst(String::compareTo)  // Handle null names
        )
    );
```

### Practical Examples

```java
// Example 1: Sorting students by grade (descending), then by name
class Student {
    private String name;
    private int grade;
    
    // constructors, getters...
}

List<Student> students = Arrays.asList(
    new Student("Alice", 85),
    new Student("Bob", 92),
    new Student("Charlie", 85),
    new Student("Diana", 78)
);

// Sort by grade (highest first), then by name (alphabetical)
Comparator<Student> studentComparator = Comparator
    .comparingInt(Student::getGrade)
    .reversed()                           // Reverse for highest first
    .thenComparing(Student::getName);

students.sort(studentComparator);
// Result: [Bob(92), Alice(85), Charlie(85), Diana(78)]

// Example 2: Custom business logic comparator
class Task {
    private String name;
    private Priority priority;
    private LocalDate dueDate;
    
    enum Priority { LOW, MEDIUM, HIGH, CRITICAL }
}

// Sort by priority (CRITICAL first), then by due date (earliest first)
Comparator<Task> taskComparator = Comparator
    .<Task, Priority>comparing(Task::getPriority, (p1, p2) -> {
        // Custom priority ordering
        int[] priorityOrder = {3, 2, 1, 0}; // CRITICAL=0, HIGH=1, MEDIUM=2, LOW=3
        return Integer.compare(priorityOrder[p1.ordinal()], priorityOrder[p2.ordinal()]);
    })
    .thenComparing(Task::getDueDate);

// Example 3: Sorting map entries
Map<String, Integer> map = new HashMap<>();
map.put("apple", 5);
map.put("banana", 2);
map.put("cherry", 8);

// Sort by value (descending)
List<Map.Entry<String, Integer>> sortedEntries = map.entrySet()
    .stream()
    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
    .collect(Collectors.toList());
// Result: [cherry=8, apple=5, banana=2]

// Sort by key length, then by value
List<Map.Entry<String, Integer>> complexSort = map.entrySet()
    .stream()
    .sorted(Map.Entry.<String, Integer>comparingByKey(
        Comparator.comparing(String::length)
                  .thenComparing(String::compareTo))
            .thenComparing(Map.Entry::getValue))
    .collect(Collectors.toList());
```

### Using Comparators with Collections

```java
// With ArrayList.sort()
List<String> words = Arrays.asList("banana", "apple", "cherry");
words.sort(Comparator.comparing(String::length).reversed());

// With Collections.sort()
Collections.sort(words, Comparator.naturalOrder());

// With TreeSet (sorted set)
Set<Person> sortedPeople = new TreeSet<>(Comparator.comparing(Person::getAge));
sortedPeople.addAll(people);

// With TreeMap (sorted map)
Map<Person, String> sortedMap = new TreeMap<>(
    Comparator.comparing(Person::getName)
              .thenComparing(Person::getAge)
);

// With Stream.sorted()
List<Person> sortedByAge = people.stream()
    .sorted(Comparator.comparing(Person::getAge))
    .collect(Collectors.toList());
```

### Performance Considerations

```java
// Avoid creating comparators inside loops
// ❌ Bad - creates new comparator each time
for (List<Person> group : groups) {
    group.sort(Comparator.comparing(Person::getAge)); // New comparator each iteration
}

// ✅ Good - reuse comparator
Comparator<Person> ageComparator = Comparator.comparing(Person::getAge);
for (List<Person> group : groups) {
    group.sort(ageComparator);
}

// Use primitive-specific comparators to avoid boxing
Comparator<Person> boxingComparator = Comparator.comparing(Person::getAge); // Boxing
Comparator<Person> primitiveComparator = Comparator.comparingInt(Person::getAge); // No boxing
```

### Common Patterns and Idioms

```java
// Pattern 1: Reverse comparison result
Comparator<String> reverseLengthComparator = (s1, s2) -> 
    Integer.compare(s2.length(), s1.length()); // Note: s2, s1 swapped

// Pattern 2: Boolean comparison (true values first)
Comparator<Person> activeFirstComparator = Comparator
    .comparing(Person::isActive, (b1, b2) -> Boolean.compare(b2, b1));

// Pattern 3: Case-insensitive string comparison
Comparator<String> caseInsensitive = String.CASE_INSENSITIVE_ORDER;
// Or
Comparator<String> caseInsensitive2 = (s1, s2) -> 
    s1.toLowerCase().compareTo(s2.toLowerCase());

// Pattern 4: Distance/similarity comparator
Point referencePoint = new Point(0, 0);
Comparator<Point> distanceComparator = Comparator
    .comparingDouble(p -> p.distanceTo(referencePoint));

// Pattern 5: Multi-field comparison with weights
Comparator<Product> productRanking = (p1, p2) -> {
    double score1 = p1.getQuality() * 0.5 + p1.getPrice() * 0.3 + p1.getReviews() * 0.2;
    double score2 = p2.getQuality() * 0.5 + p2.getPrice() * 0.3 + p2.getReviews() * 0.2;
    return Double.compare(score2, score1); // Higher score first
};
```

### Best Practices Summary

#### ✅ Best Practices
```java
// Use factory methods when possible
Comparator<Person> good = Comparator.comparing(Person::getName);

// Chain comparators for complex sorting
Comparator<Person> chained = Comparator
    .comparing(Person::getDepartment)
    .thenComparing(Person::getSalary, reverseOrder())
    .thenComparing(Person::getName);

// Handle nulls explicitly
Comparator<String> nullSafe = Comparator.nullsLast(naturalOrder());

// Store frequently used comparators as constants
public static final Comparator<Person> BY_AGE = comparingInt(Person::getAge);
public static final Comparator<Person> BY_NAME = comparing(Person::getName);
```

#### ❌ Common Mistakes
```java
// Inconsistent with equals() (if implementing Comparable)
class Person implements Comparable<Person> {
    public int compareTo(Person other) {
        return name.compareTo(other.name); // Only compares name
    }
    
    public boolean equals(Object obj) {
        // Compares name AND age - inconsistent!
        Person other = (Person) obj;
        return name.equals(other.name) && age == other.age;
    }
}

// Floating point comparison without tolerance
Comparator<Double> bad = (d1, d2) -> d1 == d2 ? 0 : (d1 < d2 ? -1 : 1);
Comparator<Double> good = Double::compare; // Handles NaN, infinity correctly
```

### Interview Questions & Answers

**Q: How do you sort a list of custom objects by multiple fields?**
```java
// Example: Sort employees by department, then salary (desc), then name
List<Employee> employees = getEmployees();
employees.sort(Comparator
    .comparing(Employee::getDepartment)
    .thenComparing(Employee::getSalary, reverseOrder())
    .thenComparing(Employee::getName));
```

**Q: What's the difference between Comparable and Comparator?**
A: 
- **Comparable**: Natural ordering defined within the class (implements `compareTo()`)
- **Comparator**: External comparison logic, allows multiple sorting strategies

```java
// Comparable - single natural ordering
class Person implements Comparable<Person> {
    public int compareTo(Person other) {
        return name.compareTo(other.name); // Natural order by name
    }
}

// Comparator - multiple custom orderings
Comparator<Person> byAge = comparing(Person::getAge);
Comparator<Person> bySalary = comparing(Person::getSalary);
```

---

## 6. Primitive vs Wrapper Types

### Core Concepts
Java has 8 primitive types and their corresponding wrapper classes. Understanding when to use each is crucial for performance and correctness.

### Primitive vs Wrapper Overview

| Primitive | Wrapper Class | Default Value | Memory | Null Capable |
|-----------|---------------|---------------|---------|--------------|
| `boolean` | `Boolean` | `false` | 1 bit | No / Yes |
| `byte` | `Byte` | `0` | 8 bits | No / Yes |
| `short` | `Short` | `0` | 16 bits | No / Yes |
| `int` | `Integer` | `0` | 32 bits | No / Yes |
| `long` | `Long` | `0L` | 64 bits | No / Yes |
| `float` | `Float` | `0.0f` | 32 bits | No / Yes |
| `double` | `Double` | `0.0` | 64 bits | No / Yes |
| `char` | `Character` | `'\u0000'` | 16 bits | No / Yes |

### Autoboxing and Unboxing

```java
// Autoboxing: primitive → wrapper
int primitive = 42;
Integer wrapper = primitive; // Autoboxing: Integer.valueOf(42)

// Unboxing: wrapper → primitive  
Integer wrapperValue = 100;
int primitiveValue = wrapperValue; // Unboxing: wrapperValue.intValue()

// In collections (only accept objects)
List<Integer> numbers = new ArrayList<>();
numbers.add(42); // Autoboxing happens automatically
int first = numbers.get(0); // Unboxing happens automatically
```

### Performance Implications

```java
// Performance comparison
public class PrimitiveVsWrapper {
    
    // ✅ Fast: primitive operations
    public static long sumPrimitives(int[] numbers) {
        long sum = 0;
        for (int num : numbers) {
            sum += num; // Direct primitive operation
        }
        return sum;
    }
    
    // ❌ Slower: wrapper operations with autoboxing
    public static Long sumWrappers(List<Integer> numbers) {
        Long sum = 0L; // Wrapper
        for (Integer num : numbers) {
            sum += num; // Unboxing num, boxing sum
        }
        return sum;
    }
    
    // ✅ Better: minimize boxing/unboxing
    public static long sumWrappersOptimized(List<Integer> numbers) {
        long sum = 0; // Primitive accumulator
        for (Integer num : numbers) {
            sum += num; // Only unboxing, no boxing
        }
        return sum;
    }
}
```

### Memory and Caching

```java
// Integer caching (-128 to 127)
Integer a = 100;
Integer b = 100;
System.out.println(a == b); // true (cached)

Integer c = 200;
Integer d = 200;
System.out.println(c == d); // false (not cached, different objects)

// Always use equals() for wrapper comparison
System.out.println(c.equals(d)); // true

// Explicit creation bypasses cache
Integer e = new Integer(100); // Deprecated in Java 9+
Integer f = Integer.valueOf(100);
System.out.println(e == f); // false (e is new object)

// Cache ranges for wrapper types:
// Boolean: true/false (both cached)
// Byte: -128 to 127 (all values cached)
// Short: -128 to 127
// Integer: -128 to 127 (configurable with -XX:AutoBoxCacheMax)
// Long: -128 to 127
// Character: 0 to 127
```

### When to Use Primitives vs Wrappers

```java
// ✅ Use primitives when:
public class Calculator {
    // 1. Performance-critical code
    public double calculateDistance(double x1, double y1, double x2, double y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    // 2. Local variables and method parameters
    public int fibonacci(int n) {
        if (n <= 1) return n;
        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
    
    // 3. Array elements for better performance
    int[] primitiveArray = new int[1000]; // Compact, fast
}

// ✅ Use wrappers when:
public class UserProfile {
    // 1. Object fields that can be null
    private Integer age;        // null means "not specified"
    private Boolean verified;   // null means "not checked yet"
    
    // 2. Collections (require objects)
    private List<Integer> scores = new ArrayList<>();
    private Map<String, Boolean> settings = new HashMap<>();
    
    // 3. Generic type parameters
    public <T extends Number> T processNumber(T number) {
        // Can't use primitive types as generic parameters
        return number;
    }
    
    // 4. When you need methods from wrapper classes
    public boolean isValidAge(String ageStr) {
        try {
            Integer age = Integer.parseInt(ageStr);
            return age >= 0 && age <= 150;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
```

### Common Operations and Conversions

```java
// String conversions
int number = 42;
String str1 = Integer.toString(number);        // "42"
String str2 = String.valueOf(number);          // "42" 
String str3 = number + "";                     // "42" (less efficient)

// Parsing strings to primitives/wrappers
String numStr = "123";
int primitive = Integer.parseInt(numStr);       // throws NumberFormatException
Integer wrapper = Integer.valueOf(numStr);     // throws NumberFormatException

// Safe parsing with default values
public static int safeParseInt(String str, int defaultValue) {
    try {
        return Integer.parseInt(str);
    } catch (NumberFormatException e) {
        return defaultValue;
    }
}

// Using Optional for safe parsing
public static Optional<Integer> parseIntSafe(String str) {
    try {
        return Optional.of(Integer.parseInt(str));
    } catch (NumberFormatException e) {
        return Optional.empty();
    }
}

// Number comparisons
Integer num1 = 100;
Integer num2 = 100;

// ❌ Wrong: reference comparison
if (num1 == num2) { } // Unreliable due to caching

// ✅ Correct: value comparison
if (num1.equals(num2)) { } // Always correct
if (Objects.equals(num1, num2)) { } // Null-safe

// For primitives, == is fine
int a = 100, b = 100;
if (a == b) { } // Correct for primitives
```

### Practical Examples

```java
// Example 1: Processing optional numeric data
class Survey {
    private Integer age;          // null = not provided
    private Boolean married;      // null = prefer not to say
    private Double income;        // null = not disclosed
    
    // Safe getters with defaults
    public int getAgeOrDefault(int defaultAge) {
        return age != null ? age : defaultAge;
    }
    
    public boolean isMarriedKnown() {
        return married != null;
    }
    
    // Statistics calculation
    public static double calculateAverageAge(List<Survey> surveys) {
        return surveys.stream()
            .filter(s -> s.age != null)     // Skip null ages
            .mapToInt(s -> s.age)           // Unbox to primitive
            .average()
            .orElse(0.0);
    }
}

// Example 2: Configuration management
class Config {
    // Use wrappers for optional settings
    private Integer maxConnections;    // null = use default
    private Long timeoutMs;           // null = no timeout
    private Boolean debugEnabled;     // null = use system default
    
    // Getters with sensible defaults
    public int getMaxConnections() {
        return maxConnections != null ? maxConnections : 10;
    }
    
    public long getTimeoutMs() {
        return timeoutMs != null ? timeoutMs : 30000L;
    }
    
    public boolean isDebugEnabled() {
        return debugEnabled != null ? debugEnabled : false;
    }
}

// Example 3: Avoiding common pitfalls
class MathUtils {
    // Null-safe arithmetic
    public static Integer add(Integer a, Integer b) {
        if (a == null || b == null) {
            return null;
        }
        return a + b; // Autoboxing handles the result
    }
    
    // Performance-critical method using primitives
    public static double[] normalize(double[] values) {
        double sum = 0.0;
        for (double value : values) {  // Primitive loop
            sum += value;
        }
        
        double[] normalized = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            normalized[i] = values[i] / sum; // All primitive operations
        }
        return normalized;
    }
}
```

### Best Practices Summary

#### ✅ Best Practices
```java
// Use primitives for performance-critical code
public double calculateSum(double[] values) {
    double sum = 0.0; // Primitive accumulator
    for (double value : values) {
        sum += value; // No boxing overhead
    }
    return sum;
}

// Use wrappers for nullable fields
public class Person {
    private String name;      // Required
    private Integer age;      // Optional (null = unknown)
    private Boolean active;   // Optional (null = not set)
}

// Always use equals() for wrapper comparison
if (Objects.equals(wrapper1, wrapper2)) { } // Null-safe
if (wrapper1 != null && wrapper1.equals(wrapper2)) { } // Explicit null check

// Minimize autoboxing in loops
List<Integer> numbers = getNumbers();
long sum = 0; // Primitive accumulator
for (Integer num : numbers) {
    sum += num; // Only unboxing, no boxing
}
```

#### ❌ Common Mistakes
```java
// Using == for wrapper comparison
Integer a = 200, b = 200;
if (a == b) { } // May be false! Use equals()

// Unnecessary autoboxing in collections
List<Integer> list = new ArrayList<>();
for (int i = 0; i < 1000; i++) {
    list.add(i); // Boxing on every addition
}

// Mixed arithmetic creating unnecessary objects
Integer count = 0;
for (int i = 0; i < 1000; i++) {
    count++; // Unbox count, increment, box result - inefficient!
}
// Better: use primitive int for counting

// NullPointerException from autoboxing
Integer wrapper = null;
int primitive = wrapper; // NPE during unboxing!
```

### Interview Questions & Answers

**Q: What happens when you compare two Integer objects with ==?**
A: It depends on the values. For integers in the range -128 to 127, == returns true because they're cached. Outside this range, == compares references and returns false even for equal values. Always use equals() for wrapper comparison.

**Q: When should you use primitives vs wrapper types?**
A: Use primitives for:
- Performance-critical code
- Local variables and calculations  
- Array elements

Use wrappers for:
- Object fields that can be null
- Collections and generics
- When you need wrapper class methods
- API parameters that should accept null

**Q: What is autoboxing and what are its performance implications?**
A: Autoboxing automatically converts primitives to wrappers and vice versa. It can impact performance because:
- Each boxing creates a new object (unless cached)
- Frequent boxing/unboxing in loops creates garbage collection pressure
- Wrapper arithmetic involves unboxing → operation → boxing

---

## 7. String Manipulation with StringBuilder

### Core Concepts
Strings in Java are immutable. StringBuilder provides a mutable sequence of characters for efficient string construction and manipulation.

### String vs StringBuilder vs StringBuffer

| Class | Mutability | Thread Safety | Performance | Use Case |
|-------|------------|---------------|-------------|----------|
| **String** | Immutable | Thread-safe | Slow for concatenation | Fixed strings, small operations |
| **StringBuilder** | Mutable | Not thread-safe | Fast | Single-threaded string building |
| **StringBuffer** | Mutable | Thread-safe | Slower than StringBuilder | Multi-threaded string building |

### Why StringBuilder Matters

```java
// ❌ Inefficient: String concatenation in loop
public String buildStringBad(String[] words) {
    String result = "";
    for (String word : words) {
        result += word + " "; // Creates new String object each iteration!
    }
    return result;
}
// Time complexity: O(n²) due to copying
// Space: Creates n intermediate String objects

// ✅ Efficient: StringBuilder
public String buildStringGood(String[] words) {
    StringBuilder sb = new StringBuilder();
    for (String word : words) {
        sb.append(word).append(" "); // Modifies internal buffer
    }
    return sb.toString();
}
// Time complexity: O(n)
// Space: One StringBuilder with resizable buffer
```

### StringBuilder Constructors and Capacity

```java
// Default constructor (initial capacity 16)
StringBuilder sb1 = new StringBuilder();

// Constructor with initial capacity
StringBuilder sb2 = new StringBuilder(100); // Avoids resizing

// Constructor with initial string
StringBuilder sb3 = new StringBuilder("Hello");

// Constructor from CharSequence
StringBuilder sb4 = new StringBuilder(anotherStringBuilder);

// Capacity management
StringBuilder sb = new StringBuilder(10);
System.out.println(sb.capacity()); // 10
sb.append("This is a long string");
System.out.println(sb.capacity()); // Automatically increased (likely 22 or more)

// Manual capacity control
sb.ensureCapacity(100);   // Ensure at least 100 characters
sb.trimToSize();          // Minimize memory usage
```

### Core StringBuilder Methods

```java
StringBuilder sb = new StringBuilder();

// Append operations (method chaining)
sb.append("Hello")
  .append(" ")
  .append("World")
  .append('!')
  .append(42)
  .append(true);
// Result: "Hello World!42true"

// Insert at specific position
sb.insert(5, " Beautiful"); // "Hello Beautiful World!42true"

// Delete characters
sb.delete(5, 15);     // Remove " Beautiful" → "Hello World!42true"
sb.deleteCharAt(5);   // Remove space → "HelloWorld!42true"

// Replace substring
sb.replace(5, 10, "Java"); // "HelloJava!42true"

// Reverse the entire string
sb.reverse(); // "eurt24!avaJolleH"

// Set character at position
sb.setCharAt(0, 'E'); // "Eurt24!avaJolleH"

// Get substring without creating new StringBuilder
String sub = sb.substring(0, 4); // "Eurt"

// Length and capacity
int length = sb.length();     // Current string length
int capacity = sb.capacity(); // Current buffer capacity
```

### Practical Examples

```java
// Example 1: CSV generation
public String generateCSV(List<Person> people) {
    StringBuilder csv = new StringBuilder();
    
    // Header
    csv.append("Name,Age,Email\n");
    
    // Data rows
    for (Person person : people) {
        csv.append(person.getName())
           .append(",")
           .append(person.getAge())
           .append(",")
           .append(person.getEmail())
           .append("\n");
    }
    
    return csv.toString();
}

// Example 2: SQL query building
public class QueryBuilder {
    private StringBuilder query;
    
    public QueryBuilder() {
        this.query = new StringBuilder();
    }
    
    public QueryBuilder select(String... columns) {
        query.append("SELECT ");
        if (columns.length == 0) {
            query.append("*");
        } else {
            query.append(String.join(", ", columns));
        }
        return this;
    }
    
    public QueryBuilder from(String table) {
        query.append(" FROM ").append(table);
        return this;
    }
    
    public QueryBuilder where(String condition) {
        query.append(" WHERE ").append(condition);
        return this;
    }
    
    public QueryBuilder and(String condition) {
        query.append(" AND ").append(condition);
        return this;
    }
    
    public String build() {
        return query.toString();
    }
}

// Usage
String sql = new QueryBuilder()
    .select("name", "age")
    .from("users")
    .where("age > 18")
    .and("active = true")
    .build();
// Result: "SELECT name, age FROM users WHERE age > 18 AND active = true"

// Example 3: JSON-like string building
public String buildJsonObject(Map<String, Object> data) {
    StringBuilder json = new StringBuilder("{");
    
    boolean first = true;
    for (Map.Entry<String, Object> entry : data.entrySet()) {
        if (!first) {
            json.append(",");
        }
        
        json.append("\"")
            .append(entry.getKey())
            .append("\":")
            .append(formatValue(entry.getValue()));
        
        first = false;
    }
    
    json.append("}");
    return json.toString();
}

private String formatValue(Object value) {
    if (value instanceof String) {
        return "\"" + value + "\"";
    } else if (value == null) {
        return "null";
    } else {
        return value.toString();
    }
}

// Example 4: HTML generation
public class HtmlBuilder {
    private StringBuilder html;
    private int indentLevel = 0;
    
    public HtmlBuilder() {
        this.html = new StringBuilder();
    }
    
    public HtmlBuilder openTag(String tag) {
        addIndent();
        html.append("<").append(tag).append(">\n");
        indentLevel++;
        return this;
    }
    
    public HtmlBuilder closeTag(String tag) {
        indentLevel--;
        addIndent();
        html.append("</").append(tag).append(">\n");
        return this;
    }
    
    public HtmlBuilder addText(String text) {
        addIndent();
        html.append(text).append("\n");
        return this;
    }
    
    private void addIndent() {
        for (int i = 0; i < indentLevel; i++) {
            html.append("  ");
        }
    }
    
    public String build() {
        return html.toString();
    }
}
```

### Performance Optimization Techniques

```java
// Technique 1: Pre-size StringBuilder when possible
public String concatenateWords(List<String> words) {
    // Estimate capacity to avoid resizing
    int estimatedCapacity = words.size() * 10; // Rough estimate
    StringBuilder sb = new StringBuilder(estimatedCapacity);
    
    for (String word : words) {
        sb.append(word).append(" ");
    }
    
    // Remove trailing space
    if (sb.length() > 0) {
        sb.setLength(sb.length() - 1);
    }
    
    return sb.toString();
}

// Technique 2: Reuse StringBuilder instances
public class StringProcessor {
    private StringBuilder reusableBuilder = new StringBuilder();
    
    public String processText(String input) {
        // Clear previous content
        reusableBuilder.setLength(0);
        
        // Process input
        reusableBuilder.append("Processed: ").append(input);
        
        return reusableBuilder.toString();
    }
}

// Technique 3: Batch operations
public String createTable(String[][] data) {
    StringBuilder table = new StringBuilder();
    
    for (String[] row : data) {
        table.append("|");
        for (String cell : row) {
            table.append(" ").append(cell).append(" |");
        }
        table.append("\n");
    }
    
    return table.toString();
}
```

### Advanced StringBuilder Patterns

```java
// Pattern 1: Conditional building
public String buildMessage(User user, boolean includeDetails) {
    StringBuilder msg = new StringBuilder("Hello, ");
    msg.append(user.getName());
    
    if (includeDetails) {
        msg.append(" (")
           .append("ID: ").append(user.getId())
           .append(", Role: ").append(user.getRole())
           .append(")");
    }
    
    return msg.toString();
}

// Pattern 2: Delimiter handling
public String joinWithDelimiter(List<String> items, String delimiter) {
    if (items.isEmpty()) {
        return "";
    }
    
    StringBuilder result = new StringBuilder();
    result.append(items.get(0));
    
    for (int i = 1; i < items.size(); i++) {
        result.append(delimiter).append(items.get(i));
    }
    
    return result.toString();
}

// Pattern 3: Template-based building
public class TemplateBuilder {
    private StringBuilder template;
    
    public TemplateBuilder(String template) {
        this.template = new StringBuilder(template);
    }
    
    public TemplateBuilder replace(String placeholder, Object value) {
        String target = "{" + placeholder + "}";
        String replacement = String.valueOf(value);
        
        int index = template.indexOf(target);
        while (index != -1) {
            template.replace(index, index + target.length(), replacement);
            index = template.indexOf(target, index + replacement.length());
        }
        
        return this;
    }
    
    public String build() {
        return template.toString();
    }
}

// Usage
String email = new TemplateBuilder("Hello {name}, your order #{orderId} is {status}")
    .replace("name", "John")
    .replace("orderId", "12345")
    .replace("status", "shipped")
    .build();
// Result: "Hello John, your order #12345 is shipped"
```

### StringBuilder vs String.join() and Stream Collectors

```java
List<String> words = Arrays.asList("apple", "banana", "cherry");

// Method 1: StringBuilder (manual control)
StringBuilder sb = new StringBuilder();
for (int i = 0; i < words.size(); i++) {
    if (i > 0) sb.append(", ");
    sb.append(words.get(i));
}
String result1 = sb.toString();

// Method 2: String.join() (simpler for basic joining)
String result2 = String.join(", ", words);

// Method 3: Stream collectors (functional style)
String result3 = words.stream()
    .collect(Collectors.joining(", "));

// Method 4: Stream collectors with transformation
String result4 = words.stream()
    .map(String::toUpperCase)
    .collect(Collectors.joining(" | ", "[", "]"));
// Result: "[APPLE | BANANA | CHERRY]"

// When to use each:
// StringBuilder: Complex logic, conditional building, performance-critical
// String.join(): Simple joining with delimiter
// Stream collectors: Functional pipelines, transformations before joining
```

### Common Pitfalls and Best Practices

#### ❌ Common Mistakes

```java
// Mistake 1: Not pre-sizing when you know the approximate size
StringBuilder sb = new StringBuilder(); // Default capacity 16
for (int i = 0; i < 1000; i++) {
    sb.append("data"); // Will resize multiple times
}

// Better: Pre-size if you can estimate
StringBuilder sb2 = new StringBuilder(4000); // 1000 * 4 chars

// Mistake 2: Converting to String too early
public String processStrings(List<String> inputs) {
    StringBuilder sb = new StringBuilder();
    for (String input : inputs) {
        String processed = sb.append(input).toString(); // ❌ Creates string each time
        if (someCondition(processed)) {
            return processed;
        }
        sb.setLength(0); // Reset
    }
    return sb.toString();
}

// Mistake 3: Not handling null values
StringBuilder sb = new StringBuilder();
sb.append(null); // Appends "null" string, not null reference

// Mistake 4: Forgetting that StringBuilder is not thread-safe
// Don't share StringBuilder across threads without synchronization

// Mistake 5: Using StringBuilder for single concatenation
String result = new StringBuilder().append("Hello").append(" World").toString();
// Better: Direct concatenation for simple cases
String result2 = "Hello" + " World";
```

#### ✅ Best Practices

```java
// Practice 1: Estimate capacity to avoid resizing
public String buildLargeString(List<String> data) {
    int estimatedSize = data.size() * 50; // Rough estimate
    StringBuilder sb = new StringBuilder(estimatedSize);
    // ... build string
    return sb.toString();
}

// Practice 2: Use method chaining for readability
StringBuilder sb = new StringBuilder()
    .append("Name: ").append(name)
    .append(", Age: ").append(age)
    .append(", City: ").append(city);

// Practice 3: Handle edge cases
public String safeJoin(List<String> items, String delimiter) {
    if (items == null || items.isEmpty()) {
        return "";
    }
    
    StringBuilder result = new StringBuilder();
    boolean first = true;
    
    for (String item : items) {
        if (!first) {
            result.append(delimiter);
        }
        result.append(item != null ? item : ""); // Handle null items
        first = false;
    }
    
    return result.toString();
}

// Practice 4: Reuse StringBuilder for multiple operations
public class LogFormatter {
    private final StringBuilder buffer = new StringBuilder(256);
    
    public synchronized String formatLog(String level, String message, long timestamp) {
        buffer.setLength(0); // Clear previous content
        
        buffer.append("[")
              .append(timestamp)
              .append("] ")
              .append(level)
              .append(": ")
              .append(message);
        
        return buffer.toString();
    }
}

// Practice 5: Use StringBuilder for complex string building
public String generateReport(List<Transaction> transactions) {
    StringBuilder report = new StringBuilder(1024);
    
    report.append("Transaction Report\n")
          .append("==================\n\n");
    
    double total = 0.0;
    for (Transaction tx : transactions) {
        report.append("ID: ").append(tx.getId())
              .append(", Amount: $").append(String.format("%.2f", tx.getAmount()))
              .append(", Date: ").append(tx.getDate())
              .append("\n");
        total += tx.getAmount();
    }
    
    report.append("\n")
          .append("Total: $").append(String.format("%.2f", total));
    
    return report.toString();
}
```

### Memory and Performance Analysis

```java
// Memory usage comparison
public class StringBuilderPerformance {
    
    // Test string concatenation performance
    public static void comparePerformance(int iterations) {
        String[] words = {"Hello", "World", "Java", "Programming"};
        
        // String concatenation (slow)
        long start = System.currentTimeMillis();
        String result1 = "";
        for (int i = 0; i < iterations; i++) {
            for (String word : words) {
                result1 += word + " "; // Creates new String each time
            }
            result1 = ""; // Reset
        }
        long stringTime = System.currentTimeMillis() - start;
        
        // StringBuilder (fast)
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.setLength(0); // Reset
            for (String word : words) {
                sb.append(word).append(" ");
            }
            String result2 = sb.toString();
        }
        long sbTime = System.currentTimeMillis() - start;
        
        System.out.println("String concatenation: " + stringTime + "ms");
        System.out.println("StringBuilder: " + sbTime + "ms");
        System.out.println("StringBuilder is " + (stringTime / sbTime) + "x faster");
    }
}
```

### Real-World Use Cases

```java
// Use Case 1: Log message formatting
public class Logger {
    private final StringBuilder logBuffer = new StringBuilder(512);
    
    public String formatMessage(String level, String className, String method, 
                               String message, Object... params) {
        logBuffer.setLength(0);
        
        logBuffer.append(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                 .append(" [").append(level).append("] ")
                 .append(className).append(".").append(method).append("() - ");
        
        if (params.length > 0) {
            logBuffer.append(String.format(message, params));
        } else {
            logBuffer.append(message);
        }
        
        return logBuffer.toString();
    }
}

// Use Case 2: URL building
public class UrlBuilder {
    private final StringBuilder url;
    private boolean hasParams = false;
    
    public UrlBuilder(String baseUrl) {
        this.url = new StringBuilder(baseUrl);
    }
    
    public UrlBuilder addPath(String path) {
        if (!path.startsWith("/") && !url.toString().endsWith("/")) {
            url.append("/");
        }
        url.append(path);
        return this;
    }
    
    public UrlBuilder addParam(String key, Object value) {
        url.append(hasParams ? "&" : "?");
        url.append(key).append("=").append(String.valueOf(value));
        hasParams = true;
        return this;
    }
    
    public String build() {
        return url.toString();
    }
}

// Usage: https://api.example.com/users/123?active=true&limit=10
String apiUrl = new UrlBuilder("https://api.example.com")
    .addPath("users")
    .addPath("123")
    .addParam("active", true)
    .addParam("limit", 10)
    .build();

// Use Case 3: Configuration file generation
public class ConfigGenerator {
    
    public String generatePropertiesFile(Map<String, String> properties) {
        StringBuilder config = new StringBuilder();
        
        config.append("# Generated configuration file\n")
              .append("# Created: ").append(new Date()).append("\n\n");
        
        // Group properties by prefix
        Map<String, List<Map.Entry<String, String>>> grouped = properties.entrySet()
            .stream()
            .collect(Collectors.groupingBy(entry -> 
                entry.getKey().contains(".") ? 
                entry.getKey().substring(0, entry.getKey().indexOf(".")) : 
                "general"));
        
        for (Map.Entry<String, List<Map.Entry<String, String>>> group : grouped.entrySet()) {
            config.append("# ").append(group.getKey().toUpperCase()).append(" SETTINGS\n");
            
            for (Map.Entry<String, String> prop : group.getValue()) {
                config.append(prop.getKey())
                      .append("=")
                      .append(prop.getValue())
                      .append("\n");
            }
            
            config.append("\n");
        }
        
        return config.toString();
    }
}
```

### Interview Questions & Answers

**Q: When should you use StringBuilder instead of String concatenation?**

A: Use StringBuilder when:
- Concatenating in loops (StringBuilder is O(n), String concat is O(n²))
- Building strings with conditional logic
- Need to modify strings (insert, delete, replace operations)
- Performance is critical

Use String concatenation for:
- Simple, one-time concatenations
- Compile-time constant expressions (optimized by compiler)
- Small number of operations (< 5-10)

**Q: What's the difference between StringBuilder and StringBuffer?**

A: 
- **StringBuilder**: Not thread-safe, faster performance
- **StringBuffer**: Thread-safe (synchronized methods), slower performance

Use StringBuilder in single-threaded scenarios, StringBuffer only when multiple threads access the same instance.

**Q: How does StringBuilder manage memory internally?**

A: StringBuilder uses a resizable character array as internal storage. When the array becomes full:
1. Creates a new array (usually 2x + 2 the current size)
2. Copies existing content to the new array
3. Releases the old array

Pre-sizing with appropriate capacity avoids costly resize operations.

**Q: Can you show a performance comparison between String concatenation and StringBuilder?**

```java
// String concatenation (creates n objects)
String result = "";
for (int i = 0; i < 1000; i++) {
    result += "word" + i; // Creates new String object each iteration
}

// StringBuilder (modifies internal buffer)
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append("word").append(i); // Modifies existing buffer
}
String result = sb.toString();
```

For 1000 iterations, StringBuilder is typically 100-1000x faster than String concatenation.

---

## Summary and Quick Reference

### Key Takeaways

1. **Generics & Wildcards**: Use PECS (Producer Extends, Consumer Super) principle
2. **Exception Handling**: Catch specific exceptions, use try-with-resources for cleanup
3. **Lambda & Streams**: Prefer method references, avoid side effects in stream operations
4. **Collections**: Choose the right collection type for your use case and performance needs
5. **Comparators**: Use factory methods and chaining for complex sorting logic
6. **Primitives vs Wrappers**: Use primitives for performance, wrappers for nullability and collections
7. **StringBuilder**: Essential for efficient string manipulation, especially in loops

### Common Interview Topics Checklist

- [ ] Explain generic type erasure and its implications
- [ ] Demonstrate proper exception handling patterns
- [ ] Write lambda expressions and stream pipelines
- [ ] Choose appropriate collection types for different scenarios
- [ ] Implement custom comparators with multiple sorting criteria
- [ ] Explain autoboxing/unboxing performance implications
- [ ] Use StringBuilder for efficient string building

### Performance Quick Reference

| Operation | Time Complexity | Best Practice |
|-----------|----------------|---------------|
| ArrayList random access | O(1) | Use for frequent get/set |
| LinkedList insertion | O(1) at ends | Use for frequent insert/delete |
| HashMap operations | O(1) average | Use for fast key-value lookup |
| TreeMap operations | O(log n) | Use for sorted key-value pairs |
| String concatenation | O(n²) in loops | Use StringBuilder instead |
| StringBuilder append | O(1) amortized | Pre-size if possible |

This guide provides a solid foundation for Java development and interview preparation. Practice implementing these concepts in real projects to deepen your understanding!