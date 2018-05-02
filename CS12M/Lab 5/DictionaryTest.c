//-----------------------------------------------------------------------------
// DictionaryTest.c
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// Aug 6th, 2017
// Lab5
// Isolation testing for Dictionary.c (reference: DictionaryClient.c and
// DictionaryTest.java from PA3)
//-----------------------------------------------------------------------------
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

int main(int argc, char* argv[]){
    Dictionary A = newDictionary();
    char* k;
    char* v;
    char* word1[] = {"a","b","c","d","e"};
    char* word2[] = {"lets","get","ready","to","rumble"};
    int i;

    for(i=0; i<5; i++){
        insert(A, word1[i], word2[i]);
    }
    printf("%s\n", (isEmpty(A)?"true":"false"));
    printf("%d\n", size(A));
    //insert(A, "d", "fight");
    
    printDictionary(stdout, A);
    
    for(i=0; i<5; i++){
    k = word1[i];
    v = lookup(A, k);
    //v = lookup(B, k);
    printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
    }
    
    //delete(A, "one");
    //delete(B, "three");
    delete(A, "d");
    delete(A, "b");
    printf("%s\n", (isEmpty(A)?"true":"false"));
    printf("%d\n", size(A));
    printDictionary(stdout, A);

    for(i=0; i<5; i++){
        k = word1[i];
        v = lookup(A, k);
        printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
    }
    printf("%s\n", (isEmpty(A)?"true":"false"));
    printf("%d\n", size(A));
    makeEmpty(A);
    printf("%s\n", (isEmpty(A)?"true":"false"));
    printf("%d\n", size(A));
    freeDictionary(&A);
    
    return(EXIT_SUCCESS);
    }