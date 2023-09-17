package com.rainier.dao;

import java.util.List;

import com.rainier.beans.RecruitmentApplyNowBean;
import com.rainier.beans.RecruitmentAssignJobBean;
import com.rainier.beans.RecruitmentAssignStatusBean;
import com.rainier.beans.RecruitmentPostProfileBean;
import com.rainier.entities.RecruitmentApplyNowForJobEntity;
import com.rainier.entities.RecruitmentAssignJobEntity;
import com.rainier.entities.RecruitmentAssignReportEntity;
import com.rainier.entities.RecruitmentCandidateSignUpEntity;
import com.rainier.entities.RecruitmentInterviewScheduleEntity;
import com.rainier.entities.RecruitmentMailLogEntity;
import com.rainier.entities.RecruitmentOpeningsEntity;
import com.rainier.entities.RecruitmentPostProfileEntity;

public interface HrmsRecruitmentDao {

	// for OPening position Save ....
	public boolean saveOpeningPosition(RecruitmentOpeningsEntity entity);

	// fetch for opening position.
	public List<RecruitmentOpeningsEntity> fetchJobOpening();

	// for Candidate Sign up (External User)
	public boolean saveCandidateProfile(RecruitmentCandidateSignUpEntity entity);

	// for recruitment mail Log Dao.
	public void sendingMailByRecruiterTeam(RecruitmentMailLogEntity entity);

	// for Apply Now Save Service .....for candidate in one all.(Text + file )
	public boolean applyNowForJob(RecruitmentApplyNowBean bean);

	// for Apply Now Save Service .....for candidate
	public boolean applyNow(RecruitmentApplyNowForJobEntity entity);

	// for resume Uploading ...
	public boolean applyNowWithResume(String filePath, int applyId);

	// fetch applied Now Service ...
	public List<RecruitmentApplyNowForJobEntity> getAppliedJob();

	// Auto generator for requisition id
	public String requisitionId();

	// post profile save
	public boolean savePostProfile(RecruitmentPostProfileEntity entity);

	// post profile Fetch Dao
	public RecruitmentPostProfileEntity fetchPostProfile(int id);

	// post profile fetch for all ...
	public List<RecruitmentPostProfileEntity> getPostProfileforAll();

	// Interview Schedule Save Service ...
	public boolean interviewSchedule(RecruitmentInterviewScheduleEntity entity);

	// fetch Interview Details ....
	public List<RecruitmentInterviewScheduleEntity> getInterviewScheduleList();

	// Update Interview Schedule Service .....
	public boolean updateInterviewSchedule(RecruitmentInterviewScheduleEntity entity);

// check after update Based on new Status?----
	public List<RecruitmentInterviewScheduleEntity> listOfInterviewStatus(String interviewStatus);

	// count...
	public List<RecruitmentInterviewScheduleEntity> countInterviewStatus(int candidateId);

	// post profile save
	public boolean savePostProfileList(RecruitmentPostProfileBean bean);

	// check duplicate If it exists...
	public List<RecruitmentPostProfileEntity> checkDuplicatecandidateEmail(String candidateEmail);

	// fetching candidateId For Using Post profile ...........

	public List<Object[]> getAllcandidateId();

	// Assign to job Dao class .
	public boolean assignMultipleCandidate(RecruitmentAssignJobEntity entity);

	 public boolean assignMultipleCandidateList(RecruitmentAssignJobBean bean);

	// Assign to job Directly Showing in Candidate ....Whoever Assigned.
	public List<RecruitmentAssignJobEntity> fetchAssignedJobCandidateList();

	// post profile save for candidateId Created By Vendor.
	public boolean savePostProfileListByVendor(RecruitmentPostProfileBean bean);

	// check duplicate If it exists... final one.
	public RecruitmentPostProfileEntity checkDuplicatePostProfile(String candidateEmailid,String identificationId);

	// fetch assign job .
	
	public RecruitmentAssignJobEntity fetchAssignedJobList(int jobPstingId);
	
	// status Report for all candidateId.
	public List<RecruitmentAssignJobEntity> statusReportForEachCandidate();
	
	// assign Report Status Update Service.
	public String assignedStatusReport(RecruitmentAssignStatusBean bean);
	
	// Assign Report Updated Based on the Click Service.
	public List<RecruitmentAssignReportEntity> getSpecificStatusReport(String assignReportStatus);
	
	// 1.) Selected Status  need to use in count fuctionality.
	public List<RecruitmentAssignReportEntity> getSpecificStatusReportSelected();
	
	// Recruiters Based Status.
    public List<Object[]> getRecruitersBasedCandidateStatus(String recruiters,String assignReportStatus);
}
