package contactViewer.service;

import java.sql.SQLException;
import java.util.ArrayList;

import contactViewer.model.daos.PersonDao;
import contactViewer.model.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonService {
	private ObservableList<Person> persons;
	private  PersonDao personDao;
	private ArrayList<Person> usefulList;
	
	public PersonService()  {
		
		try {
			persons=FXCollections.observableArrayList(personDao.listAllPerson());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * try { usefulList= personDao.listAllPerson(); } catch (SQLException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * for(Person p: usefulList) { persons.add(p); }
		 */
	}
	public static ObservableList<Person> getPersons() {
		return PersonServiceHolder.INSTANCE.persons;
	}
	
	public static void updatePerson(Person person) {
		PersonServiceHolder.INSTANCE.personDao.updatePerson(person);
	}
	public static void addPerson(Person person) throws SQLException {
		PersonServiceHolder.INSTANCE.personDao.addPerson(person);
	}

	private static class PersonServiceHolder {
			private static final PersonService INSTANCE = new PersonService();
	}
}
