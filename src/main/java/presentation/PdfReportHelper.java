package presentation;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Helper compartido para armar los reportes PDF de cualquier modulo (no solo
 * los mios). La idea es no repetir el mismo codigo de iText en cada Controller:
 * cada modulo solo pasa su titulo, el nombre del archivo destino y su propio
 * TableModel (ya sabe cuales son sus columnas y como sacar cada valor).
 *
 * Sigue el mismo patron que la plantilla que dio el profesor (PdfWriter,
 * PdfDocument, Document, Table, Cell, y un metodo que abre el pdf generado
 * con Desktop). No incluye la imagen de logo del ejemplo porque el proyecto
 * todavia no tiene una carpeta resources con ninguna imagen -- si el dia de
 * mañana se agrega un logo, aca es donde habria que sumarlo (una Image mas
 * en la tabla de encabezado, igual que en el ejemplo).
 */
public class PdfReportHelper {

    private PdfReportHelper() {
    }

    public static <E> void generarPdf(String titulo, String destino, AbstractTableModel<E> tabla) throws IOException {
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        PdfWriter writer = new PdfWriter(destino);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.setMargins(20, 20, 20, 20);

        Table header = new Table(1);
        header.setWidth(400);
        header.setHorizontalAlignment(HorizontalAlignment.CENTER);
        header.addCell(getCell(new Paragraph(titulo).setFont(font).setBold(), TextAlignment.CENTER, false));
        document.add(header);

        String[] columnas = tabla.getColumnNames();
        Table datos = new Table(UnitValue.createPercentArray(columnas.length)).useAllAvailableWidth();
        for (String columna : columnas) {
            datos.addHeaderCell(getCell(new Paragraph(columna).setFont(font).setBold(), TextAlignment.CENTER, true));
        }
        for (E item : tabla.getItems()) {
            for (int i = 0; i < columnas.length; i++) {
                Object valor = tabla.getValueAt(item, i);
                datos.addCell(getCell(new Paragraph(valor != null ? valor.toString() : ""), TextAlignment.LEFT, true));
            }
        }
        document.add(datos);
        document.close();
    }

    public static void abrirPdf(String path) {
        try {
            File pdfFile = new File(path);
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println("AWT Desktop is not supported on this platform.");
                }
            } else {
                System.out.println("The target PDF file does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Cell getCell(Paragraph paragraph, TextAlignment alignment, boolean hasBorder) {
        Cell cell = new Cell().add(paragraph);
        cell.setPadding(2);
        cell.setTextAlignment(alignment);
        if (!hasBorder) {
            cell.setBorder(Border.NO_BORDER);
        }
        return cell;
    }
}
