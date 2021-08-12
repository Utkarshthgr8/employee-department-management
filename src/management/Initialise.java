package management;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Initialise extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	
	backEnd be = new backEnd();
	SimpleFormatterLogging logs;
	
	public
		String username, password;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Initialise frame = new Initialise();
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
	public Initialise() {

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
		
		JButton btnNewButton = new JButton("Check / Initialise Connection");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				username = textFieldUsername.getText();
				be.setUsername(username);
				password = textFieldPassword.getText();
				be.setPassword(password);
				be.createDatabase();
				if(be.init()) {
					JOptionPane.showMessageDialog(null, "Connection To Database Successfull");
					HomePage.main(null);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Could Not Connect to Database, try again.");
					be.setUsername(null);
					be.setPassword(null);
				}
				textFieldUsername.setText(null);
				textFieldPassword.setText(null);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(406, 390, 468, 50);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Enter Database Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(406, 270, 229, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Database Password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(406, 330, 229, 50);
		contentPane.add(lblNewLabel_1);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setText("postgres");
		textFieldUsername.setBounds(645, 270, 229, 50);
		contentPane.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setText("1234");
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(645, 330, 229, 50);
		contentPane.add(textFieldPassword);
		
		btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage.main(null);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(406, 510, 229, 50);
		contentPane.add(btnNewButton_1);
		
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
		btnNewButton_2.setBounds(645, 510, 229, 50);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Reset");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldUsername.setText(null);
				textFieldPassword.setText(null);
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_3.setBounds(406, 450, 468, 50);
		contentPane.add(btnNewButton_3);
	}
}
