package com.rainier.services;

import java.io.InputStream;

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

import com.rainier.beans.BenchSalesAddCandidateBean;
import com.rainier.beans.BenchSalesAddEmployeeBean;
import com.rainier.beans.BenchSalesAddTestimonialsBean;
import com.rainier.beans.BenchSalesRecruiterBean;
import com.rainier.beans.BenchSalesVendorCandidateMappingBean;
import com.rainier.beans.BenchSalesVendorDetailsBean;
import com.rainier.businesslogic.BenchSales;
import com.rainier.dto.requestBean.CandidateSubmissionCount;
import com.rainier.utility.FileUploadUtility;

@Path("/BenchSales")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
public class HrmsBenchSalesService {

	public static Logger logger = Logger.getLogger(HrmsBenchSalesService.class);
	private static BenchSales benchsales = new BenchSales();
	private static FileUploadUtility uoploadPicBechSales = new FileUploadUtility();

	// Save And Update Employee
	@Path("/AddEmployee")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
	@CrossOrigin
	public Response saveAddEmployee(@NotNull @FormDataParam("AddEmp") FormDataBodyPart jsonData,
			@NotNull @FormDataParam("filename") InputStream uploadInputStream,
			@NotNull @FormDataParam("filename") FormDataContentDisposition fileDetails) {
		logger.info("Inside saveAddEmployee()");

		jsonData.setMediaType(MediaType.APPLICATION_JSON_TYPE);

		BenchSalesAddEmployeeBean bean = jsonData.getValueAs(BenchSalesAddEmployeeBean.class);
		if (fileDetails != null) {
			String fileName = fileDetails.getFileName();
			FileUploadUtility uoploadPicBechSales = new FileUploadUtility();

			String uploadedFilePath = uoploadPicBechSales.getUploadPicBenchSales(0, fileName, uploadInputStream);
			if (!uploadedFilePath.equalsIgnoreCase("")) {
				bean.setUploadPic(uploadedFilePath);
			}
			return benchsales.saveAddEmployee(bean);
		} else
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
	}

	// Fetch Employee List service .
	@Path("/EmployeeAddedList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchEmpList() {
		logger.info("Entered Into fetchEmpList() method");
		logger.info("existed Into fetchEmpList() method");
		return benchsales.fetchAddedEmployee();

	}

	// Delete Recruiters Details
	@Path("/DeleteRecruiter")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response deleteRecruitersService(@QueryParam(value = "id") int id) {
		logger.info("Entered in Delete Recruiter Service Method");
		logger.info("Existed in Delete Recruiter Service Method");
		return benchsales.deleteRecruiterDetails(id);

	}

	// Delete Employee List Service
	@Path("/DeleteEmployee")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response deleteEmployeeService(@QueryParam(value = "id") int id) {
		logger.info("Entered in Delete Employee Service Method");
		logger.info("Existed in Delete Employee Service Method");
		return benchsales.deleteEmployeeDetails(id);
	}

	// bench Sales add Recruiters.

	@Path("/AddRecruitersDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response AddRecruitersDetails(BenchSalesRecruiterBean bean) {
		logger.info("Entered Into fetchEmpList() method");
		logger.info("existed Into fetchEmpList() method");
		return benchsales.saveRecruitersName(bean);

	}

	// Fetch recruiters List service .
	@Path("/RecruitersList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getRecruitersList() {
		logger.info("Entered Into fetchEmpList() method");
		logger.info("existed Into fetchEmpList() method");
		return benchsales.fetchAllRecruiters();

	}

