package test;

import java.util.HashMap;
import java.util.Map;

import basicClasses.Faculty;
import basicClasses.GradSchool;
import dataManagement.LoadFaculty;
import dataManagement.LoadGradSchool;

public class TestLoadFaculty {

	public static void main(String[] args) {
		System.out.println("-----------Testing LoadFaculty Class-----------");
		System.out.println("-----------Dependency on LoadGradSchool Class-----------");

		HashMap<String, GradSchool> gradSchools = new LoadGradSchool().loadOnSystemStartUp();

		for (Map.Entry<String, GradSchool> entry : gradSchools.entrySet()) {
			String abbreviation = entry.getKey();
			GradSchool gradSchool = entry.getValue();
			System.out.println(abbreviation + ", " + gradSchool.getName() );
		}
		System.out.println("-------------GradSchooldata loaded-----------------");
		
		HashMap<String, Faculty> faculties = new LoadFaculty(gradSchools).loadOnSystemStartUp();

		for (Map.Entry<String, Faculty> entry : faculties.entrySet()) {
			String lastName = entry.getKey();
			Faculty faculty = entry.getValue();
			System.out.println(lastName + ", " + faculty.getFirstName() + ", " + faculty.getGradSchool() + ", "
					+ faculty.getDegree() + ", " + faculty.getTitle() + ", " + faculty.getDaysToTeach() + ", "
					+ faculty.getMaxLoadFall() + ", " + faculty.getMaxLoadSpring() + ", " + faculty.getMaxLoadSummer());
		}
		System.out.println("----------------------------------------------");
	}

}
