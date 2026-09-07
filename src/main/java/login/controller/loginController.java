package login.controller;

import login.model.loginModel;
import login.view.loginView;

import javax.swing.*;

public class loginController {

    // El Controller conoce el Model.
    private loginModel model;

    // El Controller conoce la View.
    private loginView view;

    public loginController(loginModel model, loginView view) {

        // Guardamos los objetos que recibimos.
        this.model = model;
        this.view = view;

        // El Controller conecta el evento del botón.
        view.getBtnIngresar().addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {

        // Leemos los datos desde la View.
        String id = view.getId();
        String contrasena = view.getContrasena();

        // Guardamos los datos dentro del Model.
        model.setId(id);
        model.setContrasena(contrasena);

        // Validación mínima de interfaz.
        if (id.isBlank() || contrasena.isBlank()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Debe ingresar ID y contraseña"
            );

            return;
        }

        /*
         * AQUÍ irá después:
         *
         * LoginService
         *      ↓
         * buscar usuario
         *      ↓
         * XML mediante JAXB
         */

        JOptionPane.showMessageDialog(
                view,
                "Datos recibidos correctamente"
        );
    }
}