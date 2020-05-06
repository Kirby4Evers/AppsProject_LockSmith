package application.controller;

import java.io.File;
import java.io.IOException;

import application.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {
	@FXML
	private TextField inputField;
	@FXML
	private TextField inputField2;
	
	//Creating object of model class
	 Model model = new Model();

	
	 
	/**
	 * called when sign up button is pressed
	 */
	public void handle1() {
		String username = inputField.getText(); 
		 String masterPassword = inputField2.getText();
		 model.signUp(username, masterPassword);
	 }
	/**
	 * called when sign in button is pressed
	 * @throws IOException 
	 */
	public void handle2() throws IOException {
		String username = inputField.getText(); 
		 String password = inputField2.getText();
		 boolean isMatch=model.login(username, password);
		 
		 // if match, should go to next page.
		 
		 // TODO: if (isMatch)go to next page
		 
	}

}
