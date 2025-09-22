private double cosineSimilarity(int[] vectorA, int[] vectorB) {
            double dotProduct = 0, normA = 0, normB = 0;
            
            for (int i = 0; i < vectorA.length; i++) {
                dotProduct += vectorA[i] * vectorB[i];
                normA += vectorA[i] * vectorA[i];
                normB += vectorB[i] * vectorB[i];
            }
            
            return (normA == 0 || normB == 0) ? 0 : dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
        }
    }
    
    // Question: Implement inventory optimization
    public static class InventoryOptimizer {
        public static int[] optimizeStock(int[] demand, int[] costs, int[] capacity) {
            int[] optimalStock = new int[demand.length];
            
            for (int i = 0; i < demand.length; i++) {
                // Simple optimization: minimize cost while meeting expected demand
                int expectedDemand = demand[i];
                int maxCapacity = capacity[i];
                int unitCost = costs[i];
                
                // Consider holding cost vs stockout cost
                double serviceLevel = 0.95; // 95% service level target
                optimalStock[i] = Math.min(
                    (int) (expectedDemand * serviceLevel), 
                    maxCapacity
                );
            }
            
            return optimalStock;
        }
        
        // ABC analysis for inventory classification
        public static char[] classifyItems(double[] values, double[] volumes) {
            int n = values.length;
            char[] classification = new char[n];
            
            // Calculate total value
            double totalValue = Arrays.stream(values).sum();
            
            // Create indices sorted by value descending
            Integer[] indices = new Integer[n];
            for (int i = 0; i < n; i++) indices[i] = i;
            Arrays.sort(indices, (a, b) -> Double.compare(values[b], values[a]));
            
            double cumulativeValue = 0;
            for (int i = 0; i < n; i++) {
                int idx = indices[i];
                cumulativeValue += values[idx];
                double percentage = cumulativeValue / totalValue;
                
                if (percentage <= 0.8) {
                    classification[idx] = 'A'; // High value items
                } else if (percentage <= 0.95) {
                    classification[idx] = 'B'; // Medium value items
                } else {
                    classification[idx] = 'C'; // Low value items
                }
            }
            
            return classification;
        }
    }
}

### 3. Gaming and Graphics
```java
public class GamingQuestions {
    
    // Question: Implement game board representation and operations
    public static class GameBoard {
        private final int[][] board;
        private final int rows, cols;
        
        public GameBoard(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.board = new int[rows][cols];
        }
        
        // Check if a move creates a winning condition (like Connect 4)
        public boolean checkWin(int row, int col, int player) {
            int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
            
            for (int[] dir : directions) {
                int count = 1; // Count the current piece
                
                // Check in positive direction
                count += countInDirection(row, col, dir[0], dir[1], player);
                
                // Check in negative direction
                count += countInDirection(row, col, -dir[0], -dir[1], player);
                
                if (count >= 4) return true;
            }
            
            return false;
        }
        
        private int countInDirection(int row, int col, int deltaRow, int deltaCol, int player) {
            int count = 0;
            int r = row + deltaRow;
            int c = col + deltaCol;
            
            while (r >= 0 && r < rows && c >= 0 && c < cols && board[r][c] == player) {
                count++;
                r += deltaRow;
                c += deltaCol;
            }
            
            return count;
        }
        
        // Flood fill algorithm for games like Minesweeper
        public void floodFill(int startRow, int startCol, int newValue) {
            int originalValue = board[startRow][startCol];
            if (originalValue == newValue) return;
            
            dfsFloodFill(startRow, startCol, originalValue, newValue);
        }
        
        private void dfsFloodFill(int row, int col, int originalValue, int newValue) {
            if (row < 0 || row >= rows || col < 0 || col >= cols || 
                board[row][col] != originalValue) {
                return;
            }
            
            board[row][col] = newValue;
            
            // Fill in 4 directions
            dfsFloodFill(row + 1, col, originalValue, newValue);
            dfsFloodFill(row - 1, col, originalValue, newValue);
            dfsFloodFill(row, col + 1, originalValue, newValue);
            dfsFloodFill(row, col - 1, originalValue, newValue);
        }
    }
    
    // Question: Implement A* pathfinding algorithm
    public static class AStarPathfinding {
        private static class Node implements Comparable<Node> {
            int x, y;
            int gCost, hCost;
            Node parent;
            
            Node(int x, int y) {
                this.x = x;
                this.y = y;
            }
            
            int getFCost() {
                return gCost + hCost;
            }
            
            @Override
            public int compareTo(Node other) {
                return Integer.compare(this.getFCost(), other.getFCost());
            }
        }
        
        public static List<int[]> findPath(int[][] grid, int[] start, int[] goal) {
            int rows = grid.length;
            int cols = grid[0].length;
            
            PriorityQueue<Node> openSet = new PriorityQueue<>();
            boolean[][] closedSet = new boolean[rows][cols];
            
            Node startNode = new Node(start[0], start[1]);
            startNode.gCost = 0;
            startNode.hCost = manhattanDistance(start, goal);
            
            openSet.offer(startNode);
            
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            
            while (!openSet.isEmpty()) {
                Node current = openSet.poll();
                
                if (current.x == goal[0] && current.y == goal[1]) {
                    return reconstructPath(current);
                }
                
                closedSet[current.x][current.y] = true;
                
                for (int[] dir : directions) {
                    int newX = current.x + dir[0];
                    int newY = current.y + dir[1];
                    
                    if (newX < 0 || newX >= rows || newY < 0 || newY >= cols ||
                        grid[newX][newY] == 1 || closedSet[newX][newY]) {
                        continue;
                    }
                    
                    Node neighbor = new Node(newX, newY);
                    neighbor.gCost = current.gCost + 1;
                    neighbor.hCost = manhattanDistance(new int[]{newX, newY}, goal);
                    neighbor.parent = current;
                    
                    openSet.offer(neighbor);
                }
            }
            
            return new ArrayList<>(); // No path found
        }
        
        private static int manhattanDistance(int[] a, int[] b) {
            return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
        }
        
        private static List<int[]> reconstructPath(Node goal) {
            List<int[]> path = new ArrayList<>();
            Node current = goal;
            
            while (current != null) {
                path.add(0, new int[]{current.x, current.y});
                current = current.parent;
            }
            
            return path;
        }
    }
}
```

---

## Machine Learning Applications with Arrays

### 1. Linear Regression Implementation
```java
public class MachineLearningArrays {
    
    public static class LinearRegression {
        private double[] weights;
        private double bias;
        private final double learningRate;
        
        public LinearRegression(int numFeatures, double learningRate) {
            this.weights = new double[numFeatures];
            this.bias = 0.0;
            this.learningRate = learningRate;
            
            // Initialize weights randomly
            Random random = new Random();
            for (int i = 0; i < numFeatures; i++) {
                weights[i] = random.nextGaussian() * 0.1;
            }
        }
        
        public double predict(double[] features) {
            double prediction = bias;
            for (int i = 0; i < features.length; i++) {
                prediction += weights[i] * features[i];
            }
            return prediction;
        }
        
        public void train(double[][] X, double[] y, int epochs) {
            int m = X.length; // number of samples
            
            for (int epoch = 0; epoch < epochs; epoch++) {
                double totalLoss = 0.0;
                
                // Calculate gradients
                double[] weightGradients = new double[weights.length];
                double biasGradient = 0.0;
                
                for (int i = 0; i < m; i++) {
                    double prediction = predict(X[i]);
                    double error = prediction - y[i];
                    totalLoss += error * error;
                    
                    // Accumulate gradients
                    biasGradient += error;
                    for (int j = 0; j < weights.length; j++) {
                        weightGradients[j] += error * X[i][j];
                    }
                }
                
                // Update parameters
                bias -= learningRate * biasGradient / m;
                for (int j = 0; j < weights.length; j++) {
                    weights[j] -= learningRate * weightGradients[j] / m;
                }
                
                if (epoch % 100 == 0) {
                    System.out.printf("Epoch %d, Loss: %.4f%n", epoch, totalLoss / (2 * m));
                }
            }
        }
        
        public double[] getWeights() {
            return weights.clone();
        }
        
        public double getBias() {
            return bias;
        }
    }
    
    // K-Means Clustering
    public static class KMeansClustering {
        private double[][] centroids;
        private final int k;
        private final int maxIterations;
        
        public KMeansClustering(int k, int maxIterations) {
            this.k = k;
            this.maxIterations = maxIterations;
        }
        
        public int[] fit(double[][] data) {
            int n = data.length;
            int features = data[0].length;
            
            // Initialize centroids randomly
            centroids = new double[k][features];
            Random random = new Random();
            
            for (int i = 0; i < k; i++) {
                int randomIndex = random.nextInt(n);
                System.arraycopy(data[randomIndex], 0, centroids[i], 0, features);
            }
            
            int[] assignments = new int[n];
            
            for (int iter = 0; iter < maxIterations; iter++) {
                boolean changed = false;
                
                // Assign points to nearest centroids
                for (int i = 0; i < n; i++) {
                    int nearestCentroid = findNearestCentroid(data[i]);
                    if (assignments[i] != nearestCentroid) {
                        assignments[i] = nearestCentroid;
                        changed = true;
                    }
                }
                
                if (!changed) break;
                
                // Update centroids
                updateCentroids(data, assignments);
            }
            
            return assignments;
        }
        
        private int findNearestCentroid(double[] point) {
            int nearest = 0;
            double minDistance = euclideanDistance(point, centroids[0]);
            
            for (int i = 1; i < k; i++) {
                double distance = euclideanDistance(point, centroids[i]);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = i;
                }
            }
            
            return nearest;
        }
        
        private void updateCentroids(double[][] data, int[] assignments) {
            int features = data[0].length;
            int[] counts = new int[k];
            
            // Reset centroids
            for (int i = 0; i < k; i++) {
                Arrays.fill(centroids[i], 0.0);
            }
            
            // Sum points assigned to each centroid
            for (int i = 0; i < data.length; i++) {
                int cluster = assignments[i];
                counts[cluster]++;
                
                for (int j = 0; j < features; j++) {
                    centroids[cluster][j] += data[i][j];
                }
            }
            
            // Average to get new centroids
            for (int i = 0; i < k; i++) {
                if (counts[i] > 0) {
                    for (int j = 0; j < features; j++) {
                        centroids[i][j] /= counts[i];
                    }
                }
            }
        }
        
        private double euclideanDistance(double[] a, double[] b) {
            double sum = 0.0;
            for (int i = 0; i < a.length; i++) {
                double diff = a[i] - b[i];
                sum += diff * diff;
            }
            return Math.sqrt(sum);
        }
    }
    
    // Neural Network Layer
    public static class DenseLayer {
        private double[][] weights;
        private double[] biases;
        private final int inputSize;
        private final int outputSize;
        
        public DenseLayer(int inputSize, int outputSize) {
            this.inputSize = inputSize;
            this.outputSize = outputSize;
            this.weights = new double[outputSize][inputSize];
            this.biases = new double[outputSize];
            
            // Xavier initialization
            Random random = new Random();
            double scale = Math.sqrt(2.0 / inputSize);
            
            for (int i = 0; i < outputSize; i++) {
                biases[i] = 0.0;
                for (int j = 0; j < inputSize; j++) {
                    weights[i][j] = random.nextGaussian() * scale;
                }
            }
        }
        
        public double[] forward(double[] input) {
            double[] output = new double[outputSize];
            
            for (int i = 0; i < outputSize; i++) {
                output[i] = biases[i];
                for (int j = 0; j < inputSize; j++) {
                    output[i] += weights[i][j] * input[j];
                }
                output[i] = relu(output[i]); // Apply ReLU activation
            }
            
            return output;
        }
        
        private double relu(double x) {
            return Math.max(0, x);
        }
        
        public void updateWeights(double[][] weightGradients, double[] biasGradients, double learningRate) {
            for (int i = 0; i < outputSize; i++) {
                biases[i] -= learningRate * biasGradients[i];
                for (int j = 0; j < inputSize; j++) {
                    weights[i][j] -= learningRate * weightGradients[i][j];
                }
            }
        }
    }
}
```

