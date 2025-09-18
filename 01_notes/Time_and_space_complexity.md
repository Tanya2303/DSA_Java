# Complete Guide to Time and Space Complexity Analysis in Java

## Table of Contents
1. [What is Complexity Analysis?](#what-is-complexity-analysis)
2. [Big O, Omega, and Theta Notation](#big-o-omega-and-theta-notation)
3. [Time Complexity Fundamentals](#time-complexity-fundamentals)
4. [Space Complexity Fundamentals](#space-complexity-fundamentals)
5. [Common Complexity Classes](#common-complexity-classes)
6. [Analyzing Loops and Iterations](#analyzing-loops-and-iterations)
7. [Recursion and Master Theorem](#recursion-and-master-theorem)
8. [Java Collections Complexity](#java-collections-complexity)
9. [Practical Examples and Analysis](#practical-examples-and-analysis)
10. [Optimization Techniques](#optimization-techniques)
11. [Interview Tips and Common Pitfalls](#interview-tips-and-common-pitfalls)

---

## What is Complexity Analysis?

**Complexity analysis** is the process of determining how the runtime (time complexity) and memory usage (space complexity) of an algorithm scale as the input size grows.

### Why It Matters:
- **Performance Prediction**: Understand how your code will perform with large datasets
- **Resource Planning**: Estimate memory and CPU requirements
- **Algorithm Comparison**: Choose the best solution among alternatives
- **Scalability**: Ensure your solution works for production-scale data
- **Interview Success**: Essential for technical interviews

### Key Concepts:
- **Input Size (n)**: The size of the input data (array length, string length, etc.)
- **Growth Rate**: How execution time/memory grows relative to input size
- **Worst-Case Analysis**: Most common focus in interviews

---

## Big O, Omega, and Theta Notation

### Big O Notation (O) - Upper Bound
Describes the **worst-case** or **maximum** time/space an algorithm will take.

```java
// O(n) - Linear time example
public int findMax(int[] arr) {
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {  // Visits each element once
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    return max;  // Takes at most n operations
}
```

### Big Omega Notation (Ω) - Lower Bound
Describes the **best-case** or **minimum** time an algorithm will take.

```java
// Ω(1) best case, O(n) worst case
public boolean contains(int[] arr, int target) {
    for (int num : arr) {
        if (num == target) {
            return true;  // Best case: found immediately (Ω(1))
        }
    }
    return false;  // Worst case: check all elements (O(n))
}
```

### Big Theta Notation (Θ) - Tight Bound
Describes the **exact** growth rate when best and worst cases are the same.

```java
// Θ(n) - Always visits every element exactly once
public int sum(int[] arr) {
    int total = 0;
    for (int num : arr) {  // Always n iterations
        total += num;
    }
    return total;
}
```

---

## Time Complexity Fundamentals

### Definition
Time complexity measures how the number of operations grows with input size.

### Rules for Calculation:

1. **Drop Constants**: O(2n) → O(n)
2. **Drop Lower-Order Terms**: O(n² + n) → O(n²)
3. **Different Inputs = Different Variables**: O(a + b) not O(n)
4. **Nested Operations = Multiplication**: O(n × m)

### Step-by-Step Analysis Process:

```java
public void example(int[] arr) {
    // Step 1: Count each operation
    int n = arr.length;           // O(1) - constant
    
    for (int i = 0; i < n; i++) { // O(n) - loop runs n times
        System.out.println(arr[i]); // O(1) - constant operation
    }
    
    for (int i = 0; i < n; i++) { // O(n) - outer loop
        for (int j = 0; j < n; j++) { // O(n) - inner loop
            if (arr[i] == arr[j]) {   // O(1) - comparison
                System.out.println("Match found");
            }
        }
    }
    
    // Step 2: Sum up: O(1) + O(n) + O(n²) = O(n²)
    // Step 3: Drop lower terms: O(n²)
}
```

---

## Space Complexity Fundamentals

### Definition
Space complexity measures the maximum memory used by an algorithm relative to input size.

### Components:
1. **Input Space**: Memory for input data (often excluded from analysis)
2. **Auxiliary Space**: Extra memory used by algorithm
3. **Output Space**: Memory for output (sometimes included)

### Examples:

```java
// O(1) Space - Constant extra memory
public int findMax(int[] arr) {
    int max = arr[0];  // O(1) extra space
    for (int i = 1; i < arr.length; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    return max;
}

// O(n) Space - Linear extra memory
public int[] createCopy(int[] arr) {
    int[] copy = new int[arr.length];  // O(n) extra space
    for (int i = 0; i < arr.length; i++) {
        copy[i] = arr[i];
    }
    return copy;
}

// O(log n) Space - Logarithmic (recursive call stack)
public int binarySearch(int[] arr, int target, int left, int right) {
    if (left > right) return -1;
    
    int mid = left + (right - left) / 2;
    if (arr[mid] == target) return mid;
    
    if (arr[mid] > target) {
        return binarySearch(arr, target, left, mid - 1);  // log n recursive calls
    } else {
        return binarySearch(arr, target, mid + 1, right);
    }
}
```

---

## Common Complexity Classes

| Complexity | Name | Description | Example |
|------------|------|-------------|---------|
| O(1) | Constant | Same time regardless of input size | Array access, HashMap get |
| O(log n) | Logarithmic | Halves problem size each step | Binary search |
| O(n) | Linear | Proportional to input size | Array traversal |
| O(n log n) | Linearithmic | Common in efficient sorting | Merge sort, Heap sort |
| O(n²) | Quadratic | Nested loops over input | Bubble sort, nested array processing |
| O(n³) | Cubic | Triple nested loops | Matrix multiplication |
| O(2ⁿ) | Exponential | Doubles with each input increase | Fibonacci (naive), subset generation |
| O(n!) | Factorial | Permutations | Traveling salesman (brute force) |

### Visual Growth Comparison:
```
Input Size (n) | O(1) | O(log n) | O(n) | O(n log n) | O(n²) | O(2ⁿ)
1              | 1    | 1        | 1    | 1          | 1     | 2
10             | 1    | 3        | 10   | 33         | 100   | 1,024
100            | 1    | 7        | 100  | 664        | 10K   | 1.3×10³⁰
1,000          | 1    | 10       | 1K   | 9,966      | 1M    | Too large
```

---

## Analyzing Loops and Iterations

### Simple Loops

```java
// O(n) Time, O(1) Space
public void printArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {  // n iterations
        System.out.println(arr[i]);  // O(1) operation
    }
    // Total: O(n)
}
```

### Nested Loops

```java
// O(n²) Time, O(1) Space
public void printPairs(int[] arr) {
    for (int i = 0; i < arr.length; i++) {      // n iterations
        for (int j = 0; j < arr.length; j++) {  // n iterations each
            System.out.println(arr[i] + ", " + arr[j]);  // O(1)
        }
    }
    // Total: n × n × O(1) = O(n²)
}

// O(n²) Time - Triangle pattern
public void printUniquePairs(int[] arr) {
    for (int i = 0; i < arr.length; i++) {          // n iterations
        for (int j = i + 1; j < arr.length; j++) {  // decreasing iterations: (n-1) + (n-2) + ... + 1
            System.out.println(arr[i] + ", " + arr[j]);
        }
    }
    // Total: n(n-1)/2 = O(n²)
}
```

### Variable Loop Bounds

```java
// O(n log n) Time
public void example(int n) {
    for (int i = 1; i < n; i++) {        // n iterations
        for (int j = 1; j < n; j *= 2) { // log n iterations (doubling)
            System.out.println(i + ", " + j);
        }
    }
    // Total: n × log n = O(n log n)
}

// O(log n) Time
public void binaryPattern(int n) {
    for (int i = n; i > 1; i /= 2) {  // Halving each time: log n iterations
        System.out.println(i);
    }
    // Total: O(log n)
}
```

---

## Recursion and Master Theorem

### Basic Recursion Analysis

```java
// O(2ⁿ) Time, O(n) Space (call stack depth)
public int fibonacci(int n) {
    if (n <= 1) return n;  // Base case: O(1)
    return fibonacci(n-1) + fibonacci(n-2);  // Two recursive calls
}
// Each call makes 2 more calls, creating binary tree of height n
// Total calls: 2⁰ + 2¹ + 2² + ... + 2ⁿ ≈ 2ⁿ

// O(n) Time, O(n) Space - Optimized with memoization
public int fibonacciMemo(int n, int[] memo) {
    if (n <= 1) return n;
    if (memo[n] != 0) return memo[n];  // Already computed
    memo[n] = fibonacciMemo(n-1, memo) + fibonacciMemo(n-2, memo);
    return memo[n];
}
```

### Master Theorem

For recurrences of the form: **T(n) = aT(n/b) + f(n)**

Where:
- **a** = number of subproblems
- **b** = factor by which input is reduced
- **f(n)** = work done outside recursive calls

**Rules:**
1. If f(n) = O(n^c) and c < log_b(a), then T(n) = O(n^(log_b(a)))
2. If f(n) = O(n^c) and c = log_b(a), then T(n) = O(n^c log n)
3. If f(n) = O(n^c) and c > log_b(a), then T(n) = O(f(n))

### Common Examples:

```java
// Binary Search: T(n) = T(n/2) + O(1)
// a=1, b=2, f(n)=O(1), c=0
// log_2(1) = 0, so c = log_b(a) → O(log n)

// Merge Sort: T(n) = 2T(n/2) + O(n)
// a=2, b=2, f(n)=O(n), c=1
// log_2(2) = 1, so c = log_b(a) → O(n log n)

// Recursive matrix multiplication: T(n) = 8T(n/2) + O(n²)
// a=8, b=2, f(n)=O(n²), c=2
// log_2(8) = 3, so c < log_b(a) → O(n³)
```

---

## Java Collections Complexity

### ArrayList
| Operation | Time Complexity | Space | Notes |
|-----------|----------------|-------|-------|
| get(i) | O(1) | O(1) | Direct array access |
| set(i, val) | O(1) | O(1) | Direct assignment |
| add(val) | O(1) amortized | O(1) | May trigger resize O(n) |
| add(i, val) | O(n) | O(1) | Shift elements right |
| remove(i) | O(n) | O(1) | Shift elements left |
| contains(val) | O(n) | O(1) | Linear search |

```java
// ArrayList Example Analysis
List<Integer> list = new ArrayList<>();

// O(1) amortized - usually constant, occasionally O(n) for resize
list.add(42);

// O(n) - worst case shift all elements
list.add(0, 99);

// O(n) - linear search through list
boolean found = list.contains(42);
```

### LinkedList
| Operation | Time Complexity | Space | Notes |
|-----------|----------------|-------|-------|
| get(i) | O(n) | O(1) | Must traverse from head |
| set(i, val) | O(n) | O(1) | Must traverse to position |
| addFirst/Last | O(1) | O(1) | Direct head/tail access |
| add(i, val) | O(n) | O(1) | Traverse + insert |
| removeFirst/Last | O(1) | O(1) | Direct head/tail access |
| remove(i) | O(n) | O(1) | Traverse + remove |

### HashMap
| Operation | Average | Worst Case | Space | Notes |
|-----------|---------|------------|-------|-------|
| put(k, v) | O(1) | O(n) | O(1) | Hash collisions |
| get(k) | O(1) | O(n) | O(1) | Hash collisions |
| remove(k) | O(1) | O(n) | O(1) | Hash collisions |
| containsKey(k) | O(1) | O(n) | O(1) | Hash collisions |

### TreeMap/TreeSet
| Operation | Time Complexity | Space | Notes |
|-----------|----------------|-------|-------|
| put/add | O(log n) | O(1) | Balanced BST |
| get/contains | O(log n) | O(1) | Tree traversal |
| remove | O(log n) | O(1) | Tree rebalancing |
| firstKey/lastKey | O(log n) | O(1) | Tree traversal |

---

## Practical Examples and Analysis

### Example 1: Two Sum Problem

```java
// Brute Force: O(n²) Time, O(1) Space
public int[] twoSumBrute(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {        // O(n)
        for (int j = i + 1; j < nums.length; j++) { // O(n)
            if (nums[i] + nums[j] == target) {      // O(1)
                return new int[]{i, j};
            }
        }
    }
    return new int[]{};
}

// Optimized: O(n) Time, O(n) Space
public int[] twoSumOptimized(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    
    for (int i = 0; i < nums.length; i++) {  // O(n) loop
        int complement = target - nums[i];    // O(1)
        
        if (map.containsKey(complement)) {    // O(1) average
            return new int[]{map.get(complement), i};
        }
        
        map.put(nums[i], i);                  // O(1) average
    }
    return new int[]{};
}
```

### Example 2: String Palindrome Check

```java
// O(n) Time, O(1) Space - Two pointers
public boolean isPalindrome(String s) {
    int left = 0, right = s.length() - 1;
    
    while (left < right) {  // O(n/2) = O(n)
        if (s.charAt(left) != s.charAt(right)) {  // O(1)
            return false;
        }
        left++;
        right--;
    }
    return true;
}

// O(n) Time, O(n) Space - String reversal
public boolean isPalindromeReverse(String s) {
    String reversed = new StringBuilder(s).reverse().toString();  // O(n) time and space
    return s.equals(reversed);  // O(n) comparison
}
```

### Example 3: Finding Duplicates

```java
// O(n²) Time, O(1) Space - Nested loops
public boolean containsDuplicatesBrute(int[] nums) {
    for (int i = 0; i < nums.length; i++) {        // O(n)
        for (int j = i + 1; j < nums.length; j++) { // O(n)
            if (nums[i] == nums[j]) {               // O(1)
                return true;
            }
        }
    }
    return false;
}

// O(n) Time, O(n) Space - HashSet
public boolean containsDuplicatesSet(int[] nums) {
    Set<Integer> seen = new HashSet<>();
    
    for (int num : nums) {      // O(n)
        if (seen.contains(num)) {  // O(1) average
            return true;
        }
        seen.add(num);          // O(1) average
    }
    return false;
}

// O(n log n) Time, O(1) Space - Sorting
public boolean containsDuplicatesSort(int[] nums) {
    Arrays.sort(nums);  // O(n log n)
    
    for (int i = 1; i < nums.length; i++) {  // O(n)
        if (nums[i] == nums[i-1]) {          // O(1)
            return true;
        }
    }
    return false;
}
```

---

## Optimization Techniques

### Time vs Space Tradeoffs

#### Caching/Memoization
```java
// Without memoization: O(2ⁿ) time, O(n) space
public int fibonacciSlow(int n) {
    if (n <= 1) return n;
    return fibonacciSlow(n-1) + fibonacciSlow(n-2);
}

// With memoization: O(n) time, O(n) space
Map<Integer, Integer> cache = new HashMap<>();
public int fibonacciFast(int n) {
    if (n <= 1) return n;
    if (cache.containsKey(n)) return cache.get(n);
    
    int result = fibonacciFast(n-1) + fibonacciFast(n-2);
    cache.put(n, result);
    return result;
}
```

#### Space-Optimized Dynamic Programming
```java
// O(n) space version
public int fibonacciArray(int n) {
    if (n <= 1) return n;
    
    int[] dp = new int[n + 1];
    dp[0] = 0;
    dp[1] = 1;
    
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i-1] + dp[i-2];
    }
    return dp[n];
}

// O(1) space version
public int fibonacciOptimal(int n) {
    if (n <= 1) return n;
    
    int prev2 = 0, prev1 = 1;
    
    for (int i = 2; i <= n; i++) {
        int current = prev1 + prev2;
        prev2 = prev1;
        prev1 = current;
    }
    return prev1;
}
```

### Common Optimization Patterns

#### 1. Two Pointers Technique
```java
// Instead of nested loops O(n²), use two pointers O(n)
public int[] twoSumSorted(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    
    while (left < right) {
        int sum = nums[left] + nums[right];
        if (sum == target) {
            return new int[]{left, right};
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }
    return new int[]{};
}
```

#### 2. Sliding Window
```java
// Maximum sum of k consecutive elements
// Instead of O(n×k), use sliding window O(n)
public int maxSum(int[] arr, int k) {
    int windowSum = 0, maxSum = 0;
    
    // Calculate first window
    for (int i = 0; i < k; i++) {
        windowSum += arr[i];
    }
    maxSum = windowSum;
    
    // Slide the window
    for (int i = k; i < arr.length; i++) {
        windowSum = windowSum - arr[i-k] + arr[i];
        maxSum = Math.max(maxSum, windowSum);
    }
    
    return maxSum;
}
```

---

## Interview Tips and Common Pitfalls

### Step-by-Step Interview Approach

1. **Understand the Problem**
   - Ask clarifying questions
   - Identify input/output
   - Consider edge cases

2. **Start with Brute Force**
   - Explain the simplest solution first
   - Analyze its complexity
   - Code it if time permits

3. **Optimize Gradually**
   - Identify bottlenecks
   - Consider different data structures
   - Apply optimization patterns

4. **Analyze Final Solution**
   - State time and space complexity
   - Explain tradeoffs made
   - Discuss edge cases

### Common Mistakes to Avoid

#### Mistake 1: Forgetting about Space Complexity
```java
// This looks like O(1) space, but recursion uses O(n) stack space!
public int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);  // O(n) space due to call stack
}
```

#### Mistake 2: Confusing Average and Worst Case
```java
// HashMap operations are O(1) average, O(n) worst case
Map<String, Integer> map = new HashMap<>();
map.put(key, value);  // Usually O(1), but O(n) with many collisions
```

#### Mistake 3: Not Considering Input Size Variables
```java
// This is O(a + b), not O(n)
public void process(int[] arr1, int[] arr2) {
    for (int x : arr1) { /* process */ }  // O(a)
    for (int y : arr2) { /* process */ }  // O(b)
}
```

### Quick Reference for Interviews

#### When to Use Each Approach:
- **HashMap**: Fast lookups, counting, caching
- **Two Pointers**: Sorted arrays, palindromes, pair problems
- **Sliding Window**: Subarray problems, consecutive elements
- **Binary Search**: Sorted data, search space reduction
- **DFS/BFS**: Tree/graph problems, pathfinding
- **Dynamic Programming**: Optimal substructure, overlapping subproblems

#### Complexity Quick Checks:
- **Single loop through n elements**: O(n)
- **Nested loops over same data**: O(n²)
- **Dividing problem in half repeatedly**: O(log n)
- **Processing all subsets**: O(2ⁿ)
- **Processing all permutations**: O(n!)

#### Space Complexity Reminders:
- **Recursive calls**: O(depth of recursion)
- **Extra arrays/lists**: O(size of structure)
- **HashMap/HashSet**: O(number of elements stored)
- **In-place algorithms**: O(1) additional space

---

## Practice Problems by Complexity

### O(1) - Constant
- Array element access
- HashMap get/put
- Stack push/pop
- Math calculations

### O(log n) - Logarithmic
- Binary search
- Binary tree operations (balanced)
- Heap insert/delete

### O(n) - Linear
- Array traversal
- Linear search
- Tree traversal (DFS/BFS)

### O(n log n) - Linearithmic
- Merge sort
- Heap sort
- Binary search on each element

### O(n²) - Quadratic
- Bubble sort
- Selection sort
- Two nested loops

### Remember
- **Practice identifying patterns** in different types of problems
- **Always state your assumptions** about input size and constraints
- **Consider both time and space** complexity in your analysis
- **Start simple, then optimize** - don't jump to the most complex solution immediately
- **Explain your thinking process** clearly during interviews

This guide provides a solid foundation for understanding and analyzing algorithm complexity. Practice with real problems and always think about how your solutions will scale with larger inputs!