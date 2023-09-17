package com.rainier.services;

import com.rainier.beans.*;
import com.rainier.businesslogic.ListOfValues;
import com.rainier.dto.requestBean.PositionRequestBean;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("/common")
public class HrmsCommonService {

	private static final Logger logger = Logger.getLogger(HrmsCommonService.class);
	private static ListOfValues values = new ListOfValues();

	@Path("getReportingManagerList")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	@GET
	public Response getManagerList(@QueryParam(value = "roleId") int roleId) {

		logger.info("entered into getManagerList method of HrmsCommonService...");
		if (roleId == 0)
			return Response.status(Response.Status.BAD_REQUEST).build();
		else
			return values.reportingManagerList(roleId);
	}

	@Path("/getPositionList")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	@GET
	public Response getPositionsList(@QueryParam(value = "jobTitleId") int jobTitleId) {

		logger.info("entered into getPositionsList service class method..");
		return values.listOfPositions(jobTitleId);
	}

	// for Leave Types service.
	@Path("/GetLeaveType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTypeOfLeaveDetails() {
		logger.info("Entered into getTypeOfLeaveDetails()");
		// EmployeeDetailsSummary eds = new EmployeeDetailsSummary();
		return values.getTypeOfLeaveList();
	}

	// Job Title
	// List----->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	// READ
	@Path("/getJobTitleList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getJobTitleList(@QueryParam("id") int id) {
		logger.info("Entered into getJobTitleList()");
		return values.getJobTitleList(id);

	}

	// CREATE UPDATE
	@Path("addUpdateJobTitle")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addJobTitle(JobTitleBean bean) {
		logger.info("Entered into addJobTitle()");
		return values.addOrUpdateJobTitle(bean);
	}

	// DELETE
	@Path("/deleteJobTitle")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteJobTitle(@QueryParam("id") int id) {
		logger.info("Entered into deleteJobTitle()");
		return values.deleteJobTitle(id);
	}

	// ------>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Job
	// Title List

	// List of Roles
	@Path("/getRolesList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRolesList() {
		logger.info("Entered into getRolesList()");
		return values.getRolesList();

	}

	// Specific Reporting Manager based on the specified User.

	@Path("/getReportingManager")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReportingMgr(@QueryParam(value = "userId") Integer userId) {
		logger.info("Entered into getReportingMgr()");
		return values.getReportingmanager(userId);
	}

	// Get Gender Type.
	@Path("/getGenders")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGenders() {
		return values.getGenders();

	}

	// Delete Gender Type

	@Path("/deleteGenders")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteGenders(@QueryParam("id") int id) {
		logger.info("Entered into deleteGenders::");
		return values.deleteGenders(id);
	}

	// LOV for Marital Status
	@Path("/getMaritalStatus")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMaritalStatus() {
		return values.getMaritalStatus();

	}

	// Leave for Delete Marital Status
	@Path("/deleteMaritalStatus")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMaritalStatus(@QueryParam("id") int id) {
		return values.deleteMaritalStatus(id);

	}

	// LOV for Nationality
	@Path("/getNationality")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNationality() {
		return values.getNationality();
	}

	// LOV for Ethinic Code
	@Path("/getEthinicCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEthinicCode() {
		return values.getEthinicCode();
	}

	// lov For Delete Ethinic Code

	@Path("/deleteEthinicCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEthinicCode(@QueryParam("id") int id) {
		logger.info("Entered into deleteEtthinicCode");
		return values.deleteEthinicCode(id);
	}

	// LOV for Race Code
	@Path("/getRaceCode")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRaceCode() {
		return values.getRaceCode();
	}

	// Leave for delete Race code
	@Path("/deletRaceCodes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletRaceCodes(@QueryParam("id") int id) {
		logger.info("Entered into deleted Race code");
		return values.deletRaceCodes(id);

	}

	// LOV for Languages
	@Path("/getLanguages")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLanguages() {
		return values.getLanguages();
	}

	// Saving Gender Type.
	@Path("/addGender")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveGender(PersonalGenderBean genderBean) {
		return values.addGender(genderBean);

	}

	// Saving Marital Status Type
	@Path("/addMaritalStatus")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response savemarital(PersonalMaritalStatusBean maritalBean) {
		return values.addmarital(maritalBean);

	}

	// Saving Nationality Type.
	@Path("/addNationality")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveNation(PersonalNationalityBean nationBean) {
		return values.addNationality(nationBean);

	}
	// Leave for Delete Nationality Type

	@Path("/deletNationality")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletNationality(@QueryParam("id") int id) {
		logger.info("Entered into deleted Nationality code");
		return values.deletNationality(id);

	}

	// Saving Ethnic Type.
	@Path("/addEthnicCode")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveEthnic(PersonalEthnicCodeBean ethnicBean) {
		return values.addEthnicCode(ethnicBean);

	}

	// Saving Race Type.
	@Path("/addRaceCode")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveRace(PersonalRaceCodeBean raceBean) {
		return values.addRaceCode(raceBean);

	}

	// Saving Language Type.
	@Path("/addLanguage")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveLanguage(personalLanguageBean langBean) {
		return values.addlanguage(langBean);

	}

