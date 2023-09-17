package com.rainier.businesslogic;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.collection.internal.PersistentBag;

import com.rainier.beans.ChangePasswordRequestBean;
import com.rainier.beans.CommonResponseBean;
import com.rainier.beans.LoginRequestBean;
import com.rainier.beans.LoginResponseBean;
import com.rainier.beans.ModulesBean;
import com.rainier.beans.SubMenuRequestBean;
import com.rainier.beans.SubMenuResponseBean;
import com.rainier.dao.HrmsEmployeeDao;
import com.rainier.dao.HrmsUserAuthenticate;
import com.rainier.dao.Impl.HrmsEmployeeDetails;
import com.rainier.dao.Impl.HrmsUserAuthentication;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.dto.requestBean.ForgetPasswordRequest;
import com.rainier.dto.requestBean.PassWordUpadateRequest;
import com.rainier.dto.responseBean.ForgetPasswordResponse;
import com.rainier.dto.responseBean.UpdatePasswordResponse;
import com.rainier.entities.MailLogEntity;
import com.rainier.entities.MenMenu1;
import com.rainier.entities.Privileges;
import com.rainier.entities.User;
import com.rainier.entities.UserLoginLogEntity;
import com.rainier.entities.UserRole;
import com.rainier.utility.FileUploader;
import com.rainier.utility.HrmsGetDateAndTime;
import com.rainier.utility.MD5Generator;
import com.rainier.utility.SendMail;

public class UserAuthentication {

	private static final Logger logger = Logger.getLogger(UserAuthentication.class);

	private final MD5Generator generate = new MD5Generator();
	private static final HrmsUserAuthenticate userAuthentication = new HrmsUserAuthentication();
	private static FileUploader file = new FileUploader();

