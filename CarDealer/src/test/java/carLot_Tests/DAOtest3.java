package carLot_Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import carLot_DAOs.PaymentDAOclass;
import carLot_Models.Payment;

class DAOtest3 {

//	public boolean createPayment(Payment newPayment);  //C - create
//	public Payment readPayment(int vin);           //R - read one payment
//	public List<Payment> readAllPayments();        //  - read all payments
//	public boolean updatePayment(Payment modPayment);  //U - update
//	public boolean deletePayment(int vin);     //D - delete	
	
	//@Test
	//void test() {
	//	fail("Not yet implemented");
	//}
	
	//Test Payment DAO model.
	//The Honda and Toyota will be used for read testing purposes

	public static Payment testPayment1;
	public static Payment testPayment2;
	public static PaymentDAOclass paymentDAO;
	public static List<Payment> twoPayments;
	
	public void PaymentDAOsetup () {
		paymentDAO = new PaymentDAOclass();
		twoPayments = new ArrayList<Payment>();
		
		testPayment1 = new Payment ();
		testPayment1.setPaymentId(423);
		testPayment1.setCustomerName("Linda");
		testPayment1.setPaymentDate("06-15-20");
		testPayment1.setCarVin(1444);
		testPayment1.setPaymentAmount(470);
		twoPayments.add(testPayment1);
		
		testPayment2 = new Payment();
		testPayment2.setPaymentId(492);
		testPayment2.setCustomerName("Linda");
		testPayment2.setPaymentDate("07-15-20");
		testPayment2.setCarVin(1444);
		testPayment2.setPaymentAmount(470);
		twoPayments.add(testPayment2);
	}
	
	@Test
	public void payment_readPayment () {
		PaymentDAOsetup();
		Payment actual = paymentDAO.readPayment(18);
		assertEquals(null, actual);
		
	}
	
	@Test
	public void payment_readAllPayments () {
		//Test first two payments only, as others can get changed.
		PaymentDAOsetup();
		List<Payment> gotPayments = paymentDAO.readAllPayments();
		
		List<Payment> actual = new ArrayList();
		actual.add(gotPayments.get(0));
		actual.add(gotPayments.get(1));
		
		assertEquals(twoPayments.toString(), actual.toString());
		
	}
	
	@Test
	public void payment_updatePayment () {
		PaymentDAOsetup();
		assertFalse(paymentDAO.updatePayment(testPayment1));
	}

	@Test
	public void payment_addPayment() {
		PaymentDAOsetup();
		
		testPayment1.setPaymentId(1999);   //Change vin to prevent primary key conflict
		assertFalse(paymentDAO.createPayment(testPayment1));
		testPayment1.setPaymentId(1001);  //reset vin in test data
		paymentDAO.deletePayment(1999);    //delete added payment
	}
	
	@Test
	public void payment_deletePayment() {
		PaymentDAOsetup();
		
		testPayment1.setPaymentId(1999);   //Change vin to prevent primary key conflict
		paymentDAO.createPayment(testPayment1); //add a payment to be deleted
		assertFalse(paymentDAO.deletePayment(1999));
		testPayment1.setPaymentId(1001);  //reset vin in test data
		    
	}
}

