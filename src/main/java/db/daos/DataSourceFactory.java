package db.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;


public class DataSourceFactory {


	
	
	public static Connection getDriver() throws SQLException {
			return DriverManager.getConnection("jdbc:mysql://localhost:8889/contactApp");
		
	}
	

}
