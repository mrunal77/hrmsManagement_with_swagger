package com.rainier.dao.Impl;

import com.rainier.dao.HrmsPersonalInfo;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import java.util.List;

public class HrmsPersonalInfoIDaompl implements HrmsPersonalInfo {

    private final static Logger logger = Logger.getLogger(HrmsPersonalInfo.class);

    // saving Job History for Employee.

    public String saveJobHistory(JobHistoryEntity jobHistory) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().save(jobHistory);
            logger.info("Successfully Inserted.");
            DbConnect.DbCon().getTransaction().commit();
            return "job history Saved.";
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
          
        }
    }

    // update job history.

    @Override
    public int updateJobHistory(JobHistoryEntity jobHistory) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().update(jobHistory);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Updated Successfully.");
            return 1;
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return 0;
        } finally {
            DbConnect.DbCon().clear();
            
        }

    }

    // frtch job history.
    @Override
    public List<JobHistoryEntity> getJobHistory(int userId) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "From JobHistoryEntity where userId=:userId";
            DbConnect.DbCon().beginTransaction();
            Query<JobHistoryEntity> query = DbConnect.DbCon().createQuery(hql, JobHistoryEntity.class);
            query.setParameter("userId", userId);
            List<JobHistoryEntity> job = query.getResultList();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Successfully job history List retrived.");
            return job;
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // saving exp.
    @Override
    public boolean saveExperience(ExperinceEntity expEntity) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().save(expEntity);
            logger.info("Experience details saved Successfully.");
            DbConnect.DbCon().getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // fetch Exp.
    @Override
    public List<ExperinceEntity> getExpDetails(int userId) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "from ExperinceEntity where userId=:userId";
            DbConnect.DbCon().beginTransaction();
            /* Query<ExperinceEntity> query = db.DbCon().createQuery(hql); */

            List<ExperinceEntity> listOfExp = DbConnect.DbCon().createQuery(hql, ExperinceEntity.class)
                    .setParameter("userId", userId).getResultList();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Sueessfully retrieved.");
            return listOfExp;
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // update Exp.
    @Override
    public boolean updateExp(ExperinceEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().update(entity);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Updated Successfully.");
            return true;
        } catch (HibernateException e) {
            logger.error("failed to get Execute hql query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            // // System.out.println("Session status: " + DbConnect.DbCon().isOpen());
            // // System.out.println("Session Factory Status: " +
            // DbConnect.dbSessionFactory().isOpen());
        }
    }

    // Add Education Level Code.

    public boolean addEducationLevel(EducationLevelCodeEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().saveOrUpdate(entity);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Education Level Added Successfully.");
            return true;

        } catch (HibernateException e) {
            logger.error("Failed to execute Hql Query.:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // fetch Education Level....
    @Override
    public List<EducationLevelCodeEntity> getEduLevelCode() {
        logger.info("inside of getEduLevelCode of daoImpl ");

        // DbConnect dbConnect = new DbConnect();

        try {
            String hql = "from EducationLevelCodeEntity where isactive = 1";
            DbConnect.DbCon().beginTransaction();
            Query<EducationLevelCodeEntity> query = DbConnect.DbCon().createQuery(hql, EducationLevelCodeEntity.class);
            List<EducationLevelCodeEntity> entityList = query.list();
            DbConnect.DbCon().getTransaction().commit();
            return entityList;
        } catch (Exception e) {
            logger.info("inside catch block of getEduLevelCode exception:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // Add Emp Education Details.
    public boolean addEmpEducationDetails(EmpEducationDetailsEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();

            String hql = " select educationLevelCode from EducationLevelCodeEntity where id="
                    + entity.getEducationLevel();
            String educationlevelName = (String) DbConnect.DbCon().createQuery(hql).uniqueResult();
            entity.setEducationLevelName(educationlevelName);
            DbConnect.DbCon().save(entity);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Education Level Added Successfully.");
            return true;
        } catch (HibernateException e) {
            logger.error("Failed to execute Hql Query.:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // Fetch Emp Education Details.
    public List<EmpEducationDetailsEntity> getEmpEducationDetails(int userId) {
        // DbConnect db = new DbConnect();
        try {
            String hql = "from EmpEducationDetailsEntity where userId=:userId";
            DbConnect.DbCon().beginTransaction();
            List<EmpEducationDetailsEntity> listOfEmpEdu = DbConnect.DbCon()
                    .createQuery(hql, EmpEducationDetailsEntity.class).setParameter("userId", userId).getResultList();
            logger.info("Emp Education Details Retrieved Successfully.");
            DbConnect.DbCon().getTransaction().commit();
            return listOfEmpEdu;
        } catch (HibernateException e) {
            logger.error("Failed to execute Hql Query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory status" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // update Emp Education Details Services.
    @Override
    public boolean updateEmpEducationDetails(EmpEducationDetailsEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            String hql = " select educationLevelCode from EducationLevelCodeEntity where id="
                    + entity.getEducationLevel();
            String educationlevelName = (String) DbConnect.DbCon().createQuery(hql).uniqueResult();
            entity.setEducationLevelName(educationlevelName);
            DbConnect.DbCon().update(entity);
            logger.info("Updated Successfully.");
            DbConnect.DbCon().getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            logger.error("Failed to Execute Hql Query.:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // Save training Certication Details.
    @Override
    public boolean saveTrainingCertification(Training_CertificationEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().saveOrUpdate(entity);
            logger.info("Training Certification Details Saved Successfully.");
            DbConnect.DbCon().getTransaction().commit();
            return true;

        } catch (HibernateException e) {
            logger.error("Failed to Execute Hql Query:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // fetch Training Certification Details.

    @Override
    public List<Training_CertificationEntity> getTrainingDetails(int userId) {
        // DbConnect db = new DbConnect();
        try {

            String hql = "from Training_CertificationEntity where userId=:userId";
            DbConnect.DbCon().beginTransaction();

            List<Training_CertificationEntity> entityList = DbConnect.DbCon()
                    .createQuery(hql, Training_CertificationEntity.class).setParameter("userId", userId)
                    .getResultList();

            logger.info("Training Certificate Details retrieved Successfully. ");
            DbConnect.DbCon().getTransaction().commit();
            return entityList;
        } catch (HibernateException e) {
            logger.error("Failed To Execute hql Query.:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // save or update Emp Disability.
    public boolean saveOrUpdateDisability(DisabilityEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().saveOrUpdate(entity);
            logger.info("Disability Details Saved Successfully.");
            DbConnect.DbCon().getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            logger.error("Failed to Execute Hql Query:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // fetch Disability Details.
    @Override
    public List<DisabilityEntity> getDisabilityDetails(int userId) {

        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            String hql = "from DisabilityEntity where isactive=1 and userId=:uid ";
            List<DisabilityEntity> listOfDisabilityDetails = DbConnect.DbCon().createQuery(hql, DisabilityEntity.class)
                    .setParameter("uid", userId).getResultList();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Disability Details Retrieved Successfully.");
            return listOfDisabilityDetails;
        } catch (HibernateException e) {
            logger.error("Failed to Execute Hql Query:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // for Saving Medical Claim Type.
    @Override
    public boolean saveOrUpdateMedicalClaim(MedicalClaimEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().saveOrUpdate(entity);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Medical Claim Saved Successfully.");
            return true;

        } catch (HibernateException e) {
            logger.error("Failed to execute HQL Query:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // fetch for Medical Claims.
    @Override
    public List<MedicalClaimEntity> fetchMedicalClaim(int userId) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            String hql = "from MedicalClaimEntity where isactive=1 and userId=:userId";
            List<MedicalClaimEntity> claimEntity = DbConnect.DbCon().createQuery(hql, MedicalClaimEntity.class)
                    .setParameter("userId", userId).getResultList();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("medical Claim List retrieved.");
            return claimEntity;
        } catch (HibernateException e) {
            logger.error("Failed to execute HQL Query:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // for save or Update Employee Dependency.
    @Override
    public boolean saveOrUpdateDependency(DependencyDetailsEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().saveOrUpdate(entity);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Dependency Details Saved Or Updated Successfully.");
            return true;
        } catch (HibernateException e) {
            logger.error("failed to execute HQL Query:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return false;

        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // fetching Employee for Dependent List.
    @Override
    public List<DependencyDetailsEntity> getlistOfDependent(int userId) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            String hql = "from DependencyDetailsEntity where isactive=1 and userId=:userId";
            List<DependencyDetailsEntity> listOfDependent = DbConnect.DbCon()
                    .createQuery(hql, DependencyDetailsEntity.class).setParameter("userId", userId).getResultList();
            logger.info("Dependency Details Retrieved Successfully.");
            DbConnect.DbCon().getTransaction().commit();
            return listOfDependent;

        } catch (HibernateException e) {
            logger.error("Failed to Execute Hql Query:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // saving Visa & immigration Details.
    @Override
    public boolean saveOrUpdateVisa_Immigration(VisaAndImmigrationEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().saveOrUpdate(entity);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("transaction completed and Visa Details Saved Successfully.");
            return true;
        } catch (HibernateException e) {
            logger.error("Failed to Execute Hql Query:" + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }
    }

    // Fetch Visa immigration Details.
    @Override
    public List<VisaAndImmigrationEntity> getVisaimmigrationDetails(int userId) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            String hql = "from VisaAndImmigrationEntity where isactive=1 and userId=:userId";
            List<VisaAndImmigrationEntity> listofVisaForDiffuserid = DbConnect.DbCon()
                    .createQuery(hql, VisaAndImmigrationEntity.class).setParameter("userId", userId).getResultList();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("List of Visa Immigration Details Retrieved Successfully.");
            return listofVisaForDiffuserid;
        } catch (HibernateException e) {
            logger.error("Failed to Execute Hql Query.: " + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session Status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
        }

    }

}
