package application;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.HashMap;
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

public class DivisasController implements Initializable {

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
	
		//DECLARAMOS ARRAY CON TIPOS DE CAMBIO QUE VAN A POPULAR LAS LISTAS DE TIPO DE CAMBIO 
		  HashMap<String, String> exchanges = new HashMap<String, String>();
		
		//DECLARAMOS VARIABLE PARA TRAER EL RESULTADO DEL CAMBIO
		  double resultConnection;

		//FUNCION PARA VOLVER AL INICIO	
		 public void switchToHome(ActionEvent event) throws IOException {
			  Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));
			  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			  scene = new Scene(root);
			  scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			  stage.setTitle("Conversor de Divisas y Temperatura");
			  stage.setScene(scene);
			  stage.setResizable(false);
			  stage.show();

		 }

//INICIALIZAMOS TODOS NUESTROS PROCESOS QUE DEBEN EJECUTARSE AL ABRIR LA VENTANA DE CONVERSION DE DIVISAS 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		

		exchanges.put("Dólar", "USD");
		exchanges.put("Pesos", "ARS");
		exchanges.put("Euro", "EUR");
		exchanges.put("Libras", "GBP");
		exchanges.put("Yen", "JPY");
		exchanges.put("Won Coreano", "KRW");
		exchanges.put("Real", "BRL");

		ConnectionExchanges conInit = new ConnectionExchanges();
		input.setText("1");
		result.setText(conInit.convertirDivisa("ARS", "USD", 1));
		inputType.getItems().addAll(exchanges.keySet());
		inputType.getSelectionModel().select(2);
		resultType.getItems().addAll(exchanges.keySet());
		resultType.getSelectionModel().select(0);
	
		

		//EVITAMOS TIPOS DE CAMBIO IGUALES EN AMBOS CAMPOS 
		inputType.setOnAction(e->{
			if(inputType.getValue()==resultType.getValue()) {
				resultType.setValue("Euro");
			}
			if(inputType.getValue()==resultType.getValue()) {
				resultType.setValue("Dólar");
			}
			convertBtn.fire();
		});
		resultType.setOnAction(e->{
			if(inputType.getValue()==resultType.getValue()) {
				inputType.setValue("Euro");
			}
			if(inputType.getValue()==resultType.getValue()) {
				inputType.setValue("Dólar");
			}
			convertBtn.fire();
		});
		
		//VALIDACION DE VALOR DE LA DIVISA
		
		convertBtn.setOnAction(e->{
			if(input.getText().matches("(([\\d*][.]?))+(\\d*)")){
				
		//COMENZAMOS LA LOGICA DE CONVERSION DE DIVISAS
				
				//DECLARAMOS VARIABLES
				double amount = Double.parseDouble(input.getText()); 
				String typeIn = exchanges.get(inputType.getValue());
				String typeOut = exchanges.get(resultType.getValue());
				ConnectionExchanges conExchange = new ConnectionExchanges(typeIn, typeOut, amount);
				result.setText(conExchange.convertirDivisa(typeIn, typeOut, amount));
				
				//RETRASAMOS UNOS SEGUNDOS EL POPUP DEL ALERTA Y VALIDAMOS QUE NO HAYA FOCO EN LOS COMBOBOX
				if(!inputType.isFocused() && !resultType.isFocused()) {
					
					Timer timer = new Timer();
					
					TimerTask task = new TimerTask() {
						
						
						@Override
						public void run() {
						
							ImageIcon icono = new ImageIcon("exchangeico.png");
					        int resp =JOptionPane.showConfirmDialog(null, "¿Desea continuar?",
					                "Conversor de Divisas", JOptionPane.YES_NO_CANCEL_OPTION,
					                JOptionPane.INFORMATION_MESSAGE, icono);
					 
					        String mensaje = "";
					        switch (resp) {
					        case 0:
					            mensaje = "Continuemos!!";
					            break;
					        case 1:
					            mensaje = "Programa termindo";
					           
					            break;
					        case 2:
					            mensaje = "Programa termindo";
					            
					        }
					        JOptionPane.showMessageDialog(null, mensaje);
					        
					        timer.cancel();
					        
					        if(resp==1 ||resp==2) {
							   System.exit(0);
							}
						}		
					};
					timer.schedule(task, 500);
				}
				
				
				
			//MENSAJE DE ERROR DE VALIDACION	
			}else {
				JOptionPane.showMessageDialog(null, "Debe ingresar solo numeros y terminar con un decimal (##.#)", "Intentelo de nuevo :(", JOptionPane.PLAIN_MESSAGE);
				input.setText("");
				result.setText("");
			}
			
			
		});
		
		//ESCUCHAMOS LOS CAMBIOS EN EL INPUT DE TIPO DE CAMBIO PARA EXCLUIR LAS LETRAS Y FACILITAR LA VALIDACION
		
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
