package modelo.entidades;

import javax.swing.*;
import java.awt.*;

public abstract class Producto {
    protected int id;
    protected String codigo;
    protected String nombre;
    protected float precio;
    protected String rutaImagen;
    protected JLabel cantidad;

    public Producto(int id, String codigo, String nombre, float precio, String imagen) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.rutaImagen = imagen;
        this.cantidad = new JLabel("0");
        this.cantidad.setFont(new Font("Arial", Font.BOLD, 16));
    }

    /**
     * Devuelve el codigo del producto
     *
     * @return El codigo como un string de dos letras mayusculas y 4 numeros "TX0000"
     */
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

}