	// Get Currency Type.
	@Path("/getCurrency")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrency() {
		return values.getCurrency();
	}

	// Delete Currency Type
	@Path("/deleteCurrency")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCurrency(@QueryParam("id") int id) {
		logger.info("Entered into deleted Currency  code");
		return values.deleteCurrency(id);

	}

	// Get payFrequency Type.
	@Path("/getPayFrequency")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPayFrequency() {
		return values.getPayfrequency();
	}

	// Get Account class Type.
	@Path("/getAccountClassType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountclassType() {
		return values.getAccountclassType();
	}

	// Get Bank class Type.
	@Path("/getBankAccountType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getbankAccountType() {
		return values.getBankAccountType();
	}

	// saving currency.
	@Path("/addCurrency")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response savecurrency(SalaryCurrencyBean bean) {
		return values.addcurrency(bean);

	}

	// saving currency.
	@Path("/addPayFrequency")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response savePayFreq(SalaryPayFrequencyBean bean) {
		return values.savePayFrequency(bean);

	}

	// Deleting Account Class Type
	@Path("/deleteAccountClassType")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccountClassType(@QueryParam("id") int id) {
		logger.info("Entered into deleted AccountClassType code");
		return values.deleteAccountClassType(id);

	}

	// saving Account class Type.
	@Path("/addAccountClassType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveAccountClassType(SalaryAccountclassTypeBean bean) {
		return values.saveAccountClassType(bean);
	}

	// saving Bank Account class Type.
	@Path("/addBankAccountType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveBankAccount(SalaryBankAccountTypeBean bean) {
		return values.saveBankAccountType(bean);
	}

	// Visa Types List
	@Path("getVisaTypes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVisaTypes() {
		return values.getVisaTypes();
	}

	// Visa Documents List
	@Path("getVisaDocuments")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getVisaDocuments() {
		return values.getVisaDocuments();
	}

	// Saving Skills
	@Path("/addSkills")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveSkills(SkillsBean bean) {
		return values.saveSkill(bean);

	}

	// Skills List
	@Path("/getSkills")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSkills(@QueryParam(value = "userId") int userId) {
		return values.getSkills(userId);
	}

	// update Skills
	@Path("/updateSkills")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateSkills(SkillsBean bean) {
		return values.updateSkills(bean);

	}

	// Save Update Position Details
	@Path("addUpdatePosition")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUpdatePosition(PositionRequestBean bean) {
		logger.info("Entered into addUpdatePosition()");
		return values.addOrUpdatePositions(bean);
	}

	// Delete Position
	@Path("deletePosition")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePosition(@QueryParam("id") int id) {
		logger.info("Entered into deletePosition()");
		return values.deletePosition(id);
	}

	// update Pay Frequency
	@Path("/updatePayFrequency")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updatePayFreqency(SalaryPayFrequencyBean bean) {
		return values.updatePayFrequency(bean);

	}

	// Delete PayFrequency.
	@Path("/deletePayFrequency")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePayFrequency(@QueryParam("id") int id) {
		logger.info("Entered into deletePosition()");
		return values.deletePayFrequency(id);
	}

	// Hr maqnager List Service.
	@Path("/HrManagerList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response hrManagerList(@QueryParam(value = "businessunitId") int businessunitId) {
		logger.info("Entered into hrManagerList()");
		return values.fetchHRmanager(businessunitId);
	}


	@Path("/hrManagerListByBusinessUnitId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response hrManagerListByBusinessUnitId(@QueryParam(value = "businessunitId") int businessunitId) {
		logger.info("Entered into hrManagerListByBusinessUnitId()");
		return values.getHrManagerIdNameByBusinessId(businessunitId);
	}

	// Super iMM maqnager List Service.
	@Path("/IMMManagerList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response superImmManagerList(@QueryParam(value = "businessunitId") int businessunitId) {
		logger.info("Entered into superImmManagerList()");
		return values.fetchIMMmanager(businessunitId);
	}
	@Path("/immManagerListByBusinessUnitId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response immManagerListByBusinessUnitId(@QueryParam(value = "businessunitId") int businessunitId) {
		logger.info("Entered into immManagerListByBusinessUnitId()");
		return values.getImmManagerIdNameByBusinessId(businessunitId);
	}
	// Reporting Manager List --new Service.
	@Path("/AllReportingManagerList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response allReportingManagerList(@QueryParam(value = "roleId") int roleId,
			@QueryParam(value = "businessunitId") int businessunitId,
			@QueryParam(value = "departmentId") int departmentId) {
		logger.info("Entered into allReportingManagerList()");
		return values.fetchReportingManagerList(roleId, businessunitId, departmentId);
	}

	// DropDown List for HRMANAGER----
	@Path("/HRDropdownList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHrList() {
		logger.info("Entered into getHrList()");

		return values.getHRList();
	}

	// DropDown List for ImmMANAGER----
	@Path("/ImmDropdownList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getImmList() {
		logger.info("Entered into getHrList()");

		return values.getImmList();
	}

}
