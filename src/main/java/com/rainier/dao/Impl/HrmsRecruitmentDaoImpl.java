package com.rainier.dao.Impl;

import static com.rainier.dbconfiguration.DbConnect.DbCon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.constraints.Max;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.id.Assigned;
import org.hibernate.query.Query;

import com.rainier.beans.CommonResponseBean;
import com.rainier.beans.RecruitmentApplyNowBean;
import com.rainier.beans.RecruitmentAssignJobBean;
import com.rainier.beans.RecruitmentAssignStatusBean;
import com.rainier.beans.RecruitmentPostProfileBean;
import com.rainier.beans.RecruitmentPostProfileDuplicacyBean;
import com.rainier.dao.HrmsRecruitmentDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.EmployeeDocumentsEntity;
import com.rainier.entities.RecruitmentApplyNowForJobEntity;
import com.rainier.entities.RecruitmentAssignJobEntity;
import com.rainier.entities.RecruitmentAssignReportEntity;
import com.rainier.entities.RecruitmentCandidateSignUpEntity;
import com.rainier.entities.RecruitmentInterviewScheduleEntity;
import com.rainier.entities.RecruitmentMailLogEntity;
import com.rainier.entities.RecruitmentOpeningsEntity;
import com.rainier.entities.RecruitmentPostProfileEntity;
import com.rainier.entities.VisaDocumentsEntity;
import com.rainier.utility.HrmsGetDateAndTime;

public class HrmsRecruitmentDaoImpl implements HrmsRecruitmentDao {

	private static final int Object = 0;
	public static Logger logger = Logger.getLogger(HrmsRecruitmentDao.class);
	private String queryString = null;

	public boolean saveOpeningPosition(RecruitmentOpeningsEntity entity) {
		logger.info("Entering into saveOpeningPosition() of HrmsRecruitmentDao class");

		Transaction tx = DbConnect.DbCon().beginTransaction();
		try {
			DbConnect.DbCon().save(entity);
			// DbConnect.DbCon().getTransaction();
			tx.commit();
			logger.info("Opening Position Added Successfully.");
			System.out.println(entity);
			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Failed to Execute Hql Query....");
			return false;

		} finally {
			DbConnect.DbCon().clear();
		}
	}

