package application.controller;

import java.io.IOException;

import application.Main;
import application.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MainController {
	private Stage stage;
	private Model model;
	
	MainController(Stage s)
	{
		this.stage = s; //used for switching scenes
		this.model = new Model();
	}
	
	@FXML
	private TextField inputField1;
	@FXML
	private TextField inputField2;
	
	
	@FXML
	private TextField user;
	@FXML
	private TextField password;
	@FXML
	private TextField website;
	@FXML
	private TextField email;
	
	/*
	// Creating object of model class
	Model model = new Model();
	//object of this class
	MainController mainController= new MainController();
	//object of Controller2 class
	Controller2 controller2= new Controller2();
	Stage primaryStage;
	*/
	

	/**
	 * called when sign up button is pressed
	 */
	@FXML
	public void handle2() {

		String username = inputField1.getText();
		String masterPassword = inputField2.getText();
	
		model.signUp(username, masterPassword);
	}

	/**
	 * called when sign in button is pressed
	 * 
	 * @throws IOException
	 */
	@FXML
	public void handle1() throws IOException {
		String username = inputField1.getText();
		String password = inputField2.getText();
	
		boolean isMatch = model.login(username, password);
		// if match, should go to next page, that shows all the websites
		if (isMatch) {
			loadWebsites();
			
		}
		// if password is not match, dialog box will appear with message.
	else {
			Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Incorrect Password!!");
			alert.show();
		}
		
			

	}
	//getting user input and saving into the files related to that user
	@FXML
	public void save() {
		String usr=user.getText();
		String pass=password.getText();
		String ema= email.getText();
		String web= website.getText();
		
		
	}
	@FXML
	public void loadWebsites() {
		ShowEntriesController seCtrl = new ShowEntriesController(model, stage);
		FXMLLoader seLoader = new FXMLLoader(getClass().getResource("src/application/view/showEntries.fxml"));
		seLoader.setController(seCtrl);
		Parent seRoot = seLoader.load();
		Scene seScene = new Scene(seRoot,600,600);
		
		stage.setScene(seScene);
		stage.setTitle("LockSmith");
		stage.show();
		
	}

}
