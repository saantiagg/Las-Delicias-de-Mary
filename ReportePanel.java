import javax.swing.*;
import java.awt.*;

public class ReportePanel extends JPanel {

    public ReportePanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Reportes de Inventario", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JButton generar = new JButton("Generar Reporte");
        add(generar, BorderLayout.CENTER);
    }
}
