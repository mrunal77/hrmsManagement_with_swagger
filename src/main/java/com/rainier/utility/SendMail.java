package com.rainier.utility;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SendMail {
	private final static Logger logger = Logger.getLogger(SendMail.class);

	public String fromMail = "", mailPwd = "", toMail = "", host = "smtp.gmail.com";
	public String subject = "";

	public SendMail() {
		this.fromMail = "rainiersoft123@gmail.com";// email-id from which the mail is sent;
		this.mailPwd = "Rainier@123";// password of the above given mail-id
		this.host = "smtp.gmail.com"; // For Gmail
		this.subject = "Test email of emailImplementation.....";
	}

	public SendMail(String firstName, String lastName, String funtionalityName) {
		if (funtionalityName.equals("addCandiadte")) {
			this.fromMail = "rainiersoft123@gmail.com";// email-id from which the mail is sent;
			this.mailPwd = "Rainier@123";// password of the above given mail-id
			this.host = "smtp.gmail.com"; // For Gmail
			this.subject = "New Account Registration :: " + firstName + "" + lastName + "";
		} else if (funtionalityName.equals("Submission")) {
			this.fromMail = "rainiersoft123@gmail.com";// email-id from which the mail is sent;
			this.mailPwd = "Rainier@123";// password of the above given mail-id
			this.host = "smtp.gmail.com"; // For Gmail
			this.subject = "Submission :: " + firstName + "" + lastName + "";
		} else if (funtionalityName.equals("Interviewed")) {
			this.fromMail = "rainiersoft123@gmail.com";// email-id from which the mail is sent;
			this.mailPwd = "Rainier@123";// password of the above given mail-id
			this.host = "smtp.gmail.com"; // For Gmail
			this.subject = "Interviewed :: " + firstName + "" + lastName + "";
		}else if (funtionalityName.equals("Selected")) {
			this.fromMail = "rainiersoft123@gmail.com";// email-id from which the mail is sent;
			this.mailPwd = "Rainier@123";// password of the above given mail-id
			this.host = "smtp.gmail.com"; // For Gmail
			this.subject = "Project Confirmation :: " + firstName + "" + lastName + "";
		}
	}
	public SendMail(String forgetEmail,String forgetPasswordUrl) {
		this.fromMail = "rainiersoft123@gmail.com";// email-id from which the mail is sent;
		this.mailPwd = "Rainier@123";// password of the above given mail-id
		this.host = "smtp.gmail.com"; // For Gmail
		this.subject = "FORGOT Password_E&E Access Portal Account ";
	}
	synchronized public boolean send(String toMail, String empId, String password, String empFullName, String firstName,
			String lastName) {
		logger.info("entered into send method of utility class");
		boolean sent = false;
		String emailBody = "<html>\r\n" + "<head>\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.8.2/css/all.css\" integrity=\"sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay\" crossorigin=\"anonymous\">\r\n"
				+ "</head>\r\n" + "<body>\r\n" + "\r\n"
				+ "<table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n" + "<div id=\"\"></div>\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background-color:#fdfdfd;border:1px solid #dcdcdc;border-radius:3px!important\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background-color:#1f6dad;border-radius:3px 3px 0 0!important;color:#ffffff;border-bottom:0;font-weight:bold;line-height:100%;vertical-align:middle;font-family: 'Open Sans',sans-serif;\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td id=\"\" style=\"padding:10px 48px;display:block\">\r\n"
				+ "<a href=\"https://www.equinoxitsol.com/\" target=\"https:/www.equinoxitsol.com/\" style=\"\"><img height=\"\" width=\"\" src=\"https://www.equinoxitsol.com/images/logo.png\" alt=\"CestaSoft\" style=\"max-height: 100%;height:  auto;padding: 0;margin: 0 auto;display: block;\"></a></span>\r\n"
				+ "<h1 style=\"color:#ffffff;font-family:'Open Sans',sans-serif;font-size:25px;font-weight:400;line-height:90px;margin:0;text-align:right !important;\">\r\n"
				+ "New Account Regisatration</h1>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n"
				+ "<tbody style=\"background-image: url(\"../images/background.png\");>\r\n" + "<tr>\r\n"
				+ "<td id=\"\" valign=\"top\" style=\"background-color:#fdfdfd\">\r\n"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"20\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n" + "<td valign=\"top\" style=\"padding:32px\">\r\n" + "<div id=\"\">\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">"
				+ "\r\n" + "Hello <b> " + firstName + "</b> \r\n" + "\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">Thank you for registering your online account with Equinox IT Solutions LLC. We are still working on the portal development to make it more user friendly and save your time to communicate with your supervisor for your day to day activities.</p>\r\n"
				+ "\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">This Account will help you to update your activities with Equinox and better form of communication with concerned teams to progress your career without any hassles. </p>\r\n"
				+ "\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">If you have any questions on how to access this portal. Kindly send out an email to our HR Department and will guide you the best. </p>\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Contact Email: </b>\r\n"
				+ "\r\n"
				+ "<a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\">  hr@equinoxitsol.com</a></p>\r\n"
				+ "\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif; font-size: 15px;\"><b>Note:</b> Equinox IT Solutions LLC started serving client since 2009 and is growing rapidly with efforts made by each and every individual in this family. Please make sure not to mis use any access provided to serve you better for growth and development of this Organization.</p>\r\n"
				+ "<p><b>url</b>  :<a href=\"\"style=\"color:#1f6dad;font-weight:600;text-decoration:underline\">35.243.229.170</a></p>\r\n"
				+ "\r\n" + "\r\n" + "\r\n" + "<p><b>Username</b> :" + empId + " </p>\r\n" + "<p><b>Password</b>  :"
				+ password + " </p>\r\n" + "\r\n"
				+ "<h2 style=\"color:#e9b751;display:block;font-family:'Open Sans',sans-serif;font-size:18px;font-weight:bold;line-height:130%;margin:16px 0 8px;text-align:left\">\r\n"
				+ "\r\n"
				+ "<table id=\"\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width:100%;vertical-align:top\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td class=\"\" width=\"50%\" valign=\"top\">\r\n"
				+ "<h3 style=\"font-family:'Open Sans',sans-serif;font-size: 15px;\">Regards</h3>\r\n"
				+ "<p style=\"font-family:'Open Sans',sans-serif;font-size: 15px;\" class=\"\">Human Resources Department <br>\r\n"
				+ "Equinox IT Solutions LLC <br>\r\n" + "8500 N Stemmons Fwy, Suite 5080, Dallas, Texas 75247.<br>\r\n"
				+ "469-602-6658 || <a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\"> hr@equinoxitsol.com</a></p>\r\n"
				+ "\r\n" + "</td>\r\n" + "</tr>\r\n" + "<tr>\r\n"
				+ "<td valign=\"top\" style=\"padding: 10px 0px;\">\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Disclaimer:</b><a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\"> www.equinoxitsol.com/email-disclaimer.</a></p>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</div>\r\n" + "</td>\r\n" + "</tr>\r\n"
				+ "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n" + "<td valign=\"top\" style=\"padding:0\">\r\n"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n"
				+ "<td colspan=\"2\" id=\"\" valign=\"middle\" style=\"padding:19px;border:0;color:#fff;font-family:'Open Sans',sans-serif;font-size:12px;text-align:center;background:#1f6dad;\">\r\n"
				+ "<!-- <p>For More Information, Please contact <a class=\"\" href=\"mailto:cs@vydic.org\" target=\"_blank\"> -->\r\n"
				+ "<!-- cs@vydic.org</a></p> -->\r\n" + "<p>E&E Access Point Solution that saves time in your life.\r\n"
				+ "</p>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n"
				+ "\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</body>\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;
	}

	synchronized public boolean send(String toMail, String empId, String password, String empFullName) {
		logger.info("entered into send method of utility class");
		boolean sent = false;
		String emailBody = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "   <head>\r\n"
				+ "      <meta charset=\"utf-8\">\r\n"
				+ "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "      <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n"
				+ "      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
				+ "      <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n"
				+ "      <style>\r\n" + "        body {\r\n" + "    background-color: #F5F5F5;\r\n"
				+ "    font-family: Roboto,sans-serif;\r\n" + "    font-weight: 400;\r\n" + "    margin-bottom: 0;\r\n"
				+ "    margin-top: 0;\r\n" + "    padding-top: 0;\r\n" + "}\r\n" + "        td.iage_footer {\r\n"
				+ "    background: #fff;\r\n" + "    padding: 40px 20px;\r\n" + "    border-top: 1px solid #ddd;\r\n"
				+ "}\r\n" + "        \r\n" + "      </style>\r\n" + "   </head>\r\n"
				+ "   <!------ Include the above in your HEAD tag ---------->\r\n" + " <body>\r\n"
				+ "      <div id=\"mailsub\" class=\"notification\" align=\"center\">\r\n"
				+ "         <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"min-width: 320px;\">\r\n"
				+ "            <tbody>\r\n" + "               <tr>\r\n"
				+ "                  <td align=\"center\" bgcolor=\"\" style=\"padding: 20px;\">\r\n"
				+ "                     <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_width_100\" width=\"100%\" style=\"max-width: 680px;min-width: 300px;\">\r\n"
				+ "                        <tbody>\r\n" + "                          \r\n"
				+ "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#ffffff\" style=\"\r\n"
				+ "                                 \">\r\n" + "                                 <!-- padding -->\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <!-- \r\n"
				+ "                                                Item -->\r\n"
				+ "                                             <div class=\"mob_center_bl\" style=\"float: none;width: 53%;margin: 0 auto;display: block;\">\r\n"
				+ "                                                <table class=\"mob_center\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse: collapse;\">\r\n"
				+ "                                                   <tbody>\r\n"
				+ "                                                      <tr>\r\n"
				+ "                                                         <td align=\"center\" valign=\"middle\">\r\n"
				+ "                                                            <!-- padding -->\r\n"
				+ "                                                            <div style=\"\">&nbsp;</div>\r\n"
				+ "                                                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"\r\n"
				+ "                                                               \">\r\n"
				+ "                                                               <tbody>\r\n"
				+ "                                                                  <tr style=\"\">\r\n"
				+ "                                                                     <td align=\"left\" valign=\"top\" class=\"mob_center\" style=\"\">\r\n"
				+ "                                                                        <span style=\"\r\n"
				+ "                                                                           margin-top: 30px;\r\n"
				+ "                                                                           margin-right: 12px;\r\n"
				+ "                                                                           font-size: 20px;\r\n"
				+ "                                                                           color: #0c63b6;\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \">WELCOME TO</span><span style=\"\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \"><a href=\"https://www.equinoxitsol.com/\" target=\"https://www.equinoxitsol.com/\" style=\"\"><img height=\"\" width=\"\" src=\"https://www.equinoxitsol.com/images/logo.png\" alt=\"CestaSoft\" style=\"max-height: 100%;height:  auto;padding: 0;margin: 0 auto;display: block;\"></a></span>\r\n"
				+ "                                                                     </td>\r\n"
				+ "                                                                  </tr>\r\n"
				+ "                                                               </tbody>\r\n"
				+ "                                                            </table>\r\n"
				+ "                                                         </td>\r\n"
				+ "                                                      </tr>\r\n"
				+ "                                                   </tbody>\r\n"
				+ "                                                </table>\r\n"
				+ "                                             </div>\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                                 <!-- padding -->\r\n" + "                              </td>\r\n"
				+ "                           </tr>\r\n" + "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#fbfcfd\">\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"\">&nbsp;</div>\r\n"
				+ "                                             <div style=\"line-height: 45px;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Dear "
				+ empFullName + ",</span></font>\r\n" + "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"line-height: 0;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"font-size: 30px;text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Congratulations!! </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\" style=\"\r\n"
				+ "                                             \">\r\n"
				+ "                                             <div style=\"\r\n"
				+ "                                                margin-top: 30px;\r\n"
				+ "                                                \">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 15px;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 15px;color: #8c8e8d;text-align: left;line-height: 24px;\">Your account has been successfully registered. Please use the below link to login with your temporary credentials. </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                       <tr style=\"\r\n"
				+ "                                          \">\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             /* text-align: left; */\r\n"
				+ "                                             \">\r\n"
				+ "                                             <div style=\"margin-top: 30px;text-align: left;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 16px;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #000;margin: 10px 30px;\">url :<a href='http://35.243.229.170'>35.243.229.170</a></span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <div style=\"margin-top: 10px;text-align: left;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 16px;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #000;margin: 10px 30px;\">Username : "
				+ empId + "</span></font>\r\n" + "                                             </div>\r\n"
				+ "                                             <div style=\"margin-top: 10px;text-align: left;margin-bottom: 30px;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 15px;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #000;margin: 10px 30px;\">Password : "
				+ password + " </span></font>\r\n" + "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <div style=\"\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 15px;\">\r\n"
				+ "                                                <span style=\"\r\n"
				+ "                                                   font-family: Arial, Helvetica, sans-serif;\r\n"
				+ "                                                   font-size: 15px;\r\n"
				+ "                                                   color: #8c8e8d;\r\n"
				+ "                                                   text-align: left;\r\n"
				+ "                                                   line-height: 24px;\r\n"
				+ "                                                   \">Please update your password and also complete the required information to continue using the account with any suspension. This account will be used for all your communications with Equinox IT Solutions. </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             \r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "\r\n" + "<tr>\r\n"
				+ "                                          <td align=\"right\" style=\"\r\n"
				+ "                                             float: right;\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             \r\n"
				+ "                                             <div style=\"margin-top: 30px;margin-bottom: 30px;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"\r\n"
				+ "    line-height: 18px;\r\n" + "\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;/* color: #8c8e8d; */text-align: left;\">Regards <br>HR Department.</span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           \r\n" + "                           <tr>\r\n"
				+ "                              <td class=\"iage_footer\" align=\"center\" bgcolor=\"#ffffff\">\r\n"
				+ "                                 <!-- padding -->	\r\n"
				+ "                                 <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <font face=\"Arial, Helvetica, sans-serif\" size=\"3\" color=\"#96a5b5\" style=\"font-size: 13px;\">\r\n"
				+ "                                             <span style=\"font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #96a5b5;\">Equinox It Solutions LLC,<br>\r\n"
				+ "                                             13111 N. Central Expressway, <br> Suite 800, Dallas, TX 75243</span></font>				\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           <tr></tr>\r\n" + "                        </tbody>\r\n"
				+ "                     </table>\r\n" + "                  </td>\r\n" + "               </tr>\r\n"
				+ "            </tbody>\r\n" + "         </table>\r\n" + "      </div>\r\n" + "   \r\n" + "</body>\r\n"
				+ "\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;
	}

	synchronized public boolean send(String toMail, String empFullName, String empName) {
		logger.info("entered into send method of utility class");
		boolean sent = false;
		String emailBody = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "   <head>\r\n"
				+ "      <meta charset=\"utf-8\">\r\n"
				+ "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "      <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n"
				+ "      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
				+ "      <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n"
				+ "      <style>\r\n" + "        body {\r\n" + "    background-color: #F5F5F5;\r\n"
				+ "    font-family: Roboto,sans-serif;\r\n" + "    font-weight: 400;\r\n" + "    margin-bottom: 0;\r\n"
				+ "    margin-top: 0;\r\n" + "    padding-top: 0;\r\n" + "}\r\n" + "        td.iage_footer {\r\n"
				+ "    background: #fff;\r\n" + "    padding: 40px 20px;\r\n" + "    border-top: 1px solid #ddd;\r\n"
				+ "}\r\n" + "        \r\n" + "      </style>\r\n" + "   </head>\r\n"
				+ "   <!------ Include the above in your HEAD tag ---------->\r\n" + " <body>\r\n"
				+ "      <div id=\"mailsub\" class=\"notification\" align=\"center\">\r\n"
				+ "         <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"min-width: 320px;\">\r\n"
				+ "            <tbody>\r\n" + "               <tr>\r\n"
				+ "                  <td align=\"center\" bgcolor=\"\" style=\"padding: 20px;\">\r\n"
				+ "                     <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_width_100\" width=\"100%\" style=\"max-width: 680px;min-width: 300px;\">\r\n"
				+ "                        <tbody>\r\n" + "                          \r\n"
				+ "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#ffffff\" style=\"\r\n"
				+ "                                 \">\r\n" + "                                 <!-- padding -->\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <!-- \r\n"
				+ "                                                Item -->\r\n"
				+ "                                             <div class=\"mob_center_bl\" style=\"float: none;width: 53%;margin: 0 auto;display: block;\">\r\n"
				+ "                                                <table class=\"mob_center\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse: collapse;\">\r\n"
				+ "                                                   <tbody>\r\n"
				+ "                                                      <tr>\r\n"
				+ "                                                         <td align=\"center\" valign=\"middle\">\r\n"
				+ "                                                            <!-- padding -->\r\n"
				+ "                                                            <div style=\"\">&nbsp;</div>\r\n"
				+ "                                                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"\r\n"
				+ "                                                               \">\r\n"
				+ "                                                               <tbody>\r\n"
				+ "                                                                  <tr style=\"\">\r\n"
				+ "                                                                     <td align=\"left\" valign=\"top\" class=\"mob_center\" style=\"\">\r\n"
				+ "                                                                        <span style=\"\r\n"
				+ "                                                                           margin-top: 30px;\r\n"
				+ "                                                                           margin-right: 12px;\r\n"
				+ "                                                                           font-size: 20px;\r\n"
				+ "                                                                           color: #0c63b6;\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \">WELCOME TO</span><span style=\"\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \"><a href=\"https://www.equinoxitsol.com/\" target=\"https://www.equinoxitsol.com/\" style=\"\"><img height=\"\" width=\"\" src=\"https://www.equinoxitsol.com/images/logo.png\" alt=\"CestaSoft\" style=\"max-height: 100%;height:  auto;padding: 0;margin: 0 auto;display: block;\"></a></span>\r\n"
				+ "                                                                     </td>\r\n"
				+ "                                                                  </tr>\r\n"
				+ "                                                               </tbody>\r\n"
				+ "                                                            </table>\r\n"
				+ "                                                         </td>\r\n"
				+ "                                                      </tr>\r\n"
				+ "                                                   </tbody>\r\n"
				+ "                                                </table>\r\n"
				+ "                                             </div>\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                                 <!-- padding -->\r\n" + "                              </td>\r\n"
				+ "                           </tr>\r\n" + "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#fbfcfd\">\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"\">&nbsp;</div>\r\n"
				+ "                                             <div style=\"line-height: 45px;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Dear "
				+ empFullName + ",</span></font>\r\n" + "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"line-height: 0;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"font-size: 30px;text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Congratulations!! </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\" style=\"\r\n"
				+ "                                             \">\r\n"
				+ "                                             <div style=\"\r\n"
				+ "                                                margin-top: 30px;\r\n"
				+ "                                                \">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 15px;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 15px;color: #8c8e8d;text-align: left;line-height: 24px;\"> In Your organisation "
				+ empName + " Employee has been  successfully registered. </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                       <tr style=\"\r\n"
				+ "                                          \">\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             /* text-align: left; */\r\n"
				+ "                                             \">\r\n"

				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"

				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           \r\n" + "                           <tr>\r\n"
				+ "                              <td class=\"iage_footer\" align=\"center\" bgcolor=\"#ffffff\">\r\n"
				+ "                                 <!-- padding -->	\r\n"
				+ "                                 <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <font face=\"Arial, Helvetica, sans-serif\" size=\"3\" color=\"#96a5b5\" style=\"font-size: 13px;\">\r\n"
				+ "                                             <span style=\"font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #96a5b5;\">Equinox It Solutions LLC,<br>\r\n"
				+ "                                             13111 N. Central Expressway, <br> Suite 800, Dallas, TX 75243</span></font>				\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           <tr></tr>\r\n" + "                        </tbody>\r\n"
				+ "                     </table>\r\n" + "                  </td>\r\n" + "               </tr>\r\n"
				+ "            </tbody>\r\n" + "         </table>\r\n" + "      </div>\r\n" + "   \r\n" + "</body>\r\n"
				+ "\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;
	}

	synchronized public boolean sendTo(String toMail, String empId, String empFullName) {
		logger.info("entered into send method of utility class");
		boolean sent = false;
		String emailBody = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "   <head>\r\n"
				+ "      <meta charset=\"utf-8\">\r\n"
				+ "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "      <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n"
				+ "      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
				+ "      <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n"
				+ "      <style>\r\n" + "        body {\r\n" + "    background-color: #F5F5F5;\r\n"
				+ "    font-family: Roboto,sans-serif;\r\n" + "    font-weight: 400;\r\n" + "    margin-bottom: 0;\r\n"
				+ "    margin-top: 0;\r\n" + "    padding-top: 0;\r\n" + "}\r\n" + "        td.iage_footer {\r\n"
				+ "    background: #fff;\r\n" + "    padding: 40px 20px;\r\n" + "    border-top: 1px solid #ddd;\r\n"
				+ "}\r\n" + "        \r\n" + "      </style>\r\n" + "   </head>\r\n"
				+ "   <!------ Include the above in your HEAD tag ---------->\r\n" + " <body>\r\n"
				+ "      <div id=\"mailsub\" class=\"notification\" align=\"center\">\r\n"
				+ "         <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"min-width: 320px;\">\r\n"
				+ "            <tbody>\r\n" + "               <tr>\r\n"
				+ "                  <td align=\"center\" bgcolor=\"\" style=\"padding: 20px;\">\r\n"
				+ "                     <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_width_100\" width=\"100%\" style=\"max-width: 680px;min-width: 300px;\">\r\n"
				+ "                        <tbody>\r\n" + "                          \r\n"
				+ "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#ffffff\" style=\"\r\n"
				+ "                                 \">\r\n" + "                                 <!-- padding -->\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <!-- \r\n"
				+ "                                                Item -->\r\n"
				+ "                                             <div class=\"mob_center_bl\" style=\"float: none;width: 53%;margin: 0 auto;display: block;\">\r\n"
				+ "                                                <table class=\"mob_center\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse: collapse;\">\r\n"
				+ "                                                   <tbody>\r\n"
				+ "                                                      <tr>\r\n"
				+ "                                                         <td align=\"center\" valign=\"middle\">\r\n"
				+ "                                                            <!-- padding -->\r\n"
				+ "                                                            <div style=\"\">&nbsp;</div>\r\n"
				+ "                                                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"\r\n"
				+ "                                                               \">\r\n"
				+ "                                                               <tbody>\r\n"
				+ "                                                                  <tr style=\"\">\r\n"
				+ "                                                                     <td align=\"left\" valign=\"top\" class=\"mob_center\" style=\"\">\r\n"
				+ "                                                                        <span style=\"\r\n"
				+ "                                                                           margin-top: 30px;\r\n"
				+ "                                                                           margin-right: 12px;\r\n"
				+ "                                                                           font-size: 20px;\r\n"
				+ "                                                                           color: #0c63b6;\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \">WELCOME TO</span><span style=\"\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \"><a href=\"https://www.equinoxitsol.com/\" target=\"https://www.equinoxitsol.com/\" style=\"\"><img height=\"\" width=\"\" src=\"https://www.equinoxitsol.com/images/logo.png\" alt=\"CestaSoft\" style=\"max-height: 100%;height:  auto;padding: 0;margin: 0 auto;display: block;\"></a></span>\r\n"
				+ "                                                                     </td>\r\n"
				+ "                                                                  </tr>\r\n"
				+ "                                                               </tbody>\r\n"
				+ "                                                            </table>\r\n"
				+ "                                                         </td>\r\n"
				+ "                                                      </tr>\r\n"
				+ "                                                   </tbody>\r\n"
				+ "                                                </table>\r\n"
				+ "                                             </div>\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                                 <!-- padding -->\r\n" + "                              </td>\r\n"
				+ "                           </tr>\r\n" + "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#fbfcfd\">\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"\">&nbsp;</div>\r\n"
				+ "                                             <div style=\"line-height: 45px;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Dear "
				+ empFullName + ",</span></font>\r\n" + "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"line-height: 0;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"font-size: 30px;text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\"> </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\" style=\"\r\n"
				+ "                                             \">\r\n"
				+ "                                             <div style=\"\r\n"
				+ "                                                margin-top: 30px;\r\n"
				+ "                                                \">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 15px;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 15px;color: #8c8e8d;text-align: left;line-height: 24px;\">Your Passport And Driving License Has Been Expiered Please Renewal Your PassPort Date And Driving License Date. </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                       <tr style=\"\r\n"
				+ "                                          \">\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             /* text-align: left; */\r\n"
				+ "                                             \">\r\n"
				+ "                                             <div style=\"margin-top: 30px;text-align: left;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 16px;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #000;margin: 10px 30px;\">url :<a href='http://35.243.229.170'>35.243.229.170</a></span></font>\r\n"
				+ "                                             </div>\r\n"

				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <div style=\"\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 15px;\">\r\n"
				+ "                                                <span style=\"\r\n"
				+ "                                                   font-family: Arial, Helvetica, sans-serif;\r\n"
				+ "                                                   font-size: 15px;\r\n"
				+ "                                                   color: #8c8e8d;\r\n"
				+ "                                                   text-align: left;\r\n"
				+ "                                                   line-height: 24px;\r\n"
				+ "                                                   \">Please update your passport Date and Driving License and also complete the required information to continue using the account with any suspension. This account will be used for all your communications with Equinox IT Solutions. </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             \r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "\r\n" + "<tr>\r\n"
				+ "                                          <td align=\"right\" style=\"\r\n"
				+ "                                             float: right;\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             \r\n"
				+ "                                             <div style=\"margin-top: 30px;margin-bottom: 30px;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"\r\n"
				+ "    line-height: 18px;\r\n" + "\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;/* color: #8c8e8d; */text-align: left;\">Regards <br>HR Department.</span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           \r\n" + "                           <tr>\r\n"
				+ "                              <td class=\"iage_footer\" align=\"center\" bgcolor=\"#ffffff\">\r\n"
				+ "                                 <!-- padding -->	\r\n"
				+ "                                 <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <font face=\"Arial, Helvetica, sans-serif\" size=\"3\" color=\"#96a5b5\" style=\"font-size: 13px;\">\r\n"
				+ "                                             <span style=\"font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #96a5b5;\">Equinox It Solutions LLC,<br>\r\n"
				+ "                                             13111 N. Central Expressway, <br> Suite 800, Dallas, TX 75243</span></font>				\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           <tr></tr>\r\n" + "                        </tbody>\r\n"
				+ "                     </table>\r\n" + "                  </td>\r\n" + "               </tr>\r\n"
				+ "            </tbody>\r\n" + "         </table>\r\n" + "      </div>\r\n" + "   \r\n" + "</body>\r\n"
				+ "\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;
	}

	synchronized public boolean sendMailAfterSubmitted(String toMail, String clientName, String location, int id) {
		logger.info("entered into send method of utility class");
		boolean sent = false;
		String emailBody = "<html>\r\n" + "<head>\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.8.2/css/all.css\" integrity=\"sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay\" crossorigin=\"anonymous\">\r\n"
				+ "</head>\r\n" + "<body>\r\n" + "\r\n"
				+ "<table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n" + "<div id=\"\"></div>\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background-color:#fdfdfd;border:1px solid #dcdcdc;border-radius:3px!important\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background-color:#1f6dad;border-radius:3px 3px 0 0!important;color:#ffffff;border-bottom:0;font-weight:bold;line-height:100%;vertical-align:middle;font-family: 'Open Sans',sans-serif;\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td id=\"\" style=\"padding:10px 48px;display:block\">\r\n"
				+ "<img src=\"http://hrmsdev.tk/assets/images/favicon.png\" style=\"width:20% !important;margin:0 auto;display:block;float: left !important;\">\r\n"
				+ "<h1 style=\"color:#ffffff;font-family:'Open Sans',sans-serif;font-size:25px;font-weight:400;line-height:90px;margin:0;text-align:right !important;\">\r\n"
				+ "Submission</h1>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n"
				+ "</tr>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n"
				+ "<tbody style=\"background-image: url(\"../images/background.png\");>\r\n" + "<tr>\r\n"
				+ "<td id=\"\" valign=\"top\" style=\"background-color:#fdfdfd\">\r\n"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"20\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n" + "<td valign=\"top\" style=\"padding:32px\">\r\n" + "<div id=\"\">\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">Your profile has been submitted to the following Position. Please find the JD and other information for your reference. </p>\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Client Name<span style=\"padding-left: 5px;\">:"
				+ clientName + "</span></b> <br>\r\n" + "<b>Location<span style=\"padding-left: 27px;\">:" + location
				+ "</span></b> <br>\r\n" + "<b>Title<span style=\"padding-left: 58px;\">:</span></b> <br>\r\n"
				+ "<b>JD<span style=\"padding-left: 74px;\">:</span></b> <br>\r\n" + "</p>\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">Please avoid dual submission for this requirement. </p>\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Note:</b> Equinox IT Solutions LLC started serving client since 2009 and is growing rapidly with efforts made by each and every individual in this family. Please make sure not to mis use any access provided to serve you better for growth and development of this Organization.</p>\r\n"
				+ "<h2 style=\"color:#e9b751;display:block;font-family:'Open Sans',sans-serif;font-size:18px;font-weight:bold;line-height:130%;margin:16px 0 8px;text-align:left\">\r\n"
				+ "\r\n"
				+ "<table id=\"\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width:100%;vertical-align:top\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td class=\"\" width=\"50%\" valign=\"top\">\r\n"
				+ "<h3 style=\"font-family:'Open Sans',sans-serif;font-size: 15px;\">Regards</h3>\r\n"
				+ "<p style=\"font-family:'Open Sans',sans-serif;font-size: 15px;\" class=\"\">USER NAME(Should be Sales Recruiter or concerned Person)<br>\r\n"
				+ "Equinox IT Solutions LLC <br>\r\n" + "8500 N Stemmons Fwy, Suite 5080, Dallas, Texas 75247.<br>\r\n"
				+ "469-602-6658 || <a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\"> hr@equinoxitsol.com</a></p>\r\n"
				+ "\r\n" + "</td>\r\n" + "</tr>\r\n" + "<tr>\r\n"
				+ "<td valign=\"top\" style=\"padding: 10px 0px;\">\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Disclaimer:</b><a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\"> www.equinoxitsol.com/email-disclaimer.</a></p>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</div>\r\n" + "</td>\r\n" + "</tr>\r\n"
				+ "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n" + "<td valign=\"top\" style=\"padding:0\">\r\n"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n"
				+ "<td colspan=\"2\" id=\"\" valign=\"middle\" style=\"padding:19px;border:0;color:#fff;font-family:'Open Sans',sans-serif;font-size:12px;text-align:center;background:#1f6dad;\">\r\n"
				+ "<!-- <p>For More Information, Please contact <a class=\"\" href=\"mailto:cs@vydic.org\" target=\"_blank\"> -->\r\n"
				+ "<!-- cs@vydic.org</a></p> -->\r\n" + "<p>E&E Access Point Solution that saves time in your life.\r\n"
				+ "</p>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n"
				+ "\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</body>\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;
	}

	synchronized public boolean send(String toMail, List<String> recList, String candidateName) {
		String empFullName = " ";

		for (String rec : recList) {
			empFullName = empFullName + "  " + rec + ",";
		}
		logger.info("entered into send method of utility class");
		boolean sent = false;

		String emailBody = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "   <head>\r\n"
				+ "      <meta charset=\"utf-8\">\r\n"
				+ "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "      <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n"
				+ "      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
				+ "      <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n"
				+ "      <style>\r\n" + "        body {\r\n" + "    background-color: #F5F5F5;\r\n"
				+ "    font-family: Roboto,sans-serif;\r\n" + "    font-weight: 400;\r\n" + "    margin-bottom: 0;\r\n"
				+ "    margin-top: 0;\r\n" + "    padding-top: 0;\r\n" + "}\r\n" + "        td.iage_footer {\r\n"
				+ "    background: #fff;\r\n" + "    padding: 40px 20px;\r\n" + "    border-top: 1px solid #ddd;\r\n"
				+ "}\r\n" + "        \r\n" + "      </style>\r\n" + "   </head>\r\n"
				+ "   <!------ Include the above in your HEAD tag ---------->\r\n" + " <body>\r\n"
				+ "      <div id=\"mailsub\" class=\"notification\" align=\"center\">\r\n"
				+ "         <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"min-width: 320px;\">\r\n"
				+ "            <tbody>\r\n" + "               <tr>\r\n"
				+ "                  <td align=\"center\" bgcolor=\"\" style=\"padding: 20px;\">\r\n"
				+ "                     <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_width_100\" width=\"100%\" style=\"max-width: 680px;min-width: 300px;\">\r\n"
				+ "                        <tbody>\r\n" + "                          \r\n"
				+ "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#ffffff\" style=\"\r\n"
				+ "                                 \">\r\n" + "                                 <!-- padding -->\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <!-- \r\n"
				+ "                                                Item -->\r\n"
				+ "                                             <div class=\"mob_center_bl\" style=\"float: none;width: 53%;margin: 0 auto;display: block;\">\r\n"
				+ "                                                <table class=\"mob_center\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse: collapse;\">\r\n"
				+ "                                                   <tbody>\r\n"
				+ "                                                      <tr>\r\n"
				+ "                                                         <td align=\"center\" valign=\"middle\">\r\n"
				+ "                                                            <!-- padding -->\r\n"
				+ "                                                            <div style=\"\">&nbsp;</div>\r\n"
				+ "                                                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"\r\n"
				+ "                                                               \">\r\n"
				+ "                                                               <tbody>\r\n"
				+ "                                                                  <tr style=\"\">\r\n"
				+ "                                                                     <td align=\"left\" valign=\"top\" class=\"mob_center\" style=\"\">\r\n"
				+ "                                                                        <span style=\"\r\n"
				+ "                                                                           margin-top: 30px;\r\n"
				+ "                                                                           margin-right: 12px;\r\n"
				+ "                                                                           font-size: 20px;\r\n"
				+ "                                                                           color: #0c63b6;\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \">WELCOME TO</span><span style=\"\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \"><a href=\"https://www.equinoxitsol.com/\" target=\"https://www.equinoxitsol.com/\" style=\"\"><img height=\"\" width=\"\" src=\"https://www.equinoxitsol.com/images/logo.png\" alt=\"CestaSoft\" style=\"max-height: 100%;height:  auto;padding: 0;margin: 0 auto;display: block;\"></a></span>\r\n"
				+ "                                                                     </td>\r\n"
				+ "                                                                  </tr>\r\n"
				+ "                                                               </tbody>\r\n"
				+ "                                                            </table>\r\n"
				+ "                                                         </td>\r\n"
				+ "                                                      </tr>\r\n"
				+ "                                                   </tbody>\r\n"
				+ "                                                </table>\r\n"
				+ "                                             </div>\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                                 <!-- padding -->\r\n" + "                              </td>\r\n"
				+ "                           </tr>\r\n" + "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#fbfcfd\">\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"\">&nbsp;</div>\r\n"
				+ "                                             <div style=\"line-height: 45px;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Recruiters are "
				+ empFullName + "</span></font>\r\n" + "                                             </div>\r\n"
				+ "                                             <div style=\"line-height: 45px;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Candidate Name is :: "
				+ candidateName + ",</span></font>\r\n" + "                                             </div>\r\n"

				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"line-height: 0;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"font-size: 30px;text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Congratulations!! </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\" style=\"\r\n"
				+ "                                             \">\r\n"
				/*
				 * + "                                             <div style=\"\r\n" +
				 * "                                                margin-top: 30px;\r\n" +
				 * "                                                \">\r\n" +
				 * "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 15px;\">\r\n"
				 * +
				 * "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 15px;color: #8c8e8d;text-align: left;line-height: 24px;\"> CandidateName is :: "
				 * + candidateName
				 * +" Employee has been  successfully registered. </span></font>\r\n" +
				 * "                                             </div>\r\n"
				 */ + "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                       <tr style=\"\r\n"
				+ "                                          \">\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             /* text-align: left; */\r\n"
				+ "                                             \">\r\n"

				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"

				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           \r\n" + "                           <tr>\r\n"
				+ "                              <td class=\"iage_footer\" align=\"center\" bgcolor=\"#ffffff\">\r\n"
				+ "                                 <!-- padding -->	\r\n"
				+ "                                 <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <font face=\"Arial, Helvetica, sans-serif\" size=\"3\" color=\"#96a5b5\" style=\"font-size: 13px;\">\r\n"
				+ "                                             <span style=\"font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #96a5b5;\">Equinox It Solutions LLC,<br>\r\n"
				+ "                                             13111 N. Central Expressway, <br> Suite 800, Dallas, TX 75243</span></font>				\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           <tr></tr>\r\n" + "                        </tbody>\r\n"
				+ "                     </table>\r\n" + "                  </td>\r\n" + "               </tr>\r\n"
				+ "            </tbody>\r\n" + "         </table>\r\n" + "      </div>\r\n" + "   \r\n" + "</body>\r\n"
				+ "\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;

	}

	synchronized public boolean send(String toMail, String candidateName) {

		logger.info("entered into send method of utility class");
		boolean sent = false;

		String emailBody = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "   <head>\r\n"
				+ "      <meta charset=\"utf-8\">\r\n"
				+ "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "      <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n"
				+ "      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
				+ "      <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n"
				+ "      <style>\r\n" + "        body {\r\n" + "    background-color: #F5F5F5;\r\n"
				+ "    font-family: Roboto,sans-serif;\r\n" + "    font-weight: 400;\r\n" + "    margin-bottom: 0;\r\n"
				+ "    margin-top: 0;\r\n" + "    padding-top: 0;\r\n" + "}\r\n" + "        td.iage_footer {\r\n"
				+ "    background: #fff;\r\n" + "    padding: 40px 20px;\r\n" + "    border-top: 1px solid #ddd;\r\n"
				+ "}\r\n" + "        \r\n" + "      </style>\r\n" + "   </head>\r\n"
				+ "   <!------ Include the above in your HEAD tag ---------->\r\n" + " <body>\r\n"
				+ "      <div id=\"mailsub\" class=\"notification\" align=\"center\">\r\n"
				+ "         <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"min-width: 320px;\">\r\n"
				+ "            <tbody>\r\n" + "               <tr>\r\n"
				+ "                  <td align=\"center\" bgcolor=\"\" style=\"padding: 20px;\">\r\n"
				+ "                     <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_width_100\" width=\"100%\" style=\"max-width: 680px;min-width: 300px;\">\r\n"
				+ "                        <tbody>\r\n" + "                          \r\n"
				+ "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#ffffff\" style=\"\r\n"
				+ "                                 \">\r\n" + "                                 <!-- padding -->\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <!-- \r\n"
				+ "                                                Item -->\r\n"
				+ "                                             <div class=\"mob_center_bl\" style=\"float: none;width: 53%;margin: 0 auto;display: block;\">\r\n"
				+ "                                                <table class=\"mob_center\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse: collapse;\">\r\n"
				+ "                                                   <tbody>\r\n"
				+ "                                                      <tr>\r\n"
				+ "                                                         <td align=\"center\" valign=\"middle\">\r\n"
				+ "                                                            <!-- padding -->\r\n"
				+ "                                                            <div style=\"\">&nbsp;</div>\r\n"
				+ "                                                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"\r\n"
				+ "                                                               \">\r\n"
				+ "                                                               <tbody>\r\n"
				+ "                                                                  <tr style=\"\">\r\n"
				+ "                                                                     <td align=\"left\" valign=\"top\" class=\"mob_center\" style=\"\">\r\n"
				+ "                                                                        <span style=\"\r\n"
				+ "                                                                           margin-top: 30px;\r\n"
				+ "                                                                           margin-right: 12px;\r\n"
				+ "                                                                           font-size: 20px;\r\n"
				+ "                                                                           color: #0c63b6;\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \">WELCOME TO</span><span style=\"\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \"><a href=\"https://www.equinoxitsol.com/\" target=\"https://www.equinoxitsol.com/\" style=\"\"><img height=\"\" width=\"\" src=\"https://www.equinoxitsol.com/images/logo.png\" alt=\"CestaSoft\" style=\"max-height: 100%;height:  auto;padding: 0;margin: 0 auto;display: block;\"></a></span>\r\n"
				+ "                                                                     </td>\r\n"
				+ "                                                                  </tr>\r\n"
				+ "                                                               </tbody>\r\n"
				+ "                                                            </table>\r\n"
				+ "                                                         </td>\r\n"
				+ "                                                      </tr>\r\n"
				+ "                                                   </tbody>\r\n"
				+ "                                                </table>\r\n"
				+ "                                             </div>\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                                 <!-- padding -->\r\n" + "                              </td>\r\n"
				+ "                           </tr>\r\n" + "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#fbfcfd\">\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"\">&nbsp;</div>\r\n"
				+ "                                             <div style=\"line-height: 45px;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Delete Done for Candidate :: "
				+ candidateName + "</span></font>\r\n" + "                                             </div>\r\n"

				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"line-height: 0;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"font-size: 30px;text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Congratulations!! </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\" style=\"\r\n"
				+ "                                             \">\r\n"
				/*
				 * + "                                             <div style=\"\r\n" +
				 * "                                                margin-top: 30px;\r\n" +
				 * "                                                \">\r\n" +
				 * "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 15px;\">\r\n"
				 * +
				 * "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 15px;color: #8c8e8d;text-align: left;line-height: 24px;\"> CandidateName is :: "
				 * + candidateName
				 * +" Employee has been  successfully registered. </span></font>\r\n" +
				 * "                                             </div>\r\n"
				 */ + "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                       <tr style=\"\r\n"
				+ "                                          \">\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             /* text-align: left; */\r\n"
				+ "                                             \">\r\n"

				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"

				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           \r\n" + "                           <tr>\r\n"
				+ "                              <td class=\"iage_footer\" align=\"center\" bgcolor=\"#ffffff\">\r\n"
				+ "                                 <!-- padding -->	\r\n"
				+ "                                 <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <font face=\"Arial, Helvetica, sans-serif\" size=\"3\" color=\"#96a5b5\" style=\"font-size: 13px;\">\r\n"
				+ "                                             <span style=\"font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #96a5b5;\">Equinox It Solutions LLC,<br>\r\n"
				+ "                                             13111 N. Central Expressway, <br> Suite 800, Dallas, TX 75243</span></font>				\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           <tr></tr>\r\n" + "                        </tbody>\r\n"
				+ "                     </table>\r\n" + "                  </td>\r\n" + "               </tr>\r\n"
				+ "            </tbody>\r\n" + "         </table>\r\n" + "      </div>\r\n" + "   \r\n" + "</body>\r\n"
				+ "\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;

	}

	synchronized public boolean send(String userfullname, int id) {

		logger.info("entered into send method of utility class");
		boolean sent = false;

		String emailBody = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "   <head>\r\n"
				+ "      <meta charset=\"utf-8\">\r\n"
				+ "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "      <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n"
				+ "      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
				+ "      <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n"
				+ "      <style>\r\n" + "        body {\r\n" + "    background-color: #F5F5F5;\r\n"
				+ "    font-family: Roboto,sans-serif;\r\n" + "    font-weight: 400;\r\n" + "    margin-bottom: 0;\r\n"
				+ "    margin-top: 0;\r\n" + "    padding-top: 0;\r\n" + "}\r\n" + "        td.iage_footer {\r\n"
				+ "    background: #fff;\r\n" + "    padding: 40px 20px;\r\n" + "    border-top: 1px solid #ddd;\r\n"
				+ "}\r\n" + "        \r\n" + "      </style>\r\n" + "   </head>\r\n"
				+ "   <!------ Include the above in your HEAD tag ---------->\r\n" + " <body>\r\n"
				+ "      <div id=\"mailsub\" class=\"notification\" align=\"center\">\r\n"
				+ "         <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"min-width: 320px;\">\r\n"
				+ "            <tbody>\r\n" + "               <tr>\r\n"
				+ "                  <td align=\"center\" bgcolor=\"\" style=\"padding: 20px;\">\r\n"
				+ "                     <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_width_100\" width=\"100%\" style=\"max-width: 680px;min-width: 300px;\">\r\n"
				+ "                        <tbody>\r\n" + "                          \r\n"
				+ "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#ffffff\" style=\"\r\n"
				+ "                                 \">\r\n" + "                                 <!-- padding -->\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <!-- \r\n"
				+ "                                                Item -->\r\n"
				+ "                                             <div class=\"mob_center_bl\" style=\"float: none;width: 53%;margin: 0 auto;display: block;\">\r\n"
				+ "                                                <table class=\"mob_center\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse: collapse;\">\r\n"
				+ "                                                   <tbody>\r\n"
				+ "                                                      <tr>\r\n"
				+ "                                                         <td align=\"center\" valign=\"middle\">\r\n"
				+ "                                                            <!-- padding -->\r\n"
				+ "                                                            <div style=\"\">&nbsp;</div>\r\n"
				+ "                                                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"\r\n"
				+ "                                                               \">\r\n"
				+ "                                                               <tbody>\r\n"
				+ "                                                                  <tr style=\"\">\r\n"
				+ "                                                                     <td align=\"left\" valign=\"top\" class=\"mob_center\" style=\"\">\r\n"
				+ "                                                                        <span style=\"\r\n"
				+ "                                                                           margin-top: 30px;\r\n"
				+ "                                                                           margin-right: 12px;\r\n"
				+ "                                                                           font-size: 20px;\r\n"
				+ "                                                                           color: #0c63b6;\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \">WELCOME TO</span><span style=\"\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \"><a href=\"https://www.equinoxitsol.com/\" target=\"https://www.equinoxitsol.com/\" style=\"\"><img height=\"\" width=\"\" src=\"https://www.equinoxitsol.com/images/logo.png\" alt=\"CestaSoft\" style=\"max-height: 100%;height:  auto;padding: 0;margin: 0 auto;display: block;\"></a></span>\r\n"
				+ "                                                                     </td>\r\n"
				+ "                                                                  </tr>\r\n"
				+ "                                                               </tbody>\r\n"
				+ "                                                            </table>\r\n"
				+ "                                                         </td>\r\n"
				+ "                                                      </tr>\r\n"
				+ "                                                   </tbody>\r\n"
				+ "                                                </table>\r\n"
				+ "                                             </div>\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                                 <!-- padding -->\r\n" + "                              </td>\r\n"
				+ "                           </tr>\r\n" + "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#fbfcfd\">\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"\">&nbsp;</div>\r\n"

				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"line-height: 0;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"font-size: 30px;text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Congratulations!! </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\" style=\"\r\n"
				+ "                                             \">\r\n"
			    + "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                       <tr style=\"\r\n"
				+ "                                          \">\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             /* text-align: left; */\r\n"
				+ "                                             \">\r\n"

				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"

				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           \r\n" + "                           <tr>\r\n"
				+ "                              <td class=\"iage_footer\" align=\"center\" bgcolor=\"#ffffff\">\r\n"
				+ "                                 <!-- padding -->	\r\n"
				+ "                                 <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <font face=\"Arial, Helvetica, sans-serif\" size=\"3\" color=\"#96a5b5\" style=\"font-size: 13px;\">\r\n"
				+ "                                             <span style=\"font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #96a5b5;\">Equinox It Solutions LLC,<br>\r\n"
				+ "                                             13111 N. Central Expressway, <br> Suite 800, Dallas, TX 75243</span></font>				\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           <tr></tr>\r\n" + "                        </tbody>\r\n"
				+ "                     </table>\r\n" + "                  </td>\r\n" + "               </tr>\r\n"
				+ "            </tbody>\r\n" + "         </table>\r\n" + "      </div>\r\n" + "   \r\n" + "</body>\r\n"
				+ "\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;

	}

	synchronized public boolean send(String toMail) {

		logger.info("entered into send method of utility class");
		boolean sent = false;

		String emailBody = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "   <head>\r\n"
				+ "      <meta charset=\"utf-8\">\r\n"
				+ "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "      <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n"
				+ "      <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
				+ "      <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n"
				+ "      <style>\r\n" + "        body {\r\n" + "    background-color: #F5F5F5;\r\n"
				+ "    font-family: Roboto,sans-serif;\r\n" + "    font-weight: 400;\r\n" + "    margin-bottom: 0;\r\n"
				+ "    margin-top: 0;\r\n" + "    padding-top: 0;\r\n" + "}\r\n" + "        td.iage_footer {\r\n"
				+ "    background: #fff;\r\n" + "    padding: 40px 20px;\r\n" + "    border-top: 1px solid #ddd;\r\n"
				+ "}\r\n" + "        \r\n" + "      </style>\r\n" + "   </head>\r\n"
				+ "   <!------ Include the above in your HEAD tag ---------->\r\n" + " <body>\r\n"
				+ "      <div id=\"mailsub\" class=\"notification\" align=\"center\">\r\n"
				+ "         <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"min-width: 320px;\">\r\n"
				+ "            <tbody>\r\n" + "               <tr>\r\n"
				+ "                  <td align=\"center\" bgcolor=\"\" style=\"padding: 20px;\">\r\n"
				+ "                     <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"table_width_100\" width=\"100%\" style=\"max-width: 680px;min-width: 300px;\">\r\n"
				+ "                        <tbody>\r\n" + "                          \r\n"
				+ "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#ffffff\" style=\"\r\n"
				+ "                                 \">\r\n" + "                                 <!-- padding -->\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <!-- \r\n"
				+ "                                                Item -->\r\n"
				+ "                                             <div class=\"mob_center_bl\" style=\"float: none;width: 53%;margin: 0 auto;display: block;\">\r\n"
				+ "                                                <table class=\"mob_center\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\" style=\"border-collapse: collapse;\">\r\n"
				+ "                                                   <tbody>\r\n"
				+ "                                                      <tr>\r\n"
				+ "                                                         <td align=\"center\" valign=\"middle\">\r\n"
				+ "                                                            <!-- padding -->\r\n"
				+ "                                                            <div style=\"\">&nbsp;</div>\r\n"
				+ "                                                            <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"\r\n"
				+ "                                                               \">\r\n"
				+ "                                                               <tbody>\r\n"
				+ "                                                                  <tr style=\"\">\r\n"
				+ "                                                                     <td align=\"left\" valign=\"top\" class=\"mob_center\" style=\"\">\r\n"
				+ "                                                                        <span style=\"\r\n"
				+ "                                                                           margin-top: 30px;\r\n"
				+ "                                                                           margin-right: 12px;\r\n"
				+ "                                                                           font-size: 20px;\r\n"
				+ "                                                                           color: #0c63b6;\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \">WELCOME TO</span><span style=\"\r\n"
				+ "                                                                           float: left;\r\n"
				+ "                                                                           display: inline-block;\r\n"
				+ "                                                                           \"><a href=\"https://www.equinoxitsol.com/\" target=\"https://www.equinoxitsol.com/\" style=\"\"><img height=\"\" width=\"\" src=\"https://www.equinoxitsol.com/images/logo.png\" alt=\"CestaSoft\" style=\"max-height: 100%;height:  auto;padding: 0;margin: 0 auto;display: block;\"></a></span>\r\n"
				+ "                                                                     </td>\r\n"
				+ "                                                                  </tr>\r\n"
				+ "                                                               </tbody>\r\n"
				+ "                                                            </table>\r\n"
				+ "                                                         </td>\r\n"
				+ "                                                      </tr>\r\n"
				+ "                                                   </tbody>\r\n"
				+ "                                                </table>\r\n"
				+ "                                             </div>\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                                 <!-- padding -->\r\n" + "                              </td>\r\n"
				+ "                           </tr>\r\n" + "                           <tr>\r\n"
				+ "                              <td align=\"center\" bgcolor=\"#fbfcfd\">\r\n"
				+ "                                 <table width=\"90%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"\">&nbsp;</div>\r\n"
				+ "                                             <div style=\"line-height: 45px;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"text-align: left;\">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             text-align: left;\r\n"
				+ "                                             \">\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                             <div style=\"line-height: 0;\">\r\n"
				+ "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"font-size: 30px;text-align: left;\">\r\n"
				+ "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\">Congratulations!! </span></font>\r\n"
				+ "                                             </div>\r\n"
				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\" style=\"\r\n"
				+ "                                             \">\r\n"
				/*
				 * + "                                             <div style=\"\r\n" +
				 * "                                                margin-top: 30px;\r\n" +
				 * "                                                \">\r\n" +
				 * "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"4\" color=\"#57697e\" style=\"font-size: 15px;\">\r\n"
				 * +
				 * "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 15px;color: #8c8e8d;text-align: left;line-height: 24px;\"> CandidateName is :: "
				 * + candidateName
				 * +" Employee has been  successfully registered. </span></font>\r\n" +
				 * "                                             </div>\r\n"
				 */ + "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                       <tr style=\"\r\n"
				+ "                                          \">\r\n"
				+ "                                          <td align=\"center\" style=\"\r\n"
				+ "                                             /* text-align: left; */\r\n"
				+ "                                             \">\r\n"

				+ "                                             <!-- padding -->\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n" + "                                       <tr>\r\n"

				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           \r\n" + "                           <tr>\r\n"
				+ "                              <td class=\"iage_footer\" align=\"center\" bgcolor=\"#ffffff\">\r\n"
				+ "                                 <!-- padding -->	\r\n"
				+ "                                 <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n"
				+ "                                    <tbody>\r\n" + "                                       <tr>\r\n"
				+ "                                          <td align=\"left\">\r\n"
				+ "                                             <font face=\"Arial, Helvetica, sans-serif\" size=\"3\" color=\"#96a5b5\" style=\"font-size: 13px;\">\r\n"
				+ "                                             <span style=\"font-family: Arial, Helvetica, sans-serif; font-size: 13px; color: #96a5b5;\">Equinox It Solutions LLC,<br>\r\n"
				+ "                                             13111 N. Central Expressway, <br> Suite 800, Dallas, TX 75243</span></font>				\r\n"
				+ "                                          </td>\r\n"
				+ "                                       </tr>\r\n"
				+ "                                    </tbody>\r\n" + "                                 </table>\r\n"
				+ "                              </td>\r\n" + "                           </tr>\r\n"
				+ "                           <tr></tr>\r\n" + "                        </tbody>\r\n"
				+ "                     </table>\r\n" + "                  </td>\r\n" + "               </tr>\r\n"
				+ "            </tbody>\r\n" + "         </table>\r\n" + "      </div>\r\n" + "   \r\n" + "</body>\r\n"
				+ "\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;

	}

	synchronized public boolean sendMailAfterInterviewed(String toMail, String clientName, String location) {

		logger.info("entered into send method of utility class");
		boolean sent = false;

		String emailBody = "<html>\r\n" + "<head>\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.8.2/css/all.css\" integrity=\"sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay\" crossorigin=\"anonymous\">\r\n"
				+ "</head>\r\n" + "<body>\r\n" + "\r\n"
				+ "<table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n" + "<div id=\"\"></div>\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background-color:#fdfdfd;border:1px solid #dcdcdc;border-radius:3px!important\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background-color:#1f6dad;border-radius:3px 3px 0 0!important;color:#ffffff;border-bottom:0;font-weight:bold;line-height:100%;vertical-align:middle;font-family: 'Open Sans',sans-serif;\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td id=\"\" style=\"padding:10px 48px;display:block\">\r\n"
				+ "<img src=\"http://hrmsdev.tk/assets/images/favicon.png\" style=\"width:20% !important;margin:0 auto;display:block;float: left !important;\">\r\n"
				+ "<h1 style=\"color:#ffffff;font-family:'Open Sans',sans-serif;font-size:25px;font-weight:400;line-height:90px;margin:0;text-align:right !important;\">\r\n"
				+ "Interview</h1>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n"
				+ "</tr>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n"
				+ "<tbody style=\"background-image: url(\"../images/background.png\");>\r\n" + "<tr>\r\n"
				+ "<td id=\"\" valign=\"top\" style=\"background-color:#fdfdfd\">\r\n"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"20\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n" + "<td valign=\"top\" style=\"padding:32px\">\r\n" + "<div id=\"\">\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">"
				+ "\r\n" + "Hello <b></b> \r\n" + "\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">Your profile has been submitted to the following Position. Please find the JD and other information for your reference. </p>\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Client Name<span style=\"padding-left: 80px;\">:"
				+ clientName + "</span></b> <br>\r\n" + "<b>Location<span style=\"padding-left: 108px;\">:" + location
				+ "</span></b> <br>\r\n" + "<b>Title<span style=\"padding-left: 140px;\">:</span></b> <br>\r\n"
				+ "<b>JD<span style=\"padding-left: 157px;\">:</span></b> <br>\r\n"
				+ "<b>Interview Date & Time<span style=\"padding-left: 5px;\">:</span></b> <br>\r\n"
				+ "<b>Mode of Interview<span style=\"padding-left: 35px;\">:</span></b> <br>\r\n" + "</p>\r\n" + "\r\n"
				+ "\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif; font-size: 15px;\"><b>Note:</b> Equinox IT Solutions LLC started serving client since 2009 and is growing rapidly with efforts made by each and every individual in this family. Please make sure not to mis use any access provided to serve you better for growth and development of this Organization.</p>\r\n"
				+ "<h2 style=\"color:#e9b751;display:block;font-family:'Open Sans',sans-serif;font-size:18px;font-weight:bold;line-height:130%;margin:16px 0 8px;text-align:left\">\r\n"
				+ "\r\n"
				+ "<table id=\"\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width:100%;vertical-align:top\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td class=\"\" width=\"50%\" valign=\"top\">\r\n"
				+ "<h3 style=\"font-family:'Open Sans',sans-serif;font-size: 15px;\">Regards</h3>\r\n"
				+ "<p style=\"font-family:'Open Sans',sans-serif;font-size: 15px;\" class=\"\">USER NAME(Should be Sales Recruiter or concerned Person) <br>\r\n"
				+ "Equinox IT Solutions LLC <br>\r\n" + "8500 N Stemmons Fwy, Suite 5080, Dallas, Texas 75247.<br>\r\n"
				+ "469-602-6658 || <a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\"> hr@equinoxitsol.com</a></p>\r\n"
				+ "\r\n" + "</td>\r\n" + "</tr>\r\n" + "<tr>\r\n"
				+ "<td valign=\"top\" style=\"padding: 10px 0px;\">\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Disclaimer:</b><a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\"> www.equinoxitsol.com/email-disclaimer.</a></p>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</div>\r\n" + "</td>\r\n" + "</tr>\r\n"
				+ "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n" + "<td valign=\"top\" style=\"padding:0\">\r\n"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n"
				+ "<td colspan=\"2\" id=\"\" valign=\"middle\" style=\"padding:19px;border:0;color:#fff;font-family:'Open Sans',sans-serif;font-size:12px;text-align:center;background:#1f6dad;\">\r\n"
				+ "<!-- <p>For More Information, Please contact <a class=\"\" href=\"mailto:cs@vydic.org\" target=\"_blank\"> -->\r\n"
				+ "<!-- cs@vydic.org</a></p> -->\r\n" + "<p>E&E Access Point Solution that saves time in your life.\r\n"
				+ "</p>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n"
				+ "\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</body>\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;

	}

	synchronized public boolean sendMailAfterSelected(String toMail) {

		logger.info("entered into send method of utility class");
		boolean sent = false;

		String emailBody = "<html>\r\n" + "<head>\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.8.2/css/all.css\" integrity=\"sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay\" crossorigin=\"anonymous\">\r\n"
				+ "</head>\r\n" + "<body>\r\n" + "\r\n"
				+ "<table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n" + "<div id=\"\"></div>\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background-color:#fdfdfd;border:1px solid #dcdcdc;border-radius:3px!important\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background-color:#1f6dad;border-radius:3px 3px 0 0!important;color:#ffffff;border-bottom:0;font-weight:bold;line-height:100%;vertical-align:middle;font-family: 'Open Sans',sans-serif;\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td id=\"\" style=\"padding:10px 48px;display:block\">\r\n"
				+ "<img src=\"http://hrmsdev.tk/assets/images/favicon.png\" style=\"width:20% !important;margin:0 auto;display:block;float: left !important;\">\r\n"
				+ "<h1 style=\"color:#ffffff;font-family:'Open Sans',sans-serif;font-size:25px;font-weight:400;line-height:90px;margin:0;text-align:right !important;\">\r\n"
				+ "Project Confirmation</h1>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n"
				+ "<tbody style=\"background-image: url(\"../images/background.png\");>\r\n" + "<tr>\r\n"
				+ "<td id=\"\" valign=\"top\" style=\"background-color:#fdfdfd\">\r\n"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"20\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n" + "<td valign=\"top\" style=\"padding:32px\">\r\n" + "<div id=\"\">\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Sub:</b> Project Confirmation  </p>\r\n"
				+ "\r\n" + "\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b></b> <br>\r\n"
				+ "\r\n" + "Hello <b></b> \r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">Congratulations!!</p>\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">Your project is confirmed with the CLIENT and we are moving ahead with other work. \r\n"
				+ "Management will further take action in updating your records. \r\n" + "\r\n" + "</p>\r\n" + "\r\n"
				+ "\r\n" + "\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif; font-size: 15px;\"><b>Note:</b> Equinox IT Solutions LLC started serving client since 2009 and is growing rapidly with efforts made by each and every individual in this family. Please make sure not to mis use any access provided to serve you better for growth and development of this Organization.</p>\r\n"
				+ "<h2 style=\"color:#e9b751;display:block;font-family:'Open Sans',sans-serif;font-size:18px;font-weight:bold;line-height:130%;margin:16px 0 8px;text-align:left\">\r\n"
				+ "\r\n"
				+ "<table id=\"\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width:100%;vertical-align:top\">\r\n"
				+ "<tbody>\r\n" + "<tr>\r\n" + "<td class=\"\" width=\"50%\" valign=\"top\">\r\n"
				+ "<h3 style=\"font-family:'Open Sans',sans-serif;font-size: 15px;\">Regards</h3>\r\n"
				+ "<p style=\"font-family:'Open Sans',sans-serif;font-size: 15px;\" class=\"\">Human Resources Department <br>\r\n"
				+ "Equinox IT Solutions LLC <br>\r\n" + "8500 N Stemmons Fwy, Suite 5080, Dallas, Texas 75247.<br>\r\n"
				+ "469-602-6658 || <a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\"> hr@equinoxitsol.com</a></p>\r\n"
				+ "\r\n" + "</td>\r\n" + "</tr>\r\n" + "<tr>\r\n"
				+ "<td valign=\"top\" style=\"padding: 10px 0px;\">\r\n"
				+ "<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Disclaimer:</b><a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\"> www.equinoxitsol.com/email-disclaimer.</a></p>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</div>\r\n" + "</td>\r\n" + "</tr>\r\n"
				+ "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n"
				+ "</td>\r\n" + "</tr>\r\n" + "<tr>\r\n" + "<td valign=\"top\" align=\"center\">\r\n"
				+ "<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n" + "<td valign=\"top\" style=\"padding:0\">\r\n"
				+ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\">\r\n" + "<tbody>\r\n"
				+ "<tr>\r\n"
				+ "<td colspan=\"2\" id=\"\" valign=\"middle\" style=\"padding:19px;border:0;color:#fff;font-family:'Open Sans',sans-serif;font-size:12px;text-align:center;background:#1f6dad;\">\r\n"
				+ "<p>E&E Access Point Solution that saves time in your life.\r\n"
				+ " <!-- <a class=\"\" href=\"mailto:cs@vydic.org\" target=\"_blank\"> -->\r\n"
				+ "<!-- cs@vydic.org</a> -->\r\n" + "</p>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n"
				+ "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n"
				+ "</tr>\r\n" + "</tbody>\r\n" + "</table>\r\n" + "</td>\r\n" + "</tr>\r\n" + "</tbody>\r\n"
				+ "</table>\r\n" + "</body>\r\n" + "</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;

	}
	synchronized public boolean sendMailToForgetPasswordEmp(String toMail,String forgetUrl,String lastName) {

		logger.info("entered into send method of utility class");
		boolean sent = false;

		String emailBody = "<html>\r\n" + 
				"<head>\r\n" + 
				"<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.8.2/css/all.css\" integrity=\"sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay\" crossorigin=\"anonymous\">\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"\r\n" + 
				"<table width=\"100%\" height=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td valign=\"top\" align=\"center\">\r\n" + 
				"<div id=\"\"></div>\r\n" + 
				"<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background-color:#fdfdfd;border:1px solid #dcdcdc;border-radius:3px!important\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td valign=\"top\" align=\"center\">\r\n" + 
				"<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"background-color:#1f6dad;border-radius:3px 3px 0 0!important;color:#ffffff;border-bottom:0;font-weight:bold;line-height:100%;vertical-align:middle;font-family: 'Open Sans',sans-serif;\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td id=\"\" style=\"padding:10px 48px;display:block\">\r\n" + 
				"<img src=\"http://hrmsdev.tk/assets/images/favicon.png\" style=\"width:20% !important;margin:0 auto;display:block;float: left !important;\">\r\n" + 
				"<h1 style=\"color:#ffffff;font-family:'Open Sans',sans-serif;font-size:25px;font-weight:400;line-height:90px;margin:0;text-align:right !important;\">\r\n" + 
				"Forgot Password</h1>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td valign=\"top\" align=\"center\">\r\n" + 
				"<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\r\n" + 
				"<tbody style=\"background-image: url(\"../images/background.png\");>\r\n" + 
				"<tr>\r\n" + 
				"<td id=\"\" valign=\"top\" style=\"background-color:#fdfdfd\">\r\n" + 
				"<table width=\"100%\" cellspacing=\"0\" cellpadding=\"20\" border=\"0\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td valign=\"top\" style=\"padding:32px\">\r\n" + 
				"<div id=\"\">\r\n" + 
				"<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"></p>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><br>\r\n" + 
				"\r\n" + 
				"Hello <b>"+lastName+"</b> \r\n" + 
				"\r\n" + 
				"<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">Please<a href="+forgetUrl+"><b>CLICK Here</b></a>  to generate your new password. \r\n" + 
				"Passwords are confidential, and don’t share with anyone who is not authorized. \r\n" + 
				"</p>\r\n" + 
				"\r\n" + 
				"<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">If you have any questions on how to access this portal. Kindly send out an email to our HR Department and will guide you the best.  </p>\r\n" + 
				"\r\n" + 
				"<!-- <p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\">If you have any questions on how to access this portal. Kindly send out an email to our HR Department and will guide you the best. </p> -->\r\n" + 
				"<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Contact Email: </b>\r\n" + 
				"\r\n" + 
				"<a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\">  hr@equinoxitsol.com</a></p>\r\n" + 
				"\r\n" + 
				"<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif; font-size: 15px;\"><b>Note:</b> Equinox IT Solutions LLC started serving client since 2009 and is growing rapidly with efforts made by each and every individual in this family. Please make sure not to mis use any access provided to serve you better for growth and development of this Organization.</p>\r\n" + 
				"<h2 style=\"color:#e9b751;display:block;font-family:'Open Sans',sans-serif;font-size:18px;font-weight:bold;line-height:130%;margin:16px 0 8px;text-align:left\">\r\n" + 
				"\r\n" + 
				"<table id=\"\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" style=\"width:100%;vertical-align:top\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td class=\"\" width=\"50%\" valign=\"top\">\r\n" + 
				"<h3 style=\"font-family:'Open Sans',sans-serif;font-size: 15px;\">Regards</h3>\r\n" + 
				"<p style=\"font-family:'Open Sans',sans-serif;font-size: 15px;\" class=\"\">Human Resources Department <br>\r\n" + 
				"Equinox IT Solutions LLC <br>\r\n" + 
				"8500 N Stemmons Fwy, Suite 5080, Dallas, Texas 75247.<br>\r\n" + 
				"469-602-6658 || <a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\"> hr@equinoxitsol.com</a></p>\r\n" + 
				"\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td valign=\"top\" style=\"padding: 10px 0px;\">\r\n" + 
				"<p style=\"margin:0 0 16px;font-family:'Open Sans',sans-serif;font-size: 15px;\"><b>Disclaimer:</b><a class=\"\" href=\"#\" style=\"color:#1f6dad;font-weight:600;text-decoration:underline\"> www.equinoxitsol.com/email-disclaimer.</a></p>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"</div>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"<tr>\r\n" + 
				"<td valign=\"top\" align=\"center\">\r\n" + 
				"<table id=\"\" width=\"600\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td valign=\"top\" style=\"padding:0\">\r\n" + 
				"<table width=\"100%\" cellspacing=\"0\" cellpadding=\"10\" border=\"0\">\r\n" + 
				"<tbody>\r\n" + 
				"<tr>\r\n" + 
				"<td colspan=\"2\" id=\"\" valign=\"middle\" style=\"padding:19px;border:0;color:#fff;font-family:'Open Sans',sans-serif;font-size:12px;text-align:center;background:#1f6dad;\">\r\n" + 
				"<p>E&E Access Point Solution that saves time in your life.\r\n" + 
				" <!-- <a class=\"\" href=\"mailto:cs@vydic.org\" target=\"_blank\"> -->\r\n" + 
				"<!-- cs@vydic.org</a> -->\r\n" + 
				"</p>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"</td>\r\n" + 
				"</tr>\r\n" + 
				"</tbody>\r\n" + 
				"</table>\r\n" + 
				"</body>\r\n" + 
				"</html>";
		try {
			// java.security.Security.addProvider(new
			// com.sun.net.ssl.internal.ssl.Provider());
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			// props.put("mail.smtp.port","587");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.auth", "true");
			// System.out.println("host :" + host);
			props.put("mail.smtp.starttls.enable", "true");
			if (host.contains("gmail")) {
				// System.out.println("in if :");
				props.put("mail.smtp.starttls.enable", "true");
			}
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");
			// props.put("mail.smtp.socketFactory.fallback", false);
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props, auth);
			session.setDebug(true);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromMail));
			InternetAddress[] toAddress = { new InternetAddress(toMail) };
			msg.setRecipients(Message.RecipientType.TO, toAddress);
			// InternetAddress[] ccAddress = new InternetAddress[cc.length];
			// for (int i = 0; i < cc.length; i++) {
			// ccAddress[i] = new InternetAddress(cc[i]);
			// }
			// msg.setRecipients(Message.RecipientType.CC, ccAddress);
			msg.setSubject(subject);
			// create and fill the first message part
			// MimeBodyPart mbp1 = new MimeBodyPart();
			// mbp1.setText(mailbody.toString());
			// create the second message part
			// MimeBodyPart mbp2 = new MimeBodyPart();
			// MimeBodyPart mbp3 = new MimeBodyPart();
			// create the Multipart and add its parts to it
			// Multipart mp = new MimeMultipart();
			// mp.addBodyPart(mbp1);
			// mp.addBodyPart(mbp2);
			// mp.addBodyPart(mbp3);
			// add the Multipart to the message
			msg.setContent(emailBody, "text/html");
			// set the Date: header
			msg.setSentDate(new Date());
			// send the message
			Transport.send(msg);
			sent = true;
			// System.out.println("Mail Sent");
		} catch (Exception e) {
			logger.error("catch block of send method of utility class:" + e);
			sent = false;
			// System.out.println(e.getMessage());
			e.printStackTrace();
			logger.error(e.toString());
		}
		return sent;

	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(fromMail, mailPwd);
		}
	}

}
