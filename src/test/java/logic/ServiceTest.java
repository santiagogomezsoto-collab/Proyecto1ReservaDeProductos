package logic;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias del CRUD de Funcionarios/Categorias/Recursos en Service.
 *
 * Service es un singleton (mismo estado durante toda la corrida de tests), asi
 * que cada test genera sus propios ids unicos con UUID en vez de fijarse en
 * el tamaño total de las listas -- asi no importa el orden en que corran.
 */
class ServiceTest {

    private final Service service = Service.getInstance();

    private String idUnico(String prefijo) {
        return prefijo + "-" + UUID.randomUUID();
    }

    // ---------- Funcionarios ----------

    @Test
    void crearFuncionario_datosValidos_claveQuedaIgualAlId() throws ValidationException {
        String id = idUnico("F");
        Funcionario f = new Funcionario(id, "clave-cualquiera", Rol.FUNCIONARIO, "Ana Perez", "8888-8888");

        service.crearFuncionario(f);

        assertEquals(id, service.buscarFuncionarioPorId(id).getClave());
    }

    @Test
    void crearFuncionario_idDuplicado_lanzaValidationException() throws ValidationException {
        String id = idUnico("F");
        service.crearFuncionario(new Funcionario(id, "x", Rol.FUNCIONARIO, "Ana", "8888-8888"));

        assertThrows(ValidationException.class, () ->
                service.crearFuncionario(new Funcionario(id, "x", Rol.FUNCIONARIO, "Otro", "9999-9999")));
    }

    @Test
    void crearFuncionario_nombreVacio_lanzaValidationException() {
        Funcionario f = new Funcionario(idUnico("F"), "x", Rol.FUNCIONARIO, "", "8888-8888");

        assertThrows(ValidationException.class, () -> service.crearFuncionario(f));
    }

    @Test
    void actualizarFuncionario_idInexistente_lanzaValidationException() {
        Funcionario f = new Funcionario(idUnico("F"), "x", Rol.FUNCIONARIO, "Ana", "8888-8888");

        assertThrows(ValidationException.class, () -> service.actualizarFuncionario(f));
    }

    @Test
    void actualizarFuncionario_datosValidos_noModificaClave() throws ValidationException {
        String id = idUnico("F");
        service.crearFuncionario(new Funcionario(id, "ignorada", Rol.FUNCIONARIO, "Ana", "8888-8888"));
        String claveOriginal = service.buscarFuncionarioPorId(id).getClave();

        Funcionario cambios = new Funcionario(id, "otra-clave", Rol.FUNCIONARIO, "Ana Maria", "7777-7777");
        service.actualizarFuncionario(cambios);

        Funcionario actualizado = service.buscarFuncionarioPorId(id);
        assertEquals("Ana Maria", actualizado.getNombre());
        assertEquals("7777-7777", actualizado.getTelefono());
        assertEquals(claveOriginal, actualizado.getClave());
    }

    @Test
    void eliminarFuncionario_idInexistente_lanzaValidationException() {
        assertThrows(ValidationException.class, () -> service.eliminarFuncionario(idUnico("F")));
    }

    @Test
    void eliminarFuncionario_existente_yaNoApareceEnListar() throws ValidationException {
        String id = idUnico("F");
        service.crearFuncionario(new Funcionario(id, "x", Rol.FUNCIONARIO, "Ana", "8888-8888"));

        service.eliminarFuncionario(id);

        assertNull(service.buscarFuncionarioPorId(id));
    }

    @Test
    void buscarFuncionarios_coincideParcialCaseInsensitive() throws ValidationException {
        String id = idUnico("F");
        service.crearFuncionario(new Funcionario(id, "x", Rol.FUNCIONARIO, "Maria Rodriguez", "8888-8888"));

        List<Funcionario> resultado = service.buscarFuncionarios("rodriguez");

        assertTrue(resultado.stream().anyMatch(f -> f.getId().equals(id)));
    }

