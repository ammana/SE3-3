/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicClasses;

import java.util.HashSet;

/**
 *
 * @author mounika
 */
public class Section{
    public Course course;
    public Faculty faculty;
    public int sectionNumber;
    public HashSet<Student> students;

    public Section(Course course, Faculty faculty) {
        this.course = course;
        this.faculty = faculty;
    }    

    @Override
    public String toString() {
        return course + ": " + faculty +"-"+ 
                (students == null? 0: students.size() );
    }
}
