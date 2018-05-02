//-----------------------------------------------------------------------------
// QueueEmptyException.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// August 9, 2017
// PA4
// Handles exception when certain Queue ADT operations are attempted on an empty queue
// reference: QueueEmptyException.java from cmps012b/Summer17/Examples/Lecture/IntegerQueueADT/Array
//-----------------------------------------------------------------------------

public class QueueEmptyException extends RuntimeException{
    public QueueEmptyException(String s) {
        super(s);
    }
}