package contactViewer.view;

import java.sql.SQLException;

import contactViewer.model.daos.PersonDao;
import contactViewer.model.entities.Person;
import contactViewer.service.PersonService;
import contactViewer.service.StageService;
import contactViewer.service.ViewService;
import contactViewer.util.PersonValueFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class contactListController {

	@FXML
	private TextField firstnameField;
	@FXML
	private TextField lastnameField;
	@FXML
	private TextField nicknameField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField emailAddressField;
	@FXML
	private TextField phoneNumberField; 
	@FXML
	private DatePicker birthDatePicker;
	@FXML
	private TextField urlPhoto;
	
	@FXML
	public TableView<Person> personsTable;
	@FXML
	public TableColumn<Person,String> personColumn;
	
	@FXML 
	private AnchorPane formPane;
	private static Person currentPerson= new Person();
	
	private static PersonDao personDao= new PersonDao();
	
	
	@FXML
	private void initialize() throws SQLException {
		
		this.personColumn.setCellValueFactory(new PersonValueFactory());
		//populateList();
		
		personsTable.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Object>() {

		@Override
		public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				showPersonDetails(personsTable.getSelectionModel().getSelectedItem());
			}
		});
	}
	
	private void refreshList() {
		this.personsTable.refresh();
		this.personsTable.getSelectionModel().clearSelection();;
	}
	
	private void populateList() throws SQLException {
		
		this.personsTable.setItems(PersonService.getPersons());
		refreshList();
	}
	
	private void resetView() {
		showPersonDetails(null);
		refreshList();
	}
	
	@FXML 
	public void onSaveHandler() {
		  currentPerson.setLastname(this.lastnameField.getText());
		  currentPerson.setFirstname(this.firstnameField.getText());
		  currentPerson.setNickname(this.nicknameField.getText());
		  currentPerson.setAddress(this.addressField.getText());
		  currentPerson.setEmail_address(this.emailAddressField.getText());
		  currentPerson.setPhone_number(Integer.valueOf(this.phoneNumberField.getText()));
		  currentPerson.setBirth_date(this.birthDatePicker.getValue());
		  try {
		  personDao.updatePerson(currentPerson);
		  
		  resetView();      // à voir si utile de reset la view 
		  
		  Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifications");
			//alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Sauvegarde effectuée avec succès");

			alert.showAndWait();
			
		  }catch(Exception e) {
			  e.printStackTrace();
			  
			  Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Notifications");
				//alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("Une erreur s'est prosuite lors de la sauvegarde");

				alert.showAndWait();
		  }
	}
	@FXML 
	public void onDeleteButtonPressed() {
		
		  currentPerson.setLastname(this.lastnameField.getText());
		  currentPerson.setFirstname(this.firstnameField.getText());
		  currentPerson.setNickname(this.nicknameField.getText());
		  currentPerson.setAddress(this.addressField.getText());
		  currentPerson.setEmail_address(this.emailAddressField.getText());
		  currentPerson.setPhone_number(Integer.valueOf(this.phoneNumberField.getText()));
		  currentPerson.setBirth_date(this.birthDatePicker.getValue());
		 
		  try {
		  personDao.deletePerson(currentPerson);
		  
		  int selectedIndex = personsTable.getSelectionModel().getSelectedIndex();
	    	if (selectedIndex >= 0)
	    	{
	    		personsTable.getItems().remove(selectedIndex);
	    		resetView();
	    	}
		  }catch(Exception e) {
			  Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Notifications");
				//alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("Une erreur s'est produite lors de la suppression");

				alert.showAndWait();
		  }
		  
	}
	@FXML
	public void onBackPressed() {
		StageService.showView(ViewService.getView("HomeScreen"));
	}
	
	@FXML
	private void clearFields() {
		
		this.firstnameField.setText("");
		this.addressField.setText("");
		this.emailAddressField.setText("");
		this.lastnameField.setText("");
		this.phoneNumberField.setText("");
		this.birthDatePicker.setValue(null);
		this.nicknameField.setText("");
	}
	@FXML 
	private void showPersonDetails(Person person) {
		if(person == null)
			this.formPane.setVisible(false);
		else {
			this.formPane.setVisible(true);
			// fill in question field
			currentPerson= person;
			this.firstnameField.setText(currentPerson.getFirstname());
			this.addressField.setText(currentPerson.getAddress());
			this.emailAddressField.setText(currentPerson.getEmail_address());
			this.lastnameField.setText(currentPerson.getLastname());
			this.phoneNumberField.setText(currentPerson.getPhone_number().toString());
			this.birthDatePicker.setValue(currentPerson.getBirth_date());
			this.nicknameField.setText(currentPerson.getNickname());
			
		}
	}
}
