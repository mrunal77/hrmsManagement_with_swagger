package com.rainier.dao.Impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.rainier.dao.HrmsListOfValuesDao;
import com.rainier.dbconfiguration.DbConnect;
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
import com.rainier.utility.HrmsGetDateAndTime;

public class HrmsListOfValues implements HrmsListOfValuesDao {

	private final static Logger logger = Logger.getLogger(HrmsListOfValues.class);

	@Override
	public String EmployeeId() {
		logger.info("entered into EmployeeId method of Dao implementation class");
		//// DbConnect db = new DbConnect();
		try {
			String hqlMaxId = "select max(Id) from User";
			String hqlEmpId = "select u.employeeId from User u where Id=:uId";
			DbConnect.DbCon().beginTransaction();
			Integer id = (Integer) DbConnect.DbCon().createQuery(hqlMaxId).uniqueResult();
			String employeeId = (String) DbConnect.DbCon().createQuery(hqlEmpId).setParameter("uId", id).uniqueResult();
			DbConnect.DbCon().getTransaction().commit();
			return employeeId;
		} catch (Exception e) {
			logger.info("catch block of EmployeeId method of Dao implementation class::" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("Failed");
			return null;
		} finally {
			DbConnect.DbCon().clear();
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
		}
	}

	public List<PositionEntity> listOfPositions(int jobTitleId) {
		// DbConnect db = new DbConnect();
		String hql = "select p from PositionEntity p, JobTitlesEntity j where p.jobTitleId.id = j.id and p.isActive = 1 and j.isActive =1";
		try {
			if (jobTitleId > 0)
				hql = "select p from PositionEntity p,JobTitlesEntity j where p.jobTitleId.id=" + jobTitleId
						+ "and p.jobTitleId.id = j.id  and p.isActive = 1 and j.isActive = 1";
			DbConnect.DbCon().beginTransaction();
			Query<PositionEntity> query = DbConnect.DbCon().createQuery(hql, PositionEntity.class);
			List<PositionEntity> list = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			return list;
		} catch (Exception e) {
			logger.error("Catch block of listOfPositions :" + e);
			// System.out.println("Failed");
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
		}
	}

	public List<EmployeeDetailsEntity> reportingManagerList(int roleId) {
		logger.info("entered into reportingManagerList method of Dao implementation class");

		// DbConnect db = new DbConnect();
		String hql;
		List<EmployeeDetailsEntity> list;
		try {
			// if (roleId <= 2) {
			/*
			 * if (roleId <= 4) { hql = "from EmployeeDetailsEntity where emprole <=" +
			 * roleId; } else { hql = "from EmployeeDetailsEntity where emprole<" + roleId +
			 * "and department_id=" + deptId + "or department_id = 0"; }
			 */

			/*
			 * if (roleId <= 7) { hql = "from EmployeeDetailsEntity where emprole <=" +
			 * roleId; } else if (roleId == 5) { hql =
			 * "from EmployeeDetailsEntity where emprole in(4,6) and department_id=" +
			 * deptId; } else { hql = "from EmployeeDetailsEntity where emprole<" + roleId +
			 * "and department_id=" + deptId + "or department_id = 0"; }
			 */
			hql = "from EmployeeDetailsEntity";
			DbConnect.DbCon().beginTransaction();
			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);
			list = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			return list;
		} catch (Exception e) {
			logger.info("catch block of reportingManagerList method of Dao implementation class::" + e);
			DbConnect.DbCon().getTransaction().rollback();
			// System.out.println("Failed");
			return null;
		} finally {
			DbConnect.DbCon().clear();
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
		}
	}

	/*
	 * public static void main(String[] args) { // new
	 * HrmsListOfValues().listOfPositions(); // new
	 * HrmsListOfValues().reportingManagerList(3, 1);
	 *
	 * // new HrmsListOfValues().getJobTitleList(); new
	 * HrmsListOfValues().getRolesList(); }
	 */

