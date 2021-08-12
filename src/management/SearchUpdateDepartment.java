package management;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class SearchUpdateDepartment extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnDelete;
	private JLabel lblNewLabel;
	private JTextField textFieldDeptName;
	private JButton btnBack;
	private JButton btnExit;
	private JButton btnSearch;
	private JLabel lblNewLabel_1;
	private JTextField textFieldDeptId;
	private JButton btnReset;
	
	backEnd be = new backEnd();
	ResultSet rs,rs2;
	SimpleFormatterLogging logs;
	String str_temp = "";
	int dept_id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchUpdateDepartment frame = new SearchUpdateDepartment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void createTable(String _dept_id, String dept_name) {
		//update table here
		if(be.init()) {
			rs = be.searchDept(_dept_id, dept_name);
			Object[] columnData = new Object[3];
			DefaultTableModel dtm = (DefaultTableModel)table.getModel();
			dtm.setRowCount(0);
			try {
				while(rs.next()) {
					columnData[0] = rs.getInt("dept_id");
					dept_id = Integer.parseInt(rs.getString("dept_id"));
					columnData[1] = rs.getString("dept_name");
					rs2 = be.empIdUsingDeptid(rs.getInt("dept_id"));
					rs2 = be.empIdUsingDeptid(Integer.parseInt(rs.getString("dept_id")));
					while(rs2.next()) {
						if(!str_temp.isEmpty()) {
							str_temp = str_temp+",";
						}
						 str_temp= str_temp + rs2.getString("emp_id");
					}
					columnData[2] = str_temp;
					dtm.addRow(columnData);
					str_temp = "";
					if(columnData[2]!="") {
						columnData[2] = "";
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
	 * Create the frame.
	 */
	public SearchUpdateDepartment() {
		
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
				"Department ID", "Department Name", "Employee ID"
			}
		));
		
		btnDelete = new JButton("Delete");
		btnDelete.setVisible(false);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(be.delete_dept(dept_id)) {
					JOptionPane.showMessageDialog(btnDelete, "Department ID " + dept_id + " deleted successfully.");
				}else {
					JOptionPane.showMessageDialog(btnBack, "Could not delete department");
				}
				DefaultTableModel dtm = (DefaultTableModel)table.getModel();
				dtm.setRowCount(0);
				textFieldDeptName.setText(null);
				textFieldDeptId.setText(null);
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBounds(320, 326, 200, 50);
		contentPane.add(btnDelete);
		
		lblNewLabel = new JLabel("Enter Department Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(110, 206, 200, 50);
		contentPane.add(lblNewLabel);
		
		textFieldDeptName = new JTextField();
		textFieldDeptName.setColumns(10);
		textFieldDeptName.setBounds(320, 206, 200, 50);
		contentPane.add(textFieldDeptName);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentPage.main(null);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBack.setBounds(110, 386, 200, 50);
		contentPane.add(btnBack);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm", "Employee Department System", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					logs.logger.info("Session ended by user");
					System.exit(0);
				}
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(320, 386, 200, 50);
		contentPane.add(btnExit);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTable(textFieldDeptId.getText(), textFieldDeptName.getText());
				btnDelete.setVisible(true);
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.setBounds(110, 266, 410, 50);
		contentPane.add(btnSearch);
		
		lblNewLabel_1 = new JLabel("Enter Department ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(110, 146, 200, 50);
		contentPane.add(lblNewLabel_1);
		
		textFieldDeptId = new JTextField();
		textFieldDeptId.setColumns(10);
		textFieldDeptId.setBounds(320, 146, 200, 50);
		contentPane.add(textFieldDeptId);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDeptName.setText(null);
				textFieldDeptId.setText(null);
				btnDelete.setVisible(false);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReset.setBounds(110, 326, 200, 50);
		contentPane.add(btnReset);
	}

}
