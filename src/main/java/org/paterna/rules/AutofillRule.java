package org.paterna.rules;

import org.paterna.rules.rules.Rule;

public class AutofillRule extends Rule {
    private String sourceField;

    @Override
    public String toXsdAssert() {
        return "";
    }
}
