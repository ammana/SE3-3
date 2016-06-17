package dataManagement;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import BasicClasses.University;

public class LoadUniversity {
	
	HashMap<String, University> universities;
	
	public HashMap<String, University> loadOnSystemStartUp() {
		University university;
		try {
			Scanner sc = new Scanner(new File("data/TestDataUniversityName.csv"));
			universities = new HashMap<String, University>();

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
				String abbreviation = line.next();
				university = new University(name, abbreviation);
				// university abbreviation is used as the “key”
				universities.put(abbreviation, university);

				line.close();
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return universities;
	}
	
	
}
