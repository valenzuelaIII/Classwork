//-----------------------------------------------------------------------------
// List.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// August 15, 2017
// lab6
// List ADT that implements ListInterface's specified operations and is capable of operating with 
// any data type. Linked List version of IntegerListADT was used as starting point.
//-----------------------------------------------------------------------------

//annotation
@SuppressWarnings("overrides")
public class List<T> implements ListInterface<T>{
//Node operations
//-----------------------------------------------------------------------------
    //Private Inner Node class
    //type: singly linked list with head reference
    private class Node{
        T item;
        Node next;
        
        Node(T x){
            item = x;
            next = null;
        }
    }
    // Fields and constructor for List class
    private Node head;
    private int numItems;
    
    public List(){
        head = null;
        numItems = 0;
    }
    
// Helper function
//-----------------------------------------------------------------------------
    // find()
    // pre: none
    // post: returns reference to node at specified index if it exits.
    // reference: find() in IntegerList.java (linked list) and findKey() Dictionary.java from PA3
    private Node find(int index){
        Node N = head;
        for (int i = 1; i < index; i++){
            N = N.next;
            }
        return N;
    }
    
// ADT operations not specified in ListInterface.java
//-----------------------------------------------------------------------------    
    //toString()
    //pre: none
    //post: returns converts items in list to string
    //reference: toString() in Dictionary.java from PA3
    public String toString(){
        StringBuffer sb = new StringBuffer();
        Node N = head;
        for( ; N!=null; N=N.next){
            sb.append(N.item).append(" ");
        }
        return new String(sb);
    }
    
    //equals()
    //pre: object x and this.list are of same class
    //post: returns true if lists are equivalent, otherwise function returns false
    //reference: equals() in lab6 manual draft 2
    @SuppressWarnings("unchecked")
    public boolean equals(Object x){
        boolean eq = false; 
        List<T> A = null; 
        Node N = null;
        Node temp = null;
        //comparison
        if(x.getClass() == this.getClass()){ //compares runtime classes of x and this
            A = (List<T>)x;
            eq = (this.numItems == A.numItems); //tests first elements
            N = this.head;
            temp = A.head; 
            //for loop traverses both lists and compares each item
            for(;N != null && eq == true; N = N.next, temp = temp.next){
                eq = (N.item == temp.item);
            }
        }
        return eq; 
    }
    
// ADT operations specified in ListInterface.java
//-----------------------------------------------------------------------------
    // isEmpty()
    // pre: none
    // post: returns true if this List is empty, false otherwise
    public boolean isEmpty(){
        return (numItems == 0);
    }

    // size()
    // pre: none
    // post: returns the number of elements in this List
    public int size(){
        return (numItems);
    }

    // get()
    // pre: 1 <= index <= size()
    // post: returns item at position index
    public T get(int index) throws ListIndexOutOfBoundsException{
        if(index < 1 || index > size()){
            throw new ListIndexOutOfBoundsException("get(): invalid index: " + index);
        }else{ //finds node at specified index and returns item for corresponding node
            Node N = find(index);
            return N.item;
        }   
    }

    // add()
    // inserts newItem in this List at position index
    // pre: 1 <= index <= size()+1
    // post: !isEmpty(), items to the right of newItem are renumbered
    public void add(int index, T newItem) throws ListIndexOutOfBoundsException{
        if(index < 1 || index > numItems+1){
            throw new ListIndexOutOfBoundsException("add(): invalid index: " + index);
        }
        if(index == 1){ //if item is inserted at top of list
            Node N = new Node(newItem);
            N.next = head;
            head = N;
        }
        else{ //if item is inserted anywhere else in the list
            Node P = find(index-1);
            Node O = P.next;
            P.next = new Node(newItem);
            P = P.next;
            P.next = O;
        }
        numItems++;
    }

    // remove()
    // deletes item from position index
    // pre: 1 <= index <= size()
    // post: items to the right of deleted item are renumbered
    public void remove(int index) throws ListIndexOutOfBoundsException{
        if(index < 1 || index > numItems){
            throw new ListIndexOutOfBoundsException("remove(): invalid index: " + index);
        }else if(index == 1){ //deletion from front of list
            Node N = head;
            head = head.next;
            N.next = null;
        }else{ //deletion from any other point in the list 
            Node N = find(index-1);
            Node temp = N.next;
            N.next = temp.next;
            temp.next = null;
        }
        numItems--;
    }

    // removeAll()
    // pre: none
    // post: isEmpty()
    public void removeAll(){ //re-initializes list to empty
        head = null;
        numItems = 0;
    }

}