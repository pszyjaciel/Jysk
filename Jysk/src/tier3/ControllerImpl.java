// liczy sie kolejnosc uruchamiania! pierwszy jest zawsze server.

package tier3;

import jysk.Article;
import java.sql.SQLException;
import jysk.ControllerPOA;
import jysk.Pallet;

public class ControllerImpl extends ControllerPOA {

 
   public ControllerImpl() {
      // gdzie constructor?
   }

   @Override
   public boolean addArticle(Article art) {
      JyskDatabase db = new JyskDatabase("jysk", "article");
      try {
         db.createNewArticle(art);
         return true;
      }
      catch (SQLException e) {
         // e.printStackTrace();
         int ec = e.getErrorCode();
         System.err.println("ERROR WITH ControllerImpl.addArticle() input, Erro.code: " + ec);
      }
      return false;
   }

   @Override
   public boolean addPallet(Pallet pal) {
      JyskDatabase db = new JyskDatabase("jysk", "pallet");
      try {
         db.createNewPallet(pal);
         return true;
      }
      catch (SQLException e) {
         // e.printStackTrace();
         int ec = e.getErrorCode();
         System.err.println("ERROR WITH ControllerImpl.addPallet() input, Err.code: " + ec);
      }
      return false;
   }

   @Override
   public Article getArticle(int articleid) throws IndexOutOfBoundsException {
      JyskDatabase db = new JyskDatabase("jysk", "article");
      Article art;
      try {
         art = db.getArticle(articleid);
         return art;
      }
      catch (SQLException e) {
         e.getErrorCode();
      }
      return null;
   }

   @Override
   public Article[] getArtList() {
      JyskDatabase db = new JyskDatabase("jysk", "article");
      Article[] artList;
      try {
         artList = db.getArticleList();
         return artList;
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public Pallet[] getPalList() {
      JyskDatabase db = new JyskDatabase("jysk", "pallet");
      Pallet[] palList;
      try {
         palList = db.getPalletList();
         return palList;
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public Pallet getPallet(int palletid) {
      JyskDatabase db = new JyskDatabase("jysk", "pallet");
      Pallet pal;
      try {
         pal = db.getPallet(palletid);
         return pal;
      }
      catch (SQLException e) {
         e.getErrorCode();
      }
      return null;
   }
}