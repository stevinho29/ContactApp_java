package contactViewer.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import contactViewer.model.daos.PersonDao;
import contactViewer.model.entities.Person;
import contactViewer.service.PersonService;
import contactViewer.service.ProfilePhotoService;
import contactViewer.service.StageService;
import contactViewer.service.VcardService;
import contactViewer.service.ViewService;
import contactViewer.util.PersonValueFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ContactListController {

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
	private Button uploadPhotoButton;
	
	private String urlPhoto;
	
	@FXML 
	private ImageView profilePhoto;
	
	@FXML
	public TableView<Person> personsTable;
	@FXML
	public TableColumn<Person,String> personColumn;
	
	@FXML 
	private AnchorPane formPane;
	private static Person currentPerson= new Person();
	
	private static PersonDao personDao= new PersonDao();
	
	private static ProfilePhotoService photoService= new ProfilePhotoService();
	
	private static VcardService vcard= new VcardService();
	@FXML
	private void initialize() throws SQLException {
		
		this.personColumn.setCellValueFactory(new PersonValueFactory());
		populateList();
		
		personsTable.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<Object>() {
		
		@Override
		public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
				// TODO Auto-generated method stub
				try {
					//profilePhoto.setVisible(true);
					showPersonDetails(personsTable.getSelectionModel().getSelectedItem());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	private void refreshList() {
		this.personsTable.refresh();
		this.personsTable.getSelectionModel().clearSelection();;
	}
	
	public void populateList() throws SQLException {
		
		this.personsTable.setItems(PersonService.getPersons());
		refreshList();
	}
	
	public void resetView() throws FileNotFoundException {
		showPersonDetails(null);
		refreshList();
	}
	@FXML
	public void exportOne() {
		if(!this.lastnameField.getText().isEmpty() && !this.firstnameField.getText().isEmpty() && !this.nicknameField.getText().isEmpty()&& !this.addressField.getText().isEmpty() && !this.emailAddressField.getText().isEmpty()
				&& !this.phoneNumberField.getText().isEmpty() && this.birthDatePicker.getValue() != null) {
			  currentPerson.setLastname(this.lastnameField.getText());
			  currentPerson.setFirstname(this.firstnameField.getText());
			  currentPerson.setNickname(this.nicknameField.getText());
			  currentPerson.setAddress(this.addressField.getText());
			  currentPerson.setEmail_address(this.emailAddressField.getText());
			  currentPerson.setPhone_number(Integer.valueOf(this.phoneNumberField.getText()));
			  currentPerson.setBirth_date(this.birthDatePicker.getValue());
			  try {
				  vcard.orchestrator(currentPerson);  // l'orchestrator se charge de l'exportation
				  Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Notifications");
					//alert.setHeaderText("Look, an Information Dialog");
					alert.setContentText("Opération d'exporation réussie");

					alert.showAndWait();
			  }catch(Exception e) {
				  e.printStackTrace();
				  Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Notifications");
					//alert.setHeaderText("Look, an Information Dialog");
					alert.setContentText("une erreur s'est produite lors de l'exportation");

					alert.showAndWait();
				  
			  }
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifications");
			//alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("un ou des champs sont vides... exportation impossible");

			alert.showAndWait();
		}
	}
	@FXML 
	public void onSaveHandler() {
		
		if(!this.lastnameField.getText().isEmpty() && !this.firstnameField.getText().isEmpty() && !this.nicknameField.getText().isEmpty()&& !this.addressField.getText().isEmpty() && !this.emailAddressField.getText().isEmpty()
				&& !this.phoneNumberField.getText().isEmpty() && this.birthDatePicker.getValue() != null) {
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
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifications");
			//alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("un ou des champs sont vides... sauvegarde impossible");

			alert.showAndWait();
		}
	}
	@FXML 
	public void onDeleteButtonPressed() {
		
		if(!this.lastnameField.getText().isEmpty() && !this.firstnameField.getText().isEmpty() && !this.nicknameField.getText().isEmpty()&& !this.addressField.getText().isEmpty() && !this.emailAddressField.getText().isEmpty()
				&& !this.phoneNumberField.getText().isEmpty() && this.birthDatePicker.getValue() != null) {
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
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifications");
			//alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("un ou des champs sont vides... suppression impossible");

			alert.showAndWait();
		}
		  
	}
	@FXML
	public void onBackPressed() {
		StageService.showView(ViewService.getView("HomeScreen"));
	}
	
	@FXML
	public void updatePhoto() throws IOException, SQLException {
		
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 fileChooser.getExtensionFilters().addAll(
		         new ExtensionFilter("Text Files", "*.txt"),
		         new ExtensionFilter("Image Files", "*.png","*.jpeg" ,"*.jpg", "*.gif"),
		         new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
		         new ExtensionFilter("All Files", "*.*"));
		File selectedFile = fileChooser.showOpenDialog(StageService.getPrimaryStage());
		this.urlPhoto= selectedFile.getAbsolutePath();
		
		try {
			System.out.println(this.emailAddressField.toString());
			photoService.orchestrator(this.emailAddressField.getText(),this.urlPhoto);
			 Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Notifications");
				//alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("opération d'Upload éffectuée avec succès ");
				alert.showAndWait();
		
		}catch(Exception e) {
			System.out.println("ERROR WHEN UPLOADING ");
			e.printStackTrace();
			 Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Notifications");
				//alert.setHeaderText("Look, an Information Dialog");
				alert.setContentText("Une erreur s'est produite lors de l'upload de la photo");

				alert.showAndWait();
		}finally {
			populateList();
		}
		
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
	private void showPersonDetails(Person person) throws FileNotFoundException {
		if(person == null)
			this.formPane.setVisible(false);
		else {
			this.formPane.setVisible(true);
			this.uploadPhotoButton.setVisible(true);
			// fill in question field
			currentPerson= person;
			this.firstnameField.setText(currentPerson.getFirstname());
			this.addressField.setText(currentPerson.getAddress());
			this.emailAddressField.setText(currentPerson.getEmail_address());
			this.lastnameField.setText(currentPerson.getLastname());
			this.phoneNumberField.setText(currentPerson.getPhone_number().toString());
			this.birthDatePicker.setValue(currentPerson.getBirth_date());
			this.nicknameField.setText(currentPerson.getNickname());
			this.urlPhoto= currentPerson.getUrl_photo();
			
			if(this.urlPhoto.compareTo("nourl") != 0) {
				try {
				Image image = new Image(new FileInputStream(urlPhoto)); 
				this.profilePhoto.setImage(image);
				
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			else {
				this.profilePhoto.setImage(null);
			}
			
		}
	}
	

}
