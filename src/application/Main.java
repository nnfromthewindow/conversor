package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) 	{
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Conversor de Divisas y Temperatura");
			primaryStage.setResizable(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	};
	 
	
	public static void main(String[] args) {
		launch(args);
	
	}
}
