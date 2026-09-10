package presentation.recursos;

import logic.CategoriaRecurso;
import logic.Recurso;
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
        model.setLista(service.listarRecursos());
        model.setCategoriasDisponibles(service.listarCategorias());
    }

    public void filtrarPorCategoria(CategoriaRecurso categoria) {
        model.setLista(service.buscarRecursosPorCategoria(categoria));
    }

    public void crear(Recurso recurso) throws ValidationException {
        service.crearRecurso(recurso);
        cargarTodos();
    }

    public void actualizar(Recurso recurso) throws ValidationException {
        service.actualizarRecurso(recurso);
        cargarTodos();
    }

    public void eliminar(String id) throws ValidationException {
        service.eliminarRecurso(id);
        cargarTodos();
    }

    public void imprimir() throws Exception {
        TableModel tabla = new TableModel();
        tabla.setItems(model.getLista());
        PdfReportHelper.generarPdf("Listado de Recursos", "recursos.pdf", tabla);
        PdfReportHelper.abrirPdf("recursos.pdf");
    }
}
