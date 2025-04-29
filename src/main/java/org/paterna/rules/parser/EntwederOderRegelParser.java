package org.paterna.rules.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.paterna.rules.rules.EntwederOderRule;

public class EntwederOderRegelParser extends RuleParser<EntwederOderRule> {

	private static final Pattern PATTERN = Pattern.compile(
			"^Entweder (" + FIELD_PATTERN + ") oder (" + FIELD_PATTERN + ") (?:ist|sind) (?:Pflichtfeld|Pflichtfelder)",
			Pattern.CASE_INSENSITIVE);

	@Override
	public EntwederOderRule parse(String text) {
		Matcher matcher = PATTERN.matcher(text);
		if (!matcher.find()) {
			throw new IllegalArgumentException("ENtwerder Oder Regel ungültig!");
		}
		return new EntwederOderRule(List.of(matcher.group(1), matcher.group(2)));
	}

	@Override
	public boolean matchesPattern(String text) {
		return PATTERN.matcher(text).find();
	}
}
