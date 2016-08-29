// c:\eclipse\Workspaces\SDJI3Workspace\BankOfficial\bin>java sdji3Bank.CORBA_BankServer NS_Ref
// set CLASSPATH=.;c:\eclipse\plugins\mysql-connector-java-5.1.22-bin.jar 

package tier3;

import services.Server;
import org.omg.PortableServer.*;

public class CORBAServer {

   public static void main(String[] args) {

      Server s = new Server(args);
      // create Servant objects

      System.out.println("Starting Jysk-DB");
      JyskDatabase db = new JyskDatabase("jysk", "article");
      // new JyskDatabase("jysk", "pallet");

      Servant controller = new ControllerImpl();
      Servant crane = new CraneImpl();
      Servant goods = new GoodsInputImpl();

      s.attach(controller);
      s.attach(crane);
      s.attach(goods);

      s.closeFile();
      s.run();
   }
}
