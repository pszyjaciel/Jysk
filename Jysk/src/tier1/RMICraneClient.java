package tier1;

import java.net.MalformedURLException;
import java.rmi.*;

import tier2.IRMICrane;
import jni.NativeServer;
import jysk.Pallet;

public class RMICraneClient {

   private IRMICrane icrane;
   private NativeServer ns;

   public RMICraneClient() {  // constructor
      try {
         icrane = (IRMICrane) Naming.lookup("//127.0.0.1:2089/RMICraneImpl");
         ns = new NativeServer();
      }
      catch (MalformedURLException | RemoteException | NotBoundException e1) {
         e1.printStackTrace();
      }
   }

   {

      for (int i = 0; i < 10; i++) {
         // Pallet pal = null; // mus be initialized before
         // pal = ictrl.getPallet(palid);
         // boolean res = icrane.takeFromBelt(pal);
         System.out.println("RMICraneClient takes a pallet from the belt.");

         try {
            Thread.sleep(800);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }

   public static void main(String args[]) {

      new RMICraneClient();
   }
}