package org.paterna.rules.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.paterna.rules.rules.UngleichRule;

public class UngleichRegelParser extends RuleParser<UngleichRule> {

	// Regex für das Parsen der Regel: "Wenn C15 ungleich Deutschland"
	private static final Pattern PATTERN = Pattern.compile(
			"Wenn\\s([A-Za-z]\\d+(?:\\.\\d+)?)\\sungleich\\s+([A-Za-z]+)\\s*,\\sdann\\skein\\sPflichtfeld",
			Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.DOTALL);

	@Override
	public UngleichRule parse(String text) {
		// Matcher für den Text
		Matcher matcher = PATTERN.matcher(text);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Ungültiges Format: " + text);
		}

		// Extrahieren von Feldnamen und Vergleichswert
		String feldName = matcher.group(1);
		String wert = matcher.group(2);

		// Erstellen und Rückgabe der Rule
		return new UngleichRule(feldName, wert);
	}

	@Override
	public boolean matchesPattern(String text) {
		// Überprüft, ob der Text mit dem definierten Pattern übereinstimmt
		return PATTERN.matcher(text).find();
	}
}
