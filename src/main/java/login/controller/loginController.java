package login.controller;

import login.model.loginModel;
import login.service.LoginService;
import login.view.loginView;
import model.Usuario;

import javax.swing.*;

public class loginController {

    private loginModel model;
    private loginView view;

    // Objeto que contiene la lógica del login.
    private LoginService service;

    public loginController(
            loginModel model,
            loginView view,
            LoginService service) {

        // Guardamos los objetos recibidos.
        this.model = model;
        this.view = view;
        this.service = service;

        // El Controller escucha el botón.
        view.getBtnIngresar().addActionListener(e -> iniciarSesion());
    }

    private void iniciarSesion() {

        // Leemos los datos escritos en la View.
        String id = view.getId();
        String contrasena = view.getContrasena();

        // Validamos que no estén vacíos.
        if (id.isBlank() || contrasena.isBlank()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Debe ingresar ID y contraseña"
            );

            return;
        }

        // Pedimos al Service que autentique al usuario.
        Usuario usuario =
                service.iniciarSesion(id, contrasena);

        if (usuario != null) {

            // Guardamos el usuario autenticado en el Model.
            model.setUsuarioAutenticado(usuario);

            JOptionPane.showMessageDialog(
                    view,
                    "Ingreso correcto"
            );

            view.dispose();

        } else {

            JOptionPane.showMessageDialog(
                    view,
                    "ID o contraseña incorrectos"
            );
        }
    }
}