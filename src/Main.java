public class Main {
    public static void main(String[] args) throws Exception {
        MyLinkedList ll = new MyLinkedList();
        ll.display();
        for(int i=10; i<20; i++) {
            ll.addLast(i);
        }
        ll.display();
        ll.reversePointerRecursive();
        ll.display();
        //ll.deleteMAfterN(2,0);
    }
}
