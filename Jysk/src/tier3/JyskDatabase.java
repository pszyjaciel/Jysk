package tier3;

import java.sql.SQLException;
import java.util.ArrayList;

import jysk.Article;
import jysk.Pallet;

public class JyskDatabase {
   private Database db;

   private String table;

   public JyskDatabase(String dbName, String table) {
      this.table = table;
      try {
         this.db = new Database(dbName);
      }
      catch (ClassNotFoundException e) {
         e.printStackTrace();
         System.out.println("MySQL driver not found. Exiting.");
         System.exit(1);
      }
   }

   public synchronized void createNewArticle(Article art) throws SQLException {
      // The id (primary key) is auto incremented
      String sql = "INSERT INTO " + table + "(articleid, articlename, articleweight) VALUES (?, ?, ?);";
      db.update(sql, art.articleid, art.articlename, art.articleweight);
   }

   public synchronized void createNewPallet(Pallet pallet) throws SQLException {
      String sql = "INSERT INTO " + table + " (palletid, articleid, articleamount, palletweight) VALUES (?, ?, ?, ?);";
      // System.out.println("FROM BankDatabase.insertDB(bank.Account acc): " + sql);
      db.update(sql, pallet.palletid, pallet.articleid, pallet.articleamount, pallet.palletweight);
   }

   public Article getArticle(int articleid) throws SQLException { // na poczatek tylko objekt
      String sql = "SELECT * FROM article WHERE articleid = " + articleid;
      System.out.println("FROM JyskDatabase.getArticle(): " + sql);
      Article[] arts = new Article[10];
      ArrayList<Object[]> result = db.query(sql);

      if (result != null && result.get(0) != null && result.get(0).length > 0) {
         for (int i = 0; i < result.size(); i++) {
            int artid = ((Integer) result.get(i)[0]).intValue();
            String aName = (String) result.get(i)[1].toString();
            double aWeight = ((Number) result.get(i)[2]).doubleValue();
            arts[i] = new Article(artid, aName, aWeight);
         }
      }
      Article article = new Article(arts[0].articleid, arts[0].articlename, arts[0].articleweight);
      return article;
   }

   public Article[] getArticleList() throws SQLException {
      String sql = "SELECT * FROM article;";
      System.out.println("FROM JyskDatabase.getArticleList(): " + sql);
      ArrayList<Object[]> result = db.query(sql);
      Article[] arts = new Article[result.size()];

      if (result != null && result.get(0) != null && result.get(0).length > 0) {
         for (int i = 0; i < arts.length; i++) {
            int artid = ((Integer) result.get(i)[0]).intValue();
            String aName = (String) result.get(i)[1].toString();
            double aWeight = ((Number) result.get(i)[2]).doubleValue();
            arts[i] = new Article(artid, aName, aWeight);
         }
      }
      return arts;
   }

   public Pallet[] getPalletList() throws SQLException {
      String sql = "SELECT * FROM pallet;";
      System.out.println("FROM JyskDatabase.getPalletList(): " + sql);
      ArrayList<Object[]> result = db.query(sql);
      Pallet[] pals = new Pallet[result.size()];

      if (result != null && result.get(0) != null && result.get(0).length > 0) {
         for (int i = 0; i < pals.length; i++) {
            int palletid = ((Integer) result.get(i)[0]).intValue();
            int articleid = ((Integer) result.get(i)[1]).intValue();
            double articleamount = ((Number) result.get(i)[2]).doubleValue();
            double palletweight = ((Number) result.get(i)[3]).doubleValue();
            pals[i] = new Pallet(palletid, articleid, articleamount, palletweight);
         }
      }
      return pals;
   }

   public Pallet getPallet(int palletid) throws SQLException {
      String sql = "SELECT * FROM pallet WHERE palletid = " + palletid;
      System.out.println("FROM BankDatabase.getAccounts(): " + sql);
      Pallet[] pal = new Pallet[10];
      ArrayList<Object[]> result = db.query(sql);

      if (result != null && result.get(0) != null && result.get(0).length > 0) {
         for (int i = 0; i < result.size(); i++) {
            int palid = ((Integer) result.get(i)[1]).intValue();
            int artid = ((Integer) result.get(i)[2]).intValue();
            double artAmount = ((Number) result.get(i)[3]).doubleValue();
            double palWeight = ((Number) result.get(i)[4]).doubleValue();
            pal[i] = new Pallet(palid, artid, artAmount, palWeight);
         }
      }
      Pallet pallet = new Pallet(pal[0].palletid, pal[0].articleid, pal[0].articleamount, pal[0].palletweight);
      return pallet;
   }
}