### 2. Computer Vision with Arrays
```java
public class ComputerVision {
    
    // Convolution operation for CNNs
    public static double[][] convolution2D(double[][] image, double[][] kernel) {
        int imageHeight = image.length;
        int imageWidth = image[0].length;
        int kernelHeight = kernel.length;
        int kernelWidth = kernel[0].length;
        
        int outputHeight = imageHeight - kernelHeight + 1;
        int outputWidth = imageWidth - kernelWidth + 1;
        
        double[][] output = new double[outputHeight][outputWidth];
        
        for (int i = 0; i < outputHeight; i++) {
            for (int j = 0; j < outputWidth; j++) {
                double sum = 0.0;
                
                for (int ki = 0; ki < kernelHeight; ki++) {
                    for (int kj = 0; kj < kernelWidth; kj++) {
                        sum += image[i + ki][j + kj] * kernel[ki][kj];
                    }
                }
                
                output[i][j] = sum;
            }
        }
        
        return output;
    }
    
    // Max pooling operation
    public static double[][] maxPooling(double[][] input, int poolSize, int stride) {
        int inputHeight = input.length;
        int inputWidth = input[0].length;
        
        int outputHeight = (inputHeight - poolSize) / stride + 1;
        int outputWidth = (inputWidth - poolSize) / stride + 1;
        
        double[][] output = new double[outputHeight][outputWidth];
        
        for (int i = 0; i < outputHeight; i++) {
            for (int j = 0; j < outputWidth; j++) {
                double max = Double.NEGATIVE_INFINITY;
                
                for (int pi = 0; pi < poolSize; pi++) {
                    for (int pj = 0; pj < poolSize; pj++) {
                        int inputI = i * stride + pi;
                        int inputJ = j * stride + pj;
                        max = Math.max(max, input[inputI][inputJ]);
                    }
                }
                
                output[i][j] = max;
            }
        }
        
        return output;
    }
    
    // Histogram equalization for image enhancement
    public static int[][] histogramEqualization(int[][] image) {
        int height = image.length;
        int width = image[0].length;
        int totalPixels = height * width;
        
        // Calculate histogram
        int[] histogram = new int[256];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                histogram[image[i][j]]++;
            }
        }
        
        // Calculate cumulative distribution function
        int[] cdf = new int[256];
        cdf[0] = histogram[0];
        for (int i = 1; i < 256; i++) {
            cdf[i] = cdf[i - 1] + histogram[i];
        }
        
        // Create lookup table
        int[] lookupTable = new int[256];
        for (int i = 0; i < 256; i++) {
            lookupTable[i] = (int) Math.round(255.0 * cdf[i] / totalPixels);
        }
        
        // Apply equalization
        int[][] equalizedImage = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                equalizedImage[i][j] = lookupTable[image[i][j]];
            }
        }
        
        return equalizedImage;
    }
}
```

---

## Performance Optimization and Profiling

### 1. Advanced Performance Monitoring
```java
public class PerformanceProfiler {
    
    public static class ArrayPerformanceMetrics {
        private long totalOperations;
        private long totalTime;
        private long minTime = Long.MAX_VALUE;
        private long maxTime = Long.MIN_VALUE;
        private List<Long> operationTimes;
        
        public ArrayPerformanceMetrics() {
            this.operationTimes = new ArrayList<>();
        }
        
        public void recordOperation(long timeNanos) {
            totalOperations++;
            totalTime += timeNanos;
            minTime = Math.min(minTime, timeNanos);
            maxTime = Math.max(maxTime, timeNanos);
            operationTimes.add(timeNanos);
        }
        
        public double getAverageTime() {
            return totalOperations == 0 ? 0 : (double) totalTime / totalOperations;
        }
        
        public double getMedianTime() {
            if (operationTimes.isEmpty()) return 0;
            
            List<Long> sorted = new ArrayList<>(operationTimes);
            Collections.sort(sorted);
            
            int size = sorted.size();
            if (size % 2 == 0) {
                return (sorted.get(size / 2 - 1) + sorted.get(size / 2)) / 2.0;
            } else {
                return sorted.get(size / 2);
            }
        }
        
        public double getPercentile(double percentile) {
            if (operationTimes.isEmpty()) return 0;
            
            List<Long> sorted = new ArrayList<>(operationTimes);
            Collections.sort(sorted);
            
            int index = (int) Math.ceil(percentile / 100.0 * sorted.size()) - 1;
            return sorted.get(Math.max(0, index));
        }
        
        public void printReport() {
            System.out.println("=== Array Performance Report ===");
            System.out.printf("Total Operations: %d%n", totalOperations);
            System.out.printf("Average Time: %.2f ns%n", getAverageTime());
            System.out.printf("Median Time: %.2f ns%n", getMedianTime());
            System.out.printf("Min Time: %d ns%n", minTime);
            System.out.printf("Max Time: %d ns%n", maxTime);
            System.out.printf("95th Percentile: %.2f ns%n", getPercentile(95));
            System.out.printf("99th Percentile: %.2f ns%n", getPercentile(99));
        }
    }
    
    // Benchmark different array access patterns
    public static void benchmarkAccessPatterns() {
        int size = 1000000;
        int[] array = new int[size];
        Random random = new Random(42);
        
        // Fill array with random data
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        
        ArrayPerformanceMetrics sequentialMetrics = new ArrayPerformanceMetrics();
        ArrayPerformanceMetrics randomMetrics = new ArrayPerformanceMetrics();
        
        // Sequential access
        for (int trial = 0; trial < 1000; trial++) {
            long startTime = System.nanoTime();
            
            for (int i = 0; i < size; i++) {
                int value = array[i]; // Sequential access
            }
            
            long endTime = System.nanoTime();
            sequentialMetrics.recordOperation(endTime - startTime);
        }
        
        // Random access
        int[] randomIndices = random.ints(size, 0, size).toArray();
        
        for (int trial = 0; trial < 1000; trial++) {
            long startTime = System.nanoTime();
            
            for (int i = 0; i < size; i++) {
                int value = array[randomIndices[i]]; // Random access
            }
            
            long endTime = System.nanoTime();
            randomMetrics.recordOperation(endTime - startTime);
        }
        
        System.out.println("Sequential Access:");
        sequentialMetrics.printReport();
        
        System.out.println("\nRandom Access:");
        randomMetrics.printReport();
    }
}
```

