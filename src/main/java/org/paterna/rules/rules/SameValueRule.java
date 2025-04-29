package org.paterna.rules.rules;

public class SameValueRule extends Rule {

	private String referenceField;

	// Konstruktor, der das Referenz-Datumfeld entgegennimmt
	public SameValueRule(String referenceField) {
		this.referenceField = referenceField;
	}

	@Override
	public String toXsdAssert() {
		if (referenceField == null) {
			throw new IllegalStateException("Das Referenz-Datumfeld wurde nicht korrekt extrahiert.");
		}

		// XSD-Element erstellen und den gleichen Wert wie das Referenzdatum zuweisen
		return String.format("$value = ../%s", referenceField);
	}

	@Override
	public String toXsdAssert(String actualElement) {
		if (referenceField == null) {
			throw new IllegalStateException("Das Referenz-Datumfeld wurde nicht korrekt extrahiert.");
		}
		// Hier wird das "actualElement" verwendet, um eine Vergleichsbedingung zu
		// erstellen.
		return String.format("%s = ../%s", actualElement, referenceField);
	}
}
