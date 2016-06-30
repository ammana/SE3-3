/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import basicClasses.Course;
import basicClasses.Degree;
import basicClasses.DegreePlanReq;
import dataManagement.SystemData;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mounika
 */
public class DegreeAddEdit extends javax.swing.JPanel {

    JFrame panelHolder;
    SystemData systemData;
    boolean isEdit;
    Degree selectedDegree;

    /**
     * Creates new form ImportStudent
     */
    public DegreeAddEdit(JFrame panelHolder, SystemData systemData, boolean isEdit, Degree selectedDegree) {
        this.panelHolder = panelHolder;
        this.systemData = systemData;
        this.isEdit = isEdit;
        this.selectedDegree = selectedDegree;
        initComponents();
        gradSchool.setModel(new DefaultComboBoxModel(systemData.getGradSchools().keySet().toArray()));
        DefaultListModel model1 = new DefaultListModel();
        Object[] courseArray = systemData.getCourses().keySet().toArray();
        HashMap<Object, Integer> courseToIndexMap = new HashMap<>();
        for (Object course : courseArray) {
            model1.addElement(course);
            courseToIndexMap.put(course, courseToIndexMap.size());
        }
        option1.setModel(model1);
        option2.setModel(model1);
        option3.setModel(model1);

        if (isEdit) {
            jLabel1.setText("Edit Degree");
            degreeCode.setText(selectedDegree.getCode());
            degreeName.setText(selectedDegree.getName());
            gradSchool.setSelectedItem(selectedDegree.getGradSchool());
            forecast.setText("" + selectedDegree.getForecast());

            int i = 0;
            Collection<DegreePlanReq> degreePlanReqInfo = systemData.getDegreePlanReqInfo().values();
            for (DegreePlanReq degreePlanReq : degreePlanReqInfo) {
                if (degreePlanReq.getDegree().toString().equals(selectedDegree.toString())) {
                    ++i;
                    System.out.println(i + ". gui.DegreeAddEdit()");
                    if (i == 1) {
                        description1.setText(degreePlanReq.getDescription());
                        hours1.setText(degreePlanReq.getHours() + "");
                        type1.setSelectedItem(degreePlanReq.getType());
                        HashSet<Course> courses = degreePlanReq.getCourses();
                        int[] selectedIndices = new int[courses.size()];
                        int index = 0;
                        for (Course course : courses) {
                            System.out.println(course + ": " + courseToIndexMap.get(course.toString()));
                            selectedIndices[index++] = courseToIndexMap.get(course.toString());
                        }
                        option1.setSelectedIndices(selectedIndices);
                    }
                    if (i == 2) {
                        description2.setText(degreePlanReq.getDescription());
                        hours2.setText(degreePlanReq.getHours() + "");
                        type2.setSelectedItem(degreePlanReq.getType());

                        HashSet<Course> courses = degreePlanReq.getCourses();
                        int[] selectedIndices = new int[courses.size()];
                        int index = 0;
                        for (Course course : courses) {
                            System.out.println(course + ": " + courseToIndexMap.get(course.toString()));
                            selectedIndices[index++] = courseToIndexMap.get(course.toString());
                        }
                        option2.setSelectedIndices(selectedIndices);
                    }
                    if (i == 3) {
                        description3.setText(degreePlanReq.getDescription());
                        hours3.setText(degreePlanReq.getHours() + "");
                        type3.setSelectedItem(degreePlanReq.getType());

                        HashSet<Course> courses = degreePlanReq.getCourses();
                        int[] selectedIndices = new int[courses.size()];
                        int index = 0;
                        for (Course course : courses) {
                            System.out.println(course + ": " + courseToIndexMap.get(course.toString()));
                            selectedIndices[index++] = courseToIndexMap.get(course.toString());
                        }
                        option3.setSelectedIndices(selectedIndices);
                    }
                }

            }

        }

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
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        option1Label = new javax.swing.JLabel();
        option2Label = new javax.swing.JLabel();
        option3Label = new javax.swing.JLabel();
        degreeCode = new javax.swing.JTextField();
        gradSchool = new javax.swing.JComboBox<>();
        degreeName = new javax.swing.JTextField();
        forecast = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        option1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        option2 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        option3 = new javax.swing.JList<>();
        cancelButton = new javax.swing.JButton();
        save = new javax.swing.JButton();
        description1 = new javax.swing.JTextField();
        hours1 = new javax.swing.JTextField();
        type1 = new javax.swing.JComboBox<>();
        description2 = new javax.swing.JTextField();
        hours2 = new javax.swing.JTextField();
        type2 = new javax.swing.JComboBox<>();
        description3 = new javax.swing.JTextField();
        hours3 = new javax.swing.JTextField();
        type3 = new javax.swing.JComboBox<>();

        setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Add a new Degree");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Degree Code");

        jLabel3.setText("Grad School");

        jLabel4.setText("Degree Name");

        jLabel5.setText("Forecast");

        option1Label.setText("Course Bucket 1");

        option2Label.setText("Course Bucket 2");

        option3Label.setText("Course Bucket 3");