	// Save And Update Candidate
	@Path("/AddCandidate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.MULTIPART_FORM_DATA })
	@CrossOrigin
	public Response saveAddCandidate(@NotNull @FormDataParam("AddCandidate") FormDataBodyPart jsonData,
			@NotNull @FormDataParam("filename") InputStream uploadInputStream,
			@NotNull @FormDataParam("filename") FormDataContentDisposition fileDetails) {
		logger.info("Save Candidate");
		jsonData.setMediaType(MediaType.APPLICATION_JSON_TYPE);
		BenchSalesAddCandidateBean bean = jsonData.getValueAs(BenchSalesAddCandidateBean.class);
		if (fileDetails != null) {
			String fileName = fileDetails.getFileName();
			FileUploadUtility fileUploadUtility = new FileUploadUtility();
			String uploadedFilePath = fileUploadUtility.getUploadPicBenchSales(0, fileName, uploadInputStream);
			if (!uploadedFilePath.equalsIgnoreCase("")) {
				bean.setUploadProfile(uploadedFilePath);
			}
			return benchsales.saveAddCandidate(bean);
		} else
			return Response.status(Response.Status.UNSUPPORTED_MEDIA_TYPE).build();
	}

	// Fetch Candidate List Service
	@Path("/retriveCandidateList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchCandidate(@QueryParam(value = "role") int role, @QueryParam(value = "menuId") int menuId) {
		logger.info(" Entered into fetchCandidate() method ");
		return benchsales.retriveAllCandidate(role, menuId);
	}

	// get Candidate Based On The Recruiters That Are Under On that Recruiters
	@GET
	@Path("/candidateListBasedOnRec")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getCandListBasedOnRec(@QueryParam(value = "recId") int recId, @QueryParam(value = "role") int role,
			@QueryParam(value = "menuId") int menuId) {
		logger.info("Entered into getCandListBasedOnRec() ");
		return benchsales.getCandidateBasedOnTheRec(recId, role, menuId);
	}

	// GET Candidate That are Not Related That Recruiters
	@GET
	@Path("/candidateList")
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getCandList(@QueryParam(value = "recId") int recId, @QueryParam(value = "role") int role,
			@QueryParam(value = "menuId") int menuId) {
		logger.info("Entered into getCandListBasedOnRec() ");
		return benchsales.getCandidate(recId, role, menuId);
	}

	// Delete Candidate Service
	@Path("/deleteCandidate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response delCandidate(@QueryParam(value = "id") int id) {
		return benchsales.deleteCandidate(id);

	}

	// Save And Update Testimonilas Service in Service Package
	@Path("/saveUpdateTestimonilas")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveUpdateTestimoninal(BenchSalesAddTestimonialsBean bean) {
		return benchsales.saveUpdateTestimonial(bean);

	}

	// Fetch Testimonials List Service
	@Path("/retriveTestimonialsList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchTestimonilas() {
		logger.info(" Entered into fetchTestimonilas() method ");
		return benchsales.retriveAllTestimonils();
	}

	// Delete Testimonilas Service
	@Path("/DeleteTestimonial")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response deleteTestimonial(@QueryParam(value = "id") int id) {
		return benchsales.deleteTestimonilas(id);

	}

	// Save VendorList
	@Path("/saveVendorLists")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveVendorLists(BenchSalesVendorDetailsBean bean) {
		return benchsales.saveVendorDetails(bean);

	}

	// get VendorList Service Based On VendorName
	@Path("/retriveVendorListBasedonName")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchCandidateBasedOnVenName(@QueryParam(value = "venName") String venName) {
		logger.info(" Entered into fetchVendor() method ");
		return benchsales.retriveAllVendors(venName);
	}

	// Save VendorList
	@Path("/saveVendorList")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveVendorList(BenchSalesVendorDetailsBean bean, @QueryParam(value = "id") int id) {
		return benchsales.saveVendorDetails(bean, id);

	}

	// Update Status Candidate
	@Path("/updateCandidateStatus")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateStatus(BenchSalesVendorCandidateMappingBean bean) {
		return benchsales.updateCandidateStatus(bean);

	}

	// fetch vendor Data based on the candidate id
	@Path("/retriveVendorListByCandidateId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response retriveVendorListBasedOnCandidateId(@QueryParam(value = "id") int id) {
		logger.info(" Entered into fetchVendor() method ");
		return benchsales.retriveAllVendorsBasedOnCandidatateId(id);
	}

	// Update Status VendorList
	@Path("/updateVendorList")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response updateVendorList(BenchSalesVendorDetailsBean bean, @QueryParam(value = "id") int id) {
		return benchsales.updateVendorList(bean, id);

	}

	// Intelligecie Service Based On Vendor word
	@Path("/getVendorList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getVendor(@QueryParam(value = "name") String name, @QueryParam(value = "exName") String exName) {
		logger.info(" Entered into fetchVendor() method ");
		return benchsales.retriveVendors(name, exName);
	}

	// Intelligecie Service Based On Email Executive Super Admin
	@Path("/getVendorEmailList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getVendor(@QueryParam(value = "name") String name) {
		logger.info(" Entered into fetchVendor() method ");
		return benchsales.retriveVendors(name);
	}

	// Based on SaleExecutive We need To Fetch Candidate
	@Path("/retriveCandidateListBasedOnExecutive")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchCandidate(@QueryParam(value = "name") String name) {
		logger.info(" Entered into fetchCandidate() method ");
		return benchsales.retriveAllCandidate(name);
	}

	// Based On The Candidate Id and Recruiters Name All The Vendor List Come
	@Path("/retriveVendorListByCandidateIdAndRecNa")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response retriveVendorListBasedOnCandidateId(@QueryParam(value = "recName") String recName,
			@QueryParam(value = "id") int id) {
		logger.info(" Entered into retriveVendorListBasedOnCandidateId() method ");
		return benchsales.retriveAllVendorsBasedOnCandidatateIdAndRecNa(recName, id);
	}

	// Recruiters Service Under The Candidate Will Come

	@Path("/retriveCandidate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response retriveCandidate() {
		logger.info(" Entered into retriveCandidate() method ");
		return benchsales.getCandidate();
	}

	// Vendor Search Based On VendorName,Phone,EmailAddress,Location
	@Path("/getVendorLists")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getVendorLists(BenchSalesVendorDetailsBean bean) {
		return benchsales.retriveVendorsLists(bean);

	}

	// Candidate Search Based On Candidate Name,Phone,EmailAddress,Location
	@Path("/getCandidateLists")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getCandidateLists(BenchSalesAddCandidateBean bean) {
		return benchsales.retriveCandidateLists(bean);

	}

	// Get All InActive Candidate In This Service
	@Path("/getInActiveCandidateList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getInActiveCandidate() {
		return benchsales.getInActiveCandidateLists();
	}

	// Get Candidate Vendor Status And Date Based On VendorId And Candidate Id
	@Path("/retriveVendorStatusAndDateCandIdVenId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response retriveVendorStatusAndDateCandIdVenId(@QueryParam(value = "canId") int id,
			@QueryParam(value = "venId") int venId) {
		logger.info(" Entered into retriveVendorStatusAndDateCandIdVenId() method ");
		return benchsales.retriveAllVendorsCandidatateStatusDate(id, venId);
	}

	// Get Vendor Count Basd On Candidate Id
	@Path("/retriveVendorCount")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response retriveVendorCount() {
		logger.info(" Entered into retriveVendorCountBasedOnCanId() method ");
		return benchsales.getVendorCount();
	}

	// Vendor Client Submission
	@Path("/vendorClientSubList")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response saveVendorList(BenchSalesVendorDetailsBean bean) {
		return benchsales.vendorclientSub(bean);

	}

	// Based On The Candidate Id and Recruiters Id All The Vendor List Come
	@Path("/retriveVendorListByCandidateIdAndRecId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response retriveVendorListBasedOnCandidateId(@QueryParam(value = "recId") int recId,
			@QueryParam(value = "id") int id) {
		logger.info(" Entered into retriveVendorListBasedOnCandidateId() method ");
		return benchsales.retriveAllVendorsBasedOnCandidatateIdAndRecId(recId, id);
	}

	// getCandidate Submission
	@Path("/getcandidateSubmission")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response candidateSubmission(@QueryParam(value = "recId") int recId) {
		logger.info(" Entered into getcandidateSubmission() method ");
		return benchsales.getcandidateSubmission(recId);
	}

	// getCandidateSubmissionWithInDates
	@Path("/getCandidateSubmissionWithInDates")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getCandidateSubmissionWithInDates(CandidateSubmissionCount candSubCount) {
		logger.info(" Entered into getCandidateSubmissionWithInDates() method ");
		return benchsales.getCandidateSubmissionWithInDates(candSubCount);
	}

	@Path("/getVendorByCandidateId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getVendorByCandidateId(@QueryParam(value = "candId") int candId) {
		logger.info("Entered Into getVendorByCandidateId() method");
		return benchsales.getVendorByCandidateId(candId);

	}

	// Based On SalesManagerId SalesExecutive And UnderCandidate will Come
	@Path("/retriveCandidateListBasedOnSaleManAndExe")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response fetchCandidateList(@QueryParam(value = "role") int role, @QueryParam(value = "menuId") int menuId,
			@QueryParam(value = "saleManId") int saleManId) {
		logger.info(" Entered into fetchCandidateList() method ");
		return benchsales.retriveCandidateBasedOnSalesManagerAndSalesExecutive(role, menuId, saleManId);
	}

	// Based On The Candidate Id and Recruiters Id All The Vendor List Come For
	// AuthoriZation
	@Path("/retriveVendorListByCandidateIdAndRecIdForAuth")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response retriveVendorListBasedOnCandidateIdForAuth(@QueryParam(value = "recId") int recId,
			@QueryParam(value = "id") int id) {
		logger.info(" Entered into retriveVendorListBasedOnCandidateId() method ");
		return benchsales.retriveAllVendorsBasedOnCandidatateIdAndRecIdForAuth(recId, id);
	}

	// Get VendorList With Sales Executive Name
	@Path("/retriveVendorListwithSalExe")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response retriveVendorWithSaleExe() {
		logger.info(" Entered into retriveVendorWithSaleExe() method ");
		return benchsales.retriveAllVendorWithSalesExeName();
	}

	// Through This Service We Are Making InActive Candidate To Active Mode
	@Path("/activeCandidate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response activeCandidate(@QueryParam(value = "id") int id) {
		logger.info(" Entered into activeCandidate() method ");
		return benchsales.activeCandidate(id);

	}

	// Get States List service .
	@Path("/stateList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getStatesList() {
		logger.info("Entered Into getStatesList() method");
		return benchsales.getAllStates();

	}

	// Get Cities List service .
	@Path("/cityList")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getCitiesList(@QueryParam("stateId") int stateId) {
		logger.info("Entered Into getCitiesList() method");
		return benchsales.getAllCities(stateId);

	}

	// Based On The SalesManId The SalesExecutive List Will Come
	@Path("/retriveSalesExeBasedOnSalManId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response retriveSalesExeBasedOnSalManId(@QueryParam(value = "salManId") int salManId) {
		logger.info(" Entered into retriveSalesExeBasedOnSalManId() method ");
		return benchsales.listOfSalesExecutive(salManId);
	}
	
	// get All SalesExceutive And Candidate Based On SalManagerId
	@Path("/retriveCandidateAndSalExeBasedOnSalmanId")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response retriveCandidate(@QueryParam("salManId")int salManId) {
		logger.info(" Entered into retriveCandidateSalesExecutive() method ");
		return benchsales.getSalesExeCandidate(salManId);
	}
}
