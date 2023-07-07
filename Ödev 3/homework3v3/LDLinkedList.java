package homework3v3;
import java.util.AbstractList;
import java.util.List;

public class LDLinkedList<E> extends AbstractList<E> implements List<E> {
	
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private int oldIndex=0;

    public LDLinkedList() {
    	
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
    	
        checkIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    public boolean getBool(int index) {
        checkIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.isDeleted;
    }
    
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public int size() {
    	
        return size;
    }
    

    @Override
    public E set(int index, E element) {
    	
        checkIndex(index);
        
        Node<E> iter = head;
        for (int i = 0; i < index; i++) {
        	iter = iter.next;
        }
        E oldValue = iter.data;
        iter.data = element;
        return oldValue;
    }

   /* @Override
    public void add(int index, E element) {
    	
    	Node<E> newNode= new Node<E>(element);
    	
        if (head == null) {
        	head = new Node<E>(element);
        	tail = head;
        	size++;
        }
        
        else if (index == 0) {
           newNode.next=head;
           head=newNode;
           size++;
        } 
        else if(index == size){
        	tail.next = newNode;
        	tail = newNode;
        	size++;
        }
        else {
            Node<E> iter = head;
            
            for (int i = 0; i < index - 1; i++) {
                iter = iter.next;
            }
            newNode.next= iter.next;
            iter.next=newNode;
            size++;
        }
    }*/
    
    @Override
    public boolean add(E element) {
    	Node<E> newNode= new Node<E>(element);
    	
    	if (head == null) {
        	head = new Node<E>(element);
        	tail = head;
        	size++;
        	return true;
        }
        
        else {
        	tail.next = newNode;
        	tail = newNode;
        	size++;
        	return true;
        }
    }
    
   /* public void show()
    {
    	 System.out.println("Size:"+ size);
    	 Node<E> iter = head;
         for (int i = 0; i < size; i++) {
        	 System.out.println(iter.data + " " + iter.isDeleted);
         	iter = iter.next;
         }
    } */
    
    @Override
    public E remove(int index) {  // management function
    	
       	checkIndex(index);
    	boolean flag=false;
    	int oldIndex=0;
    	
    	Node<E> current = head;
    	Node<E> current2 = head;
    	
    	while(current!= null) {
    		if(current.isDeleted == true) {
    			flag=true;
    			break;
    		}
    		oldIndex++;
    		current=current.next;
    	}
    	if(!flag) {
    		for (int i = 0; i < index; i++) 
    			current2 = current2.next;
    		current2.isDeleted=true;
    	}
    	if(flag) {
    		if(oldIndex>index) {
    			Myremove(oldIndex);
        		Myremove(index);
    		}
    		else {
    			Myremove(oldIndex);
        		Myremove(index-1);
    		}
    		
    	}
		return null;
    }
    
    public E Myremove(int index) {
    	
    	checkIndex(index);
	    E removedValue;
	    
        Node<E> iter = head;
        
        if (head == tail) {
    		removedValue=head.data;
        	head = null;
        	tail = null;
        	size--;
        	return removedValue;
        }
        else {
        	if(index == 0) {
    			removedValue=head.data;
        		Node<E> oldhead  = head;
				head=head.next;
				oldhead.next = null;
				size--;
				return removedValue;
			}
        	if(index == size-1) {
    			for (int i = 0; i < index - 1; i++) {
                    iter = iter.next;
                }
        		 removedValue=iter.next.data;
        		 iter.next=null;
        		 tail=iter;
        		 size--;
        		 return removedValue;
        	}
        	else {
    			 for (int i = 0; i < index - 1; i++) {
                     iter = iter.next;
                 }
                 removedValue=iter.next.data;
                 Node<E> temp = iter.next;
                 iter.next = iter.next.next;
                 temp.next = null;
                 size--;
                 return removedValue;
        	}  
        }    
    }
}
