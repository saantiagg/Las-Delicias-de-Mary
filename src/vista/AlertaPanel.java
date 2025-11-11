package src.vista; // Paquete corregido

import javax.swing.*;
import java.awt.*;

public class AlertaPanel extends JPanel {

    public AlertaPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Alertas de Stock", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JTextArea area = new JTextArea("No hay alertas por ahora. (Implementar lógica de umbral mínimo)");
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}