package com.rainier.utility;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;



public class SendMailBenchSalesEmployeeUtility {
	
	private static final Logger logger = Logger.getLogger(SendMailBenchSalesEmployeeUtility.class);
	
  public String fromMail = "",mailPwd="", toMail = "", host = "smtp.gmail.com",  subject = "";
	  
	  
	  public SendMailBenchSalesEmployeeUtility() {
		  fromMail="rainiersoft123@gmail.com";
		  mailPwd = "Rainier@123";
		  host = "smtp.gmail.com";
		  subject = "Employee Added By Admin Submission Status";
		  
	  }
	  
	  synchronized public boolean sendToEmployee(String toMail,String name) {
		  boolean sent=false;
		  
		  logger.info("entered into send method of utility class");
		  
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
	                + "                                                                           \">Greeting From EEAccess</span><span style=\"\r\n"
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
	                + name + ",</span></font>\r\n" + "                                             </div>\r\n"
	                + "                                             <!-- padding -->\r\n"
	                + "                                          </td>\r\n"
	                + "                                       </tr>\r\n" + "                                       <tr>\r\n"
	                + "                                          <td align=\"center\" style=\"\r\n"
	                + "                                             text-align: left;\r\n"
	                + "                                             \">\r\n"
	                + "                                             <!-- padding -->\r\n"
	                + "                                             <div style=\"line-height: 0;\">\r\n"
	                + "                                                <font face=\"Arial, Helvetica, sans-serif\" size=\"5\" color=\"#57697e\" style=\"font-size: 30px;text-align: left;\">\r\n"
	                + "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;color: #333;text-align: left;\"> WoW Congratulations.. </span></font>\r\n"
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
	                + "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 15px;color: #8c8e8d;text-align: left;line-height: 24px;\">Congratulations !! You are now Added as a Employee For Our Organization.  </span></font>\r\n"
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
	                + "                                          <td align=\"left\">\r\n"
	              
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
	                + "                                                <span style=\"font-family: Arial, Helvetica, sans-serif;font-size: 16px;/* color: #8c8e8d; */text-align: left;\">Regards <br>BenchSales Team</span></font>\r\n"
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
			  Properties props = new Properties();
	           props.put("mail.smtp.host", host);
	           props.put("mail.smtp.debug", "true");
	           props.put("mail.smtp.auth", "true");
	           props.put("mail.smtp.starttls.enable", "true");
	           
	           if (host.contains("gmail")) {
	              
	                props.put("mail.smtp.starttls.enable", "true");
	            }
	            props.put("mail.transport.protocol", "smtp");
	            props.put("mail.smtp.tsl.trust", "smtp.gmail.com");
	            props.put("mail.smtp.port", "587");
	           // props.put("mail.smtp.port", "25");
	            // ssl - localhost
	            Authenticator auth = new SMTPAuthenticator();
	            Session session = Session.getInstance(props, auth);
	            session.setDebug(true);
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress(fromMail));
	            InternetAddress[] toAddress = {new InternetAddress(toMail)};
	            msg.setRecipients(Message.RecipientType.TO, toAddress);
	            msg.setSubject(subject);
	            msg.setContent(emailBody, "text/html");
	            msg.setSentDate(new Date());
	            Transport.send(msg);
	            sent = true;
	            
			  
		  }catch(Exception e) {
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
