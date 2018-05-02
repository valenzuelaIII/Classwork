//-----------------------------------------------------------------------------
// Dictionary.c
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// Aug 6th, 2017
// Lab5
// Implements Dictionary ADT similar to file Dictionary.java from PA3 using 
// singly linked list with head reference
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types
// Modified from IntegerStack.c
//-----------------------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
   char* key;
   char* value;
   struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* x, char* y) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->key = x;
   N->value = y;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
typedef struct DictionaryObj{
   Node head;
   int numItems;
} DictionaryObj;

// Public types
//-----------------------------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
// Reference: IntegerStack.c and Dictionary.java
Dictionary newDictionary(void){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D!=NULL);
    D->head = NULL; //singly linked list with head reference
    D->numItems = 0;
    return D;
}

// freeDictionary()
// destructor for the Dictionary type
// Reference: IntegerStack.c
void freeDictionary(Dictionary* pD){
    if( pD!=NULL && *pD!=NULL ){
        free(*pD);
        *pD = NULL;
   }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
// error: error message occurs on NULL dictionary reference
// Reference: IntegerStack.c
int isEmpty(Dictionary D){
    if(D==NULL){
      fprintf(stderr, 
            "Dictionary Error: Cannot check if empty on NULL Dictionary reference. isEmpty()\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
// Reference: IntegerStack.c
int size(Dictionary D){
    if (D==NULL){
        fprintf(stderr,
            "Dictionary Error: Cannot find size on NULL Dictionary Reference. size()\n");
        exit(EXIT_FAILURE);
    }
    return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
// Reference: LookUp() and FindKey() in Dictionary.java from PA3
char* lookup(Dictionary D, char* k){
    if (D==NULL){
        fprintf(stderr,
            "Dictionary Error: Cannot lookup on NULL Dictionary Reference. Lookup()\n");
        exit(EXIT_FAILURE);
    }
    Node P;
    for(P = D->head; P != NULL; P = P->next){
        if(strcmp(P->key,k)==0)
            return P->value;
    }
    return NULL; //if no value v exists
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
// Reference: Insert() in Dictionary.java from PA3 and push() in IntegerStack.c
void insert(Dictionary D, char* k, char* v){
    if(lookup(D,k)!=NULL){ //if there already exists a key 'k' in Dictionary
        fprintf(stderr,
            "Dictionary Error: Cannot insert duplicate key. insert()\n");
        exit(EXIT_FAILURE);
    }else if (isEmpty(D)){ //if Dictionary is empty
        D->head = newNode(k,v);
        D->numItems++;
    }else{
        Node P;
        for(P=D->head; P->next!=NULL; P=P->next){ //traverses along the list
        }
        P->next = newNode(k,v); //insertion
        D->numItems++;
    }
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
// Reference: Insert() in Dictionary.java from PA3 and pop() in IntegerStack.c
void delete(Dictionary D, char* k){
    //if Dictionary is empty
    if(lookup(D,k)==NULL){
        fprintf(stderr,
            "Dictionary Error: Cannot delete non-existent key. delete()\n");
        exit(EXIT_FAILURE);
    }else if (strcmp(D->head->key, k)==0){ //if first Node's key is equal to k
        Node temp = D->head;
        D->head = temp->next;
        D->numItems--;
        freeNode(&temp);
    }else{
        Node P;
        for(P=D->head; P->next!=NULL; P=P->next){ //the preceding node's 'next pointer' to the 
            if(strcmp(P->next->key,k)==0){        //node immediately following desired deletion node
                Node temp = P->next;
                P->next = P->next->next;
                freeNode(&temp);
                D->numItems--;
                return;
            }
        }
    }
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
// Reference: Insert() in Dictionary.java from PA3 and popAll() in IntegerStack.c
void makeEmpty(Dictionary D){
    if (D==NULL){
        fprintf(stderr,
            "Dictionary Error: Cannot empty on NULL Dictionary Reference. makeEmpty()\n");
        exit(EXIT_FAILURE);
    }
    if(isEmpty(D)){
        return;
    }
    Node temp = D->head;
    Node P = D->head;
    while(P!=NULL) {    //loop to free Nodes
        P = P->next;
        freeNode(&temp);
        temp=P;
    }
   // freeNode(&(D->head));
    D->numItems = 0;
    D->head = NULL;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
// Reference: printStack() in IntegerStack.c
void printDictionary(FILE* out, Dictionary D){
    if (D==NULL){
        fprintf(stderr,
            "Dictionary Error: Cannot print on NULL Dictionary Reference. printDictionary()\n");
        exit(EXIT_FAILURE);
    }
    Node N;
    for (N=D->head; N!=NULL; N=N->next){ 
        fprintf(out, "%s %s\n", N->key, N->value);
    }
}