    @Test
    void buscarFuncionarios_porId_retornaCoincidencia() throws ValidationException {
        String id = idUnico("F");
        service.crearFuncionario(new Funcionario(id, "x", Rol.FUNCIONARIO, "Carlos Mora", "8888-8888"));

        List<Funcionario> resultado = service.buscarFuncionarios(id);

        assertTrue(resultado.stream().anyMatch(f -> f.getId().equals(id)));
    }

    @Test
    void buscarFuncionarios_textoVacio_retornaTodos() throws ValidationException {
        String id = idUnico("F");
        service.crearFuncionario(new Funcionario(id, "x", Rol.FUNCIONARIO, "Ana", "8888-8888"));

        List<Funcionario> resultado = service.buscarFuncionarios("");

        assertTrue(resultado.stream().anyMatch(f -> f.getId().equals(id)));
    }

    // ---------- Categorias ----------

    @Test
    void crearCategoria_generaIdConFormatoCAT() throws ValidationException {
        CategoriaRecurso c = new CategoriaRecurso(null, "Sala para " + idUnico("desc"));

        service.crearCategoria(c);

        assertTrue(c.getId().matches("CAT-\\d{6}"));
    }

    @Test
    void crearCategoria_idsGeneradosSonUnicosEnCreacionesSucesivas() throws ValidationException {
        CategoriaRecurso c1 = new CategoriaRecurso(null, idUnico("desc"));
        CategoriaRecurso c2 = new CategoriaRecurso(null, idUnico("desc"));

        service.crearCategoria(c1);
        service.crearCategoria(c2);

        assertFalse(c1.getId().equals(c2.getId()));
    }

    @Test
    void crearCategoria_descripcionVacia_lanzaValidationException() {
        CategoriaRecurso c = new CategoriaRecurso(null, " ");

        assertThrows(ValidationException.class, () -> service.crearCategoria(c));
    }

    @Test
    void actualizarCategoria_idInexistente_lanzaValidationException() {
        CategoriaRecurso c = new CategoriaRecurso("CAT-999999", "algo");

        assertThrows(ValidationException.class, () -> service.actualizarCategoria(c));
    }

    @Test
    void eliminarCategoria_conRecursosAsociados_lanzaValidationException() throws ValidationException {
        CategoriaRecurso categoria = new CategoriaRecurso(null, idUnico("desc"));
        service.crearCategoria(categoria);
        service.crearRecurso(new Recurso(idUnico("R"), "Recurso de prueba", categoria));

        assertThrows(ValidationException.class, () -> service.eliminarCategoria(categoria.getId()));
    }

    @Test
    void eliminarCategoria_sinRecursosAsociados_laElimina() throws ValidationException {
        CategoriaRecurso categoria = new CategoriaRecurso(null, idUnico("desc"));
        service.crearCategoria(categoria);

        service.eliminarCategoria(categoria.getId());

        assertNull(service.buscarCategoriaPorId(categoria.getId()));
    }

    @Test
    void buscarCategoriasPorDescripcion_containsCaseInsensitive() throws ValidationException {
        String marca = idUnico("marca");
        CategoriaRecurso categoria = new CategoriaRecurso(null, "Sala " + marca + " para reuniones");
        service.crearCategoria(categoria);

        List<CategoriaRecurso> resultado = service.buscarCategoriasPorDescripcion(marca.toUpperCase());

        assertTrue(resultado.stream().anyMatch(c -> c.getId().equals(categoria.getId())));
    }

    // ---------- Recursos ----------

    @Test
    void crearRecurso_datosValidos_seAgregaALaLista() throws ValidationException {
        CategoriaRecurso categoria = new CategoriaRecurso(null, idUnico("desc"));
        service.crearCategoria(categoria);
        String idRecurso = idUnico("R");

        service.crearRecurso(new Recurso(idRecurso, "Laptop de prueba", categoria));

        assertNotNull(service.buscarRecursoPorId(idRecurso));
    }

