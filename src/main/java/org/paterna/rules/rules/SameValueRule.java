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
		return String.format("<xs:element name=\"geburtsdatum\" type=\"xs:date\">" + "<xs:annotation>" + "<xs:appinfo>"
				+ "<xs:value>ref:%s</xs:value>" + // Hier wird der Wert auf das Referenzfeld (z.B. C41) gesetzt
				"</xs:appinfo>" + "</xs:annotation>" + "</xs:element>", referenceField // Der Wert des Referenzfeldes
		);
	}
}
