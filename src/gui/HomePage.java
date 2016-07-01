/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dataManagement.SystemData;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author mounika
 */
public class HomePage extends javax.swing.JPanel {
    JFrame  panelHolder;
    SystemData systemData;
    DefaultListModel model;
    
    /**
     * Creates new form HomePage
     * @param panelHolder
     * @param systemData
     */
    public HomePage(JFrame  panelHolder, SystemData systemData, boolean isFirstLogin) {
        this.panelHolder = panelHolder;
        this.systemData = systemData;        
        initComponents();
        if(!isFirstLogin){
            jLabel1.setText("Home Page");
            jLabel2.setText("");
        }
        
        
        JMenuBar menuBar = new JMenuBar();
        panelHolder.setJMenuBar(menuBar);

        JMenu mnMaintain = new JMenu("Maintain");
        menuBar.add(mnMaintain);
        JMenuItem mntmCourse = new JMenuItem("Course");
        mnMaintain.add(mntmCourse);
        mntmCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Course Maintainance");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new CourseMaintainance(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmFaculty = new JMenuItem("Faculty");
        mnMaintain.add(mntmFaculty);        
        mntmFaculty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Faculty Maintainance");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new FacultyMaintainance(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmDegree = new JMenuItem("Degree");
        mnMaintain.add(mntmDegree);
        mntmDegree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Degree Maintainance");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new DegreeMaintainance(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmSemester = new JMenuItem("Semester");
        mnMaintain.add(mntmSemester);
        mntmSemester.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Semester Maintainance");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new SemesterMaintainance(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
                
        
        JMenu mnSchedule = new JMenu("Schedule");
        menuBar.add(mnSchedule);
        JMenuItem mntmGenerateSchedule = new JMenuItem("Generate Schedule");
        mnSchedule.add(mntmGenerateSchedule);
        mntmGenerateSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Generate Schedule");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ScheduleGenerate(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        JMenuItem mntmNewMenuItem = new JMenuItem("Test Schedule");
        mnSchedule.add(mntmNewMenuItem);
        mntmNewMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(systemData.getSchedule()==null){                    
                    JOptionPane.showMessageDialog(null, "Please Genereate the Schedule first!");   
                    return;
                }
                panelHolder.setTitle("Test Schedule");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ScheduleTest(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });

        JMenu mnReport = new JMenu("Report");
        menuBar.add(mnReport);
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("Schedule Report");
        mnReport.add(mntmNewMenuItem_1);
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(systemData.getSchedule()==null){                    
                    JOptionPane.showMessageDialog(null, "Please Genereate the Schedule first!");   
                    return;
                }
                panelHolder.setTitle("Schedule Report");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ScheduleReport(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmNewMenuItem_2 = new JMenuItem("Student Report");
        mnReport.add(mntmNewMenuItem_2);
        mntmNewMenuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(systemData.getSchedule()==null){                    
                    JOptionPane.showMessageDialog(null, "Please Genereate the Schedule first!");   
                    return;
                }
                panelHolder.setTitle("Student Report");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new StudentReport(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenu mnImport = new JMenu("Import");
        menuBar.add(mnImport);
        JMenuItem mntmImportStudent = new JMenuItem("Import Student");
        mnImport.add(mntmImportStudent);
        mntmImportStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Import Student");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ImportStudent(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
        
        JMenuItem mntmNewMenuItem_3 = new JMenuItem("Import Student Courses");
        mnImport.add(mntmNewMenuItem_3);
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Import Student Courses");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ImportStudentCourse(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();        
            }
        });
                
        JMenu mnCurUser = new JMenu(systemData.getCurrentUser().getName());
        menuBar.add(mnCurUser);
        JMenuItem mntmChangePassword = new JMenuItem("Change Password");
        mnCurUser.add(mntmChangePassword);
        mntmChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Change Password");
                panelHolder.getContentPane().removeAll();
		panelHolder.getContentPane().add(new ChangePassword(panelHolder, systemData));
		panelHolder.getContentPane().revalidate();      
            }
        });   
        
        JMenuItem mntmLogout = new JMenuItem("Logout");
        mnCurUser.add(mntmLogout);
        mntmLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelHolder.setTitle("Login Page");
                //panelHolder.removeAll();
                //panelHolder.setExtendedState(JFrame.MAXIMIZED_BOTH);
                panelHolder.setJMenuBar(null);
                panelHolder.getContentPane().removeAll();                                
		panelHolder.getContentPane().add(new LogInPanel(panelHolder, systemData.getUserCredentials()));
		panelHolder.getContentPane().revalidate();      
            }
        });       
        
             
        
        Object[] gradSchoolCodeArray = systemData.getGradSchools().keySet().toArray();
        String[] gradSchoolName = new String[gradSchoolCodeArray.length];
        int i = 0;
        for(Object o : gradSchoolCodeArray){
            gradSchoolName[i++] = systemData.getGradSchools().get(o).getName();
        }
        gradSchool.setModel(new DefaultComboBoxModel(gradSchoolName));
        
        Object[] coursesArray = systemData.getCourses().keySet().toArray();
        String[] courseStringArray = new String[coursesArray.length];
        i =0;
        for(Object object: coursesArray){
            courseStringArray[i++] = systemData.getCourses().get(object).getCourseCode()+
                    ": "+systemData.getCourses().get(object).getCourseName();
        }
        model = new DefaultListModel();
        for(String course : courseStringArray)
            model.addElement(course);
        
        courseList.setModel(model);
                
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        gradSchool = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        courseList = new javax.swing.JList<>();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Oklahoma Christian University");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setText("Graduate School:");

        gradSchool.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gradSchool, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gradSchool)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setText("Courses offered:");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        courseList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        courseList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(courseList);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Login successful!");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(" Please go ahead with the task you would like to perform.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteButton)
                        .addGap(18, 18, 18)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(editButton))
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 30, 0, 30);
        add(jPanel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        //System.out.println(courseList.getSelectedValue());
        if(courseList.getSelectedValue() == null){
            JOptionPane.showMessageDialog(null, "Please select a Course to Delete"); 
            return;         
        }
        String selectedCourseCode = courseList.getSelectedValue().split(":")[0];
        //System.out.println(selectedCourseCode);
        systemData.getCourses().remove(selectedCourseCode);
        model.remove(courseList.getSelectedIndex());
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        if(courseList.getSelectedValue() == null){
            JOptionPane.showMessageDialog(null, "Please select a Course to Edit"); 
            return;         
        }
        String selectedCourseCode = courseList.getSelectedValue().split(":")[0];
        panelHolder.setTitle("Edit Course");
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(new CourseAddEdit(panelHolder, systemData,
                true, systemData.getCourses().get(selectedCourseCode), 1));
        panelHolder.getContentPane().revalidate(); 
    }//GEN-LAST:event_editButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> courseList;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JComboBox<String> gradSchool;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
