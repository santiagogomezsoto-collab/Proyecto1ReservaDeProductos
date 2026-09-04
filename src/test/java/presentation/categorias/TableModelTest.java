package presentation.categorias;

import model.Categoria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TableModelTest {

    private TableModel tableModel;

    @BeforeEach
    void setUp() {
        tableModel = new TableModel();
    }

    @Test
    void columnasSonIdYDescripcion() {
        assertEquals(2, tableModel.getColumnCount());
        assertEquals("Id", tableModel.getColumnName(0));
        assertEquals("Descripción", tableModel.getColumnName(1));
    }

    @Test
    void muestraLosDatosDeCadaCategoria() {
        tableModel.setItems(List.of(new Categoria("CAT-000001", "Sala para 10 personas")));

        assertEquals(1, tableModel.getRowCount());
        assertEquals("CAT-000001", tableModel.getValueAt(0, 0));
        assertEquals("Sala para 10 personas", tableModel.getValueAt(0, 1));
    }
}
