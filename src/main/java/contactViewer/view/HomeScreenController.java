package contactViewer.view;

import contactViewer.service.StageService;
import contactViewer.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HomeScreenController {

	@FXML
	private TextArea newsTextArea;
	
	@FXML
	private void initialize() {
		updateTextArea();
	}
	
	public void updateTextArea() {
	
		newsTextArea.setText("Github repo: https://github.com/stevinho29/ContactApp_java\n Be ready for latest relaeased and have fun ");
		
	}
	
	@FXML
	public void showContactList() {
		StageService.showView(ViewService.getView("contactList"));
	}
	
	@FXML
	public void showAddContactView() {
		StageService.showView(ViewService.getView("addContact"));
	}
	@FXML
	public void showExportView() {
		StageService.showView(ViewService.getView("exportScreen"));
	}
}

