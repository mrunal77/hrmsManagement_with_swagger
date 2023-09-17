package com.rainier.services;

import java.io.InputStream;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rainier.beans.RecruitmentApplyNowBean;
import com.rainier.beans.RecruitmentOpeningBean;
import com.rainier.beans.RecruitmentPostProfileBean;
import com.rainier.businesslogic.RecruitmentPortal;

import com.rainier.entities.RecruitmentCandidateSignUpEntity;
import com.rainier.entities.RecruitmentInterviewScheduleEntity;
import com.rainier.entities.RecruitmentPostProfileEntity;
import com.rainier.utility.FileUploader;

// @CrossOrigin(maxAge = 3600, allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("/Recruitment")

public class RecruitmentService {
	public static Logger logger = Logger.getLogger(RecruitmentService.class);
	RecruitmentPortal portal = new RecruitmentPortal();
	private static FileUploader upload = new FileUploader();

	@Path("/Vacancies")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveOpeningpositions(RecruitmentOpeningBean bean) {

		logger.info("Entered Into saveOpeningposition() method");
		if (bean != null) {
			return portal.saveOpeningPositions(bean);

		}

		else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@Path("/CandidateSignUp")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveCandidateProfile(RecruitmentCandidateSignUpEntity entity) {

		logger.info("Entered Into saveOpeningposition() method");
		if (entity != null) {
			return portal.saveCandidateProfile(entity);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	// fetch for Job Openings Service ...
	@Path("/OpenJobList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchJobOpenings(@QueryParam(value = "roleId") int roleId,
			@QueryParam(value = "menuId") int menuId) {
		logger.info("Entered Into fetchJobOpenings() method");
		logger.info("existed Into fetchJobOpenings() method");
		return portal.fetchJobOpenings(roleId, menuId);

	}

	// Apply now for particular job... (Text + File )
	@Path("ApplyNowForJOb")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
	@CrossOrigin
	public Response saveUpdateApplyNowForJOb(@NotNull @FormDataParam("applyNowDetails") FormDataBodyPart jsonData,
			@NotNull @FormDataParam("filename") InputStream uploadInputStream,
			@NotNull @FormDataParam("filename") FormDataContentDisposition fileDetails) {
		logger.info("Inside saveUpdateApplyNowForJOb()");
		jsonData.setMediaType(MediaType.APPLICATION_JSON_TYPE);

		RecruitmentApplyNowBean bean = jsonData.getValueAs(RecruitmentApplyNowBean.class);
		if (fileDetails != null) {
			String fileName = fileDetails.getFileName();
			FileUploader upload = new FileUploader();
			String uploadedFilePath = upload.getUploadResumePath(0, fileName, uploadInputStream);
			if (!uploadedFilePath.equalsIgnoreCase("")) {
				bean.setUploadResume(uploadedFilePath);
			}
			return portal.saveApplyForjob(bean);
		} else
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
	}
// apply now ....

	@Path("/applyNow")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveApplyNow(RecruitmentApplyNowBean bean) {

		logger.info("Entered Into saveOpeningposition() method");
		if (bean != null) {
			return portal.saveApply(bean);

		}

		else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	// Resume Uploading Service...

	@Path("uploadResume/{applyId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@CrossOrigin
	public Response UploadExpLetterHistory(@NotNull @PathParam(value = "applyId") int applyId,
			@FormDataParam("resume") InputStream uploadInputStram,
			@FormDataParam("resume") FormDataContentDisposition expFileDetails) {

		logger.info("resume Upload Service of service class..");

		String resumeExtentsion = expFileDetails.getFileName().substring(expFileDetails.getFileName().lastIndexOf("."));
		if (resumeExtentsion.equals(".doc") || resumeExtentsion.equals(".pdf")) {

			String fileName = "resume" + applyId + resumeExtentsion;
			String filePath = upload.getUploadResumePath(applyId, fileName, uploadInputStram);
			logger.info("Upload Path Received:" + filePath);
			if (!filePath.equalsIgnoreCase("")) {
				return portal.uploadResume(filePath, applyId);
			} else
				return Response.status(Response.Status.CONFLICT).build();
		} else {
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}

	}

	// fetch for applied Job List....
	@Path("/appliedJobList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchJobOpenings() {

		return portal.getAppliedJobDetails();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getRequisitionCode")
	@CrossOrigin
	public Response autoGeneraterequisitionCode() {
		logger.info("entered into autoGenerateEmployeeId service class method..");
		return portal.requisitionIdAutoGeneration();
	}

	// post profile Service (TEXT + FILE)

	@Path("/SavePost-Profile")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
	@CrossOrigin
	public Response savePostProfile(@NotNull @FormDataParam("postProfile") FormDataBodyPart jsonData,
			@NotNull @FormDataParam("filename") InputStream uploadedInputStream,
			@NotNull @FormDataParam("filename") FormDataContentDisposition fileDetails) {

		logger.info("Entyered into savePostProfile( ) method of Service class.");

		jsonData.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		RecruitmentPostProfileEntity entity = jsonData.getValueAs(RecruitmentPostProfileEntity.class);

		if (fileDetails != null) {
			String fileName = fileDetails.getFileName();
			FileUploader upload = new FileUploader();
			int id = 0;
			// int applyId = 0;
			String uploadedFilePath = upload.getUploadPostProfilePath(id, fileName, uploadedInputStream);
			if (!uploadedFilePath.equalsIgnoreCase("")) {
				entity.setUploadProfile(uploadedFilePath);
			}
			return portal.savePostProfile(entity);
		} else
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();

	}

	// post profile Fetch Service....

	@Path("/PostProfileForcandidate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchpostprofile(@QueryParam(value = "id") int id) {
		return portal.getPostProfileDetails(id);

	}

	// interview Schedule Service ...
	@Path("/ScheduleInterview")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveInterviewSchedule(RecruitmentInterviewScheduleEntity entity) {
		return portal.saveInterviewSchedule(entity);

	}

	// For All post profile Fetch Service....

	@Path("/PostProfileForAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchpostprofileList( ) {
		return portal.getAllPostProfile();
	}

	// For All post profile Fetch Service....

	@Path("/InterviewScheduleAll")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getInterviewScheduleAll() {

		logger.info("Entered into getInterviewScheduleAll() methos of Recruitment Service Class.");
		return portal.getAllScheduleList();
	}

	// interview Schedule Update Service ...Status
	@Path("/UpdateInterviewStatus")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateInterviewScheduleStatus(RecruitmentInterviewScheduleEntity entity) {
		return portal.updateInterviewScheduleStatus(entity);

	}

	// For All Interview Status Service....Base don Status:-

	@Path("/GetInterviewStatus")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInterviewStatus(@QueryParam(value = "interviewStatus") String interviewStatus) {

		logger.info("Entered into getInterviewStatus() methos of Recruitment Service Class.");
		return portal.getAllScheduleListStatus(interviewStatus);
	}

	// For All Interview Status Service....Base don Status:-

	@Path("/CountInterviewStatus")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response countInterviewStatus(@QueryParam(value = "candidateId") int candidateId) {

		logger.info("Entered into countInterviewStatus() methos of Recruitment Service Class.");
		return portal.getInterviewStatusCount(candidateId);
	}

	// Post Profile List  for particular job... (Text + File )
	/*@Path("PostProfileListAdd")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
	@CrossOrigin
	public Response saveUpdateApplyNowForJObList(@NotNull @FormDataParam("postData") FormDataBodyPart jsonData,
			@NotNull @FormDataParam("filenames") InputStream uploadInputStream,
			@NotNull @FormDataParam("filenames") FormDataContentDisposition fileDetails) {
		logger.info("Inside saveUpdateApplyNowForJObList()");
		
		jsonData.setMediaType(MediaType.APPLICATION_JSON_TYPE);

		RecruitmentPostProfileBean bean = jsonData.getValueAs(RecruitmentPostProfileBean.class);
		if (fileDetails != null) {
			String fileName = fileDetails.getFileName();
			FileUploader upload = new FileUploader();
			//int id;
			String uploadedFilePath = upload.getUploadPostProfilePath(0, fileName, uploadInputStream);
			if (!uploadedFilePath.equalsIgnoreCase("")) {
				bean.setUploadProfile(uploadedFilePath);
			}
			return portal.saveApplyForjobList(bean);
		} else
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
	}
*/
}
