package org.paterna.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeParser {

	public String parseToRestriction(String type, String condition) {

		String typeToParse = (condition != null && !condition.isBlank()) ? condition : type;
		if (typeToParse == null || typeToParse.isBlank()) {
			return "";
		}

		// Handle numeric types
		if (typeToParse.matches("numerisch.*")) {
			return handleNumericTypes(typeToParse);
		}
		// Handle alphanumeric types
		else if (typeToParse.matches("alphanummerisch.*")) {
			return handleAlphanumericTypes(typeToParse);
		}
		// Handle special cases
		else if (typeToParse.contains("IBAN")) {
			return "<xs:pattern value=\"[A-Z]{2}[0-9]{2}[A-Z0-9]{11,30}\"/>";
		} else if (typeToParse.matches("Ja/Nein")) {
			return "<xs:pattern value=\"(Ja|Nein)\"/>";
		}

		return "";
	}

	private String handleNumericTypes(String type) {
		if (type.matches("numerisch\\s+genau\\s+\\d+")) {
			int digits = Integer.parseInt(type.replaceAll("\\D+", ""));
			return "<xs:totalDigits value=\"" + digits + "\"/>";
		} else if (type.matches("numerisch\\s+\\d+")) {
			int maxDigits = Integer.parseInt(type.replaceAll("\\D+", ""));
			return "<xs:maxInclusive value=\"" + ((int) Math.pow(10, maxDigits) - 1) + "\"/>";
		} else if (type.contains("Vorkommastellen") && type.contains("Nachkommastellen")) {
			return "<xs:totalDigits value=\"12\"/><xs:fractionDigits value=\"2\"/>";
		}
		return "";
	}

	private String handleAlphanumericTypes(String type) {
		int maxLength = extractMaxLength(type);
		String pattern = type.contains("Sonderzeichen") ? "[\\p{L}\\p{N}\\p{P}\\p{S}]+" : "[A-Za-z0-9]*";

		return "<xs:maxLength value=\"" + maxLength + "\"/>" + "<xs:pattern value=\"" + pattern + "\"/>";
	}

	private int extractMaxLength(String text) {
		Matcher m = Pattern.compile("0\\.\\.(\\d+)").matcher(text);
		return m.find() ? Integer.parseInt(m.group(1)) : 255;
	}
}
