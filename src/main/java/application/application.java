package application;

import login.controller.loginController;
import login.model.loginModel;
import login.view.loginView;

public class application {

    public static void main(String[] args) {

        // Creamos el Model.
        loginModel model = new loginModel();

        // Creamos la View.
        loginView view = new loginView();

        // Creamos el Controller y le pasamos Model + View.
        loginController controller =
                new loginController(model, view);

        // Mostramos la ventana.
        view.setVisible(true);
    }
}