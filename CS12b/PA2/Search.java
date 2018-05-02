//-----------------------------------------------------------------------------
// Search.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// July 16, 2017
// Implements Binary Search and Merge Sort algorithms to operate on String
// arrays. 
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class Search {
    
    // reference: LineCount.java
    // Pseudocode:
    //  declare vairables
    //  if (FileNotFoundException)
    //      Error Message and Quit
    //      scan File1
    //  while (hasNextLine())
    //      count lines
    //  close File1
    //  for (i=0;i< line count; i++);
    //      store line number in array
    //  open file1
    //  for file1.hasNextLine   
    //      store string from current line
    //  mergeSort()
    //  for () //search for target1 [target2 ..]
    //      if binarySearch is -1
    //          return not found
    //      else
    //          return target found & line number
    
    public static void main(String[] args) throws IOException{
        int lineCount = 0;
        String currentLine = null;
        int i = 0;
        int j = 0;
        // if FileNotFoundException, print out command line structure
        if (args.length < 2){
            System.err.println("Usage: Search file target1 [target2 ..]");
            System.exit(1);
        }
            
        Scanner in = new Scanner(new File(args[0]));
        while(in.hasNextLine()){
            in.nextLine();
            lineCount++;
        }
        
        int [] lineNum = new int[lineCount];
        for (i = 1; i <= lineNum.length; i++){
            lineNum[i-1] = i;
        }
        
        String[] S = new String[lineCount];
        in = new Scanner(new File(args[0]));
        for (i = 0; in.hasNextLine(); i++){
            currentLine = in.nextLine();
            S[i] = currentLine;
        }
        
        // testing for file reading and line number assignment
        //for (i=0; i < S.length; i++){
        //    System.out.println(S[i] + " " + lineNum[i]);
        //    }
        
        mergeSort(S, lineNum, 0, S.length-1);
        
        // testing for mergeSort() and merge() function
        //for (i=0; i < S.length; i++){
        //    System.out.println(S[i] + " " + lineNum[i]);
        //    }
            
        int targetLine;
        for (i = 1; i < args.length; i++){
            targetLine = binarySearch(S, lineNum, 0, S.length - 1, args[i]);
            if (targetLine == -1){
                System.out.println(args[i] + " not found");
            }else {
                System.out.println(args[i] + " found on line " + targetLine);
            } 
        }
        in.close();
    }
    
    
    // Merges sorted subarrays word[p..q] and word[q+1..r]
    // reference: merge() from MergeSort.java example
    static void merge(String[] word, int[] lineNumber, int p, int q, int r){
        int n1 = q-p+1;
        int n2 = r-q;
        String [] L_string = new String[n1];
        String [] R_string= new String[n2];
        int [] L_count = new int[n1];
        int [] R_count = new int[n2];
        int i, j, k;

        for(i=0; i<n1; i++){
            L_string[i] = word[p+i];
            L_count[i] = lineNumber[p+i];
        }
        
        for(j=0; j<n2; j++){ 
            R_string[j] = word[q+j+1];
            R_count[j] = lineNumber[q+j+1];
        }

        i = 0; j = 0;
        for(k=p; k<=r; k++){
            if( i<n1 && j<n2 ){ //if two comparable strings arrays are available
                if(L_string[i].compareTo(R_string[j]) < 0){ //L precedes R lexicographically
                    word[k] = L_string[i];
                    lineNumber[k] = L_count[i];
                    i++;
               }else{   //R precedes L lexicographically
                    word[k] = R_string[j];
                    lineNumber[k] = R_count[j];
                    j++;
                    }
            }else if( i<n1 ){ // if left array is left
                word[k] = L_string[i];
                lineNumber[k] = L_count[i];
                i++;
            }else{ // if right array is left
                word[k] = R_string[j];
                lineNumber[k] = R_count[j];
                j++;
         }
      }
    }
    
    
    // sort subarray word[p..r]. essentially the same as mergeSort() but with 
    //function calls changed
    // reference: mergeSort() from MergeSort.java example
    static void mergeSort(String[] word, int[] lineNumber, int p, int r){
        int q;
        if(p < r) {
            q = (p+r)/2;
            mergeSort(word, lineNumber, p, q);
            mergeSort(word, lineNumber, q+1, r);
            merge(word, lineNumber, p, q, r);
        }
    }
    
    
    // pre: String [p...r] is sorted
    // reference: binarySearch from BinarySearch.java example
    static int binarySearch(String[] word, int[] lineNumber, int p, int r, String target){
        int q;
        //System.out.println(p+" "+r);
            if(p == r) {
                //System.out.println(p+" "+r);
                return -1;
            }else{
                q = (p+r)/2;
                if(target.compareTo(word[q]) == 0){
                    //System.out.println(p+" "+r + "=");
                    return lineNumber[q];
                }else if( target.compareTo(word[q]) < 0){
                    //System.out.println(p+" "+r + "<");
                    return binarySearch(word, lineNumber, p, q, target);
                }else{ // target > word[q]
                    //System.out.println(p+" "+r + ">");
                    return binarySearch(word, lineNumber, q+1, r, target);
                }
            }
    }
}