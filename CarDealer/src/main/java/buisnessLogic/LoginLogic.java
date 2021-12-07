package buisnessLogic;

import carLot_DAOs.CustomerDAOclass;
import carLot_Models.Customer;
import carLot_Utilities.UserInput;

public class LoginLogic {
	
	private static CustomerDAOclass udc = new CustomerDAOclass();

	public static void LoginMenu () {
		while (true) {
			//System.out.println("\n");
			System.out.println("===================================================");
			System.out.println("|                    Login Menu                   |");
			System.out.println("|                                                 |");
			System.out.println("|       1.) Login                                 |");
			System.out.println("|       2.) Create new customer account           |");
			System.out.println("|       3.) Exit the Car Lot                      |");
			System.out.println("|                                                 |");
			System.out.println("===================================================");
			System.out.println(" ");
			System.out.print("Your Choice? ");
			
			//Scanner input that goes into integer choiceMade
			int choiceMade=0;
			
			String keyboardInput = "";
			keyboardInput = UserInput.getKeyboard.nextLine();
			if (keyboardInput.equals("q") || keyboardInput.equals("Q")) return;
			if (UserInput.isNumeric(keyboardInput)) {
				choiceMade = Integer.parseInt(keyboardInput);
				
				switch(choiceMade) {
				case 1:
					UserLogin();
					break;
				case 2:
					MakeNewAccount();
					break;
				case 3:
					return;
				}
			}
			else
				System.out.println("Invalid menu choice.");
				
		}
	}

	public static void MakeNewAccount() {
		Customer newCust = new Customer();
		String inputString;
		System.out.println("");
		System.out.println("=========================================");
		System.out.println("|              New Customer             |");
		System.out.println("=========================================");	
		System.out.println("\nWelcome new customer!");
		System.out.print("\nPlease enter your name: ");
		
		inputString = "Kathy";
		inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		newCust.setCustomerName(inputString);
		
		System.out.print("\nPlease enter your password: ");	
		
		inputString = "lalalala";
		inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		
		newCust.setCustomerPassword(inputString);
		newCust.setEmployeeFlag(0);
		
		udc.createCustomer(newCust);
		
		System.out.println("\nThank you "+newCust.getCustomerName()+" for registering with us!\n");
		
		return;
	}

	public static void UserLogin() {
		String inputString;
		Customer userTyped = new Customer();
		Customer userLookup;
		
		System.out.println("");
		System.out.println("=========================================");
		System.out.println("|                User Login             |");
		System.out.println("=========================================");	
		System.out.print("\nPlease enter your name: ");
		
		//inputString = "Nancy";	  //customer
		inputString = "Bob";    //employee
		inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		userTyped.setCustomerName(inputString);
		
		System.out.print("\nPlease enter your password: ");
		
		inputString = "12345678";
		inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		userTyped.setCustomerPassword(inputString);
		
		userLookup = udc.readCustomer(userTyped.getCustomerName());
		
		
		if (userLookup != null && userTyped.getCustomerName().equals(userLookup.getCustomerName()) &&
			userTyped.getCustomerPassword().equals(userLookup.getCustomerPassword())) {
			if(userLookup.getEmployeeFlag()==0) {
				System.out.println("\nWelcome customer "+userLookup.getCustomerName());
				CustomerLogic.setNameOfCustomer(userLookup.getCustomerName());
				CustomerLogic.CustomerMenu();     //it's a customer
			}				
			if(userLookup.getEmployeeFlag()==1) {
				System.out.println("\nWelcome employee "+userLookup.getCustomerName());
				EmployeeLogic.EmployeeMenu();     //it's an employee				
				
			}

		}
		else {
			System.out.println("User not found. Please log in again, or sign up for a new customer account.\n");
		}
			
	return;
		
	}
}
