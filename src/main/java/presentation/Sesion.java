package presentation;

import model.Usuario;

public final class Sesion {

    private static Usuario usuario;

    private Sesion() {
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuarioActual) {
        usuario = usuarioActual;
    }

    public static boolean isLoggedIn() {
        return usuario != null;
    }
}
