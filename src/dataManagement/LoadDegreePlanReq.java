package dataManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import basicClasses.DegreePlanReq;
import basicClasses.Course;
import basicClasses.Degree;
import java.util.HashSet;

public class LoadDegreePlanReq {
	HashMap<String, Degree> degrees;
	HashMap<String, Course> allCourses;
	HashMap<String, DegreePlanReq> degreePlanReqInfo;

	/**
	 * @param degrees
	 */
	public LoadDegreePlanReq(HashMap<String, Degree> degrees, HashMap<String, Course> allCourses) {
		this.degrees = degrees;
		this.allCourses = allCourses;
	}

	public HashMap<String, DegreePlanReq> loadOnSystemStartUp() {
		DegreePlanReq degreePlanReq;
		try {
			Scanner sc = new Scanner(new File("data/TestDataDegreePlanReq.csv"));
			degreePlanReqInfo = new HashMap<String, DegreePlanReq>();

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

				Degree degree = degrees.get(line.next());
				degreePlanReq = new DegreePlanReq(degree, line.next(), line.nextInt(), line.next()
						, getCourses(line));
				degreePlanReqInfo.put(degreePlanReq.toString(), degreePlanReq);
							
//				System.out.println(degree+", "+ line.next()+", "+ line.nextInt()+", "+ line.next()
//						+", "+ getCourses(line));
				
				line.close();
			}
			sc.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return degreePlanReqInfo;
	}
	
	private HashSet<Course> getCourses(Scanner line){
		HashSet<Course> courses = new HashSet<Course>();
		String s = line.next();	
		
		if(!s.trim().startsWith("\"")){	
			courses.add(allCourses.get(s));										
		}
		else{
			courses.add(allCourses.get(s.replace("\"", "")));
			s = line.next();
			while(!s.trim().endsWith("\"")){
				courses.add(allCourses.get(s));	
				s = line.next();
			}
			courses.add(allCourses.get(s.replace("\"", "")));
		}
		return courses;
	}

}
