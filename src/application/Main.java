package application;
	
import javafx.application.Application;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * Main class sets the dimensions of the BorderPane and the characteristics.
 * Main class is the running class of the project.
 * 
 * @author Jasmin Kaur, Jonathan Delin
 *
 */
public class Main extends Application {

	/**
	 * Start Method starts the GUI, and sets it's characteristics.
	 * 
	 * @param primaryStage a window for displaying a scene that contains nodes.
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Payrollv2.fxml"));
			Scene scene = new Scene(root, 620, 620);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Company Payroll");
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Main method launches the GUI.
	 * 
	 * @param args, not used
	 */
	public static void main(String[] args) {
		launch(args);
	}
}