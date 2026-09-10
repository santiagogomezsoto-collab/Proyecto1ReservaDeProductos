package data;

/**
 * Traduce LocalDate <-> String para que JAXB lo pueda guardar en el XML
 * (JAXB no sabe manejar LocalDate de forma nativa).
 *
 * TODO: implementar extendiendo javax.xml.bind.annotation.adapters.XmlAdapter<String, LocalDate>
 * cuando se agregue la dependencia de JAXB al pom.xml.
 */
public class LocalDateAdapter {
}
