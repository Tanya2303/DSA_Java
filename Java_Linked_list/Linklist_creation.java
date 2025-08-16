package Java_Linked_list;

class Linklist_creation{
    public static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;

    public void addFritst(int data) {
        Node newNode = new Node(data);
        if(head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        if(head == null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void printList() {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Linklist_creation ll = new Linklist_creation();
        ll.addFritst(2);
        ll.addFritst(1);
        ll.addLast(3);
        ll.addLast(4);
        System.out.println("Linked List:");
        ll.printList(); // Output: 1 2 3 4

    }

}