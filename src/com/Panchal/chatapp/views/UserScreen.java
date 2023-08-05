package com.Panchal.chatapp.views;
// chit chat application
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.Panchal.chatapp.dao.Userdao;
import com.Panchal.chatapp.dto.UserDTO;
import com.Panchal.chatapp.utils.UserInfo;
// database name -> chatdb
public class UserScreen extends JFrame{
	private JTextField useridtxt;
	private JPasswordField passwordField;
	
	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
					
	}
	Userdao userdao=new Userdao();	
	private void doLogin() {
		String userid=useridtxt.getText();
		char [] password =passwordField.getPassword();
	//	Userdao userdao=new Userdao();	
		UserDTO userDTO =new UserDTO(userid ,password );
		try {
			String message="";
			if(userdao.isLogin(userDTO)) {
//				JOptionPane.showMessageDialog(this, "Welcome "+userid);
				message="Welcome "+userid;
				UserInfo.USER_NAME=userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();// memory se bhi khatam ho jayega 
				DashBoard dashBoard= new DashBoard(message);
				dashBoard.setVisible(true);
			}
			else {
				message="Invalid Userid or Password ";
				JOptionPane.showMessageDialog(this, message);
			}
//			JOptionPane.showMessageDialog(this, message);
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void register() {
		String userid=useridtxt.getText();
		char [] password =passwordField.getPassword();
//		Userdao userdao=new Userdao();	
		UserDTO userDTO =new UserDTO(userid ,password );
		try{
		int result =userdao.add(userDTO);
		if(result>0) {
			//System.out.println("Record Added.....");
			JOptionPane.showMessageDialog(this, "Register SuccessFully");
		}
		else {
			//System.out.println("Record  Not Added....");
			JOptionPane.showMessageDialog(this, "Register Fail");
		}
		}
		catch(ClassNotFoundException | SQLException ex) {
			System.out.println("DB  ISSUE......");
			ex.printStackTrace();
			
		}
		catch(Exception ex) {
			System.out.println("Some Generic Exception Raised..... ");
			ex.printStackTrace();// where is the exception 
		}
		System.out.println("user id : "+userid+"  "+ " password : "+password );//password-> ClassName@HashCode(in Hexa decimal form)that format show resaon is tostring()
		}

	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setTitle("LOGIN");
		getContentPane().setBackground(new Color(255, 255, 255));
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(339, 38, 188, 49);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(438, 147, 300, 39);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("Userid");
		useridlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		useridlbl.setBounds(269, 147, 99, 49);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Tahoma", Font.BOLD, 20));
		pwdlbl.setBounds(243, 248, 99, 49);
		getContentPane().add(pwdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(438, 253, 300, 39);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setBackground(Color.WHITE);
		loginbt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loginbt.setBounds(243, 388, 112, 39);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setBackground(Color.WHITE);
		registerbt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		registerbt.setBounds(560, 388, 112, 39);
		getContentPane().add(registerbt);
		setBackground(Color.white);
		setSize( 911, 561);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	
	}
}