### 2. Memory Usage Optimization
```java
public class MemoryOptimization {
    
    // Bit array for memory-efficient boolean storage
    public static class BitArray {
        private final int[] bits;
        private final int size;
        
        public BitArray(int size) {
            this.size = size;
            this.bits = new int[(size + 31) / 32]; // Ceiling division
        }
        
        public void set(int index, boolean value) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            
            int arrayIndex = index / 32;
            int bitIndex = index % 32;
            
            if (value) {
                bits[arrayIndex] |= (1 << bitIndex);
            } else {
                bits[arrayIndex] &= ~(1 << bitIndex);
            }
        }
        
        public boolean get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            
            int arrayIndex = index / 32;
            int bitIndex = index % 32;
            
            return (bits[arrayIndex] & (1 << bitIndex)) != 0;
        }
        
        public int getMemoryUsage() {
            return bits.length * 4; // 4 bytes per int
        }
    }
    
    // Compressed sparse row matrix
    public static class CompressedSparseMatrix {
        private final double[] values;
        private final int[] columnIndices;
        private final int[] rowPointers;
        private final int rows, cols;
        
        public CompressedSparseMatrix(double[][] denseMatrix) {
            this.rows = denseMatrix.length;
            this.cols = denseMatrix[0].length;
            
            List<Double> valuesList = new ArrayList<>();
            List<Integer> columnsList = new ArrayList<>();
            List<Integer> rowPointersList = new ArrayList<>();
            
            rowPointersList.add(0);
            
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (denseMatrix[i][j] != 0) {
                        valuesList.add(denseMatrix[i][j]);
                        columnsList.add(j);
                    }
                }
                rowPointersList.add(valuesList.size());
            }
            
            this.values = valuesList.stream().mapToDouble(Double::doubleValue).toArray();
            this.columnIndices = columnsList.stream().mapToInt(Integer::intValue).toArray();
            this.rowPointers = rowPointersList.stream().mapToInt(Integer::intValue).toArray();
        }
        
        public double get(int row, int col) {
            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                throw new IndexOutOfBoundsException();
            }
            
            int start = rowPointers[row];
            int end = rowPointers[row + 1];
            
            for (int i = start; i < end; i++) {
                if (columnIndices[i] == col) {
                    return values[i];
                }
            }
            
            return 0.0; // Not found, assume zero
        }
        
        public double[] multiplyVector(double[] vector) {
            if (vector.length != cols) {
                throw new IllegalArgumentException("Vector size doesn't match matrix columns");
            }
            
            double[] result = new double[rows];
            
            for (int i = 0; i < rows; i++) {
                double sum = 0.0;
                int start = rowPointers[i];
                int end = rowPointers[i + 1];
                
                for (int j = start; j < end; j++) {
                    sum += values[j] * vector[columnIndices[j]];
                }
                
                result[i] = sum;
            }
            
            return result;
        }
        
        public int getMemoryUsage() {
            return values.length * 8 + // 8 bytes per double
                   columnIndices.length * 4 + // 4 bytes per int
                   rowPointers.length * 4; // 4 bytes per int
        }
    }
    
    // Object pool for array reuse
    public static class ArrayPool<T> {
        private final Queue<T[]> pool;
        private final int maxSize;
        private final IntFunction<T[]> arrayCreator;
        
        public ArrayPool(int maxPoolSize, IntFunction<T[]> arrayCreator) {
            this.pool = new ConcurrentLinkedQueue<>();
            this.maxSize = maxPoolSize;
            this.arrayCreator = arrayCreator;
        }
        
        public T[] acquire(int size) {
            T[] array = pool.poll();
            if (array == null || array.length < size) {
                return arrayCreator.apply(size);
            }
            
            // Clear the array before reuse
            Arrays.fill(array, null);
            return array;
        }
        
        public void release(T[] array) {
            if (pool.size() < maxSize) {
                pool.offer(array);
            }
        }
    }
}
```

---

## Final Comprehensive Assessment

### Master-Level Challenge Problems

```java
public class MasterLevelChallenges {
    
    // Challenge 1: Implement a self-balancing array-based data structure
    public static class AdaptiveArray<T> {
        private Object[] array;
        private int size;
        private int capacity;
        private final double shrinkThreshold = 0.25;
        private final double growthFactor = 1.5;
        
        @SuppressWarnings("unchecked")
        public AdaptiveArray() {
            this.capacity = 10;
            this.array = new Object[capacity];
            this.size = 0;
        }
        
        public void add(T element) {
            if (size >= capacity) {
                resize((int) (capacity * growthFactor));
            }
            array[size++] = element;
        }
        
        @SuppressWarnings("unchecked")
        public T remove(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            
            T removed = (T) array[index];
            
            // Shift elements
            System.arraycopy(array, index + 1, array, index, size - index - 1);
            array[--size] = null;
            
            // Shrink if necessary
            if (size < capacity * shrinkThreshold && capacity > 10) {
                resize(Math.max(10, (int) (capacity / growthFactor)));
            }
            
            return removed;
        }
        
        private void resize(int newCapacity) {
            array = Arrays.copyOf(array, newCapacity);
            capacity = newCapacity;
        }
        
        @SuppressWarnings("unchecked")
        public T get(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            return (T) array[index];
        }
        
        public int size() {You now have everything needed to master arrays and excel in coding interviews. The key is consistent, deliberate practice combined with deep understanding of underlying patterns. Good luck! 🚀🎯

---

## Multi-Threading and Concurrent Arrays

### 1. Thread-Safe Array Operations
```java
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ThreadSafeArray {
    private final int[] array;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    public ThreadSafeArray(int size) {
        this.array = new int[size];
    }
    
    public int get(int index) {
        lock.readLock().lock();
        try {
            return array[index];
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public void set(int index, int value) {
        lock.writeLock().lock();
        try {
            array[index] = value;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    // Atomic operations using AtomicIntegerArray
    public static class AtomicArray {
        private final AtomicIntegerArray atomicArray;
        
        public AtomicArray(int size) {
            this.atomicArray = new AtomicIntegerArray(size);
        }
        
        public int incrementAndGet(int index) {
            return atomicArray.incrementAndGet(index);
        }
        
        public boolean compareAndSet(int index, int expected, int update) {
            return atomicArray.compareAndSet(index, expected, update);
        }
        
        public int addAndGet(int index, int delta) {
            return atomicArray.addAndGet(index, delta);
        }
    }
}
```

### 2. Parallel Array Processing
```java
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ParallelArrayOperations {
    
    // Parallel array sum using Fork-Join Framework
    public static class ParallelSum extends RecursiveTask<Long> {
        private static final int THRESHOLD = 10000;
        private final int[] array;
        private final int start, end;
        
        public ParallelSum(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected Long compute() {
            if (end - start <= THRESHOLD) {
                // Sequential computation for small arrays
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            }
            
            // Divide and conquer
            int mid = (start + end) / 2;
            ParallelSum leftTask = new ParallelSum(array, start, mid);
            ParallelSum rightTask = new ParallelSum(array, mid, end);
            
            leftTask.fork(); // Execute asynchronously
            long rightResult = rightTask.compute();
            long leftResult = leftTask.join();
            
            return leftResult + rightResult;
        }
    }
    
    // Parallel array operations using Streams
    public static void parallelStreamOperations() {
        int[] array = IntStream.range(0, 1000000).toArray();
        
        // Parallel sum
        long sum = IntStream.of(array).parallel().sum();
        
        // Parallel filtering and mapping
        int[] evenSquares = IntStream.of(array)
            .parallel()
            .filter(x -> x % 2 == 0)
            .map(x -> x * x)
            .toArray();
        
        // Parallel sorting
        int[] sortedArray = IntStream.of(array)
            .parallel()
            .sorted()
            .toArray();
    }
    
    // Custom parallel merge sort
    public static void parallelMergeSort(int[] array) {
        ForkJoinPool pool = new ForkJoinPool();
        try {
            pool.invoke(new ParallelMergeSortTask(array, 0, array.length - 1));
        } finally {
            pool.shutdown();
        }
    }
    
    private static class ParallelMergeSortTask extends RecursiveTask<Void> {
        private static final int THRESHOLD = 10000;
        private final int[] array;
        private final int start, end;
        
        public ParallelMergeSortTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected Void compute() {
            if (end - start <= THRESHOLD) {
                Arrays.sort(array, start, end + 1);
                return null;
            }
            
            int mid = (start + end) / 2;
            ParallelMergeSortTask left = new ParallelMergeSortTask(array, start, mid);
            ParallelMergeSortTask right = new ParallelMergeSortTask(array, mid + 1, end);
            
            invokeAll(left, right);
            merge(array, start, mid, end);
            
            return null;
        }
        
        private void merge(int[] arr, int left, int mid, int right) {
            int[] temp = new int[right - left + 1];
            int i = left, j = mid + 1, k = 0;
            
            while (i <= mid && j <= right) {
                if (arr[i] <= arr[j]) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                }
            }
            
            while (i <= mid) temp[k++] = arr[i++];
            while (j <= right) temp[k++] = arr[j++];
            
            System.arraycopy(temp, 0, arr, left, temp.length);
        }
    }
}
```

---

## Memory-Mapped Arrays and Big Data

### 1. Memory-Mapped File Arrays
```java
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryMappedArray {
    private final MappedByteBuffer buffer;
    private final int size;
    
    public MemoryMappedArray(String filename, int size) throws Exception {
        this.size = size;
        RandomAccessFile file = new RandomAccessFile(filename, "rw");
        FileChannel channel = file.getChannel();
        
        // Map file to memory (4 bytes per integer)
        this.buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, size * 4L);
        
        channel.close();
        file.close();
    }
    
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return buffer.getInt(index * 4);
    }
    
    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        buffer.putInt(index * 4, value);
    }
    
    public void force() {
        buffer.force(); // Force any changes to be written to storage
    }
    
    // Batch operations for better performance
    public void bulkSet(int startIndex, int[] values) {
        for (int i = 0; i < values.length && startIndex + i < size; i++) {
            buffer.putInt((startIndex + i) * 4, values[i]);
        }
    }
    
    public int[] bulkGet(int startIndex, int count) {
        int actualCount = Math.min(count, size - startIndex);
        int[] result = new int[actualCount];
        
        for (int i = 0; i < actualCount; i++) {
            result[i] = buffer.getInt((startIndex + i) * 4);
        }
        
        return result;
    }
}
```

### 2. External Sorting for Large Arrays
```java
import java.io.*;
import java.util.*;

public class ExternalSort {
    private static final int MAX_MEMORY_SIZE = 1000000; // Max elements in memory
    
    public static void externalSort(String inputFile, String outputFile) throws IOException {
        List<String> tempFiles = createSortedChunks(inputFile);
        mergeChunks(tempFiles, outputFile);
        
        // Clean up temporary files
        for (String tempFile : tempFiles) {
            new File(tempFile).delete();
        }
    }
    
    private static List<String> createSortedChunks(String inputFile) throws IOException {
        List<String> tempFiles = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        
        List<Integer> chunk = new ArrayList<>();
        String line;
        int chunkNumber = 0;
        
        while ((line = reader.readLine()) != null) {
            chunk.add(Integer.parseInt(line));
            
            if (chunk.size() >= MAX_MEMORY_SIZE) {
                String tempFile = sortAndWriteChunk(chunk, chunkNumber++);
                tempFiles.add(tempFile);
                chunk.clear();
            }
        }
        
        // Handle remaining elements
        if (!chunk.isEmpty()) {
            String tempFile = sortAndWriteChunk(chunk, chunkNumber);
            tempFiles.add(tempFile);
        }
        
        reader.close();
        return tempFiles;
    }
    
    private static String sortAndWriteChunk(List<Integer> chunk, int chunkNumber) throws IOException {
        Collections.sort(chunk);
        String tempFile = "temp_chunk_" + chunkNumber + ".txt";
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        for (Integer value : chunk) {
            writer.write(value.toString());
            writer.newLine();
        }
        writer.close();
        
        return tempFile;
    }
    
    private static void mergeChunks(List<String> tempFiles, String outputFile) throws IOException {
        PriorityQueue<ChunkReader> pq = new PriorityQueue<>(
            Comparator.comparingInt(reader -> reader.currentValue)
        );
        
        // Initialize chunk readers
        for (String tempFile : tempFiles) {
            ChunkReader reader = new ChunkReader(tempFile);
            if (reader.hasNext()) {
                pq.offer(reader);
            }
        }
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        
        while (!pq.isEmpty()) {
            ChunkReader reader = pq.poll();
            writer.write(String.valueOf(reader.currentValue));
            writer.newLine();
            
            if (reader.readNext()) {
                pq.offer(reader);
            } else {
                reader.close();
            }
        }
        
        writer.close();
    }
    
    private static class ChunkReader {
        private BufferedReader reader;
        private Integer currentValue;
        
        public ChunkReader(String filename) throws IOException {
            reader = new BufferedReader(new FileReader(filename));
            readNext();
        }
        
        public boolean readNext() throws IOException {
            String line = reader.readLine();
            if (line != null) {
                currentValue = Integer.parseInt(line);
                return true;
            }
            return false;
        }
        
        public boolean hasNext() {
            return currentValue != null;
        }
        
        public void close() throws IOException {
            reader.close();
        }
    }
}
```

---

## Advanced Data Structures with Arrays

### 1. Trie Implementation with Arrays
```java
public class ArrayTrie {
    private static final int ALPHABET_SIZE = 26;
    
    private static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        
        public TrieNode() {
            children = new TrieNode[ALPHABET_SIZE];
            isEndOfWord = false;
        }
    }
    
    private final TrieNode root;
    
    public ArrayTrie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        
        current.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord;
    }
    
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }
    
    private TrieNode searchNode(String word) {
        TrieNode current = root;
        
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        
        return current;
    }
    
    public List<String> getAllWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode prefixNode = searchNode(prefix);
        
        if (prefixNode != null) {
            dfs(prefixNode, prefix, result);
        }
        
        return result;
    }
    
    private void dfs(TrieNode node, String currentWord, List<String> result) {
        if (node.isEndOfWord) {
            result.add(currentWord);
        }
        
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (node.children[i] != null) {
                char nextChar = (char) ('a' + i);
                dfs(node.children[i], currentWord + nextChar, result);
            }
        }
    }
}
```

### 2. Advanced Hash Table with Array Backing
```java
public class ArrayHashTable<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    
    private Entry<K, V>[] buckets;
    private int size;
    private int capacity;
    
    @SuppressWarnings("unchecked")
    public ArrayHashTable() {
        this.capacity = DEFAULT_CAPACITY;
        this.buckets = new Entry[capacity];
        this.size = 0;
    }
    
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private int hash(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode()) % capacity;
    }
    
    public void put(K key, V value) {
        if ((double) size / capacity >= LOAD_FACTOR) {
            resize();
        }
        
        int index = hash(key);
        Entry<K, V> entry = buckets[index];
        
        if (entry == null) {
            buckets[index] = new Entry<>(key, value);
            size++;
            return;
        }
        
        // Handle collisions with chaining
        while (entry != null) {
            if (entry.key != null && entry.key.equals(key)) {
                entry.value = value; // Update existing key
                return;
            }
            if (entry.next == null) break;
            entry = entry.next;
        }
        
        entry.next = new Entry<>(key, value);
        size++;
    }
    
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> entry = buckets[index];
        
        while (entry != null) {
            if (entry.key != null && entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        
        return null;
    }
    
    public boolean remove(K key) {
        int index = hash(key);
        Entry<K, V> entry = buckets[index];
        Entry<K, V> prev = null;
        
        while (entry != null) {
            if (entry.key != null && entry.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return true;
            }
            prev = entry;
            entry = entry.next;
        }
        
        return false;
    }
    
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldBuckets = buckets;
        capacity *= 2;
        buckets = new Entry[capacity];
        size = 0;
        
        for (Entry<K, V> entry : oldBuckets) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Get load factor for monitoring performance
    public double getLoadFactor() {
        return (double) size / capacity;
    }
}
```

---

## Real-World Applications and Case Studies

### 1. Image Processing with Arrays
```java
public class ImageProcessor {
    
