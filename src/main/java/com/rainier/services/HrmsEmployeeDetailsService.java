package com.rainier.services;

import com.rainier.beans.*;
import com.rainier.businesslogic.EmployeeDetailsSummary;
import com.rainier.utility.FileUploader;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("Employees")
public class HrmsEmployeeDetailsService {

	final static Logger logger = Logger.getLogger(HrmsEmployeeDetailsService.class);
	private static EmployeeDetailsSummary db = new EmployeeDetailsSummary();
	private static FileUploader upload = new FileUploader();

	@Path("/addEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	@POST
	public Response saveEmployee(EmployeeRequestBean bean) {
		if (bean != null) {
			return db.saveEmployeeOfficialDetails(bean);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Path("/updateEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	@CrossOrigin
	public Response updateEmployee(EmployeeRequestBean bean) {
		if (bean != null) {
			return db.updateEmployeeOfficialDetails(bean);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	@Path("/getEmpOfficialInfo")
	public Response getEmpList(@QueryParam(value = "roleId") int roleId, @QueryParam(value = "menuId") int menuId,
			@QueryParam(value = "userId") int userId, @QueryParam("filter") String filter) {
		logger.info("entered into getEmpList service class method..");
		return db.getOfficialdetailsOfEmployee(roleId, menuId, userId, filter);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	@Path("/getBUDUEmpList")
	public Response getBUDUEmpList(@QueryParam(value = "businessunitId") int businessunitId,
			@QueryParam(value = "departmentId") int departmentId) {
		logger.info("entered into getBUDUEmpList service class method..");
		return db.getEmpDataBUDUInfo(businessunitId, departmentId);
	}

	// add employee leave for Hr module.
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	@Path("/addEmployeeLeaveCount")
	public Response getEmpLeave(LeaveAllottedRequestBean leaveBean) {
		logger.info("entered into getEmpLeave service class method..");
		return db.getEmployeeLeave(leaveBean);
	}

	/*
	 * // Adding all employee leave limit set by super Admin or Management.
	 *
	 * @GET
	 *
	 * @Produces(MediaType.APPLICATION_JSON)
	 *
	 * @CrossOrigin
	 *
	 * @Path("/AddEmpLeaveByAdmin") public Response
	 * addedEmpLeavelist(FetchAddEmpleaveRequestBean
	 * addLimit,@QueryParam(value="roleId")int roleId,@QueryParam(value="menuId")int
	 * menuId) {
	 * logger.info("entered into addedEmpLeavelist service class method..");
	 * EmployeeDetailsSummary eds=new EmployeeDetailsSummary(); return
	 * eds.addLeaveLimitByAdmin(addLimit, roleId, menuId);
	 *
	 * }
	 */

	// for employee profile service.
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/GetEmployeeProfile")
	@CrossOrigin
	public Response getEmpProfiles(@QueryParam(value = "employeeId") String employeeId) {
		logger.info("entered into getEmpProfiles service class method..");
		return db.getEmployeeProfile(employeeId);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getEmployeeId")
	@CrossOrigin
	public Response autoGenerateEmployeeId() {
		logger.info("entered into autoGenerateEmployeeId service class method..");
		return db.employeeIdAutoGeneration();
	}

	// Save Employee Personal Details.
	@POST
	@Path("savePersonalDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response savePersonalDetails(PersonalDetailsRequestBean bean) {
		if (bean != null)
			return db.savePersonalDetails(bean);
		else
			return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// Get Emp Personal Details
	@GET
	@Path("getPersonalDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmpPersonalDetails(@QueryParam(value = "userId") int userId) {
		return db.getEmpPersonalDetails(userId);
	}

	// Update Emp Personal Details
	@POST
	@Path("updatePersonalDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePersonalDetails(PersonalDetailsRequestBean bean) {
		if (bean != null)
			return db.updatePersonalDetails(bean);
		else
			return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// Save Emp Contact Details\
	@POST
	@Path("saveContactDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveEmpContactDetails(ContactDetailsRequestBean bean) {
		if (bean != null)
			return db.saveContactDetails(bean);
		else
			return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// Save Emp Contact Details\
	@POST
	@Path("updateContactDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateEmpContactDetails(ContactDetailsRequestBean bean) {
		if (bean != null)
			return db.updateContactDetails(bean);
		else
			return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// Get Emp Contact Details
	@GET
	@Path("getContactDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContactDetails(@QueryParam(value = "userId") int userId) {
		return db.getEmpContactDetails(userId);
	}

	// Get Emp Salary Details
	@GET
	@Path("getSalaryDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSalaryDetails(@QueryParam(value = "userId") int userId) {
		return db.getEmpSalaryDetails(userId);
	}

	// Update Emp Salary Details
	@POST
	@Path("updateSalaryDetails")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSalaryDetails(SalaryDetailsBean bean) {
		if (bean != null)
			return db.updateSalaryDetails(bean);
		else
			return Response.status(Response.Status.BAD_REQUEST).build();
	}

	// Signature Uploading Service.
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("uploadSignature/{userId}")
	public Response uploadSignature(@NotNull @PathParam(value = "userId") int userId,
			@FormDataParam("sign") InputStream uploadInputStream,
			@FormDataParam("sign") FormDataContentDisposition fileDetails) {
		logger.info("Signature Upload Service of service class..");
		String fileExtension = fileDetails.getFileName().substring(fileDetails.getFileName().lastIndexOf("."));

		if (fileExtension.equals(".jpg") || fileExtension.equals(".png") || fileExtension.equals(".jpeg")) {
			String fileName = "signature_" + userId + fileExtension;
			String filePath = upload.getUploadPath(userId, fileName, uploadInputStream);
			logger.info("Upload Path Received:" + filePath);
			if (!filePath.equalsIgnoreCase(""))
				return db.signaturePathUpdate(filePath, userId);
			else
				return Response.status(Response.Status.CONFLICT).build();
		} else {
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}
	}

	// Hr Login After that he will get All added emp official Details.
	@Path("/getEmpOfficialInfoAddedByHR")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin

	public Response getEmpList(@QueryParam("hrManagerName") String hrManagerName, @QueryParam("roleId") int roleId,
			@QueryParam("menuId") int menuId) {
		logger.info("entered into getEmpList service class method..");
		return db.getOfficialdetailsOfEmployeeForHR(hrManagerName, roleId, menuId);
	}

	// Email Address Duplicate Check Service for Adding Employee.
	@GET
	@Path("checkExistedEmail")
	@Produces(MediaType.APPLICATION_JSON)
	public Response checkExistedEmail(@QueryParam(value = "emailAddress") String emailAddress) {
		return db.existedEmailFindService(emailAddress);
	}

	// get Sells Executive Service
	@GET
	@Path("getSalesExecutive")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSalesExecutive() {
		return db.getListOfusername();
	}

	// Employee Soft Delete Service
	@Path("/employeeSoftDelete")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response softDeleteEmp(@QueryParam("userId") int userId) {
		return db.softDeleteResponse(userId);
	}

	@Path("/getAllInActiveEmp")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchAllInActive() {
		return db.getAllInActiveEmp();
	}

	// Employee Soft Delete Service
	@Path("/getAllEmpByRepId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getAllEmpByRepId(@QueryParam("repId") int repId) {
		return db.getAllEmpByRepId(repId);
	}

}
