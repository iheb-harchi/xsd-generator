package org.paterna.rules.rules;

public abstract class Rule {

	protected String tqrgetField;

	public abstract String toXsdAssert();

	public abstract String toXsdAssert(String actualElement);
}
