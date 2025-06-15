package controlador;

import modelo.Inventario;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;

/**
 * Clase ControladorTienda: esta clase sincroniza el paquete Modelo con la vista Tienda.fxml.
 * Permite la interacción del usuario con la tienda, realizando intercambios de leche por monedas
 * y de monedas por alimento, y actualizando la vista de acuerdo a las acciones realizadas.
 */
public class ControladorTienda {

	    /** Etiqueta que muestra la cantidad de leche en el inventario */
	    @FXML private Label lecheLabel; //Etiqueta del atributo "Leche"
	    
	    /** Etiqueta que muestra la cantidad de monedas en el inventario */
	    @FXML private Label monedasLabel; //Etiqueta del atributo "Monedas"
	    
	    /** Etiqueta que muestra la cantidad de comida en el inventario */
	    @FXML private Label alimentoLabel; //Etiqueta del atributo "Alimento" 
	    
	    /** Botón para cambiar leche por monedas */
	    @FXML private Button cambiarLechePorMonedas; //Botón para cambiar leche por monedas
	    
	    /** Botón para cambiar monedas por alimento */
	    @FXML private Button cambiarMonedaPorAlimento; //Botón para cambiar monedas por alimento
	    
	    /**
	     * Constructor vacío requerido por JavaFX para instanciar el controlador automáticamente. 
	     * Inicializa la instancia de la clase sin parámetros adicionales.
	     */
	    public ControladorTienda() {
	    	
	    }

	    private ControladorGranja controladorGranja;
	    /** Atributo tipo Inventario que contiene los datos del inventario */
	    private Inventario inventario; 
	    /** Atributo que almacena una función (Runnable) para actualizar la vista desde el exterior del controlador */
	    private Runnable actualizarVistaCallback; 

	    /**
	     * Método para inicializar los datos de la aplicación y actualizar las etiquetas de leche, comida y monedas.
	     * Este método es llamado para pasar los datos necesarios y actualizar la vista con la información actual del inventario.
	     * 
	     * @param inventario Instancia de la clase Inventario que contiene la información de las cantidades de leche, monedas y comida.
	     * @param actualizarVistaCallback Instancia de la clase Runnable que define la acción a ejecutar para actualizar la vista después de un cambio en el inventario.
	     * @param controladorGranja Instancia de ControladorGranja para poder acceder a las funcionalidades relacionadas con la granja.
	     */
	    public void inicializarDatos(Inventario inventario, Runnable actualizarVistaCallback, ControladorGranja controladorGranja) {
	        this.inventario = inventario;
	        this.actualizarVistaCallback = actualizarVistaCallback;
	        this.controladorGranja = controladorGranja;
	        actualizarEtiquetas();
	    }

	    /**
	     * Método para actualizar las etiquetas de leche, comida y monedas en la interfaz de usuario.
	     * Este método se encarga de refrescar la vista con la información más reciente del inventario.
	     */
	    public void actualizarEtiquetas() {
	        lecheLabel.setText("Leche: " + inventario.getCantidadLeche());
	        monedasLabel.setText("Monedas: " + inventario.getMonedas());
	        alimentoLabel.setText("Comida: " + inventario.getComida());
	    }

	    /**
	     * Método que cambia leche por monedas. 
	     * Realiza la conversión de 1 unidad de leche por 10 unidades de moneda. 
	     * No devuelve ningún valor, pero actualiza las etiquetas de la ventana y la vista general si corresponde.
	     * @see Inventario#cambiarLechePorMonedas()
	     */
	    @FXML
	    private void cambiarLechePorMonedas() {
	        inventario.cambiarLechePorMonedas();
	        actualizarEtiquetas();
	        if (actualizarVistaCallback != null) actualizarVistaCallback.run();
	        if (controladorGranja != null) controladorGranja.reproducirSonido("/cash.mp3");
	    }

	    /**
	     * Método que cambia monedas por alimento para la vaca. 
	     * Convierte 1 moneda en 10 unidades de alimento. 
	     * No devuelve ningún valor, pero actualiza las etiquetas de la ventana y la vista general si corresponde.
	     * @see Inventario#cambiarMonedaPorAlimento()
	     */
	    @FXML
	    private void cambiarMonedaPorAlimento() {
	        inventario.cambiarMonedaPorAlimento();
	        actualizarEtiquetas();
	        if (actualizarVistaCallback != null) actualizarVistaCallback.run();
	        if (controladorGranja != null) controladorGranja.reproducirSonido("/cash.mp3");
	        URL urlSonido = getClass().getResource("/resources_files/cash.mp3");
	    
	    } 
	    
	}