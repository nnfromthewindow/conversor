package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FrameTemperatura implements Initializable {
	
//DECLARAMOS VARIABLES FXML PARA TRAER ELEMENTOS E INICIALIZARLOS
	
	@FXML
	private TextField input;
	@FXML
	private TextField result;
	@FXML
	private ComboBox<String> inputType;
	@FXML
	private ComboBox<String> resultType;
	@FXML 
	private Button convertBtn;
	@FXML 
	private Button principalBtn;
	
	//DECLARAMOS STAGES PARA PODER ABRIR OTRA PANTALLA
	private Stage stage;
	private Scene scene;
	
	//DECLARAMOS ARRAY CON TIPOS DE UNIDAD DE MEDIDA QUE VAN A POPULAR LAS LISTAS DE TIPO DE UNIDAD DE MEDIDA  
	String[] types= {"Celsius","Farenheit","Kelvin"};

	//FUNCION PARA VOLVER AL INICIO	
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
	
	//INICIALIZAMOS TODOS NUESTROS PROCESOS QUE DEBEN EJECUTARSE AL ABRIR LA VENTANA DE TEMPERATURA 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		input.setText("0.0");
		result.setText("32.0");
		inputType.getItems().addAll(types);
		inputType.getSelectionModel().selectFirst();
		resultType.getItems().addAll(types);
		resultType.getSelectionModel().select(1);
	
		

		//EVITAMOS TIPOS DE TEMPERATURA IGUALES EN AMBOS CAMPOS 
		inputType.setOnAction(e->{
			if(inputType.getValue()==resultType.getValue()) {
				resultType.setValue("Farenheit");
			}
			if(inputType.getValue()==resultType.getValue()) {
				resultType.setValue("Kelvin");
			}
			convertBtn.fire();
		});
		resultType.setOnAction(e->{
			if(inputType.getValue()==resultType.getValue()) {
				inputType.setValue("Farenheit");
			}
			if(inputType.getValue()==resultType.getValue()) {
				inputType.setValue("Kelvin");
			}
			convertBtn.fire();
		});

		//VALIDACION DE VALOR TEMPERATURA
		
		convertBtn.setOnAction(e->{
			if(input.getText().matches("([-]?\\d+\\W*[.]\\d)")){
				
		//COMENZAMOS LA LOGICA DE CONVERSION DE TEMPERATURAS
				
				//DECLARAMOS VARIABLES
				double tempIn = Double.parseDouble(input.getText()) ;
				double celFar = (tempIn*9)/5 + 32;
				double celKel = (double) (tempIn + 273.15);
				double farCel = (double) ((tempIn-32)*5/9);
				double farKel = (double) ((tempIn-32)*5/9) + 273.15;
				double kelCel = (double) (tempIn - 273.15);
				double kelFar = (double) ((tempIn- 273.15)*9/5) + 32;
				
				

				String typeIn = inputType.getValue();
				String typeOut = resultType.getValue();
				
				if(typeIn.contains("Celsius") && typeOut.contains("Farenheit")){
					BigDecimal resultOut = new BigDecimal(celFar).setScale(1,RoundingMode.HALF_DOWN);
					result.setText(resultOut.toString());
				}
				if(typeIn.contains("Celsius") && typeOut.contains("Kelvin")){
					BigDecimal resultOut = new BigDecimal(celKel).setScale(1,RoundingMode.HALF_DOWN);
					result.setText(resultOut.toString());
				}
				if(typeIn.contains("Farenheit") && typeOut.contains("Celsius")){
					BigDecimal resultOut = new BigDecimal(farCel).setScale(1,RoundingMode.HALF_DOWN);
					result.setText(resultOut.toString());
				}
				if(typeIn.contains("Farenheit") && typeOut.contains("Kelvin")){
					BigDecimal resultOut = new BigDecimal(farKel).setScale(1,RoundingMode.HALF_DOWN);
					result.setText(resultOut.toString());
				}
				if(typeIn.contains("Kelvin") && typeOut.contains("Celsius")){
					BigDecimal resultOut = new BigDecimal(kelCel).setScale(1,RoundingMode.HALF_DOWN);
					result.setText(resultOut.toString());
				}
				if(typeIn.contains("Kelvin") && typeOut.contains("Farenheit")){
					BigDecimal resultOut = new BigDecimal(kelFar).setScale(1,RoundingMode.HALF_DOWN);
					result.setText(resultOut.toString());
				}
				
			}else {
				ImageIcon icono = new ImageIcon("tempico.png");
				JOptionPane.showMessageDialog(null, "Debe ingresar solo numeros y terminar con un decimal (##.#)", "Intentelo de nuevo :(", JOptionPane.PLAIN_MESSAGE,icono);
				input.setText("");
				result.setText("");
			}
		});
		
		//ESCUCHAMOS LOS CAMBIOS EN EL INPUT DE TEMPERATURA PARA EXCLUIR LAS LETRAS Y FACILITAR LA VALIDACION
		
		input.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) throws StringIndexOutOfBoundsException {
		        try {
		  
					if (!newValue.matches("([-]?\\d+\\W*[.]\\d)")) {
					    input.setText(newValue.replaceAll("[^[.]\\d*-]", ""));
					}
				
				} catch (Exception e) {

					e.printStackTrace();
				}
		    }
		});
	}
}
