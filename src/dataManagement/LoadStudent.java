package dataManagement;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import BasicClasses.Student;
import BasicClasses.Degree;
import BasicClasses.Semester;

public class LoadStudent {

	HashMap<Integer, Student> students;
	HashMap<String, Degree> degrees;
	HashMap<String, Semester> semesters;
	

	/**
	 * @param degrees
	 * @param semesters
	 */
	public LoadStudent(HashMap<String, Degree> degrees, HashMap<String, Semester> semesters) {
		this.degrees = degrees;
		this.semesters = semesters;
	}

	public HashMap<Integer, Student> loadOnSystemStartUp() {
		Student student;
		try {
			Scanner sc = new Scanner(new File("data/STU.DUMP.csv"));
			students = new HashMap<Integer, Student>();

			// Skips first line which contains the column heading
//			if (sc.hasNextLine()) {
//				sc.nextLine();
//				// System.out.println(sc.nextLine());
//			}

			while (sc.hasNextLine()) {
				// System.out.println(sc.nextLine());
				Scanner line = new Scanner(sc.nextLine());
				line.useDelimiter(",");

				Integer id = line.nextInt();
				Degree major = degrees.get(line.next());
				Semester graduatingSemester =  semesters.get(line.next());
				
				if(major != null){//this will discard MSE.ECE, MSE.ENGMGT and multiple major					
					student = new Student(id, major, graduatingSemester);
					// student id is used as the “key”
					students.put(id, student);
				}

				//System.out.println(id+", "+ line.next()+", "+ line.next());

				line.close();
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return students;
	}

}
