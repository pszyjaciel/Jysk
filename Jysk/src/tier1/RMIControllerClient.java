package tier1;

import java.net.MalformedURLException;
import java.rmi.*;
import java.sql.SQLException;

import services.Cin;
import tier2.IRMIController;
import jni.NativeServer;
import jysk.Article;
import jysk.Pallet;

public class RMIControllerClient {

   private IRMIController ictrl;
   private NativeServer ns;

   {
      // lookup, returns a proxy object:
      try {
         ictrl = (IRMIController) Naming.lookup("//127.0.0.1:2089/RMIControllerImpl");
         ns = new NativeServer();
      }
      catch (MalformedURLException | RemoteException | NotBoundException e1) {
         e1.printStackTrace();
      }

      int choice;
      do {
         menu();
         choice = Cin.readInt();
         switch (choice)
         {
            case 1:
               System.out.println("Type in: articleid, articlename, articleweight");
               int articleid = Cin.readInt();
               String articlename = Cin.readString();
               double articleweight = Cin.readDouble();

               Article art = new Article(articleid, articlename, articleweight);
               boolean res;
               try {
                  res = ictrl.addArticle(art);
                  System.out.println("Article has been created: " + res);
               }
               catch (RemoteException | SQLException e) {
                  e.printStackTrace();
               }
               break;

            case 2:
               System.out.println("Type in: palletid, articleid, articleamount, palletweight");
               int palletid = Cin.readInt();
               articleid = Cin.readInt();
               double articleamount = Cin.readDouble();
               double palletweight = Cin.readDouble();

               Pallet pal = new Pallet(palletid, articleid, articleamount, palletweight);
               try {
                  res = ictrl.addPallet(pal);
                  System.out.println("Pallet has been created: " + res);
               }
               catch (RemoteException | SQLException e) {

                  e.printStackTrace();
               }
               break;

            case 3:
               System.out.print("Type id of article: ");
               int artid = Cin.readInt();
               try {
                  art = ictrl.getArticle(artid);
                  System.out.println("FROM CORBAClient: " + art.articleid + " : " + art.articlename + " : "
                        + art.articleweight);
               }
               catch (RemoteException | SQLException e) {
                  e.printStackTrace();
               }

               break;

            case 4:
               System.out.println("Type id of pallet: ");
               int palid = Cin.readInt();
               try {
                  pal = ictrl.getPallet(palid);
                  System.out.println("From CORBACLIENT: " + pal.palletid + " : " + pal.articleid + " : "
                        + pal.articleamount + " : " + pal.palletweight);

               }
               catch (RemoteException | SQLException e) {
                  e.printStackTrace();
               }
               break;

            case 5:
               Article[] artList;
               try {
                  artList = ictrl.getArtList();  // get the list from DB
                  for (int i = 0; i < artList.length; i++) {
                     System.out.println("id: " + artList[i].articleid + "\t\tname: " + artList[i].articlename
                           + "\t\tweight: " + artList[i].articleweight);
                  }

                  int result = ns.calcAmountOfArticles(artList);  // calculate the total amount with the DLL-library
                  System.out.println("The total amount of articles: " + result);

               }
               catch (RemoteException | SQLException e) {
                  e.printStackTrace();
               }
               break;

            case 6:
               Pallet[] palList;
               try {
                  palList = ictrl.getPalList();  // get the list from DB
                  for (int i = 0; i < palList.length; i++) {
                     System.out.println("palID: " + palList[i].palletid + "\tartID: " + palList[i].articleid
                           + "\tamount: " + palList[i].articleamount + "\tweight: " + palList[i].palletweight);
                  }

                  double result = ns.calcTotalWeight(palList);  // calculate the total weight with the DLL-library
                  System.out.println("The total weight of pallets: " + result + " kg");

                  result = ns.calcVolume(palList); // calculate the avarage volume with the DLL
                  System.out.println("The average volume is: " + result + " cubic meters");

               }
               catch (RemoteException | SQLException e) {
                  e.printStackTrace();
               }
               break;

            default:
               break;
         }
      }
      while (choice > 0);

   }

   private void menu() {
      System.out.println("\n JYSK");
      System.out.println(" 1) Create an article");
      System.out.println(" 2) Create a pallet");
      System.out.println(" 3) Get an article");

      System.out.println(" 5) Get list of articles");
      System.out.println(" 6) Get list of pallets");

      System.out.println(" 0) Quit");
      System.out.print(" Type Choice: ");
   }

   public static void main(String args[]) {

      new RMIControllerClient();
   }

}