package presentation.categorias;

import model.Categoria;

public class TableModel extends presentation.AbstractTableModel<Categoria> {

    private static final String[] COLUMNAS = {"Id", "Descripción"};

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMNAS[column];
    }

    @Override
    protected Object getValueAt(Categoria item, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> item.getId();
            case 1 -> item.getDescripcion();
            default -> null;
        };
    }
}
