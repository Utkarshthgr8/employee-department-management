package management;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SearchUpdateEmployee extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblNewLabel;
	private JTextField textFieldFirstName;
	private JLabel lblLastName;
	private JTextField textFieldLastName;
	private JLabel lblAge;
	private JTextField textFieldAge;
	private JLabel lblEmployeeId;
	private JTextField textFieldEmpId;
	
	ResultSet rs,rs2;
	backEnd be = new backEnd();
	SimpleFormatterLogging logs;
	int emp_id;
	
	public void createTable(String empId, String firstName, String lastName, String age) {
		//update table here
			if(be.init()) {
				rs = be.searchUsingAnything(empId, firstName, lastName, age);
				Object[] columnData = new Object[5];
				DefaultTableModel dtm = (DefaultTableModel)table.getModel();
				dtm.setRowCount(0);
				try {
					String str_temp = "";
					while(rs.next()) {
						columnData[0] = Integer.toString(rs.getInt("emp_id"));
						emp_id = rs.getInt("emp_id");
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
			}
		// till here
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchUpdateEmployee frame = new SearchUpdateEmployee();
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
	public SearchUpdateEmployee() {
		
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
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "First Name", "Last Name", "Age", "Department Number"
			}
		));
		
		lblNewLabel = new JLabel("First Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(111, 140, 200, 50);
		contentPane.add(lblNewLabel);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setText("");
		textFieldFirstName.setColumns(10);
		textFieldFirstName.setBounds(321, 140, 200, 50);
		contentPane.add(textFieldFirstName);
		
		lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastName.setBounds(111, 200, 200, 50);
		contentPane.add(lblLastName);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setText("");
		textFieldLastName.setColumns(10);
		textFieldLastName.setBounds(321, 200, 200, 50);
		contentPane.add(textFieldLastName);
		
		lblAge = new JLabel("Age");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAge.setBounds(111, 260, 200, 50);
		contentPane.add(lblAge);
		
		textFieldAge = new JTextField();
		textFieldAge.setText("");
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(321, 260, 200, 50);
		contentPane.add(textFieldAge);
		
		lblEmployeeId = new JLabel("Employee ID");
		lblEmployeeId.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmployeeId.setBounds(111, 80, 200, 50);
		contentPane.add(lblEmployeeId);
		
		textFieldEmpId = new JTextField();
		textFieldEmpId.setText("");
		textFieldEmpId.setColumns(10);
		textFieldEmpId.setBounds(321, 80, 200, 50);
		contentPane.add(textFieldEmpId);
		
		JButton btnNewButtonDelete = new JButton("Delete");
		btnNewButtonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean result = be.delete_emp(emp_id);
				if(result) {
					JOptionPane.showMessageDialog(btnNewButtonDelete, "Employee ID " + emp_id + " Deleted Successfully.");
				}else {
					JOptionPane.showMessageDialog(btnNewButtonDelete, "Could Not Delete Employee, try again.");
				}
				DefaultTableModel dtm = (DefaultTableModel)table.getModel();
				dtm.setRowCount(0);
			}
		});
		btnNewButtonDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButtonDelete.setBounds(321, 484, 200, 50);
		contentPane.add(btnNewButtonDelete);
		
		JButton btnNewButtonSearch = new JButton("Search");
		btnNewButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empId = textFieldEmpId.getText();
				String firstName = textFieldFirstName.getText();
				String lastName = textFieldLastName.getText();
				String age = textFieldAge.getText();
				createTable(empId, firstName, lastName, age);
				textFieldFirstName.setText("");
				textFieldLastName.setText("");
				textFieldAge.setText("");
				textFieldEmpId.setText("");
			}
		});
		btnNewButtonSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButtonSearch.setBounds(111, 424, 410, 50);
		contentPane.add(btnNewButtonSearch);
		
		JButton btnNewButtonReset = new JButton("Reset");
		btnNewButtonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldFirstName.setText("");
				textFieldLastName.setText("");
				textFieldAge.setText("");
				textFieldEmpId.setText("");
			}
		});
		btnNewButtonReset.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButtonReset.setBounds(111, 484, 200, 50);
		contentPane.add(btnNewButtonReset);
		
		JButton btnNewButton_2_2 = new JButton("Exit");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm", "Employee Department System", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					logs.logger.info("Session ended by user");
					System.exit(0);
				}
			}
		});
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2_2.setBounds(321, 544, 200, 50);
		contentPane.add(btnNewButton_2_2);
		
		JButton btnNewButton_1_2 = new JButton("Back");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeePage.main(null);
				dispose();
			}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_2.setBounds(111, 544, 200, 50);
		contentPane.add(btnNewButton_1_2);
	}
}
