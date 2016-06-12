import java.util.*;

public class Degree
{

	/**
	 * It is the short form number given to degree
	 */
	private String code;
	/**
	 * It is the name of the degree
	 */
	private String name;
	/**
	 * It denotes the type of degree taken
	 */
	private String degree;
	/**
	 * It is the type of degree taken
	 */
	private String type;
	ArrayList<DegreePlanReq> degreeReqs;
	Student degree1;

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDegree() {
		return this.degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * it is used initialize the object
	 */
	private void ListCoursesToTake() {
		// TODO - implement Degree.ListCoursesToTake
		throw new UnsupportedOperationException();
	}

	private void NumberCoursesNeeded() {
		// TODO - implement Degree.NumberCoursesNeeded
		throw new UnsupportedOperationException();
	}

	public Degree() {
		// TODO - implement Degree.Degree
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param code
	 * @param name
	 */
	public Degree(String code, String name) {
		// TODO - implement Degree.Degree
		throw new UnsupportedOperationException();
	}

	public ArrayList<DegreePlanReq> getDegreeReqs() {
		return this.degreeReqs;
	}

	public Student getDegree1() {
		return this.degree1;
	}

}