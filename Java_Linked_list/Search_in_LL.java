package Java_Linked_list;

public class Search_in_LL {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;

    public static boolean search(Node head, int key) {
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.data == key) {
                System.out.println("Element found at index: " + i);
                return true;
            }
            temp = temp.next;
            i++;
        }
        System.out.println("Element not found");
        return false;
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
    
