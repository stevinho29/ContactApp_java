package contactViewer.service;

import java.io.IOException;


import contactViewer.App.Main;
import javafx.fxml.FXMLLoader;

public class ViewService {

	public static <T> T getView(String id) {
		return getLoader(id).getRoot();
	}
	/**
	 * return loader with variable id parameter
	 * @param id
	 * @return
	 */
	private static FXMLLoader getLoader(String id) {
		try {
			
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("view/"+id + ".fxml"));
				loader.load();
				return loader;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
