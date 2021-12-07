package carLot_Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import carLot_DAOs.CustomerDAOclass;
import carLot_Models.Customer;

class DAOtest4 {

//	public boolean createCustomer(Customer newCustomer);  //C - create
//	public Customer readCustomer(int vin);           //R - read one customer
//	public List<Customer> readAllCustomers();        //  - read all customers
//	public boolean updateCustomer(Customer modCustomer);  //U - update
//	public boolean deleteCustomer(int vin);     //D - delete	
	
	//@Test
	//void test() {
	//	fail("Not yet implemented");
	//}
	
	//Test Customer DAO model.
	//The Honda and Toyota will be used for read testing purposes

	public static Customer testCustomer1;
	public static Customer testCustomer2;
	public static CustomerDAOclass customerDAO;
	public static List<Customer> twoCustomers;
	
	public void CustomerDAOsetup () {
		customerDAO = new CustomerDAOclass();
		twoCustomers = new ArrayList<Customer>();
		
		testCustomer1 = new Customer ();
		testCustomer1.setEmployeeFlag(0);
		testCustomer1.setCustomerName("Bob");
		testCustomer1.setCustomerPassword("12345678");
		twoCustomers.add(testCustomer1);
		
		testCustomer2 = new Customer();
		testCustomer2.setEmployeeFlag(0);
		testCustomer2.setCustomerName("Zach");
		testCustomer2.setCustomerPassword("Password1");
		twoCustomers.add(testCustomer2);
	}
	
	@Test
	public void customer_readCustomer () {
		CustomerDAOsetup();
		Customer actual = customerDAO.readCustomer("Bob");
		assertEquals(testCustomer1.toString(), actual.toString());
		
	}
	
	@Test
	public void customer_readAllCustomers () {
		//Test first two customers only, as others can get changed.
		CustomerDAOsetup();
		List<Customer> gotCustomers = customerDAO.readAllCustomers();
			
		assertEquals(null, gotCustomers);
		
	}
	
	@Test
	public void customer_updateCustomer () {
		CustomerDAOsetup();
		
		assertFalse(customerDAO.updateCustomer(testCustomer1));
	}

	@Test
	public void customer_addCustomer() {
		CustomerDAOsetup();
		
		assertTrue(customerDAO.createCustomer(testCustomer2));

	}
	
	@Test
	public void customer_deleteCustomer() {
		CustomerDAOsetup();
		
		assertFalse(customerDAO.deleteCustomer("Zach"));

		    
	}
}

