#include <iostream>
#include <string>
#include "Header.h"

int main()
{
    std::string response = "";
    do {

        if (response == "quit")
            break;

        std::cout << "Please specify the number system of the input number. Or, type \"quit\" to exit.\n\t1) Base 10 (decimal)\n\t2) Base 16 (hexadecimal)\n) ";
        std::cin >> response;

        std::string num = "";

        if (response == "1") {
            std::cout << "first option\n";
            std::cout << "Enter a decimal number:\n";
            std::cin >> num;
            //decimal conversion to binary & hexadecimal
            std::cout << "The binary number is: " + decimalToBinary(num) + " and the hexadecimal number is: " + decimalToHexadecimal(num) + "\n\n";
        }

        if (response == "2") {
            std::cout << "second option\n";
            std::cout << "Enter a hexadecimal number:\n";
            std::cin >> num;
            //hexadecimal conversion to binary & decimal
            std::cout << "The binary number is: " + hexadecimalToBinary(num) + " and the decimal number is: " + hexadecimalToDecimal(num) + "\n\n";

        }

    } while (response != "quit");
    std::cout << "quitted\n";

}
