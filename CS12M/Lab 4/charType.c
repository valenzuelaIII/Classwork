/*
*   -----------------------------------------------------------------------------
*   charType.c
*   Antonio Valenzuela
*   anlvalen@ucsc.edu
*   CS12B
*   July 29, 2017
*   reads lines from specified input file and prints out the character breakdowns of 
*   each individual line into a specified output file. 
*   command line form: %charType <file1> <file2>
*  -----------------------------------------------------------------------------
*/

/* preprocessor directives */
#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char *s, char *a, char *d, char *p, char *w);

int main(int argc, char *argv[]){
    
    FILE *in;       /*  handle for input file */
    FILE *out;      /* handle for output file */
    char *line;     /* string holding input line */
    char *alpha;    /* string holding all alphabetic chars */
    char *digit;    /* string holding all digit chars */
    char *pun;      /* string holding all punctuation chars */
    char *space;     /* string holding all white spaces */
    
    /* error message */
    char *filemessage = "Unable to write to file ";     
    /* output messages for plural and singular messages */
    char *astring = " alphabetic ";
    char *nstring = " numeric ";
    char *pstring = " punctuation ";
    char *wstring = " whitespace ";
    char *singular = "character: ";
    char *plural = "characters: ";
    
    /* check command line for correct number of arguments  */
    if( argc != 3 ){
      printf("Usage: %s input-file output-file\n", argv[0]);
      exit(EXIT_FAILURE);
    }

    /* open input file for reading */ 
    if( (in=fopen(argv[1], "r"))==NULL ){
      printf("%s%s\n", filemessage, argv[1]);
      exit(EXIT_FAILURE);
    }

    /* open output file for writing  */
    if((out=fopen(argv[2], "w"))==NULL){
      printf("%s%s\n", filemessage, argv[2]);
      exit(EXIT_FAILURE);
    }
    
    /* allocate strings on the heap */
    line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    digit = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    pun = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    space = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    
    assert(line!=NULL && alpha!=NULL && digit!=NULL && pun!=NULL && space!=NULL);
    
    /* 
    while (in has next line)
        extract_chars();
        print line contains message
        if (number of characters > 1) 
            print plural message
        else
            print singular message
     */
        
    int i = 1;
    while( fgets(line, MAX_STRING_LENGTH, in) != NULL ){
        extract_chars(line, alpha, digit, pun, space);
        fprintf(out,"line %d contains:\n", i);
        if (strlen(alpha)>1){
            fprintf(out,"%d%s%s%s\n", (int)strlen(alpha), astring, plural, alpha);
        }else{
            fprintf(out,"%d%s%s%s\n", (int)strlen(alpha), astring, singular, alpha);
        }
        if (strlen(digit)>1){
            fprintf(out,"%d%s%s%s\n", (int)strlen(digit), nstring, plural, digit);
        }else{
            fprintf(out,"%d%s%s%s\n", (int)strlen(digit), nstring, singular, digit);
        }
        if (strlen(pun)>1){
            fprintf(out,"%d%s%s%s\n", (int)strlen(pun), pstring, plural, pun);
        }else{
            fprintf(out,"%d%s%s%s\n", (int)strlen(pun), pstring, singular, pun);
        }
        if (strlen(space)>1){
            fprintf(out,"%d%s%s%s\n", (int)strlen(space), wstring, plural, space);
        }else{
            fprintf(out,"%d%s%s%s\n", (int)strlen(space), wstring, singular, space);
        }
        i++;
    }
    /* free heap memory and close files  */
    free(line);
    free(alpha);
    free(digit);
    free(pun);
    free(space);
    fclose(in);
    fclose(out);

   return EXIT_SUCCESS;
}

    /* reference: extract_alpha_num(char* s, char* a) from alphaNum.c */
void extract_chars(char *s, char *a, char *d, char* p, char* w){
    int i=0;
    int alpha=0, digit=0, punct=0, white=0;
    while(s[i]!='\0' && i<MAX_STRING_LENGTH){
        if(isalpha((int)s[i])){ /* if alphabet character */
            a[alpha] = s[i];
            alpha++;
        }else if(isdigit((int)s[i])){ /* if digit character */
            d[digit] = s[i];
            digit++;
        }else if(ispunct((int)s[i])){ /* if punctuation character */
            p[punct] = s[i];
            punct++;
        }else{ /* else if (isspace((int)s[i])) if whitespace character */
            w[white] = s[i];
            white++;
        }
            i++;
    }
    a[alpha] = '\0';
    d[digit] = '\0';
    p[punct] = '\0';
    w[white] = '\0';
}