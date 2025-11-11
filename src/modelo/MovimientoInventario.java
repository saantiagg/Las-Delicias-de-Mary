package src.modelo; // Paquete corregido

import src.conexion.ConexionBD; // Importación corregida
import java.sql.*;
import java.time.LocalDate;

public class MovimientoInventario {

    public static void registrarMovimiento(int idTipoMovimiento, int idInsumo, int cantidad, String observacion) {
        String insertarMovimiento = "INSERT INTO movimientoinventario (idtipomovimiento, fecha, observacion) VALUES (?, ?, ?)";
        String insertarDetalle = "INSERT INTO detallemovimientoinventario (idmovimiento, idinsumo, cantidad) VALUES (?, ?, ?)";
        String actualizarStock = "UPDATE insumo SET cantidadactual = cantidadactual + (? * (SELECT signo FROM tipomovimiento WHERE idtipomovimiento = ?)) WHERE idinsumo = ?";

        try (Connection conn = ConexionBD.getConexion()) {
            if (conn == null) throw new SQLException("No se pudo establecer la conexión a la base de datos.");
            
            conn.setAutoCommit(false);

            PreparedStatement ps1 = conn.prepareStatement(insertarMovimiento, PreparedStatement.RETURN_GENERATED_KEYS);
            ps1.setInt(1, idTipoMovimiento);
            ps1.setDate(2, Date.valueOf(LocalDate.now()));
            ps1.setString(3, observacion);
            ps1.executeUpdate();

            ResultSet rs = ps1.getGeneratedKeys();
            int idMovimiento = 0;
            if (rs.next()) idMovimiento = rs.getInt(1);

            PreparedStatement ps2 = conn.prepareStatement(insertarDetalle);
            ps2.setInt(1, idMovimiento);
            ps2.setInt(2, idInsumo);
            ps2.setInt(3, cantidad);
            ps2.executeUpdate();

            PreparedStatement ps3 = conn.prepareStatement(actualizarStock);
            ps3.setInt(1, cantidad);
            ps3.setInt(2, idTipoMovimiento);
            ps3.setInt(3, idInsumo);
            ps3.executeUpdate();

            conn.commit();
            System.out.println("✅ Movimiento registrado correctamente en BD.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al registrar movimiento: " + e.getMessage());
        }
    }
}