    // Represent image as 2D array of pixels
    public static class Image {
        private final int[][] pixels;
        private final int width, height;
        
        public Image(int width, int height) {
            this.width = width;
            this.height = height;
            this.pixels = new int[height][width];
        }
        
        public void setPixel(int x, int y, int value) {
            if (x >= 0 && x < width && y >= 0 && y < height) {
                pixels[y][x] = value;
            }
        }
        
        public int getPixel(int x, int y) {
            if (x >= 0 && x < width && y >= 0 && y < height) {
                return pixels[y][x];
            }
            return 0;
        }
    }
    
    // Gaussian blur filter
    public static Image applyGaussianBlur(Image input) {
        int[][] kernel = {
            {1, 2, 1},
            {2, 4, 2},
            {1, 2, 1}
        };
        int kernelSum = 16;
        
        Image output = new Image(input.width, input.height);
        
        for (int y = 1; y < input.height - 1; y++) {
            for (int x = 1; x < input.width - 1; x++) {
                int sum = 0;
                
                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        sum += input.getPixel(x + kx, y + ky) * kernel[ky + 1][kx + 1];
                    }
                }
                
                output.setPixel(x, y, sum / kernelSum);
            }
        }
        
        return output;
    }
    
    // Edge detection using Sobel operator
    public static Image detectEdges(Image input) {
        int[][] sobelX = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] sobelY = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        
        Image output = new Image(input.width, input.height);
        
        for (int y = 1; y < input.height - 1; y++) {
            for (int x = 1; x < input.width - 1; x++) {
                int gx = 0, gy = 0;
                
                for (int ky = -1; ky <= 1; ky++) {
                    for (int kx = -1; kx <= 1; kx++) {
                        int pixel = input.getPixel(x + kx, y + ky);
                        gx += pixel * sobelX[ky + 1][kx + 1];
                        gy += pixel * sobelY[ky + 1][kx + 1];
                    }
                }
                
                int magnitude = (int) Math.sqrt(gx * gx + gy * gy);
                output.setPixel(x, y, Math.min(255, magnitude));
            }
        }
        
        return output;
    }
}
```

### 2. Financial Data Analysis
```java
public class FinancialAnalyzer {
    
    public static class StockData {
        private final double[] prices;
        private final long[] volumes;
        private final String[] dates;
        
        public StockData(double[] prices, long[] volumes, String[] dates) {
            this.prices = prices.clone();
            this.volumes = volumes.clone();
            this.dates = dates.clone();
        }
        
        // Calculate moving average
        public double[] calculateMovingAverage(int window) {
            double[] movingAvg = new double[prices.length - window + 1];
            
            for (int i = 0; i <= prices.length - window; i++) {
                double sum = 0;
                for (int j = i; j < i + window; j++) {
                    sum += prices[j];
                }
                movingAvg[i] = sum / window;
            }
            
            return movingAvg;
        }
        
        // Calculate volatility (standard deviation)
        public double calculateVolatility() {
            double mean = Arrays.stream(prices).average().orElse(0.0);
            double variance = Arrays.stream(prices)
                .map(price -> Math.pow(price - mean, 2))
                .average()
                .orElse(0.0);
            
            return Math.sqrt(variance);
        }
        
        // Find maximum drawdown
        public double calculateMaxDrawdown() {
            double maxDrawdown = 0.0;
            double peak = prices[0];
            
            for (double price : prices) {
                if (price > peak) {
                    peak = price;
                }
                
                double drawdown = (peak - price) / peak;
                maxDrawdown = Math.max(maxDrawdown, drawdown);
            }
            
            return maxDrawdown;
        }
        
        // Calculate Relative Strength Index (RSI)
        public double[] calculateRSI(int period) {
            double[] rsi = new double[prices.length - period];
            double[] gains = new double[prices.length - 1];
            double[] losses = new double[prices.length - 1];
            
            // Calculate daily gains and losses
            for (int i = 1; i < prices.length; i++) {
                double change = prices[i] - prices[i - 1];
                gains[i - 1] = Math.max(change, 0);
                losses[i - 1] = Math.max(-change, 0);
            }
            
            // Calculate RSI
            for (int i = period; i < prices.length; i++) {
                double avgGain = 0, avgLoss = 0;
                
                for (int j = i - period; j < i; j++) {
                    avgGain += gains[j];
                    avgLoss += losses[j];
                }
                
                avgGain /= period;
                avgLoss /= period;
                
                double rs = avgLoss == 0 ? 100 : avgGain / avgLoss;
                rsi[i - period] = 100 - (100 / (1 + rs));
            }
            
            return rsi;
        }
    }
    
    // Portfolio optimization using array operations
    public static class PortfolioOptimizer {
        public static double[] optimizeWeights(double[][] returns, double targetReturn) {
            int numAssets = returns[0].length;
            double[] weights = new double[numAssets];
            
            // Initialize with equal weights
            Arrays.fill(weights, 1.0 / numAssets);
            
            // Simple optimization using gradient descent
            double learningRate = 0.01;
            int iterations = 1000;
            
            for (int iter = 0; iter < iterations; iter++) {
                double[] gradients = calculateGradients(returns, weights, targetReturn);
                
                for (int i = 0; i < numAssets; i++) {
                    weights[i] -= learningRate * gradients[i];
                    weights[i] = Math.max(0, Math.min(1, weights[i])); // Constrain to [0,1]
                }
                
                // Normalize weights to sum to 1
                double sum = Arrays.stream(weights).sum();
                for (int i = 0; i < numAssets; i++) {
                    weights[i] /= sum;
                }
            }
            
            return weights;
        }
        
