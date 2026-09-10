package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Service es un singleton (una sola instancia para toda la app) que concentra
 * TODA la logica de negocio. Las pantallas (presentation.*) nunca tocan los
 * datos directamente, siempre pasan por aca.
 *
 * OJO: esto es compartido entre los tres. Por ahora solo tiene lo que necesito
 * yo (funcionarios, categorias, recursos). Cada quien va agregando sus propios
 * metodos (login, reservas, calendarizacion, estadisticas) sin pisar los de
 * los demas -- si hay que tocar algo que ya existe aca, se avisa al grupo.
 *
 * Convencion de errores: cualquier metodo que MODIFICA datos (crear/actualizar/
 * eliminar) puede lanzar ValidationException con un mensaje ya listo para
 * mostrarle al usuario. Los metodos de listar/buscar nunca lanzan, devuelven
 * listas vacias si no hay resultados.
 *
 * TODO: por ahora los datos viven solo en memoria (listas). Cuando este lista
 * la parte de persistencia (Data/XmlPersister leyendo/escribiendo data.xml)
 * hay que cargar y guardar desde ahi en vez de usar listas sueltas. Ojo con
 * categoriaSeq -- hay que reseedearlo desde el maximo id existente al cargar.
 */
public class Service {

    private static Service instancia;

    private final List<Funcionario> funcionarios = new ArrayList<>();
    private final List<CategoriaRecurso> categorias = new ArrayList<>();
    private final List<Recurso> recursos = new ArrayList<>();

    private int categoriaSeq = 0;

    private Service() {
    }

    public static Service getInstance() {
        if (instancia == null) {
            instancia = new Service();
        }
        return instancia;
    }

    private void requireNonBlank(String valor, String mensaje) throws ValidationException {
        if (valor == null || valor.isBlank()) {
            throw new ValidationException(mensaje);
        }
    }

    private boolean contiene(String texto, String busqueda) {
        return texto != null && texto.toLowerCase(Locale.ROOT).contains(busqueda.toLowerCase(Locale.ROOT));
    }

    // ---------- Funcionarios ----------

    public List<Funcionario> listarFuncionarios() {
        return new ArrayList<>(funcionarios);
    }

    public Funcionario buscarFuncionarioPorId(String id) {
        for (Funcionario f : funcionarios) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    public List<Funcionario> buscarFuncionarios(String texto) {
        if (texto == null || texto.isBlank()) {
            return listarFuncionarios();
        }
        List<Funcionario> resultado = new ArrayList<>();
        for (Funcionario f : funcionarios) {
            if (contiene(f.getId(), texto) || contiene(f.getNombre(), texto)) {
                resultado.add(f);
            }
        }
        return resultado;
    }

    public void crearFuncionario(Funcionario funcionario) throws ValidationException {
        if (funcionario == null) {
            throw new ValidationException("El funcionario no puede ser nulo.");
        }
        requireNonBlank(funcionario.getId(), "El id del funcionario es obligatorio.");
        requireNonBlank(funcionario.getNombre(), "El nombre del funcionario es obligatorio.");
        requireNonBlank(funcionario.getTelefono(), "El telefono del funcionario es obligatorio.");
        if (funcionario.getRol() == null) {
            throw new ValidationException("El rol del funcionario es obligatorio.");
        }
        if (buscarFuncionarioPorId(funcionario.getId()) != null) {
            throw new ValidationException("Ya existe un funcionario con id " + funcionario.getId());
        }
        // regla del enunciado: la clave inicial queda igual al id
        funcionario.setClave(funcionario.getId());
        funcionarios.add(funcionario);
    }

    public void actualizarFuncionario(Funcionario funcionario) throws ValidationException {
        if (funcionario == null) {
            throw new ValidationException("El funcionario no puede ser nulo.");
        }
        Funcionario existente = buscarFuncionarioPorId(funcionario.getId());
        if (existente == null) {
            throw new ValidationException("No existe un funcionario con id " + funcionario.getId());
        }
        requireNonBlank(funcionario.getNombre(), "El nombre del funcionario es obligatorio.");
        requireNonBlank(funcionario.getTelefono(), "El telefono del funcionario es obligatorio.");
        // ojo: solo nombre/telefono -- la clave la cambia el propio funcionario
        // desde cambiarclave, y el id/rol no deberian cambiar nunca aca.
        existente.setNombre(funcionario.getNombre());
        existente.setTelefono(funcionario.getTelefono());
    }

    public void eliminarFuncionario(String id) throws ValidationException {
        Funcionario existente = buscarFuncionarioPorId(id);
        if (existente == null) {
            throw new ValidationException("No existe un funcionario con id " + id);
        }
        // TODO: coordinar con Andres -- Reserva.funcionario puede quedar
        // apuntando a un funcionario borrado. Falta chequear reservas activas.
        funcionarios.remove(existente);
    }

    // ---------- Categorias ----------

    public List<CategoriaRecurso> listarCategorias() {
        return new ArrayList<>(categorias);
    }

    public CategoriaRecurso buscarCategoriaPorId(String id) {
        for (CategoriaRecurso c : categorias) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public List<CategoriaRecurso> buscarCategoriasPorDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            return listarCategorias();
        }
        List<CategoriaRecurso> resultado = new ArrayList<>();
        for (CategoriaRecurso c : categorias) {
            if (contiene(c.getDescripcion(), descripcion)) {
                resultado.add(c);
            }
        }
        return resultado;
    }

