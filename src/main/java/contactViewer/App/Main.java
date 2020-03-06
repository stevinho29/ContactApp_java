package contactViewer.App;
	
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import contactViewer.model.daos.DataSourceFactory;
import contactViewer.service.StageService;
import contactViewer.service.ViewService;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws SQLException, URISyntaxException {
		StageService.initPrimaryStage(primaryStage);
		StageService.showView((Node) ViewService.getView("HomeScreen"));
		
		System.out.println( "Hello World!" );
	     
        DataSourceFactory.createdatabaseSchema();
	}

	public static void main(String[] args) throws IOException, SQLException {
		launch(args);
	
	}
}
