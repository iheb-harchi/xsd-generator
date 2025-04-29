package org.paterna.rules.rules;

public class PflichtfeldRegel extends Rule {

	private String feldName1; // ID C3.2
	private String wert1; // Steuerberichtigung einer Steuermeldung ab.
	private String feldName2; // C65
	private int wert2; // 0

	public PflichtfeldRegel(String feldName1, String wert1, String feldName2, int wert2) {
		this.feldName1 = feldName1;
		this.wert1 = wert1;
		this.feldName2 = feldName2;
		this.wert2 = wert2;
	}

	@Override
	public String toXsdAssert() {
		return String.format("if (%s = '%s' and %s &lt; %d) then true() else false()", feldName1, wert1, feldName2,
				wert2);
	}

	// Getter und Setter (optional)
	public String getFeldName1() {
		return feldName1;
	}

	public String getWert1() {
		return wert1;
	}

	public String getFeldName2() {
		return feldName2;
	}

	public int getWert2() {
		return wert2;
	}

	@Override

	    public String toXsdAssert(String actualElement) {
	        if (actualElement == null || actualElement.isEmpty()) {
	            throw new IllegalArgumentException("Das aktuelle Element darf nicht null oder leer sein.");
	        }

	        // Dynamisches XSD-Assert unter Verwendung des aktuellen Elements
	        return String.format("if (%s = '%s' and %s &lt; %d) then %s else false()", feldName1, wert1, feldName2, wert2, actualElement);
	    }
}
