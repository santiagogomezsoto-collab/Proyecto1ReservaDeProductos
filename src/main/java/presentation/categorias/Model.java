package presentation.categorias;

import logic.CategoriaRecurso;
import presentation.AbstractModel;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    public static final String PROP_ACTUAL = "categoriaActual";
    public static final String PROP_LISTA = "listaCategorias";

    private CategoriaRecurso actual;
    private List<CategoriaRecurso> lista = new ArrayList<>();

    public CategoriaRecurso getActual() {
        return actual;
    }

    public void setActual(CategoriaRecurso actual) {
        CategoriaRecurso anterior = this.actual;
        this.actual = actual;
        firePropertyChange(PROP_ACTUAL, anterior, actual);
    }

    public List<CategoriaRecurso> getLista() {
        return lista;
    }

    public void setLista(List<CategoriaRecurso> lista) {
        List<CategoriaRecurso> anterior = this.lista;
        this.lista = lista;
        firePropertyChange(PROP_LISTA, anterior, lista);
    }
}