    @Test
    void crearRecurso_idDuplicado_lanzaValidationException() throws ValidationException {
        CategoriaRecurso categoria = new CategoriaRecurso(null, idUnico("desc"));
        service.crearCategoria(categoria);
        String idRecurso = idUnico("R");
        service.crearRecurso(new Recurso(idRecurso, "Laptop A", categoria));

        assertThrows(ValidationException.class, () ->
                service.crearRecurso(new Recurso(idRecurso, "Laptop B", categoria)));
    }

    @Test
    void crearRecurso_categoriaInexistente_lanzaValidationException() {
        CategoriaRecurso categoriaFalsa = new CategoriaRecurso("CAT-000000", "no existe");
        Recurso r = new Recurso(idUnico("R"), "Laptop", categoriaFalsa);

        assertThrows(ValidationException.class, () -> service.crearRecurso(r));
    }

    @Test
    void actualizarRecurso_idInexistente_lanzaValidationException() throws ValidationException {
        CategoriaRecurso categoria = new CategoriaRecurso(null, idUnico("desc"));
        service.crearCategoria(categoria);
        Recurso r = new Recurso(idUnico("R"), "Laptop", categoria);

        assertThrows(ValidationException.class, () -> service.actualizarRecurso(r));
    }

    @Test
    void actualizarRecurso_datosValidos_actualizaDescripcionYCategoria() throws ValidationException {
        CategoriaRecurso categoriaOriginal = new CategoriaRecurso(null, idUnico("desc"));
        CategoriaRecurso categoriaNueva = new CategoriaRecurso(null, idUnico("desc"));
        service.crearCategoria(categoriaOriginal);
        service.crearCategoria(categoriaNueva);
        String idRecurso = idUnico("R");
        service.crearRecurso(new Recurso(idRecurso, "Descripcion vieja", categoriaOriginal));

        Recurso cambios = new Recurso(idRecurso, "Descripcion nueva", categoriaNueva);
        service.actualizarRecurso(cambios);

        Recurso actualizado = service.buscarRecursoPorId(idRecurso);
        assertEquals("Descripcion nueva", actualizado.getDescripcion());
        assertEquals(categoriaNueva.getId(), actualizado.getCategoria().getId());
    }

    @Test
    void eliminarRecurso_idInexistente_lanzaValidationException() {
        assertThrows(ValidationException.class, () -> service.eliminarRecurso(idUnico("R")));
    }

    @Test
    void buscarRecursosPorCategoria_null_retornaTodos() throws ValidationException {
        CategoriaRecurso categoria = new CategoriaRecurso(null, idUnico("desc"));
        service.crearCategoria(categoria);
        String idRecurso = idUnico("R");
        service.crearRecurso(new Recurso(idRecurso, "Laptop", categoria));

        List<Recurso> resultado = service.buscarRecursosPorCategoria(null);

        assertTrue(resultado.stream().anyMatch(r -> r.getId().equals(idRecurso)));
    }

    @Test
    void buscarRecursosPorCategoria_categoriaEspecifica_filtraCorrectamente() throws ValidationException {
        CategoriaRecurso categoriaA = new CategoriaRecurso(null, idUnico("desc"));
        CategoriaRecurso categoriaB = new CategoriaRecurso(null, idUnico("desc"));
        service.crearCategoria(categoriaA);
        service.crearCategoria(categoriaB);
        String idEnA = idUnico("R");
        String idEnB = idUnico("R");
        service.crearRecurso(new Recurso(idEnA, "Recurso A", categoriaA));
        service.crearRecurso(new Recurso(idEnB, "Recurso B", categoriaB));

        List<Recurso> resultado = service.buscarRecursosPorCategoria(categoriaA);

        assertTrue(resultado.stream().anyMatch(r -> r.getId().equals(idEnA)));
        assertFalse(resultado.stream().anyMatch(r -> r.getId().equals(idEnB)));
    }
}
