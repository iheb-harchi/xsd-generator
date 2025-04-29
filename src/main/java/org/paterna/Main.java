package org.paterna;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
//		DynamicValueRegelParser dynamicValueRegelParser = new DynamicValueRegelParser();
//		EntwederOderRegelParser entwederOderRegelParser = new EntwederOderRegelParser();
//		AutoFillRegelParser autoFillRegelParser = new AutoFillRegelParser();
//		SameValueRegelParser sameValueRegelParser = new SameValueRegelParser();
//		UngleichRegelParser ungleichRegelParser = new UngleichRegelParser();
//		PflichtfeldRegelParser pflichtfeldRegelParser = new PflichtfeldRegelParser();

		try {
			List<FormField> rows = ExcelReader.parseExcel("src/main/resources/formular.xlsx");
			rows.stream().map(a -> a.getType()).distinct().collect(Collectors.toList()).forEach(System.out::println);
			XsdGenerator generator = new XsdGenerator();
			generator.generateXSD(rows, "src/main/resources/generated.xsd");
		} catch (IOException e) {
			e.printStackTrace();
		}
//rows.forEach(a -> {
//	 if (a.getCondition() != null && a.getCondition().contains("Pflichtfeld wenn ID C3.2"))	{
//		 System.err.println(a.getCondition());
//	 }
//});
//List<String> conditions = rows.stream().map(a -> a.getCondition()).filter(c -> !c.equals("") && null != c &&
////					!(dynamicValueRegelParser.matchesPattern(c) || entwederOderRegelParser.matchesPattern(c)
////							|| autoFillRegelParser.matchesPattern(c) || sameValueRegelParser.matchesPattern(c)
////							|| ungleichRegelParser.matchesPattern(c) ||
//					pflichtfeldRegelParser.matchesPattern(c))
//
//
//					.collect(Collectors.toUnmodifiableList());
//conditions.forEach(a -> {
//	PflichtfeldRegel regel = pflichtfeldRegelParser.parse(a);
//	System.out.println(regel.toXsdAssert());
//
//});
//			System.out.println("-------------------------------------" + conditions.size() + "--------");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		String regelText = "Nur anzeigen und Pflichtfeld wenn ID C3.2 = \"Steuerberichtigung einer Steuermeldung ab.\" und der Gesamtbetrag in C65 kleiner 0 ist, sonst nicht anzeigen.";
//
//        // Erstelle den Parser
//        PflichtfeldRegelParser parser = new PflichtfeldRegelParser();
//
//        // Parsen der Regel
//        if (parser.matchesPattern(regelText)) {
//            PflichtfeldRegel regel = parser.parse(regelText);
//
//            // Ausgeben der extrahierten Werte
//            System.out.println("Feldname1: " + regel.getFeldName1());  // C3.2
//            System.out.println("Wert1: " + regel.getWert1());            // Steuerberichtigung einer Steuermeldung ab.
//            System.out.println("Feldname2: " + regel.getFeldName2());    // C65
//            System.out.println("Wert2: " + regel.getWert2());            // 0
//        } else {
//            System.out.println("Die Regel entspricht nicht dem erwarteten Format.");
//        }
//    }

	}

}
