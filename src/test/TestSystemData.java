/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import basicClasses.Course;
import basicClasses.Degree;
import basicClasses.DegreePlanReq;
import basicClasses.Faculty;
import basicClasses.GradSchool;
import basicClasses.Semester;
import basicClasses.Student;
import basicClasses.StudentCourse;
import dataManagement.LoadCourse;
import dataManagement.LoadDegree;
import dataManagement.LoadDegreePlanReq;
import dataManagement.LoadFaculty;
import dataManagement.LoadGradSchool;
import dataManagement.LoadSemester;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sandeep
 */
public class TestSystemData {
    static HashMap<String, GradSchool> gradSchools;
    static HashMap<String, Faculty> faculties;
    static HashMap<String, Course> courses;
    static HashMap<String, Degree> degrees;
    static HashMap<String, DegreePlanReq>  degreePlanReqInfo;	
    static HashMap<String, Semester> semesters;
    static HashMap<Integer, Student> students ;
    static HashMap<String, StudentCourse> studentCourses;
    
    public static void main(String[] args) {
		
		System.out.println("-----------Loading GradSchool data-----------");
		gradSchools = new LoadGradSchool().loadOnSystemStartUp();
		for (Map.Entry<String, GradSchool> entry : gradSchools.entrySet()) {
			String abbreviation = entry.getKey();
			GradSchool gradSchool = entry.getValue();
			System.out.println(abbreviation + ", " + gradSchool.getName() );
		}
		System.out.println("-------------GradSchool data loaded-----------------");
				
		System.out.println("-----------Loading Faculty data-----------");		
		faculties = new LoadFaculty(gradSchools).loadOnSystemStartUp();
		for (Map.Entry<String, Faculty> entry : faculties.entrySet()) {
			String lastName = entry.getKey();
			Faculty faculty = entry.getValue();
			System.out.println(lastName + ", " + faculty.getFirstName() + ", " + faculty.getGradSchool() + ", "
					+ faculty.getDegree() + ", " + faculty.getTitle() + ", " + faculty.getDaysToTeach() + ", "
					+ faculty.getMaxLoadFall() + ", " + faculty.getMaxLoadSpring() + ", " + faculty.getMaxLoadSummer());
		}
		System.out.println("-----------Faculty data loaded-----------------");	
				
		System.out.println("-----------Loading Course data-----------");		
		courses = new LoadCourse(faculties).loadOnSystemStartUp();
		for (Map.Entry<String, Course> entry : courses.entrySet()) {
			String courseCode = entry.getKey();
			Course course = entry.getValue();			
			System.out.println(courseCode + ", " + course.getCourseName() + ", " + course.getCourseDescription() + ", "
					+ course.getCourseHours()+ ", " + course.getCourseCap()+ ", " + course.isOfferedInFall() + ", "
					+ course.isOfferedInSpring() + ", " + course.isOfferedInSummer() + ", " + course.getPrerequisites()
					+ ", " + course.getTeachers());
			
	//		System.out.println(courseCode +"# " + course.getPrerequisites() +"# "+ course.getTeachers());
		}
		System.out.println("------------Course data loaded-----------------");
		
		System.out.println("------------Loading Degreees-----------");
		degrees = new LoadDegree(gradSchools).loadOnSystemStartUp();
		for (Map.Entry<String, Degree> entry : degrees.entrySet()) {
			String degreeCode = entry.getKey();
			Degree degree = entry.getValue();			
			System.out.println(degreeCode + ", " + degree.getGradSchool() 
					+ ", " + degree.getName() + ", "+ degree.getForecast());
			
		}
		System.out.println("------------Degree data loaded-----------------");
		
		System.out.println("-----------Loading DegreePlanReq-----------");
		degreePlanReqInfo = new LoadDegreePlanReq(degrees, courses).loadOnSystemStartUp();
		for (Map.Entry<String, DegreePlanReq> entry : degreePlanReqInfo.entrySet()) {
			//String degreePlanReqCode = entry.getKey();
			DegreePlanReq degreePlanReq = entry.getValue();
			
			System.out.println(//degreePlanReqCode + ", " + 
					degreePlanReq.getDegree() 
					+ ", " + degreePlanReq.getDescription() + ", "+ degreePlanReq.getHours() 
					+ ", "+ degreePlanReq.getType() + ", "+ degreePlanReq.getCourses());
			
		}
		System.out.println("------------DegreePlanReq data loaded-----------------");
		
		System.out.println("-----------Loading Semester Data-----------");
		semesters = new LoadSemester().loadOnSystemStartUp();
		for (Map.Entry<String, Semester> entry : semesters.entrySet()) {
			//String name = entry.getKey();
			Semester semester = entry.getValue();
			System.out.println(semester.getName()+ ", " + semester.getStartDate()+ ", " + semester.getEndDate());
		}
		System.out.println("--------------------------------------------------");
		
//		System.out.println("-----------Loading Student data-----------");
//		students = new LoadStudent(degrees, semesters).loadOnSystemStartUp();
//		for (Entry<Integer, Student> entry : students.entrySet()) {
//			Student student = entry.getValue();
//			System.out.println(student.getId()+ ", " + student.getMajor()+ ", " + student.getGradsemester());
//		}
//		System.out.println("-------------Student data loaded-----------------");
//		
//		System.out.println("-----------Loading StudentCourse data-----------");
//		studentCourses = new LoadStudentCourse(students, semesters, courses).loadOnSystemStartUp();
//		for (Entry<String, StudentCourse> entry : studentCourses.entrySet()) {
//			StudentCourse studentCourse = entry.getValue();
//			System.out.println(studentCourse.getStudent()+ ", " + studentCourse.getCourse()
//			+ ", " + studentCourse.getCourseName()+ ", " + studentCourse.getSemester()
//					+ ", " + studentCourse.getGrade());
//		}
//		System.out.println("-------------StudentCourse data loaded-----------------");
		
	}

}
