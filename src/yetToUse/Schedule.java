package yetToUse;

import basicClasses.Course;
import basicClasses.Degree;
import basicClasses.DegreePlanReq;
import basicClasses.Faculty;
import basicClasses.Semester;
import basicClasses.Student;
import basicClasses.StudentCourse;
import dataManagement.SystemData;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Schedule {

    SystemData systemData;
    Semester schedulingSemester;
    double fillPercentage, overagePercentage;
    
    final int CreditHourPerCourse = 3;
    HashMap<Degree, ArrayList<CourseSet>> consolidatedDegreePlanReq;
    HashMap<Student, HashSet<Course>> consolidatedStudentCourse;
    HashMap<Student, ArrayList<CourseSet>> coursesYetToTake;
    HashMap<Course, ArrayList<StudentNeedValue>> requiredCourseNeedForExistingStudents;
    HashMap<Course, ArrayList<StudentNeedValue>> electiveCourseNeedForExistingStudents;
    
    HashMap<Course, Double> requiredCourseNeedForExistingStudentsSum;
    HashMap<Course, Double> electiveCourseNeedForExistingStudentsSum;
    HashMap<Course, Integer> requiredForNoOfStudent;
    HashMap<Course, Integer> electiveForNoOfStudent;    
    
    HashMap<Course, ArrayList<Section>> sections;

    public Schedule(SystemData systemData, Semester schedulingSemester, double fillPercentage, double overagePercentage) {
        this.systemData = systemData;
        this.schedulingSemester = schedulingSemester;
        this.fillPercentage = fillPercentage;
        this.overagePercentage = overagePercentage;
        
        getConsolidatedDegreePlanReq();
        getConsolidatedStudentCourse();
        removeStudentsAlreadyGraduated();
        getcourseYetToTake();
        getCourseNeedForExistingStudents();
        getCourseNeedForExistingStudentsSum();
        getPossibleSectionsForSchedulingSemester();
    }

    public Schedule() {
        systemData = new SystemData();
        systemData.load();
        schedulingSemester = systemData.getSemesters().get("2017SP");
        this.fillPercentage = 0;
        this.overagePercentage = 0;
        
        
        getConsolidatedDegreePlanReq();
        getConsolidatedStudentCourse();
        removeStudentsAlreadyGraduated();
        getcourseYetToTake();
        getCourseNeedForExistingStudents();
        getCourseNeedForExistingStudentsSum();
        getPossibleSectionsForSchedulingSemester();
    }

    public void getConsolidatedDegreePlanReq() {
        //System.out.println("yetToUse.Schedule.getConsolidatedDegreePlanReq()");
        consolidatedDegreePlanReq = new HashMap<Degree, ArrayList<CourseSet>>();
        HashMap<String, DegreePlanReq> degreePlanReqInfo = systemData.getDegreePlanReqInfo();
        for (Map.Entry<String, DegreePlanReq> entry : degreePlanReqInfo.entrySet()) {
            DegreePlanReq degreePlanReq = entry.getValue();
            if (!consolidatedDegreePlanReq.containsKey(degreePlanReq.getDegree())) {
                consolidatedDegreePlanReq.put(degreePlanReq.getDegree(), new ArrayList<CourseSet>());
            }
            ArrayList<CourseSet> courseSetList = consolidatedDegreePlanReq.get(degreePlanReq.getDegree());
            courseSetList.add(new CourseSet(degreePlanReq.getType(), degreePlanReq.getHours() / CreditHourPerCourse,
                    degreePlanReq.getCourses()));
            consolidatedDegreePlanReq.put(degreePlanReq.getDegree(), courseSetList);
//                System.out.println(//degreePlanReqCode + ", " + 
//                                degreePlanReq.getDegree() 
//                                + ", " + degreePlanReq.getDescription() + ", "+ degreePlanReq.getHours() 
//                                + ", "+ degreePlanReq.getType() + ", "+ degreePlanReq.getCourses());

        }
        System.out.println("==================");
        System.out.println(consolidatedDegreePlanReq);
    }

    public void getConsolidatedStudentCourse() {
        //System.out.println("yetToUse.Schedule.getConsolidatedStudentCourse()");
        consolidatedStudentCourse = new HashMap<Student, HashSet<Course>>();
        HashMap<String, StudentCourse> studentCourses = systemData.getStudentCourses();
        for (Map.Entry<String, StudentCourse> entry : studentCourses.entrySet()) {
            StudentCourse studentCourse = entry.getValue();
            if (!consolidatedStudentCourse.containsKey(studentCourse.getStudent())) {
                consolidatedStudentCourse.put(studentCourse.getStudent(), new HashSet<Course>());
            }
            HashSet<Course> courseList = consolidatedStudentCourse.get(studentCourse.getStudent());
            courseList.add(studentCourse.getCourse());
            consolidatedStudentCourse.put(studentCourse.getStudent(), courseList);

//                System.out.println(studentCourse.getStudent()+ ", " + studentCourse.getCourse()
//                + ", " + studentCourse.getCourseName()+ ", " + studentCourse.getSemester()
//                                + ", " + studentCourse.getGrade());
        }
        System.out.println("==================");
        System.out.println(consolidatedStudentCourse);
    }

    public void removeStudentsAlreadyGraduated() {
        Date startDateOfSchedulingSemester = new Date(schedulingSemester.getStartDate());//start date of 2017Spring
        Iterator<Map.Entry<Student, HashSet<Course>>> it = consolidatedStudentCourse.entrySet().iterator();
        Map.Entry<Student, HashSet<Course>> entry;
        while (it.hasNext()) {
            entry = it.next();
            Student student = entry.getKey();
            HashSet<Course> studentCourse = entry.getValue();
            Date gradSemStartDate = new Date(student.getGradsemester().getStartDate());
            if (startDateOfSchedulingSemester.after(gradSemStartDate) //|| !student.getMajor().toString().equals("MSE.CE")
                    ) {
                it.remove();
            }
        }
        System.out.println("==================");
        System.out.println(consolidatedStudentCourse);
    }

    public void getcourseYetToTake() {
        coursesYetToTake = new HashMap<Student, ArrayList<CourseSet>>();
        for (Map.Entry<Student, HashSet<Course>> entry : consolidatedStudentCourse.entrySet()) {
            Student student = entry.getKey();
            HashSet<Course> coursesAlreadyTaken = entry.getValue();
            Set<Course> coursesAlreadyTakenCopy = new HashSet<Course>(coursesAlreadyTaken);
            ArrayList<CourseSet> allCoursesToGraduate = consolidatedDegreePlanReq.get(student.getMajor());
            ArrayList<CourseSet> leftCoursesToGraduate = new ArrayList<CourseSet>();
            
            System.out.println("@"+student);
            System.out.println("all:"+allCoursesToGraduate);
            System.out.println("Taken:"+coursesAlreadyTaken);
            int prevSize = coursesAlreadyTakenCopy.size();
            for (CourseSet courseSet : allCoursesToGraduate) {
                coursesAlreadyTakenCopy.removeAll(courseSet.fromBucket);
//                System.out.print((size - coursesAlreadyTakenCopy.size())+"==");
//                System.out.println(courseSet.noOfCourseToTake-(size - coursesAlreadyTakenCopy.size()));
                int noOfCourseToTake = courseSet.noOfCourseToTake-(prevSize - coursesAlreadyTakenCopy.size());
                prevSize = coursesAlreadyTakenCopy.size();
                
                
                HashSet<Course> courseBucket = new HashSet<Course>(courseSet.fromBucket);
                courseBucket.removeAll(coursesAlreadyTaken);
                
                leftCoursesToGraduate.add(new CourseSet(courseSet.isRequired, 
                        (noOfCourseToTake>0?noOfCourseToTake:0), courseBucket));
                
            }
            
            coursesYetToTake.put(student, leftCoursesToGraduate);
            System.out.println("left:" +leftCoursesToGraduate);

        }
        //System.out.println(coursesYetToTake);
        
    }

    public void getCourseNeedForExistingStudents(){
        requiredCourseNeedForExistingStudents = new HashMap<Course, ArrayList<StudentNeedValue>>() ;
        electiveCourseNeedForExistingStudents = new HashMap<Course, ArrayList<StudentNeedValue>>() ;
        ArrayList<StudentNeedValue> studentNeedValue;
        for (Map.Entry<Student, ArrayList<CourseSet>>entry : coursesYetToTake.entrySet()) {
            Student student = entry.getKey();            
            ArrayList<CourseSet> courseBuckets = entry.getValue();
            for (CourseSet courseSet : courseBuckets) {
                if(courseSet.noOfCourseToTake<=0)
                    continue;
                
                if(courseSet.isRequired.equals("Required")){
                    for(Course course : courseSet.fromBucket){
                        if(!requiredCourseNeedForExistingStudents.containsKey(course)){
                            requiredCourseNeedForExistingStudents.put(course, new ArrayList<StudentNeedValue>());                            
                        }
                        studentNeedValue = requiredCourseNeedForExistingStudents.get(course);
                        studentNeedValue.add(new StudentNeedValue(student,
                                (double)courseSet.noOfCourseToTake/courseSet.fromBucket.size()));
                        requiredCourseNeedForExistingStudents.put(course,studentNeedValue);
                    }
                    
                }
                
                else{
                    for(Course course : courseSet.fromBucket){
                         if(!electiveCourseNeedForExistingStudents.containsKey(course)){
                            electiveCourseNeedForExistingStudents.put(course, new ArrayList<StudentNeedValue>());                            
                        }
                        studentNeedValue = electiveCourseNeedForExistingStudents.get(course);
                        studentNeedValue.add(new StudentNeedValue(student,                         
                                (double)courseSet.noOfCourseToTake/courseSet.fromBucket.size()));
                        electiveCourseNeedForExistingStudents.put(course,studentNeedValue);
                    }
                }
            }
        }
        System.out.println(requiredCourseNeedForExistingStudents);
        System.out.println(electiveCourseNeedForExistingStudents);
        
    }
    
    public void getCourseNeedForExistingStudentsSum(){
        requiredCourseNeedForExistingStudentsSum = new HashMap<Course, Double>();
        electiveCourseNeedForExistingStudentsSum = new HashMap<Course, Double>();
        requiredForNoOfStudent = new HashMap<Course, Integer>();
        electiveForNoOfStudent = new HashMap<Course, Integer>();
        for (Map.Entry<Course, ArrayList<StudentNeedValue>>entry : 
                requiredCourseNeedForExistingStudents.entrySet()){
            Course course = entry.getKey();
            ArrayList<StudentNeedValue> studentNeedValueList = entry.getValue();
            double sum = 0.0;
            int i=0;
            for(StudentNeedValue studentNeedValue: studentNeedValueList){
                sum = sum + studentNeedValue.needValue;
                ++i;
            }
            requiredCourseNeedForExistingStudentsSum.put(course, sum);
            requiredForNoOfStudent.put(course, i);
        } 
        for (Map.Entry<Course, ArrayList<StudentNeedValue>>entry : 
                electiveCourseNeedForExistingStudents.entrySet()){
            Course course = entry.getKey();
            ArrayList<StudentNeedValue> studentNeedValueList = entry.getValue();
            double sum = 0.0;
            int i =0;
            for(StudentNeedValue studentNeedValue: studentNeedValueList){
                sum = sum + studentNeedValue.needValue;
                ++i;
            }
            electiveCourseNeedForExistingStudentsSum.put(course, sum);
            electiveForNoOfStudent.put(course,i);
        } 
        
        System.out.println(requiredCourseNeedForExistingStudentsSum);
        System.out.println(electiveCourseNeedForExistingStudentsSum);
        System.out.println(requiredForNoOfStudent);
        System.out.println(electiveForNoOfStudent);
    }
    
    public void getPossibleSectionsForSchedulingSemester(){
        sections = new HashMap<Course, ArrayList<Section>> ();
        HashMap<String, Course> courses = systemData.getCourses();
        for (Map.Entry<String, Course> entry : courses.entrySet()) {
            ArrayList<Section>  sectionList = new ArrayList<Section>();
            Course course = entry.getValue();
            if(schedulingSemester.toString().contains("SP") && course.isOfferedInSpring()){
                for(Faculty faculty : course.getTeachers()){
                    sectionList.add(new Section(course, faculty));
                }                
            }
            
            if(schedulingSemester.toString().contains("SU") && course.isOfferedInSummer()){
                for(Faculty faculty : course.getTeachers()){
                    sectionList.add(new Section(course, faculty));
                }
            }
            
            if(schedulingSemester.toString().contains("FA") && course.isOfferedInFall()){
                for(Faculty faculty : course.getTeachers()){
                    sectionList.add(new Section(course, faculty));
                }
            }            
            sections.put(course, sectionList);
        }        
        System.out.println(sections);
    }
    
    public static void main(String[] args) {
        Schedule schedule = new Schedule();
    }

}

class CourseSet {

    String isRequired;
    int noOfCourseToTake;
    HashSet<Course> fromBucket;

    public CourseSet(String isRequired, int noOfCourseToTake, HashSet<Course> fromBucket) {
        this.isRequired = isRequired;
        this.noOfCourseToTake = noOfCourseToTake;
        this.fromBucket = fromBucket;
    }
    
    @Override
    public String toString() {
        return "[" + isRequired + ", "+
                noOfCourseToTake + ", " + fromBucket + "]";
    }

}


class StudentNeedValue{
    Student student;
    double needValue;

    public StudentNeedValue(Student student, double needValue) {
        this.student = student;
        this.needValue = needValue;
    }

    @Override
    public String toString() {
        return student + ": " + needValue;
    }
    
}


class Section{
    Course course;
    Faculty faculty;

    public Section(Course course, Faculty faculty) {
        this.course = course;
        this.faculty = faculty;
    }    

    @Override
    public String toString() {
        return course + ": " + faculty ;
    }
}