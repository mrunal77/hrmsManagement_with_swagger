package com.rainier.businesslogic;

import com.rainier.beans.*;
import com.rainier.dao.HrmsBackgroundCheckDao;
import com.rainier.dao.Impl.HrmsBackgroundCheckDaoImpl;
import com.rainier.dto.responseBean.CommonResponseBean;
import com.rainier.entities.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class BackgroundCheckDetails {
	//private static final Logger logger = Logger.getLogger(BackgroundCheckDetails.class);
	private static final HrmsBackgroundCheckDao dao = new HrmsBackgroundCheckDaoImpl();

	/*
	 * public Response saveHighestDegreeEarned(BgHighestDegreeEarnedBean bean) {
	 * logger.info("saveHighestDegreeEarned method"); BgHighestDegreeEarnedEntity
	 * entityList = new BgHighestDegreeEarnedEntity(); CommonResponseBean response =
	 * new CommonResponseBean(); try {
	 * 
	 * BeanUtils.copyProperties(entityList, bean); int educationDetails =
	 * dao.addHighestDegreeEarned(bean); if (educationDetails == 1 && bean.getId() >
	 * 0) { response.setMessage("Updated Successfully."); response.setStatus(true);
	 * } else { response.setMessage("Saved Successfully.");
	 * response.setStatus(true); } return
	 * Response.status(Response.Status.OK).entity(response).build(); } catch
	 * (IllegalAccessException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); response.setMessage("Error while saving details - " +
	 * e.getMessage()); response.setStatus(false); return
	 * Response.status(Response.Status.OK).entity(response).build(); } catch
	 * (InvocationTargetException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); response.setMessage("Error while saving BU details - " +
	 * e.getMessage()); response.setStatus(false); return
	 * Response.status(Response.Status.OK).entity(response).build();
	 * 
	 * }
	 * 
	 * }
	 */

	public Response SaveHighestDegreeWithOther(BgHighestDegreeEarnedBean bean) {

		CommonResponseBean response = new CommonResponseBean();
		ArrayList<OtherHighestDegree> beanAl = new ArrayList<OtherHighestDegree>();
		boolean result = false;

		for (OtherHighestDegree degree : bean.getOtherHighestDegreeName()) {

			BgHighestDegreeEarnedEntity entity = new BgHighestDegreeEarnedEntity();

			// entity.setId(bean.getId());
			entity.setUserId(bean.getUserId());

			entity.setBachlorsDegree(bean.getBachlorsDegree());
			entity.setBachlorsDegreeInstituteName(bean.getBachlorsDegreeInstituteName());
			entity.setBachlorsDegreeNOY(bean.getBachlorsDegreeNOY());
			entity.setBachlorsDegreeYOP(bean.getBachlorsDegreeYOP());
			entity.setDiploma_HsscDegree(bean.getDiploma_HsscDegree());
			entity.setDiploma_HsscDegreeInstituteName(bean.getDiploma_HsscDegreeInstituteName());
			entity.setDiploma_HsscDegreeNOY(bean.getDiploma_HsscDegreeNOY());
			entity.setDiploma_HsscDegreeYOP(bean.getDiploma_HsscDegreeYOP());
			entity.setMasterDegree(bean.getMasterDegree());
			entity.setMasterDegreeInstituteName(bean.getMasterDegreeInstituteName());
			entity.setMasterDegreeNOY(bean.getMasterDegreeNOY());
			entity.setMasterDegreeYOP(bean.getMasterDegreeYOP());
			entity.setOther(degree.getOtherHighestDegree());
			entity.setOtherDegreeInstituteName(degree.getOtherDegreeInstituteName());
			entity.setOtherDegreeNOY(degree.getOtherDegreeNOY());
			entity.setOtherDegreeYOP(degree.getOtherDegreeYOP());

			result = dao.saveHighestDegreeEarned(entity);
		}

		if (result == true) {

			response.setMessage("Saved Successfully.");
			response.setStatus(true);
			return Response.status(Response.Status.OK).entity(response).build();
		} else {
			response.setMessage("Failed to Save.");
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	@SuppressWarnings({ "unused", "unchecked" })
	public Response getHighestDegreeEarned(int userId) {
		boolean flag = true;
		CommonResponseBean response = new CommonResponseBean();
		BgHighestDegreeEarnedEntity bgEntity = new BgHighestDegreeEarnedEntity();

		// List<BgHighestDegreeEarnedEntity> bgListEntity= new ArrayList<>();

		List bgListEntity = new ArrayList<>();

		List<BgHighestDegreeEarnedEntity> listOfHighestDegreeEarned = dao.fetchHighestDegree(userId);
		for (BgHighestDegreeEarnedEntity bg : listOfHighestDegreeEarned) {
			if (flag == true) {

				bgEntity.setId(bg.getId());
				bgEntity.setUserId(bg.getUserId());
				bgEntity.setBachlorsDegree(bg.getBachlorsDegree());
				bgEntity.setBachlorsDegreeInstituteName(bg.getBachlorsDegreeInstituteName());
				bgEntity.setBachlorsDegreeNOY(bg.getBachlorsDegreeNOY());
				bgEntity.setBachlorsDegreeYOP(bg.getBachlorsDegreeYOP());
				bgEntity.setDiploma_HsscDegree(bg.getDiploma_HsscDegree());
				bgEntity.setDiploma_HsscDegreeInstituteName(bg.getDiploma_HsscDegreeInstituteName());
				bgEntity.setDiploma_HsscDegreeNOY(bg.getDiploma_HsscDegreeNOY());
				bgEntity.setDiploma_HsscDegreeYOP(bg.getDiploma_HsscDegreeYOP());
				bgEntity.setMasterDegree(bg.getMasterDegree());
				bgEntity.setMasterDegreeInstituteName(bg.getMasterDegreeInstituteName());
				bgEntity.setMasterDegreeNOY(bg.getMasterDegreeNOY());
				bgEntity.setMasterDegreeYOP(bg.getMasterDegreeYOP());
				bgEntity.setSscDegree(bg.getSscDegree());
				bgEntity.setSscDegreeInstituteName(bg.getSscDegreeInstituteName());
				bgEntity.setSscDegreeYOP(bg.getSscDegreeYOP());
				bgEntity.setOther(bg.getOther());
				bgEntity.setOtherDegreeInstituteName(bg.getOtherDegreeInstituteName());
				bgEntity.setOtherDegreeNOY(bg.getOtherDegreeNOY());
				bgEntity.setOtherDegreeYOP(bg.getOtherDegreeYOP());

				bgListEntity.add(bgEntity);
				flag = false;

			} else {

				OtherHighestDegree otherDegree = new OtherHighestDegree();
				List<OtherHighestDegree> others = new ArrayList<>();
				otherDegree.setOtherHighestDegree(bg.getOther());
				otherDegree.setOtherDegreeInstituteName(bg.getOtherDegreeInstituteName());
				otherDegree.setOtherDegreeNOY(bg.getOtherDegreeNOY());
				otherDegree.setOtherDegreeYOP(bg.getOtherDegreeYOP());

				others.add(otherDegree);

				bgListEntity.add(others);

				// System.out.println(bgListEntity);
			}

			// System.out.println(bgListEntity);
		}
		if (bgListEntity != null) {
			response.setMessage("Highest Degree Retrived.");
			response.setStatus(true);
			response.setList(bgListEntity);

		} else {
			response.setMessage("failed to Retrived data.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// Save emp personal Info .

	public Response saveEmpPersonalDetails(BgEmpPersonalInfoBean bean) {

		CommonResponseBean response = new CommonResponseBean();
		BgEmpPersonalInfoEntity entity = new BgEmpPersonalInfoEntity();
		// HrmsBackgroundCheckDao dao = new HrmsBackgroundCheckDaoImpl();
		try {
			BeanUtils.copyProperties(entity, bean);
			if (bean.getIsSameCurr_as_Perm() == 0) {
				entity.setPermState(bean.getPermState());
				entity.setPermCity(bean.getPermCity());
				entity.setPermZipcode(bean.getPermZipcode());

			} else {
				entity.setPermState(bean.getCurrentState());
				entity.setPermCity(bean.getCurrentCity());
				entity.setPermZipcode(bean.getCurrentZipcode());
			}

			boolean bgEmpDetails = dao.saveEmpPersonalInfo(entity);
			if (bgEmpDetails == true && bean.getId() > 0) {
				response.setMessage("Employee Personal Detail Updated Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Employee Personal Detail Inserted Successfully.");
				response.setStatus(true);
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
			response.setMessage("Error while saving  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}

	// Employment History Saving.
	public Response saveEmploymentHistory(BgEmploymentHistoryBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		BgEmploymentHistoryEntity entity = new BgEmploymentHistoryEntity();
		// HrmsBackgroundCheckDao dao = new HrmsBackgroundCheckDaoImpl();

		try {
			BeanUtils.copyProperties(entity, bean);
			boolean employmentHistory = dao.saveEmploymentHistory(entity);
			if (employmentHistory == true && bean.getCompanyid() > 0) {
				response.setMessage("Updated Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Inserted Successfully.");
				response.setStatus(true);
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
			response.setMessage("Error while saving  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}

	public Response saveEmpWorkedHistory(BgEmploymentHistoryBean bean) {
		CommonResponseBean response = new CommonResponseBean();

		ArrayList<companyInfo> beanAl = new ArrayList<companyInfo>();
		boolean result = false;

		for (companyInfo info : bean.getPreviousCompany()) {

			BgEmploymentHistoryEntity entity = new BgEmploymentHistoryEntity();

			entity.setUserId(bean.getUserId());
			entity.setEmployerAddressC1(info.getEmployerAddressC1());
			entity.setEmployerCityC1(info.getEmployerCityC1());
			entity.setEmployerDutiesC1(info.getEmployerDutiesC1());
			entity.setEmployerPhoneC1(info.getEmployerPhoneC1());
			entity.setEmployerReasonForLeaveC1(info.getEmployerReasonForLeaveC1());
			entity.setEmployerStateC1(info.getEmployerStateC1());
			entity.setEmployerSupervisorContactNoC1(info.getEmployerSupervisorContactNoC1());
			entity.setEmployerSupervisorEmailC1(info.getEmployerSupervisorEmailC1());
			entity.setEmployerSupervisorNameC1(info.getEmployerSupervisorNameC1());
			entity.setEmployerTypeC1(info.getEmployerTypeC1());
			entity.setEmployerWebsiteC1(info.getEmployerWebsiteC1());
			entity.setEmployerZipcodeC1(info.getEmployerZipcodeC1());
			entity.setJobTitleC1(info.getJobTitleC1());

			/*
			 * entity.setJoiningDateC1(info.getJoiningDateC1());
			 * entity.setLeaveDateC1(info.getLeaveDateC1());
			 */

			entity.setJoiningDateC1(info.getJoiningDateC1());
			entity.setLeaveDateC1(info.getLeaveDateC1());

			result = dao.saveEmpWorkedHistory(entity);

		}
		if (result == true) {

			response.setMessage("EmployeeMent History Saved  Successfully.");
			response.setStatus(true);
			response.setList(beanAl);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// professional Reference.
	public Response saveProfessionalReference(BgEmpProfessionalReferenceBean bean) {
		CommonResponseBean response = new CommonResponseBean();

		/*
		 * try { BeanUtils.copyProperties(entity, bean);
		 */
		for (BgReferenceApplied reference : bean.getApplied()) {

			BgEmpProfessionalReferneceEntity entity = new BgEmpProfessionalReferneceEntity();

			entity.setId(bean.getId());
			entity.setUserId(bean.getUserId());

			entity.setReferredByCity(reference.getReferredByCity());
			entity.setReferredByEmail(reference.getReferredByEmail());
			entity.setReferredByName(reference.getReferredByName());
			entity.setReferredByOccupation(reference.getReferredByOccupation());
			entity.setReferredByPhone(reference.getReferredByPhone());
			entity.setReferredByState(reference.getReferredByState());

			boolean savedEntity = dao.saveProfessionalreference(entity);

			if (bean.getId() > 0 && savedEntity == true) {
				response.setMessage("Employee Professional Reference Updated  Successfully.");
				response.setStatus(true);
			} else {
				response.setMessage("Employee Professional Reference Inserted Successfuly.");
				response.setStatus(true);

			}

		}

		/*
		 * boolean savedEntity = dao.saveProfessionalreference(entity); if (bean.getId()
		 * > 0 && savedEntity == true) { response.setMessage("Updated Successfully.");
		 * response.setStatus(true); } else {
		 * response.setMessage("Inserted Successfuly."); response.setStatus(true);
		 * 
		 * }
		 */
		return Response.status(Response.Status.OK).entity(response).build();
		/*
		 * } catch (IllegalAccessException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); response.setMessage("Error while saving details - " +
		 * e.getMessage()); response.setStatus(false); return
		 * Response.status(Response.Status.OK).entity(response).build(); } catch
		 * (InvocationTargetException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); response.setMessage("Error while saving  details - " +
		 * e.getMessage()); response.setStatus(false); return
		 * Response.status(Response.Status.OK).entity(response).build();
		 * 
		 * }
		 */

	}

	// Employment Gap Saving Logic .
	public Response saveEmploymentGap(BgEmpEmploymentGapBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		BgEmpEmploymentGapEntity entity = new BgEmpEmploymentGapEntity();
		// HrmsBackgroundCheckDao dao= new HrmsBackgroundCheckDaoImpl();
		try {
			BeanUtils.copyProperties(entity, bean);
			boolean gap = dao.saveEmpEmploymentorEducationGap(entity);
			if (gap == true && bean.getId() > 0) {
				response.setMessage("Employee Gap Updated Successfully.");
				response.setStatus(true);

			} else {
				response.setMessage("Employee Gap Saved Successfully.");
				response.setStatus(true);
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
			response.setMessage("Error while saving  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}

	// for fetching Education Gap.
	public Response fetchEmploymentGap(int userId) {
		CommonResponseBean response = new CommonResponseBean();
		List<BgEmpEmploymentGapEntity> entityList = dao.getEmpEmploymentGap(userId);
		if (entityList != null) {
			response.setMessage("Employment Gap Retrived.");
			response.setStatus(true);
			response.setList(entityList);
		} else {
			response.setMessage("failed to Retrived data.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// for fetching professional Reference....
	public Response fetchEmpProfessionalReference(int userId) {
		CommonResponseBean response = new CommonResponseBean();
		List<BgEmpProfessionalReferneceEntity> entityList = dao.getProfessionalReference(userId);
		if (entityList != null) {
			response.setMessage("Employment Professional Reference Retrived.");
			response.setStatus(true);
			response.setList(entityList);
		} else {
			response.setMessage("failed to Retrived data.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// for fetching Employment History...
	public Response fetchEmpEmploymentHistory(int userId) {
		CommonResponseBean response = new CommonResponseBean();
		List<BgEmploymentHistoryEntity> entityList = dao.getEmploymentHistory(userId);
		if (entityList != null) {
			response.setMessage("Employment History  Retrived.");
			response.setStatus(true);
			response.setList(entityList);
		} else {
			response.setMessage("failed to Retrived data.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// for fetching Employee Personal Info ...
	public Response fetchEmployeePersonalInfo(int userId) {
		CommonResponseBean response = new CommonResponseBean();
		List<BgEmpPersonalInfoEntity> entityList = dao.getEmpPersonalInfo(userId);
		if (entityList != null) {
			response.setMessage("Employee Personal Info  Retrived.");
			response.setStatus(true);
			response.setList(entityList);
		} else {
			response.setMessage("failed to Retrived data.");
			response.setStatus(false);
		}

		return Response.status(Response.Status.OK).entity(response).build();

	}

	// upload Exp Letter....
	public Response uploadExpLetter(String filePath, int companyid) {
		SuccessResponseBean response = new SuccessResponseBean();
		if (dao.updateExpLetter(filePath, companyid)) {
			response.setStatus(true);
			response.setMessage("ExpLetter Uploaded Successfully..");
			response.setUploadURL(filePath);

		} else {
			response.setMessage("ExpLetter Overrided Successfully.");
			response.setStatus(false);
			File delFile = new File(filePath);
			if (delFile.exists()) {
				delFile.delete();
			}

		}
		return Response.ok().entity(response).build();

	}

	// Save Or Update Highest Degree Simple One .
	public Response saveHighestDegree(BgHighestDegreeSimpleBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		BgHighestDegreeEarnedEntity entity = new BgHighestDegreeEarnedEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			boolean res = dao.saveHighestDegreeBg(entity);
			if (res == true) {
				response.setMessage("Employee Highest Degree Saved Successfully.");
				response.setStatus(true);

			} else {
				response.setMessage("Failed to Saved Highest Degree.");
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
			response.setMessage("Error while saving  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}
	
	// BU Logic for Fetch Highest Degree.
	public Response fetchDegree(int userId) {
		
		CommonResponseBean response= new CommonResponseBean();
		List<BgHighestDegreeEarnedEntity> listEntity= dao.getDegree(userId);
		if(listEntity!=null) {
			response.setMessage(" Highest Degree Retrived.");
			response.setStatus(true);
			response.setList(listEntity);
		}else {
			response.setMessage(" Failed to Retrived Data.");
			response.setStatus(false);
		}
		
		return Response.status(Response.Status.OK).entity(response).build();
		
	}
	
	// Save Or Update  Employee Ref Simple One .
	public Response saveEmpRefList(BgEmpProfessionalReferenceSimpleBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		BgEmpProfessionalReferneceEntity entity = new BgEmpProfessionalReferneceEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			boolean res = dao.saveUpdateReferenceList(entity);
			if (res == true && bean.getId()> 0) {
				response.setMessage("Employee Reference Updated Successfully.");
				response.setStatus(true);

			} else {
				response.setMessage(" Saved Employee Reference .");
				response.setStatus(true);
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
			response.setMessage("Error while saving  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}
	
	// Save And update Emp History 
	public Response saveUpdate(BgEmployementHistorySimpleBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		BgEmploymentHistoryEntity entity=new BgEmploymentHistoryEntity();
		try {
			BeanUtils.copyProperties(entity, bean);
			boolean res = dao.saveUpdateEmpHis(entity);
			if (res == true && bean.getId() > 0) {
				response.setMessage("Employee History Updated Successfully.");
				response.setStatus(true);

			} else {
				response.setMessage(" Saved Employee History Successfully..");
				response.setStatus(true);
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
			response.setMessage("Error while saving  details - " + e.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();

		}

	}
	

}
