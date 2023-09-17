package com.rainier.dao;

import java.util.List;

import com.rainier.dto.requestBean.PositionRequestBean;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.EmployeeLeaveTypeEntity;
import com.rainier.entities.EthinicCodeEntity;
import com.rainier.entities.GenderEntity;
import com.rainier.entities.HolidaysEntity;
import com.rainier.entities.JobTitlesEntity;
import com.rainier.entities.LanguageEntity;
import com.rainier.entities.MaritalStatusEntity;
import com.rainier.entities.NationalityEntity;
import com.rainier.entities.PositionEntity;
import com.rainier.entities.RaceCodeEntity;
import com.rainier.entities.SalaryAccountClassTypeEntity;
import com.rainier.entities.SalaryBankAccountEntity;
import com.rainier.entities.SalaryPayfrequencyEntity;
import com.rainier.entities.SalarycurrencyEntity;
import com.rainier.entities.SkillsEntity;
import com.rainier.entities.UserRole;
import com.rainier.entities.VisaDocumentsEntity;
import com.rainier.entities.VisaTypeEntity;
import com.rainier.response.HrManagerIdNameResponse;
import com.rainier.response.ImmManagerIdNameResponse;

public interface HrmsListOfValuesDao {
	// EmployeeId Auto Generation
    String EmployeeId();

	// List of positions
    List<PositionEntity> listOfPositions(int jobTitleId);

	// Add Update Positions in Table
    boolean saveUpdatePositions(PositionRequestBean entity);

	// Reporting Manager List
    List<EmployeeDetailsEntity> reportingManagerList(int roleId);

	// for leave Types by Organization
    List<EmployeeLeaveTypeEntity> getLeaveType();

	// Job Title List
    List<JobTitlesEntity> getJobTitleList(int id);

	// List Of Roles
    List<UserRole> getRolesList();

	/*
	 * -----------------------------------------------------------------------------
	 * ----------------------------
	 */

	// for getting casual Leave and Sick Leave.
    List<Object[]> getAvailableLeaves(Integer userid);

	// List of ReportingManager based on userId.
    List<EmployeeDetailsEntity> getReportManagerBasedOnUserId(Integer userId);

	/*
	 * -----------------------------------------------------------------------------
	 * ----------------------------
	 */

	// List of Gender
    List<GenderEntity> getGenders();
    
    //deleting Gender
    boolean deleteGenders(int id);

	// List of marital Status
    List<MaritalStatusEntity> getMaritalStatus();
    
    //Deleting Marital Status
    boolean deleteMaritalStatus(int id);

	// List of Nationality
    List<NationalityEntity> getNationality();
    
//Delete of Nationality
    boolean deletNationality(int id);
    
	// List of EthinicCode
    List<EthinicCodeEntity> getEthinicCode();
    
    //deleting Ethinic code 
    boolean deleteEthinicCode(int id);
  


	// List of Race Code
    List<RaceCodeEntity> getRaceCodes();
    
    //delete of race code
    boolean deletRaceCodes(int id);

	// List of languages
    List<LanguageEntity> getLanguages();

	/*
	 * -----------------------------------------------------------------------------
	 * ----------------------------
	 */

	// Saving Personal gender type.
    String saveGenderType(GenderEntity genderType);

	// Saving marital Status .
    String saveMaritalStatus(MaritalStatusEntity maritalType);

	// Saving natianlity.
    String saveNationality(NationalityEntity nationType);

	// Saving Ethnic code.
    String saveEthnicCode(EthinicCodeEntity ethnicType);
    

	// Saving Race code.
    String saveRaceCode(RaceCodeEntity raceType);

	// Saving Race code.
    String saveLanguage(LanguageEntity langType);

	/*
	 * -----------------------------------------------------------------------------
	 */

	// List of salary Currency
    List<SalarycurrencyEntity> getCurrenncy();
    
    // Delete of Salary Currency
    boolean deleteCurrency(int id);

	// List of salary Currency
    List<SalaryPayfrequencyEntity> getPayrequency();

	// List of salary Currency
    List<SalaryAccountClassTypeEntity> getAccountClassType();

	// List of salary Currency
    List<SalaryBankAccountEntity> getBankAccount();

	/*
	 * -----------------------------------------------------------------------------
	 * ----------------------------
	 */

	// List of Visa Types
    List<VisaTypeEntity> getVisaType();

	// List of Visa Documents
    List<VisaDocumentsEntity> getVisaDocuments();

	/*
	 * -----------------------------------------------------------------------------
	 * ----------------------------
	 */

	// Saving Currency
    String saveCurrency(SalarycurrencyEntity entity);

	// Saving pay frequenncy
    String savePayFrequency(SalaryPayfrequencyEntity entity);

	// Saving Account class type.
    String saveAccountClassType(SalaryAccountClassTypeEntity entity);
    
    //Delete Account Class Type
    boolean deleteAccountClassType(int id);

	// Saving Bank Account type.
    String saveBankAccount(SalaryBankAccountEntity entity);

	/*
	 * -----------------------------------------------------------------------------
	 * ----------------------------
	 */

	// list of Holidays Group.
    List<HolidaysEntity> getHolidays();

	// Saving Holidays Group.
    String saveHolidays(HolidaysEntity holidays);

	/*
	 * -----------------------------------------------------------------------------
	 * ----------------------------
	 */

	// Saving Skill
    String saveSkills(SkillsEntity skill);

	// list of Skills.
    List<SkillsEntity> getSkills(int userId);

	// Update Skills.
    int updateSkills(SkillsEntity skills);

	/*
	 * -----------------------------------------------------------------------------
	 * ----------------------------
	 */

	boolean saveOrUpdateJobTitles(JobTitlesEntity entity);

	JobTitlesEntity deleteJobTitle(int id);

	PositionEntity deletePosition(int id);

	// update of pay frequency.
    boolean updatePayFrequency(SalaryPayfrequencyEntity entity);

	// delete Pay Frequency.
    boolean deletePayFrequency(int id);
    
    
    // for Immigration Manager List and reporting manager
    List<EmployeeDetailsEntity> getHrManager(int businessunitId);
    
    List<EmployeeDetailsEntity>  getImmManager(int businessunitId);
    
 public List<HrManagerIdNameResponse>getHrManagerByBusinessId(int businessunitId);
 public List<ImmManagerIdNameResponse> getImmManagerByBusinessId(int businessunitId);

    
    // Reporting Manager List---new Service.
    List<EmployeeDetailsEntity> reportingManagerListAddEmployee(int roleId,int businessunitId,int departmentId);
    
    
    // DropDown List for HRMANAGER----
    
    List<Object[]> getDropDownHRList();
    List<Object[]> getDropDownIMMList();
    
    
    
    
    

}
