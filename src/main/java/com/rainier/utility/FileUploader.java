package com.rainier.utility;

import org.apache.log4j.Logger;

import java.io.*;

public class FileUploader {
	private final static Logger logger = Logger.getLogger(FileUploader.class);

	// save uploaded file to new location
	private boolean writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
		logger.info("writeToFile method of File Uploader class");
		try {
			File delFile = new File(uploadedFileLocation);
			if (delFile.exists()) {
				delFile.delete();
			}
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Catch block of writeToFile" + e);
			return false;
		}
	}

	// Create Save Path for Uploads
	public String getUploadPath(int userId, String fileName, InputStream uploadedInputStream) {
		String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "HRMS_Uploads"
				+ File.separator + "emp_" + userId;
		logger.info("File Path Creating...");
		File pathExist = new File(path);
		if (!pathExist.exists()) // Create Path if Not Exists
			pathExist.mkdirs();
		if (writeToFile(uploadedInputStream, path + File.separator + fileName)) {
			String uploadPath = "/HRMS_Uploads/emp_" + userId + "/" + fileName;
			logger.info("upload Path Created...");
			return uploadPath;
		} else {
			logger.error("upload Path Creation Failed...");
			return "";
		}
	}

	// Create Save Path for Uploads Resume

	public String getUploadResumePath(int applyId, String fileName, InputStream uploadedInputStream) {
		String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "HRMS_Uploads"
				+ File.separator + "External_user" + applyId;
		logger.info("File Path Creating...");
		File pathExist = new File(path);
		if (!pathExist.exists()) // Create Path if Not Exists
			pathExist.mkdirs();
		if (writeToFile(uploadedInputStream, path + File.separator + fileName)) {
			String uploadResumePath = "/HRMS_Uploads/External_user" + applyId + "/" + fileName;
			logger.info("upload Path Created...");
			return uploadResumePath;
		} else {
			logger.error("upload Path Creation Failed...");
			return "";
		}
	}

	// post profile Uploading Resume path creation....
	public String getUploadPostProfilePath(int id, String fileName, InputStream uploadedInputStream) {

		String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "HRMS_Uploads"
				+ File.separator + "post_Profile" + id;
		logger.info("file path created Successfully");
		File pathExist = new File(path);
		if (!pathExist.exists())
			pathExist.mkdirs();
		if (writeToFile(uploadedInputStream, path + File.separator + fileName)) {
			String uploadpostprofile = "/HRMS_Uploads/post_Profile" + id + "/" + fileName;
			logger.info("post profile Uploaded into this path");
			return uploadpostprofile;
		} else {
			logger.error("Upload Creation Path Failed....");
			return "";
		}

	}

	/*
	 * @Context ServletContext servletContext;
	 * 
	 * @Context HttpServletRequest request;
	 * 
	 * public String baseUrl() { String serverName = request.getServerName(); int
	 * portNumber = request.getServerPort(); String baseUrl = request.getScheme() +
	 * "://" + serverName + ":" + portNumber; logger.info(baseUrl); return baseUrl;
	 * }
	 */
}
