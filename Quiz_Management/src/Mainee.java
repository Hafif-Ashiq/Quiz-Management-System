//package com.company;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
public class Mainee {
    public static void main(String args[]) throws ParseException {

        // Creating Scanner Object
        Scanner input = new Scanner(System.in);


        // Initializing variables
        long cardNumber;
        String card_No;
        String date;
        // Validating input

        System.out.println("Enter the credit card Number: ");
        cardNumber = input.nextLong();

        String blank = input.nextLine();

        while (true){
            System.out.println("Enter the credit card expiry date having format mm/yy: ");
            date = input.nextLine();

            //date formate
            Calendar cal = GregorianCalendar.getInstance();
            if (cal.get(Calendar.YEAR) < 2022) {
                if(date.matches("[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}")) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
                    try {
                        Date d = sdf.parse(date);
                        String S2 = sdf.format(d);
                        System.out.println(S2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else
                {
                    System.out.println("not valid date formate");
                }
                throw new ParseException("Date under 1970 are not allowed!", 6);
            }
            else {
//            System.out.println(date);
                String[] date_array = date.split("/");
                int year_date = Integer.parseInt(date_array[1]);
                if (year_date >= 22) {
                    System.out.println("valid credit card date");
                    break;
                }else
                    System.out.println("Card Expiry date is invalid!!");

            }
        }

        // Checking if credit card number is valid or not
        boolean validNumber = isValid(cardNumber);

        if (validNumber) // IF Valid
            System.out.println(cardNumber + " is valid...");

        else // IF Not Valid
            System.out.println(cardNumber + " is In-valid...");

    }
    // Creating method to check if the credit card number is valid
    public static boolean isValid(long number){



// Checking prefix of number to be 4,5,37 or 6 and number of digits are between 13 and 16
        if (prefixMatched(number,4) || prefixMatched(number,5) || prefixMatched(number,37) || prefixMatched(number,6) && getSize(number)>=13 && getSize(number)<=16){
// Taking sum of double even and odd number places
            int sumOfEvenAndOdd = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);

            // If sum is divisible by 10, the number is valid else not valid
            if (sumOfEvenAndOdd % 10 == 0)
                return true;

            else
                return false;


        }

        else
            return false;

    }



    /** Get the result from Step 2 */
    public static int sumOfDoubleEvenPlace(long number){
// Declaring & Initializing Variables
        int mod , double_mod, digit;
        int count = 1;
        int sum = 0;

// Getting sum
        do{
            // Taking numbers from right to left
            mod = (int)(number % 10);
            number /= 10;

            if (count % 2 == 0){
                // Getting double of number at even places
                double_mod =  mod * 2;
                // Getting sum of each digit in double
                digit = getDigit(double_mod);
                // Adding the value of digit in sum
                sum = sum + digit;


            }
            // incrementing place after every iteration
            count++;


        }while(number !=0); // Repeating till the most left digit of the number

        // Return sum to function
        return sum;

    }


    // Return number for doubling of the numbers at  even places
    public static int getDigit(int number){
// If number has single digit then return number as it is
        if (number >= 0 && number <=9)
            return number;
// If number has 2 digits add both digits
        else{
            // getting last digit
            int mod = number % 10;
            // Getting first digit
            int remaining = number / 10;
// Get the number
            int digit = remaining + mod;
// Return number
            return digit;
        }

    }
    // Return sum of odd-place digits in number
    public static int sumOfOddPlace(long number){
// Initialzing and declaring variables
        int mod;
        int sum = 0;
        int count = 1;


// Starting do while to calculate sum of numbers at odd places
        do{
            // getting digits fro right to left
            mod = (int)(number % 10);
            number /= 10;

            // Checkind odd places and adding the number to the sum
            if (count % 2 != 0){


                sum = sum + mod;
            }
            // Incrementing place number for loop
            count++;


        }while(number !=0);// Repeating until the left most digit of  number
// Returning sum value to function
        return sum;

    }

    // Return true if the digit d is a prefix for number
    // Checking prefix of the number

    public static boolean prefixMatched(long number, int d){
        // Checking if the prefix matches the number d
        if(getPrefix(number,getSize(d)) == d){
            return true;
        }
        else
            return false;


    }



    // Return the number of digits in entered number
    public static int getSize(long d){
        // Convert number to string
        String string_num = Long.toString(d);
// return string length to get number of digits in entered number
        return string_num.length();

    }

    /** Return the first k number of digits from number. If the
     * number of digits in number is less than k, return number. */


    public static long getPrefix(long number, int k) {

        if (getSize(number) > k){
            // changing number to string
            String card_Num = Long.toString(number);
            String sub_card = card_Num.substring(0, k);

            Long num = Long.parseLong(sub_card);

            // Returning num
            return num;
        }

        return number;

    }
    }