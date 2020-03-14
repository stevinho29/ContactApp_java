package contactViewer.service;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class StageService {

		/**
		 * initialize the MainLayout interface
		 */
		private StageService() {
			mainLayout = ViewService.getView("MainLayout");
		}
		/**
		 *initialize the  StageService INSTANCE
		 */
		private static class StageServiceHolder {
			private static final StageService INSTANCE = new StageService();
		}
		/**
		 * attribute of the  StageService class
		 */
		private Stage primaryStage;

		private BorderPane mainLayout;
		
		/**
		 * @return return StageServiceHolder.INSTANCE.primaryStage
		 */
		public static Stage getPrimaryStage() {
			return StageServiceHolder.INSTANCE.primaryStage;
		}
		/**
		 * initialize the PrimaryStage with the stage variable 
		 * @param primaryStage
		 */
		public static void initPrimaryStage(Stage primaryStage) {
			primaryStage.setTitle("ContactUs");
			primaryStage.setScene(new Scene(StageServiceHolder.INSTANCE.mainLayout));
			primaryStage.show();

			StageServiceHolder.INSTANCE.primaryStage = primaryStage;
		}
		/**
		 * show view with the Node variable
		 * @param rootElement
		 */
		public static void showView(Node rootElement) {
			StageServiceHolder.INSTANCE.mainLayout.setCenter(rootElement);
		}
		/**
		 * close the stage
		 */
		public static void closeStage() {
			StageServiceHolder.INSTANCE.primaryStage
					.fireEvent(new WindowEvent(StageServiceHolder.INSTANCE.primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));

		}

	}


