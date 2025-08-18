package Java_Linked_list;

public class Remove_nth_end {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;

    public static int size() {
        int size = 0;
        Node temp = head;
        while (temp != null) {
            temp = temp.next;
            size++;
        }
        return size;
    }
    public static Node removeNthFromEnd(Node head, int n) {
        int size = size();
        if (n == size) {
            head = head.next; // Remove the first node
            return head;
        }
        int i = 1;
        int iToFind = size - n;
        Node temp = head;
        while (temp != null && i < iToFind) {
            temp = temp.next;
            i++;
        }
        temp.next = temp.next.next; // Remove the nth node from the end
        return head;
    }

    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Creating a linked list for testing
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.println("Original List:");
        printList(head); // Output: 1->2->3->4->5->null

        int n = 2; // Remove the 2nd node from the end
        head = removeNthFromEnd(head, n);

        System.out.println("List after removing " + n + "th node from the end:");
        printList(head); // Output: 1->2->3->5->null
    }

    
}
