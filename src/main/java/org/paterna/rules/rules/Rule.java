package org.paterna.rules.rules;

import org.paterna.rules.Condition;

public abstract class Rule {

    protected String tqrgetField;
    protected Condition condition;


    public abstract String toXsdAssert();
}
