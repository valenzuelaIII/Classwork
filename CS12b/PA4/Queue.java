//-----------------------------------------------------------------------------
// Queue.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// August 9, 2017
// PA4
// implements Queue ADT from macros specified on QueueInterface.java utilizing singly linked lists
//-----------------------------------------------------------------------------

public class Queue implements QueueInterface{
    
    // Node construction for Queue ADT's singly linked list
    // -------------------------------------------------------------------------
    
    // private inner Node class implemented as a singly linked list
    private class Node{
        Object objects;
        Node next;
        
        Node(Object objects){
            this.objects = objects;
            next = null;
        }
    }
    // constructor and field declarations
    private Node head;
    private int numItems;
    public Queue(){
        head = null;
        numItems = 0;
    }
    
    
    // ADT operations using macros from QueueInterface.java
    // -------------------------------------------------------------------------
    
    // isEmpty()
    // pre: none
    // post: returns true if this Queue is empty, false otherwise
    public boolean isEmpty(){
        return(numItems == 0);
    }

    // length()
    // pre: none
    // post: returns the length of this Queue.
    public int length(){
        return numItems;
    }

    // enqueue()
    // adds newItem to back of this Queue
    // pre: none
    // post: !isEmpty()
    // reference: enqueue() in IntegerQueue.java and insert() in Dictionary.java from PA3
    public void enqueue(Object newItem){
        if(head==null){
            head = new Node(newItem);
        }else{
            Node N = head;
            for( ; N.next != null ; N = N.next){ //loop traverses list until end of list
            }
            N.next = new Node(newItem); //adds newItem to the end of the list
        }
        numItems++;
    }

    // dequeue()
    // deletes and returns item from front of this Queue
    // pre: !isEmpty()
    // post: this Queue will have one fewer element
    // reference: dequeue() in IntegerQueue.java
    public Object dequeue() throws QueueEmptyException{
        if(numItems==0){
            throw new QueueEmptyException("cannot dequeue() empty queue");
        }else{
            Node N = head;
            head = N.next;
            numItems--;
            return head.objects;
        }
    }

    // peek()
    // pre: !isEmpty()
    // post: returns item at front of Queue
    // reference: peek() in IntegerQueue.java
    public Object peek() throws QueueEmptyException{
        if(numItems==0){
            throw new QueueEmptyException("cannot peek() empty queue");
        }else{
            return head.objects;
        }
    }

    // dequeueAll()
    // sets this Queue to the empty state
    // pre: !isEmpty()
    // post: isEmpty()
    // reference: dequeueAll() in IntegerQueue.java and makeEmpty() in Dictionary.java from PA3
    public void dequeueAll() throws QueueEmptyException{
        if(numItems==0){
            throw new QueueEmptyException("cannot dequeueAll() empty queue");
        }else{
            head = null;
            numItems = 0;
        }
    }

    // toString()
    // overrides Object's toString() method
    // reference: enqueue() in IntegerQueue.java and insert() in Dictionary.java from PA3
    public String toString(){
        String s = "";
        Node N = head;
        for( ; N != null; N = N.next){
            s += N.objects + " ";
        }
        return s;
    }

}