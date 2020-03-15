package contactViewer.view;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import contactViewer.model.daos.PersonDao;
import contactViewer.model.entities.Person;
import contactViewer.service.StageService;
import contactViewer.service.VcardService;
import contactViewer.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;

public class ExportScreenController {

	/**
	 * attribute of the  ExportScreenController class
	 */
	@FXML
	private Button defaultExportButton;
	@FXML
	private Button personnalizedExportButton;
	@FXML
	private Text defaultExportText;
	@FXML
	private Text personnalizedExportText;
	
	private static PersonDao personDao= new PersonDao();
	private static VcardService vcard= new VcardService();
	private static List<Person>  personList;
	
	/**
	 * we request from the database the list of person in a different thread when loading exportScreen fxml layout 
	 */
	@FXML
	public void initialize() {
		
		 Thread t = new Thread() {
		      public void run() {
		        try {
					personList= personDao.listAllPerson();
					System.out.println("liste de personnes"+personList);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      }
		    };
		    t.start();
			
	}
	
	/**
	 * export all contact
	 * @throws IOException
	 * @throws SQLException
	 */
	@FXML
	public void export() throws IOException, SQLException {
		showComponents();
	}
	@FXML
	public void exportByCategory() {
		
		hideComponents();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notifications");
		//alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText("Fonctionnalité pas encore implémentée");

		alert.showAndWait();
	}
	
	private void toggleComponentsVisibility() {
	
		if(!this.defaultExportText.isVisible()) {
			this.defaultExportText.setVisible(true);
			this.personnalizedExportText.setVisible(true);
			this.defaultExportButton.setVisible(true);
			this.personnalizedExportButton.setVisible(true);
		}else {
			this.defaultExportText.setVisible(false);
			this.personnalizedExportText.setVisible(false);
			this.defaultExportButton.setVisible(false);
			this.personnalizedExportButton.setVisible(false);
		}
	}
	
	private void hideComponents() {
		this.defaultExportText.setVisible(false);
		this.personnalizedExportText.setVisible(false);
		this.defaultExportButton.setVisible(false);
		this.personnalizedExportButton.setVisible(false);
	}
	
	private void showComponents() {
		this.defaultExportText.setVisible(true);
		this.personnalizedExportText.setVisible(true);
		this.defaultExportButton.setVisible(true);
		this.personnalizedExportButton.setVisible(true);
	}
	@FXML
	private void defaultExportation() {
		try {
			vcard.orchestrator(personList);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Exportation réussie");
			//alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Exportation réussie "+VcardService.counter+" mis à jour ");
	
			alert.showAndWait();
		
		}catch( IOException e) {
			System.out.println("IOEXCEPTION");
		}
		catch( Exception e) {
			System.out.println("EXCEPTION");
			e.printStackTrace();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Notifications");
			//alert.setHeaderText("Look, an Information Dialog");
			alert.setContentText("Une erreur s'est produite lors de l'exportation");

			alert.showAndWait();
		}finally {
			toggleComponentsVisibility();
		}
	}
	
	@FXML
	private void personnalizedExportation() {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Select a directorry as output directory");

		File selectedDirectory = chooser.showDialog(StageService.getPrimaryStage());
		if( selectedDirectory.getAbsolutePath() != null) {
			try {
				System.out.println(selectedDirectory.getAbsolutePath());
				vcard.personnalizedExport(personList, selectedDirectory.getAbsolutePath());
			}catch(Exception e ) {
				e.printStackTrace();
			}finally {
				toggleComponentsVisibility();
			}
		}
		
	}
	/**
	 * return to the homeScreen view
	 */
	@FXML
	private void onBackPressed() {
		StageService.showView(ViewService.getView("HomeScreen"));
	}
	
	

}