        private static double[] calculateGradients(double[][] returns, double[] weights, double targetReturn) {
            int numAssets = weights.length;
            double[] gradients = new double[numAssets];
            
            // Simplified gradient calculation for demonstration
            for (int i = 0; i < numAssets; i++) {
                double assetReturn = Arrays.stream(returns).mapToDouble(period -> period[i]).average().orElse(0);
                gradients[i] = 2 * (calculatePortfolioReturn(returns, weights) - targetReturn) * assetReturn;
            }
            
            return gradients;
        }
        
        private static double calculatePortfolioReturn(double[][] returns, double[] weights) {
            return Arrays.stream(returns)
                .mapToDouble(period -> {
                    double periodReturn = 0;
                    for (int i = 0; i < weights.length; i++) {
                        periodReturn += weights[i] * period[i];
                    }
                    return periodReturn;
                })
                .average()
                .orElse(0);
        }
    }
}
```

---

## Industry-Specific Interview Questions

### 1. Trading Systems (Quantitative Finance)
```java
public class TradingSystemQuestions {
    
    // Question: Implement a VWAP (Volume Weighted Average Price) calculator
    public static double calculateVWAP(int[] prices, int[] volumes) {
        long totalVolume = 0;
        long totalValue = 0;
        
        for (int i = 0; i < prices.length; i++) {
            totalValue += (long) prices[i] * volumes[i];
            totalVolume += volumes[i];
        }
        
        return totalVolume == 0 ? 0 : (double) totalValue / totalVolume;
    }
    
    // Question: Find the best time to execute a large order to minimize market impact
    public static class OrderScheduler {
        public static int[] scheduleOrder(int[] volumes, int totalOrderSize) {
            int[] schedule = new int[volumes.length];
            double totalMarketVolume = Arrays.stream(volumes).sum();
            
            for (int i = 0; i < volumes.length; i++) {
                // Schedule proportional to market volume to minimize impact
                double proportion = volumes[i] / totalMarketVolume;
                schedule[i] = (int) (totalOrderSize * proportion);
            }
            
            return schedule;
        }
    }
    
    // Question: Detect price manipulation patterns
    public static List<Integer> detectSuspiciousActivity(int[] prices, int[] volumes) {
        List<Integer> suspiciousPeriods = new ArrayList<>();
        
        for (int i = 1; i < prices.length - 1; i++) {
            // Look for unusual price movements with low volume
            double priceChange = Math.abs(prices[i] - prices[i-1]) / (double) prices[i-1];
            boolean lowVolume = volumes[i] < Arrays.stream(volumes).average().orElse(0) * 0.5;
            
            if (priceChange > 0.05 && lowVolume) { // 5% price change with low volume
                suspiciousPeriods.add(i);
            }
        }
        
        return suspiciousPeriods;
    }
}
```

### 2. E-commerce and Recommendation Systems
```java
public class EcommerceQuestions {
    
    // Question: Implement a recommendation system based on user behavior
    public static class RecommendationEngine {
        private final int[][] userItemMatrix; // rows: users, cols: items
        
        public RecommendationEngine(int[][] userItemMatrix) {
            this.userItemMatrix = userItemMatrix;
        }
        
        // Collaborative filtering using cosine similarity
        public int[] recommendItems(int userId, int numRecommendations) {
            double[] similarities = calculateUserSimilarities(userId);
            Map<Integer, Double> itemScores = new HashMap<>();
            
            for (int otherUser = 0; otherUser < userItemMatrix.length; otherUser++) {
                if (otherUser == userId) continue;
                
                double similarity = similarities[otherUser];
                if (similarity > 0) {
                    for (int item = 0; item < userItemMatrix[0].length; item++) {
                        if (userItemMatrix[userId][item] == 0 && userItemMatrix[otherUser][item] > 0) {
                            itemScores.put(item, itemScores.getOrDefault(item, 0.0) + 
                                         similarity * userItemMatrix[otherUser][item]);
                        }
                    }
                }
            }
            
            return itemScores.entrySet().stream()
                .sorted(Map.Entry.<Integer, Double>comparingByValue().reversed())
                .limit(numRecommendations)
                .mapToInt(Map.Entry::getKey)
                .toArray();
        }
        
        private double[] calculateUserSimilarities(int userId) {
            double[] similarities = new double[userItemMatrix.length];
            
            for (int otherUser = 0; otherUser < userItemMatrix.length; otherUser++) {
                if (otherUser != userId) {
                    similarities[otherUser] = cosineSimilarity(
                        userItemMatrix[userId], userItemMatrix[otherUser]
                    );
                }
            }
            
            return similarities;
        }
        
