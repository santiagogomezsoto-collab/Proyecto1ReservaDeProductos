package view;



import javax.swing.*;

import controller.LoginController;
import model.Usuario;


public class LoginDialog extends JDialog{
    private JPanel panelPrincipal;
    private JTextField txtId;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;


    private LoginController controller;

    public LoginDialog(LoginController controller) {
        this.controller = controller;

        setContentPane(panelPrincipal);

        setTitle("Ingreso al Sistema");
        setModal(true);
        setSize(220,330);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //VIENE EL EVENTO BOTON

        btnIngresar.addActionListener(e -> ingresar());
    }

    private void ingresar(){
        //tomo el id y contra escrita en el GUI
        String id = txtId.getText();
        String contrasena = new String(txtContrasena.getPassword());

        //le  mando al controller que verifique el login()

        Usuario usuario = controller.iniciarSesion(id, contrasena);


        //si el usuario es correcto, pop up de usuario invalido
        if(usuario != null) {
            JOptionPane.showMessageDialog(this, "Ingreso correcto");

            dispose(); //cierro el joptionpane
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "ID o contraseña incorrectos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        }
    }

}

//CLICK en btnIngresar
//        ↓
//ActionListener
//        ↓
//ingresar()
//        ↓
//lee txtId y txtContrasena
//        ↓
//LoginController.iniciarSesion()
//        ↓
//retorna Usuario o null
//        ↓
//JDialog muestra resultado
