package application.controller;

import java.io.File;

import application.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {
	@FXML
	private TextField inputField;
	@FXML
	private TextField inputField2;
	
	 
	/**
	 * called when sign up button is pressed
	 */
	public void handle1() {
		String username = inputField.getText(); 
		 String masterPassword = inputField2.getText();
		 Model model = new Model();
		 model.signUp(username, masterPassword);
	 }

}
