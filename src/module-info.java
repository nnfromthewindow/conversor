module ar.com.alura.conversor {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
