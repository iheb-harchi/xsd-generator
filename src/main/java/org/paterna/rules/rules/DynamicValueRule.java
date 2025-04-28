package org.paterna.rules.rules;

import java.util.Map;

public class DynamicValueRule extends Rule {
    private String dateField;
    private String valueField;
    private Map<String, String> dateValueMap;

    public DynamicValueRule(String dateField, String valueField, Map<String, String> dateValueMap) {
        this.dateField = dateField;
        this.valueField = valueField;
        this.dateValueMap = dateValueMap;
    }

    @Override
    public String toXsdAssert(){
        StringBuilder sb = new StringBuilder();
        dateValueMap.forEach((range, value) -> {
            String[] dates = range.split(" - ");
            sb.append(String.format(
                    "if (%s >= xs:date('%s') and %s <= xs:date('%s')) then $value = '%s' else ",
                    dateField, convertToXSDate(dates[0]),
                    dateField, convertToXSDate(dates[1]),
                    valueField, value

            )
            );
        });
        sb.append("true();");
        return sb.toString();
    }

    private String convertToXSDate(String date) {
        String[] parts = date.split("\\.");
        return parts[2]+"-"+parts[1]+"-"+parts[0];
    }
}
