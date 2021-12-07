package buisnessLogic;

import java.util.List;
import carLot_DAOs.CarDAOclass;
import carLot_DAOs.OfferDAOclass;
import carLot_DAOs.PaymentDAOclass;
import carLot_Models.Car;
import carLot_Models.Offer;
import carLot_Models.Payment;
import carLot_Utilities.UserInput;

public class CustomerLogic {

	//* As a customer, I can view the cars on the lot.
	//* As a customer, I can make an offer for a car.
	//* As a customer, I can view the cars that I own.
	//* As a customer, I can view my remaining payments for a car.
	//* As the system, I can calculate the monthly payment.
	
	private static String nameOfCustomer;
	private static CarDAOclass cdc = new CarDAOclass();
	private static OfferDAOclass odc = new OfferDAOclass();
	private static PaymentDAOclass pdc = new PaymentDAOclass();
	
	public static String getNameOfCustomer() {
		return nameOfCustomer;
	}

	public static void setNameOfCustomer(String nameCust) {
		nameOfCustomer = nameCust;
	}
	
	public static void CustomerMenu () {
		while (true) {
			System.out.println("");
			System.out.println("===================================================");
			System.out.println("|            Customer Menu                        |");
			System.out.println("|                                                 |");
			System.out.println("|  All cars have a total of 60 monthly payments.  |");
			System.out.println("|                                                 |");
			System.out.println("|       1.) View all cars and monthly payments    |");
			System.out.println("|       2.) Make an offer for a car               |");
			System.out.println("|       3.) View my cars and remaining payments   |");
			System.out.println("|       4.) Exit to User Menu                     |");
			System.out.println("|                                                 |");
			System.out.println("===================================================");
			System.out.println("");
			System.out.print("Your Choice? ");
			
			//Scanner input 
			int choiceMade=4;
			if (UserInput.interactive) {
				String keyboardInput = "";
				keyboardInput = UserInput.getKeyboard.nextLine();
				if (keyboardInput.equals("q") || keyboardInput.equals("Q")) return;
				
				if (UserInput.isNumeric(keyboardInput)) {
					choiceMade = Integer.parseInt(keyboardInput); 
				}
				else {
					//System.out.println("Invalid menu choice.");
					choiceMade = 5;
				}
			}
			
			switch(choiceMade) {
			case 1:
				ViewAllCars();
				break;
			case 2:
				MakeAnOffer();
				break;
			case 3:
				ViewMyCars();
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid menu choice.");
			}
		}
		
	}
	
	public static void ViewAllCars() {
		List<Car> allCars;
		int monthlyPayment;
		
		System.out.println("");
		System.out.println("=========================================");
		System.out.println("|        View all cars on the lot       |");
		System.out.println("=========================================");	
		System.out.println("\nThese are all of the cars on the lot:\n");
		allCars = cdc.readAllCars();
		Car.displayHeader();
		
		for(Car iterateCar : allCars ) {
			monthlyPayment = iterateCar.getCarPrice()/60;      //Calculate monthly payment
			if (iterateCar.getCarOwner().equals("DEALER")) {
				iterateCar.displayOnScreen();
				System.out.println(" Monthly Payment: $"+ monthlyPayment);
			}
		}		
	}
	
	public static void MakeAnOffer() {
		
		List<Car> allCars;
		String inputString;
		int monthlyPayment;
		Offer newOffer = new Offer();
		Car carForOffer;
		
		System.out.println("");
		System.out.println("=========================================");
		System.out.println("|        Make an Offer for a car        |");
		System.out.println("=========================================");	
		System.out.println("\nThese are all of the cars on the lot:\n");
		allCars = cdc.readAllCars();
		
		Car.displayHeader();
		for(Car iterateCar : allCars ) {
			monthlyPayment = iterateCar.getCarPrice()/60;      //Calculate monthly payment
			if (iterateCar.getCarOwner().equals("DEALER")) {
				iterateCar.displayOnScreen();
				System.out.println(" Monthly Payment: $"+ monthlyPayment);
			}
		}	
				
		//System.out.println(allCars);
		System.out.print("\nWhat is the VIN of the car you want to make an offer on? ");
		
		inputString = "1333";
		if (UserInput.interactive) inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		
		if (UserInput.isNumeric(inputString)) {
		newOffer.setCarVin(Integer.parseInt(inputString));
		
		carForOffer = cdc.readCar(Integer.parseInt(inputString));
		if (carForOffer != null) {
			
			System.out.println("\nYou are making an offer on this car:\n");
			Car.displayHeader();
			carForOffer.displayOnScreen();
			
			System.out.print("\n\nWhat is the PRICE you would like to offer for this car? ");
			
			inputString = "18500";
			if (UserInput.interactive) inputString = UserInput.getKeyboard.nextLine();
			if (inputString.equals("q") || inputString.equals("Q")) return;
			
			newOffer.setPrice(Integer.parseInt(inputString));
			newOffer.setCustomerName(nameOfCustomer);
			newOffer.setStatus("PENDING");
			
			System.out.println("\nYour offer has been recieved and is now pending.\n");
			
			//Add offer to the database
			odc.createOffer(newOffer);
		}
		else
			System.out.println("You have entered an invalid or non-existent VIN");	
		}
		else
			System.out.println("Non-numeric data entered when a number was expected");	
		
	}
	
	public static void ViewMyCars() {
		List<Car> allCars;
		List<Payment> allPayments;
		int monthlyPayment;
		int paidMoney;
		int totalPayments;
		
		System.out.println("\n\n");
		System.out.println("=========================================");
		System.out.println("|               View my Cars            |");
		System.out.println("=========================================");	
		System.out.println("\n\n\nThese are all of the cars that you own:\n");
		allCars = cdc.readAllCars();
		allPayments = pdc.readAllPayments();
		
		for(Car iterateCar : allCars ) {
			
			monthlyPayment = iterateCar.getCarPrice()/60;      //Calculate monthly payment
			
			if (iterateCar.getCarOwner().equals(nameOfCustomer)) {
				
			
				//System.out.println(iterateCar + " Monthly Payment: $"+ monthlyPayment); //print the car
				
				paidMoney = 0;
				totalPayments = 0;
				for(Payment iteratePayment : allPayments) {
					
					if (iteratePayment.getCarVin() == iterateCar.getCarVin()) {     //if the payment and the car match...
						
						//System.out.println(iteratePayment); //to debug
						
						paidMoney = paidMoney + iteratePayment.getPaymentAmount();  //...total up amount paid...
						
						totalPayments++;                                            //...and total up number of payments
					
					} //end of inner if
					
				} //end of payment for
				iterateCar.displayOnScreen();
				System.out.println("\nOriginal Price: $"+ iterateCar.getCarPrice()+"        MonthlyPayment: $"+monthlyPayment+ "     Payments Made: "+totalPayments); //print the car
				System.out.println("Remaining Balance: $"+(iterateCar.getCarPrice()-paidMoney)+"     Total Paid: $"+ paidMoney+"        Remaining Payments: "+(iterateCar.getCarPrice()-paidMoney)/monthlyPayment);
				System.out.println(" ");
			
			}	//end of if
			
		//System.out.println(allCars);
		
		} //end of car for
	
	} //end of method
	
}
