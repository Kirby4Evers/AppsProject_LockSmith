package application.controller;

import java.io.IOException;

import application.model.Entry;
import application.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ShowEntryInfo {
	private Entry entry;
	private Stage stage;
	private Model model;

	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private TextField websiteName;
	@FXML private TextField emailField;
	
	public ShowEntryInfo(Entry e, Stage s, Model model) { //stage and return scene to go back to previous view
		
		this.entry = e;
		this.stage = s;
		this.model = model;
		
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
			
			goBack();	
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
			try {
				
			
				FXMLLoader eLoader = new FXMLLoader(getClass().getResource("../view/showEntries.fxml"));
				ShowEntriesController retCtrl = new ShowEntriesController( model, stage);
				eLoader.setController( retCtrl );
				Parent eRoot = eLoader.load();
				Scene eScene = new Scene(eRoot,600,600);
				
				stage.setScene(eScene);
				stage.show();
				
				} catch (IOException e1) {
					System.out.println("Hah, caught one!");
					e1.printStackTrace();
				}
		}
}
