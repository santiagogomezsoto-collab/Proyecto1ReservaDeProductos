package login.service;

import login.repository.UsuarioRepository;
import model.Usuario;
import model.Usuarios;

public class LoginService {

    // El Service obtiene los datos mediante Repository.
    private UsuarioRepository repository;

    public LoginService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario iniciarSesion(String id, String contrasena) {

        // Repository convierte el XML en objetos Java.
        Usuarios datos = repository.cargarUsuarios();

        // Si hubo algún problema leyendo el XML.
        if (datos == null) {
            return null;
        }

        // Recorremos los objetos Usuario obtenidos del XML.
        for (Usuario usuario : datos.getUsuarios()) {

            if (usuario.getId().equals(id)
                    && usuario.getClave().equals(contrasena)) {

                // Encontramos al usuario correcto.
                return usuario;
            }
        }

        // No encontramos coincidencia.
        return null;
    }
}