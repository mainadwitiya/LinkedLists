public class MyLinkedList {
    class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head, tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return (this.getSize() == 0);
    }

    public void addFirst(int data) {
        Node newNode = new Node(data, head);

        if(isEmpty()) {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    public void addLast(int data) {
        Node newNode = new Node(data, null);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public Node removeFirst() throws Exception{
        if (isEmpty()) {
            throw new Exception("List is already empty");
        }
        Node removed = head;
        if(getSize() == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        return removed;
    }

    public Node removeLast() throws Exception{
        if(isEmpty()) {
            throw ne
        }
    }
}
