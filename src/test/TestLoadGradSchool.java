package test;

import java.util.HashMap;
import java.util.Map;

import basicClasses.GradSchool;
import dataManagement.LoadGradSchool;

public class TestLoadGradSchool {
	public static void main(String[] args) {
		System.out.println("-----------Testing LoadGradSchool Class-----------");

		HashMap<String, GradSchool> gradSchools = new LoadGradSchool().loadOnSystemStartUp();

		for (Map.Entry<String, GradSchool> entry : gradSchools.entrySet()) {
			String abbreviation = entry.getKey();
			GradSchool gradSchool = entry.getValue();
			System.out.println(abbreviation + ", " + gradSchool.getName() );
		}
		System.out.println("--------------------------------------------------");
	}
}
