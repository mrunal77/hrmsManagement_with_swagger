package com.rainier.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class FileUploadUtility {
	private final static Logger logger = Logger.getLogger(FileUploadUtility.class);

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

	// Create Save Path for Uploads Post Profile ....
	public String getUploadPostProfilePath(int id, String fileName, InputStream uploadedInputStream) {
		String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "HRMS_PostProfile"
				+ File.separator + "PostProfile_" + id;
		logger.info("File Path Creating...");
		File pathExist = new File(path);
		if (!pathExist.exists()) // Create Path if Not Exists
			pathExist.mkdirs();
		if (writeToFile(uploadedInputStream, path + File.separator + fileName)) {
			String uploadPath = "/HRMS_PostProfile/PostProfile_" + id + "/" + fileName;
			logger.info("upload Path Created...");
			return uploadPath;
		} else {
			logger.error("upload Path Creation Failed...");
			return "";
		}
	}
	
	
	// Create Save Path for Uploads Post Profile ....
		public String getUploadPicBenchSales(int id, String fileName, InputStream uploadedInputStream) {
			String path = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "HRMS_BechSales"
					+ File.separator + "BenchSales_" + id;
			logger.info("File Path Creating...");
			File pathExist = new File(path);
			if (!pathExist.exists()) // Create Path if Not Exists
				pathExist.mkdirs();
			if (writeToFile(uploadedInputStream, path + File.separator + fileName)) {
				String uploadPath = "/HRMS_BechSales/BenchSales_" + id + "/" + fileName;
				logger.info("upload Path Created...");
				return uploadPath;
			} else {
				logger.error("upload Path Creation Failed...");
				return "";
			}
		}
}
