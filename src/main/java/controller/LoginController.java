package controller;

import model.Rol;
import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    private List<Usuario> usuarios;

    public LoginController() {

        usuarios = new ArrayList<>();

        usuarios.add(new Usuario("admin", "admin",
                Rol.ADMINISTRADOR
        ));

        usuarios.add(new Usuario("123", "123",
                Rol.FUNCIONARIO
        ));
    }

    public Usuario iniciarSesion(String id, String clave) {

        // Recorremos cada usuario de la lista.
        for (Usuario usuario : usuarios) {

            // Si el id y la clave coinciden...
            if (usuario.getId().equals(id)
                    && usuario.getClave().equals(clave)) {

                // Devolvemos ese objeto Usuario.
                return usuario;
            }
        }

        // Si recorremos toda la lista y no encontramos coincidencia.
        return null;
    }
}
