//-----------------------------------------------------------------------------
// Dictionary.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// July 23, 2017
// PA3
//-----------------------------------------------------------------------------

// Pseudocode:
// 1. construct Node
// 2. Set fields for Dictionary
// 3. construct for the Dictionary class
// 4. private helper function
// 5. ADT Operations
// Reference: IntegerList.java

public class Dictionary implements DictionaryInterface{
    
    //private inner Node class of form Node(String key, String value)
    //type: singly linked list with head reference
    private class Node{
        String k;
        String v;
        Node next;
        Node(String key, String value){
            this.k = key;
            this.v = value;
            next = null;
        }
    }
    //Fields and constructor for Dictionary class
    private Node head;
    private int numItems;
    public Dictionary(){
        head = null;
        numItems = 0;
    }
    
    //returns reference to node "key" if it exits. null otherwise
    // reference: find() in IntegerList.java
    private Node findKey(String key){
        Node N = head;
        for (; N != null; N = N.next){
            if(N.k.equals(key)){
                break;
            }
        }
        return N;//returns null if no key exists
    }
    
    // isEmpty()
    // pre: none
    // same as IntegerList.java isEmpty()
    // returns true if this Dictionary is empty, false otherwise
    public boolean isEmpty(){
        return(numItems == 0);
    }

    // size()
    // pre: none
    // same as IntegerList.java size()
    // returns the number of entries in this Dictionary
    public int size(){
        return numItems;
    }

    // lookup()
    // pre: none
    // returns value associated key, or null reference if no such key exists
    // reference find()
    public String lookup(String key){
        Node N = findKey(key);
        if(N != null){
            return N.v;
        }else
            return null;
    }

    // insert()
    // inserts new (key,value) pair into this Dictionary
    // pre: lookup(key)==null
    // reference: add() in IntegerList.java
    public void insert(String key, String value) throws DuplicateKeyException{
        if (lookup(key) != null){
            throw new DuplicateKeyException("cannot insert duplicate keys");
        }else if (isEmpty()){
            Node N = new Node(key, value);
            N.next = head;
            head = N;
        }else{
            Node N = head;
            for ( ; N.next != null; N = N.next){
            }
            N.next = new Node(key, value);
        }
        numItems++;
    }

    // delete()
    // deletes pair with the given key
    // pre: lookup(key)!=null
    // reference: remove() in IntegerList.java
    // changed: 1. ListIndexOutOfBoundsException to KeyNotFoundException
    // 2. 
    public void delete(String key) throws KeyNotFoundException{
        if (lookup(key) == null){
            throw new KeyNotFoundException("cannot delete non-existent key");
        }
        Node temp = findKey(key);
        if (numItems == 1){
            makeEmpty();
        }else{
            Node N = head;
            if (N.k.equals(key)){
                head = N.next;
            }else{
                for(; N.next!= temp; N = N.next){
                }
                N.next = temp.next;
            }
            numItems--;
        }
    }

    // makeEmpty()
    // same as IntegerList.java removeAll()
    // pre: none
    public void makeEmpty(){
        head = null;
        numItems = 0;
    }

    // toString()
    // returns a String representation of this Dictionary
    // overrides Object's toString() method
    // pre: none
    // reference: toString()
    // changes: adjust sb.append() to include value field
    public String toString(){
        StringBuffer sb = new StringBuffer();
        Node N = head;

        for( ; N!=null; N=N.next){
            sb.append(N.k).append(" ").append(N.v).append("\n");
        }
        return new String(sb);
    }
}