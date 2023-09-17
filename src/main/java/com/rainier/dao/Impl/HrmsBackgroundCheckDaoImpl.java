package com.rainier.dao.Impl;

import com.rainier.beans.BgEmploymentHistoryBean;
import com.rainier.dao.HrmsBackgroundCheckDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

import java.util.List;

import static com.rainier.dbconfiguration.DbConnect.DbCon;

public class HrmsBackgroundCheckDaoImpl implements HrmsBackgroundCheckDao {

	private static final Logger logger = Logger.getLogger(HrmsBackgroundCheckDao.class);

	/*
	 * public int addHighestDegreeEarned(BgHighestDegreeEarnedBean bean) { //
	 * DbConnect db = new DbConnect(); try { // List<BgHighestDegreeEarnedEntity>
	 * listEntity = new // ArrayList<BgHighestDegreeEarnedEntity>(); String[] other
	 * = bean.getOther(); String[] otherInstituteName =
	 * bean.getOtherDegreeInstituteName(); String[] otherYOP =
	 * bean.getOtherDegreeYOP(); String[] otherNOY = bean.getOtherDegreeNOY();
	 * String res = ""; String res1 = ""; String res2 = ""; String res3 = "";
	 * BgHighestDegreeEarnedEntity entity = new BgHighestDegreeEarnedEntity(); for
	 * (String value : other) { // System.out.println(value); res = res + value +
	 * ","; } for (String value : otherInstituteName) { res1 = res1 + value + ",";
	 * // System.out.println(value); } for (String value : otherYOP) { res2 = res2 +
	 * value + ","; // System.out.println(value); } for (String value : otherNOY) {
	 * res3 = res3 + value + ","; // System.out.println(value); }
	 *
	 * entity.setOther(res); entity.setOtherDegreeInstituteName(res1);
	 * entity.setOtherDegreeYOP(res2); entity.setOtherDegreeNOY(res3); //
	 * System.out.println(entity.getOther());
	 * entity.setBachlorsDegree(bean.getBachlorsDegree());
	 * entity.setBachlorsDegreeInstituteName(bean.getBachlorsDegreeInstituteName());
	 * entity.setBachlorsDegreeNOY(bean.getBachlorsDegreeNOY());
	 * entity.setBachlorsDegreeYOP(bean.getBachlorsDegreeYOP());
	 * entity.setDiploma_HsscDegree(bean.getDiploma_HsscDegree());
	 * entity.setDiploma_HsscDegreeInstituteName(bean.
	 * getDiploma_HsscDegreeInstituteName());
	 * entity.setDiploma_HsscDegreeNOY(bean.getDiploma_HsscDegreeNOY());
	 * entity.setDiploma_HsscDegreeYOP(bean.getDiploma_HsscDegreeYOP());
	 * entity.setMasterDegree(bean.getMasterDegree());
	 * entity.setMasterDegreeInstituteName(bean.getMasterDegreeInstituteName());
	 * entity.setMasterDegreeNOY(bean.getMasterDegreeNOY());
	 * entity.setMasterDegreeYOP(bean.getMasterDegreeYOP());
	 * entity.setUserId(bean.getUserId()); entity.setId(bean.getId());
	 *
	 * DbConnect.DbCon().beginTransaction(); DbConnect.DbCon().saveOrUpdate(entity);
	 * DbConnect.DbCon().getTransaction().commit(); return 1; } catch
	 * (HibernateException e) { DbConnect.DbCon().getTransaction().rollback();
	 * logger.error("failed to get Execute hql query." + e); return 0; } catch
	 * (Exception e) { DbConnect.DbCon().getTransaction().rollback();
	 * logger.error("failed to get Execute hql query." + e); return 0; } finally {
	 * System.out.println("Session status: " + DbConnect.DbCon().isOpen());
	 * System.out.println("Session Factory Status: " +
	 * DbConnect.dbSessionFactory().isOpen()); // db.DbCon().close(); //
	 * db.dbSessionFactory().close(); // System.out.println("Session status: " +
	 * DbConnect.DbCon().isOpen()); // System.out.println("Session Factory Status: "
	 * + // DbConnect.dbSessionFactory().isOpen()); } }
	 */

	public boolean saveHighestDegreeEarned(BgHighestDegreeEarnedEntity entity) {
		boolean flag;

		try {

			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Saved Successfully.");
			flag = true;

		} catch (HibernateException e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			flag = false;
		} finally {
			DbCon().clear();
		}
		return flag;

	}

	// fetch Highest Degree....

