//-----------------------------------------------------------------------------
// DictionaryTest.c
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// August 19, 2017
// PA5
// isolation tests for Dictionary.c
//-----------------------------------------------------------------------------
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
    Dictionary D = newDictionary();
    printf("%s\n", (isEmpty(D)?"true":"false"));
    printf("%d\n", size(D));
    int i;
    char* k;
    char* v;
    char* word1[] = {"lets", "get", "ready", "to", "rumble"};
    char* word2[] = {"float", "like","a", "butter", "fly"};
    
    for(i=0; i<5; i++){
      insert(D, word1[i], word2[i]);
    }
    //size(A); //null dictionary reference error
    //lookup(A, "bee"); //null dictionary reference error
    
    
    printf("%s\n", (isEmpty(D)?"true":"false"));
    printf("%d\n", size(D));
    
    //insert(A, "lets", "go"); //Error message for duplicate key
    printDictionary(stdout, D);
    
    delete(D, "lets");
    //delete(A, "Ali"); //Error message for nonexistent key
    
    printf("%s\n", (isEmpty(D)?"true":"false"));
    printf("%d\n", size(D));
    
    char* j = lookup(D, "bee"); //null
    printf("key=\"%s\" %s\"%s\"\n", "bee", (j==NULL?"not found ":"value="), j);
    
    j = lookup(D, "ready"); //null
    printf("key=\"%s\" %s\"%s\"\n", "ready", (j==NULL?"not found ":"value="), j);
    
    makeEmpty(D);
    
    printf("%s\n", (isEmpty(D)?"true":"false"));
    printf("%d\n", size(D));
}