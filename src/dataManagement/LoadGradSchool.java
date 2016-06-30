package dataManagement;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import basicClasses.GradSchool;

public class LoadGradSchool {
	
	HashMap<String, GradSchool> gradSchools;
	
	public HashMap<String, GradSchool> loadOnSystemStartUp() {
		GradSchool gradSchool;
		try {
			Scanner sc = new Scanner(new File("data/TestDataGradSchools.csv"));
			gradSchools = new HashMap<String, GradSchool>();

			// Skips first line containing the column heading
			if (sc.hasNextLine()) {
				sc.nextLine();
				// System.out.println(sc.nextLine());
			}

			while (sc.hasNextLine()) {
				// System.out.println(sc.nextLine());
				Scanner line = new Scanner(sc.nextLine());
				line.useDelimiter(",");

				String abbreviation = line.next();
				gradSchool = new GradSchool(abbreviation, line.next());
				// gradSchool abbreviation is used as the “key”
				gradSchools.put(abbreviation, gradSchool);

				//System.out.println(abbreviation+", "+ line.next());

				line.close();
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return gradSchools;
	}
	
	
}
