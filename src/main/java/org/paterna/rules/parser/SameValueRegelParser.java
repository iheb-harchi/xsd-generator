package org.paterna.rules.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.paterna.rules.rules.SameValueRule;

public class SameValueRegelParser extends RuleParser<SameValueRule> {

	// Regex für das Parsen der "muss gleichen Wert wie C41 enthalten"-Regel
	public static final Pattern PATTERN = Pattern.compile("muss gleichen Wert wie ([A-Za-z]\\d+(?:\\.\\d+)?) enthalten",
			Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.DOTALL);

	@Override
	public SameValueRule parse(String text) {
		// Matcher auf den Eingabetext anwenden
		Matcher matcher = PATTERN.matcher(text);
		if (!matcher.find()) {
			throw new IllegalArgumentException("Ungültiges Format: " + text);
		}

		// Referenz-Datumfeld extrahieren
		String referenceField = matcher.group(1);

		// Rückgabe einer neuen SameValueRule mit dem extrahierten Referenz-Datumfeld
		return new SameValueRule(referenceField);
	}

	@Override
	public boolean matchesPattern(String text) {
		// Überprüfen, ob der Text dem Muster entspricht
		return PATTERN.matcher(text).find();
	}
}
