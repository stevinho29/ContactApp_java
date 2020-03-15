package contactViewer.service;

import java.sql.SQLException;
import java.util.List;

import contactViewer.model.daos.PersonDao;
import contactViewer.model.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonService {
	/**
	 * attribute of the PersonService class
	 */
	private ObservableList<Person> persons;
	private  PersonDao personDao= new PersonDao();
	private List<Person> usefulList;
	
	/**
	 * default constructor of the PersonService class
	 */
	public PersonService()  {
		
		try {
			//System.out.println(personDao.listAllPerson());
			usefulList= personDao.listAllPerson();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(usefulList != null){
			persons= FXCollections.observableArrayList(usefulList);
		}
		else
			persons= FXCollections.observableArrayList();		
		
	}
	/**
	 *  
	 * @return return a list of all persons inside the database
	 */
	public static ObservableList<Person> getPersons() {
		return PersonServiceHolder.INSTANCE.persons;
	}
	/**
	 * update a person inside database by giving a Person object
	 * @param person
	 */
	public static void updatePerson(Person person) {
		PersonServiceHolder.INSTANCE.personDao.updatePerson(person);
	}
	/**
	 * add a person inside database by giving a Person object
	 * @param Person person
	 * @throws SQLException
	 */
	public static void addPerson(Person person) throws SQLException {
		PersonServiceHolder.INSTANCE.personDao.addPerson(person);
	}
	/**
	 * update the  urlphoto and email  of a person with a Strings parameters email and urlphoto 
	 * @param email
	 * @param urlPhoto
	 */
	public static void addUrlPhoto( String email,String urlPhoto) {
		PersonServiceHolder.INSTANCE.personDao.addUrlPhoto(email, urlPhoto);;
	}
	/**
	 * initialize the instance PersonService
	 */
	private static class PersonServiceHolder {
			private static final PersonService INSTANCE = new PersonService();
	}
}
