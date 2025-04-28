package org.paterna;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

	public static List<FormField> parseExcel(String filePath) throws IOException {
		List<FormField> rows = new ArrayList<>();
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);

		boolean firstRow = true;
		for (Row row : sheet) {
			if (firstRow) {
				firstRow = false;
				continue;
			}
			FormField formField = new FormField();

			formField.setId(getCellValue(row.getCell(0)));
			formField.setText(getCellValue(row.getCell(5)));
			formField.setType(getCellValue(row.getCell(18)));
			formField.setWerteBereich(getCellValue(row.getCell(19)));
			formField.setCondition(getCellValue(row.getCell(14)));
			rows.add(formField);

		}

		workbook.close();
		fis.close();
		return rows;
	}

	private static String getCellValue(Cell cell) {
		return cell == null ? "" : String.valueOf(cell);
	}

}
