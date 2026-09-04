package presentation;

import java.util.ArrayList;
import java.util.List;

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
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getValueAt(items.get(rowIndex), columnIndex);
    }

    protected abstract Object getValueAt(E item, int columnIndex);
}
