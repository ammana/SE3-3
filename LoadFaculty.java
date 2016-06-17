package dataManagement;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import BasicClasses.Faculty;
import BasicClasses.GradSchool;

public class LoadFaculty {

	HashMap<String, Faculty> faculties;
	HashMap<String, GradSchool> gradSchools;

	/**
	 * @param gradSchools
	 */
	public LoadFaculty(HashMap<String, GradSchool> gradSchools) {
		this.gradSchools = gradSchools;
	}

	public HashMap<String, Faculty> loadOnSystemStartUp() {
		Faculty faculty;
		try {
			Scanner sc = new Scanner(new File("data/TestDataFaculty.csv"));
			faculties = new HashMap<String, Faculty>();

			// Skips first line which contains the column heading
			if (sc.hasNextLine()) {
				sc.nextLine();
				// System.out.println(sc.nextLine());
			}

			while (sc.hasNextLine()) {
				// System.out.println(sc.nextLine());
				Scanner line = new Scanner(sc.nextLine());
				line.useDelimiter(",");

				String lastName = line.next();
				faculty = new Faculty(lastName, line.next(), gradSchools.get(line.next()), line.next(), line.next(), line.next(),
						line.nextInt(), line.nextInt(), line.nextInt());
				// faculty last name is used as the “key”
				faculties.put(lastName, faculty);

				// System.out.println(lastName+", "+ line.next()+", "+
				// line.next()+", "+ line.next()+", "+ line.next()
				// +", "+ line.next()+", "+ line.nextInt()+", "+
				// line.nextInt()+", "+ line.nextInt());

				line.close();
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return faculties;
	}

}
