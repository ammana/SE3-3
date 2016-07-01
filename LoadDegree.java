package dataManagement;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import basicClasses.Degree;
import basicClasses.GradSchool;

public class LoadDegree {
	HashMap<String, GradSchool> gradSchools;
	HashMap<String, Degree> degrees;

	/**
	 * @param gradSchools
	 */
	public LoadDegree(HashMap<String, GradSchool> gradSchools) {
		this.gradSchools = gradSchools;
	}

	public HashMap<String, Degree> loadOnSystemStartUp() {
		Degree degree;
		try {
			Scanner sc = new Scanner(new File("data/TestDataDegrees.csv"));
			degrees = new HashMap<String, Degree>();

			// Skips first line which contains the column heading
			if (sc.hasNextLine()) {
				sc.nextLine();
				//System.out.println(sc.nextLine());
			}

			while (sc.hasNextLine()) {
				String nextLine = sc.nextLine();
				//System.out.println("*"+nextLine);
				Scanner line = new Scanner(nextLine);
				line.useDelimiter(",");

				String code = line.next();
				degree = new Degree(code, gradSchools.get(line.next()), line.next(), line.nextInt() );
				degrees.put(code, degree);
							
//				System.out.println(code+", "+ line.next()+", "+ line.next()+", "+ line.nextInt());
				
				line.close();
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return degrees;
	}

}
