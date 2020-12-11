package com.proyectosj4sas.app.reportes;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.proyectosj4sas.app.modelo.entidad.Obrero;

public class ObreroReporteExcel {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFFont font;

	private List<Obrero> obrerosSinArl;
	private List<Obrero> obrerosSinEps;
	private List<Obrero> obrerosSinAfp;

	public ObreroReporteExcel(List<Obrero> obrerosSinArl,List<Obrero> obrerosSinEps,List<Obrero> obrerosSinAfp) {
		this.obrerosSinArl = obrerosSinArl;
		this.obrerosSinEps = obrerosSinEps;
		this.obrerosSinAfp = obrerosSinAfp;
		this.workbook = new XSSFWorkbook();

		this.font = workbook.createFont();
		font.setFontHeight(11);
	}

	/**
	 * Agrega al principio de la hoja de excel la informacion acerca de la obra
	 * a la cual se le hace el reporte: Constructora a la cual pertenece, Representante de la obra,Nombre de la obra:
	 * @param nombreSheet, indica el nombre de la hoja de excel
	 */
	private void InformacionObra(String nombreSheet) {
		sheet = workbook.getSheet(nombreSheet);

		Row rowConstructora = sheet.createRow(1);
		Row rowObra = sheet.createRow(2);
		Row rowEncargado = sheet.createRow(3);
		
		CellStyle style = this.estiloBlancoAlineadoALaIzquierda();


		createCell(rowConstructora, 1, "CONSTRUCTORA:", style);
		createCell(rowObra, 1, "OBRA:", style);
		createCell(rowEncargado, 1, "ENCARGADO:", style);
		createCellSinAjusteDeTamanio(rowConstructora, 2, obrerosSinArl.get(0).getObra().getEmpresa().getNombre(),
				style);
		createCellSinAjusteDeTamanio(rowObra, 2, obrerosSinArl.get(0).getObra().getNombre(), style);
		createCellSinAjusteDeTamanio(rowEncargado, 2, obrerosSinArl.get(0).getObra().getRepresentante().getNombre(),
				style);
	}

	private void TableHeaderSinArl(String nombresheet) {
		sheet = workbook.createSheet(nombresheet);

		Row row = sheet.createRow(5);

		CellStyle style = this.estiloTableHeader();
		style.setFont(font);

		createCell(row, 0, "No", style);
		createCell(row, 1, "CEDULA", style);
		createCell(row, 2, "NOMBRE", style);

	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void createCellSinAjusteDeTamanio(Row row, int columnCount, Object value, CellStyle style ) {
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void TableBodySinArl() {

		int rowCount = 6;

		CellStyle styleNombre = this.estiloBlancoAlineadoALaIzquierda();
		CellStyle styleNombreOpaco = this.estiloOpacoAlineadoALaIzquierda();
		CellStyle styleCedula = this.estiloBlancoCentrado();
		CellStyle styleCedulaOpaco = this.estiloOpacoCentrado();

		int pos = 0;
		for (Obrero obrero : obrerosSinArl) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			if (pos % 2 != 0) {
				createCell(row, columnCount++, pos + 1, styleCedulaOpaco);
				createCell(row, columnCount++, obrero.getTrabajador().getCedula(), styleCedulaOpaco);
				createCell(row, columnCount++, obrero.getTrabajador().getNombre(), styleNombreOpaco);

			} else {
				createCell(row, columnCount++, pos + 1, styleCedula);
				createCell(row, columnCount++, obrero.getTrabajador().getCedula(), styleCedula);
				createCell(row, columnCount++, obrero.getTrabajador().getNombre(), styleNombre);

			}
			pos++;
		}

	}

	private void excelSinArl(String nombreSheet) {
		TableHeaderSinArl(nombreSheet);
		TableBodySinArl();
		InformacionObra(nombreSheet);
	}

	private void excelSinEps(String nombreSheet) {
		TableHeaderSinEps(nombreSheet);
		TableBodySinEps();
		InformacionObra(nombreSheet);
	}

	private CellStyle estiloBlancoCentrado() {
		CellStyle style = workbook.createCellStyle();
		style.setFont(this.font);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		return style;
	}

	private CellStyle estiloBlancoAlineadoALaIzquierda() {
		CellStyle style = workbook.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(this.font);
		style.setAlignment(HorizontalAlignment.LEFT);
		return style;
	}

	private CellStyle estiloOpacoAlineadoALaIzquierda() {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillBackgroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		style.setFont(this.font);
		style.setFillPattern(FillPatternType.BIG_SPOTS);
		return style;
	}
	private CellStyle estiloOpacoCentrado() {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFillBackgroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		style.setFont(this.font);
		style.setFillPattern(FillPatternType.BIG_SPOTS);
		return style;
	}
	
	private CellStyle estiloTableHeader() {
		CellStyle style = workbook.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);

		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);