	@Override
	public List<BgHighestDegreeEarnedEntity> fetchHighestDegree(int userId) {
		// DbConnect db = new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			String hql = "from BgHighestDegreeEarnedEntity where  userId=:userId";
			List<BgHighestDegreeEarnedEntity> listOfHighestDegree = DbConnect.DbCon()
					.createQuery(hql, BgHighestDegreeEarnedEntity.class).setParameter("userId", userId).getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("list of Highest Degree retrieved Successfully.");
			return listOfHighestDegree;
		} catch (Exception e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to execute hql query.");
			return null;
		} finally {
			DbCon().clear();
		}
	}

	// save emp personal info for background verification.
	public boolean saveEmpPersonalInfo(BgEmpPersonalInfoEntity entity) {
		// DbConnect db= new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully Added or updated.");
			return true;
		} catch (Exception e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			return false;
		} finally {
			logger.info("Session status: " + DbConnect.DbCon().isOpen());
			logger.info("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
			// System.out.println("Session status: " + DbConnect.DbCon().isOpen());
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
			DbCon().clear();
		}
	}

	// Employment History Details Saving.
	@Override
	public boolean saveEmploymentHistory(BgEmploymentHistoryEntity entity) {
		// DbConnect db= new DbConnect();
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Successfully Added or updated.");
			return true;

		} catch (Exception e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			return false;
		} finally {
			// System.out.println("Session status: " + DbConnect.DbCon().isOpen());
			// System.out.println("Session Factory Status: " +
			// DbConnect.dbSessionFactory().isOpen());
			DbCon().clear();
		}
	}

	// professional reference.
	public boolean saveProfessionalreference(BgEmpProfessionalReferneceEntity entity) {
		boolean flag;
		try {

			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			logger.info("Saved Successfully.");
			DbConnect.DbCon().getTransaction().commit();
			flag = true;
		} catch (HibernateException e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			flag = false;
		} finally {
			DbCon().clear();
		}
		return flag;
	}

	// save Employment gap. or Education gap.

	@Override
	public boolean saveEmpEmploymentorEducationGap(BgEmpEmploymentGapEntity entity) {

		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().save(entity);
			logger.info("Saved Successfully.");
			DbConnect.DbCon().getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			return false;
		} finally {
			DbCon().clear();
		}

	}
	// fetch Employment Gap ...

	public List<BgEmpEmploymentGapEntity> getEmpEmploymentGap(int userId) {
		try {
			DbConnect.DbCon().beginTransaction();
			String hql = "from BgEmpEmploymentGapEntity where userId=:userId";
			List<BgEmpEmploymentGapEntity> gapEntity = DbConnect.DbCon()
					.createQuery(hql, BgEmpEmploymentGapEntity.class).setParameter("userId", userId).getResultList();
			DbConnect.DbCon().getTransaction().commit();
			return gapEntity;
		} catch (Exception e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			return null;
		} finally {
			DbCon().clear();
		}

	}

	// fetch professional Reference Saving for particular Employee.
	@Override
	public List<BgEmpProfessionalReferneceEntity> getProfessionalReference(int userId) {
		try {
			DbConnect.DbCon().beginTransaction();
			String hql = "from BgEmpProfessionalReferneceEntity where userId=:uId";
			List<BgEmpProfessionalReferneceEntity> gapEntity = DbConnect.DbCon()
					.createQuery(hql, BgEmpProfessionalReferneceEntity.class).setParameter("uId", userId)
					.getResultList();
			DbConnect.DbCon().getTransaction().commit();
			return gapEntity;
		} catch (Exception e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			return null;
		} finally {
			DbCon().clear();
		}

	}

	// fetch Employment History Details .

	@Override
	public List<BgEmploymentHistoryEntity> getEmploymentHistory(int userId) {
		try {
			DbConnect.DbCon().beginTransaction();
			String hql = "from BgEmploymentHistoryEntity where userId=:userId";
			List<BgEmploymentHistoryEntity> gapEntity = DbConnect.DbCon()
					.createQuery(hql, BgEmploymentHistoryEntity.class).setParameter("userId", userId).getResultList();
			DbConnect.DbCon().getTransaction().commit();
			return gapEntity;
		} catch (Exception e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			return null;
		} finally {
			DbCon().clear();
		}

	}

	// for Emp Personal Info fetch.....
	@Override
	public List<BgEmpPersonalInfoEntity> getEmpPersonalInfo(int userId) {
		try {
			DbConnect.DbCon().beginTransaction();
			String hql = "from BgEmpPersonalInfoEntity where userId=:userId";
			List<BgEmpPersonalInfoEntity> gapEntity = DbConnect.DbCon().createQuery(hql, BgEmpPersonalInfoEntity.class)
					.setParameter("userId", userId).getResultList();
			DbConnect.DbCon().getTransaction().commit();
			return gapEntity;
		} catch (Exception e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			return null;
		} finally {
			DbCon().clear();
		}
	}

	// Work Details History.....

	public boolean saveEmpWorkedHistory(BgEmploymentHistoryEntity entity) {
		boolean flag;
		try {
			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().save(entity);

			DbConnect.DbCon().getTransaction().commit();

			// DbConnect.DbCon().save(entity);
			logger.info("Successfully Added or updated.");
			flag = true;

		} catch (HibernateException e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			flag = false;
		} finally {
			DbCon().clear();
		}
		return flag;
	}

	// worked......
	@Override
	public boolean saveEmpPersonalInfo(BgEmploymentHistoryBean bean) {
		boolean flag;
		try {
			DbConnect.DbCon().beginTransaction();

			DbConnect.DbCon().save(bean);
			DbConnect.DbCon().getTransaction().commit();

			logger.info("Successfully Added or updated.");
			flag = true;

		} catch (HibernateException e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			flag = false;
		} finally {
			DbCon().clear();
		}
		return flag;

	}

	// upload Exp Letter...
	@Override
	public boolean updateExpLetter(String filePath, int companyid) {
		Transaction tx = DbConnect.DbCon().getTransaction();
		try {

			tx.begin();
			BgEmploymentHistoryEntity history = DbConnect.DbCon().get(BgEmploymentHistoryEntity.class, companyid);

			history.setExpLetter(filePath);
			// history.setUserId(userId);
			DbConnect.DbCon().update(history);
			System.out.println(history);
			tx.commit();
			logger.info("Success.. ExpLetter Uploaded.");
			return true;

		} catch (HibernateException e) {
			logger.error("ExpLetter path updating Failed.");
			tx.rollback();
			return false;
		} finally {
			DbCon().clear();
		}

	}

	// save Highest Degree...
	@Override
	public boolean saveHighestDegreeBg(BgHighestDegreeEarnedEntity entity) {

		try {
			Transaction tx = DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			tx.commit();
			logger.info("Sucesssfully Save and Update Highest Degree for BackGround Verification.");
			return true;

		} catch (HibernateException e) {
			logger.error("failed to Save or Update Highest Degree.");

			DbConnect.DbCon().getTransaction().rollback();
			return false;

		} finally {
			DbCon().clear();
		}

	}

	// Fetch Impl Logic for Highest Degree.
	@Override
	public List<BgHighestDegreeEarnedEntity> getDegree(int userId) {
		try {
			DbConnect.DbCon().beginTransaction();
			String hql = "from BgHighestDegreeEarnedEntity where userId=:userId";
			List<BgHighestDegreeEarnedEntity> degreeList = DbConnect.DbCon()
					.createQuery(hql, BgHighestDegreeEarnedEntity.class).setParameter("userId", userId).getResultList();
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Degree list Retrived Successfully.");
			return degreeList;
		} catch (Exception e) {
			DbConnect.DbCon().getTransaction().rollback();
			logger.error("failed to get Execute hql query." + e);
			return null;
		} finally {
			DbCon().clear();
		}
	}

	// Save And Update Emp Reference
	@Override
	public boolean saveUpdateReferenceList(BgEmpProfessionalReferneceEntity entity) {
		
		try {
			Transaction tx = DbConnect.DbCon().beginTransaction();

			DbConnect.DbCon().saveOrUpdate(entity);
			DbConnect.DbCon().getTransaction().commit();
			logger.info("Sucesssfully Save and Update Emp Reference for BackGround Verification.");
			return true;

		} catch (HibernateException e) {
			logger.error("failed to  Save and Update Emp Reference");

			DbConnect.DbCon().getTransaction().rollback();
			return false;

		} finally {
			DbCon().clear();
		}

	}

	
	//Save And Update Emp History
@Override
	public boolean saveUpdateEmpHis(BgEmploymentHistoryEntity entity) {
	
	try {
		Transaction tx = DbConnect.DbCon().beginTransaction();

		DbConnect.DbCon().saveOrUpdate(entity);
		DbConnect.DbCon().getTransaction().commit();
		logger.info("Sucesssfully Save and Update Emp History for BackGround Verification.");
		return true;

	} catch (HibernateException e) {
		logger.error("failed to  Save and Update Emp History");

		DbConnect.DbCon().getTransaction().rollback();
		return false;

	} finally {
		DbCon().clear();
	}

		
	}

	


}
