package presentation.categorias;

import logic.CategoriaRecurso;
import presentation.AbstractTableModel;

public class TableModel extends AbstractTableModel<CategoriaRecurso> {

    private static final String[] COLUMNAS = {"Id", "Descripcion"};

    @Override
    public String[] getColumnNames() {
        return COLUMNAS;
    }

    @Override
    public Object getValueAt(CategoriaRecurso item, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> item.getId();
            case 1 -> item.getDescripcion();
            default -> null;
        };
    }
}
