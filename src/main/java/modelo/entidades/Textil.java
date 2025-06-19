package modelo.entidades;

public class Textil extends Producto {
    private String composicion;

    public Textil(int id, String codigo, String nombre, float precio, String composicion, String imagen) {
        super(id, codigo, nombre, precio, imagen);
        this.composicion = composicion;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    @Override
    public String toString() {
        return String.format("Nom: %s\nPreu: %.2f€\nComposicio: %s", nombre, precio, composicion);
    }
}
