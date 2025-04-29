package org.paterna;

public class FormField {

	public static final String FIELD_PATTERN = "^[A-Za-z]\\d+(?:\\.\\d+)?$";

	private String id;
	private String name;
	private String text;
	private String type;
	private String condition;

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
		System.err.println(text);
		if (text == null || !text.matches(FIELD_PATTERN)) {
			this.text = id;
		} else {
			this.text = text;
		}

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
}
