//-----------------------------------------------------------------------------
// List.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// August 18, 2017
// lab7
// converts BST version of Dictionary.c into java
//-----------------------------------------------------------------------------

public class Dictionary implements DictionaryInterface{

//private innner class for node construction
//-----------------------------------------------------------------------------
    private class Node{
        String key;
        String value;
        Node left;
        Node right;
        
        Node(String x, String y){
            key = x;
            value = y;
            left = null;
            right = null;
        }
    }
    
    private Node root;
    private int numItems;
    
    public Dictionary(){
        root = null;
        numItems = 0;
    }
    
//helper functions
//-----------------------------------------------------------------------------
    //returns searches a tree for a node associated a specified key
    private Node findKey(Node R, String key){
        if (R == null|| key.compareTo(R.key)==0)
            return R;
        if(key.compareTo(R.key)>0){
            return findKey(R.right, key);
        }else{
            return findKey(R.left, key);
        }
    }    
    //finds parent node of a child
    private Node findParent(Node N, Node R){
        Node P = null;
        if(N!=R){
            P=R;
            while(P.right!=N && P.left!=N){
                if(N.key.compareTo(P.key)>0){
                    P=P.right;
                }else{
                    P=P.left;
                }
            }
        }
        return P;
    }
    
    //prints all children of a specified subtree including specified root in order
    private String printInOrder(Node R, String s){
        if (R!=null){
            s=printInOrder(R.left, s);
            s += R.key + " " + R.value + "\n";
            s=printInOrder(R.right,s);
        }
        return s;
    }
    
    //deletes nodes in subtree
    void deleteAll(Node R){
        if (R!=null){
            deleteAll(R.left);
            deleteAll(R.right);
            R=null;
        }
    }
    
    //finds leftmost child
    private Node findLeftMost(Node R){
        Node N = R;
        if(N!=null){
            for(; N.left!=null; N=N.left){
            }
        }
        return N; 
    }

//ADT functions
//-----------------------------------------------------------------------------
    
    // isEmpty()
    // pre: none
    // returns true if this Dictionary is empty, false otherwise
    public boolean isEmpty(){
        return(numItems==0);
    }

    // size()
    // pre: none
    // returns the number of entries in this Dictionary
    public int size(){
        return numItems;
    }

    // lookup()
    // pre: none
    // returns value associated key, or null reference if no such key exists
    public String lookup(String key){
        Node N = findKey(root, key);
        if(N == null){
            return null;
        }else{
            return N.value;
        }
    } 

    // insert()
    // inserts new (key,value) pair into this Dictionary
    // pre: lookup(key)==null
    public void insert(String key, String value) throws DuplicateKeyException{
        if(findKey(root,key)!=null){
            throw new DuplicateKeyException(
            "Dictionary Error: insert(): cannot insert duplicate keys");
        }
        Node N, R, P;
        N = new Node(key, value);
        P = null;
        R = root;
        while(R!=null){
            P = R;
            if(key.compareTo(R.key)>0){
                R = R.right;
            }else{
                R=R.left;
            }
        }
        if(P==null){
            root = N;
        }else if(key.compareTo(P.key)>0){
            P.right = N;
        }else{
            P.left = N;
        }
        numItems++;
        }

    // delete()
    // deletes pair with the given key
    // pre: lookup(key)!=null
    public void delete(String key) throws KeyNotFoundException{
        if(findKey(root, key) == null){
            throw new KeyNotFoundException(
                "Dictionary Error: delete(): cannot delete nonexistent key");
        }
        Node N=findKey(root, key), P, L;
        //no children
        if(N.left==null && N.right == null){
            if(N==root){ 
                root = null;
            }else{
                P = findParent(N, root);
                if(P.left == N){ 
                    P.left = null;
                }else{
                    P.right = null;
                }
            }
        }else if(N.left == null){//right child but no left
            if(N==root){
                root = N.right;
                N = null;
            }else{
                P = findParent(N, root);
                if(P.left == N){
                    P.left= N.right;
                }else{
                    P.right = N.right;
                }
                N = null;
            }
        }else if(N.right == null){//left child but no right
            if(N==root){
                root = N.left;
                N = null;
            }else{
                P = findParent(N, root);
                if(P.left == N){
                    P.left = N.left;
                }else{
                    P.right = N.left;
                }
                N = null;
            }
        }else{//two children
            L = findLeftMost(N.right);
            N.key = L.key;
            N.value = L.value;
            P = findParent(L,N);
            if(P.left == L){
                P.left = L.left;
            }else{
                P.right = L.right;
            }
        }
        numItems--;
    }

    // makeEmpty()
    // pre: none
    public void makeEmpty(){
        deleteAll(root);
        root = null;
        numItems = 0;
    }

    // toString()
    // returns a String representation of this Dictionary
    // overrides Object's toString() method
    // pre: none
    public String toString(){
        String s = printInOrder(root, "");
        return s;
    }
}