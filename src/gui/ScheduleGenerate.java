/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import basicClasses.Course;
import basicClasses.Faculty;
import basicClasses.Section;
import basicClasses.Semester;
import dataManagement.SystemData;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import dataManagement.Schedule;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author mounika
 */
public class ScheduleGenerate extends javax.swing.JPanel {
    JFrame  panelHolder;
    SystemData systemData;
    int courseCap;
    Object[] semesterArraySorted;
    /**
     * Creates new form ImportStudent
     */
    public ScheduleGenerate(JFrame  panelHolder, SystemData systemData) {
        this.panelHolder = panelHolder;
        this.systemData = systemData;  
        initComponents();
        
        HashSet<Semester> semesters = new HashSet(systemData.getSemesters().values());
        HashMap<Semester, Date> semesterToStartDate = new HashMap<Semester, Date>();
        for(Semester semester: semesters){
            Date startDate = new Date(semester.getStartDate());
            Date systemDate = new Date();//new Date(System.getProperty("date"))
            if(startDate.after(systemDate)){                
                semesterToStartDate.put(semester, startDate);
            }
        }
        semesterToStartDate = semesterToStartDate.entrySet().stream()
        .sorted(Map.Entry.<Semester, Date>comparingByValue())
        .collect(Collectors.toMap(Map.Entry::getKey, 
                Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new)); 
        //System.out.println("Semester sorted by startDate:"+semesterToStartDate);
        
        semesterArraySorted = semesterToStartDate.keySet().toArray();
        DefaultComboBoxModel semesterList = new DefaultComboBoxModel(semesterArraySorted);
        selectSemester.setModel(semesterList);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        generateButton = new javax.swing.JButton();
        selectSemester = new javax.swing.JComboBox<>();
        fillPercentageTxt = new javax.swing.JTextField();
        overagePercentageTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        courseCapacity = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Schedule Generation");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Semester");

        jLabel3.setText("Fill Percentage");

        jLabel4.setText("Overage Percentage");

        generateButton.setText("Generate");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        selectSemester.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        fillPercentageTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        fillPercentageTxt.setText("75");

        overagePercentageTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        overagePercentageTxt.setText("20");

        courseCapacity.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        courseCapacity.setText("25");

        jLabel6.setText("Course Capacity");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generateButton)
                    .addComponent(selectSemester, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fillPercentageTxt)
                    .addComponent(overagePercentageTxt)
                    .addComponent(courseCapacity))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(selectSemester, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fillPercentageTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(overagePercentageTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(courseCapacity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(generateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(50, 86, 50, 74);
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        String selectedSemester = selectSemester.getSelectedItem().toString();
        Semester schedulingSemmester = systemData.getSemesters().get(selectedSemester);        
        double fillPercentage, overagePercentage;
        try{
            fillPercentage = Double.parseDouble(fillPercentageTxt.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please enter decimal value for Fill Percentage");   
            return;
        }
        try{
            overagePercentage = Double.parseDouble(overagePercentageTxt.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please enter decimal value for Overage Percentage");   
            return;
        } 
        try{
            courseCap = Integer.parseInt(courseCapacity.getText());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Please enter integer value for Course Capacity");   
            return;
        }
        Schedule schedule = new Schedule(systemData, schedulingSemmester, fillPercentage,
                overagePercentage, courseCap, semesterArraySorted);
        systemData.setSchedule(schedule);
        JOptionPane.showMessageDialog(null, "Schedule Generated"); 
        
        
        
        String term = schedulingSemmester.toString();       
        String academicLevel = "Graduate";
        Integer credits = 3;
        Object columnNames[] = {"Term", "Section", "Meeting Information", "Faculty",
            "Available/Capacity", "Credits", "Academic level"};            
        
        HashMap<String, String> days = new HashMap<>();
        days.put("M", "Monday");
        days.put("T", "Tuesday");
        days.put("W", "Wednesday");
        days.put("R", "Thursday");
        days.put("F", "Friday");
        
        HashMap<Faculty, String> facultyDaysAlreadyTaken = new HashMap();
        
        Iterator<Map.Entry<Course, ArrayList<Section>>> it= 
                systemData.getSchedule().allocatedSections.entrySet().iterator();
        Object rowData[][] = new Object[systemData.getSchedule().noOfSections][7];
        int i = 0;
        while(it.hasNext()){
            Map.Entry<Course, ArrayList<Section>> entry = it.next();
            ArrayList<Section> sections = entry.getValue();
            for(Section section: sections){
                if(section.students!=null){                    
                    System.out.println(section.course+"  "+section.faculty+"   "+section.students.size());
                    
                    rowData[i][0] = term;                    
                    //rowData[i][1] = section.course+"("+section.sectionNumber +"): "+section.course.getCourseName();
                    rowData[i][1] = section.course+": "+section.course.getCourseName();
                    rowData[i][3] = section.faculty;
                    rowData[i][4] = section.students.size()+"/"+courseCap; 
                    rowData[i][5] = credits; 
                    rowData[i][6] = academicLevel; 
                    
                    if(facultyDaysAlreadyTaken.get(section.faculty)==null)
                        facultyDaysAlreadyTaken.put(section.faculty,"");
                    String daysAlreadyTaken = facultyDaysAlreadyTaken.get(section.faculty);
                    String shortDay = section.faculty.getDaysToTeach().
                            replace(daysAlreadyTaken, "").substring(0, 1);
                    
                    daysAlreadyTaken = daysAlreadyTaken+shortDay;
                    if(daysAlreadyTaken.equals(section.faculty.getDaysToTeach()))
                        daysAlreadyTaken = "";
                    facultyDaysAlreadyTaken.put(section.faculty, daysAlreadyTaken);
                    
                    rowData[i][2] = days.get(shortDay);
                    
                    ++i;
                }
            }
        }
        
        panelHolder.setTitle("Schedule at a Glance");
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(new ViewScheduling(panelHolder, systemData, "ScheduleGenerate",
                "Schedule at a Glance", rowData, columnNames));
        panelHolder.getContentPane().revalidate(); 
    }//GEN-LAST:event_generateButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField courseCapacity;
    private javax.swing.JTextField fillPercentageTxt;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField overagePercentageTxt;
    private javax.swing.JComboBox<String> selectSemester;
    // End of variables declaration//GEN-END:variables
}
