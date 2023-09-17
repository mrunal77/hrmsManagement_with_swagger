package com.rainier.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.rainier.beans.BenchSalesAddCandidateBean;
import com.rainier.beans.BenchSalesAddEmployeeBean;
import com.rainier.beans.BenchSalesAddTestimonialsBean;
import com.rainier.beans.BenchSalesVendorCandidateMappingBean;
import com.rainier.beans.BenchSalesVendorDetailsBean;
import com.rainier.beans.CandSubmissionBean;
import com.rainier.dto.requestBean.CandidateSubmissionCount;
import com.rainier.dto.responseBean.BenchSalesVendorDetailsEntityResponse;
import com.rainier.dto.responseBean.CandidateRecruitersResponse;
import com.rainier.dto.responseBean.CandidateResponseObject;
import com.rainier.dto.responseBean.CandidateSalesManagerResponse;
import com.rainier.dto.responseBean.CityStateResponseBean;
import com.rainier.dto.responseBean.VendorSalesExecutiveResponseBean;
import com.rainier.entities.BenchSalesAddCandidateEntity;
import com.rainier.entities.BenchSalesAddEmployeeEntity;
import com.rainier.entities.BenchSalesAddTestimonialsEntity;
import com.rainier.entities.BenchSalesMailLogEntity;
import com.rainier.entities.BenchSalesRecruiterEntity;
import com.rainier.entities.BenchSalesVendorCandidateMappingEntity;
import com.rainier.entities.BenchSalesVendorDetailsEntity;
import com.rainier.entities.Privileges;
import com.rainier.entities.Tbl_CitiesEntity;
import com.rainier.entities.User;

public interface HrmsBenchSalesDao {

	public boolean saveUpdateAddEmployee(BenchSalesAddEmployeeBean bean);

	// for BenchSales mail Log Dao.
	public void sendingMailByBenchSalesAdmin(BenchSalesMailLogEntity entity);

	// fetch Added Employee
	public List<BenchSalesAddEmployeeEntity> fetchAddedEmployee();

	// Delete Service For Employee
	public String deleteEmployee(int id);

	// Add Recruiter Name.
	public boolean addRecruiterName(BenchSalesRecruiterEntity entity);

	// Fetch Recruiter List Service

	public List<BenchSalesRecruiterEntity> getAllRecruiters();

	// Delete Service For Recruiters
	public int deleteRecruiters(int id);

	// Save Update Candidate List Service

	public boolean saveUpdateCandidate(BenchSalesAddCandidateBean bean);

	// Fetch Candidate List Service

	public List<BenchSalesAddCandidateBean> retriveCandidateList();

	// Delete Candidate List Service

	public void deleteCandidate(int id);

	// Save And Update Testimonials List Service
	public boolean saveUpdateTestimonials(BenchSalesAddTestimonialsBean bean);

	// Fetch Testimonials Service
	public List<BenchSalesAddTestimonialsEntity> fetchTestimonilasList();

	// Delete Testimonilas List Service
	public boolean deleteTestimonials(int id);

	// get Candidate Based On The Recruiters That Are Under On that Recruiters
	public Set<CandidateRecruitersResponse> getCandidateList(int recId);

	// GET Candidate That are Not Related That Recruiters
	public Set<CandidateRecruitersResponse> getCandidate(int recId);

	// get Candidate Privilieges Basd On The RoleId
	public Privileges getPriviligesForCand(int id, int menuId);

	// Save VendorList Service
	public int saveVendorList(BenchSalesVendorDetailsBean bean);

	// get VendorList Service Based On VendorName
	public List<BenchSalesVendorDetailsEntity> getVendorList(String venName);

	// Add Candidate Vendor Service
	public int addCandidateVendor(BenchSalesVendorDetailsBean bean, int id);

	// Update Status Of Candidate
	public boolean updateStatusCandidate(BenchSalesVendorCandidateMappingBean bean);

	// fetch vendor Data based on the candidate id
	public List<Object[]> listOfVendorBasedonCandidateId(int id);

	// Update Vendor Details Based On Vendor id
	public boolean updateVendorList(BenchSalesVendorDetailsBean bean, int id);

	// Intelligecie Service Based On get Vendor Email Exe
	public List<BenchSalesVendorDetailsEntity> getvendorBasedOnFirstAlphabet(String name, String exName);

