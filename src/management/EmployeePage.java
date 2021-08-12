package management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EmployeePage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	backEnd be = new backEnd();
	SimpleFormatterLogging logs;
	ResultSet rs, rs2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeePage frame = new EmployeePage();
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
	public EmployeePage() {
		
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
		
		JPanel panel = new JPanel();
		panel.setBounds(633, 0, 633, 683);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 633, 683);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 633, 683);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setAutoCreateRowSorter(true);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Employee ID", "First Name", "Last Name", "Age", "Department Number"
			}
		));
		
		JButton btnNewButton = new JButton("Create Employee");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetEmployeeData.main(null);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(191, 169, 250, 50);
		contentPane.add(btnNewButton);
		
		JButton btnLinkupdateEmployeeDepartment = new JButton("Add Employee Department");
		btnLinkupdateEmployeeDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetEmployeeDepartment.main(null);
				dispose();
			}
		});
		btnLinkupdateEmployeeDepartment.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLinkupdateEmployeeDepartment.setBounds(191, 229, 250, 50);
		contentPane.add(btnLinkupdateEmployeeDepartment);
		
		JButton btnNewButton_1_1 = new JButton("Search/Delete");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchUpdateEmployee.main(null);
				dispose();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_1.setBounds(191, 289, 250, 50);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage.main(null);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(67, 442, 250, 50);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm", "Employee Department System", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					logs.logger.info("Session ended by user");
					System.exit(0);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(327, 442, 250, 50);
		contentPane.add(btnNewButton_2);
		
		createTable();
	}
}