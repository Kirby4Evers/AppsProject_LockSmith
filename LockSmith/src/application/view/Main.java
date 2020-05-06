package application.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main {

public void start(Stage primaryStage) {
	try 
	{	
		FXMLLoader FLoader = new FXMLLoader(getClass().getResource("../view/Whatever.fxml"));
		//FLoader.setController( );
		Parent FRoot = FLoader.load();
		//Scene FScene = new Scene(FRoot,xSize,ySize);
		
		
	//	primaryStage.setScene(FScene);
		primaryStage.setTitle("Title");
		primaryStage.show();
		} 
		catch(Exception e) 
			{ e.printStackTrace(); }
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
