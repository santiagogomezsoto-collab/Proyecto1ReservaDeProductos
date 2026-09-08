package cambiarClave.model;
import model.Usuario;


public class cambiarClaveModel {

    // Usuario que inició sesión.
    private Usuario usuario;

    // Datos de esta pantalla.
    private String claveActual;
    private String nuevaClave;
    private String confirmarClave;

    public cambiarClaveModel(Usuario usuario) {

        // Guardamos el objeto Usuario recibido.
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getConfirmarClave() {
        return confirmarClave;
    }

    public void setConfirmarClave(String confirmarClave) {
        this.confirmarClave = confirmarClave;
    }
}
