package org.paterna.rules.rules;

import java.util.List;

public class EntwederOderRule extends Rule {
    private List<String> alternativeFields;

    public EntwederOderRule(List<String> alternativeFields) {
        this.alternativeFields = alternativeFields;
    }

    @Override
    public String toXsdAssert() {
        return alternativeFields.stream()
                .map(field -> field + " != ''")
                .reduce((a, b) -> a + " or " + b)
                .orElse("true()");
    }
}
