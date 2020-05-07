package application.controller;

import application.model.Entry;
import application.model.Model;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class showEntriesController {
	Model model;
	
	public showEntriesController(Model m)
	{
		this.model = m;
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
			hbox.setOnMouseClicked( ev -> handle(e) );
			
			entriesBox.getChildren().add(hbox);
			
		}
	}

	private Object handle(Entry e) {
		// TODO Auto-generated method stub
		return null;
	}
}
