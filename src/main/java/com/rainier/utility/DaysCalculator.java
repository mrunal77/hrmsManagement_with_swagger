package com.rainier.utility;

import org.apache.log4j.Logger;

import java.util.Date;

public class DaysCalculator {

	private final static Logger logger = Logger.getLogger(DaysCalculator.class);
	private float daysBetween;

	public float getDays(Date fromDate, Date toDate) {
		logger.info("entered into getDays method of utility class ");
		// SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		/*
		 * String dateBeforeString = "2018/08/17"; String dateAfterString =
		 * "2018/08/25";
		 */
		try {
			// Date dateBefore = myFormat.parse(fromDate);
			// Date dateAfter = myFormat.parse(toDate);
			long difference = toDate.getTime() - fromDate.getTime();
			daysBetween = (difference / (1000 * 60 * 60 * 24));
			// System.out.println("Number of Days between dates: " + daysBetween);
		} catch (Exception e) {
			logger.error("catch block of getDays method of utility class:" + e);
			e.printStackTrace();
		}
		return daysBetween + 1;
	}
	/*
	 * public static void main(String[] args) { DaysCalculator days= new
	 * DaysCalculator(); days.getDays("31 01 2014", "02 02 2014"); }
	 */

	// for Medical Claim Applying Leave Date.

	public float getNoOfDaysApplied(Date appliedleaveStartDate, Date appliedleaveEndDate) {
		logger.info("entered into getNoOfDaysApplied method of utility class ");
		long noOfDaysApplied = 0;
		try {
			long difference = appliedleaveEndDate.getTime() - appliedleaveStartDate.getTime();
			noOfDaysApplied = (difference / (1000 * 60 * 60 * 24));

			// System.out.println("noOfDaysApplied of Days between dates: " + daysBetween);
		} catch (Exception e1) {
			// e1.printStackTrace();
			logger.error("catch block of getNoOfDaysApplied method of utility class:" + e1);
		}
		return noOfDaysApplied;
	}

	// Approved No of Days By medical Claim team.
	public float getApprovedNoOfDays(Date approvedleaveStartDate, Date approvedleaveEndDate) {
		logger.info("entered into getApprovedNoOfDays method of utility class ");
		float noOfDaysApproved = 0;
		try {
			long difference = approvedleaveEndDate.getTime() - approvedleaveStartDate.getTime();
			noOfDaysApproved = (difference / (1000 * 60 * 60 * 24));
		} catch (Exception e2) {
			// e2.printStackTrace();
			logger.error("entered into catch block of getApprovedNoOfDays method of utility class " + e2);
		}
		return noOfDaysApproved;

	}

}
