package com.rainier.services;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rainier.beans.RecruitmentAssignJobBean;
import com.rainier.beans.RecruitmentAssignStatusBean;
import com.rainier.beans.RecruitmentPostProfileBean;
import com.rainier.beans.RecruitmentPostProfileDuplicacyBean;
import com.rainier.businesslogic.RecruitmentPortal;
import com.rainier.utility.FileUploadUtility;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("/PostProfile")

public class HrmsPostProfileService {
	public static Logger logger = Logger.getLogger(RecruitmentService.class);

	RecruitmentPortal postPortal = new RecruitmentPortal();
	private static FileUploadUtility uoploadPostprofile = new FileUploadUtility();

	@Path("/savePostProfileList")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
	@CrossOrigin
	public Response saveUpdateApplyNowForJObList(@NotNull @FormDataParam("postData") FormDataBodyPart jsonData,
			@NotNull @FormDataParam("filename") InputStream uploadInputStream,
			@NotNull @FormDataParam("filename") FormDataContentDisposition fileDetails) {
		logger.info("Inside saveUpdateApplyNowForJObList()");

		jsonData.setMediaType(MediaType.APPLICATION_JSON_TYPE);

		RecruitmentPostProfileBean bean = jsonData.getValueAs(RecruitmentPostProfileBean.class);
		if (fileDetails != null) {
			String fileName = fileDetails.getFileName();
			FileUploadUtility uoploadPostprofile = new FileUploadUtility();

			String uploadedFilePath = uoploadPostprofile.getUploadPostProfilePath(0, fileName, uploadInputStream);
			if (!uploadedFilePath.equalsIgnoreCase("")) {
				bean.setUploadProfile(uploadedFilePath);
			}
			return postPortal.saveApplyForjobList(bean);
		} else
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
	}

	// Post Profile Checking Duplicate Email ....Existance ...
	@Path("/CheckDuplicateEmail")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response countInterviewStatus(@QueryParam(value = "candidateEmail") String candidateEmail) {

		logger.info("Entered into countInterviewStatus() methos of Recruitment Service Class.");
		return postPortal.checkDuplicateEmailPostProfile(candidateEmail);
	}

	// All Candidate Id and Information.....
	@Path("/GetAllcandidateId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCandidateIdDetails() {
		return postPortal.getAllcandidateIdDetails();

	}
	// Assign to job Service ...

	@Path("/AssignToJobDirectly")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignToJob(RecruitmentAssignJobBean bean) {
		return postPortal.assignToJobDirectly(bean);

	}

	// Assigned Job Candidate List....
	@Path("/AssignedCandidateForJob")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignedCandidateForJob() {
		return postPortal.fetchAssignedJobCandidateList();

	}

	// post profile--candidate Id Created By Vendor.
	@Path("/savePostProfileListByVendor")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
	@CrossOrigin
	public Response saveUpdateApplyNowForJObListByVendor(
			@NotNull @FormDataParam("postProfile") FormDataBodyPart jsonData,
			@NotNull @FormDataParam("filename") InputStream uploadInputStream,
			@NotNull @FormDataParam("filename") FormDataContentDisposition fileDetails) {
		logger.info("Inside saveUpdateApplyNowForJObList()");

		jsonData.setMediaType(MediaType.APPLICATION_JSON_TYPE);

		RecruitmentPostProfileBean bean = jsonData.getValueAs(RecruitmentPostProfileBean.class);
		if (fileDetails != null) {
			String fileName = fileDetails.getFileName();
			FileUploadUtility uoploadPostprofile = new FileUploadUtility();

			String uploadedFilePath = uoploadPostprofile.getUploadPostProfilePath(0, fileName, uploadInputStream);
			if (!uploadedFilePath.equalsIgnoreCase("")) {
				bean.setUploadProfile(uploadedFilePath);
			}
			return postPortal.saveApplyForjobListByVendor(bean);
		} else
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
	}

	// All Post Profile Duplicacy check info....
	@Path("/DuplicacyCheckPostprofile")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response duplicacyCheckPostprofile(@QueryParam(value = "candidateEmailid") String candidateEmailid,
			@QueryParam(value = "identificationId") String identificationId) {

		return postPortal.getPostProfileDuplicacy(candidateEmailid, identificationId);
	}

	// Assign to job Service ...

	@Path("/AssignToJob")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignToJobDirectly(RecruitmentAssignJobBean bean) {
		return postPortal.assignToJobDirectlyCandidateList(bean);

	}

	// Assigned Job Candidate ....
	@Path("/AssignedCandidate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignedCandidate(@QueryParam(value = "jobPostingId") int jobPostingId) {
		return postPortal.fetchAssignedJobCandidate(jobPostingId);

	}

	// Fetch status Report....
	@Path("/StatusReportCandidate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response statusReportCandidate() {
		return postPortal.getStatusReportForEachcandidate();

	}

	// Assign to job Update Status Service ...

	@Path("/reportUpdate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignToJobEachRowUpdate(RecruitmentAssignStatusBean bean) {
		return postPortal.updateAssignedReport(bean);

	}

	// Assign Report Updated Based on the Click Service.

	@Path("/UpdatedReportBasedOnStatus")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatedReportBasedOnStatus(@QueryParam(value = "assignReportStatus") String assignReportStatus) {
		return postPortal.getUpdatedStatusReport(assignReportStatus);

	}

	// 1.) Selected Status

	@Path("/SelectedStatusReportCandidate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response SelectedStatusReportCandidate() {
		return postPortal.getUpdatedSelectedStatusReport();

	}

	// Report Summary Based on the Recruiters.

	@Path("/ReportSummaryBasedRecruitersName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response reportSummaryBasedRecruitersName(@QueryParam(value = "recruiters") String recruiters,@QueryParam(value = "assignReportStatus")String assignReportStatus) {
		return postPortal.getRecruitersBasedStatus(recruiters, assignReportStatus);

	}

}
