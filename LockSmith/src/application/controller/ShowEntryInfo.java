package application.controller;

import application.model.Entry;
import application.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ShowEntryInfo {
	private Entry entry;
	
	public ShowEntryInfo(Entry e) {
		this.entry = e;
		fill();
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
			entry.setWebsite(website.getText() );
			entry.setEmail(email.getText() );
			entry.setPassword(password.getText() );
			entry.setUsername(user.getText() );
			
		}
		
		public void fill() {
			user.setText( entry.getUsername() );
			password.setText( entry.getPassword() );
			email.setText( entry.getEmail() );
			website.setText( entry.getWebsite());
		}
}
