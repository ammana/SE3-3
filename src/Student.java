import java.util.*;

public class Student
{

	/**
	 * It provides with identification number of student.
	 */
	private int id;
	/**
	 * It provides with name of the student.
	 */
	private String name;
	Semester gradsemester;
	ArrayList<StudentCouse> courses;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * It is used to initialize the object.
	 */
	public Student() {
		// TODO - implement Student.Student
		throw new UnsupportedOperationException();
	}

	/**
	 * It is used to pass the parameters
	 * @param id
	 * @param name
	 */
	public Student(int id, String name) {
		// TODO - implement Student.Student
		throw new UnsupportedOperationException();
	}

	public Semester getGradsemester() {
		return this.gradsemester;
	}

}