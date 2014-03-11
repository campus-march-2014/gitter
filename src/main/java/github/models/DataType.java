package github.models;

public enum DataType {
	COMMIT("commit");

	/**
     */
	private String type;

	DataType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
