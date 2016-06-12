import java.util.*;

public class GradSchool
{

	/**
	 * It retrieves the name of gradschool.
	 */
	private ArrayList<String> name;
	/**
	 * It provides with short name of gradschool
	 */
	private String abbrevation;
	private University university;
	ArrayList<Degree> degrees;
	ArrayList<Faculty> faculty;

	public ArrayList<String> getName() {
		return this.name;
	}

	public void setName(ArrayList<String> name) {
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
	public GradSchool() {
		// TODO - implement GradSchool.GradSchool
		throw new UnsupportedOperationException();
	}

	/**
	 * It is used to pass the parameters
	 * @param name
	 * @param abbrevation
	 */
	public GradSchool(String name, String abbrevation) {
		// TODO - implement GradSchool.GradSchool
		throw new UnsupportedOperationException();
	}

	public University getUniversity() {
		return this.university;
	}

	public ArrayList<Degree> getDegrees() {
		return this.degrees;
	}

	public ArrayList<Faculty> getFaculty() {
		return this.faculty;
	}

}