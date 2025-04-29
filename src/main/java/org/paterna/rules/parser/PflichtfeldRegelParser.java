package org.paterna.rules.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.paterna.rules.rules.PflichtfeldRegel;

public class PflichtfeldRegelParser extends RuleParser<PflichtfeldRegel> {

	// Regex für das Parsen der Regel: "Pflichtfeld wenn ID C3.2 = ... und der
	// Gesamtbetrag in C65 kleiner 0"
	private static final Pattern PATTERN = Pattern.compile(
			"Nur anzeigen und Pflichtfeld wenn ID\\s([A-Za-z]\\d+(?:\\.\\d+)?)\\s*=\\s*\"([^\"]+)\"\\s+und\\s+der\\s+Gesamtbetrag\\s+in\\s([A-Za-z]\\d+(?:\\.\\d+)?)\\s+kleiner\\s+([-\\d\\.]+)\\s+ist,\\s+sonst\\s+nicht\\s+anzeigen\\.",
			Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.DOTALL);

	@Override
	public PflichtfeldRegel parse(String text) {
		// Matcher für den Text
		Matcher matcher = PATTERN.matcher(text);
		if (!matcher.find()) {
			throw new IllegalArgumentException("Ungültiges Format: " + text);
		}

		// Extrahieren von Feldnamen, Vergleichswerten und Bedingungen
		String feldName1 = matcher.group(1); // ID C3.2
		String wert1 = matcher.group(2); // Steuerberichtigung einer Steuermeldung ab.
		String feldName2 = matcher.group(3); // C65
		String wert2 = matcher.group(4); // 0 (kleiner als)

		// Erstellen und Rückgabe der Rule
		return new PflichtfeldRegel(feldName1, wert1, feldName2, Integer.parseInt(wert2.trim()));
	}

	@Override
	public boolean matchesPattern(String text) {
		// Überprüft, ob der Text mit dem definierten Pattern übereinstimmt
		return PATTERN.matcher(text).find();
	}

}
