//-----------------------------------------------------------------------------
// Dictionary.c
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// August 19, 2017
// PA5
// implements DictionaryADT based on a hash table w/ chaining
//-----------------------------------------------------------------------------


#include <stdio.h>
#include <string.h>
#include <assert.h>
#include <stdlib.h>
#include "Dictionary.h"

// Provided Code
//-----------------------------------------------------------------------------

const int tableSize=101; 

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
    int sizeInBits = 8*sizeof(unsigned int);
    shift = shift & (sizeInBits - 1);
    if ( shift == 0 )
        return value;
    return (value << shift) | (value >> (sizeInBits - shift));
}
// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
    unsigned int result = 0xBAE86554;
    while (*input) {
        result ^= *input++;
        result = rotate_left(result, 5);
    }
    return result;
}
// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(char* key){
    return pre_hash(key)%tableSize;
}

//Node Construction
//-----------------------------------------------------------------------------

typedef struct NodeObj{
    char* key;
    char* value;
    struct NodeObj* next;
}NodeObj;

typedef NodeObj* Node;

// node constructor
Node newNode(char *x, char* y){
    Node N = malloc(sizeof(NodeObj));
    assert(N!=NULL);
    N->key = x;
    N->value = y;
    N->next = NULL;
    return(N);
}

// frees allocated heap memory for a node pN
void freeNode(Node* pN){
    if(pN!=NULL && *pN!=NULL){
        free(*pN);
        *pN = NULL;
    }
}

//ADT implementation
//-----------------------------------------------------------------------------

typedef struct DictionaryObj{
    Node *table;
    int numItems;
}DictionaryObj;

// Dictionary
// Exported reference type
typedef struct DictionaryObj* Dictionary;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
    Dictionary D = malloc(sizeof(DictionaryObj));
    assert(D!=NULL);
    D->table = calloc(tableSize, sizeof(NodeObj));
    assert(D->table != NULL);
    D->numItems = 0;
}

// returns node associated with desired key, otherwise returns NULL
Node findKey(Dictionary D, char* key){
    Node N = D->table[hash(key)];
    for ( ; N!=NULL; N=N->next){
        if(strcmp(N->key,key) == 0) 
            break;
    }
    return N;
}
// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
    if (pD!=NULL && *pD!=NULL){
        makeEmpty(*pD);
        free((*pD)->table);
        free(*pD);
        *pD = NULL;
    }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
    if(D == NULL){
        fprintf(stderr, 
            "Dictionary Error: isEmpty(): cannot perform isEmpty() on NULL dictionary reference");
        exit(EXIT_FAILURE);
    }else
        return (D->numItems == 0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
    if(D == NULL){
        fprintf(stderr, 
            "Dictionary Error: size(): cannot perform size() on NULL dictionary reference");
        exit(EXIT_FAILURE);
    }else
        return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
    if(D == NULL){
        fprintf(stderr, 
            "Dictionary Error: lookup(): cannot perform lookup() on NULL dictionary reference");
        exit(EXIT_FAILURE);
    }else if(D->numItems == 0){
        fprintf(stderr, 
            "Dictionary Error: lookup(): cannot perform lookup() on empty dictionary");
        exit(EXIT_FAILURE);
    }else{
        Node N = findKey(D,k);
        if (N==NULL) 
            return NULL; //if node not found
        else
            return N->value; //value of node's key
    }
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
    if(D == NULL){
        fprintf(stderr, 
            "Dictionary Error: insert(): cannot perform insert() on NULL dictionary reference");
        exit(EXIT_FAILURE);
    }
    if(findKey(D,k)!=NULL){
        fprintf(stderr, 
            "Dictionary Error: insert(): cannot perform insert() on duplicate keys");
        exit(EXIT_FAILURE);
    }
    if (D->table[hash(k)] == NULL){ //if table at index hash(k) is empty, insert first node of chain
        D->table[hash(k)] = newNode(k,v);
    }else{
        Node N = newNode(k,v); //insert new node at non empty slot hash(k) at the front
        N->next = D->table[hash(k)];
        D->table[hash(k)] = N;
    }
    D->numItems++;
}

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
    if(D == NULL){
        fprintf(stderr, 
            "Dictionary Error: delete(): cannot perform delete() on NULL dictionary reference");
        exit(EXIT_FAILURE);
    }
    if(findKey(D,k)==NULL){
        fprintf(stderr, 
            "Dictionary Error: delete(): cannot perform delete() on non-existent keys");
        exit(EXIT_FAILURE);
    }
    Node N = findKey(D,k);
    if (D->table[hash(k)] == N){ //if Node to be deleted is at head of the slot's chain
        if(N->next==NULL){ //Node is not followed by any other node
            D->table[hash(k)] = NULL;
            freeNode(&N);        
        }else{ //if Node has Nodes chained to it
            D->table[hash(k)] = N->next;
            freeNode(&N);
        }
    }else{//node is along a chain
        Node P = D->table[hash(k)];
        for ( ; P->next!=N; P=P->next);//traverses chain
        P->next = N->next;
        freeNode(&N);
    }
    D->numItems--;
}

// makeEmpty()
// re-sets D to the empty state.
// pre: none
void makeEmpty(Dictionary D){
    if(D == NULL){
        fprintf(stderr, 
        "Dictionary Error: makeEmpty(): cannot perform makeEmpty() on NULL dictionary reference");
        exit(EXIT_FAILURE);
    }
    Node N;
    for(int i=0; i<tableSize; i++){ // for each slot i
        while(D->table[i] != NULL){ //traverses chain for slot i and frees nodes
            N=D->table[i];
            D->table[i] = N->next;
            freeNode(&N);
        }
    }
    D->numItems = 0;
}

// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
    Node N;
    for (int i=0; i<tableSize; i++){ //for each slot i
        if(D->table[i]!=NULL){  
            for(N = D->table[i]; N!=NULL; N=N->next){ //traverses chain and prints list
                fprintf(out, "%s %s\n", N->key, N->value);
            }
        }
    }
}