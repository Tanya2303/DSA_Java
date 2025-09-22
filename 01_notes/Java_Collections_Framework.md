# Complete Java Collections Framework Guide

## Table of Contents
1. [Collections Framework Overview](#collections-framework-overview)
2. [ArrayList](#arraylist)
3. [LinkedList](#linkedlist)
4. [HashMap](#hashmap)
5. [HashSet](#hashset)
6. [TreeMap](#treemap)
7. [TreeSet](#treeset)
8. [Performance Comparison Cheat Sheet](#performance-comparison-cheat-sheet)
9. [Best Practices & Common Pitfalls](#best-practices--common-pitfalls)

---

## Collections Framework Overview

The Java Collections Framework provides a unified architecture for storing and manipulating groups of objects. It consists of:

- **Interfaces**: Abstract data types (List, Set, Map)
- **Implementations**: Concrete classes (ArrayList, HashMap, etc.)
- **Algorithms**: Static methods for sorting, searching, etc.

### Core Interfaces Hierarchy
```
Collection
├── List (ArrayList, LinkedList)
├── Set (HashSet, TreeSet)
└── Map (HashMap, TreeMap) - Not part of Collection interface
```

---

## ArrayList

### Purpose & Key Features
ArrayList is a **resizable array** implementation that provides:
- Dynamic sizing (grows/shrinks automatically)
- Fast random access by index
- Maintains insertion order
- Allows duplicates and null values

### Internal Working
- Uses an internal **Object array** to store elements
- Default initial capacity: 10
- Growth strategy: New capacity = (old capacity * 3/2) + 1
- Elements are stored contiguously in memory

```java
// Internal structure (simplified)
public class ArrayList<E> {
    private Object[] elementData;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
}
```

### Constructors & Key Methods

#### Constructors
```java
// Default constructor (capacity = 10)
ArrayList<String> list1 = new ArrayList<>();

// With initial capacity
ArrayList<String> list2 = new ArrayList<>(20);

// From another collection
ArrayList<String> list3 = new ArrayList<>(Arrays.asList("A", "B", "C"));
```

#### Essential Methods
```java
ArrayList<String> list = new ArrayList<>();

// Adding elements
list.add("Apple");           // Adds at end
list.add(0, "Banana");      // Adds at specific index
list.addAll(Arrays.asList("Cherry", "Date"));

// Accessing elements
String first = list.get(0);        // Get by index
int index = list.indexOf("Apple"); // Find index (-1 if not found)
boolean exists = list.contains("Banana");

// Modifying elements
list.set(1, "Blueberry");    // Replace at index
list.remove("Cherry");       // Remove by value
list.remove(0);              // Remove by index

// Size and capacity
int size = list.size();
boolean empty = list.isEmpty();
list.clear();                // Remove all elements

// Iteration
for (String item : list) {
    System.out.println(item);
}
```

### Time Complexity
| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| get(index) | O(1) | Direct array access |
| add(element) | O(1) amortized | May need to resize |
| add(index, element) | O(n) | Shifting required |
| remove(index) | O(n) | Shifting required |
| contains(element) | O(n) | Linear search |
| size() | O(1) | Stored as field |

### When to Use ArrayList
✅ **Use When:**
- Need fast random access by index
- Frequent read operations
- Memory efficiency is important
- Need to maintain insertion order

❌ **Avoid When:**
- Frequent insertions/deletions in middle
- Need thread safety (use Vector or Collections.synchronizedList())

### Real-World Use Cases
```java
// 1. Shopping cart items
ArrayList<Product> cart = new ArrayList<>();

// 2. Menu items in a restaurant app
ArrayList<MenuItem> menu = new ArrayList<>();

// 3. Student grades
ArrayList<Double> grades = new ArrayList<>();
```

### Common Interview Questions
1. **What happens when ArrayList capacity is exceeded?**
   - Creates a new array with 1.5x capacity
   - Copies all elements to new array
   - Old array becomes eligible for garbage collection

2. **ArrayList vs Array?**
   - ArrayList: Dynamic, more methods, only objects
   - Array: Fixed size, primitive support, slightly faster

---

## LinkedList

### Purpose & Key Features
LinkedList is a **doubly-linked list** implementation that provides:
- Dynamic sizing
- Efficient insertion/deletion at any position
- Implements both List and Deque interfaces
- No random access (must traverse)

### Internal Working
- Each element is stored in a **Node** containing data and references
- Each node has pointers to next and previous nodes
- Maintains references to first and last nodes

```java
// Internal structure (simplified)
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
    
    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```

### Constructors & Key Methods

#### Constructors
```java
// Default constructor
LinkedList<String> list1 = new LinkedList<>();

// From another collection
LinkedList<String> list2 = new LinkedList<>(Arrays.asList("A", "B", "C"));
```

#### Essential Methods
```java
LinkedList<String> list = new LinkedList<>();

// Adding elements
list.add("Apple");              // Add at end
list.addFirst("Banana");        // Add at beginning
list.addLast("Cherry");         // Add at end
list.add(1, "Date");           // Add at index

// Queue operations (Deque interface)
list.offer("Elderberry");       // Add at end (queue)
list.offerFirst("Fig");         // Add at beginning
list.offerLast("Grape");        // Add at end

// Accessing elements
String first = list.getFirst(); // Get first element
String last = list.getLast();   // Get last element
String byIndex = list.get(2);   // Get by index (slow!)

// Removing elements
String removed = list.removeFirst();    // Remove and return first
String removedLast = list.removeLast(); // Remove and return last
list.remove("Apple");                   // Remove by value
list.remove(0);                         // Remove by index

// Stack operations
list.push("Item");              // Add to front (stack)
String popped = list.pop();     // Remove from front (stack)
```

### Time Complexity
| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| get(index) | O(n) | Must traverse from head/tail |
| add(element) | O(1) | Add at end |
| add(index, element) | O(n) | Must traverse to index |
| addFirst/addLast | O(1) | Direct access to head/tail |
| remove(index) | O(n) | Must traverse to index |
| removeFirst/removeLast | O(1) | Direct access |

### ArrayList vs LinkedList Comparison

| Feature | ArrayList | LinkedList |
|---------|-----------|------------|
| **Random Access** | O(1) | O(n) |
| **Memory Overhead** | Lower | Higher (node objects) |
| **Insert/Delete at End** | O(1) amortized | O(1) |
| **Insert/Delete at Beginning** | O(n) | O(1) |
| **Insert/Delete in Middle** | O(n) | O(n) |
| **Memory Layout** | Contiguous | Scattered |
| **Cache Performance** | Better | Worse |

### When to Use LinkedList
✅ **Use When:**
- Frequent insertions/deletions at beginning or end
- Implementing stacks, queues, or deques
- Don't know the size in advance
- Need to implement custom iteration

❌ **Avoid When:**
- Need frequent random access
- Memory usage is critical
- Performance is critical (ArrayList usually faster)

### Real-World Use Cases
```java
// 1. Browser history (back/forward navigation)
LinkedList<String> browserHistory = new LinkedList<>();

// 2. Music playlist with previous/next functionality
LinkedList<Song> playlist = new LinkedList<>();

// 3. Undo/Redo operations
LinkedList<Action> undoStack = new LinkedList<>();
```

---

## HashMap

### Purpose & Key Features
HashMap is a **hash table** implementation that provides:
- Key-value pair storage
- Fast access, insertion, and deletion
- No ordering guarantee
- Allows one null key and multiple null values

### Internal Working
- Uses an array of **buckets** (default size: 16)
- Each bucket can hold multiple entries (collision handling)
- Uses **chaining** for collision resolution
- Load factor: 0.75 (resizes when 75% full)
- Uses **hash codes** to determine bucket location

```java
// Simplified internal structure
public class HashMap<K,V> {
    Node<K,V>[] table;        // Array of buckets
    int size;                 // Number of key-value pairs
    int threshold;            // Resize threshold
    final float loadFactor;   // Load factor (default 0.75)
    
    static class Node<K,V> {  // Bucket entry
        final int hash;
        final K key;
        V value;
        Node<K,V> next;       // For chaining
    }
}
```

### Constructors & Key Methods

#### Constructors
```java
// Default constructor (capacity=16, loadFactor=0.75)
HashMap<String, Integer> map1 = new HashMap<>();

// With initial capacity
HashMap<String, Integer> map2 = new HashMap<>(32);

// With capacity and load factor
HashMap<String, Integer> map3 = new HashMap<>(32, 0.8f);

// From another map
Map<String, Integer> existing = Map.of("A", 1, "B", 2);
HashMap<String, Integer> map4 = new HashMap<>(existing);
```

#### Essential Methods
```java
HashMap<String, Integer> map = new HashMap<>();

// Adding/updating entries
map.put("Apple", 5);           // Add or update
map.putIfAbsent("Banana", 3);  // Add only if key doesn't exist
map.putAll(Map.of("Cherry", 7, "Date", 2));

// Accessing values
Integer value = map.get("Apple");        // Get value (null if not found)
Integer defaultVal = map.getOrDefault("Orange", 0); // With default

// Checking existence
boolean hasKey = map.containsKey("Apple");
boolean hasValue = map.containsValue(5);

// Removing entries
Integer removed = map.remove("Apple");    // Remove and return value
map.remove("Banana", 3);                 // Remove only if key-value matches

// Size and state
int size = map.size();
boolean empty = map.isEmpty();
map.clear();

// Iteration
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}

// Java 8+ forEach
map.forEach((key, value) -> System.out.println(key + " = " + value));
```

### Advanced Methods
```java
// Compute methods (Java 8+)
map.compute("Apple", (key, val) -> val == null ? 1 : val + 1);
map.computeIfAbsent("Banana", key -> key.length());
map.computeIfPresent("Cherry", (key, val) -> val * 2);

// Merge method
map.merge("Date", 1, Integer::sum); // Add 1, or sum if exists

// Replace methods
map.replace("Apple", 5);                    // Replace if exists
map.replace("Apple", 5, 10);               // Replace if current value matches
map.replaceAll((key, value) -> value * 2); // Transform all values
```

### Time Complexity
| Operation | Average Case | Worst Case | Notes |
|-----------|-------------|------------|-------|
| get(key) | O(1) | O(n) | Worst case with many collisions |
| put(key, value) | O(1) | O(n) | May trigger resize |
| remove(key) | O(1) | O(n) | Hash collisions |
| containsKey | O(1) | O(n) | Same as get |

### Hash Collision Handling
When multiple keys hash to the same bucket:
1. **Java 7 and earlier**: Uses linked list (O(n) worst case)
2. **Java 8+**: Uses red-black tree when bucket has 8+ entries (O(log n))

### When to Use HashMap
✅ **Use When:**
- Need fast key-based lookups
- Don't care about ordering
- Want best general-purpose map implementation
- Need null key/value support

❌ **Avoid When:**
- Need sorted keys (use TreeMap)
- Need thread safety (use ConcurrentHashMap)
- Need insertion order (use LinkedHashMap)

### Real-World Use Cases
```java
// 1. User session data
HashMap<String, User> userSessions = new HashMap<>();

// 2. Configuration properties
HashMap<String, String> config = new HashMap<>();

// 3. Caching computed results
HashMap<String, ExpensiveResult> cache = new HashMap<>();

// 4. Counting word frequencies
HashMap<String, Integer> wordCount = new HashMap<>();
```

### Common Interview Questions
1. **How does HashMap handle collisions?**
   - Uses chaining (linked list/tree in same bucket)
   - Java 8+ converts to red-black tree when bucket size ≥ 8

2. **What happens if two objects have the same hashCode?**
   - They're placed in the same bucket
   - equals() method is used to distinguish them

3. **Why is HashMap not thread-safe?**
   - Multiple threads can corrupt internal structure
   - Can cause infinite loops in older versions
   - Use ConcurrentHashMap for thread safety

---

## HashSet

### Purpose & Key Features
HashSet is a **hash table-based Set** implementation that provides:
- No duplicate elements
- Fast operations (add, remove, contains)
- No ordering guarantee
- Allows one null value

### Internal Working
- **Internally uses HashMap** where elements are keys
- All values in internal HashMap are the same dummy object
- Inherits HashMap's performance characteristics

```java
// Simplified internal structure
public class HashSet<E> {
    private HashMap<E, Object> map;
    private static final Object PRESENT = new Object();
    
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }
}
```

### Constructors & Key Methods

#### Constructors
```java
// Default constructor
HashSet<String> set1 = new HashSet<>();

// With initial capacity
HashSet<String> set2 = new HashSet<>(32);

// With capacity and load factor
HashSet<String> set3 = new HashSet<>(32, 0.8f);

// From another collection
HashSet<String> set4 = new HashSet<>(Arrays.asList("A", "B", "C"));
```

#### Essential Methods
```java
HashSet<String> set = new HashSet<>();

// Adding elements
set.add("Apple");           // Returns true if added, false if already exists
set.addAll(Arrays.asList("Banana", "Cherry", "Apple")); // Duplicate ignored

// Checking existence
boolean contains = set.contains("Apple");
boolean empty = set.isEmpty();

// Removing elements
boolean removed = set.remove("Apple");    // Returns true if removed
set.removeAll(Arrays.asList("Banana", "Cherry")); // Remove multiple
set.retainAll(Arrays.asList("Date", "Fig"));      // Keep only these
set.clear();

// Size
int size = set.size();

// Set operations
HashSet<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C"));
HashSet<String> set2 = new HashSet<>(Arrays.asList("B", "C", "D"));

// Union (all elements from both sets)
HashSet<String> union = new HashSet<>(set1);
union.addAll(set2); // {A, B, C, D}

// Intersection (common elements)
HashSet<String> intersection = new HashSet<>(set1);
intersection.retainAll(set2); // {B, C}

// Difference (elements in set1 but not set2)
HashSet<String> difference = new HashSet<>(set1);
difference.removeAll(set2); // {A}

// Iteration
for (String element : set) {
    System.out.println(element);
}
```

### Time Complexity
| Operation | Average Case | Worst Case |
|-----------|-------------|------------|
| add(element) | O(1) | O(n) |
| remove(element) | O(1) | O(n) |
| contains(element) | O(1) | O(n) |
| size() | O(1) | O(1) |

### When to Use HashSet
✅ **Use When:**
- Need to ensure uniqueness
- Fast lookup/existence checking
- Don't care about element ordering
- Want best general-purpose Set implementation

❌ **Avoid When:**
- Need sorted elements (use TreeSet)
- Need insertion order (use LinkedHashSet)
- Elements don't have good hashCode implementation

### Real-World Use Cases
```java
// 1. Unique user IDs in a session
HashSet<String> activeUsers = new HashSet<>();

// 2. Unique tags for a blog post
HashSet<String> tags = new HashSet<>();

// 3. Visited pages tracking
HashSet<String> visitedPages = new HashSet<>();

// 4. Duplicate detection
HashSet<String> seenEmails = new HashSet<>();
```

---

## TreeMap

### Purpose & Key Features
TreeMap is a **Red-Black tree-based NavigableMap** implementation that provides:
- Sorted key-value pairs
- Maintains natural ordering or custom Comparator ordering
- No null keys (null values allowed)
- Guaranteed O(log n) performance

### Internal Working
- Uses **Red-Black Tree** (self-balancing binary search tree)
- Keys must be Comparable or provide custom Comparator
- Maintains sorted order at all times
- Each node colored red or black for balance

```java
// Simplified internal structure
public class TreeMap<K,V> {
    private final Comparator<? super K> comparator;
    private Entry<K,V> root;
    private int size = 0;
    
    static final class Entry<K,V> {
        K key;
        V value;
        Entry<K,V> left;
        Entry<K,V> right;
        Entry<K,V> parent;
        boolean color = BLACK;
    }
}
```

### Constructors & Key Methods

#### Constructors
```java
// Natural ordering (keys must implement Comparable)
TreeMap<String, Integer> map1 = new TreeMap<>();

// Custom comparator
TreeMap<String, Integer> map2 = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

// Reverse order
TreeMap<String, Integer> map3 = new TreeMap<>(Collections.reverseOrder());

// From another map
TreeMap<String, Integer> map4 = new TreeMap<>(existingMap);

// Custom comparator example
TreeMap<Person, String> personMap = new TreeMap<>((p1, p2) -> 
    Integer.compare(p1.getAge(), p2.getAge()));
```

#### Essential Methods
```java
TreeMap<String, Integer> map = new TreeMap<>();

// Basic operations
map.put("Banana", 2);
map.put("Apple", 5);
map.put("Cherry", 7);
map.put("Date", 1);

// Navigation methods (unique to TreeMap)
String firstKey = map.firstKey();        // "Apple" (smallest)
String lastKey = map.lastKey();          // "Date" (largest)

Map.Entry<String, Integer> firstEntry = map.firstEntry();
Map.Entry<String, Integer> lastEntry = map.lastEntry();

// Remove and return first/last
Map.Entry<String, Integer> polledFirst = map.pollFirstEntry();
Map.Entry<String, Integer> polledLast = map.pollLastEntry();

// Range operations
String lowerKey = map.lowerKey("Cherry");      // Largest key < "Cherry"
String floorKey = map.floorKey("Cherry");      // Largest key ≤ "Cherry"
String ceilingKey = map.ceilingKey("Cherry");  // Smallest key ≥ "Cherry"
String higherKey = map.higherKey("Cherry");    // Smallest key > "Cherry"

// SubMap views
SortedMap<String, Integer> subMap = map.subMap("Banana", "Date");
SortedMap<String, Integer> headMap = map.headMap("Cherry");  // Keys < "Cherry"
SortedMap<String, Integer> tailMap = map.tailMap("Banana");  // Keys ≥ "Banana"

// Descending views
NavigableMap<String, Integer> descendingMap = map.descendingMap();
NavigableSet<String> descendingKeys = map.descendingKeySet();
```

### Advanced Navigation Examples
```java
TreeMap<Integer, String> scores = new TreeMap<>();
scores.put(85, "Bob");
scores.put(92, "Alice");
scores.put(78, "Charlie");
scores.put(96, "David");

// Find students with scores in range [80, 95]
NavigableMap<Integer, String> range = scores.subMap(80, true, 95, true);

// Find next higher score than 85
Integer nextScore = scores.higherKey(85); // 92

// Get top 3 scores
scores.descendingMap().entrySet().stream()
      .limit(3)
      .forEach(System.out::println);
```

### Time Complexity
| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| get(key) | O(log n) | Tree traversal |
| put(key, value) | O(log n) | Insert and rebalance |
| remove(key) | O(log n) | Remove and rebalance |
| firstKey/lastKey | O(log n) | Find min/max |
| Navigation methods | O(log n) | Tree traversal |

### When to Use TreeMap
✅ **Use When:**
- Need sorted key-value pairs
- Need range operations (subMap, headMap, tailMap)
- Need navigation methods (firstKey, lastKey, etc.)
- Want predictable O(log n) performance

❌ **Avoid When:**
- Don't need sorting (HashMap is faster)
- Keys don't implement Comparable and no custom Comparator
- Need very frequent operations (HashMap is O(1) average)

### Real-World Use Cases
```java
// 1. Student grades sorted by score
TreeMap<Integer, List<String>> gradeBook = new TreeMap<>();

// 2. Event scheduling by timestamp
TreeMap<LocalDateTime, Event> schedule = new TreeMap<>();

// 3. Price levels in trading system
TreeMap<Double, Integer> bidPrices = new TreeMap<>(Collections.reverseOrder());

// 4. Directory listing (sorted file names)
TreeMap<String, File> directory = new TreeMap<>();
```

---

## TreeSet

### Purpose & Key Features
TreeSet is a **Red-Black tree-based NavigableSet** implementation that provides:
- Sorted unique elements
- No duplicates
- Natural ordering or custom Comparator
- No null elements

### Internal Working
- **Internally uses TreeMap** where elements are keys
- All values in internal TreeMap are the same dummy object
- Inherits TreeMap's Red-Black tree characteristics

```java
// Simplified internal structure
public class TreeSet<E> {
    private NavigableMap<E, Object> map;
    private static final Object PRESENT = new Object();
    
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }
}
```

### Constructors & Key Methods

#### Constructors
```java
// Natural ordering
TreeSet<String> set1 = new TreeSet<>();

// Custom comparator
TreeSet<String> set2 = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

// From another collection
TreeSet<Integer> set3 = new TreeSet<>(Arrays.asList(5, 2, 8, 1, 9));

// Custom comparator for objects
TreeSet<Person> people = new TreeSet<>((p1, p2) -> 
    p1.getName().compareTo(p2.getName()));
```

#### Essential Methods
```java
TreeSet<Integer> set = new TreeSet<>();
set.addAll(Arrays.asList(5, 2, 8, 1, 9, 3, 7));

// Basic operations
set.add(6);                    // Maintains sorted order
boolean contains = set.contains(5);
set.remove(2);

// Navigation methods
Integer first = set.first();   // Smallest element (1)
Integer last = set.last();     // Largest element (9)

// Remove and return first/last
Integer polledFirst = set.pollFirst();   // Remove and return 1
Integer polledLast = set.pollLast();     // Remove and return 9

// Range queries
Integer lower = set.lower(5);     // Largest element < 5 (3)
Integer floor = set.floor(5);     // Largest element ≤ 5 (5)
Integer ceiling = set.ceiling(5); // Smallest element ≥ 5 (5)
Integer higher = set.higher(5);   // Smallest element > 5 (6)

// Subset operations
SortedSet<Integer> subset = set.subSet(3, 8);      // [3, 8) - exclusive end
NavigableSet<Integer> subsetInc = set.subSet(3, true, 8, true); // [3, 8] - inclusive

SortedSet<Integer> headSet = set.headSet(5);       // Elements < 5
SortedSet<Integer> tailSet = set.tailSet(5);       // Elements ≥ 5

// Descending operations
NavigableSet<Integer> descending = set.descendingSet();
Iterator<Integer> descendingIter = set.descendingIterator();

// Iteration (always in sorted order)
for (Integer num : set) {
    System.out.println(num); // Prints in ascending order
}
```

### Advanced Examples
```java
// Example: Finding elements in ranges
TreeSet<Integer> scores = new TreeSet<>(Arrays.asList(65, 78, 85, 92, 88, 76));

// Students with scores between 75 and 90 (inclusive)
NavigableSet<Integer> midRange = scores.subSet(75, true, 90, true);
System.out.println(midRange); // [76, 78, 85, 88]

// Top 3 scores
scores.descendingSet().stream()
      .limit(3)
      .forEach(System.out::println); // 92, 88, 85

// Custom object sorting
TreeSet<Student> students = new TreeSet<>((s1, s2) -> {
    int gradeCompare = Double.compare(s2.getGrade(), s1.getGrade()); // Descending
    return gradeCompare != 0 ? gradeCompare : s1.getName().compareTo(s2.getName());
});
```

### Time Complexity
| Operation | Time Complexity | Notes |
|-----------|----------------|-------|
| add(element) | O(log n) | Insert and maintain order |
| remove(element) | O(log n) | Remove and rebalance |
| contains(element) | O(log n) | Tree search |
| first/last | O(log n) | Find min/max |
| Navigation methods | O(log n) | Tree traversal |

### HashSet vs TreeSet Comparison

| Feature | HashSet | TreeSet |
|---------|---------|---------|
| **Ordering** | No ordering | Sorted order |
| **Performance** | O(1) average | O(log n) |
| **Null values** | One null allowed | No nulls |
| **Requirements** | hashCode/equals | Comparable/Comparator |
| **Memory** | Lower overhead | Higher (tree structure) |
| **Use case** | Fast lookup | Sorted unique elements |

### When to Use TreeSet
✅ **Use When:**
- Need sorted unique elements
- Need range operations on sets
- Need navigation methods (first, last, etc.)
- Want predictable performance

❌ **Avoid When:**
- Don't need sorting (HashSet is faster)
- Elements don't implement Comparable
- Very frequent add/remove operations

### Real-World Use Cases
```java
// 1. Maintaining sorted unique user IDs
TreeSet<String> sortedUserIds = new TreeSet<>();

// 2. Priority levels (sorted unique priorities)
TreeSet<Integer> priorities = new TreeSet<>(Collections.reverseOrder());

// 3. Sorted unique tags
TreeSet<String> tags = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);

// 4. Time slots (sorted unique time periods)
TreeSet<LocalTime> availableSlots = new TreeSet<>();
```

---

## Performance Comparison Cheat Sheet

### Time Complexity Summary

| Collection | Access | Search | Insert | Delete | Notes |
|------------|--------|---------|---------|---------|--------|
| **ArrayList** | O(1) | O(n) | O(1)* | O(n) | *Amortized, O(n) at index |
| **LinkedList** | O(n) | O(n) | O(1) | O(1)** | **At known position |
| **HashMap** | O(1)* | O(1)* | O(1)* | O(1)* | *Average case |
| **HashSet** | N/A | O(1)* | O(1)* | O(1)* | *Average case |
| **TreeMap** | O(log n) | O(log n) | O(log n) | O(log n) | Sorted |
| **TreeSet** | N/A | O(log n) | O(log n) | O(log n) | Sorted |

### Space Complexity

| Collection | Space Complexity | Memory Overhead |
|------------|------------------|-----------------|
| **ArrayList** | O(n) | Low (contiguous array) |
| **LinkedList** | O(n) | High (node objects + pointers) |
| **HashMap** | O(n) | Medium (array + chaining) |
| **HashSet** | O(n) | Medium (uses HashMap) |
| **TreeMap** | O(n) | Medium (tree nodes) |
| **TreeSet** | O(n) | Medium (uses TreeMap) |

### Quick Decision Guide

```
Need fast random access? → ArrayList
Need frequent insertions at beginning/end? → LinkedList
Need fast key-value lookups? → HashMap
Need unique elements with fast operations? → HashSet
Need sorted key-value pairs? → TreeMap
Need sorted unique elements? → TreeSet
```

---

## Best Practices & Common Pitfalls

### 1. Choosing the Right Collection

#### ArrayList vs LinkedList
```java
// ✅ Good: ArrayList for frequent random access
List<String> menuItems = new ArrayList<>();
String item = menuItems.get(5); // Fast O(1)

// ❌ Bad: LinkedList for frequent random access  
List<String> menuItems = new LinkedList<>();
String item = menuItems.get(5); // Slow O(n)

// ✅ Good: LinkedList for frequent insertions at beginning
LinkedList<String> recentActions = new LinkedList<>();
recentActions.addFirst(newAction); // Fast O(1)

// ❌ Bad: ArrayList for frequent insertions at beginning
ArrayList<String> recentActions = new ArrayList<>();
recentActions.add(0, newAction); // Slow O(n)
```

#### HashMap vs TreeMap
```java
// ✅ Good: HashMap for fast lookups without ordering
Map<String, User> userCache = new HashMap<>(); // O(1) access

// ✅ Good: TreeMap when you need sorted keys
Map<LocalDate, Sales> dailySales = new TreeMap<>(); // Automatic date sorting
```

### 2. Common Pitfalls and How to Avoid Them

#### Pitfall 1: Modifying Collections During Iteration
```java
// ❌ Bad: ConcurrentModificationException
List<String> items = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
for (String item : items) {
    if (item.equals("B")) {
        items.remove(item); // Throws ConcurrentModificationException
    }
}

// ✅ Good: Use Iterator.remove()
List<String> items = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
Iterator<String> iterator = items.iterator();
while (iterator.hasNext()) {
    String item = iterator.next();
    if (item.equals("B")) {
        iterator.remove(); // Safe removal
    }
}

// ✅ Good: Use removeIf() (Java 8+)
items.removeIf(item -> item.equals("B"));

// ✅ Good: Collect items to remove, then remove them
List<String> toRemove = items.stream()
    .filter(item -> item.equals("B"))
    .collect(Collectors.toList());
items.removeAll(toRemove);
```

#### Pitfall 2: Using Objects as Keys Without Proper hashCode/equals
```java
// ❌ Bad: Custom class without proper hashCode/equals
class BadKey {
    private String name;
    private int id;
    // Missing hashCode() and equals() implementations
}

Map<BadKey, String> map = new HashMap<>();
BadKey key1 = new BadKey("John", 1);
BadKey key2 = new BadKey("John", 1);
map.put(key1, "Value");
String value = map.get(key2); // Returns null! Different object references

// ✅ Good: Proper hashCode and equals implementation
class GoodKey {
    private String name;
    private int id;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        GoodKey goodKey = (GoodKey) obj;
        return id == goodKey.id && Objects.equals(name, goodKey.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
```

#### Pitfall 3: Assuming Ordering in HashMap/HashSet
```java
// ❌ Bad: Expecting order in HashMap
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.put("B", 2);
map.put("C", 3);
// Order is NOT guaranteed - could be C, A, B or any other order

// ✅ Good: Use LinkedHashMap for insertion order
Map<String, Integer> orderedMap = new LinkedHashMap<>();
orderedMap.put("A", 1);
orderedMap.put("B", 2);
orderedMap.put("C", 3);
// Maintains insertion order: A, B, C

// ✅ Good: Use TreeMap for sorted order
Map<String, Integer> sortedMap = new TreeMap<>();
sortedMap.put("C", 3);
sortedMap.put("A", 1);
sortedMap.put("B", 2);
// Always sorted: A, B, C
```

#### Pitfall 4: Null Handling Issues
```java
// ❌ Bad: Adding null to TreeSet/TreeMap
TreeSet<String> treeSet = new TreeSet<>();
treeSet.add(null); // Throws NullPointerException

TreeMap<String, Integer> treeMap = new TreeMap<>();
treeMap.put(null, 1); // Throws NullPointerException

// ✅ Good: Check for nulls or use collections that allow them
HashSet<String> hashSet = new HashSet<>();
hashSet.add(null); // OK - one null allowed

HashMap<String, Integer> hashMap = new HashMap<>();
hashMap.put(null, 1); // OK - one null key allowed
```

#### Pitfall 5: Performance Issues with Large Collections
```java
// ❌ Bad: Using LinkedList for large datasets with random access
List<String> largeList = new LinkedList<>();
// ... add 100,000 items
String item = largeList.get(50000); // Very slow O(n)

// ✅ Good: Use ArrayList for random access
List<String> largeList = new ArrayList<>();
// ... add 100,000 items  
String item = largeList.get(50000); // Fast O(1)

// ❌ Bad: Using ArrayList for frequent insertions at beginning
List<String> frequentInserts = new ArrayList<>();
for (int i = 0; i < 10000; i++) {
    frequentInserts.add(0, "Item " + i); // O(n) each time
}

// ✅ Good: Use LinkedList or ArrayDeque for frequent head insertions
Deque<String> frequentInserts = new ArrayDeque<>();
for (int i = 0; i < 10000; i++) {
    frequentInserts.addFirst("Item " + i); // O(1) each time
}
```

### 3. Advanced Tips and Best Practices

#### Tip 1: Use Diamond Operator and Var (Java 7+, 10+)
```java
// Old way (verbose)
HashMap<String, List<Integer>> oldMap = new HashMap<String, List<Integer>>();

// ✅ Better: Diamond operator (Java 7+)
HashMap<String, List<Integer>> newMap = new HashMap<>();

// ✅ Even better: var keyword (Java 10+)
var map = new HashMap<String, List<Integer>>();
```

#### Tip 2: Initialize Collections with Known Size
```java
// ❌ Bad: Default capacity might cause resizing
List<String> list = new ArrayList<>(); // Default capacity: 10
for (int i = 0; i < 1000; i++) {
    list.add("Item " + i); // Multiple resizing operations
}

// ✅ Good: Pre-size for better performance
List<String> list = new ArrayList<>(1000); // No resizing needed
for (int i = 0; i < 1000; i++) {
    list.add("Item " + i);
}
```

#### Tip 3: Use Factory Methods (Java 9+)
```java
// ✅ Immutable collections (Java 9+)
List<String> immutableList = List.of("A", "B", "C");
Set<String> immutableSet = Set.of("A", "B", "C");
Map<String, Integer> immutableMap = Map.of("A", 1, "B", 2, "C", 3);

// Note: These are immutable - trying to modify throws UnsupportedOperationException
```

#### Tip 4: Efficient Iteration Patterns
```java
Map<String, Integer> map = new HashMap<>();

// ✅ Best: Enhanced for loop for entry set
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    Integer value = entry.getValue();
    // Process key-value pair
}

// ✅ Good: Java 8+ forEach
map.forEach((key, value) -> {
    // Process key-value pair
});

// ❌ Inefficient: Iterating keys and getting values
for (String key : map.keySet()) {
    Integer value = map.get(key); // Extra lookup for each key
}
```

#### Tip 5: Custom Comparators for TreeMap/TreeSet
```java
// ✅ Complex sorting with multiple criteria
TreeSet<Student> students = new TreeSet<>((s1, s2) -> {
    // Primary: Grade (descending)
    int gradeCompare = Double.compare(s2.getGrade(), s1.getGrade());
    if (gradeCompare != 0) return gradeCompare;
    
    // Secondary: Name (ascending)
    int nameCompare = s1.getName().compareTo(s2.getName());
    if (nameCompare != 0) return nameCompare;
    
    // Tertiary: ID (ascending) - for complete ordering
    return Integer.compare(s1.getId(), s2.getId());
});

// ✅ Using Comparator utility methods (Java 8+)
TreeSet<Student> students = new TreeSet<>(
    Comparator.comparing(Student::getGrade).reversed()
              .thenComparing(Student::getName)
              .thenComparing(Student::getId)
);
```

#### Tip 6: Thread Safety Considerations
```java
// ❌ Bad: Using non-thread-safe collections in multithreaded environment
Map<String, Integer> unsafeMap = new HashMap<>(); // Not thread-safe

// ✅ Option 1: Synchronized wrapper
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

// ✅ Option 2: Concurrent collections (better performance)
Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();

// ✅ Option 3: Copy-on-write for read-heavy scenarios
List<String> cowList = new CopyOnWriteArrayList<>();
```

### 4. Performance Optimization Tips

#### Memory Optimization
```java
// ✅ Use primitive collections libraries for better memory efficiency
// TIntArrayList from Trove4j instead of ArrayList<Integer>
// Avoids boxing/unboxing overhead

// ✅ Set initial capacity appropriately
Map<String, String> map = new HashMap<>(expectedSize * 4 / 3 + 1);

// ✅ Use StringBuilder for string concatenation in loops
StringBuilder sb = new StringBuilder();
for (String item : items) {
    sb.append(item).append(", ");
}
```

#### Algorithm Optimization
```java
// ✅ Use appropriate collection for your use case
// Need unique elements + fast lookup? → HashSet
// Need unique elements + sorting? → TreeSet  
// Need key-value + fast lookup? → HashMap
// Need key-value + sorting? → TreeMap

// ✅ Batch operations when possible
List<String> itemsToRemove = Arrays.asList("A", "B", "C");
list.removeAll(itemsToRemove); // Better than individual removes
```

### 5. Testing and Debugging Tips

#### Testing Collection Behavior
```java
@Test
public void testHashMapBehavior() {
    Map<String, Integer> map = new HashMap<>();
    
    // Test null handling
    assertNull(map.put("key", 1));  // Returns null for new key
    assertEquals(Integer.valueOf(1), map.put("key", 2)); // Returns old value
    
    // Test size and isEmpty
    assertEquals(1, map.size());
    assertFalse(map.isEmpty());
    
    // Test containsKey vs containsValue
    assertTrue(map.containsKey("key"));
    assertTrue(map.containsValue(2));
    assertFalse(map.containsValue(1)); // Old value is gone
}
```

#### Common Debugging Scenarios
```java
// Debug: Why is my HashMap not working as expected?
// 1. Check if key class has proper equals() and hashCode()
// 2. Check for null keys/values
// 3. Verify you're using the same key object or equivalent keys

// Debug: Why is my TreeSet not sorting properly?
// 1. Ensure elements implement Comparable or provide Comparator
// 2. Check if comparator is consistent with equals
// 3. Verify no null elements

// Debug: Why am I getting ConcurrentModificationException?
// 1. Check if you're modifying collection during iteration
// 2. Use Iterator.remove() or collect-then-modify pattern
// 3. Consider thread safety issues
```

### 6. Interview Tips and Common Questions

#### Must-Know Concepts
1. **HashMap internals**: Hash function, collision handling, load factor
2. **ArrayList vs LinkedList**: When to use each, performance characteristics
3. **TreeMap/TreeSet**: Red-black tree properties, ordering requirements
4. **Iteration safety**: ConcurrentModificationException and solutions
5. **equals() and hashCode() contract**: Why both must be overridden together

#### Sample Interview Questions & Answers

**Q: What happens internally when you put an element in HashMap?**
A: 
1. Calculate hash code of the key
2. Apply hash function to determine bucket index
3. If bucket is empty, create new entry
4. If bucket has elements, check for existing key using equals()
5. If key exists, replace value; if not, add to chain
6. If load factor exceeds threshold, resize and rehash

**Q: Why is String a good key for HashMap?**
A: Strings are immutable, have well-distributed hash codes, and implement equals() and hashCode() properly. Immutability ensures the hash code won't change after insertion.

**Q: How would you make HashMap thread-safe?**
A: Three options:
1. `Collections.synchronizedMap()` - synchronizes all methods
2. `ConcurrentHashMap` - better performance with segment-based locking
3. External synchronization using locks

### 7. Java 8+ Stream Integration

```java
List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

// Filter and collect to different collection types
List<String> longWords = words.stream()
    .filter(w -> w.length() > 5)
    .collect(Collectors.toList());

Set<String> uniqueWords = words.stream()
    .collect(Collectors.toSet());

Map<Integer, List<String>> wordsByLength = words.stream()
    .collect(Collectors.groupingBy(String::length));

// TreeSet with custom comparator
TreeSet<String> sortedWords = words.stream()
    .collect(Collectors.toCollection(() -> 
        new TreeSet<>(String.CASE_INSENSITIVE_ORDER)));
```

---

## Conclusion

The Java Collections Framework provides powerful, efficient data structures for most programming needs. Key takeaways:

1. **Choose the right collection** based on your specific use case
2. **Understand performance characteristics** - know when operations are O(1), O(log n), or O(n)
3. **Be aware of common pitfalls** like concurrent modification and improper key implementations
4. **Use modern Java features** like diamond operator, var, and stream operations
5. **Consider thread safety** requirements for concurrent applications

Remember: The best collection depends on your specific requirements for ordering, uniqueness, performance, and access patterns. When in doubt, start with ArrayList for lists, HashMap for maps, and HashSet for sets, then optimize based on profiling and specific needs.

### Quick Reference Card

```
LIST IMPLEMENTATIONS:
ArrayList    → Fast random access, dynamic array
LinkedList   → Fast insertion/deletion, implements Deque

SET IMPLEMENTATIONS:  
HashSet      → Fast operations, no ordering
LinkedHashSet→ Insertion order + fast operations  
TreeSet      → Sorted order, O(log n) operations

MAP IMPLEMENTATIONS:
HashMap      → Fast operations, no ordering
LinkedHashMap→ Insertion/access order + fast operations
TreeMap      → Sorted keys, O(log n) operations
```