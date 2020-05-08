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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	
	@FXML
    public void initialize() {
		fillEntries(); //happens after @FXML declarations
	}
	
	
	@FXML
	public void add() {
		
		Entry e = new Entry();
		model.getEntries().add(e);
		switchToEntry(e, true);
	}
	
	
	public void fillEntries() {
		
		
		for(Entry e: model.getEntries() )
		{
			//make labels
			Label web = new Label( e.getWebsite() );
			web.setFont(new Font("Arial", 30) );
			char webC0 = e.getWebsite().charAt(0);
			Label web0 = new Label( Character.toString(webC0) );
			web0.setFont(new Font("Arial", 35) );
			web0.setTextFill(Color.web("#FFFFFF"));
			
			//make img
			String imgPath = "file:///" +  System.getProperty("user.dir") + "\\src\\application\\view\\bar.png";
			Image image = new Image(imgPath);
			ImageView img = new ImageView();
			img.setImage(image);
			
			//set labels in AnchorPane
			double xOffset = 10;
			double yOffset = 7.5;
			AnchorPane anchor = new AnchorPane();
			AnchorPane.setTopAnchor(img, 0.0 + yOffset);
			AnchorPane.setLeftAnchor(img, 0.0 + xOffset);
			AnchorPane.setTopAnchor(web0, 7.0 + yOffset);
			AnchorPane.setLeftAnchor(web0,12.5 + xOffset);
			AnchorPane.setTopAnchor(web, 12.0 + yOffset);
			AnchorPane.setLeftAnchor(web, 75.0 + xOffset);
			
			anchor.getChildren().addAll(img, web0, web);
			
			HBox hbox = new HBox(anchor); //20 is spacing
			hbox.setAlignment( Pos.CENTER_LEFT );
			hbox.setOnMouseClicked( ev -> switchToEntry(e, false) );
			
			entriesBox.getChildren().add(hbox);
			
		}
	}

	public void switchToEntry(Entry e, Boolean temporary){
		try {
			
		ShowEntryInfoController eCtonroller = new ShowEntryInfoController( e, stage, model, temporary );//used to go back

		FXMLLoader eLoader = new FXMLLoader(getClass().getResource("../view/WebsiteUI.fxml"));
		eLoader.setController(eCtonroller);
		Parent eRoot = eLoader.load();
		Scene eScene = new Scene(eRoot,600,400);
		
		stage.setScene(eScene);
		stage.show();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}
