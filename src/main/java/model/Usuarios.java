package model;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

// Representa la etiqueta raíz <usuarios>.
@XmlRootElement(name = "usuarios")
@XmlAccessorType(XmlAccessType.FIELD)
public class Usuarios {

    // Cada objeto de la lista será una etiqueta <usuario>.
    @XmlElement(name = "usuario")
    private List<Usuario> usuarios = new ArrayList<>();

    public Usuarios() {
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
