package presentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Base generica para las tablas (JTable) de cada modulo.
 * Cada TableModel especifico (funcionarios, categorias, recursos, etc.) solo
 * tiene que decir cuales son las columnas y como sacar cada valor de un item,
 * el resto (contar filas, contar columnas, etc.) ya esta resuelto aca.
 */
public abstract class AbstractTableModel<E> extends javax.swing.table.AbstractTableModel {

    private List<E> items = new ArrayList<>();

    public void setItems(List<E> items) {
        this.items = items != null ? items : new ArrayList<>();
        fireTableDataChanged();
    }

    public List<E> getItems() {
        return items;
    }

    public E getItemAt(int rowIndex) {
        return items.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return getColumnNames().length;
    }

    @Override
    public String getColumnName(int column) {
        return getColumnNames()[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getValueAt(items.get(rowIndex), columnIndex);
    }

    public abstract String[] getColumnNames();

    public abstract Object getValueAt(E item, int columnIndex);
}
