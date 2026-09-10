package presentation.categorias;

import logic.CategoriaRecurso;
import logic.Service;
import logic.ValidationException;
import presentation.PdfReportHelper;

public class Controller {

    private final Model model;
    private final Service service = Service.getInstance();

    public Controller(Model model) {
        this.model = model;
    }

    public void cargarTodos() {
        model.setLista(service.listarCategorias());
    }

    public void buscar(String descripcion) {
        model.setLista(service.buscarCategoriasPorDescripcion(descripcion));
    }

    public void crear(CategoriaRecurso categoria) throws ValidationException {
        service.crearCategoria(categoria);
        cargarTodos();
    }

    public void actualizar(CategoriaRecurso categoria) throws ValidationException {
        service.actualizarCategoria(categoria);
        cargarTodos();
    }

    public void eliminar(String id) throws ValidationException {
        service.eliminarCategoria(id);
        cargarTodos();
    }

    public void imprimir() throws Exception {
        TableModel tabla = new TableModel();
        tabla.setItems(model.getLista());
        PdfReportHelper.generarPdf("Listado de Categorias", "categorias.pdf", tabla);
        PdfReportHelper.abrirPdf("categorias.pdf");
    }
}
