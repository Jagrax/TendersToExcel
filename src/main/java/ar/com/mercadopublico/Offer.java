package ar.com.mercadopublico;

public class Offer {

    private String id;
    private String proveedor;
    private String nombreDeLaOferta;
    private String especificacionesDelProveedor;
    private String cantidadOfertada;
    private String precioUnitario;
    private String moneda;
    private String monto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getNombreDeLaOferta() {
        return nombreDeLaOferta;
    }

    public void setNombreDeLaOferta(String nombreDeLaOferta) {
        this.nombreDeLaOferta = nombreDeLaOferta;
    }

    public String getEspecificacionesDelProveedor() {
        return especificacionesDelProveedor;
    }

    public void setEspecificacionesDelProveedor(String especificacionesDelProveedor) {
        this.especificacionesDelProveedor = especificacionesDelProveedor;
    }

    public String getCantidadOfertada() {
        return cantidadOfertada;
    }

    public void setCantidadOfertada(String cantidadOfertada) {
        this.cantidadOfertada = cantidadOfertada;
    }

    public String getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Offer [" +
                "id=" + id +
                ", proveedor=" + proveedor +
                ", nombreDeLaOferta=" + nombreDeLaOferta +
                ", especificacionesDelProveedor=" + especificacionesDelProveedor +
                ", cantidadOfertada=" + cantidadOfertada +
                ", precioUnitario=" + precioUnitario +
                ", moneda=" + moneda +
                ", monto=" + monto +
                ']';
    }
}
