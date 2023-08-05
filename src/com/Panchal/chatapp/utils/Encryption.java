package com.Panchal.chatapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
	public static String passwordEncrypt(String plainPassword) throws NoSuchAlgorithmException {
		String encryptedPassword=null;
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");//MD5 is hashing algorithm ye algo sbhi ke liye 20 bits lega chahekuch bhi ho one way encryption hb no decryption  
		messageDigest.update(plainPassword.getBytes());
		byte [] encrypt =messageDigest.digest();
		StringBuffer sb =new StringBuffer();
		for(byte b:encrypt) {
			sb.append(b);
		}
		encryptedPassword=sb.toString();
		//System.out.println("Encrypted Password "+encryptedPassword);
		return encryptedPassword;
	}
	
}
