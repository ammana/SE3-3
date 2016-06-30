package dataManagement;

import basicClasses.Course;
import basicClasses.Degree;
import basicClasses.DegreePlanReq;
import basicClasses.Faculty;
import basicClasses.Semester;
import basicClasses.Student;
import basicClasses.StudentCourse;
import basicClasses.Section;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Schedule {

    SystemData systemData;
    Semester schedulingSemester;
    int courseCap;
    double fillPercentage, overagePercentage;
    Object[] semesterArraySorted;
    Semester gradSemForForecastedStudents;
    
    int upperLimit;
    int lowerLimit;
    
    final int CreditHourPerCourse = 3;
    HashMap<Degree, ArrayList<CourseSet>> consolidatedDegreePlanReq;
    public HashMap<Student, HashSet<Course>> consolidatedStudentCourse;
    HashMap<Student, ArrayList<CourseSet>> coursesYetToTake;
    HashMap<Course, ArrayList<StudentNeedValue>> requiredCourseNeedForExistingStudents;
    HashMap<Course, ArrayList<StudentNeedValue>> electiveCourseNeedForExistingStudents;
    
    HashMap<Course, Double> requiredCourseNeedForExistingStudentsSum;
    HashMap<Course, Double> electiveCourseNeedForExistingStudentsSum;
    HashMap<Course, Integer> requiredForNoOfExistingStudent;
    HashMap<Course, Integer> electiveForNoOfExistingStudent;  
    
    HashMap<Course, Integer> courseForNoOfExistingStudent; 
    
    HashMap<Course, Double> courseToNoOfExistingStuDouble;       
    Map<Course, Integer> courseForNoOfExistingStudentSorted;
    HashMap<Course, ArrayList<Section>> possibleSections;  
    HashMap<Faculty, Integer> availableFacultyToNoOfSections;
    
    HashMap<Course, Double> allocatedCoursesForExistingStuDouble;
    HashMap<Course, Integer> alloccatedCoursesForExistingStudent;
    HashMap<Faculty, Integer> utilizedFacultyToNoOfSections;
    
    public HashMap<Course, ArrayList<Section>> allocatedSections;
    HashMap<Student, HashSet<Course>> allocatedStudentToRequiredCourse;
    HashMap<Student, HashSet<Course>> allocatedStudentToElectiveCourse;
    
    public HashMap<Student, Integer> studentToNoOfCoursesTaken;
    public HashMap<Student, Integer> studentToNoOfCoursesNeededToGrad;
    
    public int noOfStudents;
    public int noOfStudentsWithRequiredCourses;
    public int noOfStudentsWithoutRequiredCourses;
    
    public int noOfSections;
    public int noOfSectionsAboveCap;
    public int noOfSectionsBelowFillPercent;
    public int noOfSectionsAboveOveragePercent;
    public int noOfSectionsWithinTolerance;
    
    public int noOfStudentsWithLessThan4Sections;
    

    public Schedule(SystemData systemData, Semester schedulingSemester, double fillPercentage,
            double overagePercentage, int courseCap, Object[] semesterSortedArray ) {
        this.systemData = systemData;
        this.schedulingSemester = schedulingSemester;
        this.fillPercentage = fillPercentage;
        this.semesterArraySorted = semesterSortedArray;
        this.overagePercentage = overagePercentage;
        this.courseCap = courseCap;
        this.upperLimit = (int)(this.courseCap * (1 + (1.0*this.overagePercentage)/100));
        this.lowerLimit = (int)(this.courseCap *  (1.0*this.fillPercentage)/100);        
        gradSemForForecastedStudents = (Semester)semesterArraySorted[3];
        
        getConsolidatedDegreePlanReq();
        getConsolidatedStudentCourse();
        removeStudentsAlreadyGraduated();
        getcourseYetToTake();
        getCourseNeedForExistingStudents();
        getCourseNeedForExistingStudentsSum();
        getPossibleSectionsForSchedulingSemester();
        getCourseForNoOfExistingStudent();
        createSectionsBasedOnNeedValue();
        statisticsBeforeStudentsToSectionAllocation();
        allocateStudentsToSection();
        testSchedule();
        //getScheduleReport();
    }

    public Schedule() {
        systemData = new SystemData();
        systemData.load();
        schedulingSemester = systemData.getSemesters().get("2016FA");
        this.fillPercentage = 75;
        this.overagePercentage = 20;
        this.courseCap = 25;        
        gradSemForForecastedStudents = systemData.getSemesters().get("2018FA");
        
//        this.upperLimit = 30;
//        this.lowerLimit = 10;

        this.upperLimit = (int)(this.courseCap * (1 + (1.0*this.overagePercentage)/100));
        this.lowerLimit = (int)(this.courseCap *  (1.0*this.fillPercentage)/100);
        System.out.println("upperLimit"+this.upperLimit);
        System.out.println("lowerLimit"+this.lowerLimit);
        
        getConsolidatedDegreePlanReq();
        getConsolidatedStudentCourse();
        removeStudentsAlreadyGraduated();
        getcourseYetToTake();
        getCourseNeedForExistingStudents();
        getCourseNeedForExistingStudentsSum();
        getPossibleSectionsForSchedulingSemester();
        getCourseForNoOfExistingStudent();
        createSectionsBasedOnNeedValue();
        statisticsBeforeStudentsToSectionAllocation();
        allocateStudentsToSection();
        testSchedule();
        getScheduleReport();
    }

    public void getConsolidatedDegreePlanReq() {
        consolidatedDegreePlanReq = new HashMap<Degree, ArrayList<CourseSet>>();
        HashMap<String, DegreePlanReq> degreePlanReqInfo = systemData.getDegreePlanReqInfo();
        for (Map.Entry<String, DegreePlanReq> entry : degreePlanReqInfo.entrySet()) {
            DegreePlanReq degreePlanReq = entry.getValue();
            if (!consolidatedDegreePlanReq.containsKey(degreePlanReq.getDegree())) {
                consolidatedDegreePlanReq.put(degreePlanReq.getDegree(), new ArrayList<CourseSet>());
            }
            ArrayList<CourseSet> courseSetList = consolidatedDegreePlanReq.get(degreePlanReq.getDegree());
            
            if(degreePlanReq.getType().equals("Required")){
                courseSetList.add(0, new CourseSet(degreePlanReq.getType(), degreePlanReq.getHours() / CreditHourPerCourse,
                    degreePlanReq.getCourses()));
            }else{
                courseSetList.add(new CourseSet(degreePlanReq.getType(), degreePlanReq.getHours() / CreditHourPerCourse,
                    degreePlanReq.getCourses()));
            }
            consolidatedDegreePlanReq.put(degreePlanReq.getDegree(), courseSetList);
        } 
        
        System.out.println("==================");
        System.out.println(consolidatedDegreePlanReq);
    }

    public void getConsolidatedStudentCourse() {
        //Adding existing students to consolidatedStudentCourse who have taken at least one course
        consolidatedStudentCourse = new HashMap<Student, HashSet<Course>>();
        HashMap<String, StudentCourse> studentCourses = systemData.getStudentCourses();
        //System.out.println("fhsdffhkjj"+studentCourses.size());
        for (Map.Entry<String, StudentCourse> entry : studentCourses.entrySet()) {
            StudentCourse studentCourse = entry.getValue();
//            if(studentCourse.getStudent().getId()== 1000204)
//                System.out.println("Hiiiiiii"+studentCourse.getCourse());
            if (!consolidatedStudentCourse.containsKey(studentCourse.getStudent())) {
                consolidatedStudentCourse.put(studentCourse.getStudent(), new HashSet<Course>());
            }
            HashSet<Course> courseList = consolidatedStudentCourse.get(studentCourse.getStudent());
            Date schedulingSemesterStartDate = new Date(schedulingSemester.getStartDate());
            Date courseSemStartDate = new Date(studentCourse.getSemester().getStartDate());
            //remove student course of future semesters
            if(schedulingSemesterStartDate.after(courseSemStartDate)){
                courseList.add(studentCourse.getCourse());
                consolidatedStudentCourse.put(studentCourse.getStudent(), courseList);                
            }
        } 
        
        //Adding existing students to consolidatedStudentCourse who have taken no courses yet
        for (Map.Entry<Integer, Student> entry : systemData.getStudents().entrySet()) {
            Student student = entry.getValue();
            if (!consolidatedStudentCourse.containsKey(student)) {
                consolidatedStudentCourse.put(student, new HashSet<Course>());
            }
        }        
        System.out.println("consolidatedStudentCourse Size before adding Forecasted Students= "+ consolidatedStudentCourse.size());        
        
        /**
         * Adding Forecasted Students
         */        
        int forecastedStudentStartId = 9000000;
        System.out.println(gradSemForForecastedStudents);
        for (Map.Entry<String, Degree> entry : systemData.getDegrees().entrySet()) {
            Degree degree = entry.getValue();
            int forecast = degree.getForecast();
            for(int i=0; i<forecast; ){
                ++i;
                int id = ++forecastedStudentStartId;
                systemData.getStudents().put( id, 
                        new Student(id, degree, gradSemForForecastedStudents));
                //System.out.println("id="+id+" ,degree="+degree);
            }
        }        
        
        //Adding forecasted student to consolidatedStudentCourse
        for (Map.Entry<Integer, Student> entry : systemData.getStudents().entrySet()) {
            Student student = entry.getValue();
            if (!consolidatedStudentCourse.containsKey(student)) {
                consolidatedStudentCourse.put(student, new HashSet<Course>());
            }
        }        
        System.out.println("consolidatedStudentCourse Size after adding Forecasted Students= "+ consolidatedStudentCourse.size());                
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
                systemData.getStudents().remove(student.getId());
            }
        }
        System.out.println("consolidatedStudentCourse Size After removing graduated students="+consolidatedStudentCourse.size());
        System.out.println("==================");
        System.out.println("consolidatedStudentCourse="+consolidatedStudentCourse);
    }

    public void getcourseYetToTake() {
        studentToNoOfCoursesTaken = new HashMap<Student, Integer>();
        studentToNoOfCoursesNeededToGrad = new HashMap<Student, Integer>();
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
            studentToNoOfCoursesTaken.put(student, coursesAlreadyTaken.size());
            
            int totalNoOfCourseToTake = 0;
            int prevSize = coursesAlreadyTakenCopy.size();
            for (CourseSet courseSet : allCoursesToGraduate) {
                coursesAlreadyTakenCopy.removeAll(courseSet.fromBucket);
//                System.out.print((size - coursesAlreadyTakenCopy.size())+"==");
//                System.out.println(courseSet.noOfCourseToTake-(size - coursesAlreadyTakenCopy.size()));
                int noOfCourseToTake = courseSet.noOfCourseToTake-(prevSize - coursesAlreadyTakenCopy.size());
                prevSize = coursesAlreadyTakenCopy.size();
                
                
                HashSet<Course> courseBucket = new HashSet<Course>(courseSet.fromBucket);
                courseBucket.removeAll(coursesAlreadyTaken);
                
                noOfCourseToTake = noOfCourseToTake>0?noOfCourseToTake:0;
                leftCoursesToGraduate.add(new CourseSet(courseSet.isRequired, 
                        (noOfCourseToTake), courseBucket));
                
                totalNoOfCourseToTake = totalNoOfCourseToTake+ noOfCourseToTake;
                
            }
            
            coursesYetToTake.put(student, leftCoursesToGraduate);
            System.out.println("left:" +leftCoursesToGraduate);
            studentToNoOfCoursesNeededToGrad.put(student, totalNoOfCourseToTake);

        }
        System.out.println("coursesYetToTake= "+coursesYetToTake);
