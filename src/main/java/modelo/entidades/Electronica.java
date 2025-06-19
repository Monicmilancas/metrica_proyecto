package modelo.entidades;

public class Electronica extends Producto {

    private int garantia;

    public Electronica(int id, String codigo, String nombre, float precio, int garantia, String imagen) {
        super(id, codigo, nombre, precio, imagen);
        this.garantia = garantia;
    }

    /**
     * Obtiene el numero de dias de garantia del producto
     *
     * @return dias de garantia
     */
    public int getGarantia() {
        return garantia;
    }
    
    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    /**
     * Retorna una representacion en string de este objeto.
     *
     * @return string con la informacion del producto
     */
    @Override
    public String toString() {
        return String.format("Código:%s\nNombre: %s\nPrecio: %.2f€\nDias de garantía: %s", codigo, nombre, getPrecio(), garantia);
    }
}
