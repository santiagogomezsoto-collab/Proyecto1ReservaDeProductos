package presentation.funcionarios;

import logic.Funcionario;
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
        model.setLista(service.listarFuncionarios());
    }

    public void buscar(String texto) {
        model.setLista(service.buscarFuncionarios(texto));
    }

    public void crear(Funcionario funcionario) throws ValidationException {
        service.crearFuncionario(funcionario);
        cargarTodos();
    }

    public void actualizar(Funcionario funcionario) throws ValidationException {
        service.actualizarFuncionario(funcionario);
        cargarTodos();
    }

    public void eliminar(String id) throws ValidationException {
        service.eliminarFuncionario(id);
        cargarTodos();
    }

    public void imprimir() throws Exception {
        TableModel tabla = new TableModel();
        tabla.setItems(model.getLista());
        PdfReportHelper.generarPdf("Listado de Funcionarios", "funcionarios.pdf", tabla);
        PdfReportHelper.abrirPdf("funcionarios.pdf");
    }
}
