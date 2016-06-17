package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dataManagement.LoadSystemDataOnStartUp;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dialog;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[95px][][grow][grow]", "[14px][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Administrator Log in");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 2 0,alignx leading,aligny center");
		
		Component verticalStrut = Box.createVerticalStrut(20);
		contentPane.add(verticalStrut, "cell 2 1");
		
		JLabel lblUserName = new JLabel("User Name");
		contentPane.add(lblUserName, "cell 1 2");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 2 2,alignx leading");
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		contentPane.add(lblPassword, "cell 1 3,alignx trailing");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		contentPane.add(passwordField, "cell 2 3,alignx leading");
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = textField.getText().toLowerCase();
				String password  = new String(passwordField.getPassword());

				//System.out.print(System.getProperty("user.dir"));
				//System.exit(0);
				
				HashMap<String, String> userCredentials = null;
				try{
					Scanner sc = new Scanner(new File("data/LoginCredentials.csv"));					
					userCredentials = new HashMap<String, String>();
					while(sc.hasNextLine()){
						Scanner line = new Scanner(sc.nextLine());
						line.useDelimiter(",\\s");
						userCredentials.put(line.next().toLowerCase(), line.next());
						line.close();
					}
					sc.close();
					
					if(userCredentials.get(userName)!= null && userCredentials.get(userName).equals(password)){						
						//System.out.println(userName  + "=" + password);
						LoadSystemDataOnStartUp systemData = new LoadSystemDataOnStartUp();
						systemData.load();
						new AfterLogin(systemData).go();
						setVisible(false);
					}else{
						JOptionPane.showMessageDialog(null, "Invalid Login credential. Try again!");
					}
					
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "Either \"data/LoginCredentials.csv\" file "
							+ "does not exist or file format is wrong!");	
				}
			}
		});
		contentPane.add(btnLogIn, "cell 2 4");
	}

}
