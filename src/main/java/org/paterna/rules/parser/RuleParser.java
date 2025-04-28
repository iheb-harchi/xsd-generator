package org.paterna.rules.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RuleParser<T> {
    protected static final String FIELD_PATTERN = "[A-Za-z]\\d+(?:\\.\\d+)?";

    public abstract T parse(String text) throws IllegalArgumentException;

    protected String extractField(String text, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1) : null;
    }

}
