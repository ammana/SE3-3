package test;

import java.util.HashMap;
import java.util.Map;

import BasicClasses.Course;
import BasicClasses.Faculty;
import BasicClasses.GradSchool;
import dataManagement.LoadCourse;
import dataManagement.LoadFaculty;
import dataManagement.LoadGradSchool;

public class TestLoadCourse {

	public static void main(String[] args) {

		System.out.println("-----------Testing LoadCourse Class-----------");
		System.out.println("-----------Dependency on LoadFaculty Class-----------");
		System.out.println("-----------Dependency on LoadGradSchool Class-----------");
		HashMap<String, GradSchool> gradSchools = new LoadGradSchool().loadOnSystemStartUp();

		for (Map.Entry<String, GradSchool> entry : gradSchools.entrySet()) {
			String abbreviation = entry.getKey();
			GradSchool gradSchool = entry.getValue();
			System.out.println(abbreviation + ", " + gradSchool.getName() );
		}
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

		System.out.println("-----------Trying to load Courses-----------");

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
	}

}
