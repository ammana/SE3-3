package basicClasses;

public class StudentCourse
{
	private Student student;
	private Course course;
	private String courseName;//outDated
	private Semester semester;
	private String grade;
	/**
	 * 
	 */
	public StudentCourse() {
	}
	/**
	 * @param student
	 * @param course
	 * @param courseName
	 * @param semester
	 * @param grade
	 */
	public StudentCourse(Student student, Course course, String courseName, Semester semester, String grade) {
		this.student = student;
		this.course = course;
		this.courseName = courseName;
		this.semester = semester;
		this.grade = grade;
	}
	/**
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}
	/**
	 * @param student the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}
	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
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
	 * @return the semester
	 */
	public Semester getSemester() {
		return semester;
	}
	/**
	 * @param semester the semester to set
	 */
	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return student + ": " + course;
	}

	

}