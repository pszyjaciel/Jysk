package tier2;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.SQLException;

import jysk.Article;
import jysk.Controller;
import jysk.Pallet;

public class RMIControllerImpl extends UnicastRemoteObject implements IRMIController {

   private Controller controller;
   private Belt belt;
   private BoundedBuffer<Pallet> buffer;

   public RMIControllerImpl(Controller ictrl) throws RemoteException {
      super();
      this.controller = ictrl;
      BoundedBuffer buffer = new BoundedBuffer<Pallet>(10);
   }

   @Override
   public boolean addArticle(Article art) {
      controller.addArticle(art);
      return true;
   }

   @Override
   public boolean addPallet(Pallet pal) {
      buffer.put(pal);
      belt.put(pal);
      controller.addPallet(pal);
      return true;
   }

   @Override
   public Article getArticle(int artid) throws RemoteException, SQLException {
      Article art = controller.getArticle(artid);
      return art;
   }

   @Override
   public Article[] getArtList() throws RemoteException, SQLException {
      Article[] artList = controller.getArtList();
      return artList;
   }

   @Override
   public Pallet[] getPalList() throws RemoteException, SQLException {
      Pallet[] palList = controller.getPalList();
      return palList;
   }

   @Override
   public Pallet getPallet(int palid) throws RemoteException, SQLException {
      {
         // storage.run();
         Pallet pal = controller.getPallet(palid);
         return pal;
      }
   }
}