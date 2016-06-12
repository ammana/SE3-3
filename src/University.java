import java.util.*;

/**
 * The place where education is taught.
 */
public class University
{

	/**
	 * It provides with the name of the university.
	 */
	public String name;
	/**
	 * It provides with the short form name of university.
	 */
	public String abbrevation;
	ArrayList<GradSchool> gradschools;
	ArrayList<Course> courses;
	ArrayList<Semester> semesters;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbrevation() {
		return this.abbrevation;
	}

	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}

	/**
	 * It is used to initialize the object
	 */
	public University() {
		// TODO - implement University.University
		throw new UnsupportedOperationException();
	}

	/**
	 * This constructor is used to pass the values.
	 * @param name
	 * @param abbrevation
	 */
	public University(String name, String abbrevation) {
		// TODO - implement University.University
		throw new UnsupportedOperationException();
	}

	public ArrayList<GradSchool> getGradschools() {
		return this.gradschools;
	}

	public ArrayList<Course> getCourses() {
		return this.courses;
	}

	public ArrayList<Semester> getSemesters() {
		return this.semesters;
	}

}