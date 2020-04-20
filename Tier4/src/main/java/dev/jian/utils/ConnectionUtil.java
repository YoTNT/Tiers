package dev.jian.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	public static Connection createConnection() {
		
		try {
			Properties props = new Properties();
			
			FileInputStream in = new FileInputStream("src/main/resources/connection.properties");
			
			props.load(in);
			
			String details = props.getProperty("condetails");
			
			Connection conn = DriverManager.getConnection(details);
			
			return conn;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		

		Connection conn = createConnection();
		System.out.println(conn);

	}

}
