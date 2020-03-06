package contactViewer.service;

import java.sql.SQLException;

import contactViewer.model.daos.PersonDao;
import contactViewer.model.entities.Person;
import javafx.collections.ObservableList;

public class PersonService {
	private ObservableList<Person> persons;
	private static PersonDao personDao;
	
	public PersonService() {
		try {
		persons= (ObservableList<Person>)personDao.listAllPerson();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static ObservableList<Person> getPersons() {
		return PersonServiceHolder.INSTANCE.persons;
	}
	
	public static void updatePerson(Person person) {
		personDao.updatePerson(person);
	}
	public static void addPerson(Person person) throws SQLException {
		personDao.addPerson(person);
	}

	private static class PersonServiceHolder {
	
			private static final PersonService INSTANCE = new PersonService();
		
	}
}
