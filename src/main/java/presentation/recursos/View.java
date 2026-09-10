package presentation.recursos;

import javax.swing.*;

/**
 * Misma logica que en los otros modulos: falta crear el View.form en el GUI
 * Designer de IntelliJ, vinculado a esta clase.
 *
 * Componentes sugeridos:
 *   - txtId              (JTextField, numero de activo, lo escribe el usuario)
 *   - comboCategoria     (JComboBox<Categoria>, se llena con categoriasDisponibles del Model)
 *   - txtDescripcion     (JTextField)
 *   - comboFiltroCategoria (JComboBox<Categoria>, para filtrar la tabla)
 *   - tablaRecursos      (JTable, usa el TableModel de este paquete)
 *   - btnNuevo, btnGuardar, btnEliminar, btnImprimir (JButton)
 *
 * $$$setupUI$$$() y los campos de los componentes los agrega solo IntelliJ
 * cuando exista el .form -- eso no se edita a mano.
 */
public class View extends JPanel {

    // TODO: cuando exista el .form, aca se conecta el Controller.
    //
    // Los metodos crear/actualizar/eliminar del Controller pueden lanzar
    // ValidationException -- cada listener de boton debe envolver su llamada
    // en try/catch y mostrar el mensaje con
    // JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE).
}
