package contactViewer.view;

import contactViewer.service.StageService;
import contactViewer.service.ViewService;
import javafx.fxml.FXML;

public class MainLayoutController {

	@FXML
	public void onHomeMenuPressed() {
		StageService.showView(ViewService.getView("Homescreen"));
	}
	
	@FXML
	public void onCloseMenuPressed() {
		StageService.closeStage();
	}
	
	@FXML
	public void onListContactMenuPressed() {
		StageService.showView(ViewService.getView("contactList"));
	}
	
	@FXML
	public void onAddContactMenuPressed() {
		StageService.showView(ViewService.getView("addContact"));
	}
}
