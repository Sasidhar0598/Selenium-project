package com.simplilearn.seleniumtest.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JdbcDemo {
	
	private final static String DB_URL = "jdbc:mysql://localhost:3306/estore_zone_db";
	private final static String DB_USR = "root";
	private final static String DB_PAS = "sasi234@";

	static Connection connection = null;
	static Statement statment = null;
	static ResultSet rst;
	static WebDriver driver;

	public static void main(String[] args) {
		
		try {
			// step1 : Register Driver (optional)
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("1. Register Driver class.");

			// step2 : Create a connection
			connection = DriverManager.getConnection(DB_URL, DB_USR, DB_PAS);
			System.out.println("2. Connection is created.");

			// step3 : Create a statement
			statment = connection.createStatement();
			System.out.println("3. Statement is created.");

			// step4 : Execute query
			String query = "select * from eproducts;";
			rst = statment.executeQuery(query);
			while (rst.next()) {
			     System.out.println(rst.getInt(1)+"  "+rst.getString(2)+"  "+rst.getString(3));  
			}
			System.out.println("4. Query is executed.");

		} catch (ClassNotFoundException e) {
			System.out.println("Exception Occured ::: " + e.getClass());
		} catch (SQLException e) {
			System.out.println("Exception Occured ::: " + e.getClass());
			// e.printStackTrace();
		} finally {
			// clean up
			//try {
				// step5 : close connection
				//rst.close();
				//statment.close();
				//connection.close();
				//System.out.println("5. Closing the connection.");
			//} catch (SQLException e) {
				//System.out.println("Exception Occured ::: " + e.getClass());
			//}

		}
		setUp();
		listProduct();
	}
	
	public static void setUp() {
		// step1: formulate a test domain url & driver path
		String siteUrl = "http://localhost:9090/WebProject/";
		String driverPath = "drivers/windows/geckodriver.exe";

		// step2: set system properties for selenium dirver
		System.setProperty("webdriver.chrome.driver", driverPath);

		// step3: instantiate selenium webdriver
		driver = new FirefoxDriver();

		// step4: add implicit wait (Unconditional Delay)
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));

		// step5: launch browser
		driver.get(siteUrl);
	}
	
	public static void listProduct() {
		driver.findElement(By.xpath("/html/body/div/a[4]")).click();
	}

}
