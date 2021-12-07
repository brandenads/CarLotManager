package carLot_Models;

public class Customer {

	//Properties from database columns
	private String CustomerName;
	private String CustomerPassword;
	private int EmployeeFlag;
	
	
	public String getCustomerName() {
		return CustomerName;
	}
	public String getCustomerPassword() {
		return CustomerPassword;
	}
	public int getEmployeeFlag() {
		return EmployeeFlag;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public void setCustomerPassword(String customerPassword) {
		CustomerPassword = customerPassword;
	}
	public void setEmployeeFlag(int employeeFlag) {
		EmployeeFlag = employeeFlag;
	}
	
	
	public Customer() {
		super();
	}
	
	@Override
	public String toString() {

		return "Customer [CustomerName=" + CustomerName + ", CustomerPassword=" + CustomerPassword + ", EmployeeFlag="
				+ EmployeeFlag + "]";
	}
	
	
	
	
	
	
}
