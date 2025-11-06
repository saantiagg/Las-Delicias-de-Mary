import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    public LoginFrame() {
        setTitle("Inicio de Sesión - Las Delicias de Mary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ======= TÍTULO =======
        JLabel lblTitulo = new JLabel("Sistema de Inventario", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // ======= PANEL CENTRAL =======
        JPanel panelCentro = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        panelCentro.add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        panelCentro.add(txtUsuario);

        panelCentro.add(new JLabel("Contraseña:"));
        txtContrasena = new JPasswordField();
        panelCentro.add(txtContrasena);

        add(panelCentro, BorderLayout.CENTER);

        // ======= PANEL DE BOTONES =======
        JPanel panelBotones = new JPanel();
        JButton btnIngresar = new JButton("Ingresar");
        JButton btnCancelar = new JButton("Cancelar");
        panelBotones.add(btnIngresar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // ======= ACCIONES =======
        btnIngresar.addActionListener(e -> validarLogin());
        btnCancelar.addActionListener(e -> System.exit(0));
    }

    private void validarLogin() {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        // 🔒 Validación temporal (más adelante se conectará a la BD)
        if (usuario.equals("admin") && contrasena.equals("1234")) {
            JOptionPane.showMessageDialog(this, "Bienvenido, " + usuario + "!");
            dispose(); // Cierra el login
            new MainFrame().setVisible(true); // Abre la ventana principal
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
