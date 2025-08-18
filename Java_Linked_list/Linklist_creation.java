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
    public static int size;

    public void addFritst(int data) {
        Node newNode = new Node(data);
        size++;
        if(head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if(head == null){
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void printList() {
        if(head == null) {
            System.out.println("List is empty");
            return;
        }
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public void addMid(int data, int index){
        Node newNode = new Node(data);
        size++;
        if (index == 0) {  // insert at head
            newNode.next = head;
            head = newNode;
            return;
        }
        Node temp = head;
        int i = 0;
        while(i < index - 1 && temp != null) {
            temp = temp.next;
            i++;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public int RemoveFrist(){
        if(size == 0){
            System.out.println("List is empty");
            return -1;
        }else if(size == 1){
            int val = head.data;
            head = tail = null;
            size--;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public static int RemoveLast(){
        if(size == 0){
            System.out.println("List is empty");
            return -1;
        }else if(size == 1){
            int val = head.data;
            head = tail = null;
            size--;
            return val;
        }
        Node temp = head;
        for(int i = 0; i < size - 2; i++) {
            temp = temp.next;
        }
        int val = temp.next.data;
        temp.next = null;
        tail = temp;
        size--;
        return val; 
    }

    public static void main(String[] args) {
        Linklist_creation ll = new Linklist_creation();
        ll.printList(); // Output: List is empty
        ll.addFritst(2);
        ll.addFritst(1);
        ll.addLast(3);
        ll.addLast(4);
        System.out.println("Linked List:");
        ll.printList(); // Output: 1 2 3 4
        ll.addMid(5, 2);
        System.out.println("\nAfter adding 5 at index 2:");
        ll.RemoveFrist(); // Removes the first element (1)
        ll.printList(); // Output: 1 2 5 3 4
        ll.RemoveLast(); // Removes the last element (4)
        System.out.println("\nAfter removing the last element:");
        ll.printList(); // Output: 1 2 5 3
        System.out.println("\nSize of linked list: " + size); // Output: Size of linked list: 5

    }

}