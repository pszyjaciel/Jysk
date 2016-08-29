package tier2;

import jysk.Article;
import jysk.Pallet;

public class Belt {
   QueueAsCircularArray<Pallet> qca;

   public Belt(Article art, Pallet pal) {
      qca = new QueueAsCircularArray(10);
   }

   public void put(Pallet pal) {
      qca.put(pal);
   }

   public void take(Pallet pal) {
      qca.take();
   }
}
