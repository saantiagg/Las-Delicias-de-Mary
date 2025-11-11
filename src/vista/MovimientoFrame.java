package src.vista;

import src.modelo.MovimientoInventario;
import javax.swing.*;
import java.awt.*;

public class MovimientoFrame extends JFrame {
    
    private JComboBox<String> TipoMovimientoBox;
    // CORRECCIÓN: idInsumoField
    private JTextField idInsumoField, cantidadField, observacionField; 
    // CORRECCIÓN: registrarBtn
    private JButton registrarBtn, salirBtn; 

    public MovimientoFrame() {
        setTitle("Registro de Movimientos de Inventario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Tipo de Movimiento:"));
        TipoMovimientoBox = new JComboBox<>(new String[]{"Entrada", "Salida"});
        panel.add(TipoMovimientoBox);

        panel.add(new JLabel("ID del Insumo:"));
        // CORRECCIÓN: Inicializar idInsumoField
        idInsumoField = new JTextField(); 
        panel.add(idInsumoField);

        panel.add(new JLabel("Cantidad:"));
        cantidadField = new JTextField();
        panel.add(cantidadField);

        panel.add(new JLabel("Observación:"));
        observacionField = new JTextField();
        panel.add(observacionField);

        // CORRECCIÓN: Inicializar registrarBtn
        registrarBtn = new JButton("Registrar");
        salirBtn = new JButton("Salir");

        // CORRECCIÓN: Añadir registrarBtn al panel
        panel.add(registrarBtn); 
        panel.add(salirBtn);

        add(panel);
        
        // Listener para registrar movimientos
        registrarBtn.addActionListener(e -> {
            try {
                int tipoMovimiento = TipoMovimientoBox.getSelectedItem().equals("Entrada") ? 1 : 2;
                int idInsumo = Integer.parseInt(idInsumoField.getText());
                int cantidad = Integer.parseInt(cantidadField.getText());
                String observacion = observacionField.getText();

                MovimientoInventario.registrarMovimiento(tipoMovimiento, idInsumo, cantidad, observacion);
                JOptionPane.showMessageDialog(this, "Movimiento registrado correctamente ✅");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, 
                    "Error de formato: El ID del Insumo y la Cantidad deben ser números enteros válidos.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        salirBtn.addActionListener(e -> dispose());
    }
}