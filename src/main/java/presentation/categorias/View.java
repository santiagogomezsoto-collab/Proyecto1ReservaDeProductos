package presentation.categorias;

import javax.swing.*;
import java.awt.event.ActionListener;

public class View extends JPanel {
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnImprimir;
    private JTextField txtDescripcion;
    private JTextField txtId;
    private JButton btnGuardar;
    private JButton btnBorrar;
    private JButton btnLimpiar;
    private JTable table;
    private JPanel PanelCategoria;
    private JPanel PanelIdcat;
    private JPanel PanelDescripCat;
    private JPanel PanelButtonsCat;
    private JPanel PanelBusqueda;
    private JPanel PaneldescripcionBusq;
    private JPanel listadotitu;
    private JPanel panelTabla;

    public View() {
        table.setModel(new TableModel());
    }

    public String getIdTexto() {
        return txtId.getText();
    }

    public void setIdTexto(String id) {
        txtId.setText(id);
    }

    public String getDescripcionTexto() {
        return txtDescripcion.getText();
    }

    public void setDescripcionTexto(String descripcion) {
        txtDescripcion.setText(descripcion);
    }

    public String getTextoBusqueda() {
        return txtBuscar.getText();
    }

    public JTable getTable() {
        return table;
    }

    public void addBuscarListener(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }

    public void addImprimirListener(ActionListener listener) {
        btnImprimir.addActionListener(listener);
    }

    public void addGuardarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void addBorrarListener(ActionListener listener) {
        btnBorrar.addActionListener(listener);
    }

    public void addLimpiarListener(ActionListener listener) {
        btnLimpiar.addActionListener(listener);
    }

    public void limpiarFormulario() {
        txtId.setText("");
        txtDescripcion.setText("");
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