	// for Leave Type

	public List<EmployeeLeaveTypeEntity> getLeaveType() {
		// DbConnect db = new DbConnect();
		try {
			String hql = "from EmployeeLeaveTypeEntity where isActive=1";
			DbConnect.DbCon().beginTransaction();
			Query<EmployeeLeaveTypeEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeLeaveTypeEntity.class);
			List<EmployeeLeaveTypeEntity> listLeaveType = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrieved leaveType ...");
			return listLeaveType;
		} catch (Exception e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get execute Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// List of Job Titles
	@Override
	public List<JobTitlesEntity> getJobTitleList(int id) {
		// DbConnect db = new DbConnect();
		String hql = "from JobTitlesEntity where isActive=1";
		try {
			if (id > 0)
				hql = "from JobTitlesEntity where isActive=1 and id =" + id;
			DbConnect.DbCon().beginTransaction();
			Query<JobTitlesEntity> query = DbConnect.DbCon().createQuery(hql, JobTitlesEntity.class);
			List<JobTitlesEntity> jobTitlesList = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrieved JobTitles ...");
			return jobTitlesList;
		} catch (Exception e) {
			logger.error("failed to get execute Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// List of Roles
	public List<UserRole> getRolesList() {
		// DbConnect db = new DbConnect();
		try {
			String hql = "from UserRole where isActive=1 and uId != 1";
			DbConnect.DbCon().beginTransaction();
			Query<UserRole> query = DbConnect.DbCon().createQuery(hql, UserRole.class);
			List<UserRole> roleList = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrieved Roles List ...");
			return roleList;
		} catch (Exception e) {
			logger.error("failed to get execute Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}
	}

	@Override
	public List<EmployeeDetailsEntity> getReportManagerBasedOnUserId(Integer userId) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			String hql = "FROM EmployeeDetailsEntity where userId=:userId and isactive=1";
			DbConnect.DbCon().beginTransaction();
			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);
			query.setParameter("userId", userId);
			List<EmployeeDetailsEntity> listOfReportmgr = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Reporting Manager for user." + userId);
			return listOfReportmgr;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("failed to get Execute Query.");
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// Gender List
	public List<GenderEntity> getGenders() {
		// DbConnect db = new DbConnect();
		try {
			String hql = "from GenderEntity ";
			DbConnect.DbCon().beginTransaction();
			Query<GenderEntity> query = DbConnect.DbCon().createQuery(hql, GenderEntity.class);
			List<GenderEntity> genderList = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrieved Gender List ...");
			return genderList;
		} catch (Exception e) {
			logger.error("failed to get execute Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// delete gender
	@Override
	public boolean deleteGenders(int id) {

		try {
			DbConnect.DbCon().beginTransaction();
			GenderEntity delCompleteRow = DbConnect.DbCon().byId(GenderEntity.class).load(id);
			DbConnect.DbCon().delete(delCompleteRow);
			DbConnect.DbCon().getTransaction().commit();

			logger.info("Deleted Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("failed to Execute Hql Query.:" + e);
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {

			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Marital Status
	public List<MaritalStatusEntity> getMaritalStatus() {
		// DbConnect db = new DbConnect();
		try {
			String hql = "from MaritalStatusEntity";
			DbConnect.DbCon().beginTransaction();
			Query<MaritalStatusEntity> query = DbConnect.DbCon().createQuery(hql, MaritalStatusEntity.class);
			List<MaritalStatusEntity> maritalStatusList = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrieved Marital Status List ...");
			return maritalStatusList;
		} catch (Exception e) {
			logger.error("failed to get execute Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// deleting Marital Status
	public boolean deleteMaritalStatus(int id) {
		try {
			DbConnect.DbCon().beginTransaction();
			MaritalStatusEntity delCompleteRow = DbConnect.DbCon().byId(MaritalStatusEntity.class).load(id);
			DbConnect.DbCon().delete(delCompleteRow);
			DbConnect.DbCon().getTransaction().commit();

			logger.info("Deleted Marital Status Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("failed to Execute Hql Query.:" + e);
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {

			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// Nationality
	public List<NationalityEntity> getNationality() {
		// DbConnect db = new DbConnect();
		try {
			String hql = "from NationalityEntity";
			DbConnect.DbCon().beginTransaction();
			Query<NationalityEntity> query = DbConnect.DbCon().createQuery(hql, NationalityEntity.class);
			List<NationalityEntity> nationalityList = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrieved Nationality List ...");
			return nationalityList;
		} catch (Exception e) {
			logger.error("failed to get execute Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Ethinic Code
	public List<EthinicCodeEntity> getEthinicCode() {
		// DbConnect db = new DbConnect();
		try {
			String hql = "from EthinicCodeEntity ";
			DbConnect.DbCon().beginTransaction();
			Query<EthinicCodeEntity> query = DbConnect.DbCon().createQuery(hql, EthinicCodeEntity.class);
			List<EthinicCodeEntity> ethinicCodeList = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrieved Ethinic Code List ...");
			return ethinicCodeList;
		} catch (Exception e) {
			logger.error("failed to get execute Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// delete Ethinic code
	public boolean deleteEthinicCode(int id) {
		try {
			DbConnect.DbCon().beginTransaction();
			EthinicCodeEntity delCompleteRow = DbConnect.DbCon().byId(EthinicCodeEntity.class).load(id);
			DbConnect.DbCon().delete(delCompleteRow);
			DbConnect.DbCon().getTransaction().commit();

			logger.info("Deleted Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("failed to Execute Hql Query.:" + e);
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Race Codes
	public List<RaceCodeEntity> getRaceCodes() {
		// DbConnect db = new DbConnect();
		try {
			String hql = "from RaceCodeEntity ";
			DbConnect.DbCon().beginTransaction();
			Query<RaceCodeEntity> query = DbConnect.DbCon().createQuery(hql, RaceCodeEntity.class);
			List<RaceCodeEntity> raceCodes = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrieved Race Code List ...");
			return raceCodes;
		} catch (Exception e) {
			logger.error("failed to get execute Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Delete Race Code
	public boolean deletRaceCodes(int id) {

		try {
			DbConnect.DbCon().beginTransaction();
			RaceCodeEntity delCompleteRow = DbConnect.DbCon().byId(RaceCodeEntity.class).load(id);
			DbConnect.DbCon().delete(delCompleteRow);
			DbConnect.DbCon().getTransaction().commit();

			logger.info("Deleted Raced code Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("failed to Execute Hql Query.:" + e);
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {

			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// Languages
	public List<LanguageEntity> getLanguages() {
		// DbConnect db = new DbConnect();
		try {
			String hql = "from LanguageEntity ";
			DbConnect.DbCon().beginTransaction();
			Query<LanguageEntity> query = DbConnect.DbCon().createQuery(hql, LanguageEntity.class);
			List<LanguageEntity> languages = query.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrieved Language List ...");
			return languages;
		} catch (Exception e) {
			logger.error("failed to get execute Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Gender Saving.
	@Override
	public String saveGenderType(GenderEntity genderType) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(genderType);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Gender Type added Successfully.");
			return "Successfully inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// Marital Status Saving.
	@Override
	public String saveMaritalStatus(MaritalStatusEntity maritalType) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(maritalType);
			DbConnect.DbCon().getTransaction().commit();
			logger.info(" Marital Status Successfully Inserted. ");
			return "Successfully Inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Exexute hql query.:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status:" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Nationality Saving.
	@Override
	public String saveNationality(NationalityEntity nationType) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(nationType);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Nationality Successfully Inserted.");
			return "Successfully Inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// Ethnic Code saving.
	@Override
	public String saveEthnicCode(EthinicCodeEntity ethnicType) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(ethnicType);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("EthnicCode Successfully Inserted.");
			return "Successfully Inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Saving Race code Type.
	@Override
	public String saveRaceCode(RaceCodeEntity raceType) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(raceType);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("RaceCode Successfully Inserted.");
			return "Successfully Inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// saving Language.
	@Override
	public String saveLanguage(LanguageEntity langType) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(langType);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Language Successfully Inserted.");
			return "Successfully Inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// salary Currency
	@Override
	public List<SalarycurrencyEntity> getCurrenncy() {
		// TODO Auto-generated method stub

		// DbConnect db = new DbConnect();
		try {
			String hql = "From SalarycurrencyEntity ";
			DbConnect.DbCon().beginTransaction();
			Query<SalarycurrencyEntity> query = DbConnect.DbCon().createQuery(hql, SalarycurrencyEntity.class);
			List<SalarycurrencyEntity> list = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully Currency List retrived.");
			return list;
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// salary pay Frequency
	@Override
	public List<SalaryPayfrequencyEntity> getPayrequency() {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			String hql = "From SalaryPayfrequencyEntity ";
			DbConnect.DbCon().beginTransaction();
			Query<SalaryPayfrequencyEntity> query = DbConnect.DbCon().createQuery(hql, SalaryPayfrequencyEntity.class);
			List<SalaryPayfrequencyEntity> list = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully  pay Frequency List retrived.");
			return list;
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Leave for delete Nationality

	@Override
	public boolean deletNationality(int id) {

		try {
			DbConnect.DbCon().beginTransaction();
			NationalityEntity delCompleteRow = DbConnect.DbCon().byId(NationalityEntity.class).load(id);
			DbConnect.DbCon().delete(delCompleteRow);
			DbConnect.DbCon().getTransaction().commit();

			logger.info("Deleted Nationality Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("failed to Execute Hql Query.:" + e);
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {

			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	@Override
	public boolean deleteCurrency(int id) {
		// TODO Auto-generated method stub

		try {
			DbConnect.DbCon().beginTransaction();
			SalarycurrencyEntity delCompleteRow = DbConnect.DbCon().byId(SalarycurrencyEntity.class).load(id);
			DbConnect.DbCon().delete(delCompleteRow);
			DbConnect.DbCon().getTransaction().commit();

			logger.info("Deleted Currency Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("failed to Execute Hql Query.:" + e);
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {

			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	@Override
	public boolean deleteAccountClassType(int id) {
		// TODO Auto-generated method stub

		try {
			DbConnect.DbCon().beginTransaction();
			SalaryAccountClassTypeEntity delCompleteRow = DbConnect.DbCon().byId(SalaryAccountClassTypeEntity.class)
					.load(id);
			DbConnect.DbCon().delete(delCompleteRow);
			DbConnect.DbCon().getTransaction().commit();

			logger.info("Deleted AccountClass Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("failed to Execute Hql Query.:" + e);
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {

			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// salary Account class Type.
	@Override
	public List<SalaryAccountClassTypeEntity> getAccountClassType() {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			String hql = "From SalaryAccountClassTypeEntity ";
			DbConnect.DbCon().beginTransaction();
			Query<SalaryAccountClassTypeEntity> query = DbConnect.DbCon().createQuery(hql,
					SalaryAccountClassTypeEntity.class);
			List<SalaryAccountClassTypeEntity> list = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully  Account class Type List retrived.");
			return list;
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// salary Bank Account type
	@Override
	public List<SalaryBankAccountEntity> getBankAccount() {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			String hql = "From SalaryBankAccountEntity ";
			DbConnect.DbCon().beginTransaction();
			Query<SalaryBankAccountEntity> query = DbConnect.DbCon().createQuery(hql, SalaryBankAccountEntity.class);
			List<SalaryBankAccountEntity> list = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully   Bank Account Type List retrived.");
			return list;
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// saving Currency
	@Override
	public String saveCurrency(SalarycurrencyEntity entity) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Currency added Successfully.");
			return "Successfully inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// saving pay frequency
	@Override
	public String savePayFrequency(SalaryPayfrequencyEntity entity) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Pay frequency added Successfully.");
			return "Successfully inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// saving Account class type
	@Override
	public String saveAccountClassType(SalaryAccountClassTypeEntity entity) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Account class Type added Successfully.");
			return "Successfully inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// saving Bank Type
	@Override
	public String saveBankAccount(SalaryBankAccountEntity entity) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Currency added Successfully.");
			return "Successfully inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status: " + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// List of Visa Types
	public List<VisaTypeEntity> getVisaType() {
		logger.info("entered into getVisaType method of Dao implementation class");
		// DbConnect db = new DbConnect();
		try {
			String hql = "From VisaTypeEntity";
			DbConnect.DbCon().beginTransaction();
			Query<VisaTypeEntity> query = DbConnect.DbCon().createQuery(hql, VisaTypeEntity.class);
			List<VisaTypeEntity> list = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrived Visa Type List.");
			return list;
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// List of Visa Documents
	public List<VisaDocumentsEntity> getVisaDocuments() {
		logger.info("entered into getVisaDocuments method of Dao implementation class");
		// DbConnect db = new DbConnect();
		try {
			String hql = "From VisaDocumentsEntity";
			DbConnect.DbCon().beginTransaction();
			Query<VisaDocumentsEntity> query = DbConnect.DbCon().createQuery(hql, VisaDocumentsEntity.class);
			List<VisaDocumentsEntity> list = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully retrived visa documents list.");
			return list;
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// holidays Listing.
	@Override
	public List<HolidaysEntity> getHolidays() {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			String hql = "From HolidaysEntity where isActive=1";
			DbConnect.DbCon().beginTransaction();
			Query<HolidaysEntity> query = DbConnect.DbCon().createQuery(hql, HolidaysEntity.class);
			List<HolidaysEntity> holiday = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully  Holidays List retrived.");
			return holiday;
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Saving holidays.

	@Override
	public String saveHolidays(HolidaysEntity holidays) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(holidays);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Holidays added Successfully.");
			return "Successfully inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute hql Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// for getting Casual Leave and Sick Leave .

	@Override
	public List<Object[]> getAvailableLeaves(Integer userid) { // TODO Auto-generated method stub

		logger.info("entered into getAvailableLeaves of Dao Implementation class "); // DbConnect db = new DbConnect();

		try {
			String hql = "select (select numberofdays from main_employeeleavetypes where leavetype='casual leave') - "
					+ "(select IFNULL(used_casual_leaves,0) from main_employeeleaves where user_id=:userid1 and alloted_year=:year1) as casualleaves,"
					+ "(select numberofdays from main_employeeleavetypes where leavetype='sick leave') - "
					+ "(select IFNULL(used_sick_leaves,0) from main_employeeleaves where user_id=:userid2 and alloted_year=:year2) as sickleaves";

			DbConnect.DbCon().beginTransaction();
			Query<Object[]> sqlquery = DbConnect.DbCon().createNativeQuery(hql);
			sqlquery.setParameter("userid1", userid);
			sqlquery.setParameter("userid2", userid);
			Calendar cal = Calendar.getInstance();
			int currentYear = cal.get(Calendar.YEAR);
			sqlquery.setParameter("year1", currentYear);
			sqlquery.setParameter("year2", currentYear);
			List<Object[]> availLeavelist = sqlquery.list();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("based on User Id available leaves retieved." + availLeavelist);
			return availLeavelist;
		} catch (Exception e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.info("catch block of  getAvailableLeaves of Dao Implementation class" + e);
			// // System.out.println("failed to execute query:"+e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
		}
	}

	// saving skills.
	@Override
	public String saveSkills(SkillsEntity skill) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().save(skill);
			DbConnect.DbCon().getTransaction().commit();
			logger.info(" Skills added Successfully .");
			return "Successfully inserted.";
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to save entity" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// List of Skills.
	@Override
	public List<SkillsEntity> getSkills(int userId) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			String hql = "From SkillsEntity where userId=:userId";
			DbConnect.DbCon().beginTransaction();
			Query<SkillsEntity> query = DbConnect.DbCon().createQuery(hql, SkillsEntity.class);
			query.setParameter("userId", userId);
			List<SkillsEntity> skills = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully Skills List retrived.");
			return skills;
		} catch (HibernateException e) {
			logger.error("failed to get Execute hql query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} catch (Exception e) {
			logger.error("failed to get execute Query" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}
	// Update skills

	@Override
	public int updateSkills(SkillsEntity skills) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().update(skills);
			logger.info("Updation completed.");
			DbConnect.DbCon().getTransaction().commit();
			return 1;
		} catch (Exception e) {
			logger.error("Conflicts in updating Employee Skills Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return 0;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Save Update JobTitles
	@Override
	public boolean saveOrUpdateJobTitles(JobTitlesEntity entity) {
		// DbConnect db = new DbConnect();
		try {
			logger.info("Insertion   " + "" + "  .");
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			logger.error("Conflicts in updating JobTitlesEntity Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	@Override
	public JobTitlesEntity deleteJobTitle(int id) {
		// DbConnect db = new DbConnect();
		String hql = "update JobTitlesEntity set isActive = 0 where id = " + id + "and isActive = 1 ";
		String hqlJob = "select p from PositionEntity p,JobTitlesEntity j where p.jobTitleId.id=" + id
				+ "and p.jobTitleId.id = j.id  and p.isActive = 1 and j.isActive = 1";
		JobTitlesEntity entity = new JobTitlesEntity();
		try {
			logger.info("Deletion");
			DbConnect.DbCon().beginTransaction();
			if (DbConnect.DbCon().createQuery(hqlJob).getResultList().isEmpty())
				DbConnect.DbCon().createQuery(hql).executeUpdate();
			entity = DbConnect.DbCon().get(JobTitlesEntity.class, id);
			DbConnect.DbCon().getTransaction().commit();
			return entity;
		} catch (Exception e) {
			logger.error("Conflicts in deleting JobTitlesEntity Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return entity;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	@Override
	public boolean saveUpdatePositions(PositionRequestBean bean) {
		// DbConnect db = new DbConnect();
		try {
			logger.info("Insertion Started...");
			DbConnect.DbCon().beginTransaction();
			PositionEntity entity = new PositionEntity();
			entity.setId(bean.getId());
			entity.setPositionName(bean.getPositionName());
			// System.out.println(bean.getJobTitleId());
			int id = bean.getJobTitleId();
			JobTitlesEntity dummy = DbConnect.DbCon().get(JobTitlesEntity.class, id);
			// System.out.println(dummy.getJobTitleName());
			entity.setJobTitleId(dummy);
			entity.setCreatedBy(bean.getCreatedOrModifiedBy());
			entity.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entity.setIsActive(1);
			entity.setModifiedBy(bean.getCreatedOrModifiedBy());
			entity.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Insertion Completed..");
			return true;
		} catch (HibernateException e) {
			logger.error("Conflicts in updating JobTitlesEntity Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	@Override
	public PositionEntity deletePosition(int id) {
		// DbConnect db = new DbConnect();
		String hql = "update PositionEntity set isActive = 0 where id = " + id + "and isActive = 1 ";
		String hqlCheckEmployees = "from EmployeeDetailsEntity where position_id = " + id;
		PositionEntity entity = new PositionEntity();
		try {
			logger.info("Position Deletion");
			DbConnect.DbCon().beginTransaction();
			if (DbConnect.DbCon().createQuery(hqlCheckEmployees).getResultList().isEmpty())
				DbConnect.DbCon().createQuery(hql).executeUpdate();
			entity = DbConnect.DbCon().get(PositionEntity.class, id);
			DbConnect.DbCon().getTransaction().commit();
			return entity;
		} catch (Exception e) {
			logger.error("Conflicts in deleting PositionEntity Details." + e);
			DbConnect.DbCon().getTransaction().rollback();
			return entity;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// update Salary Pay Frequency.
	@Override
	public boolean updatePayFrequency(SalaryPayfrequencyEntity entity) {
		// DbConnect db = new DbConnect();
		logger.info("Pay Frequency isActive: " + entity.getIsactive());
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().update(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Pay Frequency Updated Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query.:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}
	}

	// Deleting Complete row of PayFrequency.
	@Override
	public boolean deletePayFrequency(int id) {
		// TODO Auto-generated method stub
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			SalaryPayfrequencyEntity delCompleteRow = DbConnect.DbCon().byId(SalaryPayfrequencyEntity.class).load(id);
			DbConnect.DbCon().delete(delCompleteRow);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Deleted Successfully.");
			return true;
		} catch (HibernateException e) {
			logger.error("failed to Execute Hql Query.:" + e);
			e.printStackTrace();
			DbConnect.DbCon().getTransaction().rollback();
			return false;
		} finally {
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
			DbConnect.DbCon().clear();
			logger.debug("Session status:" + DbConnect.DbCon().isOpen());
			logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
		}

	}

	// for Hr Manager as Well as Super Manager Deropdown List.
	@Override
	public List<EmployeeDetailsEntity> getHrManager(int businessunitId) {
		DbConnect.DbCon().beginTransaction();
		try {
			// String hql = "from EmployeeDetailsEntity where emprole in(1,2) or emprole=4
			// and businessunitId=:businessunitId and departmentId=:departmentId";

			String hql = "from EmployeeDetailsEntity where emprole in(1,2) or emprole=4 and businessunitId=:businessunitId";
			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);

			query.setParameter("businessunitId", businessunitId);

			List<EmployeeDetailsEntity> listofHRManager = query.list();
			DbConnect.DbCon().getTransaction().commit();

			return listofHRManager;

		} catch (Exception e) {
			logger.error("Catch block of listOfmanagers :" + e);
			// System.out.println("Failed");
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
			// DbConnect.DbCon().close();
			// DbConnect.dbSessionFactory().close();
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<HrManagerIdNameResponse> getHrManagerByBusinessId(int businessunitId) {
		String hql = null;
		List<HrManagerIdNameResponse> listsOfHrIdName = new ArrayList<HrManagerIdNameResponse>();
		try {
			hql = "Select hr_manager_name,user_id from main_employees_summary where emprole in(1,2) or emprole=4 and businessunit_id=:businessunitId";
			List<Object[]> listOfHrIdName = DbConnect.DbCon().createSQLQuery(hql)
					.setParameter("businessunitId", businessunitId).getResultList();
			for (Object[] ob : listOfHrIdName) {
				HrManagerIdNameResponse hrIdName = new HrManagerIdNameResponse();
				String hrName = (String) ob[0];
				int hrId = (int) ob[1];
				hrIdName.setHrManagerId(hrId);
				hrIdName.setHrManagerName(hrName);
				listsOfHrIdName.add(hrIdName);
			}
			return listsOfHrIdName;
		} catch (Exception e) {
			logger.error("Catch block of listOfmanagers :" + e);
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}
	}

	// Imm Manager

	@Override
	public List<EmployeeDetailsEntity> getImmManager(int businessunitId) {
		DbConnect.DbCon().beginTransaction();
		try {
			String hql = "from EmployeeDetailsEntity  where emprole in(1,2) or emprole=3 and businessunitId=:businessunitId";

			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);

			query.setParameter("businessunitId", businessunitId);

			List<EmployeeDetailsEntity> listofIMManager = query.list();
			DbConnect.DbCon().getTransaction().commit();

			return listofIMManager;

		} catch (Exception e) {
			logger.error("Catch block of listOfmanagers :" + e);
			// System.out.println("Failed");
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();

		}
	}

	public List<ImmManagerIdNameResponse> getImmManagerByBusinessId(int businessunitId) {
		List<ImmManagerIdNameResponse> listsOfImMangerIdName = new ArrayList<>();
		try {
			String hql = "Select imm_manager_id,imm_manager_name from main_employees_summary  where  emprole=3 and businessunit_id=:businessunitId";
			@SuppressWarnings({ "deprecation", "unchecked" })
			List<Object[]> listsOfImmMangerIdNa = DbConnect.DbCon().createSQLQuery(hql)
					.setParameter("businessunitId", businessunitId).getResultList();
			for (Object[] ob : listsOfImmMangerIdNa) {
				ImmManagerIdNameResponse imIdName = new ImmManagerIdNameResponse();
				String id = (String) ob[0];
				String name = (String) ob[1];
				imIdName.setImmManagerId(id);
				imIdName.setImmManagerName(name);
				listsOfImMangerIdName.add(imIdName);
			}

			return listsOfImMangerIdName;

		} catch (Exception e) {
			logger.error("Catch block of listOfmanagers :" + e);
			return null;
		} finally {
			DbConnect.DbCon().clear();

		}
	}
	// Reporting Manager List---new Service.

	@Override
	public List<EmployeeDetailsEntity> reportingManagerListAddEmployee(int roleId, int businessunitId,
			int departmentId) {
		DbConnect.DbCon().beginTransaction();
		String hql = null;
		try {

			if (roleId <= 4) {
				hql = "from EmployeeDetailsEntity where emprole = " + roleId
						+ " && ( businessunitId=: businessunitId  ||  businessunitId=0 ) && (departmentId = : departmentId  || departmentId=0)";
				hql.replaceAll(":", "");

				System.out.println(hql);
			} else if (roleId == 5) {

				hql = "from EmployeeDetailsEntity where emprole = " + roleId
						+ " && ( businessunitId= : businessunitId ) && (departmentId = : departmentId )";
				hql.replaceAll(":", "");
				System.out.println(hql);
			}

			Query<EmployeeDetailsEntity> query = DbConnect.DbCon().createQuery(hql, EmployeeDetailsEntity.class);

			query.setParameter("businessunitId", businessunitId);
			query.setParameter("departmentId", departmentId);

			List<EmployeeDetailsEntity> listofReportMgr = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();

			return listofReportMgr;

		} catch (Exception e) {
			logger.error("Catch block of listOfmanagers :" + e);

			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();

		}
	}

	// DropDown List for HRMANAGER----
	@Override
	public List<Object[]> getDropDownHRList() {
		try {
			DbConnect.DbCon().beginTransaction();
			String hql = "select distinct(hr_manager_name) from main_employees_summary where hr_manager_name=hr_manager_name";
			@SuppressWarnings({ "deprecation", "unchecked" })
			Query<Object[]> query = DbConnect.DbCon().createSQLQuery(hql);
			List<Object[]> listOfHR = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Hr Manager List Retrived.");
			return listOfHR;

		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

	@Override
	public List<Object[]> getDropDownIMMList() {

		try {
			DbConnect.DbCon().beginTransaction();
			String hql = "SELECT distinct(imm_manager_name) FROM main_employees_summary where imm_manager_name=imm_manager_name;";
			@SuppressWarnings({ "deprecation", "unchecked" })
			Query<Object[]> query = DbConnect.DbCon().createSQLQuery(hql);
			List<Object[]> listOfImm = query.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Hr Manager List Retrived.");
			return listOfImm;

		} catch (HibernateException e) {
			logger.error("Failed to Execute Hql Query:" + e);
			DbConnect.DbCon().getTransaction().rollback();
			return null;
		} finally {
			DbConnect.DbCon().clear();
		}

	}

}
