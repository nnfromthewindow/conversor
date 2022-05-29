package application;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SceneController{
	
	@FXML
	private Label labelInit;
	@FXML
	private Button monedaBtn;
	@FXML
	private Button tempBtn;
	@FXML
	private Button principalBtn;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	 

 	public void switchToDivisas(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("Moneda.fxml"));
	  root.setId("moneda");
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	  stage.setTitle("Conversor de Divisas");
	  stage.setScene(scene);
	  stage.setResizable(false);
	  stage.show();
	
	 };
	 
	 public void switchToTemp(ActionEvent event) throws IOException {
		
	  Parent root = FXMLLoader.load(getClass().getResource("Temperatura.fxml"));
	  root.setId("temperatura");
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	  stage.setTitle("Conversor de Temperatura");
	  stage.setScene(scene);
	  stage.setResizable(false);
	  stage.show();

	  };
	 
	  public void switchToHome(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));
		  root.setId("intro");
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		  stage.setTitle("Conversor de Divisas y Temperatura");
		  stage.setScene(scene);
		  stage.setResizable(false);
		  stage.show();

	 }


}