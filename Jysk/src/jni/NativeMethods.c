#include "jni_NativeMethods.h"
#include <stdio.h>
#include <stdlib.h>
#include "sum.h"
#include <wchar.h>
#include <stddef.h>

JNIEXPORT void JNICALL Java_NativeMethods_displayHelloWorld (JNIEnv *env, jclass cls)
{
	printf("\nFrom NativeMethods_displayHelloWorld: JNI Hello world, it works!\n\n");
	return;
}

JNIEXPORT jdouble JNICALL Java_NativeMethods_division(JNIEnv *env, jclass cls, jdouble x, jdouble y) {
	return division(x, y);
}

JNIEXPORT jdouble JNICALL Java_jni_NativeMethods_dSum(JNIEnv *env, jclass cls, jdouble x, jdouble y) {
	printf("czy dojszlo?");
	return dSum(x, y);
}

JNIEXPORT jdouble JNICALL Java_jni_NativeMethods_area(JNIEnv *env, jclass cls, jdouble x, jdouble y) {
	return dArea(x, y);
}

JNIEXPORT jint JNICALL Java_jni_NativeMethods_getInt(JNIEnv *env, jobject obj) {
	int result = gInt();

	printf("\nFrom NativeMethods_getInt: %d", result);
	return result;
}

JNIEXPORT jchar JNICALL Java_jni_NativeMethods_getChar(JNIEnv *env, jobject obj, jchar myChar) {
	char result = gChar(myChar);
	printf("\nFrom NativeMethods_getChar: %c", result);
	return (result);
}

JNIEXPORT jstring JNICALL Java_jni_NativeMethods_getString(JNIEnv *env, jobject obj, jstring prompt) {
	char buf[128];
	const jbyte *str;
	str = (*env)->GetStringUTFChars(env, prompt, NULL);
	if (str == NULL) {
		return NULL; /* OutOfMemoryError already thrown */
	}
	printf("\nFrom NativeMethods_getString: %s", str);
	(*env)->ReleaseStringUTFChars(env, prompt, str);
	scanf("%s", buf); // pobierz jakis wyraz
	jstring wyraz = (*env)->NewStringUTF(env, buf);
	printf("\nFrom NativeMethods_getString: %s", buf);
	return wyraz;
}

JNIEXPORT jstring JNICALL Java_jni_NativeMethods_getString2(JNIEnv *env, jobject obj) // will not work properly
{
	jstring myNewString;
	jchar chArray[10];

	scanf("%10c", chArray); // pobierz 10 znakow
	chArray[9] = '\0'; // zakoncz zerem

	myNewString = (*env)->NewString(env, chArray, wcslen(chArray));
	printf("\nFrom NativeMethods_getString2: %s", chArray);
	return myNewString;
}

JNIEXPORT jdouble JNICALL Java_jni_NativeMethods_sumOfArray(JNIEnv *env, jclass cls, jdoubleArray arr) {

	printf("\nFrom Java_NativeMethods_sumOfArray: %d", arr);

	jdouble *doubleArr = (*env)->GetDoubleArrayElements(env, arr, NULL);
	jsize length = (*env)->GetArrayLength(env, arr);
	(*env)->ReleaseDoubleArrayElements(env, arr, doubleArr, 0);
	return length;
}

JNIEXPORT jdoubleArray JNICALL Java_jni_NativeMethods_circle1(JNIEnv *env, jclass cls, jdouble radius) {
	jdouble buf[2];
	calculateCircle(radius, buf[2]);
	jdoubleArray resultArr = (*env)->NewDoubleArray(env, 2);
	(*env)->SetDoubleArrayRegion(env, resultArr, 0, 2, buf);
	return resultArr;
}

JNIEXPORT jint JNICALL Java_jni_NativeMethods_testarrayint(JNIEnv *env, jobject obj, jintArray myarray) {
	int i, sum = 0;
	jsize length;
	jint *body;  // pointer

	length = (*env)->GetArrayLength(env, myarray);
	printf("\nFROM Java_jni_NativeMethods_testarrayint: The length of the array is: %d%\n", length);

	body = (*env)->GetIntArrayElements(env, myarray, 0);
	for (i = 0; i < length; i++) {
		printf("%S%d%s%d%\n", "myarray[", i, "]: ", body[i]);
		sum += body[i];
	}

	/*Release body*/
	(*env)->ReleaseIntArrayElements(env, myarray, body, 0);
	return (sum);
}

JNIEXPORT jdouble JNICALL Java_jni_NativeMethods_getAllWeights(JNIEnv *env, jobject obj, jdoubleArray myarray) {
	int i;
	double sum = 0;
	jsize length;
	jdouble *body;  // pointer

	length = (*env)->GetArrayLength(env, myarray);

	body = (*env)->GetDoubleArrayElements(env, myarray, 0);
	for (i = 0; i < length; i++) {
		sum += body[i];
	}

	/*Release body*/
	(*env)->ReleaseDoubleArrayElements(env, myarray, body, 0);
	return (sum);
}

JNIEXPORT jint JNICALL Java_jni_NativeMethods_getAllArticles(JNIEnv *env, jobject obj, jintArray myarray) {

	int i, sum = 0;
	jsize length;
	jint *body;  // pointer

	// printf("%s", 1234);
	length = (*env)->GetArrayLength(env, myarray);
	return length;
}

JNIEXPORT jdouble JNICALL Java_jni_NativeMethods_getAvgVolume(JNIEnv *env, jobject obj, jdoubleArray myarray) {

	const ro = 500; // 500kg/m3

	int i;
	double sum = 0;
	jsize length;
	jdouble *body;  // pointer

	length = (*env)->GetArrayLength(env, myarray);

	body = (*env)->GetDoubleArrayElements(env, myarray, 0);
	for (i = 0; i < length; i++) {
		sum += body[i] / ro;
	}

	/*Release body*/
	(*env)->ReleaseDoubleArrayElements(env, myarray, body, 0);
	return (sum);
}

// http://rab.ict.pwr.wroc.pl/~kreczmer/po/materialy/03-referencje.pdf
// Java_NativeMethods_getInt : getInt to nazwa metody w NativeMethods.java
// JNIEXPORT i JNICALL to sa makra
// JNIEnv *env to interface pointer

// http://diglib.stanford.edu:8091/~testbed/doc/JavaUsage/JNI/tutorial.txt
// ogolnie o c: http://www.mimuw.edu.pl/~przemek/przemek1_files/wstep.pdf
