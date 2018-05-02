//-----------------------------------------------------------------------------
// Simulation.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12B
// August 9, 2017
// PA4
// Client for Jobs and Queue ADTs. (could not complete fully)
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Simulation{

    // provided helper function
    //-----------------------------------------------------------------------------

    public static Job getJob(Scanner in) {
        String[] s = in.nextLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int d = Integer.parseInt(s[1]);
        return new Job(a, d);
   }
    // main() with pseudocode 
    //-----------------------------------------------------------------------------

    public static void main(String[] args) throws IOException{

        // 1.  check command line arguments
        if(args.length < 1){
            System.err.println("Usage: Simulation file");
            System.exit(1);
        }
        
        // 2.  open files for reading and writing
        Scanner in = new Scanner(new File(args[0]));
        PrintWriter trace = new PrintWriter(new FileWriter(args[0]+".trc"));
        PrintWriter report = new PrintWriter(new FileWriter(args[0]+".rpt"));
        
        // 3.  read in m jobs from input file
        int m = Integer.parseInt(in.nextLine());
        // storage and backup que for jobs
        Queue jobs = new Queue();
        Queue backup = new Queue();
        
        for(int i = 0; i < m; i++){
            jobs.enqueue(getJob(in));
            backup.enqueue(getJob(in));
        }
        
        Queue[] processerQueue = new Queue[m+1];
        
        //print the header for both files
        trace.println("Trace file: "+(args[0]+".trc"));
        report.println("Report file: "+(args[0]+".rpt"));
        trace.println(m+" Jobs:");
        report.println(m+" Jobs:");
        trace.println(m+" Jobs:");
        report.println(m+" Jobs:");
        trace.println(jobs);
        report.println(jobs);
        trace.println();
        report.println();
        report.println("***********************************************************");
        
        int time;
        int wait;
        int max;
        int jobcount;
        int jobscompleted;
        double average;
        
        // 4.  run simulation with n processors for n=1 to n=m-1  {
        for (int n = 1; n < m; n++){
            //format for processor header in trace file
            trace.println("*****************************");
            if(n==1){
                trace.println(n+"processor:");
            }else{                
                trace.println(n+"processors:");
            }
            trace.println("*****************************");
            
           //  time = 0;
//             wait = 0;
//             max = 0;
//             jobcount= 0 ;
//             jobscomplete = 0;
//             average = 0.0;
//             
//             int jobLength = jobs.length();
//             for (int i = 0; i < joblength; i++){
//                 Job j = (Job)jobs.dequeue();
//                 j.resetFinishTime();
//                 storage.enqueue(j);
            }
        // 5.      declare and initialize an array of n processor Queues and any 
        //        necessary storage Queues
        
            
        // 6.      while unprocessed jobs remain  {
        // 
        // 7.          determine the time of the next arrival or finish event and 
        //            update time
        // 
        // 8.          complete all processes finishing now
        // 
        // 9.          if there are any jobs arriving now, assign them to a processor 
        //            Queue of minimum length and with lowest index in the queue array.
        // 
        // 10.     } end loop
        // 
        // 11.     compute the total wait, maximum wait, and average wait for 
        //        all Jobs, then reset finish times
        // 
        // 12. } end loop
        
        // 13. close input and output files
        in.close();
        report.close();
        trace.close();
   }
}