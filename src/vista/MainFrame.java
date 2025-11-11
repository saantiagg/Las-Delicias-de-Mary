package src.vista; // Paquete corregido

import javax.swing.*;

import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Sistema de Inventario - Las Delicias de Mary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // Agregamos los paneles
        tabs.addTab("Insumos", new InsumoPanel());
        tabs.addTab("Alertas", new AlertaPanel());

        add(tabs, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}