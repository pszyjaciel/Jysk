package tier2;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.SQLException;

import jysk.GoodsInput;
import jysk.Pallet;

public class RMIGoodsInputImpl extends UnicastRemoteObject implements IRMIGoodsInput {

   GoodsInput ginput;

   public RMIGoodsInputImpl(GoodsInput ginput) throws RemoteException {
      super();
      this.ginput = ginput;
   }

   @Override
   public boolean putOnBelt(Pallet pal) throws RemoteException, SQLException {
      ginput.putOnBelt(pal);
      return true;

   }
}