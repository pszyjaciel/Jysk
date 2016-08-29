// przywywolaniu nalezy podac plik jako argument: NSRef
package tier2;

import java.net.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;

import javax.naming.NamingException;

import tier2.RMIControllerImpl;
import tier2.CORBAClient;

public class RMIServer {

   public static void main(String args[]) throws NamingException, MalformedURLException, RemoteException {
      LocateRegistry.createRegistry(2089); // zamiast sec.manager

      try {
         CORBAClient cclient = new CORBAClient(args); // musi byc argument: NSRef
         RMIControllerImpl ictrl = new RMIControllerImpl(cclient.getController());
         RMICraneImpl icrane = new RMICraneImpl(cclient.getCrane());
         RMIGoodsInputImpl igoods = new RMIGoodsInputImpl(cclient.getGoodsInput());

         Naming.rebind("//127.0.0.1:2089/RMIControllerImpl", ictrl);
         System.out.println("RMIServer on RMIControllerImpl is ready.");

         Naming.rebind("//127.0.0.1:2089/RMICraneImpl", icrane);
         System.out.println("RMIServer on RMICraneImpl is ready.");

         Naming.rebind("//127.0.0.1:2089/RMIGoodsInputImpl", igoods);
         System.out.println("RMIServer on RMIGoodsInputImpl is ready.");

      }
      catch (RemoteException exn) {
         System.out.println("RemoteException: " + exn);
      }
      catch (MalformedURLException exn) {
         System.out.println("MalformedURLException: " + exn);
      }
   }
}

// Error: java.rmi.server.ExportException: remote object implements illegal remote interface; nested exception is:
// java.lang.IllegalArgumentException: illegal remote method encountered: public abstract boolean
// tier1.IRMIController.addPallet(jysk.Pallet)

// Solution: All methods in IRMIController must have .. throws RemoteException