	// Intelligecie Service Based On get Vendor Email Super
	public List<BenchSalesVendorDetailsEntity> getvendorBasedOnFirstAlphabet(String name);

	// Based On The Candidate Id and Recruiters Name All The Vendor List Come
	public List<Object[]> listOfVendorBasedonCandidateIdAndRecName(String recName, int id);

	// Based On The Candidate Id and Recruiters Id All The Vendor List Come
	public Set<BenchSalesVendorDetailsBean>  listOfVendorBasedonCandidateIdAndRecId(int recId, int id);

	// Based On The SalesManId The SalesExecutive List Will Come
	public List<User> listOfSalesExecutive(int salManId);

	// Based On The Candidate Id and Recruiters Id All The Vendor List Come For
	// AuthoriZation
	public List<Object[]> listOfVendorBasedonCandidateIdAndRecIdForAuth(int recId, int id);

	// Based on SaleExecutive We need To Fetch Candidate
	public List<BenchSalesAddCandidateEntity> getCandidatesBasedOnExecutive(String name);

	// Recruiters Service Under The Candidate Will Come
	public List<CandidateResponseObject> getCandidateUnderTheRecruites();

	// Vendor Search Based On VendorName,Phone,EmailAddress,Location
	public List<BenchSalesVendorDetailsEntity> getVendorLists(BenchSalesVendorDetailsBean bean);

	// Candidate Search Based On CandidateName,Phone,EmailAddress,Location
	public List<BenchSalesAddCandidateEntity> getCandidateLists(BenchSalesAddCandidateBean bean);

	// Get All InActive Candidate In This Service
	public List<BenchSalesAddCandidateEntity> getInActiveCandidateList();

	// Get Candidate Vendor Status And Date Based On VendorId And Candidate Id
	public List<Object[]> getStatusDate(int canId, int venId);

	// Get Vendor Count Based On Candidate Id
	public List<ArrayList<BigInteger>> getVendorCount();

	// Vendor Client Submission Service
	public boolean vendorClientCandidateSub(BenchSalesVendorDetailsBean bean);

	// Get SalesExecutive Based On Id
	public User getRecruiterEmailById(int recId);

	// Get List Of SalesExecutive Based On List Of Id
	public List<User> getRecruiterEmailListByIdList(List<Integer> idList);

	// Get Super Admin Mail
	public String getSuperAdminEmail();

	// Get Candiadte By Id
	public BenchSalesAddCandidateEntity getCandidateEntityById(int Id);

	// getting email by id from BenchSalesAddCandidateEntity table;
	public String getCandidateEmailById(int id);

	// getting email by using createdby from BenchSalesVendorDetailsEntity table
	public String getCandidateEmailByCreatedby(int createdBy);

	// get ReportingManagerEmailId Based On SalesExeId
	public String getReportingManagerMail(int salId);

	// Based On SalesManagerId SalesExecutive And UnderCandidate will Come
	public Set<CandidateSalesManagerResponse> retriveCandidateBasedOnSalesManagerAndSalesExecutive(int saleManId);

	public List<CandidateResponseObject> getAllCandidatesAndRecruiters();

	public List<CandSubmissionBean> getCandidateSubmissionDetails(int recId);

	public List<CandSubmissionBean> getCandidateSubmissionWithInDates(CandidateSubmissionCount candSubCount);

	public List<BenchSalesVendorDetailsEntityResponse> getVendorByCandidateId(int recId);

	// Get VendorList With Sales Executive Name
	public List<VendorSalesExecutiveResponseBean> getVendorListWithSaleExecutiveName();

	// Through This Service We Are Making InActive Candidate To Active Mode
	public void activeCandidate(int id);

	// Get All States
	public List<CityStateResponseBean> getAllStates();

	// Get All Cities Based On State Id
	public List<CityStateResponseBean> getAllCities(int id);

	// Validation For Candidate Submission With Organisation ClientName And Location

	public int checkWithCandIdThatOrgClieLoc(String organisationName, String clientName, String loc);

	// get All SalesExceutive And Candidate Based On SalManagerId
	public List<CandidateResponseObject> getSaleExeAndCanBasedOnSalManId(int salManId);

}
