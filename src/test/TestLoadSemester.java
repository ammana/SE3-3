package test;

import java.util.HashMap;
import java.util.Map;

import BasicClasses.Semester;
import dataManagement.LoadSemester;

public class TestLoadSemester {
	public static void main(String[] args) {
		System.out.println("-----------Testing LoadSemester Class-----------");
		HashMap<String, Semester> semesters = new LoadSemester().loadOnSystemStartUp();
		for (Map.Entry<String, Semester> entry : semesters.entrySet()) {
			//String name = entry.getKey();
			Semester semester = entry.getValue();
			System.out.println(semester.getName()+ ", " + semester.getStartDate()+ ", " + semester.getEndDate());
		}
		System.out.println("--------------------------------------------------");
	}
}