        private double cosineSimilarity(int[] vectorA, int[] vectorB) {
            double dotProduct = 0, normA = 0, normB = 0;
            
            for (int i = 0; i < vectorA.length; i++) {
                dotProduct += vectorA[i] * vectorB[i];
                normA += vectorA[i] * vectorA[i];
                normB += vectorB[i] * vectorB[i];
            }
            
            return (normA == 0 ||# Complete Java Arrays Mastery Guide for Coding Interviews

## Table of Contents
1. [Array Fundamentals](#array-fundamentals)
2. [Array Creation and Initialization](#array-creation-and-initialization)
3. [Core Array Operations](#core-array-operations)
4. [Essential Array Algorithms](#essential-array-algorithms)
5. [Common Array Patterns](#common-array-patterns)
6. [Time and Space Complexity](#time-and-space-complexity)
7. [LeetCode Problem Categories](#leetcode-problem-categories)
8. [Advanced Techniques](#advanced-techniques)
9. [Interview Questions and Solutions](#interview-questions-and-solutions)
10. [Practice Problems](#practice-problems)

---

## Array Fundamentals

### What are Arrays?
Arrays are data structures that store multiple elements of the same type in contiguous memory locations. In Java, arrays are objects that provide indexed access to elements.

### Key Characteristics
- **Fixed Size:** Once created, size cannot be changed
- **Zero-indexed:** First element is at index 0
- **Homogeneous:** All elements must be of the same type
- **Reference Type:** Arrays are objects in Java
- **Contiguous Memory:** Elements stored in adjacent memory locations

### Array Declaration and Types
```java
// Declaration syntax
int[] arr1;           // Preferred Java style
int arr2[];          // C-style (works but not preferred)

// Multi-dimensional arrays
int[][] matrix;      // 2D array
int[][][] cube;      // 3D array

// Different data types
boolean[] flags;
String[] names;
double[] prices;
Object[] objects;
```

---

## Array Creation and Initialization

### 1. Static Initialization
```java
// Array literal initialization
int[] numbers = {1, 2, 3, 4, 5};
String[] fruits = {"apple", "banana", "orange"};
boolean[] flags = {true, false, true};

// Multi-dimensional arrays
int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
int[][] jagged = {{1, 2}, {3, 4, 5}, {6}};
```

### 2. Dynamic Initialization
```java
// Using new keyword
int[] arr = new int[5];              // Creates array of size 5, all elements = 0
String[] names = new String[3];      // All elements = null
boolean[] flags = new boolean[4];    // All elements = false

// Initialize with values
int[] nums = new int[]{10, 20, 30, 40, 50};
```

### 3. Default Values
```java
// Primitive types default values
int[] ints = new int[3];        // [0, 0, 0]
double[] doubles = new double[3]; // [0.0, 0.0, 0.0]
boolean[] bools = new boolean[3]; // [false, false, false]
char[] chars = new char[3];     // ['\u0000', '\u0000', '\u0000']

// Reference types default to null
String[] strings = new String[3]; // [null, null, null]
Object[] objects = new Object[3]; // [null, null, null]
```

### 4. Array Copying
```java
// Method 1: System.arraycopy()
int[] source = {1, 2, 3, 4, 5};
int[] dest = new int[5];
System.arraycopy(source, 0, dest, 0, 5);

// Method 2: Arrays.copyOf()
int[] copy1 = Arrays.copyOf(source, source.length);
int[] copy2 = Arrays.copyOf(source, 10); // Extends with default values

// Method 3: Arrays.copyOfRange()
int[] partial = Arrays.copyOfRange(source, 1, 4); // [2, 3, 4]

// Method 4: clone() - shallow copy
int[] clone = source.clone();
```

---

## Core Array Operations

### 1. Basic Operations
```java
public class ArrayOperations {
    
    // Access elements
    public static void accessElements(int[] arr) {
        System.out.println("First element: " + arr[0]);
        System.out.println("Last element: " + arr[arr.length - 1]);
        
        // Safe access with bounds checking
        int index = 5;
        if (index >= 0 && index < arr.length) {
            System.out.println("Element at " + index + ": " + arr[index]);
        }
    }
    
    // Traverse array
    public static void traverseArray(int[] arr) {
        // Method 1: Traditional for loop
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        
        // Method 2: Enhanced for loop (for-each)
        for (int element : arr) {
            System.out.print(element + " ");
        }
        
        // Method 3: While loop
        int i = 0;
        while (i < arr.length) {
            System.out.print(arr[i] + " ");
            i++;
        }
    }
    
    // Update elements
    public static void updateElements(int[] arr) {
        arr[0] = 100;              // Update single element
        Arrays.fill(arr, 0);       // Fill entire array with 0
        Arrays.fill(arr, 2, 4, 5); // Fill arr[2] to arr[3] with 5
    }
}
```

### 2. Search Operations
```java
public class ArraySearch {
    
    // Linear Search - O(n)
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1; // Not found
    }
    
    // Binary Search - O(log n) - requires sorted array
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoid overflow
            
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Not found
    }
    
    // Using built-in binary search
    public static int builtInBinarySearch(int[] arr, int target) {
        // Array must be sorted first
        Arrays.sort(arr);
        int result = Arrays.binarySearch(arr, target);
        return result >= 0 ? result : -1;
    }
    
    // Find all occurrences
    public static List<Integer> findAllOccurrences(int[] arr, int target) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                indices.add(i);
            }
        }
        return indices;
    }
}
```

### 3. Sorting Operations
```java
public class ArraySorting {
    
    // Bubble Sort - O(n²)
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break; // Optimization: array is sorted
        }
    }
    
    // Selection Sort - O(n²)
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            swap(arr, i, minIdx);
        }
    }
    
    // Insertion Sort - O(n²) best case O(n)
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    
    // Quick Sort - O(n log n) average, O(n²) worst
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    // Merge Sort - O(n log n) guaranteed
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        
        System.arraycopy(temp, 0, arr, left, temp.length);
    }
    
    // Using built-in sort - O(n log n)
    public static void builtInSort(int[] arr) {
        Arrays.sort(arr); // Uses Dual-Pivot Quicksort
    }
    
    // Custom comparator sort for objects
    public static void customSort(String[] arr) {
        Arrays.sort(arr, (a, b) -> a.length() - b.length()); // Sort by length
        Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);     // Case insensitive
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

---

## Essential Array Algorithms

### 1. Array Manipulation Algorithms
```java
public class ArrayManipulation {
    
    // Reverse Array - O(n)
    public static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    
    // Rotate Array Left by k positions - O(n)
    public static void rotateLeft(int[] arr, int k) {
        int n = arr.length;
        k = k % n; // Handle k > n
        
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
        reverse(arr, 0, n - 1);
    }
    
    // Rotate Array Right by k positions - O(n)
    public static void rotateRight(int[] arr, int k) {
        int n = arr.length;
        k = k % n;
        
        reverse(arr, 0, n - 1);
        reverse(arr, 0, k - 1);
        reverse(arr, k, n - 1);
    }
    
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    
    // Remove duplicates from sorted array - O(n)
    public static int removeDuplicates(int[] arr) {
        if (arr.length == 0) return 0;
        
        int writeIndex = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                arr[writeIndex] = arr[i];
                writeIndex++;
            }
        }
        return writeIndex;
    }
    
    // Move zeros to end - O(n)
    public static void moveZerosToEnd(int[] arr) {
        int writeIndex = 0;
        
        // Move non-zero elements to front
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[writeIndex] = arr[i];
                writeIndex++;
            }
        }
        
        // Fill remaining positions with zeros
        while (writeIndex < arr.length) {
            arr[writeIndex] = 0;
            writeIndex++;
        }
    }
}
```

### 2. Mathematical Array Problems
```java
public class ArrayMath {
    
    // Find Maximum and Minimum - O(n)
    public static int[] findMaxMin(int[] arr) {
        if (arr.length == 0) return new int[]{0, 0};
        
        int max = arr[0];
        int min = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        
        return new int[]{max, min};
    }
    
    // Sum of Array Elements - O(n)
    public static long sumArray(int[] arr) {
        long sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }
    
    // Product of Array Elements - O(n)
    public static long productArray(int[] arr) {
        long product = 1;
        for (int num : arr) {
            product *= num;
            if (product == 0) break; // Optimization for zero
        }
        return product;
    }
    
    // Array of products except self - O(n)
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Left products
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        // Right products
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= rightProduct;
            rightProduct *= nums[i];
        }
        
        return result;
    }
    
    // Find equilibrium index - O(n)
    public static int findEquilibriumIndex(int[] arr) {
        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }
        
        long leftSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum -= arr[i]; // This becomes right sum
            
            if (leftSum == totalSum) {
                return i;
            }
            
            leftSum += arr[i];
        }
        
        return -1; // No equilibrium index found
    }
    
    // Find missing number in array 1 to n+1 - O(n)
    public static int findMissingNumber(int[] arr) {
        int n = arr.length + 1;
        long expectedSum = (long) n * (n + 1) / 2;
        long actualSum = 0;
        
        for (int num : arr) {
            actualSum += num;
        }
        
        return (int) (expectedSum - actualSum);
    }
}
```

---

## Common Array Patterns

### 1. Two Pointer Technique
```java
public class TwoPointerProblems {
    
    // Two Sum in Sorted Array - O(n)
    public static int[] twoSum(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return new int[]{-1, -1}; // Not found
    }
    
    // Three Sum - O(n²)
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip duplicates
            
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // Skip duplicates
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        
        return result;
    }
    
    // Container With Most Water - O(n)
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxWater = 0;
        
        while (left < right) {
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            maxWater = Math.max(maxWater, width * currentHeight);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return maxWater;
    }
    
    // Remove duplicates from sorted array - O(n)
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        
        int slow = 0;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        
        return slow + 1;
    }
}
```

### 2. Sliding Window Technique
```java
public class SlidingWindow {
    
    // Maximum sum of k consecutive elements - O(n)
    public static int maxSumK(int[] arr, int k) {
        if (arr.length < k) return -1;
        
        // Calculate sum of first window
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        
        int maxSum = windowSum;
        
        // Slide the window
        for (int i = k; i < arr.length; i++) {
            windowSum = windowSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        
        return maxSum;
    }
    
    // Minimum window size with sum >= target - O(n)
    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0;
        int minLen = Integer.MAX_VALUE;
        
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    // Longest subarray with at most k distinct elements - O(n)
    public static int longestSubarrayKDistinct(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;
        
        for (int right = 0; right < nums.length; right++) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            
            while (map.size() > k) {
                int count = map.get(nums[left]);
                if (count == 1) {
                    map.remove(nums[left]);
                } else {
                    map.put(nums[left], count - 1);
                }
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}
```

### 3. Prefix Sum Technique
```java
public class PrefixSum {
    
    // Range sum queries - O(1) per query after O(n) preprocessing
    public static class RangeSumQuery {
        private int[] prefixSum;
        
        public RangeSumQuery(int[] nums) {
            prefixSum = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefixSum[i + 1] = prefixSum[i] + nums[i];
            }
        }
        
        public int sumRange(int left, int right) {
            return prefixSum[right + 1] - prefixSum[left];
        }
    }
    
    // Subarray sum equals k - O(n)
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1); // Empty prefix sum
        
        int count = 0, prefixSum = 0;
        for (int num : nums) {
            prefixSum += num;
            
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }
            
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }
        
        return count;
    }
    
    // Maximum subarray sum (Kadane's Algorithm) - O(n)
    public static int maxSubarraySum(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
}
```

### 4. Dutch National Flag Algorithm
```java
public class DutchNationalFlag {
    
    // Sort colors (0, 1, 2) in-place - O(n)
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        
        while (mid <= high) {
            switch (nums[mid]) {
                case 0:
                    swap(nums, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(nums, mid, high);
                    high--;
                    // Don't increment mid here
                    break;
            }
        }
    }
    
    // Partition array around pivot - O(n)
    public static int partition(int[] arr, int pivot) {
        int left = 0, right = arr.length - 1;
        
        while (left <= right) {
            if (arr[left] < pivot) {
                left++;
            } else if (arr[right] > pivot) {
                right--;
            } else {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        
        return left; // Partition point
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```

---

## Time and Space Complexity

### Common Array Operations Complexity

| Operation | Time Complexity | Space Complexity | Notes |
|-----------|----------------|------------------|--------|
| Access by index | O(1) | O(1) | Random access |
| Linear search | O(n) | O(1) | Worst case |
| Binary search | O(log n) | O(1) | Sorted array required |
| Insertion at end | O(1) | O(1) | If space available |
| Insertion at beginning | O(n) | O(1) | Requires shifting |
| Deletion at end | O(1) | O(1) | Simple assignment |
| Deletion at beginning | O(n) | O(1) | Requires shifting |
| Bubble Sort | O(n²) | O(1) | In-place |
| Selection Sort | O(n²) | O(1) | In-place |
| Insertion Sort | O(n²) | O(1) | Best case O(n) |
| Merge Sort | O(n log n) | O(n) | Stable sort |
| Quick Sort | O(n log n) avg | O(log n) | Worst case O(n²) |

### Space Complexity Considerations
```java
public class SpaceComplexity {
    
    // O(1) space - in-place algorithm
    public static void reverseInPlace(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    
    // O(n) space - creating new array
    public static int[] reverseNewArray(int[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        return result;
    }
    
    // O(log n) space - recursive calls
    public static void quickSortRecursive(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortRecursive(arr, low, pi - 1);   // Recursive call
            quickSortRecursive(arr, pi + 1, high); // Recursive call
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        // Implementation here
        return 0;
    }
}
```

---

## LeetCode Problem Categories

### Easy Level Problems (Foundation Building)
```java
public class EasyProblems {
    
    // 1. Two Sum - HashMap approach - O(n)
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }
    
    // 26. Remove Duplicates from Sorted Array - O(n)
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                j++;
                nums[j] = nums[i];
            }
        }
        return j + 1;
    }
    
    // 27. Remove Element - O(n)
    public static int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }
    
    // 53. Maximum Subarray (Kadane's Algorithm) - O(n)
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
    
    // 66. Plus One - O(n)
    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        
        // All digits were 9
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
    
    // 88. Merge Sorted Array - O(m + n)
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
    
    // 121. Best Time to Buy and Sell Stock - O(n)
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}
```

### Medium Level Problems (Core Skills)
```java
public class MediumProblems {
    
    // 11. Container With Most Water - O(n)
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxWater = 0;
        
        while (left < right) {
            int width = right - left;
            int h = Math.min(height[left], height[right]);
            maxWater = Math.max(maxWater, width * h);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxWater;
    }
    
    // 15. 3Sum - O(n²)
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
    
    // 33. Search in Rotated Sorted Array - O(log n)
    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // Check which half is sorted
            if (nums[left] <= nums[mid]) { // Left half is sorted
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // Right half is sorted
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
    
    // 48. Rotate Image - O(n²)
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // Transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // Reverse each row
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }
    
    // 56. Merge Intervals - O(n log n)
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> merged = new ArrayList<>();
        
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(
                    merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
    
    // 75. Sort Colors (Dutch National Flag) - O(n)
    public static void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;
        
        while (mid <= high) {
            switch (nums[mid]) {
                case 0:
                    swap(nums, low, mid);
                    low++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    swap(nums, mid, high);
                    high--;
                    break;
            }
        }
    }
    
    // 238. Product of Array Except Self - O(n)
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }
        
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= right;
            right *= nums[i];
        }
        
        return result;
    }
    
    // 560. Subarray Sum Equals K - O(n)
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0, 1);
        
