 package controlador;
 
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.URL;

import modelo.Animal;
import modelo.Inventario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
 
/**
* Controlador de la interfaz gráfica principal de la granja.
* Administra la interacción entre el usuario, la vista (FXML) y el modelo (Animal, Inventario).
* Permite alimentar una vaca, recolectar leche, intercambiar recursos y abrir la tienda.
*/
public class ControladorGranja {
 
    /** Barra de progreso que indica cuánto ha comido la vaca. */
    @FXML
    private ProgressBar barraAlimento;
 
    /** Botón para alimentar a la vaca. */
    @FXML
    private Button alimentarButton;
 
    /** Botón para recolectar leche una vez que la vaca ha sido alimentada. */
    @FXML
    private Button recolectarButton;
 
    /** Objeto que gestiona los recursos del jugador. */
    private Inventario inventario;
 
    /** Label para mostrar mensajes informativos al usuario. */
    @FXML
    private Label mensajeLabel;
 
    /** Label que muestra la cantidad de leche en el inventario. */
    @FXML
    private Label leche;
 
    /** Label que muestra la cantidad de comida disponible. */
    @FXML
    public Label comida;
 
    /** Label que muestra la cantidad de monedas disponibles. */
    @FXML
    private Label monedas;
 
    /** Instancia del animal que representa la vaca. */
    private Animal vaca;
    
    /**Reproductor de música de fondo*/
    private MediaPlayer musicaFondo;
    
    /**Atributo que indica si la música está activa o pausada*/
    private boolean musicaActiva = true;
    
    /**Botón para controlar la música de fondo*/
    @FXML
    private Button botonMusica;  // Debe coincidir con fx:id en el FXML
    
