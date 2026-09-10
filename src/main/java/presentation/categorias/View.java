package presentation.categorias;

import javax.swing.*;

/**
 * Igual que en funcionarios: esta clase se empareja con un View.form del GUI
 * Designer que todavia no existe, hay que crearlo en el IDE.
 *
 * Componentes sugeridos:
 *   - txtId            (JTextField, NO editable -- el id se autogenera, ej. CAT-000001)
 *   - txtDescripcion   (JTextField)
 *   - txtBusqueda      (JTextField, busqueda por descripcion)
 *   - tablaCategorias  (JTable, usa el TableModel de este paquete)
 *   - btnNuevo, btnGuardar, btnEliminar, btnBuscar, btnImprimir (JButton)
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
