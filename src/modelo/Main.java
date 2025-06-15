package modelo;
 
import java.net.URL;
import modelo.Inventario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;
 
/**
* Clase principal de la aplicación JavaFX.
*
* @version 1.0
* @author María Rodriguez, Antía Pardo, Mara Delgado
* @since 11-06-2025
*
*/
public class Main extends Application {
    
    /**
     * Método principal que se ejecuta al iniciar la aplicación JavaFX.
     *
     * Carga el archivo FXML que define la interfaz gráfica, crea la escena con los estilos CSS
     * y la muestra en el escenario principal.
     *
     * @param primaryStage El escenario principal donde se presenta la escena.
     */
	
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/vista.fxml"));
            AnchorPane root = loader.load();
 
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/vista/estilos.css").toExternalForm());
 
            primaryStage.setTitle("Granja");
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true); // Ventana maximizada al iniciar
            primaryStage.show();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Método main que lanza la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos (no usados).
     */
    public static void main(String[] args) {
        launch(args);
    }
}