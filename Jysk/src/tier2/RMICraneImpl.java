package tier2;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.SQLException;

import jysk.Crane;
import jysk.Pallet;

public class RMICraneImpl extends UnicastRemoteObject implements IRMICrane {

   private Crane crane;

   public RMICraneImpl(Crane crane) throws RemoteException {
      super();
      this.crane = crane;
   }

   @Override
   public boolean takeFromBelt(Pallet pal) throws RemoteException, SQLException {
      crane.takeFromBelt(pal);
      return true;
   }
}