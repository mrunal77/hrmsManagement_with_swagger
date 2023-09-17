package com.rainier.utility;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Generator {

	private final static Logger logger = Logger.getLogger(MD5Generator.class);

	public String generate(String password) {
		logger.info("entered into generate method of MD5Generator class");
		try {
			// MD5 logic implementation
			String hashtext;
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(password.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			logger.error("catch block of generate method of utility class:" + e); // TODO: handle exception
			// System.out.println(e);
			return null;
		}
	}

}
