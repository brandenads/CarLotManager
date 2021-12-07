package carLot_DAOs;

import java.util.List;
import carLot_Models.Offer;


public interface OfferDAOinterface {



		//This is the Data Access Object
		//Used for CRUD operations
		public boolean createOffer(Offer newOffer);  //C - create
		public Offer readOffer(int offerId);         //R - read one Offer
		public List<Offer> readAllOffers();          //  - read all Offers
		public boolean updateOffer(Offer modOffer);  //U - update
		public boolean deleteOffer(int offerId);         //D - delete
		
	
	
	
	
}
