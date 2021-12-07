package carLot_Models;

public class Car {
	
	//Properties from database columns
	private int carVin;
	private String carMake;
	private String carModel;
	private int carPrice;
	private String carOwner;
	
	public Car() {
		super();
	}



	public int getCarVin() {
		return carVin;
	}

	public String getCarMake() {
		return carMake;
	}

	public String getCarModel() {
		return carModel;
	}

	public int getCarPrice() {
		return carPrice;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarVin(int carVin) {
		this.carVin = carVin;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public void setCarPrice(int carPrice) {
		this.carPrice = carPrice;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public static void displayHeader() {
		System.out.printf("%-10s %-10s %-10s %-10s\n", "Make","Model","Price","Vin");
		System.out.println("===========================================================================");
	}
	public void displayOnScreen() {
		System.out.printf("%-10s %-10s $%-10s %-10s", carMake,carModel,carPrice,carVin);
	}
	@Override
	public String toString() {
		
	String displayString = "Make: "+carMake+"        Model: "+carModel+"        Price: "+carPrice+"        VIN #: "+carVin;
	return displayString;
		
	//	return "Car [carVin=" + carVin + ", carMake=" + carMake + ", carModel=" + carModel + ", carPrice=" + carPrice
	//			+ ", carOwner=" + carOwner + "]";
	}
	
	
	

}
