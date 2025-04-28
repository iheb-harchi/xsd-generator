package org.paterna.rules;

import java.util.ArrayList;
import java.util.List;

public class Condition {
	private String field;
	private String operator;
	private String value;
	private List<Condition> orConditions = new ArrayList<>();
	private List<Condition> andConditions = new ArrayList<>();

	public Condition(String feld, String operator, String value, List<Condition> orConditions,
			List<Condition> andConditions) {
		this.field = feld;
		this.operator = operator;
		this.value = value;
		this.orConditions = orConditions;
		andConditions = andConditions;
	}

	public static Condition of(String field, String operator, String value) {
		return new Condition(field, operator, value, null, null);
	}

	public static Condition or(List<Condition> conditions) {
		return new Condition(null, null, null, conditions, null);
	}

	public static Condition and(List<Condition> conditions) {
		return new Condition(null, null, null, null, conditions);
	}

	public boolean isLogical() {
		return isNotNullOrEmpty(orConditions) || isNotNullOrEmpty(andConditions);
	}

	public String toXsdAssert() {
		if (isLogical()) {
			if (isNotNullOrEmpty(orConditions)) {
				return orConditions.stream().map(Condition::toXsdAssert).reduce((a, b) -> a + " or " + b)
						.orElse("true()");
			} else {
				return andConditions.stream().map(Condition::toXsdAssert).reduce((a, b) -> a + " and " + b)
						.orElse("true()");
			}
		} else {
			return switch (operator) {
			case "exists" -> field + " != ''";
			case "=" -> field + " = " + value;
			case "!=" -> field + " != " + value;
			default -> throw new IllegalArgumentException("inbekannter Operator: " + operator);
			};
		}
	}

	public boolean isNotNullOrEmpty(List<Condition> list) {
		return null != list && !list.isEmpty();
	}
}
