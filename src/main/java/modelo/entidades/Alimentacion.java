package modelo.entidades;
import java.util.Date;

public class Alimentacion extends Producto {
    private Date caducidad;

    public Alimentacion(int id, String codigo, String nombre, float precio, Date caducidad, String imagen) {
        super(id, codigo, nombre, precio, imagen);
        this.caducidad = caducidad;
    }

    public String getCaducidad() {
        return caducidad.toString();
    }

    public void setCaducidad(Date caducidad) {
        this.caducidad = caducidad;
    }

    @Override
    public String toString() {
        return String.format("Codigo: %s\nNombre: %s\nPrecio: %.2f€\nCaducidad: %s", codigo, nombre, getPrecio(), caducidad);
    }
}
