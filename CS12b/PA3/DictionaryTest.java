//-----------------------------------------------------------------------------
// DictionaryTest.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// July 22, 2017
// PA3
// isolation tests for Dictionary.java 
//-----------------------------------------------------------------------------

public class DictionaryTest{
    public static void main(String[] args){
    Dictionary test = new Dictionary();
    boolean a; 
    String b; 
    int c;
    
    a = test.isEmpty();
    System.out.println("Correct output of isEmpty() is true. Actual output is: "+a);
    
    test.insert("z","2");
    test.insert("y","5");
    
    c = test.size();
    System.out.println("Correct output size is 2. Actual output is: "+c);
    
    b = test.lookup("y");
    System.out.println("Correct value of y is 5. Actual output is: "+b);
    
    //test.insert("z","1"); //trows DuplicateKeyException
    
    //test.delete("w"); //throws KeyNotFoundException
    
    a = test.isEmpty();
    System.out.println("Correct output of isEmpty is false. Actual output is: "+a);
    
    System.out.println(test);
    // expected output:
    // Correct output of isEmpty() is true. Actual output is: true
    // Correct output size is 2. Actual output is: 2
    // Correct value of y is 5. Actual output is: 5
    // Correct output of isEmpty is false. Actual output is: false
    // z 2
    // y 5
    }
}