        int count = 0, prefixSum = 0;
        for (int num : nums) {
            prefixSum += num;
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }
            prefixSumCount.put(prefixSum, prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### Hard Level Problems (Advanced Mastery)
```java
public class HardProblems {
    
    // 4. Median of Two Sorted Arrays - O(log(min(m,n)))
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        
        int x = nums1.length;
        int y = nums2.length;
        
        int low = 0;
        int high = x;
        
        while (low <= high) {
            int cutX = (low + high) / 2;
            int cutY = (x + y + 1) / 2 - cutX;
            
            int maxX = (cutX == 0) ? Integer.MIN_VALUE : nums1[cutX - 1];
            int maxY = (cutY == 0) ? Integer.MIN_VALUE : nums2[cutY - 1];
            
            int minX = (cutX == x) ? Integer.MAX_VALUE : nums1[cutX];
            int minY = (cutY == y) ? Integer.MAX_VALUE : nums2[cutY];
            
            if (maxX <= minY && maxY <= minX) {
                if ((x + y) % 2 == 0) {
                    return ((double)Math.max(maxX, maxY) + Math.min(minX, minY)) / 2;
                } else {
                    return (double)Math.max(maxX, maxY);
                }
            } else if (maxX > minY) {
                high = cutX - 1;
            } else {
                low = cutX + 1;
            }
        }
        return 1.0;
    }
    
    // 42. Trapping Rain Water - O(n)
    public static int trap(int[] height) {
        if (height.length == 0) return 0;
        
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        return water;
    }
    
    // 84. Largest Rectangle in Histogram - O(n)
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int index = 0;
        
        while (index < heights.length) {
            if (stack.isEmpty() || heights[index] >= heights[stack.peek()]) {
                stack.push(index++);
            } else {
                int top = stack.pop();
                int area = heights[top] * (stack.isEmpty() ? index : index - stack.peek() - 1);
                maxArea = Math.max(maxArea, area);
            }
        }
        
        while (!stack.isEmpty()) {
            int top = stack.pop();
            int area = heights[top] * (stack.isEmpty() ? index : index - stack.peek() - 1);
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }
    
    // 239. Sliding Window Maximum - O(n)
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        
        for (int i = 0; i < nums.length; i++) {
            // Remove indices outside current window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            
            // Remove smaller elements from back
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
            
            // Add to result if window is complete
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    // 41. First Missing Positive - O(n)
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        
        // Replace non-positive numbers and numbers > n with n+1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        
        // Mark presence using sign
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }
        
        // Find first positive index
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        
        return n + 1;
    }
}
```

---

## Advanced Techniques

### 1. Bit Manipulation with Arrays
```java
public class BitManipulation {
    
    // Single Number - O(n)
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num; // XOR cancels out duplicates
        }
        return result;
    }
    
    // Find two single numbers - O(n)
    public static int[] singleNumberII(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        
        // Find rightmost set bit
        int rightmostSetBit = xor & (-xor);
        
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & rightmostSetBit) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        
        return result;
    }
    
    // Missing number using XOR - O(n)
    public static int missingNumber(int[] nums) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }
        return result;
    }
    
    // Count set bits in range - O(n * log(maxNum))
    public static int countBits(int[] nums) {
        int count = 0;
        for (int num : nums) {
            count += Integer.bitCount(num);
        }
        return count;
    }
}
```

### 2. Cyclic Sort Pattern
```java
public class CyclicSort {
    
    // Cyclic Sort - O(n)
    public static void cyclicSort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }
    }
    
    // Find Missing Number - O(n)
    public static int findMissingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] < nums.length && nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i]);
            } else {
                i++;
            }
        }
        
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        
        return nums.length;
    }
    
    // Find All Missing Numbers - O(n)
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }
        
        List<Integer> missing = new ArrayList<>();
        for (i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                missing.add(i + 1);
            }
        }
        
        return missing;
    }
    
    // Find Duplicate Number - O(n)
    public static int findDuplicate(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i + 1) {
                if (nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i] - 1);
                } else {
                    return nums[i]; // Found duplicate
                }
            } else {
                i++;
            }
        }
        
        return -1;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### 3. Matrix Operations
```java
public class MatrixOperations {
    
    // Spiral Matrix - O(m * n)
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;
        
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        
        while (top <= bottom && left <= right) {
            // Traverse right
            for (int col = left; col <= right; col++) {
                result.add(matrix[top][col]);
            }
            top++;
            
            // Traverse down
            for (int row = top; row <= bottom; row++) {
                result.add(matrix[row][right]);
            }
            right--;
            
            // Traverse left
            if (top <= bottom) {
                for (int col = right; col >= left; col--) {
                    result.add(matrix[bottom][col]);
                }
                bottom--;
            }
            
            // Traverse up
            if (left <= right) {
                for (int row = bottom; row >= top; row--) {
                    result.add(matrix[row][left]);
                }
                left++;
            }
        }
        
        return result;
    }
    
    // Set Matrix Zeroes - O(m * n)
    public static void setZeroes(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColZero = false;
        
        // Check if first row should be zero
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        
        // Check if first column should be zero
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }
        
        // Mark zeros in first row and column
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        
        // Set zeros based on marks
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // Set first row to zero if needed
        if (firstRowZero) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        
        // Set first column to zero if needed
        if (firstColZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
    
    // Search 2D Matrix - O(log(m * n))
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0, right = m * n - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n];
            
            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
}
```

---

## Interview Questions and Solutions

### Behavioral and Conceptual Questions

**Q1: When would you choose an ArrayList over an array in Java?**
```
Answer:
- Dynamic sizing: ArrayList can grow/shrink during runtime
- Built-in methods: add(), remove(), contains(), etc.
- Type safety: Generics prevent ClassCastException
- Integration: Works well with Collections framework

Use arrays when:
- Fixed size is acceptable
- Performance is critical (arrays are faster)
- Working with primitives (ArrayList only stores objects)
- Memory usage is a concern
```

**Q2: Explain the difference between shallow copy and deep copy for arrays**
```java
public class CopyExample {
    public static void demonstrateCopying() {
        // Original array with objects
        Person[] original = {new Person("Alice"), new Person("Bob")};
        
        // Shallow copy - references are copied
        Person[] shallowCopy = original.clone();
        shallowCopy[0].setName("Charlie"); // This changes original[0] too!
        
        // Deep copy - objects are copied
        Person[] deepCopy = new Person[original.length];
        for (int i = 0; i < original.length; i++) {
            deepCopy[i] = new Person(original[i].getName());
        }
        deepCopy[0].setName("David"); // This doesn't affect original
    }
}
```

**Q3: How do you handle array index out of bounds?**
```java
public class SafeArrayAccess {
    
    public static int safeGet(int[] arr, int index, int defaultValue) {
        if (arr == null || index < 0 || index >= arr.length) {
            return defaultValue;
        }
        return arr[index];
    }
    
    public static boolean safeSet(int[] arr, int index, int value) {
        if (arr == null || index < 0 || index >= arr.length) {
            return false;
        }
        arr[index] = value;
        return true;
    }
}
```

### System Design Questions

**Q4: Design a dynamic array (like ArrayList) from scratch**
```java
public class DynamicArray<T> {
    private Object[] array;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 10;
    
    public DynamicArray() {
        this.capacity = DEFAULT_CAPACITY;
        this.array = new Object[capacity];
        this.size = 0;
    }
    
    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.capacity = initialCapacity;
        this.array = new Object[capacity];
        this.size = 0;
    }
    
    public void add(T element) {
        ensureCapacity();
        array[size++] = element;
    }
    
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        ensureCapacity();
        
        // Shift elements to right
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }
    
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (T) array[index];
    }
    
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        @SuppressWarnings("unchecked")
        T removed = (T) array[index];
        
        // Shift elements to left
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null; // Clear reference
        
        return removed;
    }
    
    private void ensureCapacity() {
        if (size >= capacity) {
            capacity = capacity * 2;
            array = Arrays.copyOf(array, capacity);
        }
    }
    
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
}
```

### Advanced Problem Solving

**Q5: Implement a circular buffer using arrays**
```java
public class CircularBuffer {
    private int[] buffer;
    private int head;
    private int tail;
    private int size;
    private int capacity;
    
    public CircularBuffer(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }
    
    public boolean enqueue(int value) {
        if (isFull()) {
            return false; // or overwrite oldest element
        }
        
        buffer[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }
    
    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }
        
        int value = buffer[head];
        head = (head + 1) % capacity;
        size--;
        return value;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
}
```

---

## Practice Problems

### Week 1: Foundations
1. **Array Rotation**: Rotate array left by k positions
2. **Second Largest**: Find second largest element
3. **Array Intersection**: Find common elements in two arrays
4. **Palindrome Array**: Check if array reads same forwards/backwards
5. **Leaders in Array**: Find elements greater than all elements to their right

### Week 2: Two Pointers and Sliding Window
1. **Container With Most Water** (LeetCode 11)
2. **3Sum** (LeetCode 15)
3. **Minimum Window Substring** (LeetCode 76)
4. **Longest Substring Without Repeating Characters** (LeetCode 3)
5. **Trapping Rain Water** (LeetCode 42)

### Week 3: Advanced Patterns
1. **Median of Two Sorted Arrays** (LeetCode 4)
2. **First Missing Positive** (LeetCode 41)
3. **Sliding Window Maximum** (LeetCode 239)
4. **Product of Array Except Self** (LeetCode 238)
5. **Maximum Subarray** (LeetCode 53)

### Week 4: Matrix and 2D Arrays
1. **Spiral Matrix** (LeetCode 54)
2. **Rotate Image** (LeetCode 48)
3. **Set Matrix Zeroes** (LeetCode 73)
4. **Search 2D Matrix** (LeetCode 74)
5. **Word Search** (LeetCode 79)

### Practice Schedule Recommendation
```
Daily Practice (1-2 hours):
- 30 minutes: Theory and concept review
- 60 minutes: Solving 2-3 problems
- 30 minutes: Code review and optimization

