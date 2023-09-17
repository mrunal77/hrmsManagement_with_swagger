package com.rainier.utility;

import org.apache.log4j.Logger;

import java.util.Random;

//Automatic generation of password
public class AutoPasswordGenerator {
	private final static Logger logger = Logger.getLogger(AutoPasswordGenerator.class);

	public String randomPassword() {
		logger.info("entered into randomPassword method of utility class");
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String values = Capital_chars + Small_chars + numbers;
		// Random method
		Random random = new Random();
		char[] password = new char[8];
		for (int i = 0; i < 8; i++) {
			password[i] = values.charAt(random.nextInt(values.length()));
		}
		// System.out.println(new String(password));
		logger.info("exiting randomPassword()  of Utility package");
		return new String(password);
	}

}
