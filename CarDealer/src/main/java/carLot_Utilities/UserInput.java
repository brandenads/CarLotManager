package carLot_Utilities;


import java.util.Scanner;


public class UserInput {

	public static final Scanner getKeyboard = new Scanner(System.in);
	
	//User interface flag for automated testing. Set true to make it user interactive, set
	//false to make it automatic.
	public static boolean interactive;
	
	//tester to avoid string to number conversion exceptions
	//modified from a function found at https://www.baeldung.com/java-check-string-number
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int testInt = Integer.parseInt(strNum);
	        testInt=testInt+5; //just to get rid of unused variable warning.
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
}
