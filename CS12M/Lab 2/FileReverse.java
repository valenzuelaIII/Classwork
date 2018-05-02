//-----------------------------------------------------------------------------
// FileReverse.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// July 19, 2017
// Reads each line from a specified input file, parses tokens, then prints each 
// token backwards to the output file on a line by itself
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class FileReverse {
    
    /* uses charAt (int index) methods in a recursive to reverse the first n
    *  characters of string s
    *  pseudocode: 
    *   if n==1 return charAt(0)
    *   else return charAt(n-1) + stringReverse(s, n-1)
    *  reference: http://docs.oracle.com/javase/8/docs/api/
    */ 
    public static String stringReverse(String s, int n){
        if (n == 1){
            return "" + s.charAt(0);
        }else
            n--;
            return s.charAt(n) + stringReverse(s, n);
    }
    
    /* main function that reads file in from the command line and prints       
    *  reversed tokens line by line to the file out onto the command line
    *  pseudocode: 
    *       if argslength < 2 : exit prompt + exit
    *       open files
    *       while (file has next line)
    *           for (# of tokens > 0)
    *               print to out (stringReverse(token, token length)
    *       close files 
    *  reference: based off of FileTokens.java provided in example
    */ 
    
    public static void main(String[] args) throws IOException{
       
        Scanner in = null;
        PrintWriter out = null;
        String line = null;
        String [] token = null;
        int i, n;
       
        // check number of command line arguments is at least 2
        if (args.length < 2) {
            System.out.println("Usage: FileCopy <input file> <output file>");
            System.exit(1);
        }
       
        // open files
        in = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));
       
        // read in lines from in, extract and print tokens from each line
        while (in.hasNextLine()){
            
            line = in.nextLine().trim() + " ";
            token = line.split("\\s+");
            n = token.length;
            for (i=0;  i<n; i++){
                out.println(stringReverse(token[i], token[i].length()));
            }
        }

        //close files
        in.close();
        out.close();
    }
}