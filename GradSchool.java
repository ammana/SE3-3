package basicClasses;

public class GradSchool {

	/**
	 * It provides with short name of gradSchool
	 */
	private String abbreviation;
	/**
	 * It retrieves the name of gradSchool.
	 */
	private String name;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrevation() {
		return this.abbreviation;
	}

	public void setAbbrevation(String abbrevation) {
		this.abbreviation = abbrevation;
	}

	/**
	 * It is used to initialize the object
	 */
	public GradSchool() {

	}

	/**
	 * @param name
	 * @param abbrevation
	 */
	public GradSchool( String abbrevation, String name) {
		this.name = name;
		this.abbreviation = abbrevation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return abbreviation;
	}

}