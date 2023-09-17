package com.rainier.utility;

import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HrmsGetDateAndTime {

	private final static Logger logger = Logger.getLogger(HrmsGetDateAndTime.class);

	static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	private static final String time = "yyyy-MM-dd";
	


	public Timestamp GetUTCdatetimeAsString() {
		logger.info("entered into GetUTCdatetimeAsString of utility class");
		// java.sql.Date sqlStartDate = null;
		Timestamp utctimestamp = null;
		String utcTime = null;
		try {
			ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
			utcTime = utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
			// System.out.println("utcTime: " + utcTime);
			utctimestamp = Timestamp.valueOf(utcTime);
			// System.out.println("sqlStartDate : " + utctimestamp);
		} catch (Exception e) {
			logger.error("catch block of GetUTCdatetimeAsString method of utility class:" + e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utctimestamp;
	}

	public java.sql.Date GetUTCDateAsString(String date) {
		logger.info("entered into GetUTCDateAsString of utility class");

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			// dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date parsedDate = dateFormat.parse(date);
			// System.out.println(parsedDate);
			java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
			// System.out.println(sqlDate);
			return sqlDate;
		} catch (Exception e) {
			logger.error("catch block of GetUTCDateAsString method of utility class:" + e);

			// System.out.println(e);
			return null;
		}
	}
	

	// only  Auto Date Saving method .... 
		
	public String getDateOnly() {
		
		String utcDate = null;
		//Date utcDate=null;
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.now();
			utcDate=DateTimeFormatter.ofPattern("yyy/MM/dd").format(localDate);
		}catch(Exception e) {
			e.printStackTrace();
		
		}
		return utcDate;	
	}
	


}
