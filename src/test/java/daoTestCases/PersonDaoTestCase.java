package daoTestCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contactApp.model.daos.DataSourceFactory;
import contactApp.model.daos.PersonDao;
import contactApp.model.entities.Person;


public class PersonDaoTestCase {

	@Before
	public void beforeEachTest() {
		String sqlOrder= "CREATE TABLE IF NOT EXISTS person ("
				+ "idperson INT NOT NULL AUTO_INCREMENT,"
				+ "lastname VARCHAR(45) NOT NULL, "
				+ "firstname VARCHAR(45) NOT NULL,"
				+ "nickname VARCHAR(45) NOT NULL,"
				+ "phone_number VARCHAR(15) NULL,"
				+ "address VARCHAR(200) NULL,"
				+ "email_address VARCHAR(150) NULL,"
				+ "birth_date DATE NULL,"
				+ "UrlPhoto VARCHAR(255) NULL,"
				+ "PRIMARY KEY (idperson));";
		
		try(Connection connection= DataSourceFactory.getConnection()){
			try(Statement statement= connection.createStatement()){
				statement.execute(sqlOrder);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	@After
	public void afterEachTest() {
		
		String sqlOrder= "DELETE FROM person WHERE lastname= ?";
		
		try(Connection connection= DataSourceFactory.getConnection()){
			try(PreparedStatement statement= connection.prepareStatement(sqlOrder)){
				statement.setString(1, "steve");
				
				statement.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldAddAPerson() throws SQLException {
		//GIVEN
		PersonDao personDao= new PersonDao();
		Person person= new Person("steve","mC","spiritueux");
		String sql= "SELECT * FROM person WHERE email_address= ?"; // parce que l'email est unique 
		//WHEN
		personDao.addPerson(person);
		//THEN
		try(Connection connection= DataSourceFactory.getConnection()){
			try(PreparedStatement statement= connection.prepareStatement(sql)){
				statement.setString(1,person.getEmail_address());
				try(ResultSet results= statement.executeQuery()){
						while(results.next()) 
							assertThat(results.getString("lastname")).isEqualTo("steve");
		
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void shouldDeletePerson() throws SQLException {
		//GIVEN
		PersonDao personDao= new PersonDao();
		Person person= new Person("steve","mC","spiritueux");
		personDao.addPerson(person); 
		//WHEN
		personDao.deletePerson(person);
		//THEN
		String sql= "SELECT * FROM person WHERE email_address= ?"; // parce que l'email est unique
		try(Connection connection= DataSourceFactory.getConnection()){
			try(PreparedStatement statement= connection.prepareStatement(sql)){
				statement.setString(1,person.getEmail_address());
				try(ResultSet results= statement.executeQuery()){
						while(results.next()) 
							assertThat(results.next()).isFalse();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void shouldUpdatePerson() throws SQLException {
		//GIVEN
		PersonDao personDao= new PersonDao();
		Person person= new Person("steve","mC","spiritueux");
		personDao.addPerson(person); 
		person.setFirstname("new firstname");
		//WHEN
		personDao.updatePerson(person);
		//THEN
		String sql= "SELECT * FROM person WHERE email_address= ?"; // parce que l'email est unique
		try(Connection connection= DataSourceFactory.getConnection()){
			try(PreparedStatement statement= connection.prepareStatement(sql)){
				statement.setString(1,person.getEmail_address());
				try(ResultSet results= statement.executeQuery()){
						while(results.next()) 
							assertThat(results.getString("firstname")).isEqualTo("new firstname");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldListAllPerson() throws SQLException {
		//GIVEN
		List<Person> list;
		PersonDao personDao= new PersonDao();
		Person person= new Person("steve","mC","spiritueux");
		personDao.addPerson(person); 
		//WHEN
		list= personDao.listAllPerson();
		//THEN 
		assertThat(list.size()).isEqualTo(1);
		
	}
}