		style.setBottomBorderColor(IndexedColors.ORANGE.getIndex());
		style.setTopBorderColor(IndexedColors.ORANGE.getIndex());
		style.setLeftBorderColor(IndexedColors.ORANGE.getIndex());
		style.setRightBorderColor(IndexedColors.ORANGE.getIndex());
		style.setFillPattern(FillPatternType.BIG_SPOTS);
		style.setFont(font);
		return style;
	}

	

	private void TableBodySinEps() {
		int rowCount = 6;

		CellStyle styleNombre = this.estiloBlancoAlineadoALaIzquierda();
		CellStyle styleNombreOpaco = this.estiloOpacoAlineadoALaIzquierda();
		CellStyle styleCedula = this.estiloBlancoCentrado();
		CellStyle styleCedulaOpaco = this.estiloOpacoCentrado();

		int pos = 0;
		for (Obrero obrero : obrerosSinEps) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			if (pos % 2 != 0) {
				createCell(row, columnCount++, pos + 1, styleCedulaOpaco);
				createCell(row, columnCount++, obrero.getTrabajador().getCedula(), styleCedulaOpaco);
				createCell(row, columnCount++, obrero.getTrabajador().getNombre(), styleNombreOpaco);

			} else {
				createCell(row, columnCount++, pos + 1, styleCedula);
				createCell(row, columnCount++, obrero.getTrabajador().getCedula(), styleCedula);
				createCell(row, columnCount++, obrero.getTrabajador().getNombre(), styleNombre);

			}
			pos++;
		}

	}

	private void TableHeaderSinEps(String nombreSheet) {
		sheet = workbook.createSheet(nombreSheet);

		Row row = sheet.createRow(5);

		CellStyle style = this.estiloTableHeader();

		createCell(row, 0, "No", style);
		createCell(row, 1, "CEDULA", style);
		createCell(row, 2, "NOMBRE", style);

	}

	private void excelSinAfp(String nombreSheet) {
		TableHeaderSinAfp(nombreSheet);
		TableBodySinAfp();
		InformacionObra(nombreSheet);
	}


	private void TableBodySinAfp() {
		int rowCount = 6;

		CellStyle styleNombre = this.estiloBlancoAlineadoALaIzquierda();
		CellStyle styleNombreOpaco = this.estiloOpacoAlineadoALaIzquierda();
		CellStyle styleCedula = this.estiloBlancoCentrado();
		CellStyle styleCedulaOpaco = this.estiloOpacoCentrado();

		int pos = 0;
		for (Obrero obrero : obrerosSinAfp) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			if (pos % 2 != 0) {
				createCell(row, columnCount++, pos + 1, styleCedulaOpaco);
				createCell(row, columnCount++, obrero.getTrabajador().getCedula(), styleCedulaOpaco);
				createCell(row, columnCount++, obrero.getTrabajador().getNombre(), styleNombreOpaco);

			} else {
				createCell(row, columnCount++, pos + 1, styleCedula);
				createCell(row, columnCount++, obrero.getTrabajador().getCedula(), styleCedula);
				createCell(row, columnCount++, obrero.getTrabajador().getNombre(), styleNombre);

			}
			pos++;
		}
		
	}
	
	

	private void TableHeaderSinAfp(String nombreSheet) {
		sheet = workbook.createSheet(nombreSheet);

		Row row = sheet.createRow(5);

		CellStyle style = this.estiloTableHeader();

		createCell(row, 0, "No", style);
		createCell(row, 1, "CEDULA", style);
		createCell(row, 2, "NOMBRE", style);		
	}

	public void export(HttpServletResponse response) throws IOException {

		this.excelSinArl("SIN ARL");
		this.excelSinEps("SIN EPS");
		this.excelSinAfp("SIN AFP");

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
	



}
