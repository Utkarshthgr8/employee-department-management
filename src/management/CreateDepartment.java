package management;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CreateDepartment extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JTextField textFieldDeptName;
	
	backEnd be = new backEnd();
	SimpleFormatterLogging logs;
	ResultSet rs,rs2;
	String str_temp = "";
	private JButton btnNewButton_1;
	private JButton btnBack;
	private JButton btnReset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateDepartment frame = new CreateDepartment();
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
	public CreateDepartment() {

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
		
		btnNewButton = new JButton("Create Department");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				be.enter_dept_data(textFieldDeptName.getText());
				createTable();
				textFieldDeptName.setText(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(311, 303, 200, 50);
		contentPane.add(btnNewButton);
		
		textFieldDeptName = new JTextField();
		textFieldDeptName.setBounds(311, 243, 200, 50);
		contentPane.add(textFieldDeptName);
		textFieldDeptName.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter Department Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(101, 243, 200, 50);
		contentPane.add(lblNewLabel);
		
		btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Exit");
				if(JOptionPane.showConfirmDialog(frame, "Confirm", "Employee Department System", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					logs.logger.info("Session ended by user");
					System.exit(0);
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(311, 363, 200, 50);
		contentPane.add(btnNewButton_1);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentPage.main(null);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBack.setBounds(101, 363, 200, 50);
		contentPane.add(btnBack);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldDeptName.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnReset.setBounds(101, 303, 200, 50);
		contentPane.add(btnReset);
	}
}
