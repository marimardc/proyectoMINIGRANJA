package modelo;

import controlador.ControladorGranja;

/**
 * Clase Inventario: representa los recursos que posee el usuario en la tienda.
 * Gestiona las cantidades de leche, comida y monedas, y permite operaciones de intercambio.
 * También se encarga de sincronizar los datos con la base de datos.
 */
public class Inventario {

	/** Cantidad de leche en el inventario. */
    private int leche; //Atributo de la cantidad de leche
    
    /** Cantidad de comida en el inventario. */
    private int comida; //Atributo de la cantidad de comida
    
    /** Cantidad de monedas en el inventario. */
    private int monedas; //Atributo de la cantidad de monedas

    /** Objeto que gestiona el acceso a la base de datos. */
    private BaseDatos baseDatos; //Objeto para gestionar el acceso a la base de datos
    
    /**
     * Constructor de la clase
     * Inicializa los valores del inventario y crea una instancia de la base de datos.
     * El inventario comienza con 50 monedas y 0 unidades de leche y comida.
     */
    public Inventario() {
    	this.comida=0;
    	this.leche = 0;
        this.monedas=50;
        this.baseDatos = new BaseDatos();
    
    }

    /**
     * Carga los datos del inventario desde la base de datos.
     * Sobrescribe los valores actuales con los que se encuentran almacenados.
     */
    public void cargarDesdeBaseDeDatos() {
        Inventario inventarioDB = baseDatos.cargarInventario();
        this.leche = inventarioDB.getCantidadLeche();
        this.comida = inventarioDB.getComida();
        this.monedas = inventarioDB.getMonedas();
    }

    /**
     * Guarda el estado actual del inventario en la base de datos.
     */
    public void guardarEnBaseDeDatos() {
        baseDatos.guardarInventario(this);
    }

    /**
     * Incrementa la cantidad de leche en 1 unidad.
     */
    public void incrementarLeche() {
        leche++;
      
    }

    /**
     * Devuelve la cantidad actual de leche en el inventario.
     * @return número de unidades de leche disponibles.
     */
    public int getCantidadLeche() {
        return leche;
    }

    /**
    * Devuelve la cantidad actual de monedas en el inventario.
    * @return número de monedas disponibles.
    */	
    public int getMonedas() {
        return monedas;
    }

    /**
     * Devuelve la cantidad actual de comida en el inventario.
     * @return número de unidades de comida disponibles.
     */
    public int getComida() {
        return comida;
    }

    /**
     * Intercambia 1 unidad de leche por 10 monedas, si hay al menos 1 unidad de leche.
     * Luego guarda los cambios en la base de datos.
     */
    public void cambiarLechePorMonedas() {
        if (leche >= 1) {
            leche--;
            monedas += 10; 
            
        }
        
        guardarEnBaseDeDatos();
    }

    /**
     * Intercambia 1 moneda por 10 unidades de comida, si hay al menos 1 moneda.
     * Luego guarda los cambios en la base de datos.
     */
    public void cambiarMonedaPorAlimento() {
        if (monedas >= 1) {
            monedas--;
            comida += 10;
      
        }

        guardarEnBaseDeDatos();
    }

    /**
     * Establece la cantidad de leche.
     * @param leche nueva cantidad de leche.
     */
    public void setLeche(int leche) {
        this.leche = leche;
    }

    /**
     * Establece la cantidad de comida.
     * @param comida nueva cantidad de comida.
     */
    public void setComida(int comida) {
        this.comida = comida;
    }

    /**
     * Establece la cantidad de monedas.
     * @param monedas nueva cantidad de monedas.
     */
    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }
}
