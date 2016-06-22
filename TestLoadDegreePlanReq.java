package test;

import java.util.HashMap;
import java.util.Map;

import basicClasses.Course;
import basicClasses.Degree;
import basicClasses.DegreePlanReq;
import basicClasses.Faculty;
import basicClasses.GradSchool;
import dataManagement.LoadCourse;
import dataManagement.LoadDegree;
import dataManagement.LoadDegreePlanReq;
import dataManagement.LoadFaculty;
import dataManagement.LoadGradSchool;

public class TestLoadDegreePlanReq {
	
public static void main(String[] args) {
		
		System.out.println("-----------Testing TestLoadDegreePlanReq Class-----------");
		
		System.out.println("-----------Dependency on LoadDegree Class-----------");
		System.out.println("-----------Dependency on LoadGradSchool Class-----------");		
		System.out.println("-----------Loading GradSchool-----------");
		HashMap<String, GradSchool> gradSchools = new LoadGradSchool().loadOnSystemStartUp();

		for (Map.Entry<String, GradSchool> entry : gradSchools.entrySet()) {
			String abbreviation = entry.getKey();
			GradSchool gradSchool = entry.getValue();
			System.out.println(abbreviation + ", " + gradSchool.getName() );
		}
		System.out.println("-------------GradSchooldata loaded-----------------");

		System.out.println("-----------Loading Degreees-----------");
		HashMap<String, Degree> degrees = new LoadDegree(gradSchools).loadOnSystemStartUp();

		for (Map.Entry<String, Degree> entry : degrees.entrySet()) {
			String degreeCode = entry.getKey();
			Degree degree = entry.getValue();
			
			System.out.println(degreeCode + ", " + degree.getGradSchool() 
					+ ", " + degree.getName() + ", "+ degree.getForecast());
			
		}
		System.out.println("------------Degree data loaded-----------------");
		
		
		System.out.println("-----------Dependency LoadCourse Class-----------");
		System.out.println("-----------Dependency on LoadFaculty Class-----------");
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

		System.out.println("-----------Loading Course data----------");

		HashMap<String, Course> courses = new LoadCourse(faculties).loadOnSystemStartUp();

		for (Map.Entry<String, Course> entry : courses.entrySet()) {
			String courseCode = entry.getKey();
			Course course = entry.getValue();
			
			System.out.println(courseCode + ", " + course.getCourseName() + ", " + course.getCourseDescription() + ", "
					+ course.getCourseHours() + ", " + course.getCourseCap()+ ", " + course.isOfferedInFall() + ", "
					+ course.isOfferedInSpring() + ", " + course.isOfferedInSummer() + ", " + course.getPrerequisites()
					+ ", " + course.getTeachers());
		}
		System.out.println("------------Course data loaded-----------------");
	
		
		System.out.println("-----------Loading DegreePlanReq-----------");
		HashMap<String, DegreePlanReq>  degreePlanReqInfo = new LoadDegreePlanReq(degrees, courses).loadOnSystemStartUp();

		for (Map.Entry<String, DegreePlanReq> entry : degreePlanReqInfo.entrySet()) {
			//String degreePlanReqCode = entry.getKey();
			DegreePlanReq degreePlanReq = entry.getValue();
			
			System.out.println(//degreePlanReqCode + ", " + 
					degreePlanReq.getDegree() 
					+ ", " + degreePlanReq.getDescription() + ", "+ degreePlanReq.getHours() 
					+ ", "+ degreePlanReq.getType() + ", "+ degreePlanReq.getCourses());
			
		}
		System.out.println("------------DegreePlanReq data loaded-----------------");
		
		
	}
}

