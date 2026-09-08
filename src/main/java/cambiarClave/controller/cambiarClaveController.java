package cambiarClave.controller;

import cambiarClave.model.cambiarClaveModel;
import cambiarClave.view.cambiarClaveView;
import login.service.LoginService;

import javax.swing.*;

public class cambiarClaveController {

    // MVC de esta pantalla.
    private cambiarClaveModel model;
    private cambiarClaveView view;

    // Service compartido que contiene la lógica.
    private LoginService service;

    public cambiarClaveController(
            cambiarClaveModel model,
            cambiarClaveView view,
            LoginService service) {

        // Guardamos los objetos recibidos.
        this.model = model;
        this.view = view;
        this.service = service;

        // El Controller escucha el botón de la View.
        view.getBtnCambiar()
                .addActionListener(e -> cambiarClave());
    }

    private void cambiarClave() {

        // Obtenemos los datos escritos en la View.
        String actual = view.getClaveActual();
        String nueva = view.getNuevaClave();
        String confirmar = view.getConfirmarClave();

        // Guardamos el estado en el Model.
        model.setClaveActual(actual);
        model.setNuevaClave(nueva);
        model.setConfirmarClave(confirmar);

        // Validamos campos vacíos.
        if (actual.isBlank()
                || nueva.isBlank()
                || confirmar.isBlank()) {

            JOptionPane.showMessageDialog(
                    view,
                    "Debe completar todos los campos"
            );

            return;
        }

        // Las nuevas contraseñas deben ser iguales.
        if (!nueva.equals(confirmar)) {

            JOptionPane.showMessageDialog(
                    view,
                    "Las nuevas contraseñas no coinciden"
            );

            return;
        }

        // Pedimos al Service realizar el cambio.
        boolean correcto =
                service.cambiarClave(
                        model.getUsuario(),
                        actual,
                        nueva
                );

        if (correcto) {

            JOptionPane.showMessageDialog(
                    view,
                    "Contraseña cambiada correctamente"
            );

            view.dispose();

        } else {

            JOptionPane.showMessageDialog(
                    view,
                    "La contraseña actual es incorrecta"
            );
        }
    }
}