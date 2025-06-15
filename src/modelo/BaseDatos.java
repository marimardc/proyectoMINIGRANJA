package modelo;
 
import java.sql.*;
 
/**
 * Clase BaseDatos, que gestiona las operaciones de conexión y manipulación de datos
 * en la base de datos MySQL.
 * Esta clase permite establecer una conexión con la base de datos, cargar y guardar
 * los datos del inventario, y cerrar la conexión cuando sea necesario.
 */
public class BaseDatos {
	
	/** URL de la base de datos MySQL. */
    private static final String URL = "jdbc:mysql://localhost:3306/granja"; // URL para conectarse a la base de datos
    
    /** Usuario para la conexión con la base de datos. */
    private static final String USER = "root"; // Usuario para conectarse a la base de datos
    
    /** Contraseña para la conexión con la base de datos. */
    private static final String PASSWORD = "Mariaroot89";// Contraseña para conectarse a la base de datos
 
    /**
     * Establece y devuelve una conexión a la base de datos utilizando los parámetros
     * definidos (URL, usuario y contraseña).
     * @return una {@link Connection} activa a la base de datos.
     * @throws SQLException si ocurre un error al intentar establecer la conexión.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
 
    
    /**
     * Este método establece una conexión con la base de datos, ejecuta una consulta SQL y
     * llena un objeto con los valores de leche, alimento y monedas obtenidos.
     * @return un objeto Inventario    
     */
    public Inventario cargarInventario() {
        Inventario inventario = new Inventario();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT leche, alimento, monedas FROM inventario WHERE id = 1");
             ResultSet resultSet = statement.executeQuery()) {
 
            if (resultSet.next()) {
                inventario.setLeche(resultSet.getInt("leche"));
                inventario.setComida(resultSet.getInt("alimento"));
                inventario.setMonedas(resultSet.getInt("monedas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventario;
    }
   
    /**
     * Guarda los datos del inventario en la base de datos, actualizando el registro
     * @param inventario Instancia de la clase Inventario
     */
    public void guardarInventario(Inventario inventario) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE inventario SET leche = ?, alimento = ?, monedas = ? WHERE id = 1")) {
 
            statement.setInt(1, inventario.getCantidadLeche());
            statement.setInt(2, inventario.getComida());
            statement.setInt(3, inventario.getMonedas());
 
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 
   
    /**
     * Método para cerrar la conexión a la base de datos (en caso de ser necesario)
     * @param connection , la conexión a cerrar;
     */
    public void cerrarConexion(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}