//-----------------------------------------------------------------------------
// HelloUser2.java
// Antonio Valenzuela
// anlvalen@ucsc.edu
// CS12M - Thursday 2pm-4pm
// July 4, 2017
// Lab 1
// Prints greeting and User's current working directory to stdout
//-----------------------------------------------------------------------------
class HelloUser2{
   public static void main( String[] args ){
      String userName = System.getProperty("user.name");
      String userDir  = System.getProperty("user.dir");
      
      System.out.println("Hello "+userName);
      System.out.println("Your current directory "+userDir);
   }
}
