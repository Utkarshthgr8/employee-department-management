package management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GetEmployeeData extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldAge;
	private JLabel lblNewLabel;
	private JLabel lblLastName;
	private JLabel lblAge;
	private JButton btnDepartments;
	private JButton btnConnectionTest_1;
	private JButton btnConnectionTest_2;
	private JButton btnBack;
	
	backEnd be = new backEnd();
	SimpleFormatterLogging logs;
	ResultSet rs,rs2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GetEmployeeData frame = new GetEmployeeData();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void createTable() {
		//update table here
		if(be.init()) {
			rs = be.complete_emp_table();
			Object[] columnData = new Object[5];
			DefaultTableModel dtm = (DefaultTableModel)table.getModel();
			dtm.setRowCount(0);
			try {
				String str_temp = "";
				while(rs.next()) {
					columnData[0] = Integer.toString(rs.getInt("emp_id"));
					columnData[1] = rs.getString("emp_fname");
					columnData[2] = rs.getString("emp_lname");
					columnData[3] = Integer.toString(rs.getInt("emp_age"));
					rs2 = be.dept_data(rs.getInt("emp_id"));
					while(rs2.next()) {
						if(!str_temp.isEmpty()) {
							str_temp = str_temp+",";
						}
						 str_temp= str_temp + rs2.getString("dept_id");
						 columnData[4] = str_temp;
					}
					
					dtm.addRow(columnData);
					str_temp = "";
					if(columnData[4]!="") {
						columnData[4] = "";
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Connect to the databse first");
			HomePage.main(null);
			dispose();
		}
		// till here
	}

	/**
	 * Create the frame.
	 */
	public GetEmployeeData() {
		
		try {
			logs = new SimpleFormatterLogging("Logs");
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(633, 0, 633, 683);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "First Name", "Last Name", "Age", "Department Number"
			}
		));
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(321, 111, 200, 50);
		contentPane.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(321, 171, 200, 50);
		contentPane.add(textFieldLastName);
		
		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(321, 234, 200, 50);
		contentPane.add(textFieldAge);
		
		lblNewLabel = new JLabel("First Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(111, 111, 200, 50);
		contentPane.add(lblNewLabel);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastName.setBounds(111, 171, 200, 50);
		contentPane.add(lblLastName);
		
		lblAge = new JLabel("Age");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAge.setBounds(111, 234, 200, 50);
		contentPane.add(lblAge);
		
		btnDepartments = new JButton("Create");
		btnDepartments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstName = textFieldFirstName.getText();
				String lastName = textFieldLastName.getText();
				int age = Integer.parseInt(textFieldAge.getText());
				if(be.enter_emp_data(firstName, lastName, age)) {
					JOptionPane.showMessageDialog(scrollPane, firstName + " added succesfully !");
				}else {
					JOptionPane.showMessageDialog(scrollPane, "Failed to Add Employee, Try Again.");
				}
				textFieldFirstName.setText(null);
				textFieldLastName.setText(null);
				textFieldAge.setText(null);
				createTable();
			}
		});
		
		btnDepartments.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDepartments.setBounds(321, 434, 200, 50);
		contentPane.add(btnDepartments);
		
		btnConnectionTest_1 = new JButton("Reset");
		btnConnectionTest_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldFirstName.setText(null);
				textFieldLastName.setText(null);
				textFieldAge.setText(null);
				createTable();
			}
		});
		btnConnectionTest_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConnectionTest_1.setBounds(111, 434, 200, 50);
		contentPane.add(btnConnectionTest_1);
		
		btnConnectionTest_2 = new JButton("Exit");
		btnConnectionTest_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm", "Employee Department System", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					logs.logger.info("Session ended by user");
					System.exit(0);
				}
			}
		});
		btnConnectionTest_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnConnectionTest_2.setBounds(321, 499, 200, 50);
		contentPane.add(btnConnectionTest_2);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeePage.main(null);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBack.setBounds(111, 499, 200, 50);
		contentPane.add(btnBack);
		
		createTable();
	}
}
