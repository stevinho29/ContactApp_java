package contactViewer.model.daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import contactViewer.model.entities.Person;


public class PersonDao {
	
	
	public List<Person> listAllPerson() throws SQLException{
		List<Person> list=new ArrayList<>() ;
		String sql= "SELECT * FROM person";
		try(Connection connection =  DataSourceFactory.getConnection()){
			try(Statement statement= connection.createStatement()){
				try(ResultSet results= statement.executeQuery(sql)){
					while(results.next()) {
						Person pers= new Person(
								results.getString("lastname"),
								results.getString("firstname"),
								results.getString("nickname"),
								results.getInt("phone_number"),
								results.getString("address"),
								results.getString("email_address"),
								results.getDate("birth_date").toLocalDate(),
								results.getString("urlPhoto"));
						list.add(pers);
					}
				}
			}
		}
		//System.out.println("la liste"+list);
		return list;
		
	}
	
	public void addPerson(Person person) throws SQLException {
		
		try(Connection connection= DataSourceFactory.getConnection()){
			String sql = "INSERT INTO person(lastname,firstname,nickname,phone_number,address,email_address,birth_date,urlPhoto) values(?,?,?,?,?,?,?,?)";
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, person.getLastname());
				statement.setString(2, person.getFirstname());
				statement.setString(3, person.getNickname());
				statement.setInt(4, person.getPhone_number());
				statement.setString(5, person.getAddress());
				statement.setString(6, person.getEmail_address());
				statement.setDate(7, java.sql.Date.valueOf(person.getBirth_date()));
				statement.setString(8, person.getUrl_photo());
				int nbRows= statement.executeUpdate();
				System.out.println("nb de lignes :"+nbRows);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void updatePerson(Person person) {
		
		try(Connection connection=  DataSourceFactory.getConnection()){
			String sql = "UPDATE person SET firstname= ?, nickname= ?,phone_number=?,address=?,email_address=?,birth_date=?,urlPhoto=? WHERE lastname = ? ";
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, person.getFirstname());
				statement.setString(2, person.getNickname());
				statement.setInt(3, person.getPhone_number());
				statement.setString(4, person.getAddress());
				statement.setString(5, person.getEmail_address());
				statement.setDate(6, java.sql.Date.valueOf(person.getBirth_date()));
				statement.setString(7, person.getUrl_photo());
				statement.setString(8, person.getLastname());
				int nbRows= statement.executeUpdate();
				System.out.println("nb de lignes :"+nbRows);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void deletePerson(Person person) {
		
		String sqlOrder= "DELETE FROM person WHERE email_address= ?";
		
		try(Connection connection= (Connection) DataSourceFactory.getConnection()){
			try(PreparedStatement statement= connection.prepareStatement(sqlOrder)){
				statement.setString(1, person.getEmail_address());
				statement.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void addUrlPhoto(String email, String urlPhoto) {
		try(Connection connection= DataSourceFactory.getConnection()){
			String sql = "UPDATE person SET urlPhoto= ? WHERE email_address = ? ";
			try(PreparedStatement statement = connection.prepareStatement(sql)){
				statement.setString(1, urlPhoto);
				statement.setString(2, email);
				int nbRows= statement.executeUpdate();
				System.out.println("nb de lignes :"+nbRows);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