    /**
     * Inicializa y reproduce la música de fondo en un bucle infinito.
     * La música se carga desde el archivo /musica.mp3 en los recursos.
     * Si no se encuentra el archivo, se muestra un mensaje de error.
     */
    public void inicializarMusicaFondo() {
        try {

            URL url = getClass().getResource("/musica.mp3");        

            if (url != null) {
                Media media = new Media(url.toString());
                musicaFondo = new MediaPlayer(media);
                musicaFondo.setCycleCount(MediaPlayer.INDEFINITE); // Repetir infinitamente
                musicaFondo.setVolume(0.3); // Volumen al 30%
                if (musicaActiva) {
                    musicaFondo.play();
                }
            } else {
                System.err.println("¡Archivo de música no encontrado!");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar música: " + e.getMessage());
        }
    }

    /**
     * Alterna el estado de la música entre pausado y reproducción.
     * Actualiza el estado interno musicaActiva.
     */
    public void toggleMusicaFondo() {
        if (musicaFondo != null) {
            if (musicaActiva) {
                musicaFondo.pause();
            } else {
                musicaFondo.play();
            }
            musicaActiva = !musicaActiva;
        }
    }
    
    /**
     * Maneja el evento de clic en el botón de música.
     * Alterna el estado de reproducción y actualiza el ícono del botón.
     */
    @FXML
    private void toggleMusica() {
        toggleMusicaFondo();  // Llama al método que controla la música
        botonMusica.setText(musicaActiva ? "🔊" : "🔇");  // Cambia el ícono
    }
 
    /**
     * Inicializa la vista y el modelo al cargar la interfaz.
     * Crea el objeto vaca, inicializa el inventario, actualiza la interfaz y carga datos persistentes.
     * Configura el estado inicial de la música y la interfaz.
     */
    public void initialize() {
        vaca = new Animal("Vaca", "Leche");
        inventario = new Inventario();
        barraAlimento.setProgress(0);
        recolectarButton.setVisible(false);
        inventario.cargarDesdeBaseDeDatos();
        actualizarVista();
        inicializarMusicaFondo();
     // Añade esta línea para sincronizar el botón con el estado inicial:
        botonMusica.setText(musicaActiva ? "🔊" : "🔇");
    }
 
    /**
     * Cambia monedas por alimento usando el inventario.
     * Actualiza la vista tras realizar la operación.
     */
    @FXML
    public void cambiarMonedaPorAlimento() {
    	
        inventario.cambiarMonedaPorAlimento();
        actualizarVista();
        
        
    }
 
    /**
     * Cambia leche por monedas usando el inventario.
     * Actualiza la vista tras realizar la operación.
     */
    @FXML
    public void cambiarLechePorMonedas() {
    	 
        inventario.cambiarLechePorMonedas();
        actualizarVista();
        
        
    }
 
    /**
     * Actualiza las etiquetas de la vista con los valores actuales del inventario.
     */
    private void actualizarVista() {
        leche.setText("Leche: " + inventario.getCantidadLeche());
        comida.setText("Comida: " + inventario.getComida());
        monedas.setText("Monedas: " + inventario.getMonedas());
    }
 
    /**
     * Alimenta a la vaca si hay suficiente comida.
     * Actualiza la barra de progreso, muestra mensajes y guarda los cambios en la base de datos.
     * Si la vaca ya está llena, muestra un mensaje para recolectar leche.
     * Si no hay comida suficiente, muestra un mensaje de error.
     */
    @FXML
    private void alimentarVaca() {
        if (vaca.getCantidadAlimento() >= vaca.getCantidadMaximaAlimento()) {
            mensajeLabel.setText("La vaca ya ha comido suficiente. ¡Recolecta la leche!");
            mensajeLabel.setVisible(true);  // ← Mostrar el Label
            recolectarButton.setVisible(true);
            return;
        }
 
        if (inventario.getComida() > 0) {
            vaca.alimentar();
            inventario.setComida(inventario.getComida() - 1);
            double progreso = vaca.getCantidadAlimento() / vaca.getCantidadMaximaAlimento();
            barraAlimento.setProgress(progreso);
            comida.setText("Comida: " + inventario.getComida());
 
            if (vaca.getCantidadAlimento() == vaca.getCantidadMaximaAlimento()) {
                mensajeLabel.setText("¡La vaca está lista para ser ordeñada!");
                mensajeLabel.setVisible(true);
                recolectarButton.setVisible(true);
            } else {
            	mensajeLabel.setVisible(false); // ← Ocultar si no hay mensaje.
            	
            }
        } else {
            mensajeLabel.setText("¡Comida insuficiente!");
            mensajeLabel.setVisible(true); // ← Forzar visibilidad.
        }
 
        inventario.guardarEnBaseDeDatos();
    }
 

    /**
     * Recolecta leche de la vaca, actualiza el inventario,
     * reinicia el progreso de alimento y guarda los datos.
     * Reproduce un efecto de sonido al recolectar.
     */
    @FXML
    private void recolectarLeche() {
    	
    	inventario.incrementarLeche();
        mensajeLabel.setText("Leche recolectada.");
        inventario.guardarEnBaseDeDatos();
        vaca.reiniciarAlimento();
        barraAlimento.setProgress(0);
        recolectarButton.setVisible(false);
        actualizarVista();
    	
        reproducirSonido("/mu.mp3");
        
    }
 
    /**
     * Abre la ventana de la tienda.
     * Carga la vista Tienda.fxml, transfiere el inventario y actualiza la vista al volver.
     * @throws IOException Si ocurre un error al cargar el archivo FXML de la tienda
     */
    @FXML
    public void abrirTienda() throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Tienda.fxml"));
            Parent rootTienda = loader.load();
 
            ControladorTienda tiendaController = loader.getController();
            
            tiendaController.inicializarDatos(inventario, this::actualizarVista, this); // Pasa "this" (la instancia actual)
 
            Scene scene = new Scene(rootTienda);
            scene.getStylesheets().add(getClass().getResource("/vista/estilos.css").toExternalForm());
 
            Stage tiendaStage = new Stage();
            tiendaStage.setTitle("Tienda");
            tiendaStage.setScene(scene);
            tiendaStage.setMaximized(true);
            tiendaStage.show();
 
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar tienda.fxml: " + e.getMessage());
        }
    }
    
    /**
     * Reinicia completamente la partida.
     * Restablece todos los valores del inventario y el estado de la vaca.
     * Muestra un diálogo de confirmación antes de realizar el reinicio.
     */
    @FXML
    private void reiniciarPartida() {
        // 1. Mostrar confirmación (opcional)
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reiniciar partida");
        alert.setHeaderText("¿Estás seguro de que quieres reiniciar? Se perderán los datos actuales.");
        
        if (alert.showAndWait().orElse(ButtonType.CANCEL) != ButtonType.OK) {
            return; // Si el usuario cancela, no hacer nada
        }

        // 2. Reiniciar valores en el Inventario
        inventario.setLeche(0);
        inventario.setComida(10);  // Valor inicial como en tu constructor
        inventario.setMonedas(50);

        // 3. Reiniciar la vaca
        vaca.reiniciarAlimento();
        barraAlimento.setProgress(0);

        // 4. Guardar en la base de datos
        inventario.guardarEnBaseDeDatos();

        // 5. Actualizar la interfaz
        actualizarVista();
        mensajeLabel.setText("¡Partida reiniciada con éxito!");
        mensajeLabel.setVisible(true);
        recolectarButton.setVisible(false);
    }
    
    /**
     * Reproduce un efecto de sonido desde la ruta especificada.
     * @param rutaSonido Ruta relativa al archivo de sonido (ej: "/mu.mp3")
     */
    public void reproducirSonido(String rutaSonido) {
        try {
            URL url = getClass().getResource(rutaSonido);
            if (url != null) {
                Media sound = new Media(url.toString());
                MediaPlayer player = new MediaPlayer(sound);
                player.setOnError(() -> System.err.println("Error de audio: " + player.getError()));
                player.play();
            } else {
                System.err.println("¡Archivo de sonido no encontrado: " + rutaSonido);
            }
        } catch (Exception e) {
            System.err.println("Error al reproducir sonido: " + e.getMessage());
        }
    }
    
}