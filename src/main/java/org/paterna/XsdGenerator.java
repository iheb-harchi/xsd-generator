package org.paterna;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XsdGenerator {

    public static void generateXSD(List<FormField> rows, String filePath) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" elementFormDefault=\"qualified\">\n");

        for (FormField row : rows) {
            writer.write(" <xs:element name=\"" + row.getId() + "\">\n");
            writer.write(" <xs:annotation>\n");
            writer.write(" <xs:documentation>\n");
            writer.write(" Text: " + row.getText() + "\n");
            writer.write(" Type: " + row.getType() + "\n");
            writer.write(" Wertebereich: " + row.getWerteBereich() + "\n");
            writer.write(" Condition: " + row.getCondition() + "\n");
            writer.write(" </xs:documentation>\n");
            writer.write(" </xs:annotation>\n");
            writer.write(" </xs:element>\n");
        }

        writer.write("</xs:schema>");
        writer.close();
    }

}
