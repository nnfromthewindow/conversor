package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller implements Initializable {

	@FXML
	private Label labelInit;
	
	@FXML
	private ComboBox<String> comboBox;
	
	private String[] conversor = {"Moneda","Temperatura"};

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		comboBox.getItems().addAll(conversor);
		
//		ActionListener comboBoxActionListener = new ActionListener();
		
		}
//-----------------------------------------------------------
	//Control de Pantallas
//-----------------------------------------------------------	
	 private Stage stage;
	 private Scene scene;
	 private Parent root;
	 
	 public void switchToMoneda(ActionEvent event) throws IOException {
	  root = FXMLLoader.load(getClass().getResource("Moneda.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }
	 
	 public void switchToTemp(ActionEvent event) throws IOException {
	  Parent root = FXMLLoader.load(getClass().getResource("Temperatura.fxml"));
	  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
	  scene = new Scene(root);
	  stage.setScene(scene);
	  stage.show();
	 }
	  public void switchToHome(ActionEvent event) throws IOException {
		  Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));
		  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		  scene = new Scene(root);
		  stage.setScene(scene);
		  stage.show();
	 }
	 


}

	
	


