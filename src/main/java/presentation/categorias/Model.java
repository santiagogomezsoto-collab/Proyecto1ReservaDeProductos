package presentation.categorias;

import model.Categoria;
import presentation.AbstractModel;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    public static final String PROP_CURRENT = "current";
    public static final String PROP_LIST = "list";

    private Categoria current;
    private List<Categoria> list = new ArrayList<>();

    public Categoria getCurrent() {
        return current;
    }

    public void setCurrent(Categoria current) {
        Categoria old = this.current;
        this.current = current;
        firePropertyChange(PROP_CURRENT, old, current);
    }

    public List<Categoria> getList() {
        return list;
    }

    public void setList(List<Categoria> list) {
        List<Categoria> old = this.list;
        this.list = list != null ? list : new ArrayList<>();
        firePropertyChange(PROP_LIST, old, this.list);
    }
}
