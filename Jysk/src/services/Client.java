package services;

import java.io.*;

import org.omg.CORBA.*;

public abstract class Client {
   private String[] theArgs;
   private ORB theOrb;

   private File f;
   private BufferedReader br;

   public Client(String args[]) {
      theArgs = args;
      // initialize the ORB.
      theOrb = ORB.init(args, null);

      try { // init buffer for reading object references from file:
         f = new File(theArgs[0]);
         br = new BufferedReader(new FileReader(f));
      }
      catch (Exception ex) {
         System.err.println(ex);
      }
   }

   public org.omg.CORBA.Object getObjectReference() {
      org.omg.CORBA.Object obj = null;
      try { // get object reference from command-line argument file
         obj = theOrb.string_to_object(br.readLine());
      }
      catch (Exception ex) {
         System.err.println(ex);
      }
      return obj;
   }

   protected void closeBuffer() {
      try {
         br.close();
      }
      catch (Exception ex) {
         System.err.println(ex);
      }
   }

   public abstract void doWork();
}
