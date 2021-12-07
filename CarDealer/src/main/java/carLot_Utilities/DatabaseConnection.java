package carLot_Utilities;
//Utilities package. Created first to establish database connectivity.


import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnection {
	
	private static Connection amazonDB = null;
	
	public static Connection getConnection() {
		
		try {	
		if (amazonDB == null) {
			//Oracle added a *hotfix* to ensure that
			//the ojdbc drivers would correctly load
			//at the beginning of your application starting
			//System.out.println("Starting the hotfix!");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//System.out.println("Got past the hotfix!");
			
			String endpoint = "bjdadata.cu2hi9r5tzfs.us-east-2.rds.amazonaws.com"; //header
			String url = "jdbc:oracle:thin:@" + endpoint + ":1521:ORCL";
			String username = "brandenads";
			String password = "tpuhnobb";
			
			//System.out.println("Before the database connection!");
			amazonDB = DriverManager.getConnection(url, username, password);
			//System.out.println("After the database connection!");
			
			return amazonDB;
		} else {
			return amazonDB;
		} //end of if statement
		} catch (Exception e ) {
			System.out.println("An exception was caught connecting to the database!!!");
			e.printStackTrace();
		} //end of catch statement
		return null;
		
	} //end of getter method
	
	
	//Testing method
	public static void main(String[] args) {
		
		//Verify only one database connection is ever created!!
		Connection connect1 = getConnection();
		Connection connect2 = getConnection();
		Connection connect3 = getConnection();
		
		System.out.println("Starting database connection....");
		System.out.println(connect1);
		System.out.println(connect2);
		System.out.println(connect3);
		System.out.println("Created database connection....");
	}
	
} //end of class declaration
