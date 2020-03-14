package contactViewer.view;

import contactViewer.service.StageService;
import contactViewer.service.ViewService;
import javafx.fxml.FXML;

public class MainLayoutController {

	/**
	 * show HomeScreen fxml layout
	 */
	@FXML
	public void onHomeMenuPressed() {
		StageService.showView(ViewService.getView("Homescreen"));
	}
	
	/**
	 * close stage
	 */
	@FXML
	public void onCloseMenuPressed() {
		StageService.closeStage();
	}
	/**
	 * show contactList fxml layout
	 */
	@FXML
	public void onListContactMenuPressed() {
		StageService.showView(ViewService.getView("contactList"));
	}
	/**
	 * show addContact fxml layout
	 */
	@FXML
	public void onAddContactMenuPressed() {
		StageService.showView(ViewService.getView("addContact"));
	}
}
