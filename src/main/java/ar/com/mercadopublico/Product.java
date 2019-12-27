package ar.com.mercadopublico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {

    private String name;
    private String unidad;
    private String cantidad;
    private String categoria;
    private String descripcion;
    private List<Offer> offers;

    Product() {
        offers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "Product [" +
                "name=" + name +
                ", unidad=" + unidad +
                ", cantidad=" + cantidad +
                ", categoria=" + categoria +
                ", descripcion=" + descripcion +
                ", offers=" + Arrays.toString(offers.toArray()) +
                ']';
    }
}
