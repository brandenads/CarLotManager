package carLot_MainProg;

import java.sql.Connection;
//## User Stories
//* As a user, I can login.
//* As a user, I can register for a customer account.
//* As an employee, I can add a car to the lot.
//* As an employee, I can accept or reject an offer for a car.
//* As an employee, I can remove a car from the lot.
//* As an employee, I can view all payments.
//* As a customer, I can view the cars on the lot.
//* As a customer, I can make an offer for a car.
//* As a customer, I can view the cars that I own.
//* As a customer, I can view my remaining payments for a car.
//* As the system, I reject all other pending offers for a car when an offer is accepted.
//* As the system, I can calculate the monthly payment.

//import java.sql.Connection;

//import buisnessLogic.CustomerLogic;
//import buisnessLogic.EmployeeLogic;
import buisnessLogic.LoginLogic;
//import carLot_DAOs.CarDAOclass;
//import carLot_DAOs.OfferDAOclass;
//import carLot_Models.Offer;
//import carLot_Utilities.DataLogger;
import carLot_Utilities.DatabaseConnection;
import carLot_Utilities.UserInput;

public class RunApp {

	public static Connection database = DatabaseConnection.getConnection();
	
	public static void main(String[] args) {
		
//		CarDAOclass cdc = new CarDAOclass();
//		OfferDAOclass odc = new OfferDAOclass();


 
		//Test user logic here
		//Printing the menu
		
		System.out.println("");
		System.out.println("=====================================================");
		System.out.println("|                 Welcome to the                    |");
		System.out.println("|                                                   |");
		System.out.println("|    CCC     A     RRR       L        OOO   TTTTT   |");
		System.out.println("|   C       A A    R  R      L       O   O    T     |");
		System.out.println("|   C      AAAAA   RRR       L       O   O    T     |");
		System.out.println("|   C      A   A   R R       L       O   O    T     |");
		System.out.println("|    CCC   A   A   R  R      LLLLL    OOO     T     |");
		System.out.println("|                                                   |");
		System.out.println("=====================================================");
		System.out.println("");		
		
		UserInput.interactive=true;
		LoginLogic.LoginMenu();
		
		System.out.println("");
		System.out.println("=====================================================");
		System.out.println("|           Thank you for visiting the              |");
		System.out.println("|                                                   |");
		System.out.println("|    CCC     A     RRR       L        OOO   TTTTT   |");
		System.out.println("|   C       A A    R  R      L       O   O    T     |");
		System.out.println("|   C      AAAAA   RRR       L       O   O    T     |");
		System.out.println("|   C      A   A   R R       L       O   O    T     |");
		System.out.println("|    CCC   A   A   R  R      LLLLL    OOO     T     |");
		System.out.println("|                     Goodbye!                      |");
		System.out.println("=====================================================");
		System.out.println("");		
		
		
		
		
/*		
		//Signing up as a new customer
		//LoginLogic.MakeNewAccount();
		
		//Logging in as a customer
		//LoginLogic.UserLogin();
		
		//Test employee logic here
		//Printing the menu
		EmployeeLogic.EmployeeMenu();
		
		//Adding a car to the car lot
		EmployeeLogic.AddCar();
		//Viewing the database
		System.out.println(cdc.readAllCars());
		
		//Removing a car from the lot
		EmployeeLogic.RemoveCar();
		//Viewing the database
		System.out.println(cdc.readAllCars());
		
		//Viewing all payments
		EmployeeLogic.ViewAllPayments();
		
		//Accepting an offer
		EmployeeLogic.AcceptOffer();
		//Reset test
		Offer otr;
		otr = odc.readOffer(15);
		otr.setStatus("PENDING");
		odc.updateOffer(otr);
		otr = odc.readOffer(16);
		otr.setStatus("PENDING");
		odc.updateOffer(otr);
		otr = odc.readOffer(17);
		otr.setStatus("PENDING");
		odc.updateOffer(otr);     
  
		//Rejecting an offer
		EmployeeLogic.RejectOffer();
		//Reset test
		otr = odc.readOffer(17);
		otr.setStatus("PENDING");
		odc.updateOffer(otr);   
		
		
		//Test customer logic here
		//Set customer name, would be done by login logic class
		CustomerLogic.setNameOfCustomer("Linda");
	
		//View all cars with monthly payments
		CustomerLogic.ViewAllCars();
		
		//Make an offer
		CustomerLogic.MakeAnOffer();
		
		//View my cars with payments remaining
		CustomerLogic.ViewMyCars();
		*/		
	}

}
