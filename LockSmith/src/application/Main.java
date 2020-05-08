
package application;

import application.controller.MainController;
import application.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Group Project : LockSmith   Spring-2020
 * 
 * @authors Kyle Evers(RIY335) ; Sabita Paudyal Ghimire(agi486); 
 *          Jonathan Villreal (zyj680); Pedro Jusino(ayt689) ;
 *          William G (Qoi678)
 *          
 *    This is the driver Class, entry point with main method.      
 *
 */
public class Main extends Application {
    //overriding start method of Application class to load and control fxml files 
	@Override
	public void start(Stage primaryStage) {
		try {
			// creating an object of MainController and setting stage, loader and parent
			MainController mCtrl = new MainController(primaryStage); // stage will be used to change scenes
			FXMLLoader loader = new FXMLLoader(getClass().getResource("view/LoginPage.fxml"));
			// Linking the LoginPage.fxml to main controller
			loader.setController(mCtrl);
			Parent root = loader.load();
			Scene scene = new Scene(root, 600, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("LockSmith");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
		
	}
}
