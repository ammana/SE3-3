package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import BasicClasses.Course;
import BasicClasses.Degree;
import BasicClasses.Faculty;
import BasicClasses.GradSchool;
import BasicClasses.Semester;
import BasicClasses.Student;
import BasicClasses.StudentCourse;
import dataManagement.LoadCourse;
import dataManagement.LoadDegree;
import dataManagement.LoadFaculty;
import dataManagement.LoadGradSchool;
import dataManagement.LoadSemester;
import dataManagement.LoadStudent;
import dataManagement.LoadStudentCourse;

public class TestLoadStudentCourse {

	public static void main(String[] args) {

		System.out.println("-----------Testing LoadStudentCourse Class-----------");
		System.out.println("-----------Dependency on LoadCourse Class-----------");
		System.out.println("-----------Dependency on LoadFaculty Class-----------");
		System.out.println("-----------Dependency on LoadGradSchool Class-----------");
		HashMap<String, GradSchool> gradSchools = new LoadGradSchool().loadOnSystemStartUp();
		for (Map.Entry<String, GradSchool> entry : gradSchools.entrySet()) {
			String abbreviation = entry.getKey();
			GradSchool gradSchool = entry.getValue();
			System.out.println(abbreviation + ", " + gradSchool.getName() );		}
		System.out.println("-------------GradSchooldata loaded-----------------");
		System.out.println("-----------Loading Faculty data-----------");
		HashMap<String, Faculty> faculties = new LoadFaculty(gradSchools).loadOnSystemStartUp();
		for (Map.Entry<String, Faculty> entry : faculties.entrySet()) {
			String lastName = entry.getKey();
			Faculty faculty = entry.getValue();
			System.out.println(lastName + ", " + faculty.getFirstName() + ", " + faculty.getGradSchool() + ", "
					+ faculty.getDegree() + ", " + faculty.getTitle() + ", " + faculty.getDaysToTeach() + ", "
					+ faculty.getMaxLoadFall() + ", " + faculty.getMaxLoadSpring() + ", " + faculty.getMaxLoadSummer());
		}
		System.out.println("-------------Faculty data loaded-----------------");
		System.out.println("-----------Loading Courses-----------");
		HashMap<String, Course> courses = new LoadCourse(faculties).loadOnSystemStartUp();
		for (Map.Entry<String, Course> entry : courses.entrySet()) {
			String courseCode = entry.getKey();
			Course course = entry.getValue();			
//			System.out.println(courseCode + ", " + course.getCourseName() + ", " + course.getCourseDescription() + ", "
//					+ course.getCreditHours() + ", " + course.getClassCap() + ", " + course.isOfferedInFall() + ", "
//					+ course.isOfferedInSpring() + ", " + course.isOfferedInSummer() + ", " + course.getPrerequisites()
//					+ ", " + course.getTeachers());			
			System.out.println(courseCode +"# " + course.getPrerequisites() +"# "+ course.getTeachers());
		}
		System.out.println("------------Course data loaded-----------------");
		
		System.out.println("-----------Dependency on LoadDegree Class-----------");
		System.out.println("-----------Loading Degreees-----------");
		HashMap<String, Degree> degrees = new LoadDegree(gradSchools).loadOnSystemStartUp();
		for (Map.Entry<String, Degree> entry : degrees.entrySet()) {
			String degreeCode = entry.getKey();
			Degree degree = entry.getValue();
			
			System.out.println(degreeCode + ", " + degree.getGradSchool() 
					+ ", " + degree.getName() + ", "+ degree.getForecast());			
		}
		System.out.println("------------Degree data loaded-----------------");		
		System.out.println("-----------Dependency on Semester Class-----------");
		System.out.println("-----------Loading Semester data-----------");
		HashMap<String, Semester> semesters = new LoadSemester().loadOnSystemStartUp();
		for (Map.Entry<String, Semester> entry : semesters.entrySet()) {
			//String name = entry.getKey();
			Semester semester = entry.getValue();
			System.out.println(semester.getName()+ ", " + semester.getStartDate()+ ", " + semester.getEndDate());
		}
		System.out.println("-------------Semester data loaded-----------------");		
		System.out.println("-----------Loading Student data-----------");
		HashMap<Integer, Student> students = new LoadStudent(degrees, semesters).loadOnSystemStartUp();
		for (Entry<Integer, Student> entry : students.entrySet()) {
			Student student = entry.getValue();
			System.out.println(student.getId()+ ", " + student.getMajor()+ ", " + student.getGradsemester());
		}
		System.out.println("-------------Student data loaded-----------------");
		
		System.out.println("-----------Loading StudentCourse data-----------");
		HashMap<String, StudentCourse> studentCourses = new LoadStudentCourse(students, semesters, courses).loadOnSystemStartUp();
		for (Entry<String, StudentCourse> entry : studentCourses.entrySet()) {
			StudentCourse studentCourse = entry.getValue();
			System.out.println(studentCourse.getStudent()+ ", " + studentCourse.getCourse()
			+ ", " + studentCourse.getCourseName()+ ", " + studentCourse.getSemester()
					+ ", " + studentCourse.getGrade());
		}
		System.out.println("-------------StudentCourse data loaded-----------------");
	}
	

}
