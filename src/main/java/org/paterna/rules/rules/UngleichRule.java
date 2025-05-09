package org.paterna.rules.rules;

public class UngleichRule extends Rule {

	private String feldName;
	private String vergleichWert;

	public UngleichRule(String feldName, String vergleichWert) {
		this.feldName = feldName;
		this.vergleichWert = vergleichWert;
	}

	@Override
	public String toXsdAssert() {
		return String.format("if (%s = '%s') then false() else true()", feldName, vergleichWert);
	}

	public String getFeldName() {
		return feldName;
	}

	public void setFeldName(String feldName) {
		this.feldName = feldName;
	}

	public String getVergleichWert() {
		return vergleichWert;
	}

	public void setVergleichWert(String vergleichWert) {
		this.vergleichWert = vergleichWert;
	}

	@Override
	public String toXsdAssert(String actualElement) {
		if (feldName == null || vergleichWert == null || actualElement == null) {
			throw new IllegalStateException(
					"Feldname, Vergleichswert oder aktuelles Element sind nicht korrekt extrahiert.");
		}

		// Wenn das andere Feld den Vergleichswert nicht hat, wird das aktuelle Feld
		// pflichtig gemacht.
		return String.format("if (%s = '%s') then %s != '' else true()", feldName, vergleichWert, actualElement);
	}

}