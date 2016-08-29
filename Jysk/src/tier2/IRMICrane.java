package tier2;

import java.rmi.RemoteException;
import java.rmi.Remote;
import java.sql.SQLException;

import jysk.Pallet;

public interface IRMICrane extends Remote {

   boolean takeFromBelt(Pallet pal) throws RemoteException, SQLException;

}
