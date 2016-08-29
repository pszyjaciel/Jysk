package tier2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import jysk.Article;
import jysk.Pallet;

public interface IRMIController extends Remote {

   boolean addArticle(Article art) throws RemoteException, SQLException;

   boolean addPallet(Pallet pal) throws RemoteException, SQLException;

   Article getArticle(int artid) throws RemoteException, SQLException;

   Pallet getPallet(int palid) throws RemoteException, SQLException;

   Article[] getArtList() throws RemoteException, SQLException;

   Pallet[] getPalList() throws RemoteException, SQLException;
}
