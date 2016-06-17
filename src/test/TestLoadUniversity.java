package test;

import java.util.HashMap;
import java.util.Map;

import BasicClasses.University;
import dataManagement.LoadUniversity;

public class TestLoadUniversity {
	public static void main(String[] args) {
		System.out.println("-----------Testing LoadUniversity Class-----------");

		HashMap<String, University> universities = new LoadUniversity().loadOnSystemStartUp();

		for (Map.Entry<String, University> entry : universities.entrySet()) {
			University university = entry.getValue();
			System.out.println(university.getName() + ", " + university.getAbbrevation());
		}
		System.out.println("--------------------------------------------------");
	}
}