        gradSchool.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        option1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(option1);

        option2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(option2);

        option3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(option3);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        description1.setText("Description");
        description1.setToolTipText("Description");
        description1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                description1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                description1FocusLost(evt);
            }
        });

        hours1.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        hours1.setText("Hours");
        hours1.setToolTipText("Hours");
        hours1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hours1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hours1FocusLost(evt);
            }
        });

        type1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Required", "Elective" }));
        type1.setToolTipText("Type");

        description2.setText("Description");
        description2.setToolTipText("Description");
        description2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                description2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                description2FocusLost(evt);
            }
        });

        hours2.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        hours2.setText("Hours");
        hours2.setToolTipText("Hours");
        hours2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hours2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hours2FocusLost(evt);
            }
        });

        type2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Required", "Elective"}));
        type2.setToolTipText("Type");

        description3.setText("Description");
        description3.setToolTipText("Description");
        description3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                description3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                description3FocusLost(evt);
            }
        });

        hours3.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        hours3.setText("Hours");
        hours3.setToolTipText("Hours");
        hours3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                hours3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                hours3FocusLost(evt);
            }
        });

        type3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Required", "Elective" }));
        type3.setToolTipText("Type");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(option1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(option3Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(option2Label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(degreeName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(degreeCode)
                    .addComponent(gradSchool, javax.swing.GroupLayout.Alignment.TRAILING, 0, 224, Short.MAX_VALUE)
                    .addComponent(forecast))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cancelButton)
                        .addGap(55, 55, 55))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(description3)
                            .addComponent(description2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(description1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hours3)
                            .addComponent(hours1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(hours2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(type2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(type1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(type3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(degreeCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(gradSchool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(degreeName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(forecast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(option1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(hours1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(type1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(description1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 25, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(option2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(description2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(hours2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(type2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(description3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(hours3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(type3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(option3Label, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelButton)
                    .addComponent(save)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 10, 23, 31);
        add(jPanel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        int forecastInt = -1;
        int hours1int, hours2int, hours3int;
        try {
            forecastInt = Integer.parseInt(forecast.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter an integer forecast value!");
            return;
        }
        try {
            hours1int = Integer.parseInt(hours1.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter an integer Hours for Course Bucket 1");
            return;
        }
        try {
            hours2int = Integer.parseInt(hours2.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter an integer Hours for Course Bucket 2");
            return;
        }
        try {
            hours3int = Integer.parseInt(hours3.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please enter an integer Hours for Course Bucket 3");
            return;
        }

        List courseList1 = option1.getSelectedValuesList();
        List courseList2 = option2.getSelectedValuesList();
        List courseList3 = option3.getSelectedValuesList();
        HashSet<Course> courseSet1, courseSet2, courseSet3;
        courseSet1 = new HashSet<>();
        courseSet2 = new HashSet<>();
        courseSet3 = new HashSet<>();
        for (Object course : courseList1) {
            courseSet1.add(systemData.getCourses().get(course));
        }
        for (Object course : courseList2) {
            courseSet2.add(systemData.getCourses().get(course));
        }
        for (Object course : courseList3) {
            courseSet3.add(systemData.getCourses().get(course));
        }

        if (isEdit) {
            Degree degree = new Degree(degreeCode.getText(),
                    systemData.getGradSchools().get(gradSchool.getSelectedItem()),
                    degreeName.getText(), forecastInt);

            HashMap<String, Degree> degrees = systemData.getDegrees();
            System.out.print(degrees.remove(selectedDegree.getCode()));
            degrees.put(degreeCode.getText(), degree);
            systemData.setDegrees(degrees);

            DegreePlanReq degreePlanReq1 = new DegreePlanReq(selectedDegree, description1.getText(), hours1int,
                    type1.getSelectedItem().toString(), courseSet1);
            DegreePlanReq degreePlanReq2 = new DegreePlanReq(selectedDegree, description2.getText(), hours2int,
                    type2.getSelectedItem().toString(), courseSet2);
            DegreePlanReq degreePlanReq3 = new DegreePlanReq(selectedDegree, description3.getText(), hours3int,
                    type3.getSelectedItem().toString(), courseSet3);

            System.out.println("====" + new HashSet(option1.getSelectedValuesList()));

            Iterator<Map.Entry<String, DegreePlanReq>> it = systemData.getDegreePlanReqInfo().entrySet().iterator();
            while (it.hasNext()) {
                DegreePlanReq degreePlanReq = it.next().getValue();
                if (degreePlanReq.getDegree().toString().equals(selectedDegree.toString())) {
                    it.remove();
                }
            }
            systemData.getDegreePlanReqInfo().put(selectedDegree.toString() + ": " + description1.getText(), degreePlanReq1);
            systemData.getDegreePlanReqInfo().put(selectedDegree.toString() + ": " + description2.getText(), degreePlanReq2);
            systemData.getDegreePlanReqInfo().put(selectedDegree.toString() + ": " + description3.getText(), degreePlanReq3);

            JOptionPane.showMessageDialog(null, "Edit was saved sucessfully");
        } else {
            Degree degree = new Degree(degreeCode.getText(),
                    systemData.getGradSchools().get(gradSchool.getSelectedItem()),
                    degreeName.getText(),
                    forecastInt);
            HashMap<String, Degree> degrees = systemData.getDegrees();
            if (degrees.containsKey(degreeCode.getText())) {
                JOptionPane.showMessageDialog(null, "A degree with same code already exists!"
                        + "\nTry another code.");
                return;
            }
            degrees.put(degreeCode.getText(), degree);
            systemData.setDegrees(degrees);
            
            DegreePlanReq degreePlanReq1 = new DegreePlanReq(degree, 
                    description1.getText(), hours1int, type1.getSelectedItem().toString(), courseSet1);
            DegreePlanReq degreePlanReq2 = new DegreePlanReq(degree,
                    description2.getText(), hours2int, type2.getSelectedItem().toString(), courseSet2);
            DegreePlanReq degreePlanReq3 = new DegreePlanReq(degree, 
                    description3.getText(), hours3int, type3.getSelectedItem().toString(), courseSet3);            
            
            systemData.getDegreePlanReqInfo().put(degree + ": " + description1.getText(), degreePlanReq1);
            systemData.getDegreePlanReqInfo().put(degree + ": " + description2.getText(), degreePlanReq2);
            systemData.getDegreePlanReqInfo().put(degree + ": " + description3.getText(), degreePlanReq3);
            JOptionPane.showMessageDialog(panelHolder, "New Degree was saved sucessfully");
        }

        System.out.println(option1.getSelectedValuesList() + "" + option2.getSelectedValuesList() + "" + option3.getSelectedValuesList());

        panelHolder.setTitle("Degree Maintainance");
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(new DegreeMaintainance(panelHolder, systemData));
        panelHolder.getContentPane().revalidate();
    }//GEN-LAST:event_saveActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        panelHolder.setTitle("Degree Maintainance");
        panelHolder.getContentPane().removeAll();
        panelHolder.getContentPane().add(new DegreeMaintainance(panelHolder, systemData));
        panelHolder.getContentPane().revalidate();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void description1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_description1FocusGained
        if (description1.getText().equals("Description")) {
            description1.setText("");
        }
    }//GEN-LAST:event_description1FocusGained

    private void description1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_description1FocusLost
        if (description1.getText().equals("")) {
            description1.setText("Description");
        }
    }//GEN-LAST:event_description1FocusLost

    private void description2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_description2FocusGained
        if (description2.getText().equals("Description")) {
            description2.setText("");
        }
    }//GEN-LAST:event_description2FocusGained

    private void description2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_description2FocusLost
        if (description2.getText().equals("")) {
            description2.setText("Description");
        }
    }//GEN-LAST:event_description2FocusLost

    private void description3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_description3FocusGained
        if (description3.getText().equals("Description")) {
            description3.setText("");
        }
    }//GEN-LAST:event_description3FocusGained

    private void description3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_description3FocusLost
        if (description3.getText().equals("")) {
            description3.setText("Description");
        }
    }//GEN-LAST:event_description3FocusLost

    private void hours1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hours1FocusGained
        if (hours1.getText().equals("Hours")) {
            hours1.setText("");
        }
    }//GEN-LAST:event_hours1FocusGained

    private void hours1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hours1FocusLost
        if (hours1.getText().equals("")) {
            hours1.setText("Hours");
        }
    }//GEN-LAST:event_hours1FocusLost

    private void hours2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hours2FocusGained
        if (hours2.getText().equals("Hours")) {
            hours2.setText("");
        }
    }//GEN-LAST:event_hours2FocusGained

    private void hours2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hours2FocusLost
        if (hours2.getText().equals("")) {
            hours2.setText("Hours");
        }
    }//GEN-LAST:event_hours2FocusLost

    private void hours3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hours3FocusGained
        if (hours3.getText().equals("Hours")) {
            hours3.setText("");
        }
    }//GEN-LAST:event_hours3FocusGained

    private void hours3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hours3FocusLost
        if (hours3.getText().equals("")) {
            hours3.setText("Hours");
        }
    }//GEN-LAST:event_hours3FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField degreeCode;
    private javax.swing.JTextField degreeName;
    private javax.swing.JTextField description1;
    private javax.swing.JTextField description2;
    private javax.swing.JTextField description3;
    private javax.swing.JTextField forecast;
    private javax.swing.JComboBox<String> gradSchool;
    private javax.swing.JTextField hours1;
    private javax.swing.JTextField hours2;
    private javax.swing.JTextField hours3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> option1;
    private javax.swing.JLabel option1Label;
    private javax.swing.JList<String> option2;
    private javax.swing.JLabel option2Label;
    private javax.swing.JList<String> option3;
    private javax.swing.JLabel option3Label;
    private javax.swing.JButton save;
    private javax.swing.JComboBox<String> type1;
    private javax.swing.JComboBox<String> type2;
    private javax.swing.JComboBox<String> type3;
    // End of variables declaration//GEN-END:variables
}
