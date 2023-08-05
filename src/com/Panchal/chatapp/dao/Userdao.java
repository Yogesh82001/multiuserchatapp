package com.Panchal.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.Panchal.chatapp.dto.UserDTO;
import com.Panchal.chatapp.utils.Encryption;

// use case -> user CRUD op
public class Userdao {
	public boolean isLogin(UserDTO userDTO) throws SQLException, ClassNotFoundException, Exception {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		final String SQL="select userid from users where userid=? and password=?";
		try {
			con=Commondao.createConnection();
			pstmt =con.prepareStatement(SQL);
			pstmt.setString(1,userDTO.getUserid());
			String encryptedpwd=Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2,encryptedpwd);
			rs=pstmt.executeQuery();
			return rs.next();
//			if(rs.next()) {
//				return true;
//			}
//			else {
//				return false;
//			}
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(con!=null) {
				con.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
		}
		
	}
	// public int add(String userid,String password){ not valid for multipul fields 
	public int add(UserDTO userDTO)  throws ClassNotFoundException, SQLException,Exception{
		System.out.println("REC "+userDTO.getUserid()+" "+userDTO.getPassword());
		Connection connection=null;
		Statement stmt=null;
		connection=Commondao.createConnection(); // step-1-> connection create
		//            step-2-> we do a Query 
		try {// Guarded region 
		stmt=connection.createStatement();
		// insert into users (userid , password) values('ram','ram123')
		int record=stmt.executeUpdate("insert into users (userid , password) values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");// insert, delete ,update , etc
		return record;
		}
		finally {// always execute resource clean up code 
			if(stmt!=null) {
		stmt.close();
			}
			if(connection!=null) {
		connection.close();
			}
		}
		
	}
}

