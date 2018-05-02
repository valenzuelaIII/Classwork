/*
*   -----------------------------------------------------------------------------
*   FileReverse.c
*   Antonio Valenzuela
*   anlvalen@ucsc.edu
*   CS12B
*   July 19, 2017
*   Reads each line from a specified input file, parses tokens, then prints each 
*   token backwards to the output file on a line by itself
*  -----------------------------------------------------------------------------
*/

/*preprocessor directives*/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

/* function declaration*/
void stringReverse(char*s);

/* 
*   opens a specified file and prints out the reversed words in that file into 
*   another specified file
*/
int main(int argc, char* argv[]){
    FILE* in; /* file handle for input */
    FILE* out; /* file handle for output */
    char word[256]; /* char array to store words from input file */
    
    /* checks command line for correct number of arguments */
    if( argc != 3 ){
        printf("Usage: %s <input file> <output file>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    /* opens input file for reading */
    in = fopen(argv[1], "r");
    if( in==NULL ){
        printf("Unable to read from file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }
    
    /* opens output file for writing */
    out = fopen(argv[2], "w");
    if( out==NULL ){
        printf("Unable to write to file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }
    
    /* reads words from input file, print on separate lines to output file*/
    while( fscanf(in, " %s", word) != EOF ){
        stringReverse(word);
        fprintf(out, "%s\n", word);
    }
    
    /* closes input and output files */
    fclose(in);
    fclose(out);
    return(EXIT_SUCCESS);
}


    /*  swaps letters in word s
    *   pseudocode:
    *1. Set two variables i and j to i=0 and j=strlen(s)-1
    *2. Swap the characters s[i] and s[j]
    *3. Increment i and decrement j
    *4. Stop if i>=j
    *5. Otherwise go back to (2)
    */
void stringReverse(char* s){
    int i = 0;
    int j = strlen(s)-1;
    char a = 0;
    char b = 0;
    while (i < j){
        a = s[i];
        b = s[j];
        s[j] = a;
        s[i] = b;
        i++;
        j--;
    }
    return;
}