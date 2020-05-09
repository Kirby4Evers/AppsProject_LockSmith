package application.controller;

import java.io.IOException;

import application.model.Entry;
import application.model.Model;
import application.model.Security;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * Group Project : LockSmith- Spring-2020
 * 
 * @authors Kyle Evers(RIY335) ; Sabita Paudyal Ghimire(agi486); Jonathan
 *          Villreal (zyj680); Pedro Jusino(ayt689) ; William G (Qoi678)
 * 
 *
 *	This class handles, getting values from model and populates on view
 */
public class ShowEntryInfoController {
	private Entry entry;
	private Stage stage;
	private Model model;
	private Boolean temporary;
	// Text field variables
	@FXML private TextField usernameField;
	@FXML private TextField passwordField;
	@FXML private TextField websiteName;
	@FXML private TextField emailField;
	//Button variables
	@FXML private Button copyWebsite;
	@FXML private Button copyUsername;
	@FXML private Button copyPassword;
	@FXML private Button copyEmail;
	
	//Following methods help to copy texts on the UI's text fields to clipboard,
	//user can use them to paste anywhere later
	@FXML public void copyWeb() { 
		Security.copyToClipboard( websiteName.getText() ); 
	}
	@FXML public void copyUsr() { 
		Security.copyToClipboard( usernameField.getText() ); 
	}
	@FXML public void copyPass() { 
		Security.copyToClipboard( passwordField.getText() ); 
	}
	@FXML public void copyMail() { 
		Security.copyToClipboard( emailField.getText() ); 
	}
	//stage and return scene to go back to previous view
	
	public ShowEntryInfoController(Entry e, Stage s, Model model, Boolean tmp) { 
		this.entry = e;
		this.stage = s;
		this.model = model;
		this.temporary = tmp;
		
	}
	
	@FXML
    public void initialize() {    
		fill(); //put here for @fxml access
	}
	
	
		/**
		 * getting user input and saving into the files related to that user
		 */
		@FXML
		public void save() {
			
			if (websiteName.getText().equals("") | websiteName.getText().equals(" ") )
				entry.setWebsite("None");
			else
				entry.setWebsite(websiteName.getText() );
			
			if (emailField.getText().equals("") | emailField.getText().equals(" ") )
				entry.setEmail("None");
			else
				entry.setEmail(emailField.getText() );
			
			if (passwordField.getText().equals("") | passwordField.getText().equals(" ") )
				entry.setPassword("None");
			else
				entry.setPassword(passwordField.getText() );
			
			if (usernameField.getText().equals("") | usernameField.getText().equals(" ") )
				entry.setUsername("None");
			else
				entry.setUsername(usernameField.getText() );
			
			temporary = false;
			goBack();	
		}
		
		/**
		 * populating the UI with values obtained from model
		 */
		public void fill() {
			
			usernameField.setText( entry.getUsername() );
			passwordField.setText( entry.getPassword() );
			emailField.setText( entry.getEmail() );
			websiteName.setText( entry.getWebsite());
		}
		
		/**
		 * This method when called, loads up the previous (shoEntries.fxml) page
		 * 
		 */
		public void goBack() {
			try {
				
				if (temporary)
					model.getEntries().remove( entry );
			
				FXMLLoader eLoader = new FXMLLoader(getClass().getResource("../view/showEntries.fxml"));
				ShowEntriesController retCtrl = new ShowEntriesController( model, stage);
				eLoader.setController( retCtrl );
				Parent eRoot = eLoader.load();
				Scene eScene = new Scene(eRoot,600,400);
				
				stage.setScene(eScene);
				stage.show();
				
				} catch (IOException e1) {
					System.out.println("Hah, caught one!");
					e1.printStackTrace();
				}
		}
}