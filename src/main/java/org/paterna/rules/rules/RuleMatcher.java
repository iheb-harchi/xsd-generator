package org.paterna.rules.rules;

import java.util.ArrayList;
import java.util.List;

import org.paterna.FormField;
import org.paterna.rules.parser.AutoFillRegelParser;
import org.paterna.rules.parser.DynamicValueRegelParser;
import org.paterna.rules.parser.EntwederOderRegelParser;
import org.paterna.rules.parser.PflichtfeldRegelParser;
import org.paterna.rules.parser.RuleParser;
import org.paterna.rules.parser.SameValueRegelParser;
import org.paterna.rules.parser.UngleichRegelParser;

public class RuleMatcher {

	private final List<RuleParser<? extends Rule>> parsers;

	public RuleMatcher() {
		this.parsers = List.of(new AutoFillRegelParser(), new DynamicValueRegelParser(), new EntwederOderRegelParser(),
				new PflichtfeldRegelParser(), new SameValueRegelParser(), new UngleichRegelParser());
	}

	public List<Rule> matchRules(FormField field) {
		String condition = field.getCondition();
		List<Rule> matchedRules = new ArrayList<>();

		if (condition == null || condition.isBlank()) {
			return matchedRules;
		}

		// Überprüfen der Condition mit mehreren Parsern
		for (RuleParser<? extends Rule> parser : parsers) {
			if (parser.matchesPattern(condition)) {
				try {
					matchedRules.add(parser.parse(condition)); // Alle passenden Regeln sammeln
				} catch (Exception e) {
					System.err.println("Fehler beim Parsen der Bedingung: " + e.getMessage());
				}
			}
		}
		return matchedRules;
	}
}
