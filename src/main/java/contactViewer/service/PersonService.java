package contactViewer.service;

import java.sql.SQLException;
import java.util.List;

import contactViewer.model.daos.PersonDao;
import contactViewer.model.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonService {
	private ObservableList<Person> persons;
	private  PersonDao personDao= new PersonDao();
	private List<Person> usefulList;
	
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
	public static ObservableList<Person> getPersons() {
		return PersonServiceHolder.INSTANCE.persons;
	}
	
	public static void updatePerson(Person person) {
		PersonServiceHolder.INSTANCE.personDao.updatePerson(person);
	}
	public static void addPerson(Person person) throws SQLException {
		PersonServiceHolder.INSTANCE.personDao.addPerson(person);
	}
	public static void addUrlPhoto( String email,String urlPhoto) {
		PersonServiceHolder.INSTANCE.personDao.addUrlPhoto(email, urlPhoto);;
	}

	private static class PersonServiceHolder {
			private static final PersonService INSTANCE = new PersonService();
	}
}
