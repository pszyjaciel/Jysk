// *****************************************************************************
// File name  : Server.java
// Start date : 
// Programmer : Hans So.
// Java       : 
// Description: Hello demo; stringified object reference has been used
//              Structured the server.
// Revision history:
//   date     init  comment
//
// *****************************************************************************

package services;

import java.io.*;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class Server {
   private ORB theOrb;
   private POA thePoa;
   private String[] theArgs;
   private PrintWriter pw;

   public Server(String[] args) {
      theArgs = args;
      try { // init ORB
         theOrb = ORB.init(args, null);
         // init POA
         thePoa = POAHelper.narrow(theOrb.resolve_initial_references("RootPOA"));
         thePoa.the_POAManager().activate();
         // init file
         pw = new PrintWriter(new FileWriter(theArgs[0]));
      }
      catch (Exception e) {
         System.out.println(e);
      }
   }

   public void attach(Servant theServantObj) {
      try { // create the object reference
         org.omg.CORBA.Object obj = thePoa.servant_to_reference(theServantObj);
         // print stringified object reference to file
         pw.println(theOrb.object_to_string(obj));
         pw.flush();
         System.out.println("stringified object reference to file");
      }
      catch (Exception e) {
         System.err.println(e);
      }
   }

   public void closeFile() {
      pw.close();
   }

   public void run() {
      System.out.println("server is ready ...");
      // wait for requests
      theOrb.run();
   }
}