	public boolean saveCandidateProfile(RecruitmentCandidateSignUpEntity entity) {
		logger.info("Entering into saveCandidateProfile() of HrmsRecruitmentDao class");

		Transaction tx = DbConnect.DbCon().beginTransaction();
		try {
			// tx.begin();
			DbConnect.DbCon().save(entity);
			tx.commit();
			logger.info("Candidate profile  Added Successfully.");

			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Failed to Execute Hql Query....");
			return false;

		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// for fetching Job Opening List.

	@Override
	public List<RecruitmentOpeningsEntity> fetchJobOpening() {

		try {

			String hql = "from RecruitmentOpeningsEntity where isactive=1";
			DbConnect.DbCon().beginTransaction();
			Query<RecruitmentOpeningsEntity> query = DbConnect.DbCon().createQuery(hql,
					RecruitmentOpeningsEntity.class);
			List<RecruitmentOpeningsEntity> joblist = query.list();
			DbConnect.DbCon().getTransaction().commit();

			logger.info("Fetch Job Opening  Retrived  Successfully.");
			return joblist;

		} catch (HibernateException e) {
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Failed to Execute Hql Query....");
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// for Recruitment log Entity Saving Part.....
	@Override
	public void sendingMailByRecruiterTeam(RecruitmentMailLogEntity entity) {

		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().save(entity);

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Email Log Insertion Successful.");
		} catch (HibernateException e) {
			logger.error("Conflicts in saving MailLog." + e);
			DbConnect.DbCon().getTransaction().rollback();
		} finally {
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}

	}

	// Apply Now .....(Text + File )

	@Override
	public boolean applyNowForJob(RecruitmentApplyNowBean bean) {
		DbConnect.DbCon().beginTransaction();

		try {
			RecruitmentApplyNowForJobEntity entity = new RecruitmentApplyNowForJobEntity();

			entity.setApplyId(bean.getApplyId());
			entity.setCoverletter(bean.getCoverletter());
			entity.setEmail(bean.getEmail());
			entity.setName(bean.getName());
			entity.setSubjectSkills(bean.getSubjectSkills());
			entity.setSubmitted((short) 1);
			entity.setUploadResume(bean.getUploadResume());
			entity.setJobTitle(bean.getJobTitle());
			entity.setLocationFor(bean.getLocationFor());
			entity.setContactNo(bean.getContactNo());
			entity.setExperience(bean.getExperience());
			entity.setJobAppliedDate(new HrmsGetDateAndTime().getDateOnly());

			// RecruitmentOpeningsEntity jobId= new RecruitmentOpeningsEntity();
			// entity.setJobPostingId(bean.getApplyId());
			// entity.setJobPostingId(jobId.getRequisitionId());
			entity.setJobPostingId(bean.getJobPostingId());

			DbConnect.DbCon().saveOrUpdate(entity);

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Applied Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("Conflicts in Applying...." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// Apply Now .....

	@Override
	public boolean applyNow(RecruitmentApplyNowForJobEntity entity) {
		try {

			DbConnect.DbCon().beginTransaction();

			DbConnect.DbCon().saveOrUpdate(entity);

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Applied Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("Conflicts in Applying...." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// apply with resume...
	@Override
	public boolean applyNowWithResume(String filePath, int applyId) {
		Transaction tx = DbConnect.DbCon().beginTransaction();
		try {
			// tx.begin();
			RecruitmentApplyNowForJobEntity resumeEntity = DbConnect.DbCon().get(RecruitmentApplyNowForJobEntity.class,
					applyId);
			resumeEntity.setUploadResume(filePath);
			DbConnect.DbCon().update(resumeEntity);
			tx.commit();
			logger.info("Resume Uploaded Successfully");
			return true;
		} catch (HibernateException e) {
			logger.error("ExpLetter path updating Failed.");
			tx.rollback();
			return false;
		} finally {
			DbCon().clear();
		}

	}
// fetch Apply now Service ....

	@Override
	public List<RecruitmentApplyNowForJobEntity> getAppliedJob() {

		try {
			Transaction tx = DbConnect.DbCon().beginTransaction();

			String hql = "from RecruitmentApplyNowForJobEntity";
			Query query = DbConnect.DbCon().createQuery(hql, RecruitmentApplyNowForJobEntity.class);
			List<RecruitmentApplyNowForJobEntity> appliedjobList = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Applied Job Details Fetched Successfully..");
			return appliedjobList;
		} catch (HibernateException e) {
			logger.error("failed to0 execute hql Query..." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Requisition Auto increment .

	@Override
	public String requisitionId() {
		try {
			String hqlMaxId = "select max(id) from RecruitmentOpeningsEntity";
			String hqlEmpId = "select u.requisitionCode from RecruitmentOpeningsEntity u where id=:uId";
			DbConnect.DbCon().beginTransaction();
			Integer id = (Integer) DbConnect.DbCon().createQuery(hqlMaxId).uniqueResult();
			String requisitionCode = (String) DbConnect.DbCon().createQuery(hqlEmpId).setParameter("uId", id)
					.uniqueResult();
			DbConnect.DbCon().getTransaction().commit();
			return requisitionCode;
		} catch (Exception e) {
			logger.info("catch block of EmployeeId method of Dao implementation class::" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("Failed");
			return null;
		} finally {
			DbConnect.DbCon().clear();

		}
	}

	// post profile Save Impl.....

	@Override
	public boolean savePostProfile(RecruitmentPostProfileEntity entity) {
		logger.info("Entering into savePostProfile() of HrmsRecruitmentDao class");

		Transaction tx = DbConnect.DbCon().beginTransaction();
		try {
			// tx.begin();
			DbConnect.DbCon().save(entity);
			tx.commit();
			logger.info("Post profile  Added Successfully.");

			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Failed to Execute Hql Query....");
			return false;

		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Fetch Post profile Impl...

	@Override
	public RecruitmentPostProfileEntity fetchPostProfile(int id) {
		// TODO Auto-generated method stub

		try {

			String hql = "from RecruitmentPostProfileEntity where id=:id";
			DbConnect.DbCon().beginTransaction();
			RecruitmentPostProfileEntity postprofileList = DbConnect.DbCon()
					.createQuery(hql, RecruitmentPostProfileEntity.class).setParameter("id", id).uniqueResult();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Sueessfully retrieved.");
			return postprofileList;

		} catch (HibernateException e) {
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Failed to Execute Hql Query....");
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}
	// Interview Schedule save Impl...

	@Override
	public boolean interviewSchedule(RecruitmentInterviewScheduleEntity entity) {

		logger.info("Entering into interviewSchedule() of HrmsRecruitmentDao class");

		Transaction tx = DbConnect.DbCon().beginTransaction();
		try {
			// tx.begin();
			DbConnect.DbCon().save(entity);
			tx.commit();
			logger.info("interview Schedule  Added Successfully.");

			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Failed to Execute Hql Query....");
			return false;

		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// for All Post Profile....

	@Override
	public List<RecruitmentPostProfileEntity> getPostProfileforAll() {

		try {

			String hql = "from RecruitmentPostProfileEntity";
			Transaction tx = DbConnect.DbCon().beginTransaction();
			Query<RecruitmentPostProfileEntity> query = DbConnect.DbCon().createQuery(hql,
					RecruitmentPostProfileEntity.class);
			List<RecruitmentPostProfileEntity> postListForAll = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("For All Post Profile List retrieved Successfully ....");
			return postListForAll;
		} catch (HibernateException e) {
			logger.error("Failed To Execute HQL Query: " + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Get Interview Schedule Impl .....

	@Override
	public List<RecruitmentInterviewScheduleEntity> getInterviewScheduleList() {
		try {
			Transaction tx = DbConnect.DbCon().beginTransaction();
			String hql = "from RecruitmentInterviewScheduleEntity";
			Query<RecruitmentInterviewScheduleEntity> query = DbConnect.DbCon().createQuery(hql,
					RecruitmentInterviewScheduleEntity.class);
			List<RecruitmentInterviewScheduleEntity> scheduleList = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Retrived Successfully.");
			return scheduleList;
		} catch (HibernateException e) {
			logger.error("Failed to Execute HQL Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}
// update iNTERVIEW Schedule Impl...

	@Override
	public boolean updateInterviewSchedule(RecruitmentInterviewScheduleEntity entity) {
		Transaction tx = DbConnect.DbCon().beginTransaction();
		try {
			// tx.begin();
			DbConnect.DbCon().update(entity);
			tx.commit();
			logger.info("interview Schedule  Added Successfully.");

			return true;

		} catch (HibernateException e) {
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Failed to Execute Hql Query....");
			return false;

		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Based on the interview status ...Checking
	@Override
	public List<RecruitmentInterviewScheduleEntity> listOfInterviewStatus(String interviewStatus) {
		try {

			String hql = "from RecruitmentInterviewScheduleEntity where interviewStatus=:interviewStatus";
			Transaction tx = DbConnect.DbCon().beginTransaction();

			List<RecruitmentInterviewScheduleEntity> statusList = DbConnect.DbCon()
					.createQuery(hql, RecruitmentInterviewScheduleEntity.class)
					.setParameter("interviewStatus", interviewStatus).getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Retrived Successfully.");
			return (List<RecruitmentInterviewScheduleEntity>) statusList;
		} catch (HibernateException e) {
			logger.error("Failed to Execute HQL Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// count ....

	@Override
	public List<RecruitmentInterviewScheduleEntity> countInterviewStatus(int candidateId) {
		Transaction tx = DbConnect.DbCon().beginTransaction();
		String queryString = null;

		if (candidateId == 0) {
			queryString = "from RecruitmentInterviewScheduleEntity";
		} else {
			queryString = "from RecruitmentInterviewScheduleEntity where candidateId=:candidateId";
		}

		try {
			// Transaction tx= DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().getTransaction();
			Query<RecruitmentInterviewScheduleEntity> query = DbConnect.DbCon().createQuery(queryString,
					RecruitmentInterviewScheduleEntity.class);
			if (candidateId != 0) {
				query.setParameter("candidateId", candidateId);
			} else {
				logger.info("parameter not set");
				System.out.println("parameter not set.....");
			}
			query.setParameter("candidateId", candidateId);
			List<RecruitmentInterviewScheduleEntity> list = query.getResultList();
			System.out.println("list of interview Status counted.");
			DbConnect.DbCon().getTransaction().commit();
			return list;

		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}
/// save Post profile List Logic ...

	@Override
	public boolean savePostProfileList(RecruitmentPostProfileBean bean) {
		DbConnect.DbCon().beginTransaction();
		// int candidateApplyId = 0;

		try {

			RecruitmentPostProfileEntity entity = new RecruitmentPostProfileEntity();
			RecruitmentApplyNowForJobEntity candidateEntity = new RecruitmentApplyNowForJobEntity();

			entity.setId(bean.getId());

			entity.setAdditionalSkills(bean.getAdditionalSkills());
			entity.setCandidateEmailid(bean.getCandidateEmailid());
			entity.setCandidateName(bean.getCandidateName());
			entity.setCityId(bean.getCityId());
			entity.setCountryId(bean.getCountryId());
			entity.setCurrentCTC(bean.getCurrentCTC());
			entity.setCurrentOrganization(bean.getCurrentOrganization());
			entity.setDomainSkills(bean.getDomainSkills());

			entity.setExpectedSallary(bean.getExpectedSallary());
			entity.setHighestEducation(bean.getHighestEducation());
			entity.setHomePhoneNo(bean.getHomePhoneNo());
			entity.setJobPostingId(DbConnect.DbCon().get(RecruitmentOpeningsEntity.class, bean.getJobPostingId()));
			// entity.setJobPostingId(bean.getJobPostingId());
			entity.setMobileNo(bean.getMobileNo());
			entity.setMultipleLocation(bean.getMultipleLocation());
			entity.setNoticePeriod(bean.getNoticePeriod());
			entity.setRelaventExpMonth(bean.getRelaventExpMonth());
			entity.setRelaventExpYear(bean.getRelaventExpYear());
			entity.setStateId(bean.getStateId());
			entity.setTitle(bean.getTitle());
			entity.setTotalExperienceMonth(bean.getTotalExperienceMonth());
			entity.setTotalExperienceYear(bean.getTotalExperienceYear());
			entity.setVisaCountryId(bean.getVisaCountryId());
			entity.setVisaExpiryDate(bean.getVisaExpiryDate());
			entity.setVisatype(bean.getVisatype());
			entity.setYearOfPassout(bean.getYearOfPassout());
			entity.setUploadProfile(bean.getUploadProfile());
			// entity.setIsactive(0);

			entity.setExCompanyGroup(bean.getExCompanyGroup());

			if (bean.getExCompanyGroup() == 1) {
				entity.setExCompanyName(bean.getExCompanyName());
			} else {
				entity.setExCompanyName("Not Applied yet.");
			}

			DbConnect.DbCon().save(entity);

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Post Profile Applied Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("Conflicts in Post Profile...." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}
// Check Duplicate candidate  Email  Exists or not ....?

	@Override
	public List<RecruitmentPostProfileEntity> checkDuplicatecandidateEmail(String candidateEmail) {
		try {

			String hql = "from RecruitmentPostProfileEntity where candidateEmail=:candidateEmail";
			Transaction tx = DbConnect.DbCon().beginTransaction();

			List<RecruitmentPostProfileEntity> duplicateEmail = DbConnect.DbCon()
					.createQuery(hql, RecruitmentPostProfileEntity.class).setParameter("candidateEmail", candidateEmail)
					.list();

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Retrived Successfully.");
			return duplicateEmail;

		} catch (HibernateException e) {
			logger.error("Failed to Execute HQL Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}
// List of All candidate Id ....

	@Override
	public List<Object[]> getAllcandidateId() {
		// TODO Auto-generated method stub
		try {

			String hql = "select applyId ,name from RecruitmentApplyNowForJobEntity";
			DbConnect.DbCon().beginTransaction();
			Query query = DbConnect.DbCon().createQuery(hql);
			List<Object[]> candidateData = query.list();
			DbConnect.DbCon().getTransaction().commit();
			return candidateData;
		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query :" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Assign to job Implementation ....

	@Override
	public boolean assignMultipleCandidate(RecruitmentAssignJobEntity entity) {

		DbConnect.DbCon().beginTransaction();
		boolean flag;
		try {

			DbConnect.DbCon().save(entity);
			DbConnect.DbCon().getTransaction().commit();

			flag = true;

		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query :" + e);
			DbConnect.DbCon().getTransaction().rollback();
			flag = false;
		} finally {
			DbConnect.DbCon().clear();
		}
		return flag;
	}

	// Assign to job final ....
	@Override
	public boolean assignMultipleCandidateList(RecruitmentAssignJobBean bean) {

		DbConnect.DbCon().beginTransaction();

		try {
			RecruitmentAssignJobEntity entity = new RecruitmentAssignJobEntity();

			StringBuilder job = new StringBuilder();
			System.out.println(bean);

			for (int i = 0; i < bean.getCandidateApplyId().length; i++) {
				if (i != (bean.getCandidateApplyId().length - 1))
					job.append(bean.getCandidateApplyId()[i]).append(",");
				else
					job.append(bean.getCandidateApplyId()[i]);

			}

			String hql = "from RecruitmentAssignJobEntity where jobPostingId=:jobPostingId";
			Query query = (Query) DbConnect.DbCon().createQuery(hql, RecruitmentAssignJobEntity.class);
			query.setParameter("jobPostingId", bean.getJobPostingId());

			RecruitmentAssignJobEntity assignPost = (RecruitmentAssignJobEntity) query.uniqueResult();

			if (assignPost == null) {

				System.out.println(assignPost);
				entity.setCandidateApplyId(job.toString());

				// System.out.println(entity.getCandidateApplyId());
				// int jobPostingId = bean.getJobPostingId();

				entity.setSkill(bean.getSkill());

				entity.setId(bean.getId());
				entity.setExperience(bean.getExperience());
				entity.setJobPostingId(bean.getJobPostingId());

				System.out.println("job is:" + job);

				DbConnect.DbCon().save(entity);
				DbConnect.DbCon().getTransaction().commit();
			} else {
				System.out.println(assignPost);

				assignPost.setCandidateApplyId(filter(job.toString(), assignPost.getJobPostingId()));
				assignPost.setId(assignPost.getId());
				// entity.setId(assignPost.getId());
				DbConnect.DbCon().saveOrUpdate(assignPost);
				DbConnect.DbCon().getTransaction().commit();
			}

			// DbConnect.DbCon().saveOrUpdate(entity);
			// DbConnect.DbCon().getTransaction().commit();

			return true;

		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query :" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Assign to job Directly Showing in Candidate ....Whoever Assigned.
	@Override
	public List<RecruitmentAssignJobEntity> fetchAssignedJobCandidateList() {
		logger.info("Entering into fetchAssignedJobCandidateList() of HrmsRecruitmentDao class");
		try {
			String hql = "From RecruitmentAssignJobEntity";
			Transaction tx = DbConnect.DbCon().beginTransaction();
			Query<RecruitmentAssignJobEntity> query = DbConnect.DbCon().createQuery(hql,
					RecruitmentAssignJobEntity.class);
			List<RecruitmentAssignJobEntity> assignedEntityList = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Assined List show now in Candidate List.");
			return assignedEntityList;

		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// CandidateId created VBy Vendor....

	@Override
	public boolean savePostProfileListByVendor(RecruitmentPostProfileBean bean) {

		DbConnect.DbCon().beginTransaction();

		try {

			RecruitmentPostProfileEntity entity = new RecruitmentPostProfileEntity();
			// RecruitmentApplyNowForJobEntity candidateEntity = new
			// RecruitmentApplyNowForJobEntity();

			entity.setId(bean.getId());

			entity.setAdditionalSkills(bean.getAdditionalSkills());
			entity.setCandidateEmailid(bean.getCandidateEmailid());
			entity.setCandidateName(bean.getCandidateName());
			entity.setCityId(bean.getCityId());
			entity.setCountryId(bean.getCountryId());
			entity.setCurrentCTC(bean.getCurrentCTC());
			entity.setCurrentOrganization(bean.getCurrentOrganization());
			entity.setDomainSkills(bean.getDomainSkills());

			entity.setExpectedSallary(bean.getExpectedSallary());
			entity.setHighestEducation(bean.getHighestEducation());
			entity.setHomePhoneNo(bean.getHomePhoneNo());
			entity.setJobPostingId(DbConnect.DbCon().get(RecruitmentOpeningsEntity.class, bean.getJobPostingId()));

			entity.setMobileNo(bean.getMobileNo());
			entity.setMultipleLocation(bean.getMultipleLocation());
			entity.setNoticePeriod(bean.getNoticePeriod());
			entity.setRelaventExpMonth(bean.getRelaventExpMonth());
			entity.setRelaventExpYear(bean.getRelaventExpYear());
			entity.setStateId(bean.getStateId());
			entity.setTitle(bean.getTitle());
			entity.setTotalExperienceMonth(bean.getTotalExperienceMonth());
			entity.setTotalExperienceYear(bean.getTotalExperienceYear());
			entity.setVisaCountryId(bean.getVisaCountryId());
			entity.setVisaExpiryDate(bean.getVisaExpiryDate());
			entity.setVisatype(bean.getVisatype());
			entity.setYearOfPassout(bean.getYearOfPassout());
			entity.setUploadProfile(bean.getUploadProfile());
			entity.setIdentificationId(bean.getIdentificationId());
			entity.setIsactive(1);// by default it is 1

			entity.setResumePostedDate(new HrmsGetDateAndTime().getDateOnly());

			entity.setExCompanyGroup(bean.getExCompanyGroup());

			if (bean.getExCompanyGroup() == 1) {
				entity.setExCompanyName(bean.getExCompanyName());
			} else {
				entity.setExCompanyName("Not Applied yet.");
			}

			// DbConnect.DbCon().save(entity);
			DbConnect.DbCon().saveOrUpdate(entity);

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Post Profile Applied Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("Conflicts in Post Profile...." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			DbConnect.DbCon().clear();
		}
	}

	// post profile Duplicacy Check ... final

	@Override
	public RecruitmentPostProfileEntity checkDuplicatePostProfile(String candidateEmailid, String identificationId) {
		logger.info("Entering into checkDuplicatePostProfile() of HrmsRecruitmentDao class");
		try {
			RecruitmentPostProfileEntity duplicate = new RecruitmentPostProfileEntity();

			String hql = "From RecruitmentPostProfileEntity where candidateEmailid=:candidateEmailid And identificationId=:identificationId";
			String hql1 = "From RecruitmentPostProfileEntity where candidateEmailid=:candidateEmailid";
			String hql2 = "From RecruitmentPostProfileEntity where  identificationId=:identificationId";
			Transaction tx = DbConnect.DbCon().beginTransaction();

			Query query = DbConnect.DbCon().createQuery(hql, RecruitmentPostProfileEntity.class);
			Query query1 = DbConnect.DbCon().createQuery(hql1, RecruitmentPostProfileEntity.class);
			Query query2 = DbConnect.DbCon().createQuery(hql2, RecruitmentPostProfileEntity.class);

			query.setParameter("candidateEmailid", candidateEmailid);
			query.setParameter("identificationId", identificationId);
			query1.setParameter("candidateEmailid", candidateEmailid);
			query2.setParameter("identificationId", identificationId);

			if (/* RecruitmentPostProfileEntity) query.uniqueResult() == null && */ (RecruitmentPostProfileEntity) query1
					.uniqueResult() != null) {
				duplicate = (RecruitmentPostProfileEntity) query1.uniqueResult();
				System.out.println(duplicate);

			} else if (/* (RecruitmentPostProfileEntity) query.uniqueResult() == null && */(RecruitmentPostProfileEntity) query2
					.uniqueResult() != null) {
				duplicate = (RecruitmentPostProfileEntity) query2.uniqueResult();
				System.out.println(duplicate);

			} else if ((RecruitmentPostProfileEntity) query.uniqueResult() != null) {
				duplicate = (RecruitmentPostProfileEntity) query.uniqueResult();
				System.out.println(duplicate);

			} else if ((RecruitmentPostProfileEntity) query.uniqueResult() == null) {
				duplicate = (RecruitmentPostProfileEntity) query.uniqueResult();
				System.out.println(duplicate);

			}

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Duplicacy  List show now in Post profile List.");
			return duplicate;

		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// fetch assign job ....
	@Override
	public RecruitmentAssignJobEntity fetchAssignedJobList(int jobPostingId) {
		logger.info("Entering into fetchAssignedJobList() of HrmsRecruitmentDao class");
		try {
			RecruitmentAssignJobEntity entity = new RecruitmentAssignJobEntity();

			String hql = "From RecruitmentAssignJobEntity where jobPostingId=:jobPostingId";
			Transaction tx = DbConnect.DbCon().beginTransaction();
			RecruitmentAssignJobEntity assignedEntityList = DbConnect.DbCon()
					.createQuery(hql, RecruitmentAssignJobEntity.class).setParameter("jobPostingId", jobPostingId)
					.uniqueResult();

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Assined List show now in Candidate List.");
			return assignedEntityList;

		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}

	}

	public String filter(String candidateApplyId, int jobPostingId) {

		String hql = "from RecruitmentAssignJobEntity where jobPostingId=:jobPostingId";
		Query query = (Query) DbConnect.DbCon().createQuery(hql, RecruitmentAssignJobEntity.class);
		query.setParameter("jobPostingId", jobPostingId);

		RecruitmentAssignJobEntity assignPost = (RecruitmentAssignJobEntity) query.uniqueResult();
		String str = assignPost.getCandidateApplyId() + "," + candidateApplyId;

		String result = Stream.of(str.split(",")) // stream of elements separated by ";"
				.distinct() // remove duplicates in stream
				.collect(Collectors.joining(","));// create String joining rest of elements using ";"

		System.out.println(result);
		return result;

	}
// Status Report for Each Candidate ..

	@Override
	public List<RecruitmentAssignJobEntity> statusReportForEachCandidate() {
		logger.info("Entering into statusReportForEachCandidate() of HrmsRecruitmentDao class");
		try {
			String str = "";

			List<RecruitmentAssignJobEntity> entity = fetchAssignedJobCandidateList();
			boolean flag = true;
			for (RecruitmentAssignJobEntity report : entity) {
				if (flag) {
					str = report.getCandidateApplyId();
					flag = false;
				} else
					str = str + "," + report.getCandidateApplyId();
			}
			String result = Stream.of(str.split(",")).distinct().collect(Collectors.joining(","));

			String result1[] = result.split(",");
			// List<String> ids = Arrays.asList(result);

			List<RecruitmentAssignJobEntity> assignedEntity = new ArrayList<>();

			for (int i = 0; i < result1.length; i++) {

				List<RecruitmentAssignJobEntity> entity1 = fetchAssignedJobCandidateList();
				// List<RecruitmentAssignJobEntity> entity1 = new ArrayList<>();
				// entity1.addAll(entity);
				for (RecruitmentAssignJobEntity assigned1 : entity1) {

					System.out.println(assigned1.getCandidateApplyId());
					System.out.println("Ststus is" + result1[i]);
					System.out.println(assigned1.getCandidateApplyId().contains(result1[i]));
					if (assigned1.getCandidateApplyId().contains(result1[i])) {
						assignedEntity.add(stausReport(assigned1, result1[i]));
						System.out.println(assignedEntity);
					}
				}

			}
			System.out.println("final result is" + assignedEntity);

			Transaction tx = DbConnect.DbCon().beginTransaction();

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Assined List show now in Status Report.");
			return assignedEntity;

		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}

	}

	// Each Object of RecruitmentAssignedJobEntity is added with Single String
	// CandidateApplyId. :-
	public RecruitmentAssignJobEntity stausReport(RecruitmentAssignJobEntity entity, String str) {
		entity.setCandidateApplyId(str);
		System.out.println("entity is " + entity);
		return entity;

	}

	// Assigned Status Update:- Report for Candidate

	/*@Override
	public String assignedStatusReport(RecruitmentAssignStatusBean bean) {
		Transaction tx = DbConnect.DbCon().beginTransaction();
		String result = null;
		try {

			// RecruitmentAssignStatusBean bean= new RecruitmentAssignStatusBean();
			RecruitmentAssignReportEntity entity = new RecruitmentAssignReportEntity();
			entity.setId(bean.getId());
			entity.setJobPostingId(bean.getJobPostingId());
			entity.setCandidateApplyId(bean.getCandidateApplyId());
			entity.setAssignReportStatus(bean.getAssignReportStatus());
			entity.setAssignReportId(DbConnect.DbCon().get(RecruitmentAssignJobEntity.class, bean.getAssignReportId()));
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Status Updated Successfully.");
			if (bean.getId() == 0) {
				result = "Saved";
				bean.setId(entity.getId());
			} else if (bean.getId() > 0)
				result = "Updated";
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Failed to Execute Hql Query....");
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}
		return result;
	}
*/
	
	@Override
	public String assignedStatusReport(RecruitmentAssignStatusBean bean) {

		String result = null;
		try {
			Transaction tx = DbConnect.DbCon().beginTransaction();
			if (bean.getId() == 0) {

				RecruitmentAssignReportEntity entity = new RecruitmentAssignReportEntity();
				entity.setId(bean.getId());
				entity.setJobPostingId(bean.getJobPostingId());
				entity.setCandidateApplyId(bean.getCandidateApplyId());
				entity.setAssignReportStatus(bean.getAssignReportStatus());
				entity.setAssignReportId(
						DbConnect.DbCon().get(RecruitmentAssignJobEntity.class, bean.getAssignReportId()));
				DbConnect.DbCon().save(entity);
				DbConnect.DbCon().getTransaction().commit();
				logger.info("Status Updated Successfully.");
				if (bean.getId() == 0) {
					result = "Saved";
					bean.setId(entity.getId());
				}

			} else if (bean.getId() > 0) {

				RecruitmentAssignReportEntity entity = new RecruitmentAssignReportEntity();
				entity.setId(bean.getId());
				entity.setJobPostingId(bean.getJobPostingId());
				entity.setCandidateApplyId(bean.getCandidateApplyId());
				entity.setAssignReportStatus(bean.getAssignReportStatus());
				entity.setAssignReportId(
						DbConnect.DbCon().get(RecruitmentAssignJobEntity.class, bean.getAssignReportId()));
				DbConnect.DbCon().update(entity);
				DbConnect.DbCon().getTransaction().commit();
				logger.info("Status Updated Successfully.");

				result = "Updated";
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("Failed to Execute Hql Query....");
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}
		return result;
	}

	// Assign Report Updated Based on the Click Service.
	@Override
	public List<RecruitmentAssignReportEntity> getSpecificStatusReport(String assignReportStatus) {

		try {

			DbConnect.DbCon().beginTransaction();
			String hql = "From RecruitmentAssignReportEntity where assignReportStatus=:assignReportStatus";
			Query<RecruitmentAssignReportEntity> query = DbConnect.DbCon().createQuery(hql,
					RecruitmentAssignReportEntity.class);

			query.setParameter("assignReportStatus", assignReportStatus);

			List<RecruitmentAssignReportEntity> listOfUpdatedStatus = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Updated Record Retrieved Successfully.");
			return listOfUpdatedStatus;

		} catch (HibernateException e) {
			logger.error("Failed To Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}

	}
	// 1.) Selected Status

	@Override
	public List<RecruitmentAssignReportEntity> getSpecificStatusReportSelected() {
		try {
			DbConnect.DbCon().beginTransaction();
			String hql = "From RecruitmentAssignReportEntity";
			List<RecruitmentAssignReportEntity> listOfUpdatedSelectedStatus = DbConnect.DbCon()
					.createQuery(hql, RecruitmentAssignReportEntity.class).list();

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Updated Record Retrieved Successfully.");
			return (List<RecruitmentAssignReportEntity>) listOfUpdatedSelectedStatus;

		} catch (HibernateException e) {
			logger.error("Failed To Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Recruiters Based Status....
	@Override
	public List<Object[]> getRecruitersBasedCandidateStatus(String recruiters, String assignReportStatus) {

		List<Object[]> recruitersBasedStatus = new ArrayList<>();
		String hql = "";
		try {
			Query<Object[]> query;

			DbConnect.DbCon().beginTransaction();
			if (recruiters.length() != 0 && assignReportStatus.length() != 0) {
				hql = "select req.recruiters,req.recruitersMailId,report.assignReportStatus,report.candidateApplyId,report.jobPostingId,req.openingForJobTitle,p.candidateName"
						+ " from RecruitmentOpeningsEntity req,RecruitmentAssignReportEntity report,RecruitmentPostProfileEntity p"
						+ " where req.recruiters=:recruiters and report.assignReportStatus=:assignReportStatus and report.jobPostingId=req.id and p.id=report.candidateApplyId";
				query = DbConnect.DbCon().createQuery(hql, Object[].class);
				query.setParameter("recruiters", recruiters);
				query.setParameter("assignReportStatus", assignReportStatus);
				recruitersBasedStatus = query.getResultList();
			} else if (recruiters.length() != 0 && assignReportStatus.length() == 0) {
				hql = "select req.recruiters,req.recruitersMailId,report.assignReportStatus,report.candidateApplyId,report.jobPostingId,req.openingForJobTitle,p.candidateName"
						+ " from RecruitmentOpeningsEntity req,RecruitmentAssignReportEntity report,RecruitmentPostProfileEntity p"
						+ " where req.recruiters=:recruiters and report.jobPostingId=req.id and p.id=report.candidateApplyId";
				query = DbConnect.DbCon().createQuery(hql, Object[].class);
				query.setParameter("recruiters", recruiters);
				recruitersBasedStatus = query.getResultList();
			} else if (recruiters.length() == 0 && assignReportStatus.length() != 0) {
				hql = "select req.recruiters,req.recruitersMailId,report.assignReportStatus,report.candidateApplyId,report.jobPostingId,req.openingForJobTitle,p.candidateName"
						+ " from RecruitmentOpeningsEntity req,RecruitmentAssignReportEntity report,RecruitmentPostProfileEntity p"
						+ " where report.assignReportStatus=:assignReportStatus and report.jobPostingId=req.id and p.id=report.candidateApplyId";
				query = DbConnect.DbCon().createQuery(hql, Object[].class);
				query.setParameter("assignReportStatus", assignReportStatus);
				recruitersBasedStatus = query.getResultList();
			}

			// Query<Object[]> query = DbConnect.DbCon().createQuery(hql, Object[].class);
			/*
			 * Query<Object[]> query1 = DbConnect.DbCon().createQuery(hql1, Object[].class);
			 * Query<Object[]> query2 = DbConnect.DbCon().createQuery(hql2, Object[].class);
			 */

			/*
			 * query.setParameter("recruiters",recruiters);
			 * query.setParameter("assignReportStatus",assignReportStatus);
			 */
			/*
			 * query1.setParameter("recruiters",recruiters);
			 * query2.setParameter("assignReportStatus",assignReportStatus);
			 */

			// recruitersBasedStatus = query.getResultList();
			/*
			 * recruitersBasedStatus = query1.getResultList(); recruitersBasedStatus =
			 * query2.getResultList();
			 */

			DbConnect.DbCon().getTransaction().commit();
			logger.info("Updated Record Retrieved Successfully.");
			return recruitersBasedStatus;

		} catch (HibernateException e) {
			logger.error("Failed To Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;

		} finally {
			DbConnect.DbCon().clear();
		}

	}
}
