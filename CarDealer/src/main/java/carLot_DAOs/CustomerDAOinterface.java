package carLot_DAOs;

import java.util.List;
import carLot_Models.Customer;

public interface CustomerDAOinterface {

	//This is the Data Access Object
	//Used for CRUD operations
	
	public boolean createCustomer(Customer newCustomer);  //C - create
	public Customer readCustomer(String nameCustomer);                //R - read one Customer
	public List<Customer> readAllCustomers();             //  - read all Customers
	public boolean updateCustomer(Customer modCustomer);  //U - update
	public boolean deleteCustomer(String nameCustomer);               //D - delete
	
}
