package contactViewer.model.daos;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class DataSourceFactory {

	public static Connection getConnection() throws SQLException {
			return DriverManager.getConnection("jdbc:mysql://localhost:8889/contactApp","root","root");
		    //return DriverManager.getConnection("jdbc:sqlite:sqlite.db");
	}
	
	public static void createdatabaseSchema() throws SQLException {
		Path path = null;
		String sqlOrder= "CREATE TABLE IF NOT EXISTS person ("
				+ "idperson INT NOT NULL AUTO_INCREMENT,"
				+ "lastname VARCHAR(45) NOT NULL, "
				+ "firstname VARCHAR(45) NOT NULL,"
				+ "nickname VARCHAR(45) NOT NULL,"
				+ "phone_number VARCHAR(15) NULL,"
				+ "address VARCHAR(200) NULL,"
				+ "email_address VARCHAR(150) NULL,"
				+ "birth_date DATE NULL,"
				+ "urlPhoto VARCHAR(255),"
				+ "PRIMARY KEY (idperson));";
		
		try(Connection connection= getConnection()){
			try(Statement statement= connection.createStatement()){
				statement.execute(sqlOrder);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		
		/*
		try {
			if(Files.exists(Paths.get("src/main/ressources/sql/database_creation.sql")))
				path = Paths.get("src/main/ressources/sql/database_creation.sql");
			else {
				System.out.println("Wrong path");
			}
		}catch( Exception e) {
			System.out.println(e);
		}
		
		try {
			/*
			 * InputStream stream= Files.newInputStream(path); BufferedReader bufferedReader
			 * = new BufferedReader(new InputStreamReader(stream));
			 * la forme verbeuse de la ligne juste en bas
			 
			
		BufferedReader bufferedReader= Files.newBufferedReader(path);
		while (bufferedReader.readLine() != null)
		{
			sqlOrder.concat(bufferedReader.readLine());
		
		}
		}catch(IOException e) {
			System.out.println(e);
		}finally {
			System.out.println("ordre sql"+sqlOrder);
		}
	*/
	
}
}
