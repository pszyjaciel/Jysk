// the goods client is working constantly..

package tier1;

import java.net.MalformedURLException;
import java.rmi.*;
import java.sql.SQLException;

import tier2.IRMIController;
import tier2.IRMIGoodsInput;
import jni.NativeServer;
import jysk.Pallet;

public class RMIGoodsClient {

   private IRMIController ictrl;
   private IRMIGoodsInput igoods;
   private NativeServer ns;

   public RMIGoodsClient() {  // constructor
      try {
         igoods = (IRMIGoodsInput) Naming.lookup("//127.0.0.1:2089/RMIGoodsInputImpl");
         ns = new NativeServer();
      }
      catch (MalformedURLException | RemoteException | NotBoundException e1) {
         e1.printStackTrace();
      }
   }

   {
      int palid = 10; // chwilowo
      Pallet pal;

      for (int i = 0; i < 10; i++) {
         try {
            // pal = ictrl.getPallet(palid); // zle
            // boolean res = igoods.putOnBelt(pal);
            System.out.println("RMIGoodsClient puts the pallet on the belt.");
            Thread.sleep(1000);
         }
         catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
   }

   public static void main(String args[]) {

      new RMIGoodsClient();
   }
}