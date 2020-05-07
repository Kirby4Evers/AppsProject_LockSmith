package application.controller;

import application.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ShowEntryInfo {
	private Model model;
	
	public ShowEntryInfo(Model m) {
		this.model = m;
	}
	
	@FXML
	private TextField user;
	@FXML
	private TextField password;
	@FXML
	private TextField website;
	@FXML
	private TextField email;
	
	
	//getting user input and saving into the files related to that user
		@FXML
		public void save() {
			String usr=user.getText();
			String pass=password.getText();
			String ema= email.getText();
			String web= website.getText();
			
			
		}
}
