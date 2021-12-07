package carLot_DAOs;

import java.util.List;
import carLot_Models.Car;

public interface CarDAOinterface {

	//This is the Data Access Object
	//Used for CRUD operations
	
	public boolean createCar(Car newCar);  //C - create
	public Car readCar(int vin);           //R - read one car
	public List<Car> readAllCars();        //  - read all cars
	public boolean updateCar(Car modCar);  //U - update
	public boolean deleteCar(int vin);     //D - delete
	
}