//        System.out.println("studentToNoOfCoursesTaken= "+studentToNoOfCoursesTaken);
//        System.out.println("studentToNoOfCoursesNeededToGrad= "+studentToNoOfCoursesNeededToGrad);


        /**
         * Remove courses where prerequisites are not fulfilled
         */        
        for (Map.Entry<Student, ArrayList<CourseSet>>entry : coursesYetToTake.entrySet()) {
            Student student = entry.getKey();            
            ArrayList<CourseSet> courseBuckets = entry.getValue();
            for (CourseSet courseSet: courseBuckets) {
                if(courseSet.noOfCourseToTake<=0)
                    continue;            
                HashSet<Course> courses= new HashSet(courseSet.fromBucket);
                for(Course course : courses){
                    boolean prerequisitesDone = true;
                    if(course.getPrerequisites()!=null && !course.getPrerequisites().isEmpty()){
                        for(Course preCourse: course.getPrerequisites()){
                           // System.out.println("precourse"+preCourse);
                            if(preCourse!=null && !consolidatedStudentCourse.get(student).contains(preCourse)){
                                prerequisitesDone = false;                                
                            }
                        }
                    }
                    if(!prerequisitesDone){
                        courseSet.fromBucket.remove(course);
                        if(courseSet.isRequired.equals("Required")){
                            --courseSet.noOfCourseToTake;
                        }
                    }
                }
                    
            }
        }        
        System.out.println("coursesYetToTake after removing courses where prereq courses are not taken= "+coursesYetToTake);
        
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
        requiredForNoOfExistingStudent = new HashMap<Course, Integer>();
        electiveForNoOfExistingStudent = new HashMap<Course, Integer>();
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
            requiredForNoOfExistingStudent.put(course, i);
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
            electiveForNoOfExistingStudent.put(course,i);
        } 
        
        System.out.println(requiredCourseNeedForExistingStudentsSum);
        System.out.println(electiveCourseNeedForExistingStudentsSum);
        System.out.println(requiredForNoOfExistingStudent);
        System.out.println(electiveForNoOfExistingStudent);
    }
       
    public void getCourseForNoOfExistingStudent(){
        courseForNoOfExistingStudent = new HashMap<Course, Integer>() ;
        courseForNoOfExistingStudent.putAll(requiredForNoOfExistingStudent);
        for (Map.Entry<Course, Integer>entry : electiveForNoOfExistingStudent.entrySet()){
            Course course = entry.getKey();
            if(courseForNoOfExistingStudent.containsKey(course)){
                courseForNoOfExistingStudent.put(course, 
                        requiredForNoOfExistingStudent.get(course)+
                        electiveForNoOfExistingStudent.get(course));                
//                System.out.println(course);
            }
            else{
                courseForNoOfExistingStudent.put(course,entry.getValue());
            }
        }
//        System.out.println(requiredForNoOfExistingStudent.size());
//        System.out.println(electiveForNoOfExistingStudent.size());
//        System.out.println(courseForNoOfExistingStudent.size());

        //System.out.println("courseForNoOfExistingStudent= "+courseForNoOfExistingStudent);    
        
        courseForNoOfExistingStudentSorted = 
        courseForNoOfExistingStudent.entrySet().stream()
        .sorted(Entry.<Course, Integer>comparingByValue().reversed())
        .collect(Collectors.toMap(Entry::getKey, 
                Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));        
        System.out.println("courseForNoOfExistingStudentSorted= "+courseForNoOfExistingStudentSorted);
        
        
        courseToNoOfExistingStuDouble = new HashMap<Course, Double>() ;
        courseToNoOfExistingStuDouble.putAll(requiredCourseNeedForExistingStudentsSum);
        for (Map.Entry<Course, Double>entry : electiveCourseNeedForExistingStudentsSum.entrySet()){
            Course course = entry.getKey();
            if(courseToNoOfExistingStuDouble.containsKey(course)){
                courseToNoOfExistingStuDouble.put(course, 
                        courseToNoOfExistingStuDouble.get(course)+
                        electiveCourseNeedForExistingStudentsSum.get(course)); 
            }
            else{
                courseToNoOfExistingStuDouble.put(course,entry.getValue());
            }
        }        
        courseToNoOfExistingStuDouble = 
        courseToNoOfExistingStuDouble.entrySet().stream()
        .sorted(Entry.<Course, Double>comparingByValue().reversed())
        .collect(Collectors.toMap(Entry::getKey, 
                Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        
        System.out.println("courseToNoOfExistingStuDouble= "+courseToNoOfExistingStuDouble);
                
    }
    
    public void getPossibleSectionsForSchedulingSemester(){
        possibleSections = new HashMap<Course, ArrayList<Section>> ();
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
            possibleSections.put(course, sectionList);
        }        
        System.out.println("possible unique course sections= "+possibleSections);
    }
    
    public void createSectionsBasedOnNeedValue() {
        availableFacultyToNoOfSections = new HashMap<>();
        HashMap<String, Faculty> faculties = systemData.getFaculties();
        for (Map.Entry<String, Faculty> entry : faculties.entrySet()) {
            if(schedulingSemester.toString().contains("SP"))
                availableFacultyToNoOfSections.put(entry.getValue(), entry.getValue().getMaxLoadSpring());
            if(schedulingSemester.toString().contains("SU"))
                availableFacultyToNoOfSections.put(entry.getValue(), entry.getValue().getMaxLoadSummer());
            if(schedulingSemester.toString().contains("FA"))
                availableFacultyToNoOfSections.put(entry.getValue(), entry.getValue().getMaxLoadFall());            
        } 
        System.out.println("availableNoOfFacultySections= "+availableFacultyToNoOfSections);
        
        allocatedSections = new HashMap<Course, ArrayList<Section>>();
        allocatedCoursesForExistingStuDouble = new HashMap<Course, Double>();
        utilizedFacultyToNoOfSections = new HashMap<Faculty, Integer>();
        
        Iterator<Map.Entry<Course, Double>> it = courseToNoOfExistingStuDouble.entrySet().iterator();
        while (it.hasNext()){
            int sectionNumber = 0;
            Entry entry = it.next();
            Course course = (Course) entry.getKey();
            double needValue = (double) entry.getValue();
            
            int noOfStudent = courseForNoOfExistingStudent.get(course);
//            if(noOfStudent<lowerLimit)
//                continue;
            ArrayList<Section> allocatedSection;
            if(possibleSections.containsKey(course) && !possibleSections.get(course).isEmpty()){
                ArrayList<Section> possibleSectionList = possibleSections.get(course);
                Section section = null;
                int i = possibleSectionList.size()-1;
                while (i>=0) {                    
                    if(availableFacultyToNoOfSections.containsKey((possibleSectionList.get(i).faculty))
                            && availableFacultyToNoOfSections.get((possibleSectionList.get(i).faculty))>0){
                        section = possibleSectionList.remove(i);                        
                        break;
                    }
                    --i;
                }
                              
                
                
                
                
                if(section!=null){
                    if(allocatedSections.containsKey(course)){
                        allocatedSection = allocatedSections.get(course);
                    }
                    else{
                        allocatedSection = new ArrayList<Section>();
                    }
                    allocatedSection.add(section);                    
                    allocatedSections.put(course, allocatedSection);  
                    
                    it.remove();
                    allocatedCoursesForExistingStuDouble.put(course, needValue);
                    
                    availableFacultyToNoOfSections.put(section.faculty, 
                            availableFacultyToNoOfSections.get(section.faculty)-1);
                    if(utilizedFacultyToNoOfSections.containsKey(section.faculty)){
                        utilizedFacultyToNoOfSections.put(section.faculty,
                                utilizedFacultyToNoOfSections.get(section.faculty)+1);
                    }else{
                        utilizedFacultyToNoOfSections.put(section.faculty, 1);
                    }
                }               
            }
        }
        
        allocatedCoursesForExistingStuDouble = allocatedCoursesForExistingStuDouble.entrySet().stream()
        .sorted(Entry.<Course, Double>comparingByValue().reversed())
        .collect(Collectors.toMap(Entry::getKey, 
                Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));        
        System.out.println("alloccatedCoursesForExistingStuDouble= "+allocatedCoursesForExistingStuDouble);
        
        System.out.println("allocatedSections= "+allocatedSections);
        System.out.println("alloccatedCoursesForExistingStuDouble= "+allocatedCoursesForExistingStuDouble);
        System.out.println("alloccatedCoursesForExistingStudent= "+alloccatedCoursesForExistingStudent);
        System.out.println("utilizedFacultyToNoOfSections= "+utilizedFacultyToNoOfSections);
        
        System.out.println("");
        
        System.out.println("possible sections= "+possibleSections);
        System.out.println("courseToNoOfExistingStuDouble= "+courseToNoOfExistingStuDouble);  
        System.out.println("courseForNoOfExistingStudentSorted= "+courseForNoOfExistingStudentSorted);        
        System.out.println("availableNoOfFacultySections= "+availableFacultyToNoOfSections);
        
        
    }
    
    public void statisticsBeforeStudentsToSectionAllocation(){
        noOfStudents = 0;
        noOfStudentsWithRequiredCourses = 0;
        Iterator<Entry<Student, ArrayList<CourseSet>>> it = coursesYetToTake.entrySet().iterator();
        while(it.hasNext()){
            Entry<Student, ArrayList<CourseSet>> entry = it.next();
            boolean isWithCourses = false, isWithRequiredCourses = false;
            ArrayList<CourseSet> courseSetList = entry.getValue();
            for(CourseSet courseSet: courseSetList){
                if(courseSet.noOfCourseToTake<=0)
                    continue;
                if(courseSet.isRequired.equals("Required")){
                    isWithRequiredCourses = true;
                }                
                isWithCourses = true;
            }
            if(isWithCourses) ++noOfStudents;
            if(isWithRequiredCourses)   ++noOfStudentsWithRequiredCourses;  
            if(!isWithRequiredCourses) System.out.println(entry.getKey());
        }
        noOfStudentsWithoutRequiredCourses = noOfStudents - noOfStudentsWithRequiredCourses;
    }
    
    public void allocateStudentsToSection(){ 
        allocatedStudentToRequiredCourse = new HashMap<Student, HashSet<Course>>();
        allocatedStudentToElectiveCourse = new HashMap<Student, HashSet<Course>>();
        for(Entry<Course, Double> entry: allocatedCoursesForExistingStuDouble.entrySet()){
            Course course = entry.getKey();
            for (Entry<Student, ArrayList<CourseSet>> entry1 : coursesYetToTake.entrySet()) {
                Student student = entry1.getKey();
                ArrayList<CourseSet> courseSetList = entry1.getValue();
                for(CourseSet courseSet: courseSetList){
                    if(courseSet.noOfCourseToTake>0 && courseSet.fromBucket.contains(course)){
                        boolean isAllocated = false;
                        ArrayList<Section> sectionList = allocatedSections.get(course);
                        for(Section section: sectionList){
                           if(section.students!= null && section.students.contains(student)){
                               isAllocated = true;
                           }                               
                        }
                        if(isAllocated){
                            courseSet.fromBucket.remove(course);
                        }
                        else{
                            if(!allocatedStudentToRequiredCourse.containsKey(student)){
                                allocatedStudentToRequiredCourse.put(student, new HashSet<>());
                            }if(! allocatedStudentToElectiveCourse.containsKey(student)){
                                allocatedStudentToElectiveCourse.put(student, new HashSet<>());
                            }
                            
                            if(allocatedStudentToRequiredCourse.get(student).size()+ allocatedStudentToElectiveCourse.get(student).size()<4){
                                   
                                for(int listIndex = 0; listIndex<sectionList.size(); ++listIndex){
                                    Section section = sectionList.get(listIndex);
                                    if(section.students == null ){
                                        section.students = new HashSet<Student>();
                                    } 
                                    if( section.students.size()>= upperLimit){// && listIndex<sectionList.size()-1){
                                        continue;
                                    }
                                    section.students.add(student);
                                    
                                    if(courseSet.isRequired.equals("Required"))
                                        allocatedStudentToRequiredCourse.get(student).add(course);
                                    if(!courseSet.isRequired.equals("Required"))
                                        allocatedStudentToElectiveCourse.get(student).add(course);

                                     --courseSet.noOfCourseToTake;
                                    courseSet.fromBucket.remove(course);
                                    
                                }
                            }
                        }                        
                    }
                }     
            }
        }        
        System.out.println("allocatedSections = "+allocatedSections);
        System.out.println("allocatedStudentToRequiredCourse = "+allocatedStudentToRequiredCourse);
        System.out.println("allocatedStudentToElectiveCourse = "+allocatedStudentToElectiveCourse);
        System.out.println("coursesYetToTake = "+coursesYetToTake);
    }
    
    public void allocateStudentsToSection1(){ 
        allocatedStudentToRequiredCourse = new HashMap<Student, HashSet<Course>>();
        allocatedStudentToElectiveCourse = new HashMap<Student, HashSet<Course>>();
        for(Entry<Course, Double> entry: allocatedCoursesForExistingStuDouble.entrySet()){
            Course course = entry.getKey();
            for (Entry<Student, ArrayList<CourseSet>> entry1 : coursesYetToTake.entrySet()) {
                Student student = entry1.getKey();
                ArrayList<CourseSet> courseSetList = entry1.getValue();
                for(CourseSet courseSet: courseSetList){
                    if(courseSet.noOfCourseToTake>0 && courseSet.fromBucket.contains(course)){
                        boolean isAllocated = false;
                        ArrayList<Section> sectionList = allocatedSections.get(course);
                        for(Section section: sectionList){
                           if(section.students!= null && section.students.contains(student)){
                               isAllocated = true;
                           }                               
                        }
                        if(isAllocated){
                            courseSet.fromBucket.remove(course);
                        }
                        else{
                            if(!allocatedStudentToRequiredCourse.containsKey(student)){
                                allocatedStudentToRequiredCourse.put(student, new HashSet<>());
                            }if(! allocatedStudentToElectiveCourse.containsKey(student)){
                                allocatedStudentToElectiveCourse.put(student, new HashSet<>());
                            }
                            
                            if(courseSet.isRequired.equals("Required") && allocatedStudentToRequiredCourse.get(student).size()<3
                                    || !courseSet.isRequired.equals("Required") && allocatedStudentToElectiveCourse.get(student).size()<1){
                                
                                if(courseSet.isRequired.equals("Required"))
                                    allocatedStudentToRequiredCourse.get(student).add(course);
                                if(!courseSet.isRequired.equals("Required"))
                                    allocatedStudentToElectiveCourse.get(student).add(course);
                                
                                 --courseSet.noOfCourseToTake;
                                courseSet.fromBucket.remove(course); 
                                
                                for(int listIndex = 0; listIndex<sectionList.size(); ++listIndex){
                                    Section section = sectionList.get(listIndex);
                                    if(section.students == null ){
                                        section.students = new HashSet<Student>();
                                    } 
                                    if( section.students.size()>= upperLimit && listIndex<sectionList.size()-1){
                                        continue;
                                    }
                                    section.students.add(student);
                                }
                            }
                        }                        
                    }
                }     
            }
        }        
        System.out.println("allocatedSections = "+allocatedSections);
        System.out.println("allocatedStudentToRequiredCourse = "+allocatedStudentToRequiredCourse);
        System.out.println("allocatedStudentToElectiveCourse = "+allocatedStudentToElectiveCourse);
        System.out.println("coursesYetToTake = "+coursesYetToTake);
    }
    
    public void testSchedule() {        
        System.out.println("--Test Schedule--");
        noOfSections = 0;        
        noOfSectionsAboveCap = 0;
        noOfSectionsBelowFillPercent = 0;
        noOfSectionsAboveOveragePercent = 0;
        noOfSectionsWithinTolerance = 0;        
        noOfStudentsWithLessThan4Sections = 0;
        
        Iterator<Entry<Course, ArrayList<Section>>> it= allocatedSections.entrySet().iterator();
        while(it.hasNext()){
            Entry<Course, ArrayList<Section>> entry = it.next();
            ArrayList<Section> sections = entry.getValue();
            for(Section section: sections){
                if(section.students!=null){
                    if(section.students.size()> 0)   ++noOfSections;
                    if(section.students.size()> courseCap)   ++noOfSectionsAboveCap;
                    if(section.students.size()< lowerLimit)  ++noOfSectionsBelowFillPercent;
                    if(section.students.size()> upperLimit)  ++noOfSectionsAboveOveragePercent;
                }
            }
        }
        noOfSectionsWithinTolerance = noOfSections -
                (noOfSectionsAboveOveragePercent + noOfSectionsBelowFillPercent);
        
        Iterator<Entry<Student, HashSet<Course>>> entrySet = allocatedStudentToRequiredCourse.entrySet().iterator();
        while(entrySet.hasNext()){
            Entry<Student, HashSet<Course>> entry = entrySet.next();
            if(entry.getValue().size()+ allocatedStudentToElectiveCourse.get(entry.getKey()).size() < 4)
                ++noOfStudentsWithLessThan4Sections;
        }
        
        
        System.out.println("noOfStudents= "+noOfStudents);
        System.out.println("noOfStudentsWithRequiredCourses= "+noOfStudentsWithRequiredCourses);
        System.out.println("noOfStudentsWithoutRequiredCourses= "+noOfStudentsWithoutRequiredCourses);
        
        System.out.println("noOfSections= "+noOfSections);
        System.out.println("noOfSectionsAboveCap= "+noOfSectionsAboveCap);
        System.out.println("noOfSectionsBelowFillPercent= "+noOfSectionsBelowFillPercent);
        System.out.println("noOfSectionsAboveOveragePercent= "+noOfSectionsAboveOveragePercent);
        System.out.println("noOfSectionsWithinTolerance= "+noOfSectionsWithinTolerance);
        System.out.println("noOfStudentsWithLessThan4Sections= "+noOfStudentsWithLessThan4Sections);
        
    }
    
    public void getScheduleReport(){
        System.out.println("--List of Sections--");
        System.out.println("Course     Faculty    NoOfStudent");
        Object columnNames[] = {"Course", "Faculty", "NoOfStudent"};
        Iterator<Entry<Course, ArrayList<Section>>> it= allocatedSections.entrySet().iterator();
        Object rowData[][] = new Object[noOfSections][3];
        int i = 0;
        while(it.hasNext()){
            Entry<Course, ArrayList<Section>> entry = it.next();
            ArrayList<Section> sections = entry.getValue();
            for(Section section: sections){
                if(section.students!=null){                    
                    System.out.println(section.course+"  "+section.faculty+"   "+section.students.size());
                    rowData[i][0] = section.course;
                    rowData[i][1] = section.faculty;
                    rowData[i][2] = section.students.size();                    
                }
            }
        }
        
        System.out.println("--Faculty Load--");
        System.out.println("Faculty   Courses   TotalTeachingHours");
        HashSet<Faculty> faculties = new HashSet(systemData.getFaculties().values());
        for(Faculty faculty: faculties){
            System.out.print(faculty+"  ");
            it= allocatedSections.entrySet().iterator();
            int noofSections =0;
            while(it.hasNext()){
                Entry<Course, ArrayList<Section>> entry = it.next();
                ArrayList<Section> sections = entry.getValue();
                for(Section section: sections){
                    if(section.students!=null && !section.students.isEmpty() && section.faculty.equals(faculty)){
                        System.out.print(section.course+"  ");
                        ++noofSections;
                    }
                }
            }   
            System.out.println((noofSections*3));
        }
        
        System.out.println("--Degree Plan Report--");
        System.out.println("Degree   NumberOfExistingStudent");
        HashSet<Degree> degrees = new HashSet(systemData.getDegrees().values());
        for(Degree degree: degrees){            
            System.out.print(degree+"  ");
            int noOfStudents = 0;
            Iterator<Entry<Student, HashSet<Course>>> entrySetIt= consolidatedStudentCourse.entrySet().iterator();
            while(entrySetIt.hasNext()){
                Student student = entrySetIt.next().getKey();
                if(student.getMajor().equals(degree)){
                    ++noOfStudents;
                }                    
            }
            System.out.println(noOfStudents);            
        }
        
        System.out.println("--Student-Course Report--");
        System.out.println("Student   #courseCompleted #courseNeededToGraduate");
        Iterator<Entry<Student, HashSet<Course>>> entrySetIt= consolidatedStudentCourse.entrySet().iterator();
        while(entrySetIt.hasNext()){            
            Student student = entrySetIt.next().getKey();            
            System.out.print(student+"  ");         
            System.out.print(studentToNoOfCoursesTaken.get(student) + "  ");         
            System.out.println(studentToNoOfCoursesNeededToGrad.get(student));
        }
        
    }    
    
    public Object[] getSemesterArraySorted() {
        return semesterArraySorted;
    }

    public void setSemesterArraySorted(Object[] semesterArraySorted) {
        this.semesterArraySorted = semesterArraySorted;
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


