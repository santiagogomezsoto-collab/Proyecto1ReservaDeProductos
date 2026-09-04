package presentation.categorias;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerTest {

    private Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller(new Model());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "   "})
    void crearConDescripcionInvalidaLanzaExcepcion(String descripcion) {
        assertThrows(IllegalArgumentException.class, () -> controller.crear(descripcion));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void actualizarConDescripcionInvalidaLanzaExcepcion(String descripcion) {
        assertThrows(IllegalArgumentException.class, () -> controller.actualizar("CAT-000001", descripcion));
    }

    @Test
    void crearConDescripcionValidaAunNoLanzaPorFaltaDeService() {
        assertThrows(UnsupportedOperationException.class, () -> controller.crear("Sala para 10 personas"));
    }
}
