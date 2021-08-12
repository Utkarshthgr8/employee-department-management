package management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class HomePage {

	private JFrame frame;
	
	backEnd be = new backEnd();
	SimpleFormatterLogging logs;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage window = new HomePage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomePage() {
		try {
			logs = new SimpleFormatterLogging("Logs");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
		
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(0, 0, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Employees");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(be.init()) {
					frame.dispose();
					EmployeePage ep = new EmployeePage();
					ep.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(btnNewButton, "Connect to the database first");
					frame.dispose();
					Initialise init = new Initialise();
					init.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(533, 287, 200, 50);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDepartments = new JButton("Departments");
		btnDepartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(be.init()) {
					frame.dispose();
					DepartmentPage dp = new DepartmentPage();
					dp.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(btnNewButton, "Connect to the database first");
					frame.dispose();
					Initialise init = new Initialise();
					init.setVisible(true);
				}
			}
		});
		btnDepartments.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDepartments.setBounds(533, 347, 200, 50);
		frame.getContentPane().add(btnDepartments);
		
		JButton btnConnectionTest = new JButton("Connect");
		btnConnectionTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Initialise init = new Initialise();
				init.setVisible(true);
			}
		});
		btnConnectionTest.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConnectionTest.setBounds(533, 227, 200, 50);
		frame.getContentPane().add(btnConnectionTest);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm", "Employee Department System", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					logs.logger.info("Session ended by user");
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(533, 407, 200, 50);
		frame.getContentPane().add(btnExit);
	}
}