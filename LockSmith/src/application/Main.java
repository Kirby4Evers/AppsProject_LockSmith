package application;


import application.controller.MainController;
import application.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


	public class Main extends Application {
		@Override
		public void start(Stage primaryStage) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/application/view/LoginPage.fxml"));
				MainController mCtrl = new MainController(primaryStage); //stage will be used to change scenes
				FXMLLoader.setController(mCtrl);
				
				Scene scene = new Scene(root,600,600);
				primaryStage.setScene(scene);
				primaryStage.setTitle("LockSmith");
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			launch(args);
			/*
			Model m = new Model();
			System.out.println( System.getProperty("user.dir") );
			m.signUp("username", "masterPassword");
			*/
		}
	}
