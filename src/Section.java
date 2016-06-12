import java.util.*;

public class Section
{

	private String number;
	ArrayList<Student> students;
	Faculty faculty;
	Course courses;
	Schedule schedule;

	public void numberstudents() {
		// TODO - implement Section.numberstudents
		throw new UnsupportedOperationException();
	}

	public void calcpercentcap() {
		// TODO - implement Section.calcpercentcap
		throw new UnsupportedOperationException();
	}

	public Section() {
		// TODO - implement Section.Section
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param number
	 * @param schedule
	 */
	public Section(String number, int schedule) {
		// TODO - implement Section.Section
		throw new UnsupportedOperationException();
	}

	public ArrayList<Student> getStudents() {
		return this.students;
	}

	public Course getCourses() {
		return this.courses;
	}

	public Schedule getSchedule() {
		return this.schedule;
	}

}