package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataManagement.LoadSystemDataOnStartUp;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class AfterLogin extends JFrame {

	private JPanel contentPane;
	private LoadSystemDataOnStartUp systemData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AfterLogin frame = new AfterLogin(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void go() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AfterLogin frame = new AfterLogin(systemData);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AfterLogin(LoadSystemDataOnStartUp systemData) {
		this.systemData = systemData;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMaintain = new JMenu("Maintain");
		menuBar.add(mnMaintain);
		
		JMenuItem mntmCourse = new JMenuItem("Course");
		mnMaintain.add(mntmCourse);
		
		JMenuItem mntmFaculty = new JMenuItem("Faculty");
		mnMaintain.add(mntmFaculty);
		
		JMenuItem mntmDegree = new JMenuItem("Degree");
		mnMaintain.add(mntmDegree);
		
		JMenu mnSchedule = new JMenu("Schedule");
		menuBar.add(mnSchedule);
		
		JMenuItem mntmGenerateSchedule = new JMenuItem("Generate Schedule");
		mnSchedule.add(mntmGenerateSchedule);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Test Schedule");
		mnSchedule.add(mntmNewMenuItem);
		
		JMenu mnReport = new JMenu("Report");
		menuBar.add(mnReport);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Schedule Report");
		mnReport.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Student Report");
		mnReport.add(mntmNewMenuItem_2);
		
		JMenu mnImport = new JMenu("Import");
		menuBar.add(mnImport);
		
		JMenuItem mntmImportStudent = new JMenuItem("Import Student");
		mnImport.add(mntmImportStudent);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Import Student Courses");
		mnImport.add(mntmNewMenuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
