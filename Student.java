package basicClasses;

public class Student {

	/**
	 * It provides with identification number of student.
	 */
	private int id;
	private Degree major;
	private Semester graduatingSemester;

	/**
	 * Default constructor
	 */
	public Student() {
	}

	/**
	 * @param id
	 * @param major
	 * @param graduatingSemester
	 */
	public Student(int id, Degree major, Semester graduatingSemester) {
		this.id = id;
		this.major = major;
		this.graduatingSemester = graduatingSemester;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the major
	 */
	public Degree getMajor() {
		return major;
	}

	/**
	 * @param major
	 *            the major to set
	 */
	public void setMajor(Degree major) {
		this.major = major;
	}

	/**
	 * @return the graduatingSemester
	 */
	public Semester getGradsemester() {
		return graduatingSemester;
	}

	/**
	 * @param graduatingSemester
	 *            the graduatingSemester to set
	 */
	public void setGradsemester(Semester graduatingSemester) {
		this.graduatingSemester = graduatingSemester;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return id + "";
	}

}