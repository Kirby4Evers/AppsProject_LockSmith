package application.controller;

import application.model.Entry;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ShowEntryInfo {
	private Entry entry;
	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private TextField websiteName;
	@FXML private TextField emailField;
	
	public ShowEntryInfo(Entry e) {
		
		this.entry = e;
		
	}
	
	@FXML
    public void initialize() {    
		fill(); //put here for @fxml access
	}
	
	
	//getting user input and saving into the files related to that user
		@FXML
		public void save() {
			entry.setWebsite(websiteName.getText() );
			entry.setEmail(emailField.getText() );
			entry.setPassword(passwordField.getText() );
			entry.setUsername(usernameField.getText() );
			
		}
		
		public void fill() {
			System.out.println(entry.getUsername());
			System.out.println( usernameField.getText() );
			
			usernameField.setText( entry.getUsername() );
			passwordField.setText( entry.getPassword() );
			emailField.setText( entry.getEmail() );
			websiteName.setText( entry.getWebsite());
		}
		
		public void goBack() {
			
		}
}