	// Login service business logic implementation
	public Response userAuthLogic(LoginRequestBean emailPassword) {
		logger.info("entered into userAuthLogic of business class ");
		LoginResponseBean loginBean = new LoginResponseBean();
		try {
			String hashedPassword = generate.generate(emailPassword.getPassword());
			// System.out.println("password encoded into md5.");
			// Loosely Coupling with HrmsUserAuthentication Class
			UserRole userRole1 = new UserRole();
			User employee = new User();
			List<UserRole> listuserRole = userAuthentication.UserData(emailPassword.getEmail(), hashedPassword);
			// System.out.println("got rolename and id from database.");
			boolean firstLogin = false;
			if (!listuserRole.isEmpty()) {
				userRole1 = listuserRole.get(0);
				// PersistentBag type of data fetched in bag.
				PersistentBag bag = (PersistentBag) userRole1.getUsers();
				for (int i = 0; i <= bag.size() - 1; i++) {
					User employee1 = (User) bag.get(i);
					if ((employee1.getUserName().equalsIgnoreCase(emailPassword.getEmail())
							|| employee1.getEmployeeId().equals(emailPassword.getEmail()))
							&& employee1.getPassword().equalsIgnoreCase(hashedPassword)) {
						// System.out.println("user record existing conmfirmed.");
						employee = (User) bag.get(i);
						UserLoginLogEntity entityBean = new UserLoginLogEntity();
						entityBean.setEmployeeId(employee.getEmployeeId());
						entityBean.setEmproleId(employee.getEmproleId());
						entityBean.setEmail(employee.getUserName());
						entityBean.setUserfullName(employee.getFirstName() + " " + employee.getLastName());
						entityBean.setUserId(employee.getId());
						entityBean.setLogindatetime(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
						HrmsUserAuthenticate dao = new HrmsUserAuthentication();
						firstLogin = dao.saveUserLoginlog(entityBean);
					}
				}
				if (employee.getIsActive() == 1) {
					HrmsEmployeeDetails hrmsEmp = new HrmsEmployeeDetails();
					loginBean.setRoleName(userRole1.getRoleName());
					loginBean.setFirstName(employee.getFirstName());
					loginBean.setProfilePictureLink(employee.getProfileImage());
					loginBean.setRoleId(userRole1.getuId());
					loginBean.setIsActive("true");
					loginBean.setMessage("Login Successful");
					loginBean.setStatus(true);
					// loginBean.setModules(menuList);
					loginBean.setUserId(employee.getId());
					loginBean.setFirstLogin(firstLogin);
					loginBean.setRepId(hrmsEmp.getReportingMangerIdByUserId(employee.getId()));
					loginBean.setDateOfJoining(hrmsEmp.getDateOfJoiningOfEmp(employee.getId()));
					loginBean.setLastName(employee.getLastName());
					loginBean.setEmployeeName(employee.getFirstName() + " " + employee.getLastName());
					loginBean.setDesignation(employee.getDesignation());
					loginBean.setEmail(employee.getUserName());
				} else {
					loginBean.setStatus(false);
					loginBean.setMessage("Login Failed, Please Activate User.");
				}
				return Response.status(Response.Status.OK).entity(loginBean).build();
			} else {
				// loginBean.setIsActive("User is not active.");
				// loginBean.setUserId(employee.getId());
				loginBean.setStatus(false);
				loginBean.setMessage(
						"Login Failed, Please Enter Valid Credentials.And User Can Be InActive Please Check");
				return Response.status(Response.Status.OK).entity(loginBean).build();
			}
		} catch (Exception e) {
			logger.error("catch block of userAuthLogic of business class::" + e);
			// System.out.println(e);
			return Response.status(Response.Status.NO_CONTENT).entity(loginBean).build();
		}
	}

	// List of Menu as per role
	public Response getListOfMenus(SubMenuRequestBean subMenuReq) {
		logger.info("entered into getListOfMenus1 of business class ");

		SubMenuResponseBean subMenuResponse = new SubMenuResponseBean();
		try {
			ArrayList<Integer> privilegesList = new ArrayList<Integer>();
			List<Privileges> listPrevileges = userAuthentication.Objet(subMenuReq.getRoleId());
			for (Privileges p : listPrevileges) {
				privilegesList.add(p.getObj());
			}
			List<MenMenu1> lstMenu = userAuthentication.MainMenu(privilegesList);
			ModulesBean dbean = new ModulesBean();
			dbean.setModuleId(0);
			dbean.setMainModule("Dashboard");
			dbean.setModulePath("/Dashboard");
			List<ModulesBean> mainMenu = new ArrayList<ModulesBean>();
			List<ModulesBean> NoModuleList = new ArrayList<ModulesBean>();
			dbean.setSubModules(NoModuleList);
			mainMenu.add(dbean);
			if (lstMenu != null) {
				for (MenMenu1 mainList : lstMenu) {
					if (mainList.getParent() != null && mainList.getParent() == 0) {
						ModulesBean mainBean = new ModulesBean();
						mainBean.setModuleId(mainList.getId());
						mainBean.setMainModule(mainList.getMenuName());
						mainBean.setModulePath(mainList.getUrls());
						int parent = mainList.getId();
						boolean flag = false;
						List<ModulesBean> subMenu = new ArrayList<ModulesBean>();
						for (MenMenu1 subList : lstMenu) {
							if (subList.getParent() != null && subList.getParent() == parent) {
								ModulesBean subBean = new ModulesBean();
								subBean.setModuleId(subList.getId());
								subBean.setMainModule(subList.getMenuName());
								subBean.setModulePath(subList.getUrls());
								subMenu.add(subBean);
								int parent1 = subList.getId();

								List<ModulesBean> innerMenu = new ArrayList<ModulesBean>();
								for (MenMenu1 innerList : lstMenu) {
									if (innerList.getParent() != null && innerList.getParent() == parent1) {
										ModulesBean innerBean = new ModulesBean();
										innerBean.setModuleId(innerList.getId());
										innerBean.setMainModule(innerList.getMenuName());
										innerBean.setModulePath(innerList.getUrls());
										innerMenu.add(innerBean);
										innerBean.setSubModules(NoModuleList);
										if (!flag) {
											mainBean.setModulePath(innerList.getUrls());
											flag = true;
										}
									}
								}
								subBean.setSubModules(innerMenu);
								if (!flag) {
									mainBean.setModulePath(subList.getUrls());
									flag = true;
								}
							}
						}
						mainBean.setSubModules(subMenu);
						mainMenu.add(mainBean);
					}
				}
			}

			if (!mainMenu.isEmpty()) {
				subMenuResponse.setMessage("Menus list is successfully retrieved");
				subMenuResponse.setStatus(true);
				subMenuResponse.setMenusList(mainMenu);
			} else {
				subMenuResponse.setMessage("Failed to retrieve Menus List.");
				subMenuResponse.setStatus(false);
				subMenuResponse.setMenusList(mainMenu);
			}
			return Response.status(Response.Status.OK).entity(subMenuResponse).build();
		} catch (Exception e) {
			logger.error("catch block of getListOfMenus1 method of business class::" + e);
			return Response.status(Response.Status.NO_CONTENT).entity(subMenuResponse).build();
		}
	}

	private final CommonResponseBean responseBean = new CommonResponseBean();

	// Changing Password
	public Response changeCurrentPassword(ChangePasswordRequestBean bean) {
		logger.info("entered into changeCurrentPassword() method of business logic. ");
		String dataBasePassWord = null;
		String hql = null;
		int id = bean.getUserId();
		hql = "select emppassword from main_users Where id=" + id + "";
		dataBasePassWord = (String) DbConnect.DbCon().createSQLQuery(hql).uniqueResult();
		String currentPasssoword = generate.generate(bean.getCurrentPassword());// Here Getting Current PassWord
		if (dataBasePassWord.equals(currentPasssoword)) {// Check For DataBase Password And CurrentPassword
			String newPassword = generate.generate(bean.getNewPassword());// new Generating Password
			if (currentPasssoword.equals(newPassword)) {
				responseBean.setStatus(false);
				responseBean.setMessage("Your Current Password  is Not same, can't be update.");
			} else if (userAuthentication.updateCurrentPassword(currentPasssoword, newPassword,
					bean.getUserId()) == true) {
				responseBean.setStatus(true);
				responseBean.setMessage("Your Password Changed Successfully.");
			} else {
				responseBean.setStatus(false);
				responseBean.setMessage("Your Have Entered Wrong Current Password.");
			}
		}
		return Response.ok().entity(responseBean).build();
	}

	// Forget Password
	public Response forgetPassWord(ForgetPasswordRequest bean) {
		HrmsEmployeeDao db = new HrmsEmployeeDetails();
		logger.info("entered into forgetPassWord() method of business logic. ");
		ForgetPasswordResponse response = new ForgetPasswordResponse();
		String empMail = bean.getEmailAddress();
		if (empMail.length() == 0) {
			response.setSatus(false);
			response.setMessage("Please Provide EmailAddress To Forget The Password");
			return Response.ok().entity(response).build();
		}
		String empMailId = userAuthentication.checkEmailIsSame(bean.getEmailAddress());
		String lastName=userAuthentication.getLastNameOfEmp(bean.getEmailAddress());
		//Integer userId=userAuthentication.getUserIdOfEmp(bean.getEmailAddress());
		if (empMailId == null && lastName==null) {
			response.setMessage("Please Provide Correct EmailAddress");
			response.setSatus(false);
			return Response.ok().entity(response).build();

		}
		if (empMailId.equals(bean.getEmailAddress()) && bean.getForgetPassWordUrl() != null) {
			SendMail send = new SendMail(bean.getEmailAddress(), bean.getForgetPassWordUrl());
			MailLogEntity mailLog = new MailLogEntity();
			boolean emailFlag = false;
			String forgotPwdURL=bean.getForgetPassWordUrl().concat("/").concat("email").concat("=").concat(empMailId);
			emailFlag = send.sendMailToForgetPasswordEmp(bean.getEmailAddress(),forgotPwdURL ,lastName);
			mailLog.setToEmails(bean.getEmailAddress());
			mailLog.setEmailSubject("EEAccess");
			mailLog.setIsSent(1);
			mailLog.setMessage("Message Sent");
			mailLog.setHeader("Greeting from EEAccess");
			if (emailFlag) {
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				db.insertMailLog(mailLog);
				response.setMessage("Email Sent To Your Mail Id Please Check Mail And Reset The Password ");
				response.setSatus(true);
			}

		}
		return Response.ok().entity(response).build();

	}
//Update Password After Forget
	public Response updatePassWord(PassWordUpadateRequest bean) {
		logger.info("Entered into updatePassWord() "); 
		UpdatePasswordResponse response=new UpdatePasswordResponse();
		String newPassword=generate.generate(bean.getNewPassWord());
		if(newPassword.equals(generate.generate(bean.getConfirmPassword()))==true) {
			if(userAuthentication.updatePassword(bean.getEmail(), newPassword)==true){
				response.setStatus(true);
				response.setMessage("Password Update Successfully");
				return Response.ok().entity(response).build();
			}
		}else {
			response.setStatus(false);
			response.setMessage("Please Enter Correct Confirm Password ");
		}
		return Response.ok().entity(response).build();
	}
}
