package com.rainier.dao.Impl;

import com.rainier.dao.HrmsDepartmentDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.*;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HrmsDepartment implements HrmsDepartmentDao {
    private final static Logger logger = Logger.getLogger(HrmsDepartment.class);

    public List<Main_Departments> getDepartmentList() {
        logger.info("Entered into getDepartmentList()");
        // DbConnect db = new DbConnect();
        try {
            /*String hql = "select dept.*,country.country as countryName,state.state as stateName,\r\n"
                    + "					 city.city as cityName,timezone.timezone as timeZoneName, timezone_abbr  \r\n"
                    + "					 from main_departments dept, main_countries country, main_cities city, main_states state, main_timezone timezone where dept.country = country.country_id_org \r\n"
                    + "					and dept.state=state.state_id_org \r\n"
                    + "					and dept.city = city.city_org_id\r\n"
                    + "					and dept.timezone = timezone.actual_id\r\n"
                    + "					and dept.isactive =1 ORDER BY dept.id asc";*/
            String hqlDepartment = "from Main_Departments where isactive = 1";
            List<Main_Departments> listDept = new ArrayList<>();

            DbConnect.DbCon().beginTransaction();
            // Query query = DbConnect.DbCon().createNativeQuery(hql);
            List<Main_Departments> tempListDept = DbConnect.DbCon().createQuery(hqlDepartment).getResultList();
            for (Main_Departments tempDepartment : tempListDept) {
                tempDepartment.setCountryName(DbConnect.DbCon().find(Tbl_CountriesEntity.class, tempDepartment.getCountryId()).getCountryName());
                tempDepartment.setStateName(DbConnect.DbCon().find(Tbl_StatesEntity.class, tempDepartment.getStateId()).getStateName());
                tempDepartment.setCityName(DbConnect.DbCon().find(Tbl_CitiesEntity.class, tempDepartment.getCityId()).getCityName());
                tempDepartment.setTimeZoneName(DbConnect.DbCon().find(Tbl_TimezonesEntity.class, tempDepartment.getTimeZoneId()).getTimeZone());
                tempDepartment.setTimeZoneAbbr(DbConnect.DbCon().find(Tbl_TimezonesEntity.class, tempDepartment.getTimeZoneId()).getTimeZoneAbbr());
                listDept.add(tempDepartment);
            }
            // Query query = db.DbCon().createQuery(hql);
            // List<Object[]> deptList = query.list();
            logger.info("successfully retrived department details.");
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Transaction status: " + DbConnect.DbCon().getTransaction().isActive());
            DbConnect.DbCon().clear();
            return listDept;
        } catch (Exception e) {
            DbConnect.DbCon().getTransaction().rollback();
            logger.error("failed to get execute query:" + e);
            return null;
        } finally {
            // db.DbCon().close();
            // db.dbSessionFactory().close();
            logger.debug("Session status: " + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }

    }

    // Read
    /*
     * public List<Main_Departments> getDepartmentList(int pageSize,int index) { int
     * maxResult = pageSize; int id=pageSize*(index-1); DbConnect sf = new
     * DbConnect(); try { String hql = "from Main_Departments where isActive=1";
     * Transaction tx = sf.DbCon().beginTransaction(); Query query =
     * sf.DbCon().createQuery(hql); query.setFirstResult(id);
     * query.setMaxResults(maxResult);
     *
     * List<Main_Departments> listofDept = query.list(); tx.commit(); //
     * System.out.println("list of department data"); return listofDept;
     *
     * } catch (Exception e) { // System.out.println("failed to get execute query" +
     * e); return null; } finally { sf.DbCon().close(); }
     *
     * }
     */
    // Insert
    public boolean insertDepartmentDetails(Main_Departments md) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            String hql = "select name from Main_Businessunits where id=" + md.getBusinessUnitId();
            String businessunitName = (String) DbConnect.DbCon().createQuery(hql).uniqueResult();
            md.setBusinessUnitName(businessunitName);
            DbConnect.DbCon().save(md);
            DbConnect.DbCon().getTransaction().commit();
            // System.out.println("successfully inserted");
            return true;
        } catch (Exception e) {
            logger.error("failed to get execute query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            // db.DbCon().close();
            // db.dbSessionFactory().close();
            logger.debug("Session status: " + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }
    }

    // Update
    public String updateDepartmentDetails(Main_Departments md) {

        // DbConnect db = new DbConnect();

        try {
            DbConnect.DbCon().beginTransaction();
            String hql = "select name from Main_Businessunits where id=" + md.getBusinessUnitId();
            String busineeunitName = (String) DbConnect.DbCon().createQuery(hql).uniqueResult();
            md.setBusinessUnitName(busineeunitName);
            DbConnect.DbCon().update(md);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Updation Completed..");
            return "Updation Completed";
        } catch (Exception e) {
            logger.error("failed to get execute query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return "Updation Failed";
        } finally {
            // db.DbCon().close();
            // db.dbSessionFactory().close();
            logger.debug("Session status: " + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }
    }

    // Delete
    public String deleteDepartmentDetails(int id, int userId) {
        // DbConnect db = new DbConnect();

        List associatedEmplist;
        try {
            // DU_Bean d=new DU_Bean();
            String hql = "update Main_Departments set  isactive=:isactive " + ",modifiedBy=:userId "
                    + ", modifiedDate=:date " + "where id=:id";
            DbConnect.DbCon().beginTransaction();

            String hql2 = "from EmployeeDetailsEntity where isactive=1 and departmentId=" + id;
            String hql1 = "Select name from  Main_Departments where id=" + id;

            associatedEmplist = DbConnect.DbCon().createQuery(hql2).list();

            String departmentname = null;
            if (associatedEmplist.isEmpty()) {

                departmentname = (String) DbConnect.DbCon().createQuery(hql1).uniqueResult();
                Query query = DbConnect.DbCon().createQuery(hql);
                query.setParameter("isactive", 0);
                query.setParameter("id", id);
                query.setParameter("date", new HrmsGetDateAndTime().GetUTCdatetimeAsString());
                query.setParameter("userId", userId);
                query.executeUpdate();
                DbConnect.DbCon().getTransaction().commit();
                return departmentname;
            } else {
                logger.info("Reassign the Employee in Another Department.");
                DbConnect.DbCon().getTransaction().rollback();
            }
            return null;
        } catch (Exception e) {
            logger.error("failed to get execute query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            // db.DbCon().close();
            // db.dbSessionFactory().close();
            logger.debug("Session status: " + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }

    }

    // hql based on bu unit id

    public List<Main_Departments> getBuDu(Integer businessUnitId) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            String hql = "from  Main_Departments  where businessUnitId=:businessUnitId and isactive=1";
            DbConnect.DbCon().beginTransaction();
            Query query = DbConnect.DbCon().createQuery(hql);
            query.setParameter("businessUnitId", businessUnitId);
            List<Main_Departments> listofDept = query.list();
            DbConnect.DbCon().getTransaction().commit();
            logger.info(" department data based on businessUnitId...");
            return listofDept;
        } catch (Exception e) {
            logger.error("failed to get execute query:" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            // db.DbCon().close();
            // db.dbSessionFactory().close();
            // System.out.println("Session status: " + DbConnect.DbCon().isOpen());
            // System.out.println("Session Factory Status: " +
            // DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }

    }

    // lov for Dept head.
    @Override
    public List<Object[]> getDeptHeadlov() {
        // DbConnect db = new DbConnect();
        try {
            String hql = "SELECT userfullname,emprole FROM main_users where emprole<=2 or emprole=4  or emprole=11";
            DbConnect.DbCon().beginTransaction();
            Query query = DbConnect.DbCon().createNativeQuery(hql);
            List<Object[]> deptHeadList = query.list();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Department Head List  Retrrived");
            return deptHeadList;
        } catch (Exception e) {
            logger.error("failed to get Execute hql query." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            // db.DbCon().close();
            // db.dbSessionFactory().close();
            logger.debug("Session status: " + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }

    }

}
