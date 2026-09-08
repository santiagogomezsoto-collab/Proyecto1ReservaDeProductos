package login.model;

import model.Usuario;

public class loginModel {

    // Datos que el usuario escribe en la pantalla.
    private String id;
    private String contrasena;

    // Aquí podremos guardar el usuario encontrado después del login.
    private Usuario usuarioAutenticado;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Usuario getUsuarioAutenticado() {
        return usuarioAutenticado;
    }

    public void setUsuarioAutenticado(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
    }
}