package com.proyectosj4sas.app.reportes;

import org.springframework.stereotype.Component;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;

@Component("/vistas/empresas/listar")
public class PdfReporte extends AbstractPdfView {

	/*
	 * @Autowired private ObraServicioImpl obraServicio;
	 */

	private Document document;

	/**
	 *
	 */
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.init(document);
		// this.crearEncabezado();
		// this.listadoObras();
	}

	private void init(Document document) {
		this.document = document;
		this.document.setPageSize(PageSize.LETTER.rotate());
		this.document.setMargins(-20, -20, 30, 20);
		this.document.open();

		try {
			PdfWriter.getInstance(document, new FileOutputStream("Images.pdf"));

			document.open();

			// document.add(new Paragraph("Leoncito"));
			Image grayscaledPng = Image.getInstance("lion.png");
			grayscaledPng.scalePercent(2);
			document.add(grayscaledPng);

		} catch (DocumentException | IOException de) {
			System.err.println(de.getMessage());
		}
	}

	/*
	 * private void crearEncabezado() { Font fuenteTitulo =
	 * FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.BLUE);
	 * 
	 * PdfPTable tablaTitulo = new PdfPTable(1); PdfPCell celda = null;
	 * 
	 * celda = new PdfPCell(new Phrase("LISTADO GENERAL DE CLIENTES",
	 * fuenteTitulo)); celda.setBorder(0); celda.setBackgroundColor(new Color(40,
	 * 190, 138)); celda.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
	 * celda.setVerticalAlignment(PdfPCell.ALIGN_CENTER); celda.setPadding(30);
	 * 
	 * tablaTitulo.addCell(celda); tablaTitulo.setSpacingAfter(30);
	 * document.add(tablaTitulo); }
	 */

	/*
	 * private void listadoObras() {
	 * 
	 * List<Obra> listadoClientes = obraServicio.findAll(); PdfPCell celda = null;
	 * 
	 * Font fuenteTituloColumnas = FontFactory.getFont(FontFactory.HELVETICA_BOLD,
	 * 12, Color.BLUE); Font fuenteDataCeldas =
	 * FontFactory.getFont(FontFactory.COURIER, 10, Color.BLACK);
	 * 
	 * Tabla Para Mostrar Listado de Clientes PdfPTable tablaClientes = new
	 * PdfPTable(3 ); tablaClientes.setWidths(new float[] { 0.8f, 2f, 2f });
	 * 
	 * celda = new PdfPCell(new Phrase("ID", fuenteTituloColumnas));
	 * celda.setBackgroundColor(Color.lightGray);
	 * celda.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * celda.setVerticalAlignment(Element.ALIGN_CENTER); celda.setPadding(10);
	 * tablaClientes.addCell(celda); System.out.println(listadoClientes.size());
	 * Bucle For, mostrar todos los datos de los clientes celda = new PdfPCell(new
	 * Phrase("CIUDAD", fuenteTituloColumnas));
	 * celda.setBackgroundColor(Color.lightGray);
	 * celda.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * celda.setVerticalAlignment(Element.ALIGN_CENTER); celda.setPadding(10);
	 * tablaClientes.addCell(celda); celda = new PdfPCell(new Phrase("CIUDAD",
	 * fuenteTituloColumnas)); celda.setBackgroundColor(Color.lightGray);
	 * celda.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * celda.setVerticalAlignment(Element.ALIGN_CENTER); celda.setPadding(10);
	 * tablaClientes.addCell(celda); celda = new PdfPCell(new Phrase("CIUDAD",
	 * fuenteTituloColumnas)); celda.setBackgroundColor(Color.lightGray);
	 * celda.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * celda.setVerticalAlignment(Element.ALIGN_CENTER); celda.setPadding(10);
	 * tablaClientes.addCell(celda);
	 * 
	 * for (Obra cliente : listadoClientes) { celda = new PdfPCell(new
	 * Phrase(cliente.getId().toString(), fuenteDataCeldas)); celda.setPadding(5);
	 * tablaClientes.addCell(celda);
	 * 
	 * celda = new PdfPCell(new Phrase(cliente.getNombre(), fuenteDataCeldas));
	 * celda.setPadding(5); tablaClientes.addCell(celda);
	 * 
	 * celda = new PdfPCell(new Phrase(cliente.getEstado().toString(),
	 * fuenteDataCeldas)); celda.setPadding(5); tablaClientes.addCell(celda);
	 * System.out.println(listadoClientes.size()); }
	 * this.document.add(tablaClientes); }
	 */

}