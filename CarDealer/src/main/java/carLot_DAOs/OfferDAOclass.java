package carLot_DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import carLot_Models.Offer;
import carLot_Utilities.DataLogger;
import carLot_Utilities.DatabaseConnection;



public class OfferDAOclass implements OfferDAOinterface {
	
	//Get database connection.
	public static Connection database = DatabaseConnection.getConnection();


	public boolean createOffer(Offer newOffer) {
		try {
			//STEP 1: Get a query string prepared
			String sqlQuery = "INSERT INTO Offers VALUES ( sequencer.nextval , ? , ? , ? , ?)";
			PreparedStatement prep = database.prepareStatement(sqlQuery);
			prep.setString(1, Integer.toString(newOffer.getCarVin()));
			prep.setString(2, newOffer.getCustomerName());
			prep.setString(3, Integer.toString(newOffer.getPrice()));
			prep.setString(4, newOffer.getStatus());
			
			//STEP 2: Execute Query
			prep.executeQuery();
			
			//Log offer added to database
			DataLogger.logToFile.info("OFFER - CREATE: The Offer "+newOffer.toString()+" has added to the database.");

			return true;
			
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return false; //If retrieving the car from the database fails, or car not found.

	}

public Offer readOffer(int offerId) {
	
	try {
	//STEP 1: Get a query string prepared
	String sqlQuery = "SELECT * FROM Offers WHERE OfferId = ?";
	PreparedStatement prep = database.prepareStatement(sqlQuery);
	prep.setString(1, Integer.toString(offerId));
	
	//STEP 2: Get a result set
	ResultSet results = prep.executeQuery();
	
	//Log offer read from database
	DataLogger.logToFile.info("OFFER - READ: The Offer with offer ID "+offerId+" has read from the database.");

	
	//STEP 3: Enter database values into Car model object
	if (results.next()) {
		Offer retrievedOffer = new Offer();
		retrievedOffer.setOfferId(results.getInt("OfferId"));
		retrievedOffer.setCarVin(results.getInt("CarVin"));
		retrievedOffer.setCustomerName(results.getString("CustomerName"));
		retrievedOffer.setPrice(results.getInt("Price"));
		retrievedOffer.setStatus(results.getString("Status"));
		return retrievedOffer;
	}
	} catch (SQLException e) {
		e.printStackTrace();
	}

	return null; //If retrieving the car from the database fails, or car not found.

}

public List<Offer> readAllOffers() {
	try {
	//STEP 1: Get a query string prepared
	String sqlQuery = "SELECT * FROM Offers ORDER BY OfferId";
	PreparedStatement prep = database.prepareStatement(sqlQuery);
		
	//STEP 2: Get a result set
	ResultSet results = prep.executeQuery();
	
	//Log the fact all offers have been read from the database
	DataLogger.logToFile.info("OFFER - READALL: all offers have been read from the database.");

	List<Offer> listOfOffers = new ArrayList<Offer>();
	
	while (results.next()) {
		Offer retrievedOffer = new Offer();
		retrievedOffer.setOfferId(results.getInt("OfferId"));
		retrievedOffer.setCarVin(results.getInt("CarVin"));
		retrievedOffer.setCustomerName(results.getString("CustomerName"));
		retrievedOffer.setPrice(results.getInt("Price"));
		retrievedOffer.setStatus(results.getString("Status"));
		if (retrievedOffer.getStatus().equals("REMOVED")==false) listOfOffers.add(retrievedOffer);
	}
	return listOfOffers;

	} catch (SQLException e) {
		e.printStackTrace();
	}

	return null;
}


public boolean updateOffer(Offer modOffer) {
	try {
		//STEP 1: Get a query string prepared
		String sqlQuery = "UPDATE Offers SET CarVin = ?, CustomerName = ?, Price = ?, Status = ? WHERE OfferId = ?";
		PreparedStatement prep = database.prepareStatement(sqlQuery);
		prep.setString(1, Integer.toString(modOffer.getCarVin()));
		prep.setString(2, modOffer.getCustomerName());
		prep.setString(3, Integer.toString(modOffer.getPrice()));
		prep.setString(4, modOffer.getStatus());
		prep.setString(5, Integer.toString(modOffer.getOfferId()));
		
		//STEP 2: Execute Query
		prep.executeQuery();
		
		//Log offer modified in database
		DataLogger.logToFile.info("OFFER - UPDATE: The Offer "+modOffer.toString()+" has changed in the database.");

		
		return true;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return false;
}


public boolean deleteOffer(int offerId) {
	try {
		
		//STEP 1: Get a query string prepared
		String sqlQuery = "DELETE Offers WHERE OfferId = ?";
		PreparedStatement prep = database.prepareStatement(sqlQuery);
		prep.setString(1, Integer.toString(offerId));
		
		//STEP 2: Execute Query
		prep.executeQuery();
		
		//Log offer deleted from database
		DataLogger.logToFile.info("OFFER - DELETE: The Offer with offerID "+offerId+" has been deleted from the database.");
		
		return true;
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return false;
}

public static void main(String[] args) { 
	/*
	OfferDAOclass testDAO = new OfferDAOclass();
	Offer myOffer;
	
	//Adding an offer
	Offer myChange = new Offer();
	myChange.setOfferId(9999); //Placeholder: value is autogen by DB
	myChange.setCarVin(1333);
	myChange.setCustomerName("Ben");
	myChange.setPrice(19500);
	myChange.setStatus("PENDING");
	System.out.println("This offer will be added:");
	System.out.println(myChange);
	testDAO.createOffer(myChange);
	System.out.println("The offers in the database are:");
	System.out.println(testDAO.readAllOffers());
	
	//Retrieving an offer
	myOffer = testDAO.readOffer(15);
	System.out.println(myOffer);
	myOffer = testDAO.readOffer(17);
	System.out.println(myOffer);
	myOffer = testDAO.readOffer(19);
	System.out.println(myOffer);
	
	//Getting a list of all offers
	List<Offer> myList;
	myList = testDAO.readAllOffers();
	System.out.println("Printing all offers in the database:");
	System.out.println(myList);
	
	//Deleting an offer
	int toDelete = 71;
	System.out.println("This offer will be deleted:");
	System.out.println(testDAO.readOffer(toDelete));
	testDAO.deleteOffer(toDelete);
	System.out.println("The remaining cars in the database are:");
	System.out.println(testDAO.readAllOffers());
	
	//Changing an offer
	myChange.setOfferId(19); //Offer id to change
	myChange.setCarVin(2002);
	myChange.setCustomerName("Tom");
	myChange.setPrice(99500);
	myChange.setStatus("PENDING");
	System.out.println("Old Offer:");
	System.out.println(testDAO.readOffer(19));
	System.out.println("New Offer:");
	testDAO.updateOffer(myChange);
	System.out.println(testDAO.readOffer(19));
	myChange.setOfferId(19); //Offer id to change
	myChange.setCarVin(1444);
	myChange.setCustomerName("Jeff");
	myChange.setPrice(27000);
	myChange.setStatus("PENDING");
	testDAO.updateOffer(myChange);
	System.out.println("Reset Offer:");
	System.out.println(testDAO.readOffer(19));
	*/
}




}
