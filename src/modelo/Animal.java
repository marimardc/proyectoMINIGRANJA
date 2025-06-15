package modelo;
 
/**
* Representa un animal de granja como una vaca
* el animal puede ser alimentado, y al alcanzar cierta cantidad de alimento,
* produce un producto como leche.
*/
public class Animal {
    
    /** Tipo de animal. */
    private String tipo;
    
    /** Indica si el animal ha sido alimentado completamente. */
    private boolean alimentado;
    
    /** Producto que genera el animal */
    private String producto;
    
    /** Indica si el animal tiene producto listo para recolectar. */
    private boolean tieneProducto;
 
    /** Cantidad de alimento que ha recibido el animal. */
    private double cantidadAlimento;
    
    /** Cantidad máxima de alimento que puede recibir el animal. */
    private final double cantidadMaximaAlimento = 10;
 
    /**
     * Constructor de la clase Animal.
     * @param producto El producto que genera el animal en este caso leche.
     */
    public Animal(String tipo, String producto) {
        this.tipo = tipo;
        this.producto = producto;
        this.cantidadAlimento = 0;
        this.alimentado = false;
        this.tieneProducto = false;
    }
 
    /**
     * Alimenta al animal incrementando su cantidad de alimento en una unidad.
     * Si alcanza la cantidad máxima de alimento, se marca como alimentado y con producto disponible.
     */
    public void alimentar() {
        this.cantidadAlimento += 1;
        if (this.cantidadAlimento >= cantidadMaximaAlimento) {
            this.alimentado = true;
            this.tieneProducto = true;
        }
    }
 
    /**
     * Obtiene el tipo de animal.
     * @return El tipo del animal.
     */
    public String getTipo() {
        return tipo;
    }
 
    /**
     * Indica si el animal está completamente alimentado.
     * @return true si está alimentado, false en caso contrario.
     */
    public boolean isAlimentado() {
        return alimentado;
    }
 
    /**
     * Obtiene el producto que genera el animal.
     * @return El producto del animal.
     */
    public String getProducto() {
        return producto;
    }
 
    /**
     * Indica si el animal tiene producto listo para recolectar.
     * @return true si tiene producto, false en caso contrario.
     */
    public boolean tieneProducto() {
        return tieneProducto;
    }
 
    /**
     * Obtiene la cantidad actual de alimento que ha recibido el animal.
     * @return Cantidad de alimento actual.
     */
    public double getCantidadAlimento() {
        return cantidadAlimento;
    }
 
    /**
     * Obtiene la cantidad máxima de alimento que puede recibir el animal.
     * @return Cantidad máxima de alimento.
     */
    public double getCantidadMaximaAlimento() {
        return cantidadMaximaAlimento;
    }
 
    /**
     * Reinicia la cantidad de alimento del animal a 0.
     */
    public void reiniciarAlimento() {
        cantidadAlimento = 0;
    }
}