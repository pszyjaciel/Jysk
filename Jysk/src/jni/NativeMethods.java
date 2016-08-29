package jni;

public class NativeMethods {
   public static native void displayHelloWorld();

   public static native double dSum(double x, double y);

   public static native double division(double x, double y);

   public static native double area(double x, double y);

   public static native int getInt();

   public static native char getChar(char myChar);

   public static native String getString(String prompt);

   public static native String getString2();

   public static native double sumOfArray(double[] myArr);

   public static native double[] circle1(double radius);

   public static native int testarrayint(int[] myarray);

   public static native double getAllWeights(double[] myArrDouble);

   public static native int getAllArticles(int[] myArrInt);

   public static native double getAvgVolume(double[] myArrDouble);

}
