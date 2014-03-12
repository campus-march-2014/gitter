package github.models;

public enum DataPropertyType {
    STRING("string"),
    INT("int");

    /**
     */
    private String type;
    DataPropertyType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
