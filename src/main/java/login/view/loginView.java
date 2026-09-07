package login.view;

import javax.swing.*;

public class loginView extends JDialog {

    // Este es el JPanel raíz del .form
    private JPanel panelPrincipal;

    private JPasswordField txtContrasena;
    private JTextField txtId;
    private JLabel ID;
    private JLabel Contraseña;
    private JButton btnIngresar;

    public loginView() {

        // Usamos el JPanel del GUI Designer como contenido del JDialog.
        setContentPane(panelPrincipal);

        setTitle("Ingreso al sistema");
        setModal(true);
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    // Permite al Controller leer el ID digitado.
    public String getId() {
        return txtId.getText();
    }

    // Permite al Controller leer la contraseña.
    public String getContrasena() {
        return new String(txtContrasena.getPassword());
    }

    // Permite al Controller acceder al botón.
    public JButton getBtnIngresar() {
        return btnIngresar;
    }
}
