package org.paterna;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.paterna.rules.rules.Rule;
import org.paterna.rules.rules.RuleMatcher;
import org.paterna.types.TypeParser;

public class XsdGenerator {

	private RuleMatcher ruleMatcher;
	private TypeParser typeParser;

	public XsdGenerator() {
		this.ruleMatcher = new RuleMatcher();
		this.typeParser = new TypeParser();
	}

	public void generateXSD(List<FormField> rows, String filePath) throws IOException {
		FileWriter writer = new FileWriter(filePath);
		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		writer.write("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\">\n");

		for (FormField row : rows) {
			writer.write("  <xs:element name=\"" + row.getText() + "\">\n");
			writer.write("    <xs:annotation>\n");
			writer.write("      <xs:documentation>\n");
//			writer.write("        Text: " + row.getText() + "\n");
//			writer.write("        Type: " + row.getType() + "\n");
//			writer.write("        Wertebereich: " + row.getWerteBereich() + "\n");

			List<Rule> matchedRules = ruleMatcher.matchRules(row);
			for (Rule rule : matchedRules) {
				writer.write(" <xs:assert test=\"" + rule.toXsdAssert() + "\"/>\n");
			}

			writer.write("      </xs:documentation>\n");
			writer.write("    </xs:annotation>\n");
			writer.write("  </xs:element>\n");
		}

		writer.write("</xs:schema>");
		writer.close();
	}

	public void generateXSD2(List<FormField> rows, String filePath) throws IOException {
		try (FileWriter writer = new FileWriter(filePath)) {
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			writer.write("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" ");
			writer.write("xmlns:vc=\"http://www.w3.org/2007/XMLSchema-versioning\" ");
			writer.write("elementFormDefault=\"qualified\" vc:minVersion=\"1.1\">\n");

			// Beginne mit dem Erstellen des Haupt-Elements
			writer.write("  <xs:element name=\"Datensatz\">\n");
			writer.write("    <xs:complexType>\n");
			writer.write("      <xs:sequence>\n");

			// Iteriere über alle Formfelder und füge sie als xs:element hinzu
			for (FormField row : rows) {
				writer.write(
						"        <xs:element name=\"" + row.getText() + "\" type=\"xs:string\" minOccurs=\"0\"/>\n");
			}

			writer.write("      </xs:sequence>\n");

			// Hier werden die Bedingungen (xs:assert) nach der sequence hinzugefügt
			for (FormField row : rows) {
				List<Rule> matchedRules = ruleMatcher.matchRules(row);
				for (Rule rule : matchedRules) {
					// Sicherstellen, dass die `actualField` an `toXsdAssert` übergeben wird
					writer.write("      <xs:assert test=\"" + rule.toXsdAssert(row.getText()) + "\"/>\n");
				}
			}

			writer.write("    </xs:complexType>\n");
			writer.write("  </xs:element>\n");

			writer.write("</xs:schema>");
		}
	}


//	public void generateXSD(List<FormField> fields, String filePath) throws IOException {
//		try (FileWriter writer = new FileWriter(filePath)) {
//			writeXsdHeader(writer);
//			writeXsdElements(writer, fields);
//			writeXsdFooter(writer);
//		}
//	}
//
//	private void writeXsdHeader(FileWriter writer) throws IOException {
//		writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
//		writer.write("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\">\n");
//	}
//
//	private void writeXsdElements(FileWriter writer, List<FormField> fields) throws IOException {
//		for (FormField field : fields) {
//			writer.write(buildXsdElement(field));
//		}
//	}
//
//	private String buildXsdElement(FormField field) {
//		StringBuilder element = new StringBuilder();
//		element.append("  <xs:element name=\"").append(field.getText()).append("\">\n");
//
//		if (hasRulesOrType(field)) {
//			element.append("    <xs:complexType>\n");
//			element.append("      <xs:sequence/>\n");
//			element.append(buildTypeRestriction(field));
//			element.append(buildRuleAssertions(field));
//			element.append("    </xs:complexType>\n");
//		}
//
//		element.append("  </xs:element>\n");
//		return element.toString();
//	}
//
//	private boolean hasRulesOrType(FormField field) {
//		return !ruleMatcher.matchRules(field).isEmpty() || (field.getType() != null && !field.getType().isEmpty());
//	}
//
//	private String buildTypeRestriction(FormField field) {
//		String typeRestriction = typeParser.parseToRestriction(field.getType(), field.getCondition());
//		return typeRestriction.isEmpty() ? ""
//				: "      <xs:simpleContent>\n" + "        <xs:restriction base=\"xs:string\">\n" + "          "
//						+ typeRestriction + "\n" + "        </xs:restriction>\n" + "      </xs:simpleContent>\n";
//	}
//
//	private String buildRuleAssertions(FormField field) {
//		return ruleMatcher.matchRules(field).stream()
//				.map(rule -> "      <xs:assert test=\"" + rule.toXsdAssert() + "\"/>\n").collect(Collectors.joining());
//	}
//
//	private void writeXsdFooter(FileWriter writer) throws IOException {
//		writer.write("</xs:schema>");
//	}
}
