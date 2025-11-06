
import java.math.BigDecimal;

public class Insumo {
    private int id;
    private String nombre;
    private String unidad;
    private BigDecimal cantidadActual;
    private BigDecimal cantidadMinima;
    private BigDecimal precioUnitario;

    public Insumo() {}

    public Insumo(int id, String nombre, String unidad, BigDecimal cantidadActual, BigDecimal cantidadMinima, BigDecimal precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.cantidadActual = cantidadActual;
        this.cantidadMinima = cantidadMinima;
        this.precioUnitario = precioUnitario;
    }

    // getters / setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public BigDecimal getCantidadActual() { return cantidadActual; }
    public void setCantidadActual(BigDecimal cantidadActual) { this.cantidadActual = cantidadActual; }
    public BigDecimal getCantidadMinima() { return cantidadMinima; }
    public void setCantidadMinima(BigDecimal cantidadMinima) { this.cantidadMinima = cantidadMinima; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - actual: %s, mínimo: %s", id, nombre, unidad, cantidadActual, cantidadMinima);
    }
}
