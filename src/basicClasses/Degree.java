package BasicClasses;

public class Degree {

	/**
	 * It is the short form number given to degree
	 */
	private String code;
	/**
	 * The GradSchool which offers this degree
	 */
	private GradSchool gradSchool;
	/**
	 * It is the name of the degree
	 */
	private String name;

	private int forecast;

	/**
	 * Default constructor
	 */
	public Degree() {
	}

	/**
	 * @param code
	 * @param gradSchool
	 * @param name
	 * @param forecast
	 */
	public Degree(String code, GradSchool gradSchool, String name, int forecast) {
		this.code = code;
		this.gradSchool = gradSchool;
		this.name = name;
		this.forecast = forecast;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the gradSchool
	 */
	public GradSchool getGradSchool() {
		return gradSchool;
	}

	/**
	 * @param gradSchool
	 *            the gradSchool to set
	 */
	public void setGradSchool(GradSchool gradSchool) {
		this.gradSchool = gradSchool;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the forecast
	 */
	public int getForecast() {
		return forecast;
	}

	/**
	 * @param forecast
	 *            the forecast to set
	 */
	public void setForecast(int forecast) {
		this.forecast = forecast;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return code;
	}

}