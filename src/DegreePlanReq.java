import java.util.*;

public class DegreePlanReq
{

	private String description;
	private int hours;
	private String type;
	ArrayList<Course> courses;
	Degree degree;

	public DegreePlanReq() {
		// TODO - implement DegreePlanReq.DegreePlanReq
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param student
	 */
	public void ListCoursesToTake(Student student) {
		// TODO - implement DegreePlanReq.ListCoursesToTake
		throw new UnsupportedOperationException();
	}

	public ArrayList<Course> getCourses() {
		return this.courses;
	}

	public Degree getDegree() {
		return this.degree;
	}

}