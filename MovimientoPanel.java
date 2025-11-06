import javax.swing.*;
import java.awt.*;

public class MovimientoPanel extends JPanel {

    public MovimientoPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Registro de Movimientos", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        JTextArea area = new JTextArea("Aquí se mostrarán los movimientos de inventario...");
        area.setEditable(false);
        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}
