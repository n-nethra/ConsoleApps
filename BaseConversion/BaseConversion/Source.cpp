#include <iostream>
#include <string>
#include <math.h>
#include <string.h>
using namespace std;

std::string numToLetter(int num) {
	switch (num) {
	case 10:
		return "A";
		break;
	case 11:
		return "B";
		break;
	case 12:
		return "C";
		break;
	case 13:
		return "D";
		break;
	case 14:
		return "E";
		break;
	case 15:
		return "F";
		break;
	default:
		return to_string(num);
	}

}



std::string decimalToHexadecimal(std::string deciString) {
	int deciNum = stoi(deciString);
	string hexaNum;
	int currentNum = deciNum;
	while (currentNum > 0) {
		hexaNum = numToLetter(currentNum % 16) + hexaNum;
		currentNum = currentNum / 16;
	}
	return hexaNum;
}

int letterToNum(char letter) {
	if (letter == 'A') {
		return 10;
	}
	else if (letter == 'B') {
		return 11;
	}
	else if (letter == 'C') {
		return 12;
	}
	else if (letter == 'D') {
		return 13;
	}
	else if (letter == 'E') {
		return 14;
	}
	else if (letter == 'F') {
		return 15;
	}
	else {
		return int(letter)-48;
	}
}


std::string hexadecimalToDecimal(std::string hexaNum) {
	int deciNum = 0;
	for (int i = 0; i < hexaNum.size(); i++) {
		int basePower = pow(16, i);
		int digit = letterToNum((hexaNum).at(hexaNum.size() - i - 1));
		if (basePower * digit != 0) {
			deciNum += basePower * digit;
		}
	}
	return std::to_string(deciNum);
}
/*
maybe hexadecimal --> decimal to convert to binary from binaryToDecimal() watch khan academy vid to see conversions


*/

std::string decimalToBinary(std::string deciString) {
	int deciNum = stoi(deciString);
	string binNum;
	int currentNum = deciNum;
	while (currentNum > 0) {
		binNum = to_string(currentNum % 2) + binNum;
		currentNum = currentNum / 2;
	}
	if (binNum == "") {
		binNum = "0";
	}
	return binNum;
}



std::string binaryToDecimal(std::string binNum) {
	int deciNum = 0;
	for (int i = 0; i < binNum.size(); i++) {
		int basePower = pow(2, i);
		int digit = (int) (binNum).at(binNum.size() - i - 1) - 48;
		if (basePower * digit != 0) {
			deciNum += basePower * digit;
		}
	}
	return std::to_string(deciNum);
}

std::string hexadecimalToBinary(std::string hexaString) {
	string deciNum = hexadecimalToDecimal(hexaString);
	return decimalToBinary(deciNum);
}

