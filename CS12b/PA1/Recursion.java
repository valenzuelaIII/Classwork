//-----------------------------------------------------------------------------
// Recursion.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// July 6, 2017
// Executes 5 recursive methods called from a provided main function and prints
// the known output to stdout
//-----------------------------------------------------------------------------

class Recursion {


    /* copies the leftmost n elements of X[] into the rightmost n positions in
    *  Y[] in reverse order. Pseudocode:
    *   if n==0 do nothing
    *       Y[length - 1] = X[n - 1]
    *       reverseArray1 
    */
    
    static void reverseArray1(int[] X, int n, int[] Y) {
        if(n > 0) {
            Y[Y.length - n] = X[n - 1];
            reverseArray1(X, n-1, Y); //recursive call
        }
        else
            return;
    }

    
    
    /* copies the rightmost n elements of X[] into the leftmost n positions in
    *  Y[] in reverse order. Pseudocode:
    *   if n==0 do nothing
    *       reverseArray2
    *       Y[n-1] = X[length -n]
    */
    
    static void reverseArray2(int[] X, int n, int[] Y) {
        if(n > 0){
            reverseArray2(X, n-1, Y); //recursive call
            Y[n - 1] = X[X.length - n];
        }
        else
            return; //return when leftmost element is reached
    }
    
    
    /* reverses the subarray X[i...j].
    *   Pseudocode:
    *   if i == j do nothing
    *       store j in temp
    *       X[j] = X[i]
    *       X[i] = temp
    *       reverseArray3 (X, x+1, j-1)
    */
    
    static void reverseArray3(int[] X, int i, int j) {
        int k;
        if(i < j) {
            k = X[j];
            X[j] = X[i];
            X[i] = k;
            reverseArray3(X, i+1, j-1);
        }
        else
            return;
    }
    
    
    /*  returns the index of the largest element in int array X
    *   Pseudocode:
    *   if r > p
    *       index = (p+r)/2
    *       if X[maxArrayIndex(p,q)] > X[maxArrayIndex(q+1,r)]
    *           return Index(p,q)
    *        else
    *           return Index(q+1,R)
    */
    static int maxArrayIndex(int[] X, int p, int r) {
        if (r > p) {
            int q = (p + r)/2;
            int a = maxArrayIndex(X, p, q);
            int b = maxArrayIndex(X, q+1, r);
            if (X[a] > X[b]) 
                return a;
            else 
                return b;
        }
        else
            return r;
    }
    
    /*
    * returns the index of the smallest element in array X
    *   Pseudocode:
    *   if r > p
    *       index = (p+r)/2
    *       if X[minArrayIndex(p,q)] < X[minArrayIndex(q+1,r)]
    *           return Index(p,q)
    *        else
    *           return Index(q+1,R)
    */
    static int minArrayIndex(int[] X, int p, int r) {
        if (r > p) {
            int q = (p + r)/2;
            int a = minArrayIndex(X, p, q);
            int b = minArrayIndex(X, q + 1, r);
            if (X[a] < X[b]) 
                return a;
            else 
                return b;
        }
        else
            return r;
    }
    
    
    /* Provided main function w/ expected output:
    * -1 2 6 12 9 2 -5 -2 8 5 7
    * minIndex = 6
    * maxIndex = 3
    * 7 5 8 -2 -5 2 9 12 6 2 -1
    * 7 5 8 -2 -5 2 9 12 6 2 -1
    * 7 5 8 -2 -5 2 9 12 6 2 -1
    */
    
    public static void main(String[] args) {
        int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
        int[] B = new int[A.length];
        int[] C = new int[A.length];
        int minIndex = minArrayIndex(A, 0, A.length-1);
        int maxIndex = maxArrayIndex(A, 0, A.length-1);

        for(int x: A) System.out.print(x+" ");
        System.out.println();

        System.out.println( "minIndex = " + minIndex );
        System.out.println( "maxIndex = " + maxIndex );
        
        reverseArray1(A, A.length, B);
        for(int x: B) System.out.print(x+" ");
        System.out.println();
        
        reverseArray2(A, A.length, C);
        for(int x: C) System.out.print(x+" ");
        System.out.println();
        
        reverseArray3(A, 0, A.length-1);
        for(int x: A) System.out.print(x+" ");
        System.out.println();
        
    }
}