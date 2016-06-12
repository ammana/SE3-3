import java.util.*;

public class Faculty
{

	private String name;
	private String title;
	private String degree;
	private String Daystoteach;
	ArrayList<Course> courses;
	ArrayList<FacultyLoad> facultyLoads;

	public Faculty() {
		// TODO - implement Faculty.Faculty
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param title
	 * @param degree
	 */
	public Faculty(String name, String title, String degree) {
		// TODO - implement Faculty.Faculty
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Schedule
	 */
	public void canTeachAddSections(String Schedule) {
		// TODO - implement Faculty.canTeachAddSections
		throw new UnsupportedOperationException();
	}

	public ArrayList<Course> getCourses() {
		return this.courses;
	}

	public ArrayList<FacultyLoad> getFacultyLoads() {
		return this.facultyLoads;
	}

}