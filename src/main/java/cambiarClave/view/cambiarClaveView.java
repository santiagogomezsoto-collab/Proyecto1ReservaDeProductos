package cambiarClave.view;

import javax.swing.*;

public class cambiarClaveView extends JDialog{
    private JPanel panelPrincipal;
    private JTextField confirmarContraseñaTextField;
    private JPasswordField txtClaveActual;
    private JPasswordField txtClaveNueva;
    private JPasswordField txtConfirmarClave;
    private JButton btnCambiar;

    public cambiarClaveView(){

            // El JPanel diseñado será el contenido del JDialog.
            setContentPane(panelPrincipal);

            setTitle("Cambiar contraseña");
            setModal(true);
            setSize(400, 250);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }

    public String getClaveActual() {
        return new String(txtClaveActual.getPassword());
    }

    public String getNuevaClave() {
        return new String(txtClaveNueva.getPassword());
    }

    public String getConfirmarClave() {
        return new String(txtConfirmarClave.getPassword());
    }

    // El Controller utilizará este botón para escuchar el evento.
    public JButton getBtnCambiar() {
        return btnCambiar;
    }


    }




