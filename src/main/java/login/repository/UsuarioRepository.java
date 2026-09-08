package login.repository;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import model.Usuarios;

import java.io.File;

public class UsuarioRepository {

    // Archivo que funciona como almacenamiento de usuarios.
    private final String RUTA = "data/usuarios.xml";

    public Usuarios cargarUsuarios() {

        try {

            // Le decimos a JAXB qué tipo de objeto queremos convertir.
            JAXBContext context =
                    JAXBContext.newInstance(Usuarios.class);

            // El Unmarshaller convierte XML → objetos Java.
            Unmarshaller unmarshaller =
                    context.createUnmarshaller();

            // Leemos el archivo XML.
            File archivo = new File(RUTA);

            // Convertimos el XML en un objeto Usuarios.
            return (Usuarios) unmarshaller.unmarshal(archivo);

        } catch (Exception e) {

            // Por ahora mostramos el error para poder diagnosticar.
            e.printStackTrace();

            return null;
        }
    }
}