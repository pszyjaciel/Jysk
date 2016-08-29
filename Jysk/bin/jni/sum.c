// Description: Some legacy code

#include <stdio.h>
#include "sum.h"

int sum(int x, int y) {
	int result = x + y;
	return result;
}

double division(double x, double y) {
	double result = x / y;
	return result;
}

double dSum(double x, double y) {
	double result = x + y;
	return result;
}

double dArea(double x, double y) {
	double result = x * y;
	return result;
}

int gInt() {
    printf("\nFrom sum.c getInt: Insert some digit..");
	int digit;
	scanf("%d", &digit);
	return digit;
}

char gChar(char myChar) {
	char rChar;
	scanf("%c", &rChar);
	return rChar;
}

void calculateCircle(double radius, double buf) {

}
