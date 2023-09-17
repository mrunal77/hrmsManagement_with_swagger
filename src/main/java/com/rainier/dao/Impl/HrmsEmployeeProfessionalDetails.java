package com.rainier.dao.Impl;

import com.rainier.dao.HrmsEmployeeProfessionalDetailsDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.EmployeeDetailsEntity;
import com.rainier.entities.EmployeeDocumentsEntity;
import com.rainier.entities.User;
import com.rainier.entities.VisaDocumentsEntity;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static com.rainier.dbconfiguration.DbConnect.DbCon;

public class HrmsEmployeeProfessionalDetails implements HrmsEmployeeProfessionalDetailsDao {

    private final static Logger logger = Logger.getLogger(EmployeeDetailsEntity.class);

    // Fetch List Of Selected Visa Documents
    public List<EmployeeDocumentsEntity> getSelectedVisaDocuments(List<Integer> list, int userId) {
        logger.info("entered into getSelectedVisaDocuments of Dao Implementation class ");
        // DbConnect db = new DbConnect();
        try {
            String hql = "from VisaDocumentsEntity where id in (:ids)";
            List<EmployeeDocumentsEntity> listDocuments = new ArrayList<>();
            // List<VisaDocumentsEntity> visaDocs = new ArrayList<>();
            DbCon().beginTransaction();
            DbCon().createQuery(hql, VisaDocumentsEntity.class).setParameter("ids", list).list();
            String hql1 = "from EmployeeDocumentsEntity where documentId in (:ids) and userId = " + userId;
            List<EmployeeDocumentsEntity> tempList = DbCon().createQuery(hql1, EmployeeDocumentsEntity.class)
                    .setParameter("ids", list).getResultList();
            for (EmployeeDocumentsEntity entity : tempList) {
                // System.out.println(entity.getAttachmentDocumentPath());
                String docPath = entity.getAttachmentDocumentPath();
                if (docPath == null || docPath.equals("")) {
                    entity.setDocumentFileName("No File Found.");
                    entity.setDocumentUrl("No Url Found");
                } else {
                    entity.setDocumentFileName(/*docPath.substring(docPath.lastIndexOf("/"))*/entity.getDocumentName());
                    entity.setDocumentUrl(docPath);
                }
                listDocuments.add(entity);
            }
            DbCon().getTransaction().commit();
            logger.info("List Of documents fetched.");
            return listDocuments;
        } catch (Exception e) {
            // e.printStackTrace();
            logger.info("catch block of  getSelectedVisaDocuments of Dao Implementation class" + e);
            DbCon().getTransaction().rollback();
            return null;
        } finally {
            logger.debug("Transaction Status" + DbCon().getTransaction().isActive());
            DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            // System.out.println("Session status: " + DbConnect.DbCon().isOpen());
            // System.out.println("Session Factory Status: " +
            // DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }
    }

    // Fetch List Of Selected Visa Documents
    public String getSelectedVisaDocumentsId(int userId) {
        logger.info("entered into getSelectedVisaDocumentsId of Dao Implementation class ");
        // DbConnect db = new DbConnect();
        try {
            String sql = "select selected_documents from main_employees_summary where user_id = " + userId;
            String visaDocsIds;
            DbCon().beginTransaction();
            visaDocsIds = (String) DbCon().createNativeQuery(sql).uniqueResult();
            DbCon().getTransaction().commit();
            // System.out.println(visaDocsIds);
            logger.info("Visa doc Ids fetched.");
            return visaDocsIds;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("catch block of  getSelectedVisaDocumentsId of Dao Implementation class" + e);
            DbCon().getTransaction().rollback();
            return null;
        } finally {
            logger.debug("Transaction Status" + DbCon().getTransaction().isActive());
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            // System.out.println("Session status: " + DbConnect.DbCon().isOpen());
            // System.out.println("Session Factory Status: " +
            // DbConnect.dbSessionFactory().isOpen());
            DbCon().clear();
        }
    }

    @Override
    public boolean uploadProfilePhoto(int userId, String uploadPath) {
        logger.info("entered into uploadProfilePhoto of Dao Implementation class ");
        // DbConnect db = new DbConnect();
        boolean flag;
        try {
            String hql1 = "update EmployeeDetailsEntity e set e.profileImg = :uploadPath  where e.userId = " + userId;

            String hql2 = "update User u set u.profileImage = :uploadPath where u.Id = " + userId;
            DbCon().beginTransaction();
            DbCon().createQuery(hql1).setParameter("uploadPath", uploadPath).executeUpdate();
            DbCon().createQuery(hql2).setParameter("uploadPath", uploadPath).executeUpdate();
            DbCon().getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("catch block of  uploadProfilePhoto of Dao Implementation class" + e);
            DbCon().getTransaction().rollback();
            flag = false;
        } finally {
            logger.debug("Transaction Status" + DbCon().getTransaction().isActive());
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            // System.out.println("Session status: " + DbConnect.DbCon().isOpen());
            // System.out.println("Session Factory Status: " +
            // DbConnect.dbSessionFactory().isOpen());
            DbCon().clear();
        }
        return flag;
    }

    @Override
    public boolean uploadDocument(int userId, int documentId, String uploadPath) {
        logger.info("entered into uploadDocument of Dao Implementation class ");
        // DbConnect db = new DbConnect();
       
        boolean flag;
        try {
            String hql1 = "update EmployeeDocumentsEntity doc set doc.attachmentDocumentPath = :uploadPath  where doc.userId = "
                    + userId + "and doc.documentId = " + documentId;
            DbCon().beginTransaction();
            DbCon().createQuery(hql1).setParameter("uploadPath", uploadPath).executeUpdate();
            DbCon().getTransaction().commit();
            logger.info("Document Path Saved.");
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("catch block of  uploadDocument of Dao Implementation class" + e);
            DbCon().getTransaction().rollback();
            flag = false;
        } finally {
            logger.debug("Transaction Status" + DbCon().getTransaction().isActive());
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            // System.out.println("Session status: " + DbConnect.DbCon().isOpen());
            // System.out.println("Session Factory Status: " +
            // DbConnect.dbSessionFactory().isOpen());
            DbCon().clear();
        }
        return flag;
    }

    @Override
    public boolean onBoardingContactInfo(String firstName, String lastName, String email, int userId) {
        try {
            // String hql = "from EmployeeDetailsEntity where firstName=:firstName and  lastName=:lastName and email=:email and  userId=:userId";
            DbCon().beginTransaction();
            /*Query<EmployeeDetailsEntity> query = DbCon().createQuery(hql, EmployeeDetailsEntity.class)
                    .setParameter("firstName", firstName).setParameter("lastName", lastName)
                    .setParameter("userId", userId).setParameter("email", email);
            EmployeeDetailsEntity onboardContactList = query.uniqueResult();*/
            //DbConnect.DbCon().find(EmployeeDetailsEntity.class, bean.getEmail());
            User employee = DbCon().find(User.class, userId);
            DbCon().getTransaction().commit();
            logger.info("Finded  Successfully." + employee.getUserName());
            return email.equals(employee.getUserName()) && firstName.equals(employee.getFirstName()) && lastName.equals(employee.getLastName());
        } catch (HibernateException e) {
            //e.printStackTrace();
            logger.info("catch block of  onBoardingContactInfo of Dao Implementation class" + e);
            DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbCon().clear();
        }
    }
}
