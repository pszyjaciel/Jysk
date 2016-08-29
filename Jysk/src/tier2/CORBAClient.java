// c:\eclipse\Workspaces\SDJI3Workspace\BankOfficial\bin>java sdji3Bank.CORBA_BankClient NS_Ref
// set CLASSPATH=.;c:\eclipse\glassfish3\glassfish\lib\mysql-connector-java-5.1.22-bin.jar 
// java tier3.CORBA_JyskServer NSREF
// java tier2.CORBA_JyskClient NSREF

// CorbaClient musi byc w jednej klasie z RMIServer albo przynajmniej miec jakies polaczenie

package tier2;

import jysk.Controller;
import jni.NativeServer;
import jysk.Article;
import jysk.ControllerHelper;
import jysk.Crane;
import jysk.CraneHelper;
import jysk.GoodsInput;
import jysk.GoodsInputHelper;
import jysk.Pallet;

import services.Cin;
import services.Client;

public class CORBAClient extends Client {
   private Controller ctrl;
   private Crane icrane;
   private GoodsInput igoods;
   private NativeServer ns;

   public CORBAClient(String args[]) {
      super(args); // argumentem jest plik: NSRef
      ctrl = ControllerHelper.narrow(getObjectReference());
      icrane = CraneHelper.narrow(getObjectReference());
      igoods = GoodsInputHelper.narrow(getObjectReference());

      ns = new NativeServer();

      closeBuffer();
   }

   public Controller getController() {
      return ctrl;
   }

   public Crane getCrane() {
      return icrane;
   }

   public GoodsInput getGoodsInput() {
      return igoods;
   }

   public void doWork() {
      System.out.println("Client.doWork:");
      testing();
   }

   private void testing() {
      int choice;

      do {
         menu();
         choice = Cin.readInt();
         switch (choice)
         {
            case 1: // create an article
               System.out.println("Type in: articleid, articlename, articleweight");
               int articleid = Cin.readInt();
               String articlename = Cin.readString();
               double articleweight = Cin.readDouble();

               Article art = new Article(articleid, articlename, articleweight);
               boolean res = ctrl.addArticle(art);
               System.out.println("Article has been created: " + res);
               break;

            case 2: // create a pallet
               System.out.println("Type in: palletid, articleid, articleamount, palletweight");
               int palletid = Cin.readInt();
               articleid = Cin.readInt();
               double articleamount = Cin.readDouble();
               double palletweight = Cin.readDouble();

               Pallet pal = new Pallet(palletid, articleid, articleamount, palletweight);
               res = ctrl.addPallet(pal);

               System.out.println("Pallet has been created: " + res);
               break;

            case 4:
               System.out.print("Type id of article: ");
               int artid = Cin.readInt();
               art = ctrl.getArticle(artid);
               System.out.println("FROM CORBAClient: " + art.articleid + " : " + art.articlename + " : "
                     + art.articleweight);

               break;

            case 5:
               Article[] artList = ctrl.getArtList();
               for (int i = 0; i < artList.length; i++) {
                  System.out.println("id: " + artList[i].articleid + "\t\tname: " + artList[i].articlename
                        + "\t\tweight: " + artList[i].articleweight);
               }
               break;

            case 6:
               System.out.println("Type id of pallet)");
               int palid = Cin.readInt();
               pal = ctrl.getPallet(palid);
               System.out.println("From CORBACLIENT: " + pal.palletid + " : " + pal.articleid + " : "
                     + pal.articleamount + " : " + pal.palletweight);
               break;

            default:
               break;
         }
      }
      while (choice > 0);
   }

   private void menu() {
      System.out.println(" BANK");
      System.out.println(" 1) Create article");
      System.out.println(" 2) Create pallet");
      System.out.println(" 4) Get an article");
      System.out.println(" 5) Get list of articles");
      System.out.println(" 6) Get a pallet");

      System.out.println(" 0) Quit");
      System.out.print(" Type Choice: ");
   }

   public static void main(String args[]) {
      Client c = new CORBAClient(args);
      c.doWork();
   }
}
