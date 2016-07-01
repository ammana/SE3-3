package basicClasses;

public class Faculty
{

	private String lastName;
	private String firstName;
	private GradSchool gradSchool;
	private String degree;
	private String title;
	private String daysToTeach;
	private int maxLoadFall;
	private int maxLoadSpring;
	private int maxLoadSummer;
	
	/**
	 * Default constructor
	 */
	public Faculty() {
	}

	/**
	 * @param lastName
	 * @param firstName
	 * @param gradSchool
	 * @param degree
	 * @param title
	 * @param daysToTeach
	 * @param maxLoadFall
	 * @param maxLoadSpring
	 * @param maxLoadSummer
	 */
	public Faculty(String lastName, String firstName, GradSchool gradSchool, String degree, String title,
			String daysToTeach, int maxLoadFall, int maxLoadSpring, int maxLoadSummer) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.gradSchool = gradSchool;
		this.degree = degree;
		this.title = title;
		this.daysToTeach = daysToTeach;
		this.maxLoadFall = maxLoadFall;
		this.maxLoadSpring = maxLoadSpring;
		this.maxLoadSummer = maxLoadSummer;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the gradSchool
	 */
	public GradSchool getGradSchool() {
		return gradSchool;
	}

	/**
	 * @param gradSchool the gradSchool to set
	 */
	public void setGradSchool(GradSchool gradSchool) {
		this.gradSchool = gradSchool;
	}

	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the daysToTeach
	 */
	public String getDaysToTeach() {
		return daysToTeach;
	}

	/**
	 * @param daysToTeach the daysToTeach to set
	 */
	public void setDaysToTeach(String daysToTeach) {
		this.daysToTeach = daysToTeach;
	}

	/**
	 * @return the maxLoadFall
	 */
	public int getMaxLoadFall() {
		return maxLoadFall;
	}

	/**
	 * @param maxLoadFall the maxLoadFall to set
	 */
	public void setMaxLoadFall(int maxLoadFall) {
		this.maxLoadFall = maxLoadFall;
	}

	/**
	 * @return the maxLoadSpring
	 */
	public int getMaxLoadSpring() {
		return maxLoadSpring;
	}

	/**
	 * @param maxLoadSpring the maxLoadSpring to set
	 */
	public void setMaxLoadSpring(int maxLoadSpring) {
		this.maxLoadSpring = maxLoadSpring;
	}

	/**
	 * @return the maxLoadSummer
	 */
	public int getMaxLoadSummer() {
		return maxLoadSummer;
	}

	/**
	 * @param maxLoadSummer the maxLoadSummer to set
	 */
	public void setMaxLoadSummer(int maxLoadSummer) {
		this.maxLoadSummer = maxLoadSummer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return lastName;
	}

}