package management;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class DepartmentPage extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private JButton btnNewButton_2;
	private JButton btnNewButton_1;
	private JButton btnSearchDepartment;
	private JButton btnSearch_1;
	
	backEnd be = new backEnd();
	SimpleFormatterLogging logs;
	ResultSet rs,rs2;
	String str_temp = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentPage frame = new DepartmentPage();
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
			rs = be.complete_dept_table();
			Object[] columnData = new Object[3];
			DefaultTableModel dtm = (DefaultTableModel)table.getModel();
			dtm.setRowCount(0);
			try {
				while(rs.next()) {
					columnData[0] = Integer.parseInt(rs.getString("dept_id"));
					columnData[1] = rs.getString("dept_name");
					rs2 = be.empIdUsingDeptid(Integer.parseInt(rs.getString("dept_id")));
					while(rs2.next()) {
						if(!str_temp.isEmpty()) {
							str_temp = str_temp+",";
						}
						 str_temp = str_temp + rs2.getString("emp_id");
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
	public DepartmentPage() {
		
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
		
		btnNewButton_2 = new JButton("Exit");
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
		btnNewButton_2.setBounds(316, 482, 200, 50);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage.main(null);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(106, 482, 200, 50);
		contentPane.add(btnNewButton_1);
		
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
		
		btnSearchDepartment = new JButton("Create Department");
		btnSearchDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDepartment.main(null);
				dispose();
			}
		});
		btnSearchDepartment.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearchDepartment.setBounds(106, 225, 410, 50);
		contentPane.add(btnSearchDepartment);
		
		btnSearch_1 = new JButton("Search/Delete Department");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchUpdateDepartment.main(null);
				dispose();
			}
		});
		btnSearch_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch_1.setBounds(106, 285, 410, 50);
		contentPane.add(btnSearch_1);
		
		createTable();
	}
}
