package presentation.funcionarios;

import logic.Funcionario;
import presentation.AbstractTableModel;

public class TableModel extends AbstractTableModel<Funcionario> {

    private static final String[] COLUMNAS = {"Id", "Nombre", "Telefono"};

    @Override
    public String[] getColumnNames() {
        return COLUMNAS;
    }

    @Override
    public Object getValueAt(Funcionario item, int columnIndex) {
        return switch (columnIndex) {
            case 0 -> item.getId();
            case 1 -> item.getNombre();
            case 2 -> item.getTelefono();
            default -> null;
        };
    }
}
