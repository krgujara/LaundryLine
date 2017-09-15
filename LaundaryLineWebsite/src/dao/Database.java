package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

	public Connection get_connection() throws Exception{
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//create connection object
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
			System.out.println("Connection Successful");
			
			return con;
		}catch(Exception e)
		{
			throw e;
		}
	}
}
