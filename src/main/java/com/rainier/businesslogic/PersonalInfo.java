package com.rainier.businesslogic;

import com.rainier.beans.*;
import com.rainier.dao.HrmsPersonalInfo;
import com.rainier.dao.Impl.HrmsPersonalInfoIDaompl;
import com.rainier.dto.responseBean.CommonResponseBean;
import com.rainier.entities.*;
import com.rainier.utility.DaysCalculator;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PersonalInfo {
	private final static Logger logger = Logger.getLogger(PersonalInfo.class);

	private final CommonResponseBean response = new CommonResponseBean();
	private static final HrmsPersonalInfo dao = new HrmsPersonalInfoIDaompl();

	public Response saveJobHistory(JobHistoryBean bean) {
		logger.info("Entered into saveJobHistory()");
		JobHistoryEntity entityBean = new JobHistoryEntity();
		try {
			BeanUtils.copyProperties(entityBean, bean);
			
			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setAmountPaid(bean.getAmountPaid());
			entityBean.setAmountRecieved(bean.getAmountRecieved());
			entityBean.setClientId(bean.getClientId());
			entityBean.setClientName(bean.getClientName());
			entityBean.setCreatedBy(bean.getUserId());
			entityBean.setDepartmentId(bean.getDepartmentId());
			entityBean.setDepartmentName(bean.getDepartmentName());
			entityBean.setFromDate(bean.getFromDate());
			entityBean.setId(bean.getId());
			entityBean.setJobTitleId(bean.getJobTitleId());
			entityBean.setJobTitleName(bean.getJobTitleName());
			entityBean.setPositionId(bean.getClientId());
			entityBean.setPositionName(bean.getPositionName());
			entityBean.setToDate(bean.getToDate());
			entityBean.setUserId(bean.getUserId());
			entityBean.setVendorName(bean.getVendorName());
			entityBean.setIsactive(1);

			 String res = dao.saveJobHistory(entityBean);
			logger.info("Successfully inserted.");
			if(!res.isEmpty()) {
			response.setMessage("JobHistory added Successfully.");
			response.setStatus(true);
			}else {
				response.setMessage("JobHistory while adding failed.");
				response.setStatus(false);
			}

			return Response.status(Response.Status.OK).entity(response).build();

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving BU details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}

	// update job history.
	public Response updateJobHistory(JobHistoryBean bean) {
		JobHistoryEntity entityBean = new JobHistoryEntity();
		try {
			BeanUtils.copyProperties(entityBean, bean);
			// Timestamp modifiedDate = new HrmsGetDateAndTime().GetUTCdatetimeAsString();
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setIsactive(1);

			dao.updateJobHistory(entityBean);
			logger.info("Updated Successfully.");
			response.setMessage("Job History Updated.");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// System.out.println("this is exception" + e1);
			response.setMessage("Error while updating - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// System.out.println("this is exception" + e1);
			response.setMessage("Error while updating - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// Fetch job history.
	public Response getJobHistory(int userId) {

		try {
			List<JobHistoryEntity> listOfJOb = dao.getJobHistory(userId);
			if (!listOfJOb.isEmpty()) {
				response.setMessage("Job History List");
				response.setStatus(true);
				response.setList(listOfJOb);
			} else {
				response.setMessage("job history not Listed.");
				response.setStatus(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error while fetching.. " + e.getMessage());
			response.setStatus(false);

		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	// saving Exp details.
	public Response addExperience(ExperienceBean bean) {
		logger.info("Entered into addExperience()");
		ExperinceEntity entity = new ExperinceEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setCreatedBy(bean.getUserId());
			entity.setIsActive((byte) 1);
			dao.saveExperience(entity);
			logger.info("Successfully inserted.");
			response.setMessage("Experience Details added Successfully.");
			response.setStatus(true);

			/*
			 * entity.setCompanyName(bean.getCompanyName());
			 * entity.setCompanyWebsite(bean.getCompanyName());
			 */
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving BU details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}

	// exp fetch
	public Response getExpDetails(int userId) {
		// ExperinceEntity entity = new ExperinceEntity();
		List<ExperinceEntity> listofExp = dao.getExpDetails(userId);
		if (!listofExp.isEmpty()) {
			response.setMessage("Experience Details retrieved.");
			response.setStatus(true);
			response.setList(listofExp);
			return Response.status(Response.Status.OK).entity(response).build();
		} else {
			response.setMessage("failed to retrieved data...");
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// update Exp.
	public Response updateExp(ExperienceBean bean) {
		ExperinceEntity entity = new ExperinceEntity();

		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setModifiedBy(bean.getUserId());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			boolean update = dao.updateExp(entity);
			if (update == true) {
				response.setMessage("Updated Successfully.");
				response.setStatus(true);

			}
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// System.out.println("this is exception" + e1);
			response.setMessage("Error while updating - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			// System.out.println("this is exception" + e1);
			response.setMessage("Error while updating - " + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// add Education Level Code .
	public Response addEducationLevel(EducationLevelCodeBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		EducationLevelCodeEntity entity = new EducationLevelCodeEntity();
		// HrmsPersonalInfo dao = new HrmsPersonalInfoIDaompl();
		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsactive(1);

			boolean saved = dao.addEducationLevel(entity);
			if (saved == true)
				response.setMessage("Education Level Added Successfully.");
			response.setStatus(true);

			return Response.status(Response.Status.OK).entity(response).build();

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving  Education Level details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving Education Level details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}

	// fetch Education Level code.
	public Response getEducationLevel() {
		CommonResponseBean response = new CommonResponseBean();
		// HrmsPersonalInfo dao = new HrmsPersonalInfoIDaompl();
		List<EducationLevelCodeEntity> ListOfEduCode = dao.getEduLevelCode();
		if (ListOfEduCode != null) {
			response.setMessage("Education Level Code Retrieved Successfully.");
			response.setStatus(true);
			response.setList(ListOfEduCode);
		} else {
			response.setMessage("Failed to Retrieved.");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();

	}

	// add Emp Education Details.
	public Response saveEmpEducation(EmpEducationDetailsBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		EmpEducationDetailsEntity entity = new EmpEducationDetailsEntity();
		// HrmsPersonalInfo dao = new HrmsPersonalInfoIDaompl();
		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsactive(1);

			boolean saved = dao.addEmpEducationDetails(entity);
			if (saved == true)
				response.setMessage(" Emp Education Details Added Successfully.");
			response.setStatus(true);

			return Response.status(Response.Status.OK).entity(response).build();

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving  Emp Education details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setMessage("Error while saving Emp Education Details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}

	// fetch Added Emp Education Details.
	public Response fetchEmpEduDetails(int userId) {
		CommonResponseBean response = new CommonResponseBean();
		// HrmsPersonalInfo dao = new HrmsPersonalInfoIDaompl();
		List<EmpEducationDetailsEntity> listOfEmpEdu = dao.getEmpEducationDetails(userId);

		if (listOfEmpEdu != null) {
			response.setMessage(" Emp Education Details Retrieved Successfully.");
			response.setStatus(true);
			response.setList(listOfEmpEdu);
		} else {
			response.setMessage("Failed to Retrieved.");
			response.setStatus(false);
		}
		return Response.ok().entity(response).build();

	}

	// update Emp Education Details
	public Response updateEmpEducationDetails(EmpEducationDetailsBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		EmpEducationDetailsEntity entity = new EmpEducationDetailsEntity();
		// HrmsPersonalInfo dao = new HrmsPersonalInfoIDaompl();
		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsactive(1);

			boolean updated = dao.updateEmpEducationDetails(entity);
			if (updated == true) {
				response.setMessage("Updated Successfully.");
				response.setStatus(true);

			} else {
				response.setMessage("Error while Updating Details.");
				response.setStatus(false);
			}
		} catch (IllegalAccessException e1) {
			logger.error("Error While updates:");
			e1.printStackTrace();
			response.setMessage("Failed to Update:" + e1.getMessage());
			response.setStatus(false);

		} catch (InvocationTargetException e2) {
			logger.error("Error While Updating.");
			e2.printStackTrace();
			response.setMessage("Error While Updating Details:" + e2.getMessage());
			response.setStatus(false);

		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// fetch Disability D4etails.
	public Response fetchDisabilityDetails(int userId) {

		CommonResponseBean responseBean = new CommonResponseBean();
		DisabilityBeans bean = new DisabilityBeans();

		List<DisabilityEntity> entityList = dao.getDisabilityDetails(userId);
		// HrmsPersonalInfo dao = new HrmsPersonalInfoIDaompl();
		List<DisabilityBeans> list = new ArrayList<>();

		if (entityList != null) {
			try {
				BeanUtils.copyProperties(bean, entityList);
				list.add(bean);
				responseBean.setMessage("Disability Details Retrieved Successfully.");
				responseBean.setStatus(true);
				responseBean.setList(entityList);
			} catch (Exception e) {
				responseBean.setMessage("Something Went Wrong");
				responseBean.setStatus(false);
				responseBean.setList(entityList);
				e.printStackTrace();
			}
		} else {
			responseBean.setMessage("Training Details Not Found.");
			responseBean.setStatus(false);
			responseBean.setList(entityList);
		}
		return Response.status(Response.Status.OK).entity(responseBean).build();
	}

	// Disability for employee logic.
	public Response saveOrUpdateEmployeeDisability(DisabilityBeans beans) {
		CommonResponseBean response = new CommonResponseBean();
		// HrmsPersonalInfo dao = new HrmsPersonalInfoIDaompl();
		DisabilityEntity entity = new DisabilityEntity();
		try {
			BeanUtils.copyProperties(entity, beans);
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsactive(1);
			boolean disabilityDetailsList = dao.saveOrUpdateDisability(entity);
			if (disabilityDetailsList == true && beans.getId() > 0) {
				response.setMessage("Disability  Updated Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Disability Saved Successfully.");
				response.setStatus(true);
			}

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error While Saving ....:" + e1.getMessage());
			response.setStatus(false);

		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			response.setMessage("Error While Saving ....:" + e2.getMessage());
			response.setStatus(false);

		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// save training & Certification Details.

	public Response saveTrainingCertification(Training_CertificationBean bean)
			throws IllegalAccessException, InvocationTargetException {

		CommonResponseBean response = new CommonResponseBean();
		// HrmsPersonalInfo dao = new HrmsPersonalInfoIDaompl();
		Training_CertificationEntity entity = new Training_CertificationEntity();

		BeanUtils.copyProperties(entity, bean);
		entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
		entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
		entity.setIsactive(1);
		dao.saveTrainingCertification(entity);
		response.setMessage("Training Certification Saved Or Updated Successfully.");
		response.setStatus(true);

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// fetch for Training Certificate logic.

	public Response getTrainingCertificate(int userId) {
		CommonResponseBean responseBean = new CommonResponseBean();
		Training_CertificationBean bean = new Training_CertificationBean();

		List<Training_CertificationEntity> entityList = dao.getTrainingDetails(userId);
		// HrmsPersonalInfo dao = new HrmsPersonalInfoIDaompl();
		List<Training_CertificationBean> list = new ArrayList<>();

		if (entityList != null) {
			try {
				BeanUtils.copyProperties(bean, entityList);
				list.add(bean);
				responseBean.setMessage("Training Details Retrieved Successfully.");
				responseBean.setStatus(true);
				responseBean.setList(entityList);
			} catch (Exception e) {
				responseBean.setMessage("Something Went Wrong");
				responseBean.setStatus(false);
				responseBean.setList(entityList);
				e.printStackTrace();
			}
		} else {
			responseBean.setMessage("Training Details Not Found.");
			responseBean.setStatus(false);
			responseBean.setList(entityList);
		}
		return Response.status(Response.Status.OK).entity(responseBean).build();

	}

	// saving Medical Claim for Employee....
	public Response saveOrUpdateMedicalClaims(MedicalClaimsInjuryBean bean) {
		// CommonResponseBean responseBean= new CommonResponseBean();
		MedicalClaimEntity entity = new MedicalClaimEntity();
		// HrmsPersonalInfo info= new HrmsPersonalInfoIDaompl();
		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());

			entity.setNoOfDaysApplied(new DaysCalculator().getNoOfDaysApplied(bean.getAppliedleaveStartDate(),
					bean.getAppliedleaveEndDate()));
			entity.setNoOfDaysApproved(new DaysCalculator().getApprovedNoOfDays(bean.getApprovedleaveStartDate(),
					bean.getApprovedleaveEndDate()));
			entity.setIsactive(1);
			boolean claim = dao.saveOrUpdateMedicalClaim(entity);

			if (claim == true && bean.getId() > 0) {
				response.setMessage("medical Claim  Updated Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("medical Claim  Saved Successfully.");
				response.setStatus(false);
			}

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error While Saving ....:" + e1.getMessage());
			response.setStatus(false);

		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			response.setMessage("Error While Saving ....:" + e2.getMessage());
			response.setStatus(false);

		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// for Medical Claim Logic ......
	public Response fetchMedicalClaims(int userId) {
		CommonResponseBean response = new CommonResponseBean();
		List<MedicalClaimEntity> entityList = dao.fetchMedicalClaim(userId);
		// List<MedicalClaimsInjuryBean> beanAl= new ArrayList<>();
		if (entityList != null) {
			response.setMessage("Medical Claim List Retrieved.");
			response.setStatus(true);
			response.setList(entityList);
		} else {
			response.setStatus(false);
			response.setMessage("failed To retrieved data.");
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// for Save or Update Dependency Logic.
	public Response saveOrUpdateDependencyDetails(DependencyDetailsBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		DependencyDetailsEntity entity = new DependencyDetailsEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsactive(1);
			entity.setId(bean.getId());
			boolean result = dao.saveOrUpdateDependency(entity);

			if (result == true && bean.getId() > 0) {

				response.setMessage("Dependency Details Updated Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Dependency Details Saved Successfully.");
				response.setStatus(true);
			}
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error While Saving ....:" + e1.getMessage());
			response.setStatus(false);
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			response.setMessage("Error While Saving ....:" + e2.getMessage());
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();

	}

	// Dependency Details Logic for fetching....
	public Response fetchDependentList(int userId) {
		CommonResponseBean response = new CommonResponseBean();
		List<DependencyDetailsEntity> entityList = dao.getlistOfDependent(userId);
		// List<DependencyDetailsBean> beanAl= new ArrayList<>();
		if (entityList != null) {
			response.setMessage("Dependency List Retrieved.");
			response.setStatus(true);
			response.setList(entityList);
		} else {
			response.setStatus(false);
			response.setMessage("failed To retrieved data.");
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// Logic for Saving or Updating Visa Details.

	public Response saveOrupdatevisaDetails(VisaAndImmigrationBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		VisaAndImmigrationEntity entity = new VisaAndImmigrationEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsactive(1);
			boolean visaDetails = dao.saveOrUpdateVisa_Immigration(entity);
			if (visaDetails == true && bean.getId() > 0) {
				response.setMessage("Visa Details Updated Successfully.");
				response.setStatus(true);
			} else if (visaDetails == true) {
				response.setMessage("Visa Details Saved Successfully");
				response.setStatus(true);
			} else {
				response.setMessage("Something went Wrong .");
				response.setStatus(false);
			}

		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error While Saving ....:" + e1.getMessage());
			response.setStatus(false);
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			response.setMessage("Error While Saving ....:" + e2.getMessage());
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	// fetch Visa & Immigration Details Services.

	public Response fetchVisaimmigrationDetails(int userId) {

		CommonResponseBean response = new CommonResponseBean();
		List<VisaAndImmigrationEntity> entityList = dao.getVisaimmigrationDetails(userId);
		// List<VisaAndImmigrationBean> beanAl= new ArrayList<>();
		if (entityList != null) {
			response.setMessage("Visa Immigration Retrived Successfully.");
			response.setStatus(true);
			response.setList(entityList);
		} else {
			response.setMessage("Failed To Retrieved Data.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

}