    public void crearCategoria(CategoriaRecurso categoria) throws ValidationException {
        if (categoria == null) {
            throw new ValidationException("La categoria no puede ser nula.");
        }
        requireNonBlank(categoria.getDescripcion(), "La descripcion de la categoria es obligatoria.");
        // el id se autogenera aca, se ignora lo que traiga el objeto
        categoria.setId("CAT-" + String.format("%06d", ++categoriaSeq));
        categorias.add(categoria);
    }

    public void actualizarCategoria(CategoriaRecurso categoria) throws ValidationException {
        if (categoria == null) {
            throw new ValidationException("La categoria no puede ser nula.");
        }
        CategoriaRecurso existente = buscarCategoriaPorId(categoria.getId());
        if (existente == null) {
            throw new ValidationException("No existe una categoria con id " + categoria.getId());
        }
        requireNonBlank(categoria.getDescripcion(), "La descripcion de la categoria es obligatoria.");
        existente.setDescripcion(categoria.getDescripcion());
    }

    public void eliminarCategoria(String id) throws ValidationException {
        CategoriaRecurso existente = buscarCategoriaPorId(id);
        if (existente == null) {
            throw new ValidationException("No existe una categoria con id " + id);
        }
        for (Recurso r : recursos) {
            if (r.getCategoria() != null && id.equals(r.getCategoria().getId())) {
                throw new ValidationException("No se puede eliminar: hay recursos asociados a esta categoria.");
            }
        }
        categorias.remove(existente);
    }

    // ---------- Recursos ----------

    public List<Recurso> listarRecursos() {
        return new ArrayList<>(recursos);
    }

    public Recurso buscarRecursoPorId(String id) {
        for (Recurso r : recursos) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    public List<Recurso> buscarRecursosPorCategoria(CategoriaRecurso categoria) {
        if (categoria == null) {
            return listarRecursos();
        }
        List<Recurso> resultado = new ArrayList<>();
        for (Recurso r : recursos) {
            if (r.getCategoria() != null && r.getCategoria().getId().equals(categoria.getId())) {
                resultado.add(r);
            }
        }
        return resultado;
    }

    private void validarCategoriaExiste(CategoriaRecurso categoria) throws ValidationException {
        if (categoria == null || buscarCategoriaPorId(categoria.getId()) == null) {
            throw new ValidationException("La categoria seleccionada no existe.");
        }
    }

    public void crearRecurso(Recurso recurso) throws ValidationException {
        if (recurso == null) {
            throw new ValidationException("El recurso no puede ser nulo.");
        }
        requireNonBlank(recurso.getId(), "El id (numero de activo) del recurso es obligatorio.");
        requireNonBlank(recurso.getDescripcion(), "La descripcion del recurso es obligatoria.");
        validarCategoriaExiste(recurso.getCategoria());
        if (buscarRecursoPorId(recurso.getId()) != null) {
            throw new ValidationException("Ya existe un recurso con id " + recurso.getId());
        }
        recursos.add(recurso);
    }

    public void actualizarRecurso(Recurso recurso) throws ValidationException {
        if (recurso == null) {
            throw new ValidationException("El recurso no puede ser nulo.");
        }
        Recurso existente = buscarRecursoPorId(recurso.getId());
        if (existente == null) {
            throw new ValidationException("No existe un recurso con id " + recurso.getId());
        }
        requireNonBlank(recurso.getDescripcion(), "La descripcion del recurso es obligatoria.");
        validarCategoriaExiste(recurso.getCategoria());
        existente.setDescripcion(recurso.getDescripcion());
        existente.setCategoria(recurso.getCategoria());
    }

    public void eliminarRecurso(String id) throws ValidationException {
        Recurso existente = buscarRecursoPorId(id);
        if (existente == null) {
            throw new ValidationException("No existe un recurso con id " + id);
        }
        // TODO: coordinar con Andres -- Reserva.recursos puede referenciar un
        // recurso borrado. Falta chequear reservas activas.
        recursos.remove(existente);
    }
}
