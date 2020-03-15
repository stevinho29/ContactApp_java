package contactViewer.view;

import contactViewer.service.StageService;
import contactViewer.service.ViewService;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HomeScreenController {

	/**
	 * attribute of the HomeScreenController class
	 */
	@FXML
	private TextArea newsTextArea;
	
	/**
	 * this method update TextArea when loading homeScreen fxml layout
	 */
	@FXML
	private void initialize() {
		updateTextArea();
	}
	
	public void updateTextArea() {
	
		newsTextArea.setText("Github repo: https://github.com/stevinho29/ContactApp_java\n Be ready for latest relaeased and have fun ");
		
	}
	/**
	 * show View contactList interface
	 */
	@FXML
	public void showContactList() {
		StageService.showView(ViewService.getView("contactList"));
	}
	/**
	 * show View addContact interface
	 */
	@FXML
	public void showAddContactView() {
		StageService.showView(ViewService.getView("addContact"));
	}
	/**
	 * show View exportScreen interface
	 */
	@FXML
	public void showExportView() {
		StageService.showView(ViewService.getView("exportScreen"));
	}
}

