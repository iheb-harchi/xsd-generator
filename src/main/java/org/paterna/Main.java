package org.paterna;

import org.paterna.rules.parser.DynamicValueRegelParser;
import org.paterna.rules.parser.EntwederOderRegelParser;
import org.paterna.rules.rules.DynamicValueRule;
import org.paterna.rules.rules.EntwederOderRule;

public class Main {
    public static void main(String[] args) {

//        try {
//            List<FormField> rows = ExcelReader.parseExcel("src/main/resources/formular.xlsx");
//            XsdGenerator.generateXSD(rows, "src/main/resources/generated.xsd");
//
//            List<String> conditions =  rows.stream().map(a ->  a.getCondition())
//                    .filter(c -> !c.equals("") && null != c).collect(Collectors.toUnmodifiableList());
//            conditions.forEach(System.out::println);
//            System.out.println("-------------------------------------"+conditions.size()+"--------");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//

        EntwederOderRegelParser entwederOderRegelParser = new EntwederOderRegelParser();
        EntwederOderRule entwederOderRule = entwederOderRegelParser.parse("Entweder C2.3 oder C2.5 ist Pflichtfeld");
        System.out.println(entwederOderRule.toXsdAssert());

        System.err.println("--------------");

        DynamicValueRegelParser dynamicValueRegelParser = new DynamicValueRegelParser();
        DynamicValueRule dynamicValueRule = dynamicValueRegelParser.parse(
                "abhängig von C5;C6 ist der in der Zeitspanne gültige Steuersatz zuwählen\n" +
                        "\n" +
                        "01.01.2012 - 31.12.2015 --> 7,5\n" +
                        "01.01.2016 - 31.12.2016 --> 7,38\n" +
                        "01.01.2017 - 31.12.2017 --> 7,47\n" +
                        "01.01.2018 - 31.12.2018 --> 7,46\n" +
                        "01.01.2019 - 31.12.2019 --> 7,38\n" +
                        "01.01.2020 - 31.03.2020 --> 7,37\n" +
                        "01.04.2020 - 31.12.2020 --> 12,9\n" +
                        "01.01.2021 - 31.12.2021 --> 12,88\n" +
                        "01.01.2022 - 31.12.2022  --> 12,77\n" +
                        "01.01.2023 - 31.12.2023  --> 12,73\n" +
                        "01.01.2024 - 30.04.2024  --> 12,48\n" +
                        "01.05.2024 -\n" +
                        "auf weiteres --> 15,53");
    }
}