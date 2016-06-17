package test;

import java.util.HashMap;
import java.util.Map;

import BasicClasses.Degree;
import BasicClasses.GradSchool;
import dataManagement.LoadDegree;
import dataManagement.LoadGradSchool;

public class TestLoadDegree {
	public static void main(String[] args) {
		
		System.out.println("-----------Testing LoadDegree Class-----------");
		System.out.println("-----------Dependency on LoadGradSchool Class-----------");

		HashMap<String, GradSchool> gradSchools = new LoadGradSchool().loadOnSystemStartUp();

		for (Map.Entry<String, GradSchool> entry : gradSchools.entrySet()) {
			String abbreviation = entry.getKey();
			GradSchool gradSchool = entry.getValue();
			System.out.println(abbreviation + ", " + gradSchool.getName() );
		}
		System.out.println("-------------GradSchooldata loaded-----------------");

		System.out.println("-----------Trying to load Degreees-----------");

		HashMap<String, Degree> degrees = new LoadDegree(gradSchools).loadOnSystemStartUp();

		for (Map.Entry<String, Degree> entry : degrees.entrySet()) {
			String degreeCode = entry.getKey();
			Degree degree = entry.getValue();
			
			System.out.println(degreeCode + ", " + degree.getGradSchool() 
					+ ", " + degree.getName() + ", "+ degree.getForecast());
			
		}
		System.out.println("------------Degree data loaded-----------------");
	}
}
