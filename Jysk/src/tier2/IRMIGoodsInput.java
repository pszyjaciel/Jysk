package tier2;

import java.rmi.RemoteException;
import java.rmi.Remote;
import java.sql.SQLException;

import jysk.Pallet;

public interface IRMIGoodsInput extends Remote {

   boolean putOnBelt(Pallet pal) throws RemoteException, SQLException;

}