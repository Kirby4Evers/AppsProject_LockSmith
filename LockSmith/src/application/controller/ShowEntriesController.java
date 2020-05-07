package application.controller;

import java.io.IOException;

import application.Main;
import application.model.Entry;
import application.model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ShowEntriesController {
	Model model;
	Stage stage;
	
	public ShowEntriesController(Model m, Stage s)
	{
		this.model = m;
		this.stage = s;
	}
	
	@FXML VBox entriesBox;
	public void fillEntries() {
		
		
		for(Entry e: model.getEntries() )
		{
			Label web = new Label( e.getWebsite() );
			web.setFont(new Font("Arial", 30) );
			Label usr = new Label( e.getUsername() );
			usr.setFont(new Font("Arial", 30) );
			
			HBox hbox = new HBox(20, web, usr); //20 is spacing
			hbox.setPadding(new Insets(20, 0, 0, 0));
			hbox.setAlignment( Pos.CENTER_LEFT );
			hbox.setOnMouseClicked( ev -> switchToEntry(e) );
			
			entriesBox.getChildren().add(hbox);
			
		}
	}

	public void switchToEntry(Entry e){
		try {
		ShowEntryInfo eCtonroller = new ShowEntryInfo();

		FXMLLoader eLoader = new FXMLLoader(getClass().getResource("src/application/view/WebsiteUI.fxml"));
		eLoader.setController(eCtonroller);
		Parent eRoot = eLoader.load();
		Scene eScene = new Scene(eRoot,600,600);
		
		stage.setScene(eScene);
		stage.setTitle("LockSmith");
		stage.show();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}