Weekly Goals:
- Week 1-2: Master basic operations and patterns
- Week 3-4: Solve medium-level LeetCode problems
- Week 5-6: Tackle hard problems and system design
- Week 7-8: Mock interviews and comprehensive review
```

### Key Success Metrics
- **Speed**: Solve easy problems in 10-15 minutes
- **Accuracy**: 90%+ correctness on first attempt
- **Optimization**: Always consider time/space trade-offs
- **Communication**: Explain approach before coding
- **Testing**: Always test with edge cases

Remember: The key to mastering arrays is consistent practice, understanding patterns, and building intuition for when to apply specific techniques. Focus on writing clean, efficient code while explaining your thought process clearly.

---

## Common Interview Mistakes and How to Avoid Them

### 1. Index Out of Bounds Errors
```java
// ❌ Common Mistake
public static int findElement(int[] arr, int target) {
    for (int i = 0; i <= arr.length; i++) { // Bug: should be i < arr.length
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}

// ✅ Correct Approach
public static int findElement(int[] arr, int target) {
    if (arr == null || arr.length == 0) return -1;
    
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i;
        }
    }
    return -1;
}
```

### 2. Not Handling Edge Cases
```java
// ❌ Common Mistake
public static int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
        map.put(nums[i], i);
    }
    return null; // Should handle this case
}

// ✅ Better Approach
public static int[] twoSum(int[] nums, int target) {
    if (nums == null || nums.length < 2) {
        return new int[]{}; // Return empty array instead of null
    }
    
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[]{map.get(complement), i};
        }
        map.put(nums[i], i);
    }
    return new int[]{}; // No solution found
}
```

### 3. Integer Overflow Issues
```java
// ❌ Potential Overflow
public static int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = (left + right) / 2; // Can overflow!
        // ... rest of the logic
    }
    return -1;
}

// ✅ Overflow-Safe
public static int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2; // Safe from overflow
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

---

## Memory Management and Performance Tips

### 1. Memory-Efficient Array Operations
```java
public class MemoryEfficient {
    
    // In-place array reversal - O(1) space
    public static void reverseInPlace(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            // XOR swap to avoid temporary variable
            arr[left] ^= arr[right];
            arr[right] ^= arr[left];
            arr[left] ^= arr[right];
            left++;
            right--;
        }
    }
    
    // Space-efficient duplicate removal
    public static int removeDuplicatesInPlace(int[] nums) {
        if (nums.length <= 1) return nums.length;
        
        int writeIndex = 1;
        for (int readIndex = 1; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != nums[readIndex - 1]) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }
        return writeIndex;
    }
    
    // Minimize object creation in loops
    public static List<String> processLargeArray(String[] input) {
        List<String> result = new ArrayList<>(input.length); // Pre-size
        StringBuilder sb = new StringBuilder(); // Reuse StringBuilder
        
        for (String item : input) {
            sb.setLength(0); // Clear instead of creating new
            sb.append("processed_").append(item);
            result.add(sb.toString());
        }
        return result;
    }
}
```

### 2. Performance Optimization Techniques
```java
public class PerformanceOptimization {
    
    // Cache-friendly array traversal
    public static long sumMatrix(int[][] matrix) {
        long sum = 0;
        // Row-major order is cache-friendly in Java
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
    
    // Batch processing for large arrays
    public static void processLargeArray(int[] arr) {
        int batchSize = 1000;
        for (int start = 0; start < arr.length; start += batchSize) {
            int end = Math.min(start + batchSize, arr.length);
            processBatch(arr, start, end);
        }
    }
    
    private static void processBatch(int[] arr, int start, int end) {
        // Process batch to improve cache locality
        for (int i = start; i < end; i++) {
            arr[i] = arr[i] * 2; // Example processing
        }
    }
    
    // Use primitive collections for better performance
    public static int findMaxWithPrimitives(int[] arr) {
        if (arr.length == 0) throw new IllegalArgumentException("Empty array");
        
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
```

---

## Advanced Problem Patterns

### 1. Monotonic Stack Pattern
```java
public class MonotonicStack {
    
    // Next Greater Element - O(n)
    public static int[] nextGreaterElements(int[] nums) {
        int[] result = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        
        // Initialize result with -1 (no greater element)
        Arrays.fill(result, -1);
        
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        
        return result;
    }
    
    // Daily Temperatures - O(n)
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        
        return result;
    }
    
    // Largest Rectangle in Histogram - O(n)
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i <= heights.length; i++) {
            int currentHeight = (i == heights.length) ? 0 : heights[i];
            
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            
            stack.push(i);
        }
        
        return maxArea;
    }
}
```

### 2. Union-Find with Arrays
```java
public class UnionFind {
    private int[] parent;
    private int[] rank;
    private int components;
    
    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        components = n;
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }
    
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }
    
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) return false;
        
        // Union by rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        
        components--;
        return true;
    }
    
    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
    
    public int getComponents() {
        return components;
    }
}

// Example usage: Number of Islands
public class NumberOfIslands {
    public static int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind uf = new UnionFind(rows * cols);
        int waterCells = 0;
        
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '0') {
                    waterCells++;
                    continue;
                }
                
                for (int[] dir : directions) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    
                    if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && grid[ni][nj] == '1') {
                        uf.union(i * cols + j, ni * cols + nj);
                    }
                }
            }
        }
        
        return uf.getComponents() - waterCells;
    }
}
```

### 3. Segment Tree with Arrays
```java
public class SegmentTree {
    private int[] tree;
    private int n;
    
    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }
    
    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
    
    public void update(int index, int value) {
        update(0, 0, n - 1, index, value);
    }
    
    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int mid = (start + end) / 2;
            if (index <= mid) {
                update(2 * node + 1, start, mid, index, value);
            } else {
                update(2 * node + 2, mid + 1, end, index, value);
            }
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }
    
    public int query(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }
    
    private int query(int node, int start, int end, int left, int right) {
        if (right < start || left > end) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        }
        
        int mid = (start + end) / 2;
        return query(2 * node + 1, start, mid, left, right) +
               query(2 * node + 2, mid + 1, end, left, right);
    }
}
```

---

## Interview Simulation Questions

### 1. Design Questions
**Q: Design a data structure that supports insert, delete, and getRandom in O(1) time.**

```java
public class RandomizedSet {
    private List<Integer> values;
    private Map<Integer, Integer> indices;
    private Random random;
    
    public RandomizedSet() {
        values = new ArrayList<>();
        indices = new HashMap<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        if (indices.containsKey(val)) {
            return false;
        }
        
        indices.put(val, values.size());
        values.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if (!indices.containsKey(val)) {
            return false;
        }
        
        int index = indices.get(val);
        int lastElement = values.get(values.size() - 1);
        
        // Move last element to index of element to remove
        values.set(index, lastElement);
        indices.put(lastElement, index);
        
        // Remove last element
        values.remove(values.size() - 1);
        indices.remove(val);
        
        return true;
    }
    
    public int getRandom() {
        return values.get(random.nextInt(values.size()));
    }
}
```

### 2. Complex Array Manipulation
**Q: Given an array of meeting intervals, merge overlapping intervals**

```java
public class MeetingRooms {
    
    public static class Interval {
        int start, end;
        
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    // Can attend all meetings
    public static boolean canAttendMeetings(Interval[] intervals) {
        if (intervals.length <= 1) return true;
        
        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start < intervals[i - 1].end) {
                return false;
            }
        }
        
        return true;
    }
    
    // Minimum meeting rooms required
    public static int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i].start;
            ends[i] = intervals[i].end;
        }
        
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        int rooms = 0;
        int endPointer = 0;
        
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[endPointer]) {
                rooms++;
            } else {
                endPointer++;
            }
        }
        
        return rooms;
    }
    
    // Merge overlapping intervals
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals;
        
        intervals.sort((a, b) -> a.start - b.start);
        List<Interval> merged = new ArrayList<>();
        
        for (Interval interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1).end < interval.start) {
                merged.add(interval);
            } else {
                merged.get(merged.size() - 1).end = 
                    Math.max(merged.get(merged.size() - 1).end, interval.end);
            }
        }
        
        return merged;
    }
}
```

---

## Final Mastery Checklist

### Technical Skills ✅
- [ ] Can implement all basic array operations from scratch
- [ ] Understand time/space complexity of all operations
- [ ] Master two-pointer technique for various problems
- [ ] Implement sliding window for optimization problems
- [ ] Use prefix sums for range query problems
- [ ] Apply cyclic sort for missing number problems
- [ ] Understand and implement advanced patterns (monotonic stack, etc.)
- [ ] Solve matrix problems efficiently
- [ ] Handle edge cases and null checks consistently

### Problem-Solving Skills ✅
- [ ] Can solve LeetCode Easy problems in 10-15 minutes
- [ ] Can solve LeetCode Medium problems in 20-30 minutes
- [ ] Can approach LeetCode Hard problems systematically
- [ ] Can optimize brute force solutions to efficient ones
- [ ] Can explain trade-offs between different approaches
- [ ] Can handle follow-up questions confidently

### Interview Skills ✅
- [ ] Can explain approach before coding
- [ ] Can write clean, readable code under pressure
- [ ] Can test code with edge cases
- [ ] Can optimize code when asked
- [ ] Can discuss alternative approaches
- [ ] Can estimate time/space complexity accurately

### Recommended Study Schedule
```
Week 1-2: Master fundamentals and basic operations
Week 3-4: Learn and practice essential patterns
Week 5-6: Solve medium-level LeetCode problems
Week 7-8: Tackle advanced problems and system design
Week 9-10: Mock interviews and comprehensive review
```

### Daily Practice (2-3 hours)
- **30 minutes**: Review concepts and patterns
- **90 minutes**: Solve 3-4 problems of varying difficulty
- **30 minutes**: Analyze solutions and optimize
- **30 minutes**: Review mistakes and learn from them

Remember: Consistency is key! Practice a little bit every day rather than cramming. Focus on understanding patterns rather than memorizing solutions. Good luck with your coding interviews! 🚀