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

    public void addAt(int data, int index) throws Exception {
        if (index < 0 || index > this.getSize()) {
            throw new Exception("Invalid index");
        }

        if (index == 0) {
            this.addFirst(data);
        } else if (index == this.size) {
            this.addLast(data);
        } else {
            Node prevNode = this.getNodeAt(index - 1);
            Node next = prevNode.next;
            Node newNode = new Node(data, next);
            prevNode.next = newNode;
            this.size++;
        }
    }

    public int getFirst() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("List empty");
        }

        return this.head.data;
    }


    public int getLast() throws Exception {
        if (this.isEmpty()) {
            throw new Exception("List empty");
        }

        return this.tail.data;
    }

    public int getAt(int index) throws Exception {
        if (index < 0 || index >= this.size) {
            throw new Exception("Invalid index");
        }

        if (index == 0) {
            return this.getFirst();
        } else if (index == size - 1) {
            return this.getLast();
        } else {
            return this.getNodeAt(index).data;
        }
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

    public int removeAt(int index) throws Exception {
        if (index < 0 || index >= this.size) {
            throw new Exception("Invalid index");
        }

        if (index == 0) {
            return removeFirst().data;
        } else if (index == this.size - 1) {
            return removeLast().data;
        } else {
            Node prev = this.getNodeAt(index - 1);
            Node curr = prev.next;
            Node next = curr.next;

            prev.next = next;
            size--;
            return curr.data;
        }
    }

    public Node getNodeAt(int index) throws Exception {
        if (index < 0 || index >= this.getSize())
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
        while (currentNode != null) {
            System.out.print(currentNode.data + "->");
            currentNode = currentNode.next;
        }
        System.out.println("-------------------------");
    }

    public void reverseDataIterative() throws Exception {
        int leftIndex = 0;
        int rightIndex = this.getSize() - 1;

        while (leftIndex <= rightIndex) {
            Node left = this.getNodeAt(leftIndex);
            Node right = this.getNodeAt(rightIndex);
            int temp;
            temp = left.data;
            left.data = right.data;
            right.data = temp;

            leftIndex++;
            rightIndex--;
        }
    }

    public void reversePointerIterative() {
        Node prev = this.head;
        Node curr = prev.next;
        while (curr != null) {
            Node tempPrev = prev;
            Node tempCurr = curr;

            prev = curr;
            curr = curr.next;
            tempCurr.next = tempPrev;
        }
        Node temp = this.head;
        this.head = this.tail;
        this.tail = temp;
    }

    public void reversePointerRecursive() {
        this.reversePointerRecursive(this.head);

        Node temp = this.head;
        this.head = this.tail;
        this.tail = temp;

        this.tail.next = null;
    }

    private void reversePointerRecursive(Node node) {
        if (node == this.tail) {
            return;
        }
        reversePointerRecursive(node.next);
        node.next.next = node;
    }

    public void reverseDataRecursive() {
        HeapMover left = new HeapMover(this.head);
        this.reverseDataRecursive(left, this.head, 0);
    }


    private void reverseDataRecursive(HeapMover left, Node right, int floor) {
        if (right == null) { //hit floor value, when right == null
            return;
        }
        // recursive call shifting right by one node and increasing floor by 1
        reverseDataRecursive(left, right.next, floor + 1);
        if (floor >= this.getSize() / 2) { // Start Swapping left and right when in the middle of the list
            int temp = left.node.data; // Swap
            left.node.data = right.data; // Swap
            right.data = temp; // Swap
            left.node = left.node.next;
        }
    }


    // Helper class for ReverseDataRecursive

    private class HeapMover {
        Node node;

        public HeapMover(Node node) {
            this.node = node;
        }
    }

    public void reverseInGroup(int groupLength) throws Exception {
        if (this.getSize() < groupLength) {
            throw new Exception("List is too small");
        }

        MyLinkedList finalList = new MyLinkedList();
        MyLinkedList tempList = new MyLinkedList();

        Node currentNode = this.head;
        while (currentNode != null) {
            for (int i = 0; i < groupLength; i++) {
                tempList.addFirst(currentNode.data);
                currentNode = currentNode.next;
            }

            if (finalList.isEmpty()) {
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

    public boolean isPalindrome() {
        HeapMover left = new HeapMover(this.head);
        return this.isPalindrome(left, this.head);
    }

    private boolean isPalindrome(HeapMover left, Node right) {
        if (right == null) {
            return true;
        }
        boolean isPalin = this.isPalindrome(left, right.next);

        if (isPalin == false) {
            return false;
        } else {
            if (left.node.data == right.data) {
                left.node = left.node.next;
                return true;
            } else {
                return false;
            }
        }
    }

    public Node mid() {
        return mid(this.head);
    }

    private Node mid(Node root) {
        Node fast = this.head;
        Node slow = this.head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private void fold(HeapMover left, Node right, int floor) {
        if (right == null) {
            return;
        }

        this.fold(left, right.next, floor + 1);
        if (floor > this.getSize() / 2) {
            Node origLeftNode = left.node.next;
            left.node.next = right;
            right.next = origLeftNode;
            left.node = origLeftNode;
        } else if (floor == this.getSize() / 2) {
            this.tail = right;
            this.tail.next = null;
        }
    }

    public int kthFromEnd(int k) {
        return kthNodeFromEnd(k);
    }

    private int kthNodeFromEnd(int k) {
        Node slow = this.head;
        Node fast = this.head;

        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow.data;
    }

    public int find(int key) {
        int rv = this.find(key, this.head);
        return rv;
    }

    private int find(int key, Node node) {
        if (node == null) {
            return -1;
        }
        if (node.data == key) {
            return 0;
        } else {
            int rv = find(key, node.next);
            if (rv == -1) {
                return rv;
            } else {
                if (rv == -1) {
                    return rv;
                } else {
                    return rv + 1;
                }
            }
        }
    }

    public MyLinkedList merge(MyLinkedList other) {
        Node thisTemp = this.head;
        Node otherTemp = other.head;

        MyLinkedList returnList = new MyLinkedList();

        while (thisTemp != null && otherTemp != null) {
            if (thisTemp.data > otherTemp.data) {
                returnList.addLast(thisTemp.data);
                thisTemp = thisTemp.next;
            } else {
                returnList.addLast(otherTemp.data);
                otherTemp = otherTemp.next;
            }
        }

        while (thisTemp != null) {
            returnList.addLast(thisTemp.data);
            thisTemp = thisTemp.next;
        }

        while (otherTemp != null) {
            returnList.addLast(otherTemp.data);
            otherTemp = otherTemp.next;
        }
        return returnList;
    }

    public void mergeSort() {
        MyLinkedList sorted = mergeSortHelper();
        this.head = sorted.head;
        this.tail = sorted.tail;
    }

    private MyLinkedList mergeSortHelper() {
        if (this.getSize() == 1) {
            return this;
        }

        Node midNode = this.mid();
        Node midNext = midNode.next;

        MyLinkedList firstHalf = new MyLinkedList();
        firstHalf.head = this.head;
        firstHalf.tail = midNode;
        firstHalf.tail.next = null;
        firstHalf.size = (this.getSize() + 1) / 2;

        MyLinkedList secondHalf = new MyLinkedList();
        secondHalf.head = midNext;
        secondHalf.tail = this.tail;
        secondHalf.size = this.getSize() / 2;

        firstHalf = firstHalf.mergeSortHelper();
        secondHalf = secondHalf.mergeSortHelper();

        return (firstHalf.merge(secondHalf));
    }

    public void oddEven() throws Exception {
        MyLinkedList odd = new MyLinkedList();
        MyLinkedList even = new MyLinkedList();

        while (this.getSize() != 0) {
            Node removed = this.removeFirst();
            if (removed.data % 2 == 0) {
                even.addLast(removed.data);
            } else {
                odd.addLast(removed.data);
            }
        }

        if (odd.getSize() == 0) {
            this.head = even.head;
            this.tail = even.tail;
            this.size = even.size;
        } else if (even.getSize() == 0) {
            this.head = odd.tail;
            this.tail = odd.tail;
            this.size = odd.getSize();
        } else {
            this.head = odd.head;
            this.tail = even.tail;
            odd.tail.next = even.head;
            this.size = odd.getSize() + even.getSize();
        }
    }

    // remove duplicates from a sorted LinkedList
    public void removeDuplicates() throws Exception {
        MyLinkedList newList = new MyLinkedList();

        Node removed = this.removeFirst();
        newList.addFirst(removed.data);
        while (!this.isEmpty()) {
            removed = this.removeFirst();
            if (newList.tail.data != removed.data) {
                newList.addLast(removed.data);
            }
        }

        this.head = newList.head;
        this.tail = newList.tail;
        this.size = newList.size;
    }

    //delete m nodes after n nodes
    public void deleteMAfterN(int m, int n) throws Exception {
        // if the list is too small
        if (m + n > this.getSize()) {
            throw new Exception("List is too small");
        }

        Node nthNode = this.head;           // the node after which nodes will be deleted
        Node mthNode = this.head;           // the node until which nodes are to be deleted
        for (int i = 0; i < n + m; i++) {
            if (i < n - 1) {                     // increment nthNode n times
                nthNode = nthNode.next;
            }
            mthNode = mthNode.next;
        }
        if (n == 0) {                             // if n=0 set head to mthNode
            this.head = mthNode;
        } else {
            nthNode.next = mthNode;             // set the next of nthNode to mthNode effectively
        }                                           // "removing" m nodes in the process
        this.size = this.getSize() - m;
    }                                       // fin


    public void removeEveryKthNode(int k) throws Exception{
        MyLinkedList newList = new MyLinkedList();
        int count = 1;

        while (!this.isEmpty()) {
            Node removed = this.removeFirst();
            if (count % k != 0) {
                newList.addLast(removed.data);
            }
            count++;
        }
        this.head = newList.head;
        this.tail = newList.tail;
        this.size = newList.size;
    }

    public void sort012Links() throws Exception {
        MyLinkedList zeroList = new MyLinkedList();
        MyLinkedList oneList = new MyLinkedList();
        MyLinkedList twoList = new MyLinkedList();
        while (!this.isEmpty()) {
            Node removed = this.removeFirst();
            if (removed.data == 0) {
                zeroList.addLast(removed.data);
            } else if (removed.data == 1) {
                oneList.addLast(removed.data);
            } else {
                zeroList.addLast(removed.data);
            }
        }

        this.head = zeroList.head;
        zeroList.tail.next = oneList.head;
        oneList.tail.next = twoList.head;
        this.tail = twoList.tail;
    }

    public void addOne() {
        int carry = addOne(this.head);
        if (carry != 0) {
            this.addLast(carry);
        }
    }

    private int addOne(Node node) {
        if (node == null) {
            return 1;
        }

        int carry = this.addOne(node.next);
        node.data = node.data + carry;
        node.data = node.data%10;
        return (carry/10);
    }

    class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    
    // edit this function to make a loop inside a Linked List, according to your preferences
    public void makeLoop() {
        this.tail.next = this.head.next.next;
    }

    // This function finds and removes loops in a LinkedList
    public void findAndRemoveLoop() {
        LoopPair loopPair = this.hasLoop(); // check if this List has a loop
        if (loopPair.hasLoop) {             // if there is a loop
            System.out.println("Loop found!");  // print loop found
            this.removeLoop(loopPair.nodeAtK);  // remove the loop
        } else {                            // Else print No Loop Found
            System.out.println("No loop found!");
        }
    }

    // Helper class to find and remove loops
    private class LoopPair {
        Node nodeAtK;     // this will store the position where fast and slow pointers meet to confirm there is a loop
        boolean hasLoop;  // this boolean variable stores whether or not the LinkedList has a loop
    }

    private LoopPair hasLoop() {// returns an object of LoopPair so that we can also get the node at K from start of loop
        Node slow = this.head;  // initialize both slow and fast pointers with this.head
        Node fast = this.head;

        LoopPair myPair = new LoopPair(); // make a LoopPair object

        while (fast.next != null && fast.next.next != null) {   // if either hits null, it means the LinkedList ends and there's no loop
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {         // if they meet, it means fast never hit null and there is a loop
                myPair.hasLoop = true;  // set the boolean to true
                myPair.nodeAtK = slow;  // return the position of either of fast/slow as this is at K distance from the start of loop
                return myPair;          // return the object
            }
        }

        myPair.hasLoop = false;         // if loop ends, it means a pointer hit null, so there is no loop
        return myPair;                  // set the boolean to false and return the object
    }

    // if 'm' is the distance from head to start of loop, 'n' is the length of the loop, 'k' is the distance from start
    // of the loop, to the point where fast and slow pointers meet.
    // we start two pointers, one from the head, another from 'k', and run a loop till they meet, they will always meet
    // at the last node of the loop, because  (m + k) is a multiple of 'n'
    private void removeLoop(Node nodeAtK) {
        Node tempNode = this.head;
        while (tempNode.next != nodeAtK.next) {
            tempNode = tempNode.next;
            nodeAtK = nodeAtK.next;
        }
        // because they will meet at the last Node of the loop, set the next pointer of this node as null
        nodeAtK.next = null;
        System.out.println("Loop removed!");
        this.tail = nodeAtK;
    }
}
