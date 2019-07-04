package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

XSSFWorkbook wb;
XSSFSheet sheet;

public ReadExcel(String file) throws IOException {
	File rutaArchivo = new File(file);
	@SuppressWarnings({"unused", "resource"})
	FileInputStream src = new FileInputStream(rutaArchivo);
	wb = new XSSFWorkbook(file);
}

public String getData(String sheetname, int row, int column) {
	sheet = wb.getSheet(sheetname);
	DataFormatter formatter = new DataFormatter();
	String data = formatter.formatCellValue(sheet.getRow(row).getCell(column));
	return data;
}
}