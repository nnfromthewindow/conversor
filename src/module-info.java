module ar.com.alura.conversor {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;
	requires java.net.http;
	requires org.json;
	
	
	opens application to javafx.graphics, javafx.fxml;
}
