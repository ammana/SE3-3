package dataManagement;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import basicClasses.Semester;

public class LoadSemester {
	HashMap<String, Semester> semesters;
	
	public HashMap<String, Semester> loadOnSystemStartUp() {
		Semester semester;
		try {
			Scanner sc = new Scanner(new File("data/TestDataSemesters.csv"));
			semesters = new HashMap<String, Semester>();

			// Skips first line containing the column heading
			if (sc.hasNextLine()) {
				sc.nextLine();
				// System.out.println(sc.nextLine());
			}

			while (sc.hasNextLine()) {
				// System.out.println(sc.nextLine());
				Scanner line = new Scanner(sc.nextLine());
				line.useDelimiter(",");

				String name = line.next();
				semester = new Semester(name, line.next(), line.next());
				semesters.put(name, semester);

				//System.out.println(name+", "+ line.next()+", "+ line.next());

				line.close();
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return semesters;
	}
	
}
