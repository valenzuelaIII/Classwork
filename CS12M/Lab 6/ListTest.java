//-----------------------------------------------------------------------------
// ListTest.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// August 15, 2017
// Lab6
//-----------------------------------------------------------------------------

class ListTest{
    public static void main(String[] args){
    List<String> test = new List<String>();
    List<String> compare = new List<String>();

    System.out.println("Correct output of isEmpty() is true. Actual output is: "+test.isEmpty());
    
    test.add(1, "lets");
    test.add(2, "get");
    test.add(3, "ready");
    test.add(4, "to");
    test.add(5, "rumble");
    
    compare.add(1, "lets");
    compare.add(2, "get");
    compare.add(3, "ready");
    compare.add(4, "to");
    compare.add(5, "rumble");
    
    System.out.println("Correct output of test.isEmpty() is false. Actual output is: "
        +test.isEmpty());
    System.out.println("Correct output of test.size() is 5. Actual output is: "+test.size());
    System.out.println("Correct output of test.get(3) is 'ready'. Actual output is: "+test.get(3));
    System.out.println("Correct output of test.equals(compare) is true. Actual output is: "
        +test.equals(compare));
    System.out.println("test: "+test); //test: lets get ready to rumble
    System.out.println("compare: "+compare); //compare: lets get ready to rumble
    
    test.remove(1);
    //compare.remove(6); //throws ListIndexOutOfBoundsException
    
    System.out.println("Correct output of compare.isEmpty() is false. Actual output is: "
        +compare.isEmpty());
    System.out.println("Correct output of test.size() is 4. Actual output is: "+test.size());
    System.out.println("Correct output of test.equals(compare) is false. Actual output is: "
        +test.equals(compare));
    System.out.println("test: "+test); //test: get ready to rumble
    
    //compare.get(7); //throws ListIndexOutOfBoundsException
    //test.add(9, "foo"); //throws ListIndexOutOfBoundsException
    
    test.removeAll();
    compare.removeAll();
    
    System.out.println("Correct output of isEmpty() is true. Actual output is: "+test.isEmpty());
    System.out.println("Correct output of test.size() is 0. Actual output is: "+test.size());
    System.out.println("Correct output of compare.size() is 0. Actual output is: "+compare.size());
    
    System.out.println("test: "+test); //test: 
    System.out.println("compare: "+compare); //compare: 
    }
}