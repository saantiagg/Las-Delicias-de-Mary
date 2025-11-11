package src.vista;

import src.modelo.Insumo;
import src.modelo.InsumoDAO;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class InsumoFormDialog extends JDialog {
    // CORRECCIÓN: Declaración correcta de los campos.
    private JTextField nombreField, unidadMedidaField, cantidadMinimaField, precioUnitarioField; 
    private JButton guardarBtn, cancelarBtn;
    private InsumoDAO insumoDAO;
    private InsumoPanel panelPadre;

    public InsumoFormDialog(JFrame parent, InsumoPanel panelPadre) {
        super(parent, "Agregar Nuevo Insumo", true); 
        this.insumoDAO = new InsumoDAO();
        this.panelPadre = panelPadre;
        
        setSize(400, 350);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        initComponents(); // Aquí se inicializan los campos.
        setupListeners();
    }

    private void initComponents() {
        // --- Panel de Campos ---
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 15));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCampos.add(new JLabel("Nombre:"));
        nombreField = new JTextField();
        panelCampos.add(nombreField);

        panelCampos.add(new JLabel("Unidad de Medida:"));
        unidadMedidaField = new JTextField(); // Inicialización de unidadMedidaField
        panelCampos.add(unidadMedidaField);

        panelCampos.add(new JLabel("Cantidad Mínima:"));
        cantidadMinimaField = new JTextField();
        panelCampos.add(cantidadMinimaField);

        panelCampos.add(new JLabel("Precio Unitario:"));
        precioUnitarioField = new JTextField();
        panelCampos.add(precioUnitarioField);

        add(panelCampos, BorderLayout.CENTER);

        // --- Panel de Botones ---
        JPanel panelBotones = new JPanel();
        guardarBtn = new JButton("Guardar");
        cancelarBtn = new JButton("Cancelar");
        panelBotones.add(guardarBtn);
        panelBotones.add(cancelarBtn);
        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private void setupListeners() {
        guardarBtn.addActionListener(e -> guardarInsumo());
        cancelarBtn.addActionListener(e -> dispose());
    }

    private void guardarInsumo() {
        try {
            String nombre = nombreField.getText().trim();
            String unidadMedida = unidadMedidaField.getText().trim(); // Lectura correcta
            
            // Validación de campos vacíos (mínimo)
            if (nombre.isEmpty() || unidadMedida.isEmpty() || cantidadMinimaField.getText().trim().isEmpty() || precioUnitarioField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe completar todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // La cantidad actual inicial es 0 (para evitar NOT NULL y manejar el stock con movimientos)
            BigDecimal cantidadActual = BigDecimal.ZERO; 
            BigDecimal cantidadMinima = new BigDecimal(cantidadMinimaField.getText().trim());
            BigDecimal precioUnitario = new BigDecimal(precioUnitarioField.getText().trim());

            Insumo nuevoInsumo = new Insumo(0, nombre, unidadMedida, cantidadActual, cantidadMinima, precioUnitario);
            
            if (insumoDAO.agregar(nuevoInsumo)) { 
                JOptionPane.showMessageDialog(this, "Insumo agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                if (panelPadre != null) {
                    panelPadre.cargarInsumos(); 
                }
                dispose();
            } else {
                // Si el DAO retorna false (SQL Exception), muestra este mensaje
                JOptionPane.showMessageDialog(this, "Error al guardar en la base de datos. Revise la consola para detalles del error SQL.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad Mínima y Precio Unitario deben ser números válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}