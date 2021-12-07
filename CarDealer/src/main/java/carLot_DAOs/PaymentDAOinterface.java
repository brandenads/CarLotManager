package carLot_DAOs;

import java.util.List;

import carLot_Models.Payment;

public interface PaymentDAOinterface {

		//This is the Data Access Object
		//Used for CRUD operations
		public boolean createPayment(Payment newPayment);  //C - create
		public Payment readPayment(int paymentId);         //R - read one Payment
		public List<Payment> readAllPayments();            //  - read all Payments
		public boolean updatePayment(Payment modPayment);  //U - update
		public boolean deletePayment(int paymentId);       //D - delete
	
}
