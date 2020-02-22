package contactApp.contactApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import contactApp.model.daos.DataSourceFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Start extends Application {

	@Override
	public void start(Stage primaryStage) {
		
	}

	public static void main(String[] args) throws IOException, SQLException {
		launch(args);
		System.out.println( "Hello World!" );
        
        try(Connection con= DataSourceFactory.getConnection()){
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
        
        DataSourceFactory.createdatabaseSchema();
	}
}
