package com.rainier.services;

import com.rainier.beans.*;
import com.rainier.businesslogic.PersonalInfo;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("/Personal")
public class HrmsPersonalInfoService {
	final static Logger logger = Logger.getLogger(HrmsPersonalInfoService.class);
	private static PersonalInfo info = new PersonalInfo();

	// for saving job history details.
	@Path("/saveJobHistory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveJobHistory(JobHistoryBean bean) {
		return info.saveJobHistory(bean);
	}

	// for updating job history.
	@Path("/updateJobHistory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateJobHistory(JobHistoryBean bean) {
		return info.updateJobHistory(bean);

	}

	// get job history list.
	@Path("/getJobHistory")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getJobHistory(@QueryParam(value="userId")int userId) {
		return info.getJobHistory(userId);
	}

	// for saving Exp details.
	@Path("/saveExperienceDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveExpDetails(ExperienceBean bean) {
		return info.addExperience(bean);
	}

	// get Exp Details.
	@Path("/getExpDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getExpDetails(@QueryParam(value = "userId") int userId) {
		return info.getExpDetails(userId);
	}

	// for updating Exp.
	@Path("/updateExpDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateExpDetails(ExperienceBean bean) {
		return info.updateExp(bean);
	}

	// add Education level
	@Path("/saveEducationLevel")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveEducationLevel(EducationLevelCodeBean bean) {
		return info.addEducationLevel(bean);
	}

	// get Edu Level code Details.
	@Path("/getEducationLevelCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getEduLevelCode() {
		return info.getEducationLevel();
	}

	// add Education level
	@Path("/saveEmpEducationDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveEmpEducationDetails(EmpEducationDetailsBean bean) {
		return info.saveEmpEducation(bean);
	}

	// get Edu Level code Details.
	@Path("/getEmpEducationDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getEmpEduDetails(@QueryParam(value = "userId") int userId) {
		return info.fetchEmpEduDetails(userId);
	}

	// Update Emp Education Details Services.

	@Path("/updateEmpEducationDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateEmpEducation(EmpEducationDetailsBean bean) {
		return info.updateEmpEducationDetails(bean);
	}

	// save or update Training Certification Services.

	@Path("/saveOrUpdateTrainingCertificate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveTrainingCertificate(Training_CertificationBean bean)
			throws IllegalAccessException, InvocationTargetException {
		return info.saveTrainingCertification(bean);
	}

	// get Training & certification Details.
	@Path("/getTrainingCertificationDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getEmpTrainingCertifications(@QueryParam(value = "userId") int userId) {
		return info.getTrainingCertificate(userId);
	}

	// save or update Disability Services.

	@Path("/saveOrUpdateDisability")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveOrUpdateDisability(DisabilityBeans bean) {
		return info.saveOrUpdateEmployeeDisability(bean);
	}

	// get Disability Details.
	@Path("/getDisabilityDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getDisabilityDetails(@QueryParam(value = "userId") int userId) {
		return info.fetchDisabilityDetails(userId);
	}

	// save or update Medical Claim Services.

	@Path("/saveOrUpdateMedicalClaim")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveOrUpdateMedicalClaim(MedicalClaimsInjuryBean bean) {
		return info.saveOrUpdateMedicalClaims(bean);
	}

	// get Medical Details.
	@Path("/getMedicalDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getMedicalClaimDetails(@QueryParam(value = "userId") int userId) {
		return info.fetchMedicalClaims(userId);
	}

	// save or update Dependency details Services.

	@Path("/saveOrUpdateDependencyDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveOrUpdateDependencyDetails(DependencyDetailsBean bean) {
		return info.saveOrUpdateDependencyDetails(bean);
	}

	// get Medical Details.
	@Path("/getDependecyDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getDependencyDetails(@QueryParam(value = "userId") int userId) {
		return info.fetchDependentList(userId);
	}

	// save or update Dependency details Services.

	@Path("/saveOrUpdateVisaImmigration")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveOrUpdateVisaimmigrationDetails(VisaAndImmigrationBean bean) {
		return info.saveOrupdatevisaDetails(bean);
	}

	// get Visa immigration Details Services.

	@Path("getVisaImmigration")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getVisaImmigrationDetails(@QueryParam(value = "userId") int userId) {
		return info.fetchVisaimmigrationDetails(userId);

	}
}
