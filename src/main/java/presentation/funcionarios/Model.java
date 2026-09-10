package presentation.funcionarios;

import logic.Funcionario;
import presentation.AbstractModel;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    public static final String PROP_ACTUAL = "funcionarioActual";
    public static final String PROP_LISTA = "listaFuncionarios";

    private Funcionario actual;
    private List<Funcionario> lista = new ArrayList<>();

    public Funcionario getActual() {
        return actual;
    }

    public void setActual(Funcionario actual) {
        Funcionario anterior = this.actual;
        this.actual = actual;
        firePropertyChange(PROP_ACTUAL, anterior, actual);
    }

    public List<Funcionario> getLista() {
        return lista;
    }

    public void setLista(List<Funcionario> lista) {
        List<Funcionario> anterior = this.lista;
        this.lista = lista;
        firePropertyChange(PROP_LISTA, anterior, lista);
    }
}
