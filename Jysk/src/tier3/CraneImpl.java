// liczy sie kolejnosc uruchamiania! pierwszy jest zawsze server.

package tier3;

import tier2.Belt;
import tier2.BoundedBuffer;

import jysk.CranePOA;
import jysk.Pallet;

public class CraneImpl extends CranePOA {

   private Belt belt;
   private BoundedBuffer<Pallet> buffer;
   JyskDatabase db;
   Pallet pal;

   // constructor
   public CraneImpl() {
      pal = new Pallet();
      buffer = new BoundedBuffer<Pallet>(10);
      db = new JyskDatabase("jysk", "pallet");
   }

   @Override
   public void takeFromBelt(Pallet pal) {
      // Pallet pallet = db.getPallet(pal.palletid);
      belt.take(pal);
   }
}

//
// @Override
// public Pallet getPallet(int palletid) {
// JyskDatabase db = new JyskDatabase("jysk", "pallet");
// Pallet pal;
// try {
// pal = db.getPallet(palletid);
// return pal;
// }
// catch (SQLException e) {
// e.getErrorCode();
// }
// return null;
// }
//
