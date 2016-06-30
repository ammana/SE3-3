package basicClasses;

import java.util.ArrayList;

public class Course {

	// private int number;
	private String courseCode;
	private String courseName;
	private String courseDescription;
	private int courseHours;
	private int courseCap;
	private boolean isOfferedInFall;
	private boolean isOfferedInSpring;
	private boolean isOfferedInSummer;
	ArrayList<Course> prerequisites;
	ArrayList<Faculty> teachers;

	/**
	 * Default constructor
	 */
	public Course() {
	}

	/**
	 * @param courseCode
	 * @param courseName
	 * @param courseDescription
	 * @param courseHours
	 * @param courseCap
	 * @param isOfferedInFall
	 * @param isOfferedInSpring
	 * @param isOfferedInSummer
	 * @param prerequisites
	 * @param teachers
	 */
	public Course(String courseCode, String courseName, String courseDescription, int courseHours, int courseCap,
			boolean isOfferedInFall, boolean isOfferedInSpring, boolean isOfferedInSummer,
			ArrayList<Course> prerequisites, ArrayList<Faculty> teachers) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseDescription = courseDescription;
		this.courseHours = courseHours;
		this.courseCap = courseCap;
		this.isOfferedInFall = isOfferedInFall;
		this.isOfferedInSpring = isOfferedInSpring;
		this.isOfferedInSummer = isOfferedInSummer;
		this.prerequisites = prerequisites;
		this.teachers = teachers;
	}

	/**
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * @param courseCode the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the courseDescription
	 */
	public String getCourseDescription() {
		return courseDescription;
	}

	/**
	 * @param courseDescription the courseDescription to set
	 */
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	/**
	 * @return the courseHours
	 */
	public int getCourseHours() {
		return courseHours;
	}

	/**
	 * @param courseHours the courseHours to set
	 */
	public void setCourseHours(int courseHours) {
		this.courseHours = courseHours;
	}

	/**
	 * @return the courseCap
	 */
	public int getCourseCap() {
		return courseCap;
	}

	/**
	 * @param courseCap the courseCap to set
	 */
	public void setCourseCap(int courseCap) {
		this.courseCap = courseCap;
	}

	/**
	 * @return the isOfferedInFall
	 */
	public boolean isOfferedInFall() {
		return isOfferedInFall;
	}

	/**
	 * @param isOfferedInFall the isOfferedInFall to set
	 */
	public void setOfferedInFall(boolean isOfferedInFall) {
		this.isOfferedInFall = isOfferedInFall;
	}

	/**
	 * @return the isOfferedInSpring
	 */
	public boolean isOfferedInSpring() {
		return isOfferedInSpring;
	}

	/**
	 * @param isOfferedInSpring the isOfferedInSpring to set
	 */
	public void setOfferedInSpring(boolean isOfferedInSpring) {
		this.isOfferedInSpring = isOfferedInSpring;
	}

	/**
	 * @return the isOfferedInSummer
	 */
	public boolean isOfferedInSummer() {
		return isOfferedInSummer;
	}

	/**
	 * @param isOfferedInSummer the isOfferedInSummer to set
	 */
	public void setOfferedInSummer(boolean isOfferedInSummer) {
		this.isOfferedInSummer = isOfferedInSummer;
	}

	/**
	 * @return the prerequisites
	 */
	public ArrayList<Course> getPrerequisites() {
		return prerequisites;
	}

	/**
	 * @param prerequisites the prerequisites to set
	 */
	public void setPrerequisites(ArrayList<Course> prerequisites) {
		this.prerequisites = prerequisites;
	}

	/**
	 * @return the teachers
	 */
	public ArrayList<Faculty> getTeachers() {
		return teachers;
	}

	/**
	 * @param teachers the teachers to set
	 */
	public void setTeachers(ArrayList<Faculty> teachers) {
		this.teachers = teachers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return courseCode;
	}

}