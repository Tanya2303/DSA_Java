package Java_Linked_list;

public class Search_in_LL_recursive {
    public static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    public static Node head;
    
    public static int helper(Node head, int key) {
        if (head == null) {
            return -1; // Element not found
        }
        if (head.data == key) {
            return 0; // Element found at current index
        }
        int index = helper(head.next, key);
        if(index == -1) {
            return -1; // Element not found in the rest of the list
        }
        return index + 1; // Increment index for the current node
    }
    public static boolean search(Node head, int key) {
        int index = helper(head, key);
        if (index != -1) {
            System.out.println("Element found at index: " + index);
            return true;
        } else {
            System.out.println("Element not found");
            return false;
        }
    }

    public static void main(String[] args) {
        // Creating a linked list for testing
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        // Searching for elements
        search(head, 3); // Output: Element found at index: 2
        search(head, 5); // Output: Element not found
    }
    
}
