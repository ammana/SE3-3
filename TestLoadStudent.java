package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import basicClasses.Degree;
import basicClasses.GradSchool;
import basicClasses.Semester;
import basicClasses.Student;
import dataManagement.LoadDegree;
import dataManagement.LoadGradSchool;
import dataManagement.LoadSemester;
import dataManagement.LoadStudent;

public class TestLoadStudent {
	public static void main(String[] args) {

		System.out.println("-----------Testing LoadStudent Class-----------");
		System.out.println("-----------Dependency on LoadDegree Class-----------");
		System.out.println("-----------Dependency on LoadGradSchool Class-----------");
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
		
	}
}
