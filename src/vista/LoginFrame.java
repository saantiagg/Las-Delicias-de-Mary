package src.vista; // Paquete corregido

import javax.swing.*;

import src.vista.MainFrame; // Importación corregida
import src.conexion.ConexionBD; // Importación corregida

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    public LoginFrame() {
        // ... (resto del constructor)
        // [CÓDIGO OMITIDO POR BREVEDAD, ES IGUAL AL ORIGINAL, SOLO CAMBIA EL PACKAGE Y IMPORTS]
        setTitle("Inicio de Sesión - Las Delicias de Mary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("Sistema de Inventario", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // --- PANEL CENTRAL ---
        JPanel panelCentro = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        panelCentro.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panelCentro.add(txtUsuario);

        panelCentro.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        panelCentro.add(txtContrasena);

        add(panelCentro, BorderLayout.CENTER);

        // --- BOTONES ---
        JPanel panelBotones = new JPanel();
        JButton btnIngresar = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");

        panelBotones.add(btnIngresar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // --- EVENTO BOTÓN INGRESAR ---
        btnIngresar.addActionListener(e -> autenticarUsuario());
        btnCancelar.addActionListener(e -> System.exit(0));
    }

    private void autenticarUsuario() {
        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos.");
            return;
        }

        try (Connection conn = ConexionBD.getConexion()) {
            String sql = "SELECT * FROM usuario WHERE usuario = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String rol = rs.getString("rol");

                JOptionPane.showMessageDialog(this, 
                    "Bienvenido " + nombre + " (" + rol + ")",
                    "Acceso permitido", JOptionPane.INFORMATION_MESSAGE);

                dispose(); // cierra login
                new MainFrame().setVisible(true); // abre ventana principal
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}