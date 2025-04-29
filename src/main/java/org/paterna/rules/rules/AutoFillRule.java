package org.paterna.rules.rules;

public class AutoFillRule extends Rule {
	private String dateField;

	public AutoFillRule(String field) {
		this.dateField = field;
	}

	@Override
	public String toXsdAssert() {
		if (dateField == null) {
			throw new IllegalStateException("Das Datumfeld wurde nicht korrekt extrahiert.");
		}

		// XSD-Element erstellen und das Datumfeld zuweisen
		return String.format("$value = ../%s", dateField);
	}

}
