package presentation.recursos;

import logic.CategoriaRecurso;
import logic.Recurso;
import presentation.AbstractModel;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    public static final String PROP_ACTUAL = "recursoActual";
    public static final String PROP_LISTA = "listaRecursos";
    public static final String PROP_CATEGORIAS = "categoriasDisponibles";

    private Recurso actual;
    private List<Recurso> lista = new ArrayList<>();
    private List<CategoriaRecurso> categoriasDisponibles = new ArrayList<>();

    public Recurso getActual() {
        return actual;
    }

    public void setActual(Recurso actual) {
        Recurso anterior = this.actual;
        this.actual = actual;
        firePropertyChange(PROP_ACTUAL, anterior, actual);
    }

    public List<Recurso> getLista() {
        return lista;
    }

    public void setLista(List<Recurso> lista) {
        List<Recurso> anterior = this.lista;
        this.lista = lista;
        firePropertyChange(PROP_LISTA, anterior, lista);
    }

    public List<CategoriaRecurso> getCategoriasDisponibles() {
        return categoriasDisponibles;
    }

    public void setCategoriasDisponibles(List<CategoriaRecurso> categoriasDisponibles) {
        List<CategoriaRecurso> anterior = this.categoriasDisponibles;
        this.categoriasDisponibles = categoriasDisponibles;
        firePropertyChange(PROP_CATEGORIAS, anterior, categoriasDisponibles);
    }
}
