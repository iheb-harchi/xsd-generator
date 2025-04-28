package org.paterna.rules.parser;

import org.paterna.rules.Condition;
import org.paterna.rules.rules.DynamicValueRule;
import org.paterna.rules.rules.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicValueRegelParser extends RuleParser<DynamicValueRule> {

//    public static Pattern MAIN_PATTERN = Pattern.compile(
//            "abhängig von ("+FIELD_PATTERN+");("+FIELD_PATTERN+ ")\\s*[:\\-]\\s*(.+)"
//            , Pattern.CASE_INSENSITIVE);
    private static final Pattern DATE_VALUE_PATTERN = Pattern.compile(
            "(\\d{2}\\.\\d{2}.\\d{4})\\s*-\\s*(\\d{2}\\.\\d{2}\\.\\d{4})\\s*[-->\\-]\s*([\\d,]+)"
    );

    public static Pattern MAIN_PATTERN = Pattern.compile(
            // Header-Teil
            "^abhängig\\s+von\\s+([A-Z]\\d+(?:\\.\\d+)?)\\s*;\\s*([A-Z]\\d+(?:\\.\\d+)?)[^\\n]*" +
            // Rate-Einträge (mit Zeilenumbrüchen)
            "(?:(\\d{2}\\.\\d{2}\\.\\d{4})\\s*-\\s*(\\d{2}\\.\\d{2}\\.\\d{4}|auf\\s+weiteres)\\s*[-→]\\s*>?\\s*([\\d,]+)(?:\\v+|$))+",
    Pattern.MULTILINE | Pattern.CASE_INSENSITIVE
);
    @Override
    public DynamicValueRule parse(String text){
        Matcher mainMatcher = MAIN_PATTERN.matcher(text);
        if(!mainMatcher.matches()){
            throw new IllegalArgumentException("Ungütiges Format "+ text);
        }

        Map<String, String> dateValueMap = parseDateValuePair(mainMatcher.group(3));
        return new DynamicValueRule(
                mainMatcher.group(1),
                mainMatcher.group(2),
                dateValueMap
        );
    }

    private Map<String, String> parseDateValuePair(String pairText) {
        Map<String, String> result = new HashMap<>();
        Matcher matcher = DATE_VALUE_PATTERN.matcher(pairText);
        while (matcher.find()){
            String startDate = matcher.group(1);
            String endDate = matcher.group(2);
            String value = matcher.group(3);
            String dateRange = startDate+ " - "+ endDate;
            result.put(dateRange, value.replace(",","."));
        }
        if(result.isEmpty()){
            throw new IllegalArgumentException("Die Datüme passen nicht!");
        }
        return result;

    }
}
