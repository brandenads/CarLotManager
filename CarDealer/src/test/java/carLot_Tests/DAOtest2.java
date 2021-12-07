package carLot_Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import carLot_DAOs.OfferDAOclass;
import carLot_Models.Offer;

class DAOtest2 {

//	public boolean createOffer(Offer newOffer);  //C - create
//	public Offer readOffer(int vin);           //R - read one offer
//	public List<Offer> readAllOffers();        //  - read all offers
//	public boolean updateOffer(Offer modOffer);  //U - update
//	public boolean deleteOffer(int vin);     //D - delete	
	
	//@Test
	//void test() {
	//	fail("Not yet implemented");
	//}
	
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
		Offer actual = offerDAO.readOffer(1001);
		assertEquals(testOffer1.toString(), actual.toString());
		
	}
	
	@Test
	public void offer_readAllOffers () {
		//Test first two offers only, as others can get changed.
		OfferDAOsetup();
		List<Offer> gotOffers = offerDAO.readAllOffers();
		
		List<Offer> actual = new ArrayList();
		actual.add(gotOffers.get(0));
		actual.add(gotOffers.get(1));
		
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
		
		testOffer1.setOfferId(1999);   //Change vin to prevent primary key conflict
		assertTrue(offerDAO.createOffer(testOffer1));
		testOffer1.setOfferId(1001);  //reset vin in test data
		offerDAO.deleteOffer(1999);    //delete added offer
	}
	
	@Test
	public void offer_deleteOffer() {
		OfferDAOsetup();
		
		testOffer1.setOfferId(1999);   //Change vin to prevent primary key conflict
		offerDAO.createOffer(testOffer1); //add a offer to be deleted
		assertTrue(offerDAO.deleteOffer(1999));
		testOffer1.setOfferId(1001);  //reset vin in test data
		    
	}
}

