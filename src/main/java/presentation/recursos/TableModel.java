package presentation.recursos;

import logic.Recurso;
import presentation.AbstractTableModel;

public class TableModel extends AbstractTableModel<Recurso> {

    private static final String[] COLUMNAS = {"Id", "Categoria", "Descripcion"};

    @Override
    public String[] getColumnNames() {
        return COLUMNAS;
    }

    @Override
    public Object getValueAt(Recurso item, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> item.getId();
            case 1 -> item.getCategoria() != null ? item.getCategoria().getDescripcion() : "";
            case 2 -> item.getDescripcion();
            default -> null;
        };
    }
}
