package tier3;

import tier2.Belt;
import tier2.BoundedBuffer;

import jysk.GoodsInputPOA;
import jysk.Pallet;

public class GoodsInputImpl extends GoodsInputPOA {

   private Belt belt;
   private BoundedBuffer<Pallet> buffer;

   // constructor
   public GoodsInputImpl() {
      buffer = new BoundedBuffer<Pallet>(10);
   }

   @Override
   public void putOnBelt(Pallet pal) {
      belt.put(pal);
   }
}