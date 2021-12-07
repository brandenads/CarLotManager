package carLot_Models;

import carLot_DAOs.CarDAOclass;

public class Offer {

	//Properties from database table Offer
	private int OfferId;
	private int CarVin;
	private String CustomerName;
	private int Price;
	private String Status;
	
	//needed for printing purposes to get make and model
	private static CarDAOclass cdc = new CarDAOclass();
	
	public Offer() {
		super();
	}


	public int getOfferId() {
		return OfferId;
	}


	public int getCarVin() {
		return CarVin;
	}


	public String getCustomerName() {
		return CustomerName;
	}


	public int getPrice() {
		return Price;
	}


	public String getStatus() {
		return Status;
	}


	public void setOfferId(int offerID) {
		OfferId = offerID;
	}


	public void setCarVin(int carVin) {
		CarVin = carVin;
	}


	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}


	public void setPrice(int price) {
		Price = price;
	}


	public void setStatus(String status) {
		Status = status;
	}

	public static void displayHeader() {
		System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "Offer ID","VIN #","Make","Model","Customer","Offer $","Status");
		System.out.println("===========================================================================");
	}
	public void displayOnScreen() {
		System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s", OfferId,CarVin,cdc.readCar(CarVin).getCarMake(),cdc.readCar(CarVin).getCarModel(),CustomerName,Price,Status);
	}
	
	@Override
	public String toString() {
		
		String displayString = "Offer ID: "+OfferId+"        VIN #: "+CarVin+"        Offer Price: "+Price+"        Status: "+Status;
		return displayString;
		
//		return "Offer [OfferID=" + OfferId + ", CarVin=" + CarVin + ", CustomerName=" + CustomerName + ", Price="
//				+ Price + ", Status=" + Status + "]";
	}
	
	
	
}
