package BasicClasses;

import java.util.ArrayList;

public class DegreePlanReq {
	
	private Degree degree;
	private String description;
	private int hours;
	private String type;
	private ArrayList<Course> courses;

	/**
	 * 
	 */
	public DegreePlanReq() {
	}

	/**
	 * @param degree
	 * @param description
	 * @param hours
	 * @param type
	 * @param courses
	 */
	public DegreePlanReq(Degree degree, String description, int hours, String type, ArrayList<Course> courses) {
		this.degree = degree;
		this.description = description;
		this.hours = hours;
		this.type = type;
		this.courses = courses;
	}

	/**
	 * @return the degree
	 */
	public Degree getDegree() {
		return degree;
	}

	/**
	 * @param degree the degree to set
	 */
	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the courses
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return degree + ": " + description;
	}

}