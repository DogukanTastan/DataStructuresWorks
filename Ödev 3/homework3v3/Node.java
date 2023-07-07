package homework3v3;

public class Node<E> {
	
    E data;
    boolean isDeleted;
    Node<E> next;

    public Node(E data) {
    	
        this.data = data;
        this.isDeleted = false;
        this.next = null;
    }
}