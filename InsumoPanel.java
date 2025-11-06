import javax.swing.*;
import java.awt.*;

public class InsumoPanel extends JPanel {

    public InsumoPanel() {
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Gestión de Insumos", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Cantidad Actual", "Cantidad Mínima"};
        Object[][] datos = {
            {"1", "Arroz", "50", "10"},
            {"2", "Aceite", "20", "5"},
            {"3", "Sal", "15", "3"}
        };

        JTable tabla = new JTable(datos, columnas);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel botones = new JPanel();
        botones.add(new JButton("Agregar"));
        botones.add(new JButton("Editar"));
        botones.add(new JButton("Eliminar"));
        add(botones, BorderLayout.SOUTH);
    }
}
