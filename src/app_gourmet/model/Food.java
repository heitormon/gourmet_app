package app_gourmet.model;

public class Food {

	private String name;
	private String type;

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public Food(String name, String type) {
		this.name = name;
		this.type = type;
	}

}
