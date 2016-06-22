package dataManagement;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import basicClasses.StudentCourse;
import basicClasses.Course;
import basicClasses.Semester;
import basicClasses.Student;

public class LoadStudentCourse {

	private HashMap<Integer, Student> students;
	private HashMap<String, Semester> semesters;
	private HashMap<String, Course> courses;
	private HashMap<String, StudentCourse> studentCourses;

	/**
	 * @param students
	 * @param semesters
	 * @param courses
	 */
	public LoadStudentCourse(HashMap<Integer, Student> students, HashMap<String, Semester> semesters,
			HashMap<String, Course> courses) {
		this.students = students;
		this.semesters = semesters;
		this.courses = courses;
	}

	public HashMap<String, StudentCourse> loadOnSystemStartUp() {
		StudentCourse studentCurse;
		try {
			Scanner sc = new Scanner(new File("data/STC.DUMP.csv"));
			studentCourses = new HashMap<String, StudentCourse>();

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
				
				int i= line.nextInt();
				Student student = students.get(i);
				Course course = courses.get(line.next());
				String courseName = getNextToken(line);
				Semester semester = semesters.get(line.next());
				String grade = line.next();
				
				if(student != null && course != null){//ignoring invalid courses 						
					studentCurse = new StudentCourse(student, course, courseName, semester, grade);
					studentCourses.put(studentCurse.toString(), studentCurse);
				}
				
//				if(student==null)
//					System.out.println("*"+i);					
				
				line.close();
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return studentCourses;
	}
        
	public HashMap<String, StudentCourse> loadFromCsv(String csvFilePath) throws Exception {
		StudentCourse studentCurse;
                    Scanner sc = new Scanner(new File(csvFilePath));
                    studentCourses = new HashMap<String, StudentCourse>();

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

                            int i= line.nextInt();
                            Student student = students.get(i);
                            Course course = courses.get(line.next());
                            String courseName = getNextToken(line);
                            Semester semester = semesters.get(line.next());
                            String grade = line.next();

                            if(student != null && course != null){//ignoring invalid courses 						
                                    studentCurse = new StudentCourse(student, course, courseName, semester, grade);
                                    studentCourses.put(studentCurse.toString(), studentCurse);
                            }

//				if(student==null)
//					System.out.println("*"+i);					

                            line.close();
                    }
                    sc.close();
		
		return studentCourses;
	}
	
	private String getNextToken(Scanner line){
		String s = line.next();	
		if(s.trim().startsWith("\"")){		
			String sNext = line.next();
			while(!sNext.trim().endsWith("\"")){
				s = s +","+ sNext;
				sNext = line.next();
			}
			s = s +","+ sNext;							
		}
		return s;
	}
	
}
