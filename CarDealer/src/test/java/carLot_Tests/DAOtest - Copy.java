package carLot_Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import carLot_DAOs.CarDAOclass;
import carLot_DAOs.OfferDAOclass;
import carLot_DAOs.PaymentDAOclass;
import carLot_Models.Car;
import carLot_Models.Offer;
import carLot_Models.Payment;

class DAOtest5 {

//	public boolean createCar(Car newCar);  //C - create
//	public Car readCar(int vin);           //R - read one car
//	public List<Car> readAllCars();        //  - read all cars
//	public boolean updateCar(Car modCar);  //U - update
//	public boolean deleteCar(int vin);     //D - delete	
	
	//@Test
	//void test() {
	//	fail("Not yet implemented");
	//}
	
	//Test Car DAO model.
	//The Honda and Toyota will be used for read testing purposes

	public static Car testCar1;
	public static Car testCar2;
	public static CarDAOclass carDAO;
	public static List<Car> twoCars;
	
	public void CarDAOsetup () {
		carDAO = new CarDAOclass();
		twoCars = new ArrayList<Car>();
		
		testCar1 = new Car ();
		testCar1.setCarVin(1001);
		testCar1.setCarMake("Honda");
		testCar1.setCarModel("Accord");
		testCar1.setCarOwner("DEALER");
		testCar1.setCarPrice(24270);
		twoCars.add(testCar1);
		
		testCar2 = new Car();
		testCar2.setCarVin(1111);
		testCar2.setCarMake("Toyota");
		testCar2.setCarModel("Camry");
		testCar2.setCarOwner("DEALER");
		testCar2.setCarPrice(24425);
		twoCars.add(testCar2);
	}
	
	@Test
	public void car_readCar () {
		CarDAOsetup();
		Car actual = carDAO.readCar(1001);
		assertEquals(testCar1.toString(), actual.toString());
		
	}
	
	@Test
	public void car_readAllCars () {
		//Test first two cars only, as others can get changed.
		CarDAOsetup();
		List<Car> gotCars = carDAO.readAllCars();
		
		List<Car> actual = new ArrayList();
		actual.add(gotCars.get(0));
		actual.add(gotCars.get(1));
		
		assertEquals(twoCars.toString(), actual.toString());
		
	}
	
	@Test
	public void car_updateCar () {
		CarDAOsetup();
		assertTrue(carDAO.updateCar(testCar1));
	}

	@Test
	public void car_addCar() {
		CarDAOsetup();
		
		testCar1.setCarVin(1999);   //Change vin to prevent primary key conflict
		assertTrue(carDAO.createCar(testCar1));
		testCar1.setCarVin(1001);  //reset vin in test data
		carDAO.deleteCar(1999);    //delete added car
	}
	
	@Test
	public void car_deleteCar() {
		CarDAOsetup();
		
		testCar1.setCarVin(1999);   //Change vin to prevent primary key conflict
		carDAO.createCar(testCar1); //add a car to be deleted
		assertTrue(carDAO.deleteCar(1999));
		testCar1.setCarVin(1001);  //reset vin in test data
		    
	}
	

	
	
	
	
	
	
	
	//Test Offer DAO model.
	//The Honda and Toyota will be used for read testing purposes

	public static Offer testOffer1;
	public static Offer testOffer2;
	public static OfferDAOclass offerDAO;
	public static List<Offer> twoOffers;
	
	public void OfferDAOsetup () {
		
		offerDAO = new OfferDAOclass();
		twoOffers = new ArrayList<Offer>();
		
		testOffer1 = new Offer ();
		testOffer1.setOfferId(18);
		testOffer1.setCustomerName("David");
		testOffer1.setStatus("PENDING");
		testOffer1.setCarVin(1222);
		testOffer1.setPrice(30000);
		twoOffers.add(testOffer1);
		
		testOffer2 = new Offer();
		testOffer2.setOfferId(19);
		testOffer2.setCustomerName("Richard");
		testOffer2.setStatus("PENDING");
		testOffer2.setCarVin(1333);
		testOffer2.setPrice(27000);
		twoOffers.add(testOffer2);
	}
	
	@Test
	public void offer_readOffer () {
		OfferDAOsetup();
		Offer actual = offerDAO.readOffer(18);
		assertEquals(testOffer1.toString(), actual.toString());
		
	}
	
	@Test
	public void offer_readAllOffers () {
		//Test first two offers only, as others can get changed.
		OfferDAOsetup();
		List<Offer> gotOffers = offerDAO.readAllOffers();
		
		List<Offer> actual = new ArrayList();
		actual.add(gotOffers.get(3));
		actual.add(gotOffers.get(4));
		
		assertEquals(twoOffers.toString(), actual.toString());
		
	}
	
	@Test
	public void offer_updateOffer () {
		OfferDAOsetup();
		assertTrue(offerDAO.updateOffer(testOffer1));
	}

	@Test
	public void offer_addOffer() {
		OfferDAOsetup();
		
		testOffer1.setOfferId(99);   //Change offer id to prevent primary key conflict
		assertTrue(offerDAO.createOffer(testOffer1));
		testOffer1.setOfferId(18);  //reset offer id in test data
		offerDAO.deleteOffer(99);    //delete added offer
	}
	
	@Test
	public void offer_deleteOffer() {
		OfferDAOsetup();
		
		testOffer1.setOfferId(99);   //Change offer id to prevent primary key conflict
		offerDAO.createOffer(testOffer1); //add a offer to be deleted
		assertTrue(offerDAO.deleteOffer(99));
		testOffer1.setOfferId(18);  //reset offer id in test data
		    
	}
	
	
	

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

