package org.paterna.rules.rules;

import java.util.regex.Pattern;

public class AutoFillRule extends Rule {
	private String dateField;
	private static final Pattern PATTERN = Pattern
			.compile("automatisch aus Datumfeld ([A-Za-z]\\\\d+(?:\\\\.\\\\d+)?) befüllen");

	public AutoFillRule(String field) {
		this.dateField = field;
	}

	@Override
	public String toXsdAssert() {
		if (dateField == null) {
			throw new IllegalStateException("Das Datumfeld wurde nicht korrekt extrahiert.");
		}

		// XSD-Element erstellen und das Datumfeld zuweisen
		return String.format("$value = $%s", dateField);
	}

}
