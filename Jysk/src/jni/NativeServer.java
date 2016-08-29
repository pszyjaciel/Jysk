package jni;

import jysk.Article;
import jysk.Pallet;

public class NativeServer {

   public NativeServer() { // constructor
      try {
         System.loadLibrary("NativeLib"); // can not be with .dll extension
      }
      catch (NullPointerException | UnsatisfiedLinkError | SecurityException e1) {
         System.out.println("Error on loading a dll-library: " + e1.getMessage());
         System.exit(1);
      }
   }

   // calculate the total weight
   public double calcTotalWeight(Pallet[] myPallets) {

      double[] myArrDouble = new double[myPallets.length];
      for (int i = 0; i < myPallets.length; i++) {
         myArrDouble[i] = myPallets[i].palletweight;
      }

      double result = NativeMethods.getAllWeights(myArrDouble);
      return result;
   }

   // CALCULATE THE AMOUNT OF ALL ARTICKLES
   public int calcAmountOfArticles(Article[] myArticles) {

      int[] myArrInt = new int[myArticles.length];

      for (int i = 0; i < myArticles.length; i++) {
         myArrInt[i] = myArticles[i].articleid;
      }

      int result = NativeMethods.getAllArticles(myArrInt);
      return result;
   }

   // calculate the average volume: = masa/gestosc
   public double calcVolume(Pallet[] myPallets) {

      double[] myArrDouble = new double[myPallets.length];

      for (int i = 0; i < myPallets.length; i++) {
         myArrDouble[i] = myPallets[i].palletweight;
      }

      double result = NativeMethods.getAvgVolume(myArrDouble);
      return result;
   }
}
