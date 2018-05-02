//-----------------------------------------------------------------------------
// QueueTest.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// August 9, 2017
// PA4
// isolation tests for methods in Queue.java including exception handling
//-----------------------------------------------------------------------------

public class QueueTest{

    public static void main(String[] args){
        
        Queue test = new Queue();
        
        System.out.println(test.isEmpty()); //output: true
        System.out.println(test.length()); //output: 0
        System.out.println(test); //output:
        
        //System.out.println(test.peek()); //QueueEmptyException is thrown
        //System.out.println(test.dequeue()); //QueueEmptyException is thrown
        //test.dequeueAll(); //QueueEmptyException is thrown
        
        test.enqueue((int)5); 
        test.enqueue((int)3);
        test.enqueue((int)9);
        test.enqueue((int)7);
        test.enqueue((int)8);
        
        System.out.println(test); //output: 5 3 9 7 8
        System.out.println(test.isEmpty()); //output: false
        System.out.println(test.length()); //output: 5
        System.out.println(test.peek()); //output 5
        
        System.out.println(test.dequeue()); //output: 3
        System.out.println(test.dequeue()); //output: 9
        
        System.out.println(test); //output: 9 7 8
        System.out.println(test.isEmpty()); //output false
        System.out.println(test.length()); //output 3
        System.out.println(test.peek()); //output: 9
        
        test.dequeueAll(); 
        System.out.println(test); //output: 
        System.out.println(test.isEmpty()); //output: true
        System.out.println(test.length()); //output: 0
        
    }
}