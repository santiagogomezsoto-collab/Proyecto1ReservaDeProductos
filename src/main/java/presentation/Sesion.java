package presentation;

import logic.Usuario;

/**
 * Guarda el usuario que hizo login para que cualquier pantalla lo pueda
 * consultar (Sesion.getUsuario()) sin tener que pasarlo de parametro por todos lados.
 */
public class Sesion {

    private static Usuario usuarioActual;

    private Sesion() {
    }

    public static Usuario getUsuario() {
        return usuarioActual;
    }

    public static void setUsuario(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static boolean haySesion() {
        return usuarioActual != null;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
