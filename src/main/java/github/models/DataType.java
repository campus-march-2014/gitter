package github.models;

public enum DataType {
	COMMIT("commit"),
	ISSUE("issue"),
	PULL_REQUEST("pull_request");
	

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
