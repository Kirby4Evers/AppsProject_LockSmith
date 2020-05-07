package application.controller;

import java.io.IOException;

import application.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MainController {
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
	

	// Creating object of model class
	Model model = new Model();
	//object of this class
	MainController mainController= new MainController();
	//object of Controller2 class
	Controller2 controller2= new Controller2();
	Stage primaryStage;
	

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
			mainController.loadWebsites();
			
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
		controller2.start(primaryStage);
		
		
	}

}
