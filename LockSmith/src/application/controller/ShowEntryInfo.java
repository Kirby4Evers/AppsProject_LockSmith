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
 * Group Project : LockSmith Spring-2020
 * 
 * @authors Kyle Evers(RIY335) ; Sabita Paudyal Ghimire(agi486); Jonathan
 *          Villreal (zyj680); Pedro Jusino(ayt689) ; William G (Qoi678)
 *          
 *          
 *
 */
public class ShowEntryInfo {
	private Entry entry;
	private Stage stage;
	private Model model;
// List of variables for Text-Fields
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private TextField websiteName;
	@FXML
	private TextField emailField;
//List of variables for copy buttons
	@FXML
	private Button copyWebsite;
	@FXML
	private Button copyUsername;
	@FXML
	private Button copyPassword;
	@FXML
	private Button copyEmail;

	//Following methods will copy the info into clipboard for user to paste later
	@FXML
	public void copyWeb() {
		Security.copyToClipboard(websiteName.getText());
	}

	@FXML
	public void copyUsr() {
		Security.copyToClipboard(usernameField.getText());
	}

	@FXML
	public void copyPass() {
		Security.copyToClipboard(passwordField.getText());
	}

	@FXML
	public void copyMail() {
		Security.copyToClipboard(emailField.getText());
	}
	// stage and return scene to go back to previous view
	public ShowEntryInfo(Entry e, Stage s, Model model) { 

		this.entry = e;
		this.stage = s;
		this.model = model;

	}

	@FXML
	public void initialize() {
		fill(); // put here for @fxml access
	}

	// getting user input and saving into the files related to that user
	@FXML
	public void save() {
		entry.setWebsite(websiteName.getText());
		entry.setEmail(emailField.getText());
		entry.setPassword(passwordField.getText());
		entry.setUsername(usernameField.getText());

		goBack();
	}

	/**
	 * getting the values from Arraylist and Populating the window with that information 
	 */
	public void fill() {
		System.out.println(entry.getUsername());
		System.out.println(usernameField.getText());

		usernameField.setText(entry.getUsername());
		passwordField.setText(entry.getPassword());
		emailField.setText(entry.getEmail());
		websiteName.setText(entry.getWebsite());
	}

	/**
	 * It takes back to previous screen
	 */
	public void goBack() {
		try {

			FXMLLoader eLoader = new FXMLLoader(getClass().getResource("../view/showEntries.fxml"));
			ShowEntriesController retCtrl = new ShowEntriesController(model, stage);
			eLoader.setController(retCtrl);
			Parent eRoot = eLoader.load();
			Scene eScene = new Scene(eRoot, 600, 400);

			stage.setScene(eScene);
			stage.show();

		} catch (IOException e1) {
			System.out.println("Hah, caught one!");
			e1.printStackTrace();
		}
	}
}
