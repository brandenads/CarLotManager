package carLot_Models;

import carLot_DAOs.CarDAOclass;

public class Payment {
	
	private int paymentId;
	private int carVin;
	private String customerName;
	private int paymentAmount;
	private String paymentDate;
	
	//needed for printing purposes to get make and model
	private static CarDAOclass cdc = new CarDAOclass();
	
	public int getPaymentAmount() {
		return paymentAmount;
	}
	public int getCarVin() {
		return carVin;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public void setCarVin(int carVin) {
		this.carVin = carVin;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public static void displayHeader() {
		System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "Paymt ID","VIN #","Make","Model","Name","Amount","Date");
		System.out.println("===========================================================================");
	}
	public void displayOnScreen() {
		System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s", paymentId,carVin,cdc.readCar(carVin).getCarMake(),
		cdc.readCar(carVin).getCarModel(),customerName,paymentAmount,paymentDate);
	}
	
	@Override
	public String toString() {
		
		String displayString = "Payment ID: "+paymentId+"        VIN #: "+carVin+"        Customer Name: "+customerName
				+"        Amount: $"+paymentAmount+"        Date: "+paymentDate;
		
		return displayString;
		
	//	return "Payment [paymentId=" + paymentId + ", carVin=" + carVin + ", customerName=" + customerName
	//			+ ", paymentAmount=" + paymentAmount + ", paymentDate=" + paymentDate + "]";
	}
	
	
	

}
