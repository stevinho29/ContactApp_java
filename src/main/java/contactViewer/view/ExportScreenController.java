package contactViewer.view;

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

public class ExportScreenController {

	private static PersonDao personDao= new PersonDao();
	private static VcardService vcard= new VcardService();
	private static List<Person>  personList;
	
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
	
	@FXML
	public void export() throws IOException, SQLException {
		
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
		}
	
	}
	@FXML
	public void exportByCategory() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notifications");
		//alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText("Fonctionnalité pas encore implémentée");

		alert.showAndWait();
	}
	
	@FXML
	private void onBackPressed() {
		StageService.showView(ViewService.getView("HomeScreen"));
	}
	
	

}

