package buisnessLogic;

import java.util.List;
import carLot_DAOs.CarDAOclass;
import carLot_DAOs.OfferDAOclass;
import carLot_DAOs.PaymentDAOclass;
import carLot_Models.Car;
import carLot_Models.Offer;
import carLot_Models.Payment;
import carLot_Utilities.UserInput;

public class EmployeeLogic {
	
	//* As an employee, I can add a car to the lot.
	//* As an employee, I can accept or reject an offer for a car.
	//* As an employee, I can remove a car from the lot.
	//* As an employee, I can view all payments.
	//* As the system, I reject all other pending offers for a car when an offer is accepted.
	
	private static String EmployeeName;
	private static CarDAOclass cdc = new CarDAOclass();
	private static PaymentDAOclass pdc = new PaymentDAOclass();
	private static OfferDAOclass odc = new OfferDAOclass();
	
	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}
	
	public static void EmployeeMenu () {
		
		while (true) {
			System.out.println("\n\n");
			System.out.println("=========================================");
			System.out.println("|            Employee Menu              |");
			System.out.println("|                                       |");
			System.out.println("|       1.) Add a Car to the Lot        |");
			System.out.println("|       2.) Remove a Car from the Lot   |");
			System.out.println("|       3.) Accept Offers               |");
			System.out.println("|       4.) Reject Offers               |");
			System.out.println("|       5.) View all Payments made      |");
			System.out.println("|       6.) Exit to User Menu           |");
			System.out.println("|                                       |");
			System.out.println("=========================================");
			System.out.println(" ");
			System.out.print("Your Choice? ");
			
			//Scanner input that goes into integer choiceMade
			int choiceMade=6;
			if (UserInput.interactive) {
				String keyboardInput = "";
				keyboardInput = UserInput.getKeyboard.nextLine();
				if (keyboardInput.equals("q") || keyboardInput.equals("Q")) return;
				
				if (UserInput.isNumeric(keyboardInput)) {
					choiceMade = Integer.parseInt(keyboardInput); 
				}
				else {
					//System.out.println("Invalid menu choice.");
					choiceMade = 7;
				}
					
			}
			
			switch(choiceMade) {
			case 1:
				AddCar();
				break;
			case 2:
				RemoveCar();
				break;
			case 3:
				AcceptOffer();
				break;
			case 4:
				RejectOffer();
				break;
			case 5:
				ViewAllPayments();
				break;
			case 6:
				return;
			default:
				System.out.println("Invalid menu choice.");
			}
		}
	}
	
	public static void RejectOffer() {
		String inputString = "0";
		Offer offerToReject = null;
		
		//Get a list of offers
		List<Offer> offerList = odc.readAllOffers();
		
		System.out.println("");
		System.out.println("=========================================");
		System.out.println("|             Reject an offer           |");
		System.out.println("=========================================");	
		System.out.println("\nThese are all of offers that have been made:\n");
		Offer.displayHeader();
		for (Offer iterateOffer : offerList) {
			iterateOffer.displayOnScreen();
			System.out.println("");
		}
		System.out.print("\nWhat is the OFFER ID of the offer to reject? ");
		
		inputString="18";
		if (UserInput.interactive) inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		
		if (UserInput.isNumeric(inputString)) {
		//Reject the offer selected by the user
		offerToReject = odc.readOffer(Integer.parseInt(inputString));     //get offer
		if (offerToReject != null) {
			offerToReject.setStatus("REJECTED");                              //change status to rejected
			odc.updateOffer(offerToReject);                                   //update the database		
			System.out.println("\nThe following offer was rejected:");
			Offer.displayHeader();
			offerToReject.displayOnScreen(); 
		}
		else
			System.out.println("An invalid or nonexistent Offer ID was provided");
		}
		else 
			System.out.println("Non-numeric data was provided when a number was expected.");
		
	}

	public static void AcceptOffer() {
		String inputString = "0";
		Offer offerToAccept;
		
		//Get a list of offers
		List<Offer> offerList = odc.readAllOffers();
		
		System.out.println("");
		System.out.println("=========================================");
		System.out.println("|             Accept an offer           |");
		System.out.println("=========================================");	
		System.out.println("\nThese are all of offers that have been made:\n");
		Offer.displayHeader();
		for (Offer iterateOffer : offerList) {
			iterateOffer.displayOnScreen();
			System.out.println("");
		}
		//System.out.println(offerList);
		System.out.print("\nWhat is the OFFER ID of the offer to accept? ");
		
		inputString="16";
		if (UserInput.interactive) inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;

		if (UserInput.isNumeric(inputString)) {
		//Part 1: Accept the offer selected by the user
		offerToAccept = odc.readOffer(Integer.parseInt(inputString));     //get offer
		if (offerToAccept != null) {
			offerToAccept.setStatus("ACCEPTED");                              //change status to accepted
			odc.updateOffer(offerToAccept);                                   //update the database		
			System.out.println("\nThe following offer was accepted:");
			Offer.displayHeader();
			offerToAccept.displayOnScreen();
			
			//Part 2: Reject all other offers.
			int offerCount=0;
			System.out.println("\n\nThe following offers were rejected:");
			Offer.displayHeader();
			for( Offer iterateOffer: offerList ) {    //iterate over all offers
				if (iterateOffer.getOfferId() != offerToAccept.getOfferId() &&   //if not reiterating on the original offer ...
				    iterateOffer.getCarVin() == offerToAccept.getCarVin() ) {    //...and the vin numbers match, ...
					iterateOffer.setStatus("REJECTED");                          //...it is a counteroffer to reject.
					odc.updateOffer(iterateOffer);								 // Update the database.
					iterateOffer.displayOnScreen();
					System.out.println("");
					offerCount++;
				}
			}
			
			//Part 3: Make the customer own the car.
			Car newlyOwned = cdc.readCar(offerToAccept.getCarVin());
			newlyOwned.setCarOwner(offerToAccept.getCustomerName());
			newlyOwned.setCarPrice(offerToAccept.getPrice());
			cdc.updateCar(newlyOwned);
			
			//if there are no counteroffers, print none for offers to reject
			if (offerCount==0) System.out.println("<< None >>");
			}
		else
			System.out.println("An invalid or nonexistent Offer ID was provided.");
		}
		else
			System.out.println("Non-numeric data was provided when a number was expected.");
	}

	public static void ViewAllPayments() {
		System.out.println("");
		System.out.println("=========================================");
		System.out.println("|            View all payments          |");
		System.out.println("=========================================");	
		System.out.println("\nThese are all of the car payments that have been made:\n");
		List<Payment> paymentList = pdc.readAllPayments();
		Payment.displayHeader();
		for (Payment iteratePayment : paymentList) {
			iteratePayment.displayOnScreen();
			System.out.println("");
		}
	}

	public static void RemoveCar() {
		
		String inputString = "0";
		
		System.out.println("");
		System.out.println("=========================================");
		System.out.println("|       Remove a Car from the Lot       |");
		System.out.println("=========================================");	
		System.out.println("\nThe cars currently on the lot are:\n");
		Car.displayHeader();
		List<Car> allCars = cdc.readAllCars();
		for(Car iterateCar : allCars ) {
			if (iterateCar.getCarOwner().equals("DEALER")) {
				iterateCar.displayOnScreen();
				System.out.println("");
			}
		}		
		System.out.print("\nWhat is the VIN NUMBER of the car to be removed? ");
		
		inputString="4070";
		if (UserInput.interactive) inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		
		if (UserInput.isNumeric(inputString)) {
		System.out.println("\nThe following car was deleted from the lot:");
		System.out.println("-----------------------------------------");
		Car carToDelete = cdc.readCar(Integer.parseInt(inputString));
		if (carToDelete != null) {
			Car.displayHeader();
			carToDelete.displayOnScreen();
			
			//Instead of deleting the car, mark the owner as REMOVED
			carToDelete.setCarOwner("REMOVED");
			cdc.updateCar(carToDelete);
			
			//Update any offers on the car as having a status of REMOVED
			//Part 2: Reject all other offers.
			List<Offer> offerList = odc.readAllOffers();
			int offerCount=0;
			System.out.println("\n\nThe following offers were removed on this deleted car:");
			Offer.displayHeader();
			for( Offer iterateOffer: offerList ) {    //iterate over all offers
				if (iterateOffer.getCarVin() == carToDelete.getCarVin() ) {    //...and the vin numbers match, ...
					iterateOffer.setStatus("REMOVED");                         //...it is a counteroffer to reject.
					odc.updateOffer(iterateOffer);							   // Update the database.
					iterateOffer.displayOnScreen();
					System.out.println("");
					offerCount++;
				}
			}	
			//if there are no offers on this removed car, print none for offers to reject
			if (offerCount==0) System.out.println("<< None >>");
			}
		else
			System.out.println("An invalid or nonexistent VIN was provided.");
		}
		else
			System.out.println("Non-numeric data was provided when a number was expected.");
	}

	public static void AddCar () { 
		//Adds a car to the database
		
		Car addedCar = new Car ();
		String inputString = "0";
		
		System.out.println("");
		System.out.println("=========================================");
		System.out.println("|            Add a Car to the Lot       |");
		System.out.println("=========================================");	
		System.out.print("\nWhat is the MAKE of the car to be added? ");
		
		inputString="Ford";
		if (UserInput.interactive) inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		
		addedCar.setCarMake(inputString);
		
		System.out.print("\nWhat is the MODEL of the car to be added? ");
		
		inputString="Explorer";
		if (UserInput.interactive) inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		
		addedCar.setCarModel(inputString);
		
		System.out.print("\nWhat is the PRICE of the car to be added? ");
		
		inputString="46000";
		if (UserInput.interactive) inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		
		
		if (UserInput.isNumeric(inputString)) {
		addedCar.setCarPrice(Integer.parseInt(inputString));
		
		System.out.print("\nWhat is the VIN NUMBER of the car (4 digits) to be added? ");
		
		inputString="4070";
		if (UserInput.interactive) inputString = UserInput.getKeyboard.nextLine();
		if (inputString.equals("q") || inputString.equals("Q")) return;
		
		
		if (UserInput.isNumeric(inputString)) {
		addedCar.setCarVin(Integer.parseInt(inputString));	
		addedCar.setCarOwner("DEALER");
		cdc.createCar(addedCar);
		
		System.out.println("\nThe following car was added to the lot:");
		System.out.println("-----------------------------------------");
		Car.displayHeader();
		addedCar.displayOnScreen();
		}
		else
			System.out.println("Non-numeric data was provided when a number was expected.");
		}
		else
			System.out.println("Non-numeric data was provided when a number was expected.");
		
		return;
	}
	
	
	
}
