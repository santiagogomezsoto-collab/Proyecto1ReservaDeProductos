package application;

import controller.LoginController;
import view.LoginDialog;

public class application {

    public static void main(String[] args) {

        // Creamos un objeto Controller.
        LoginController controller = new LoginController();

        // Creamos el JDialog y le pasamos el controller.
        // Así la vista puede utilizar la lógica del controller.
        LoginDialog login = new LoginDialog(controller);

        // Mostramos la ventana.
        login.setVisible(true);
    }
}