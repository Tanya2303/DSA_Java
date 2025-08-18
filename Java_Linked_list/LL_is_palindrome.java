package Java_Linked_list;

public class LL_is_palindrome {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;

    public Node FideMid(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // Returns the middle node
    }
    public boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true; // Empty or single node list is a palindrome
        }
        Node mid = FideMid(head); // Find the middle node
        Node prev = null;
        Node current = mid;
        Node next = null;
        // Reverse the second half of the list
        while (current != null) {
            next = current.next; // Store next node
            current.next = prev; // Reverse the link
            prev = current; // Move prev to current
            current = next; // Move to next node
        }
        Node right = prev; // This is the head of the reversed second half
        Node left = head; // Start from the head of the first half
        // Compare both halves
        while (right != null) {
            if (left.data != right.data) {
                return false; // Not a palindrome
            }
            left = left.next; // Move to next node in the first half
            right = right.next; // Move to next node in the reversed second half
        }
        return true; // If all nodes matched, it's a palindrome

    }

    public static void main(String[] args) {
        // Creating a linked list for testing
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);

        LL_is_palindrome ll = new LL_is_palindrome();
        boolean result = ll.isPalindrome(head);
        if (result) {
            System.out.println("The linked list is a palindrome.");
        } else {
            System.out.println("The linked list is not a palindrome.");
        }
    }

}