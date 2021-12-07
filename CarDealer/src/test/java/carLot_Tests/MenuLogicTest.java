package carLot_Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import buisnessLogic.CustomerLogic;
import buisnessLogic.EmployeeLogic;
import carLot_Models.Offer;
import carLot_Utilities.UserInput;
import carLot_DAOs.CarDAOclass;
import carLot_DAOs.OfferDAOclass;

class MenuLogicTest {

	/*//Test customer logic here
	//Set customer name, would be done by login logic class
	CustomerLogic.setNameOfCustomer("Linda");

	//View all cars with monthly payments
	CustomerLogic.ViewAllCars();

	//Make an offer
	CustomerLogic.MakeAnOffer();

	//View my cars with payments remaining
	CustomerLogic.ViewMyCars();
	*/
	
	@Test
	public void cmenu_CustomerMenu () {
		UserInput.interactive=false;
		CustomerLogic.setNameOfCustomer("Linda");
		CustomerLogic.CustomerMenu();
		assertEquals(1,1);
		
	}

	@Test
	public void cmenu_ViewAllCars () {
		UserInput.interactive=false;
		CustomerLogic.setNameOfCustomer("Linda");
		CustomerLogic.ViewAllCars();
		assertEquals(1,1);
		
	}
	
	@Test
	public void cmenu_makeAnOffer () {
		UserInput.interactive=false;
		CustomerLogic.setNameOfCustomer("Linda");
		CustomerLogic.MakeAnOffer();
		assertEquals(1,1);
		
	}

	@Test
	public void cmenu_ViewMyCars () {
		UserInput.interactive=false;
		CustomerLogic.setNameOfCustomer("Linda");
		CustomerLogic.ViewMyCars();
		assertEquals(1,1);
		
	}
	/*
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
	*/
	
	@Test
	public void emenu_EmployeeMenu () {
		UserInput.interactive=false;
		EmployeeLogic.EmployeeMenu();
		assertEquals(1,1);
		
	}

	@Test
	public void emenu_AddAndRemoveCar () {
		CarDAOclass carDAO;
		carDAO = new CarDAOclass();
		UserInput.interactive=false;
		EmployeeLogic.AddCar();
		EmployeeLogic.RemoveCar();
		carDAO.deleteCar(4070); //physically delete car in test.
		assertEquals(1,1);
		
	}
	
	@Test
	public void cmenu_ViewAllPayments () {
		UserInput.interactive=false;
		EmployeeLogic.ViewAllPayments();
		assertEquals(1,1);
		
	}

	@Test
	public void cmenu_AcceptOffer () {
		
		OfferDAOclass odc = new OfferDAOclass();
		
		UserInput.interactive=false;
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
		
		assertEquals(1,1);
		
	}
	
	@Test
	public void cmenu_RejectOffer () {
		
		OfferDAOclass odc = new OfferDAOclass();
		
		UserInput.interactive=false;
		EmployeeLogic.RejectOffer();

		//Rejecting an offer
		EmployeeLogic.RejectOffer();
		//Reset test
		Offer otr;
		otr = odc.readOffer(17);
		otr.setStatus("PENDING");
		odc.updateOffer(otr);  
	}
	
}
