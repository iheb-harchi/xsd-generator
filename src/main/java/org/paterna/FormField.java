package org.paterna;

public class FormField {
    private String id;
    private String name;
    private String text;
    private String type;
    private String condition;
    private String werteBereich;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWerteBereich() {
        return werteBereich;
    }

    public void setWerteBereich(String werteBereich) {
        this.werteBereich = werteBereich;
    }
}
