package org.paterna;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {

		try {
			List<FormField> rows = ExcelReader.parseExcel("src/main/resources/formular.xlsx");
			rows.stream().map(a -> a.getType()).distinct().collect(Collectors.toList()).forEach(System.out::println);
			XsdGenerator generator = new XsdGenerator();
			generator.generateXSD2(rows, "src/main/resources/generated.xsd");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
