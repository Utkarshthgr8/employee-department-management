package management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
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

public class SetEmployeeDepartment extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textFieldEmpID;
	private JButton btnAddDepartment;
	private JButton btnNewButton_2;
	private JTextField textFieldDeptID;
	private JLabel lblTellDepartmentId;
	private JButton btnNewButton_1;
	private JButton btnBack;
	private JButton btnNewButton_3;
	private JButton btnReset_1;
	
	backEnd be = new backEnd();
	SimpleFormatterLogging logs;
	DefaultTableModel model = new DefaultTableModel();
	ResultSet rs,rs2;
	private JTextField textFieldOldDeptID;
	private JLabel lblTellOldDepartmentId;
	boolean addFlag;
	
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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetEmployeeDepartment frame = new SetEmployeeDepartment();
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
	public SetEmployeeDepartment() {
		
		try {
			logs = new SimpleFormatterLogging("Logs");
		} catch (SecurityException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Object col[] = {"Employee ID", "First Name", "Last Name", "Age", "Department Number"};
		model.setColumnIdentifiers(col);
		
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
		
		JLabel lblNewLabelEmpId = new JLabel("Employee ID");
		lblNewLabelEmpId.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelEmpId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabelEmpId.setBounds(111, 230, 200, 50);
		lblNewLabelEmpId.setVisible(false);
		contentPane.add(lblNewLabelEmpId);
		
		textFieldEmpID = new JTextField();
		textFieldEmpID.setBounds(321, 233, 200, 50);
		textFieldEmpID.setVisible(false);
		textFieldEmpID.setColumns(10);
		contentPane.add(textFieldEmpID);
		
		btnAddDepartment = new JButton("Add Department");
		btnAddDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1.setVisible(true);
				textFieldOldDeptID.setVisible(false);
				lblTellOldDepartmentId.setVisible(false);
				textFieldDeptID.setVisible(true);
				lblTellDepartmentId.setVisible(true);
				textFieldEmpID.setVisible(true);
				lblNewLabelEmpId.setVisible(true);
				addFlag = true;
			}
		});
		btnAddDepartment.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddDepartment.setBounds(111, 173, 200, 50);
		contentPane.add(btnAddDepartment);
		
		btnNewButton_2 = new JButton("Change Department");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_1.setVisible(true);
				textFieldOldDeptID.setVisible(true);
				textFieldDeptID.setVisible(true);
				lblTellOldDepartmentId.setVisible(true);
				lblTellDepartmentId.setVisible(true);
				textFieldEmpID.setVisible(true);
				lblNewLabelEmpId.setVisible(true);
				addFlag = false;
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(321, 173, 200, 50);
		contentPane.add(btnNewButton_2);
		
		textFieldDeptID = new JTextField();
		textFieldDeptID.setColumns(10);
		textFieldDeptID.setBounds(321, 353, 200, 50);
		textFieldDeptID.setVisible(false);
		contentPane.add(textFieldDeptID);
		
		lblTellDepartmentId = new JLabel("Tell Department ID");
		lblTellDepartmentId.setHorizontalAlignment(SwingConstants.CENTER);
		lblTellDepartmentId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTellDepartmentId.setBounds(111, 350, 200, 50);
		lblTellDepartmentId.setVisible(false);
		contentPane.add(lblTellDepartmentId);
		
		btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(addFlag) {
					if(be.addEmpToDept(Integer.parseInt(textFieldEmpID.getText()), Integer.parseInt(textFieldDeptID.getText()))) {
						JOptionPane.showMessageDialog(btnNewButton_1, "Employee ID " + textFieldEmpID.getText() + " Added Successfully to Department ID " + textFieldDeptID.getText());
						int id = Integer.parseInt(textFieldEmpID.getText());
						if(textFieldEmpID.getText()!=null) {
							model.setRowCount(0);
							//update table here
							if(be.init()) {
								rs = be.searchEmpUsingID(id);
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
							}
							// till here
						}else {
							JOptionPane.showMessageDialog(btnNewButton_1, "Enter Employee ID to Search");
						}
					}else {
						JOptionPane.showMessageDialog(btnNewButton_1, "Could Not Add Employee, try again.");
					}
				}else{
					if(be.changeEmpDept(Integer.parseInt(textFieldEmpID.getText()), Integer.parseInt(textFieldOldDeptID.getText()), Integer.parseInt(textFieldDeptID.getText()))) {
						JOptionPane.showMessageDialog(btnNewButton_1, "Changed Employee ID Sucessfully!");
						int id = Integer.parseInt(textFieldEmpID.getText());
						if(textFieldEmpID.getText()!=null) {
							model.setRowCount(0);
							//update table here
							if(be.init()) {
								rs = be.searchEmpUsingID(id);
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
							}
							// till here
						}else {
							JOptionPane.showMessageDialog(btnNewButton_1, "Enter Employee ID to Search");
						}
					}else {
						JOptionPane.showMessageDialog(btnNewButton_1, "Could Not Change Department, try again.");
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(321, 413, 200, 50);
		btnNewButton_1.setVisible(false);
		contentPane.add(btnNewButton_1);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeePage.main(null);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBack.setBounds(111, 473, 200, 50);
		contentPane.add(btnBack);
		
		btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm", "Employee Department System", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					logs.logger.info("Session ended by user");
					System.exit(0);
				}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.setBounds(321, 473, 200, 50);
		contentPane.add(btnNewButton_3);
		
		btnReset_1 = new JButton("Reset");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDeptID.setText(null);
				textFieldOldDeptID.setText(null);
				textFieldEmpID.setText(null);
				createTable();
			}
		});
		btnReset_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReset_1.setBounds(111, 413, 200, 50);
		contentPane.add(btnReset_1);
		
		textFieldOldDeptID = new JTextField();
		textFieldOldDeptID.setColumns(10);
		textFieldOldDeptID.setBounds(321, 293, 200, 50);
		textFieldOldDeptID.setVisible(false);
		contentPane.add(textFieldOldDeptID);
		
		lblTellOldDepartmentId = new JLabel("Tell Old Department ID");
		lblTellOldDepartmentId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTellOldDepartmentId.setBounds(111, 290, 200, 50);
		lblTellOldDepartmentId.setVisible(false);
		contentPane.add(lblTellOldDepartmentId);
		
		createTable();
		
	}
}