package com.Panchal.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.Panchal.chatapp.utils.ConfigReader.getValue;
// Throw Early and catch Later  
//DAO-> data ACCESS object 
public interface Commondao {// isme yha interface ka use isliye hua h qki isme Commondao class ka object bar bar alag alag jghe se bnta 
	public static  Connection createConnection() throws ClassNotFoundException, SQLException {
		//step-1 load a Driver(driver is nothing it is class / loading of the class )
		Class.forName(getValue("DRIVER"));
		//step-2 making a connection 
		final String  CONNECTION_STRING=getValue("CONNECTION_URL");
		final String USER_ID=getValue("USER_ID");
		final String PASSWORD=getValue("PASSWORD");
		Connection con=DriverManager.getConnection(CONNECTION_STRING,USER_ID,PASSWORD);// interface Connection con =class Drivermanager .getconnection method 
		if(con!=null) {
			System.out.println("Connection created ....");
			//con.close();
			
		}
		return con;
	}
	
}
