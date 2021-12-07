package carLot_DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import carLot_Models.Customer;
import carLot_Utilities.DataLogger;
import carLot_Utilities.DatabaseConnection;

public class CustomerDAOclass  implements CustomerDAOinterface{


	//Get database connection.
	public static Connection database = DatabaseConnection.getConnection();
	
	public boolean createCustomer(Customer newCustomer) {
		try {
		//STEP 1: Get a query string prepared
		String sqlQuery = "INSERT INTO Customers VALUES ( ? , ? , ? )";
		PreparedStatement prep = database.prepareStatement(sqlQuery);
		prep.setString(1, newCustomer.getCustomerName());
		prep.setString(2, newCustomer.getCustomerPassword());
		prep.setString(3, Integer.toString(newCustomer.getEmployeeFlag()));

		//STEP 2: Execute Query
		prep.executeQuery();
		
		//Log customer added to database
		DataLogger.logToFile.info("CUSTOMER - CREATE: The Customer "+newCustomer.toString()+" has been added to the database.");

		return true;
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

	return false; //If retrieving the car from the database fails, or car not found.

	}

	public Customer readCustomer(String nameCustomer) {
		try {
			
			//STEP 1: Get a query string prepared
			String sqlQuery = "SELECT * FROM Customers WHERE CustomerName = ?";
			PreparedStatement prep = database.prepareStatement(sqlQuery);
			prep.setString(1, nameCustomer);
			
			//STEP 2: Get a result set
			ResultSet results = prep.executeQuery();
			
			//Log customer read from database
			DataLogger.logToFile.info("CUSTOMER - READ: The Customer with name "+nameCustomer+" has been read from the database.");

			
			//STEP 3: Enter database values into Car model object
			if (results.next()) {
				Customer retrievedCustomer = new Customer();
				retrievedCustomer.setCustomerName(results.getString("CustomerName"));
				retrievedCustomer.setCustomerPassword(results.getString("CustomerPassword"));
				retrievedCustomer.setEmployeeFlag(results.getInt("EmployeeFlag"));
				return retrievedCustomer;
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return null; //If retrieving the car from the database fai
	}

	public List<Customer> readAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateCustomer(Customer modCustomer) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteCustomer(String nameCustomer) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		/*
		//Test reading single cars by their VIN number
		CustomerDAOclass testDAO = new CustomerDAOclass();
		Customer myCustomer = new Customer();	
		
		//Create a new customer
		myCustomer.setCustomerName("Timothy");
		myCustomer.setCustomerPassword("Password1");
		myCustomer.setEmployeeFlag(0);
		testDAO.createCustomer(myCustomer);
		
		//Read a customer
		myCustomer=testDAO.readCustomer("Bob"); //employee
		System.out.println(myCustomer);
		myCustomer=testDAO.readCustomer("Richard");  //customer
		System.out.println(myCustomer);
		*/
	}
}
