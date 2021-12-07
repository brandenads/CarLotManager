package carLot_DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import carLot_Models.Car;
import carLot_Utilities.DataLogger;
import carLot_Utilities.DatabaseConnection;

public class CarDAOclass implements CarDAOinterface{

	//Get database connection.
	public static Connection database = DatabaseConnection.getConnection();
	
	
	public boolean createCar(Car newCar) {
		try {
			//STEP 1: Get a query string prepared
			String sqlQuery = "INSERT INTO AllCars VALUES ( ? , ? , ? , ? , ?)";
			PreparedStatement prep = database.prepareStatement(sqlQuery);
			prep.setString(1, Integer.toString(newCar.getCarVin()));
			prep.setString(2, newCar.getCarMake());
			prep.setString(3, newCar.getCarModel());
			prep.setString(4, Integer.toString(newCar.getCarPrice()));
			prep.setString(5, newCar.getCarOwner());
			
			//STEP 2: Execute Query
			prep.executeQuery();
			
			//log car being added to database
			DataLogger.logToFile.info("CAR - CREATE: The Car "+newCar.toString()+" has been entered into the database.");
			return true;
			
			} catch (SQLException e) {
				e.printStackTrace();
			}

		return false; //If retrieving the car from the database fails, or car not found.
	}
	
	
	public Car readCar(int vin) {
		try {
			
			//STEP 1: Get a query string prepared
			String sqlQuery = "SELECT * FROM AllCars WHERE CarVin = ?";
			PreparedStatement prep = database.prepareStatement(sqlQuery);
			prep.setString(1, Integer.toString(vin));
			
			//STEP 2: Get a result set
			ResultSet results = prep.executeQuery();
			
			//STEP 3: Enter database values into Car model object
			if (results.next()) {
				Car retrievedCar = new Car();
				retrievedCar.setCarVin(results.getInt("CarVin"));
				retrievedCar.setCarMake(results.getString("CarMake"));
				retrievedCar.setCarModel(results.getString("CarModel"));
				retrievedCar.setCarPrice(results.getInt("CarPrice"));
				retrievedCar.setCarOwner(results.getString("CarOwner"));
				
				//Log car read from database
				DataLogger.logToFile.info("CAR - READ: The Car "+retrievedCar.toString()+" has been read from the database.");

				
				return retrievedCar;
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return null; //If retrieving the car from the database fails, or car not found.
	}

	public List<Car> readAllCars() {
		try {
			
			//STEP 1: Get a query string prepared
			String sqlQuery = "SELECT * FROM AllCars ORDER BY CarVin";
			PreparedStatement prep = database.prepareStatement(sqlQuery);
			
			
			//STEP 2: Get a result set
			ResultSet results = prep.executeQuery();
			
			//Log the fact all cars were read  from database
			DataLogger.logToFile.info("CAR - READALL: All the cars have been read from the database.");

			
			List<Car> listOfCars = new ArrayList<Car>();
		
			//STEP 3: Enter database values into car model object
			while (results.next()) {
				Car retrievedCar = new Car();
				retrievedCar.setCarVin(results.getInt("CarVin"));
				retrievedCar.setCarMake(results.getString("CarMake"));
				retrievedCar.setCarModel(results.getString("CarModel"));
				retrievedCar.setCarPrice(results.getInt("CarPrice"));
				retrievedCar.setCarOwner(results.getString("CarOwner"));
				listOfCars.add(retrievedCar);
			}
			return listOfCars;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	return null; //If retrieving the car from the database fails, or car not found.
	}

	public boolean updateCar(Car modCar) {
		try {
			//STEP 1: Get a query string prepared
			String sqlQuery = "UPDATE AllCars SET CarMake = ?, CarModel = ?, CarPrice = ?, CarOwner = ? WHERE CarVin = ?";
			PreparedStatement prep = database.prepareStatement(sqlQuery);
			prep.setString(1, modCar.getCarMake());
			prep.setString(2, modCar.getCarModel());
			prep.setString(3, Integer.toString(modCar.getCarPrice()));
			prep.setString(4, modCar.getCarOwner());
			prep.setString(5, Integer.toString(modCar.getCarVin()));
			
			//STEP 2: Execute Query
			prep.executeQuery();
			
			//Log car modified in database
			DataLogger.logToFile.info("CAR - UPDATE: The Car "+modCar.toString()+" has been changed in the database.");

			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteCar(int vin) {
		try {
		
			//STEP 1: Get a query string prepared
			String sqlQuery = "DELETE AllCars WHERE CarVin = ?";
			PreparedStatement prep = database.prepareStatement(sqlQuery);
			prep.setString(1, Integer.toString(vin));
			
			//Log car deleted from database
			DataLogger.logToFile.info("CAR - DELETE: The Car with vin number "+vin+" has been removed from the database.");

			
			//STEP 2: Execute Query
			prep.executeQuery();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	//For debugging purposes only
	public static void main(String[] args) {
		/*
		//Test reading single cars by their VIN number
		CarDAOclass testDAO = new CarDAOclass();
		Car myCar;		
		//Getting individual cars
		myCar = testDAO.readCar(1001);
		System.out.println(myCar);
		myCar = testDAO.readCar(1222);
		System.out.println(myCar);
		myCar = testDAO.readCar(1333);
		System.out.println(myCar);
		myCar = testDAO.readCar(1444);
		System.out.println(myCar);
		//A car that does not exist;
		myCar = testDAO.readCar(1445);
		System.out.println(myCar + " does not exist");
		
		
		//Getting a list of all cars
		List<Car> myList;
		myList = testDAO.readAllCars();
		System.out.println(myList);
		
		//Changing a car
		Car myChange = new Car();
		myChange.setCarVin(1444);
		myChange.setCarMake("Mercedes");
		myChange.setCarModel("Benz");
		myChange.setCarPrice(40126);
		myChange.setCarOwner("Santa");
		System.out.println("Old Car:");
		System.out.println(testDAO.readCar(1444));
		System.out.println("New Car:");
		testDAO.updateCar(myChange);
		System.out.println(testDAO.readCar(1444));
		myChange.setCarVin(1444);
		myChange.setCarMake("Jeep");
		myChange.setCarModel("Wrangler");
		myChange.setCarPrice(28295);
		myChange.setCarOwner("DEALER");
		testDAO.updateCar(myChange);
		
		//Deleting a car
		System.out.println("This car will be deleted:");
		System.out.println(testDAO.readCar(1333));
		testDAO.deleteCar(1333);
		System.out.println("The remaining cars in the database are:");
		System.out.println(testDAO.readAllCars());
		
		//Adding a car
		myChange.setCarVin(1333);
		myChange.setCarMake("Ford");
		myChange.setCarModel("Focus");
		myChange.setCarPrice(19000);
		myChange.setCarOwner("DEALER");
		System.out.println("This car will be added:");
		System.out.println(myChange);
		testDAO.createCar(myChange);
		System.out.println("The cars in the database are:");
		System.out.println(testDAO.readAllCars());
		*/
	}
}
