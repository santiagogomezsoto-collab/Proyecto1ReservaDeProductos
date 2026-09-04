package presentation.categorias;

import model.Categoria;

import java.util.List;

public class Controller {

    private final Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void cargarTodas() {
        // TODO(Andrés/Service): reemplazar por model.setList(Service.getInstance().listarCategorias());
        throw new UnsupportedOperationException("Pendiente de integrar con Service: listarCategorias");
    }

    public void buscar(String textoDescripcion) {
        // TODO(Andrés/Service): reemplazar por model.setList(Service.getInstance().buscarCategorias(textoDescripcion));
        throw new UnsupportedOperationException("Pendiente de integrar con Service: buscarCategorias");
    }

    public void seleccionar(Categoria categoria) {
        model.setCurrent(categoria);
    }

    public void nuevo() {
        model.setCurrent(new Categoria());
    }

    public void crear(String descripcion) {
        validarDescripcion(descripcion);
        // TODO(Andrés/Service): reemplazar por Categoria creada = Service.getInstance().crearCategoria(descripcion);
        // El id (formato CAT-000001) lo autogenera Service; luego refrescar el listado con cargarTodas().
        throw new UnsupportedOperationException("Pendiente de integrar con Service: crearCategoria");
    }

    public void actualizar(String id, String descripcion) {
        validarDescripcion(descripcion);
        // TODO(Andrés/Service): reemplazar por Service.getInstance().actualizarCategoria(id, descripcion);
        throw new UnsupportedOperationException("Pendiente de integrar con Service: actualizarCategoria");
    }

    public void eliminar(String id) {
        // TODO(Andrés/Service): reemplazar por Service.getInstance().eliminarCategoria(id);
        throw new UnsupportedOperationException("Pendiente de integrar con Service: eliminarCategoria");
    }

    public void print(List<Categoria> categorias) {
        // TODO: generar categorias.pdf con iText7 a partir de la lista recibida
        throw new UnsupportedOperationException("Pendiente de implementar reporte PDF de categorías");
    }

    private void validarDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new IllegalArgumentException("La descripción de la categoría es obligatoria");
        }
    }
}
