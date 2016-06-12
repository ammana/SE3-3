import java.util.*;

public class Course
{

	private int number;
	private String name;
	private String description;
	private int credithours;
	private int classcap;
	ArrayList<Faculty> faculty;
	Course preRequisit;

	public Course() {
		// TODO - implement Course.Course
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param number
	 */
	public Course(String number) {
		// TODO - implement Course.Course
		throw new UnsupportedOperationException();
	}

	public ArrayList<Faculty> getFaculty() {
		return this.faculty;
	}

	public Course getPreRequisit() {
		return this.preRequisit;
	}

}