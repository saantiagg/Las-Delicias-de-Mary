package src.vista; 

import src.modelo.Insumo;
import src.modelo.InsumoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class InsumoPanel extends JPanel {
    
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private InsumoDAO insumoDAO;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;

    public InsumoPanel() {
        // [Código de inicialización y setup de tabla...]
        setLayout(new BorderLayout());
        insumoDAO = new InsumoDAO(); 

        JLabel title = new JLabel("Gestión de Insumos", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Nombres de columna de la tabla de la vista
        String[] columnas = {"ID", "Nombre", "Unidad de Medida", "Cantidad Actual", "Cantidad Mínima"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Integer.class; return super.getColumnClass(columnIndex);
            }
        };
        tabla = new JTable(modeloTabla);
        cargarInsumos(); 
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Inicialización y listeners...
        JPanel botones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        botones.add(btnAgregar);
        botones.add(btnEditar);
        botones.add(btnEliminar);
        add(botones, BorderLayout.SOUTH);
        
        // Listener AGREGAR: Abre el diálogo corregido
        btnAgregar.addActionListener(e -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            InsumoFormDialog dialog = new InsumoFormDialog(parentFrame, this); 
            dialog.setVisible(true);
        });
        
        // Listener ELIMINAR: Llama a la lógica de eliminación
        btnEliminar.addActionListener(e -> eliminarInsumo());
    }
    
    // Método PÚBLICO para cargar datos en la tabla (CORREGIDO)
    public void cargarInsumos() { 
        modeloTabla.setRowCount(0); 
        List<Insumo> insumos = insumoDAO.obtenerTodos();
        
        for (Insumo i : insumos) {
            modeloTabla.addRow(new Object[]{
                i.getId(), 
                i.getNombre(), 
                i.getUnidad(), 
                i.getCantidadActual(), 
                i.getCantidadMinima()
            });
        }
    }
    
    // Lógica para el botón eliminar
    private void eliminarInsumo() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada >= 0) {
            int idInsumo = (int) modeloTabla.getValueAt(filaSeleccionada, 0); 
            
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el insumo con ID " + idInsumo + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                if (insumoDAO.eliminar(idInsumo)) {
                    JOptionPane.showMessageDialog(this, "Insumo eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cargarInsumos(); 
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar. Puede que el insumo tenga movimientos de inventario asociados.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un insumo para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }
}