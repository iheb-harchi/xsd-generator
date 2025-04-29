package org.paterna.rules.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.paterna.rules.rules.AutoFillRule;

public class AutoFillRegelParser extends RuleParser<AutoFillRule> {

	private static final Pattern PATTERN = Pattern
			.compile("automatisch aus Datumfeld ([A-Za-z]\\d+(?:\\.\\d+)?) befüllen");

	@Override
	public AutoFillRule parse(String text) {
		Matcher matcher = PATTERN.matcher(text);
		if (!matcher.find()) {
			throw new IllegalArgumentException("Auto Fill Regel ungültig: " + text);
		}
		return new AutoFillRule(matcher.group(1));
	}

	@Override
	public boolean matchesPattern(String text) {
		return PATTERN.matcher(text).find();
	}
}
