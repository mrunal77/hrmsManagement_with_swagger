package com.rainier.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rainier.beans.ChangePasswordRequestBean;
import com.rainier.beans.LoginRequestBean;
import com.rainier.beans.SubMenuRequestBean;
import com.rainier.businesslogic.UserAuthentication;
import com.rainier.dto.requestBean.ForgetPasswordRequest;
import com.rainier.dto.requestBean.PassWordUpadateRequest;

// @CrossOrigin
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("/userService")
public class HrmsLoginService {

	private static final Logger logger = Logger.getLogger(HrmsLoginService.class);
	private static UserAuthentication userAuthentication = new UserAuthentication();

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response UserAuthentication(LoginRequestBean emailPassword) {
		logger.info("entered into UserAuthentication method of service class..");
		if (emailPassword != null) {
			return userAuthentication.userAuthLogic(emailPassword);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Path("/menuList1")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListMenu(SubMenuRequestBean submenureq) {
		logger.info("entered into getListMenu method of service class..");

		if (submenureq != null) {
			return userAuthentication.getListOfMenus(submenureq);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("changePassword")
	public Response changePassword(ChangePasswordRequestBean bean) {
		logger.info("entered into changePassword method of service class.");
		if (bean == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return userAuthentication.changeCurrentPassword(bean);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/forgetPassword")
	public Response forgetPassword(ForgetPasswordRequest bean) {
		logger.info("entered into forgetPassword method of service class.");
		if (bean == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return userAuthentication.forgetPassWord(bean);
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/updatePassword")
	public Response updatePassword(PassWordUpadateRequest bean) {
		logger.info("entered into updatePassword method of service class.");
		if (bean == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		} else {
			return userAuthentication.updatePassWord(bean);
		}
	}
}
