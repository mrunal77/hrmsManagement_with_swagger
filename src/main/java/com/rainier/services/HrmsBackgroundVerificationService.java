package com.rainier.services;

import com.rainier.beans.*;
import com.rainier.businesslogic.BackgroundCheckDetails;
import com.rainier.utility.FileUploader;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

@Path("/BackgroundCheck")
@Api("/BackgroundCheck")
@SwaggerDefinition(tags = {@Tag(name = "BackgroundCheck", description = "REST BackgroundCheck")})
public class HrmsBackgroundVerificationService {
	private static FileUploader upload = new FileUploader();

	public static Logger logger = Logger.getLogger(HrmsBackgroundVerificationService.class);
	private static BackgroundCheckDetails bgDetails = new BackgroundCheckDetails();

	// save Highest Degree Earned service.
	@Path("/AddHighestDegreeEarned")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveHighestDegree(BgHighestDegreeEarnedBean bean) {
		return bgDetails.SaveHighestDegreeWithOther(bean);
	}

	// for fetch Highest Degree Earned.
	@Path("/FetchHighestDegreeEarned")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getHighestDegree(@QueryParam(value = "userId") int userId) {
		return bgDetails.getHighestDegreeEarned(userId);
	}

	// Emp personal Info.
	@Path("/AddEmpPersonalInfo")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveEmpPersonalInfo(BgEmpPersonalInfoBean bean) {
		return bgDetails.saveEmpPersonalDetails(bean);
	}

	// Employment History Details
	@Path("/AddEmploymentHistory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response saveEmploymentHistory(BgEmploymentHistoryBean bean) {
		return bgDetails.saveEmploymentHistory(bean);
	}

	// Employment Professional Reference.
	@Path("/SaveEmpReferredByDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveEmpProfessionalReferred(BgEmpProfessionalReferenceBean bean) {
		return bgDetails.saveProfessionalReference(bean);
	}

	// Employment gap Save
	@Path("/AddEmploymentGap")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveEmploymentgap(BgEmpEmploymentGapBean bean) {
		return bgDetails.saveEmploymentGap(bean);
	}

	// for Eamployment gap Earned.

	@Path("/FetchEmploymentGap")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getEmploymentGap(@QueryParam(value = "userId") int userId) {
		return bgDetails.fetchEmploymentGap(userId);
	}

	// for Eamployment Professional reference.

	@Path("/FetchEmpProfessionalReference")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getEmpProfessionalReference(@QueryParam(value = "userId") int userId) {
		return bgDetails.fetchEmpProfessionalReference(userId);
	}

	// for Eamployment History

	@Path("/FetchEmpEmploymentHistory")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getEmpEmploymentHistory(@QueryParam(value = "userId") int userId) {
		return bgDetails.fetchEmpEmploymentHistory(userId);
	}

	// for Eamployee Personal Info.

	@Path("/FetchEmployeePersonalInfo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getEmpEmployeePersonalInfo(@QueryParam(value = "userId") int userId) {
		return bgDetails.fetchEmployeePersonalInfo(userId);
	}

	// Employment Worked Details Details
	@Path("/SaveEmpWorkedDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response saveEmpWorkedDetails(BgEmploymentHistoryBean bean) {
		return bgDetails.saveEmpWorkedHistory(bean);
	}

	// upload ExpLetter...

	@Path("uploadExpLetter/{companyid}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response UploadExpLetterHistory(@NotNull @PathParam(value = "companyid") int companyid,
			@FormDataParam("expLetter") InputStream uploadInputStramExpLetter,
			@FormDataParam("expLetter") FormDataContentDisposition expFileDetails) {

		logger.info("ExpLetter Upload Service of service class..");

		String expExtentsion = expFileDetails.getFileName().substring(expFileDetails.getFileName().lastIndexOf("."));
		if (expExtentsion.equals(".jpg") || expExtentsion.equals(".png") || expExtentsion.equals(".jpeg")) {

			String fileName = "expLetter_" + companyid + expExtentsion;
			String filePath = upload.getUploadPath(companyid, fileName, uploadInputStramExpLetter);
			logger.info("Upload Path Received:" + filePath);
			if (!filePath.equalsIgnoreCase("")) {
				return bgDetails.uploadExpLetter(filePath, companyid);
			} else
				return Response.status(Response.Status.CONFLICT).build();
		} else {
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}

	}

	// Simple HighestDegree.
	@Path("/SaveDegree")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveDegree(BgHighestDegreeSimpleBean bean) {

		return bgDetails.saveHighestDegree(bean);

	}

	// fetch Simple Highest Degree
	@Path("/DegreeDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getDegree(@QueryParam(value="userId")int userId) {
		return bgDetails.fetchDegree(userId);
	}
	
	// Simple EmpRef.
		@Path("/SaveUpReferenceList")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@CrossOrigin
		public Response saveUpdateEmpRefList(BgEmpProfessionalReferenceSimpleBean bean) {
       return bgDetails.saveEmpRefList(bean);
		}
		
		//Simple Save And Update Employee History
		@Path("/SaveUpHisList")
		@POST
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		@CrossOrigin
		public Response saveUpdateEmpHis(BgEmployementHistorySimpleBean bean) {
       return bgDetails.saveUpdate(bean);
		}
		
}
