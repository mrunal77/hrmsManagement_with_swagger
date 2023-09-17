package com.rainier.businesslogic;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.rainier.beans.CommonResponseBean;
import com.rainier.beans.DeptHeadBean;
import com.rainier.beans.EmployeeIdAutoGenerationResponseBean;
import com.rainier.beans.LovResponseBean;
import com.rainier.beans.Main_DepartmentBean;
import com.rainier.beans.RecruitInterviewStatusRespBean;
import com.rainier.beans.RecruitersSummaryReportBean;
import com.rainier.beans.RecruitmentApplicantStatusResponseBean;
import com.rainier.beans.RecruitmentApplyNowBean;
import com.rainier.beans.RecruitmentAssignCandidateApplyId;
import com.rainier.beans.RecruitmentAssignJobBean;
import com.rainier.beans.RecruitmentAssignReportResponseBean;
import com.rainier.beans.RecruitmentAssignStatusBean;
import com.rainier.beans.RecruitmentCheckEmailStatusResponse;
import com.rainier.beans.RecruitmentOpeningBean;
import com.rainier.beans.RecruitmentPostProfileBean;
import com.rainier.beans.RecruitmentPostProfileDuplicacyBean;
import com.rainier.beans.RequisitionIdAutoGenerationResponseBean;
import com.rainier.beans.SuccessResponseBean;
import com.rainier.dao.HrmsListOfValuesDao;
import com.rainier.dao.HrmsRecruitmentDao;
import com.rainier.dao.HrmsUserAuthenticate;
import com.rainier.dao.Impl.HrmsListOfValues;
import com.rainier.dao.Impl.HrmsRecruitmentDaoImpl;
import com.rainier.dao.Impl.HrmsUserAuthentication;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.LeaveRequestEntity;
import com.rainier.entities.Privileges;
import com.rainier.entities.RecruitmentApplyNowForJobEntity;
import com.rainier.entities.RecruitmentAssignJobEntity;
import com.rainier.entities.RecruitmentAssignReportEntity;
import com.rainier.entities.RecruitmentCandidateSignUpEntity;
import com.rainier.entities.RecruitmentInterviewScheduleEntity;
import com.rainier.entities.RecruitmentMailLogEntity;
import com.rainier.entities.RecruitmentOpeningsEntity;
import com.rainier.entities.RecruitmentPostProfileEntity;
import com.rainier.utility.HrmsGetDateAndTime;
import com.rainier.utility.SendMailToCandidateUtility;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;

public class RecruitmentPortal {
	public static Logger logger = Logger.getLogger(RecruitmentPortal.class);
	HrmsRecruitmentDao dao = new HrmsRecruitmentDaoImpl();
	CommonResponseBean response = new CommonResponseBean();
	private static final HrmsUserAuthenticate user = new HrmsUserAuthentication();

