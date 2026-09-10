package logic;

/**
 * Excepcion checked para errores de negocio/validacion (id duplicado, campo
 * vacio, referencia inexistente, etc.).
 *
 * Convencion para todo el equipo: cualquier metodo de Service que MODIFICA
 * datos (crear/actualizar/eliminar) puede lanzar esto. Los metodos de
 * listar/buscar nunca la lanzan, devuelven listas vacias en vez de fallar.
 */
public class ValidationException extends Exception {

    public ValidationException(String mensaje) {
        super(mensaje);
    }

    public ValidationException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
