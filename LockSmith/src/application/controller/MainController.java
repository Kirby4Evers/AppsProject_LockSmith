
package application.controller;

import java.io.IOException;

import application.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Group Project : LockSmith Spring-2020
 * 
 * @authors Kyle Evers(RIY335) ; Sabita Paudyal Ghimire(agi486); Jonathan
 *          Villreal (zyj680); Pedro Jusino(ayt689) ; William G (Qoi678)
 *
 *          This class controls the activities related to LoginPage.fxml and the
 *          model logIn.java
 */
public class MainController {
	private Stage stage;
	private Model model;

	public MainController(Stage s) {
		this.stage = s; // used for switching scenes
		this.model = new Model();

	}

	@FXML
	private TextField inputField1;
	@FXML
	private TextField inputField2;

	/**
	 * This method is called when sign up button is pressed, it receives the
	 * information from Text-fields and pass them to method in Model.java
	 */
	@FXML
	public void handle2() {

		String username = inputField1.getText();
		String masterPassword = inputField2.getText();

		model.signUp(username, masterPassword);
	}

	/**
	 * This method is called when sign in button is pressed it receives the
	 * information from Text-fields and pass them to method in Model.java
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

			// do on exit
			// done here for access to password (storing pass as a class var is a potential
			// vulnerability)
			stage.setOnCloseRequest(event -> {
				System.out.println("Stage is closing");
				model.writeFile(password);
			});

			model.readFile(password);
			// calling to load the WebsiteUI.fxml page
			loadWebsites();

		}
		// if password is not match, dialog box will appear with an error message.
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Incorrect Password!!");
			alert.show();
		}

	}

	/**
	 * Creates the ShowEntries controller Scene, Loader and Parent. loads
	 * showEntries.fxml page
	 */
	@FXML
	public void loadWebsites() {

		try {
			ShowEntriesController seCtrl = new ShowEntriesController(model, stage);
			FXMLLoader seLoader = new FXMLLoader(getClass().getResource("../view/showEntries.fxml"));
			seLoader.setController(seCtrl);
			Parent seRoot = seLoader.load();
			Scene seScene = new Scene(seRoot, 600, 400);
			stage.setScene(seScene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
