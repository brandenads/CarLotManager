package carLot_DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import carLot_Models.Payment;
import carLot_Utilities.DataLogger;
import carLot_Utilities.DatabaseConnection;

public class PaymentDAOclass implements PaymentDAOinterface {

	//All methods are required for the interface, but only
	//readAllPayments will be used by this specific application.
	
	//Get database connection.
	public static Connection database = DatabaseConnection.getConnection();
	
	public boolean createPayment(Payment newPayment) {
		// TODO Auto-generated method stub
		return false;
	}

	public Payment readPayment(int paymentId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Payment> readAllPayments() {
		try {
			
			//STEP 1: Get a query string prepared
			String sqlQuery = "SELECT * FROM Payments ORDER BY CustomerName";
			PreparedStatement prep = database.prepareStatement(sqlQuery);
			
			
			//STEP 2: Get a result set
			ResultSet results = prep.executeQuery();
			
			//Log the fact all payments were read from the database
			DataLogger.logToFile.info("PAYMENT - READALL: All payments have been read from the database.");

			
			List<Payment> listOfPayments = new ArrayList<Payment>();
		
			//STEP 3: Enter database values into car model object
			while (results.next()) {
				Payment retrievedPay = new Payment();
				retrievedPay.setPaymentId(results.getInt("PaymentId"));
				retrievedPay.setCarVin(results.getInt("CarVin"));
				retrievedPay.setCustomerName(results.getString("CustomerName"));
				retrievedPay.setPaymentAmount(results.getInt("PaymentAmount"));
				retrievedPay.setPaymentDate(results.getString("PaymentDate"));
				listOfPayments.add(retrievedPay);
			}
			return listOfPayments;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	return null; //If retrieving the car from the database fails, or car not found.
	
	}

	public boolean updatePayment(Payment modPayment) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deletePayment(int paymentId) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main (String[] args) {
		/*
		
		PaymentDAOclass testDAO = new PaymentDAOclass();
		
		//Getting a list of all cars
		List<Payment> myList;
		myList = testDAO.readAllPayments();
		System.out.println(myList);
		*/
		
	};
	
}
