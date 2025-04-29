package org.paterna.rules.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.paterna.rules.rules.DynamicValueRule;

public class DynamicValueRegelParser extends RuleParser<DynamicValueRule> {
	private static final Pattern HEADER_PATTERN = Pattern.compile(
			"^abhängig von\\s+([A-Za-z]\\d+(?:\\.\\d+)?)\\s*;\\s*([A-Za-z]\\d+(?:\\.\\d+)?).*",
			Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.DOTALL);

	Pattern DATE_VALUE_PATTERN = Pattern.compile(
			"(\\d{2}\\.\\d{2}\\.\\d{4})\\s*-\\s*(\\d{2}\\.\\d{2}\\.\\d{4})?\\s*-->?\\s*([\\d,]+)?|auf weiteres\\s*-->?\\s*([\\d,]+)");

	@Override
	public DynamicValueRule parse(String text) {
		Matcher headerMatcher = HEADER_PATTERN.matcher(text);
		if (!headerMatcher.find()) {
			throw new IllegalArgumentException("Ungütiges Format " + text);
		}

		Map<String, String> dateValueMap = parseDateValuePair(text);
		return new DynamicValueRule(headerMatcher.group(1), headerMatcher.group(2), dateValueMap);
	}

	private Map<String, String> parseDateValuePair(String pairText) {
		Map<String, String> result = new HashMap<>();
		Matcher matcher = DATE_VALUE_PATTERN.matcher(pairText);
		String lastStartDate = null;

		while (matcher.find()) {
			if (matcher.group(4) != null) {
				if (lastStartDate == null) {
					throw new IllegalArgumentException("'auf weiteres' ohne vorheriges Startdatum");
				}
				result.put(lastStartDate + " - 31.12.2999", matcher.group(4).replace(",", "."));
			} else {
				String startDate = matcher.group(1);
				String endDate = matcher.group(2);
				String value = matcher.group(3);

				lastStartDate = startDate;

				if (endDate == null || endDate.isEmpty()) {
					endDate = startDate;
				}

				result.put(startDate + " - " + endDate, value.replace(",", "."));
			}
		}

		if (result.isEmpty()) {
			throw new IllegalArgumentException("Keine gültigen Datums-Wert-Paare gefunden");
		}

		return result;
	}

	@Override
	public boolean matchesPattern(String text) {
		return HEADER_PATTERN.matcher(text).find() && DATE_VALUE_PATTERN.matcher(text).find();
	}
}
