public class MyLinkedList {
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

        if (isEmpty()) {
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

    public Node removeFirst() throws Exception {
        if (isEmpty()) {
            throw new Exception("List is already empty");
        }
        Node removed = head;
        if (this.getSize() == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
        }
        this.size--;
        return removed;
    }

    public Node removeLast() throws Exception {
        if (isEmpty()) {
            throw new Exception("List is already empty");
        }

        Node removed = this.tail;
        if (this.getSize() == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node secondLast = getNodeAt(this.getSize() - 2);
            secondLast.next = null;
            this.tail = secondLast;
        }
        size--;
        return removed;
    }

    public Node getNodeAt(int index) throws Exception {
        if (index < 0 || index > this.getSize())
            throw new Exception("Invalid Index");

        Node returnNode = this.head;
        int count = 0;
        while (count < index) {
            returnNode = returnNode.next;
            count++;
        }
        return returnNode;
    }

    public void display() {
        Node currentNode = this.head;
        while(currentNode != null) {
            System.out.print(currentNode.data + "->");
            currentNode = currentNode.next;
        }
        System.out.println("-------------------------");
    }

    public void reverseInGroup(int groupLength) throws Exception {
        if (this.getSize() < groupLength) {
            throw new Exception("List is too small");
        }

        MyLinkedList finalList= new MyLinkedList();
        MyLinkedList tempList = new MyLinkedList();

        Node currentNode = this.head;
        while(currentNode != null) {
            for (int i=0; i< groupLength; i++) {
                tempList.addFirst(currentNode.data);
                currentNode = currentNode.next;
            }

            if(finalList.isEmpty()) {
                finalList = tempList;
                tempList = new MyLinkedList();
            } else {
                finalList.tail.next = tempList.head;
                finalList.tail = tempList.tail;
            }
        }
        finalList.size = this.getSize();
        this.head = finalList.head;
        this.tail = finalList.tail;
        this.tail.next = null;
    }



    class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
