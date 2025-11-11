package src.modelo;

import src.conexion.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class InsumoDAO {
    
    // CORRECCIÓN: Usamos nombres de columna exactos: unidad_medida, cantidadactual, cantidadminima, preciounitario
    public List<Insumo> obtenerTodos() {
        List<Insumo> insumos = new ArrayList<>();
        String sql = "SELECT idinsumo, nombre, unidad_medida, cantidadactual, cantidadminima, preciounitario FROM insumo"; 
        
        try (Connection conn = ConexionBD.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Insumo insumo = new Insumo(
                    rs.getInt("idinsumo"),
                    rs.getString("nombre"),
                    rs.getString("unidad_medida"),   
                    rs.getBigDecimal("cantidadactual"), 
                    rs.getBigDecimal("cantidadminima"), 
                    rs.getBigDecimal("preciounitario")
                );
                insumos.add(insumo);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Dejar el printStackTrace es vital para depurar
            System.err.println("❌ Error al obtener insumos: " + e.getMessage());
        }
        return insumos;
    }

    // Método para agregar un nuevo insumo (CORREGIDO)
    public boolean agregar(Insumo insumo) {
        // CORRECCIÓN: Usamos nombres de columna exactos
        String sql = "INSERT INTO insumo (nombre, unidad_medida, cantidadactual, cantidadminima, preciounitario) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, insumo.getNombre());
            ps.setString(2, insumo.getUnidad()); 
            ps.setBigDecimal(3, insumo.getCantidadActual()); 
            ps.setBigDecimal(4, insumo.getCantidadMinima());
            ps.setBigDecimal(5, insumo.getPrecioUnitario());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace(); 
            System.err.println("❌ Error al agregar insumo: " + e.getMessage());
            return false;
        }
    }
    
    // Método para eliminar un insumo
    public boolean eliminar(int id) {
        String sql = "DELETE FROM insumo WHERE idinsumo = ?";
        try (Connection conn = ConexionBD.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("❌ Error al eliminar insumo. Asegúrese que no tenga movimientos o detalles asociados: " + e.getMessage());
            return false;
        }
    }
}