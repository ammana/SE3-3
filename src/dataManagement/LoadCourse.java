package dataManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import basicClasses.Course;
import basicClasses.Faculty;

public class LoadCourse {
	
	HashMap<String, Faculty> faculties;
	HashMap<String, Course> courses;

	/**
	 * @param faculties
	 */
	public LoadCourse(HashMap<String, Faculty> faculties) {
		this.faculties = faculties;
	}

	public HashMap<String, Course> loadOnSystemStartUp() {
		Course course;
		try {
			Scanner sc = new Scanner(new File("data/TestDataCourses.csv"),"UTF-8");
			courses = new HashMap<String, Course>();

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

				String courseCode = getNextToken(line);
				course = new Course(courseCode, getNextToken(line), getNextToken(line), line.nextInt(), line.nextInt()
						, (getNextToken(line).equals("Y"))
						, (getNextToken(line).equals("Y"))
						, (getNextToken(line).equals("Y"))
						, getPrerequisites(line)
						, getTeachers(line));
				courses.put(courseCode, course);
							
//				System.out.println(courseCode+", "+ getNextToken(line)+", "+ getNextToken(line)+", "+ line.nextInt()+", "+ line.nextInt()
//						+", "+ (getNextToken(line).equals("Y"))
//						+", "+ (getNextToken(line).equals("Y"))
//						+", "+ (getNextToken(line).equals("Y"))
//						+", "+ getPrerequisites(line).toString()
//						+", "+ getTeachers(line));
				
				line.close();
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return courses;
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
	
	private ArrayList<Course> getPrerequisites(Scanner line){
		ArrayList<Course> prerequisites = new ArrayList<Course>();
		String s = line.next();	
		
		if(!s.trim().startsWith("\"")){	
			prerequisites.add(courses.get(s));	
		}
		else{
			prerequisites.add(courses.get(s.replace("\"" , "")));
			s = line.next();
			while(!s.trim().endsWith("\"")){
				prerequisites.add(courses.get(s));	
				s = line.next();
			}
			prerequisites.add(courses.get(s.replace("\"" , "")));
		}
		return prerequisites;
	}
	
	private ArrayList<Faculty> getTeachers(Scanner line){
		ArrayList<Faculty> teachers = new ArrayList<Faculty>();
		String s = line.next();	
		
		if(!s.trim().startsWith("\"")){	
			teachers.add(faculties.get(s));										
		}
		else{
			teachers.add(faculties.get(s.replace("\"", "")));
			s = line.next();
			while(!s.trim().endsWith("\"")){
				teachers.add(faculties.get(s));	
				s = line.next();
			}
			teachers.add(faculties.get(s.replace("\"", "")));
		}
		return teachers;
	}

}
