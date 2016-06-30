package basicClasses;

/**
 * The place where education is taught.
 */
public class University
{

	/**
	 * It provides with the name of the university.
	 */
	private String name;
	/**
	 * It provides with the short form name of university.
	 */
	private String abbreviation;
	
	/**
	 * 
	 */
	public University() {
	}

	
	/**
	 * @param name
	 * @param abbrevation
	 */
	public University(String name, String abbrevation) {
		this.name = name;
		this.abbreviation = abbrevation;
	}


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


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return abbreviation;
	}


}