	public Response saveOpeningPositions(RecruitmentOpeningBean bean) {
		logger.info("Entered into saveOpeningPositions() of RecruitmentPortal class ");
		RecruitmentOpeningsEntity entity = new RecruitmentOpeningsEntity();
		try {

			BeanUtils.copyProperties(entity, bean);
			entity.setRequisitionId(bean.getRequisitionId());

			entity.setRequisitionCode(bean.getRequisitionCode());
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsactive(1);
			boolean post = dao.saveOpeningPosition(entity);
			if (post == true) {
				response.setMessage("Opening Position Added Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Failed to Save Opening Position.");
				response.setStatus(false);
			}
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error while Saving - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			logger.error("Failed to save");
			response.setMessage("failed to save -" + e2.getMessage());
			response.setStatus(false);

		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	public Response saveCandidateProfile(RecruitmentCandidateSignUpEntity entity) {
		logger.info("Entered into saveCandidateProfile() of RecruitmentPortal class ");
		try {

			BeanUtils.copyProperties(entity, entity);
			boolean post = dao.saveCandidateProfile(entity);
			if (post == true) {
				response.setMessage("Candidate Profile Added Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Failed to Save Candidate Profile.");
				response.setStatus(false);
			}
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error while Saving - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			logger.error("Failed to save");
			response.setMessage("failed to save -" + e2.getMessage());
			response.setStatus(false);

		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// fetch Open Job List Service.
	public Response fetchJobOpenings(int roleId, int menuId) {
		LovResponseBean response = new LovResponseBean();
		List<Privileges> listprivilleges = user.getPrivileges(roleId, menuId);
		List<RecruitmentOpeningsEntity> entityList = dao.fetchJobOpening();
		if (!entityList.isEmpty()) {
			response.setMessage("Job Opening Retrived Successfully.");
			response.setStatus(true);
			response.setList(entityList);
			response.setPrivillegeslist(listprivilleges);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Failed to Save Job Openings");
			response.setStatus(false);
			response.setPrivillegeslist(listprivilleges);
			return Response.ok().entity(response).build();
		}

	}

	/*
	 * // Apply Now Save logic .... .(Text + File ) public Response
	 * saveApplyForjob(RecruitmentApplyNowBean bean) {
	 * 
	 * logger.info("saveApplyForjob() method in business logic ");
	 * CommonResponseBean response = new CommonResponseBean();
	 * 
	 * if (dao.applyNowForJob(bean)) { response.setMessage("Successfully Applied.");
	 * response.setStatus(true); return Response.ok().entity(response).build(); }
	 * else {
	 * 
	 * 
	 * response.setMessage("Please Check the Inputs."); response.setStatus(false);
	 * return Response.serverError().entity(response).build(); } }
	 */

	// apply now .... with Resume .

	public Response saveApply(RecruitmentApplyNowBean bean) {

		logger.info("saveApply() method in business logic ");
		RecruitmentApplyNowForJobEntity entity = new RecruitmentApplyNowForJobEntity();
		try {

			BeanUtils.copyProperties(entity, bean);
			entity.setSubmitted((short) 1);

			boolean apply = dao.applyNow(entity);
			if (apply == true) {
				response.setMessage("Applied Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Failed to Save Opening Position.");
				response.setStatus(false);
			}
			return Response.status(Response.Status.OK).entity(response).build();

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error while Saving - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			logger.error("Failed to save");
			response.setMessage("failed to save -" + e2.getMessage());
			response.setStatus(false);

		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// for Resume Applying case ...
	public Response uploadResume(String filePath, int applyId) {
		SuccessResponseBean response = new SuccessResponseBean();
		if (dao.applyNowWithResume(filePath, applyId)) {
			response.setMessage("Resume Uploaded Successfully...");
			response.setStatus(true);
			response.setUploadURL(filePath);
		} else {
			response.setMessage("Failed to upload Resume.");
			response.setStatus(false);
			File delFile = new File(filePath);
			if (delFile.exists()) {
				delFile.delete();
			}
		}

		return Response.ok().entity(response).build();

	}

	// fetch for applied Job Details.....
	public Response getAppliedJobDetails() {
		List<RecruitmentApplyNowForJobEntity> entityList = dao.getAppliedJob();
		CommonResponseBean response = new CommonResponseBean();
		if (entityList != null) {
			response.setMessage("Applied Jobs Retrived Successfully...");
			response.setStatus(true);
			response.setList(entityList);

		} else {
			response.setMessage("Applied Jobs Fetching Conflicts .");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();
	}

	// send Mail for Application Status.

	public Response applicationMailStatus(RecruitmentApplyNowBean bean) {

		logger.info("entered into applicationMailStatus method of business class.. ");

		// bean.setName(bean.getName());

		SendMailToCandidateUtility mailTo = new SendMailToCandidateUtility();
		boolean flag = mailTo.sendToCandidate(bean.getEmail(), bean.getName());

		RecruitmentMailLogEntity mailLog = new RecruitmentMailLogEntity();

		mailLog.setToEmails(bean.getEmail());
		mailLog.setToName(bean.getName());
		mailLog.setEmailSubject("EEAccess Derive Application Status");
		mailLog.setHeader("Greeting From EEAccess HR Team");
		mailLog.setMessage("Dear Your Application has been Submitted Successfully.");
		mailLog.setIsSent(1);

		if (flag) {
			mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			dao.sendingMailByRecruiterTeam(mailLog);
			response.setMessage("Email Sent to Candidate Successfully.");
			response.setStatus(true);

		} else {
			mailLog.setIsSent(0);
			mailLog.setMessage("Message Not Sent.");
			mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			dao.sendingMailByRecruiterTeam(mailLog);
			response.setMessage("Email Not Sent to candidate");
			response.setStatus(true);
		}

		return Response.ok().entity(response).build();

	}

	// Apply Now Save logic .... .(Text + File )
	public Response saveApplyForjob(RecruitmentApplyNowBean bean) {

		logger.info("saveApplyForjob() method in business logic ");

		CommonResponseBean response = new CommonResponseBean();

		SendMailToCandidateUtility mailTo = new SendMailToCandidateUtility();
		boolean flag = mailTo.sendToCandidate(bean.getEmail(), bean.getName());

		RecruitmentMailLogEntity mailLog = new RecruitmentMailLogEntity();

		boolean result = dao.applyNowForJob(bean);
		if (result == true) {

			mailLog.setToEmails(bean.getEmail());
			mailLog.setToName(bean.getName());
			mailLog.setEmailSubject("EEAccess Derive Application Status");
			mailLog.setHeader("Greeting From EEAccess HR Team");
			mailLog.setMessage("Dear Your Application has been Submitted Successfully.");
			mailLog.setIsSent(1);

			if (flag) {
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

				dao.sendingMailByRecruiterTeam(mailLog);
				response.setMessage(
						"Application for Job  Submission Applied and Email Sent to Candidate Successfully.");
				response.setStatus(true);

			} else {
				mailLog.setIsSent(0);
				mailLog.setMessage("Message Not Sent.");
				mailLog.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
				dao.sendingMailByRecruiterTeam(mailLog);
				response.setMessage("Application for Job Applied But Email failed to sent candidate");
				response.setStatus(true);
			}

			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Application Submission failed.");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}

	}

	// private static final HrmsListOfValuesDao dao = new HrmsListOfValues();

	public Response requisitionIdAutoGeneration() {

		logger.info("entered into employeeIdAutoGeneration method of business class");

		RequisitionIdAutoGenerationResponseBean response = new RequisitionIdAutoGenerationResponseBean();

		String requisitionCode = dao.requisitionId();
		System.out.println(requisitionCode);
		String[] arr = requisitionCode.split("(?<=\\D)(?=\\d)");
		System.out.println(arr);
		// String Id = Integer.toString(Integer.parseInt(arr[1]) + 1);
		String Id = (arr[1] + 1);
		System.out.println(Id);

		response.setRequisitionId(Id);
		response.setPreFix(arr[0]);
		response.setStatus(true);

		return Response.ok().entity(response).build();
	}

	// post profile logic .....
	public Response savePostProfile(RecruitmentPostProfileEntity entity) {
		CommonResponseBean response = new CommonResponseBean();
		try {
			RecruitmentApplyNowBean bean = new RecruitmentApplyNowBean();
			BeanUtils.copyProperties(entity, entity);
			// entity.setCandidateApplyId(bean.getApplyId());
			entity.setId(entity.getId());
			boolean postprofile = dao.savePostProfile(entity);

			if (postprofile == true) {
				response.setMessage("post-profile Added Successfully....");
				response.setStatus(true);
			} else {
				response.setMessage("Failed to Saved");
				response.setStatus(false);
			}
			return Response.ok().entity(response).build();

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error while Saving - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			logger.error("Failed to save");
			response.setMessage("failed to save -" + e2.getMessage());
			response.setStatus(false);

		}
		return Response.ok().entity(response).build();

	}

	// post Profile Candidate Logic....
	public Response getPostProfileDetails(int id) {
		RecruitmentPostProfileEntity entityList = dao.fetchPostProfile(id);
		CommonResponseBean response = new CommonResponseBean();
		if (entityList != null) {
			response.setMessage("Post Profile Retrived Successfully...");
			response.setStatus(true);
			response.setList(entityList);

		} else {
			response.setMessage("Post profile Fetching Conflicts .");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();
	}

	// interview Schedule

	public Response saveInterviewSchedule(RecruitmentInterviewScheduleEntity entity) {
		CommonResponseBean response = new CommonResponseBean();
		try {
			// RecruitmentApplyNowBean bean = new RecruitmentApplyNowBean();
			// RecruitmentPostProfileEntity bean1 = new RecruitmentPostProfileEntity();
			BeanUtils.copyProperties(entity, entity);
			entity.setId(entity.getId());
			// entity.setInterviewtime(entity.getInterviewtime().toString());

			entity.setInterviewStatus("inProcess");
			boolean schedule = dao.interviewSchedule(entity);

			if (schedule == true) {
				response.setMessage("Interview Schedule Provided  Successfully....");
				response.setStatus(true);
			} else {
				response.setMessage("Failed to Saved");
				response.setStatus(false);
			}
			return Response.ok().entity(response).build();

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error while Saving - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			logger.error("Failed to save");
			response.setMessage("failed to save -" + e2.getMessage());
			response.setStatus(false);

		}

		return Response.ok().entity(response).build();

	}

	// For All post Profile List ...
	public Response getAllPostProfile() {
		CommonResponseBean response = new CommonResponseBean();
		List<RecruitmentPostProfileEntity> postList = dao.getPostProfileforAll();
		if (postList != null) {
			response.setMessage("All Post Profile list Retrived Successfully.");
			response.setStatus(true);
			response.setList(postList);
		} else {
			response.setMessage("Conflicts While Fetching..");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();
	}

	// For All post Interview Schedule List ...
	public Response getAllScheduleList() {
		CommonResponseBean response = new CommonResponseBean();
		List<RecruitmentInterviewScheduleEntity> scheduleList = dao.getInterviewScheduleList();
		if (scheduleList != null) {
			response.setMessage("All Interview Schedule list Retrived Successfully.");
			response.setStatus(true);
			response.setList(scheduleList);
		} else {
			response.setMessage("Conflicts While Fetching..");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();

	}

	// for update interview Status Service...
	public Response updateInterviewScheduleStatus(RecruitmentInterviewScheduleEntity entity) {
		CommonResponseBean response = new CommonResponseBean();
		try {

			BeanUtils.copyProperties(entity, entity);

			boolean updateSchedule = dao.updateInterviewSchedule(entity);

			if (updateSchedule == true) {
				response.setMessage("Interview Schedule Updated  Successfully....");
				response.setStatus(true);
			} else {
				response.setMessage("Failed to Update.");
				response.setStatus(false);
			}
			return Response.ok().entity(response).build();

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error while Update - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			logger.error("Failed to Update");
			response.setMessage("failed to Update -" + e2.getMessage());
			response.setStatus(false);

		}

		return Response.ok().entity(response).build();

	}

	// For All post Interview Schedule List ...Based on Status.
	public Response getAllScheduleListStatus(String interviewStatus) {

		int countAll = 0;
		int countSelected = 0;
		int countRejected = 0;
		int countInprocess = 0;
		int countOnHold = 0;
		int countComplete = 0;

		RecruitInterviewStatusRespBean response = new RecruitInterviewStatusRespBean();
		List<RecruitmentInterviewScheduleEntity> statusList = dao.listOfInterviewStatus(interviewStatus);
		List<RecruitmentInterviewScheduleEntity> statusCount = dao.getInterviewScheduleList();
		if (statusList != null) {
			response.setMessage("All Interview Schedule list Retrived Successfully, Based on Particular Status");
			response.setStatus(true);

			for (RecruitmentInterviewScheduleEntity status : statusCount) {
				countAll++;
				if ("inProcess".equalsIgnoreCase(status.getInterviewStatus()))
					countInprocess++;
				else if ("Selected".equalsIgnoreCase(status.getInterviewStatus()))
					countSelected++;
				else if ("OnHold".equalsIgnoreCase(status.getInterviewStatus()))
					countOnHold++;
				else if ("Rejected".equalsIgnoreCase(status.getInterviewStatus()))
					countRejected++;
				else if ("Complete".equalsIgnoreCase(status.getInterviewStatus()))
					countComplete++;
				else
					logger.info("Nothing to count");

			}

			response.setCountAll(countAll);
			response.setCountComplete(countComplete);
			response.setCountInprocess(countInprocess);
			response.setCountOnHold(countOnHold);
			response.setCountRejected(countRejected);
			response.setCountSelected(countSelected);

			response.setListOfInterviewStatus(statusList);
		} else {
			response.setMessage("Conflicts While Fetching..");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();

	}

	// count Interview Status....................
	public Response getInterviewStatusCount(int candidateId) {

		int countAll = 0;
		int countSelected = 0;
		int countRejected = 0;
		int countInprocess = 0;
		int countOnHold = 0;
		int countComplete = 0;

		List<RecruitmentInterviewScheduleEntity> listOfInterviewStatus = dao.countInterviewStatus(candidateId);
		RecruitInterviewStatusRespBean respBean = new RecruitInterviewStatusRespBean();

		if (!listOfInterviewStatus.isEmpty()) {
			respBean.setMessage("Success");
			respBean.setStatus(true);

			if (candidateId != 0) {
				for (RecruitmentInterviewScheduleEntity interviewStatus : listOfInterviewStatus) {
					countAll++;
					if (interviewStatus.getInterviewStatus() == "inProcess")
						countInprocess++;
					else if (interviewStatus.getInterviewStatus() == "complete")
						countComplete++;
					else if (interviewStatus.getInterviewStatus() == "OnHold")
						countOnHold++;
					else if (interviewStatus.getInterviewStatus() == "selected")
						countSelected++;
					else if (interviewStatus.getInterviewStatus() == "rejected")
						countRejected++;
					else
						logger.info("Nothing to count .....");

				}
				respBean.setCountAll(countAll);
				respBean.setCountComplete(countComplete);
				respBean.setCountInprocess(countInprocess);
				respBean.setCountOnHold(countOnHold);
				respBean.setCountRejected(countRejected);
				respBean.setCountSelected(countSelected);
			}
			respBean.setListOfInterviewStatus(listOfInterviewStatus);

		} else {
			respBean.setMessage("No Data.....");
			respBean.setStatus(false);
			respBean.setListOfInterviewStatus(listOfInterviewStatus);
		}
		return Response.ok().entity(respBean).build();
	}

	// Save Post profile List ......New One
	public Response saveApplyForjobList(RecruitmentPostProfileBean bean) {

		logger.info("saveApplyForjob() method in business logic ");
		CommonResponseBean response = new CommonResponseBean();

		if (dao.savePostProfileList(bean)) {
			response.setMessage("Successfully Post Profile List Applied.");
			response.setStatus(true);
			return Response.ok().entity(response).build();
		} else {

			response.setMessage("Please Check the Inputs.");
			response.setStatus(false);

			return Response.serverError().entity(response).build();
		}
	}

	// Check Duplicate Email Existancy .....for Post Profile
	public Response checkDuplicateEmailPostProfile(String candidateEmail) {
		RecruitmentCheckEmailStatusResponse response = new RecruitmentCheckEmailStatusResponse();
		List<RecruitmentPostProfileEntity> checkEmailStatusList = dao.checkDuplicatecandidateEmail(candidateEmail);
		if (checkEmailStatusList.isEmpty()) {
			response.setMessage("Candidate Email Not Registered With Post Profile,Please Proceed...!!! ");
			response.setStatus(true);

		} else {

			response.setMessage("Email Aready Exists, With Following Details...");
			// response.setCandidateName();
			response.setStatus(true);
			response.setList(checkEmailStatusList);
		}
		return Response.ok().entity(response).build();
	}
	// candidateId List Who ever Apply from Career......

	public Response getAllcandidateIdDetails() {
		logger.info("Entered into getAllcandidateIdDetails()");
		CommonResponseBean response = new CommonResponseBean();

		ArrayList<RecruitmentApplyNowBean> beanAl = new ArrayList<RecruitmentApplyNowBean>();
		List<Object[]> appliedCandidateListdata = dao.getAllcandidateId();
		if (appliedCandidateListdata.size() != 0) {
			for (Object[] row : appliedCandidateListdata) {
				RecruitmentApplyNowBean bean = new RecruitmentApplyNowBean();
				bean.setApplyId(objToInteger(row[0]));
				bean.setName(objToString(row[1]));

				beanAl.add(bean);
			}

			response.setMessage("All Candidate Details Are Availavele Here ....");
			response.setStatus(true);
			response.setList(beanAl);
		} else {
			response.setMessage("Error while retrieving Candidate Information");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// Assign Job Functionality Services ...

	public Response assignToJobDirectly(RecruitmentAssignJobBean bean) {
		logger.info("Entered into assignToJobDirectly()");
		CommonResponseBean response = new CommonResponseBean();

		ArrayList<RecruitmentAssignJobBean> beanAl = new ArrayList<RecruitmentAssignJobBean>();

		boolean result = false;

		/*
		 * for (RecruitmentAssignCandidateApplyId assignJob :
		 * bean.getAssignCandidateApplyId()) {
		 * 
		 * RecruitmentAssignJobEntity entity = new RecruitmentAssignJobEntity();
		 * 
		 * entity.setExperience(bean.getExperience()); entity.setIsassigned(1);
		 * entity.setJobPostingId(bean.getJobPostingId());
		 * entity.setPostProfileId(bean.getPostProfileId());
		 * entity.setSkill(bean.getSkill());
		 * entity.setCandidateApplyId(assignJob.getCandidateAssignJobId()); result =
		 * dao.assignMultipleCandidate(entity);
		 * 
		 * } if (result == true) {
		 * response.setMessage("Assign to Job Completed Successfully.");
		 * response.setStatus(true); } else {
		 * response.setMessage("Failed To Save Assigning a Job");
		 * response.setStatus(false); }
		 */

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// Assign to job Directly Showing in Candidate ....Whoever Assigned.

	public Response fetchAssignedJobCandidateList() {
		CommonResponseBean response = new CommonResponseBean();
		List<RecruitmentAssignJobEntity> assignedJobList = dao.fetchAssignedJobCandidateList();
		if (assignedJobList != null) {
			response.setMessage("Assigned Candidate List Retrieved Successfully.");
			response.setStatus(true);
			response.setList(assignedJobList);
		} else {
			response.setMessage("Failed To Get Assigned Job List.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	/*
	 * Converts Object DataType to String Input - Object returns String If Null
	 * returns empty string
	 */
	private String objToString(Object obj) {

		if (obj != null)
			return obj.toString();

		return "";
	}

	/*
	 * Converts Object DataType to Integer Input - Object returns Integer If Null
	 * returns 0
	 */
	private Integer objToInteger(Object obj) {

		if (obj != null)
			return Integer.parseInt(obj.toString());

		return 0;
	}

	// candidate Id created By Vendor... for Post profile.
	public Response saveApplyForjobListByVendor(RecruitmentPostProfileBean bean) {

		logger.info("saveApplyForjob() method in business logic ");
		CommonResponseBean response = new CommonResponseBean();

		boolean result = dao.savePostProfileListByVendor(bean);
		if (result == true && bean.getId() > 0) {
			response.setMessage("Successfully Post Profile List Updated.");
			response.setStatus(true);
			return Response.ok().entity(response).build();
		} else {

			response.setMessage("post Profile Saved Successfully.");
			response.setStatus(true);
			return Response.ok().entity(response).build();

			// return Response.serverError().entity(response).build();
		}
	}

	// Duplicacy Check For Post profile ............final

	public Response getPostProfileDuplicacy(String candidateEmailid, String identificationId) {
		logger.info("Entered into getPostProfileDuplicacy()");
		CommonResponseBean response = new CommonResponseBean();

		RecruitmentPostProfileEntity duplicatePost = dao.checkDuplicatePostProfile(candidateEmailid, identificationId);
		if (duplicatePost == null) {
			response.setMessage("Go With Profile Creation...");
			response.setStatus(false);
		} else {
			if (duplicatePost.getCandidateEmailid().equalsIgnoreCase(candidateEmailid)
					|| duplicatePost.getIdentificationId().equalsIgnoreCase(identificationId)) {

				response.setList(duplicatePost);
				response.setMessage("Duplicate Profile Found.");
				response.setStatus(true);

			} else {

				response.setMessage("Go With Profile Creation...");
				response.setStatus(false);
			}
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// Assign to job Save Service ...
	public Response assignToJobDirectlyCandidateList(RecruitmentAssignJobBean bean) {
		logger.info("Entered into assignToJobDirectlyCandidateList()");
		CommonResponseBean response = new CommonResponseBean();

		ArrayList<RecruitmentAssignJobBean> beanAl = new ArrayList<>();

		RecruitmentAssignJobEntity entity = new RecruitmentAssignJobEntity();

		try {
			BeanUtils.copyProperties(entity, bean);
			boolean result = dao.assignMultipleCandidateList(bean);
			if (result == true) {
				response.setMessage("Assign to Job Updated Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Something went Wrong .");
				response.setStatus(false);
			}
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error while Update - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			logger.error("Failed to Update");
			response.setMessage("failed to Update -" + e2.getMessage());
			response.setStatus(false);

		}

		return Response.ok().entity(response).build();

	}

	// fetch Assign to job ...
	public Response fetchAssignedJobCandidate(int jobPostingId) {
		CommonResponseBean response = new CommonResponseBean();
		RecruitmentAssignJobEntity assignedJobList = dao.fetchAssignedJobList(jobPostingId);
		List<Integer> iter = new ArrayList<>();
		for (String str : assignedJobList.getCandidateApplyId().split(",")) {
			iter.add(Integer.parseInt(str));
		}
		iter.toString();
		RecruitmentAssignJobEntity entity = new RecruitmentAssignJobEntity();
		entity.setCandidateApplyId(iter.toString());
		entity.setExperience(assignedJobList.getExperience());
		entity.setId(assignedJobList.getId());
		// entity.setIsassigned(assignedJobList.getIsassigned());
		entity.setJobPostingId(assignedJobList.getJobPostingId());
		entity.setSkill(assignedJobList.getSkill());

		if (entity != null) {
			response.setMessage("Assigned Candidate List Retrieved Successfully.");
			response.setStatus(true);
			response.setList(entity);
		} else {
			response.setMessage("Failed To Get Assigned Job List.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// status Report for Each candidate ....
	public Response getStatusReportForEachcandidate() {
		// CommonResponseBean response = new CommonResponseBean();
		RecruitmentApplicantStatusResponseBean response = new RecruitmentApplicantStatusResponseBean();

		int countAll = 0;
		List<RecruitmentAssignJobEntity> statusReportList = dao.statusReportForEachCandidate();
		for (RecruitmentAssignJobEntity assignCount : statusReportList) {
			countAll++;
		}

		if (statusReportList != null) {
			response.setMessage("Assigned Candidate List Retrieved Successfully.");
			response.setStatus(true);
			response.setCountAll(countAll);
			response.setList(statusReportList);
		} else {
			response.setMessage("Failed To Get Assigned Job List.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// Assigned Status for candidateId :- Update.
	public Response updateAssignedReport(RecruitmentAssignStatusBean bean) {
		// CommonResponseBean response = new CommonResponseBean();
		RecruitmentAssignReportResponseBean response = new RecruitmentAssignReportResponseBean();
		RecruitmentAssignReportEntity entity = new RecruitmentAssignReportEntity();

		String res = dao.assignedStatusReport(bean);
		if (res.equalsIgnoreCase("Updated")) {
			response.setMessage("Assign to Job Status Updated Successfully.");
			response.setStatus(true);
			// response.setId(bean.getId());

		} else if (res.equalsIgnoreCase("Saved")) {
			response.setMessage(" Assign To Job Status Saved Successfully");
			response.setStatus(true);
			response.setId(bean.getId());
		}

		return Response.ok().entity(response).build();

	}

	// Assign Report Updated Based on the Click Service.
	public Response getUpdatedStatusReport(String assignReportStatus) {
		RecruitmentApplicantStatusResponseBean response = new RecruitmentApplicantStatusResponseBean();

		int countAll = 0, joined = 0, rejected = 0, offered = 0, inProgress = 0;
		List<RecruitmentAssignReportEntity> updatedStatusList = dao.getSpecificStatusReport(assignReportStatus);
		List<RecruitmentAssignReportEntity> statusCount = dao.getSpecificStatusReportSelected();
		if (!updatedStatusList.isEmpty()) {
			response.setMessage("Updated Status Got");
			response.setStatus(true);

			for (RecruitmentAssignReportEntity report : statusCount) {
				countAll++;
				if ("Joined".equalsIgnoreCase(report.getAssignReportStatus()))
					joined++;
				else if ("offered".equalsIgnoreCase(report.getAssignReportStatus()))
					offered++;
				else if ("rejected".equalsIgnoreCase(report.getAssignReportStatus()))
					rejected++;
				else if ("inProgress".equalsIgnoreCase(report.getAssignReportStatus()))
					inProgress++;
				else
					logger.info("Nothing to count.");

			}
			response.setCountAll(countAll);
			response.setInProgress(inProgress);
			response.setJoined(joined);
			response.setOffered(offered);
			response.setRejected(rejected);
		}

		response.setList(updatedStatusList);

		return Response.ok().entity(response).build();

	}

	// 1.) Selected Status

	public Response getUpdatedSelectedStatusReport() {
		RecruitmentApplicantStatusResponseBean response = new RecruitmentApplicantStatusResponseBean();
		// int countAll = 0;
		List<RecruitmentAssignReportEntity> updatedSelectedStatusList = dao.getSpecificStatusReportSelected();
		/*
		 * for(RecruitmentAssignReportEntity selected:updatedSelectedStatusList) {
		 * countAll++; }
		 */
		if (!updatedSelectedStatusList.isEmpty()) {
			response.setMessage("Updated Status Based on Selected Click.");
			response.setStatus(true);
			// response.setCountAll(countAll);
			response.setList(updatedSelectedStatusList);
		} else {
			response.setMessage("Failed To Get Selected  Status.");
			response.setStatus(false);
		}

		return Response.ok().entity(response).build();

	}

	// Recruiters Based Status.

	public Response getRecruitersBasedStatus(String recruiters, String assignReportStatus) {
		logger.info("Entered into getRecruitersBasedStatus()");
		CommonResponseBean response = new CommonResponseBean();
		RecruitmentOpeningsEntity check = new RecruitmentOpeningsEntity();
		RecruitmentAssignReportEntity check1 = new RecruitmentAssignReportEntity();
		ArrayList<RecruitersSummaryReportBean> beanAl = new ArrayList<RecruitersSummaryReportBean>();
		List<Object[]> recruitersCandidateListdata = dao.getRecruitersBasedCandidateStatus(recruiters,
				assignReportStatus);
		if (recruitersCandidateListdata.size() != 0) {
			for (Object[] row : recruitersCandidateListdata) {
				RecruitersSummaryReportBean bean = new RecruitersSummaryReportBean();
				bean.setRecruiters(objToString(row[0]));
				bean.setRecruitersMailId(objToString(row[1]));
				bean.setAssignReportStatus(objToString(row[2]));
				bean.setCandidateApplyId(objToString(row[3]));
				bean.setJobPostId(objToString(row[4]));
				bean.setJobTitlename(objToString(row[5]));
				bean.setCandidateName(objToString(row[6]));
				beanAl.add(bean);
			}

			response.setMessage("Recruiters Status based Candidate  Details Are Availavele Here ....");
			response.setStatus(true);
			response.setList(beanAl);
		} else {
			response.setMessage("Error while retrieving Candidate Information");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	/*
	 * private Object row(int i) { // TODO Auto-generated method stub return null; }
	 */

}
