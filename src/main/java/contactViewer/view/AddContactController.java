package contactViewer.view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

import contactViewer.model.daos.PersonDao;
import contactViewer.model.entities.Person;
import contactViewer.service.PersonService;
import contactViewer.service.StageService;
import contactViewer.service.ValidationService;
import contactViewer.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddContactController {
	
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
	
	private static Person currentPerson = new Person();
	
	private static PersonDao personDao= new PersonDao();
	private static Map<String, Boolean>  validationMap;
	
	@FXML
	private void onBackPressed() {
		StageService.showView(ViewService.getView("HomeScreen"));
	}
	
	@FXML
	private void onSaveHandler() throws SQLException {
		
		if(!this.lastnameField.getText().isEmpty() && !this.firstnameField.getText().isEmpty() && !this.nicknameField.getText().isEmpty()&& !this.addressField.getText().isEmpty() && !this.emailAddressField.getText().isEmpty()
				&& !this.phoneNumberField.getText().isEmpty() && this.birthDatePicker.getValue() != null) {
			/*
			 * currentPerson.setLastname(this.lastnameField.getText());
			 * currentPerson.setFirstname(this.firstnameField.getText());
			 * currentPerson.setNickname(this.nicknameField.getText());
			 * currentPerson.setAddress(this.addressField.getText());
			 * currentPerson.setEmail_address(this.emailAddressField.getText());
			 * currentPerson.setPhone_number(Integer.valueOf(this.phoneNumberField.getText()
			 * )); //if(!this.urlPhoto.getText().isEmpty())
			 * //currentPerson.setUrl_photo(this.urlPhoto.getText());
			 * currentPerson.setBirth_date(this.birthDatePicker.getValue());
			 */
		
		//validationMap= ValidationService.registerValidation(currentPerson);
		
			if(!ValidationService.isValidPhoneNumber(this.phoneNumberField.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Notifications");
				alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("veuillez rentrer un numéro au bon format sans lettres ni caractères spéciaux");

				alert.showAndWait();
				
				return;
			}

			
			else if(!ValidationService.isValidEmailFormat(this.emailAddressField.getText())) { // on check le format d'email
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Notifications");
				alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("Le format de l'email est incorrect");

				alert.showAndWait();
				return;
			}
			else {
				 
				try {
					currentPerson= new Person(this.lastnameField.getText(),this.firstnameField.getText(),this.nicknameField.getText(),Integer.valueOf(this.phoneNumberField.getText()),this.addressField.getText(),this.emailAddressField.getText(),this.birthDatePicker.getValue(),"nourl");
					//PersonService.addPerson(currentPerson);
					personDao.addPerson(currentPerson);
					
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Notifications");
					alert.setHeaderText("Look, an Information Dialog");
					alert.setContentText("sauvegarde éffectuée avec succès");
	
					alert.showAndWait();
					return;
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}

		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifications");
			//alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Tous les champs sont vides");

			alert.showAndWait();
		}

	}
	@FXML
	private void onCancelHandler() {
		this.firstnameField.setText("");
		this.addressField.setText("");
		this.emailAddressField.setText("");
		this.lastnameField.setText("");
		this.phoneNumberField.setText("");
		this.birthDatePicker.setValue(null);
		this.nicknameField.setText("");
	}

}
