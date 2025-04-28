package org.paterna.rules.rules;

import java.util.Map;

public class DynamicValueRule extends Rule {
	private String startDateField;
	private String endDateField;

	private Map<String, String> dateValueMap;

	public DynamicValueRule(String startDateField, String endDateField, Map<String, String> dateValueMap) {
		this.startDateField = startDateField;
		this.endDateField = endDateField;
		this.dateValueMap = dateValueMap;
	}

	@Override
	public String toXsdAssert() {
		StringBuilder sb = new StringBuilder();
		dateValueMap.forEach((range, value) -> {
			String[] dates = range.split(" - ");
			sb.append(String.format("if (%s &gt;= xs:date('%s') and %s &lt;= xs:date('%s')) then $value = '%s' else ",
					startDateField, convertToXSDate(dates[0]), endDateField, convertToXSDate(dates[1]), value

			));
		});
		sb.append("true();");
		return sb.toString();
	}

	private String convertToXSDate(String date) {
		String[] parts = date.split("\\.");
		return parts[2] + "-" + parts[1] + "-" + parts[0];
	}
}
