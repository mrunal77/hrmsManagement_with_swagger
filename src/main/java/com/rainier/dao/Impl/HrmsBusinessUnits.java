package com.rainier.dao.Impl;

import com.rainier.businesslogic.BusinessUnits;
import com.rainier.dao.HrmsBusinessDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.*;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HrmsBusinessUnits implements HrmsBusinessDao {

    private final static Logger logger = Logger.getLogger(BusinessUnits.class);

    //	@SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Main_Businessunits> getBUList() {
        logger.info("Entered into getBUList() ");
        try {
//			String hql = "select bu.*,country.country as countryName,state.state as stateName,"
//					+ "city.city as cityName,timezone.timezone as timeZoneName, timezone_abbr  "
//					+ "from main_businessunits bu, main_countries country, main_cities city, main_states state, "
//					+ "main_timezone timezone where\r\n" + "bu.country = country.country_id_org \r\n"
//					+ "and bu.state=state.state_id_org\r\n" + "and bu.city = city.city_org_id \r\n"
//					+ "and bu.timezone = timezone.actual_id\r\n" + "and bu.isactive =1 ORDER BY bu.id asc";
            List<Main_Businessunits> listBusinessUnits = new ArrayList<>();
            String hql1 = "from Main_Businessunits bu where bu.isactive=1";
            DbConnect.DbCon().beginTransaction();
            Query<Main_Businessunits> query = DbConnect.DbCon().createQuery(hql1, Main_Businessunits.class);
            List<Main_Businessunits> listmbu = query.getResultList();
            for (Main_Businessunits tempEntity : listmbu) {
                tempEntity.setCountryName(
                        DbConnect.DbCon().find(Tbl_CountriesEntity.class, tempEntity.getCountry()).getCountryName());
                tempEntity.setStateName(
                        DbConnect.DbCon().find(Tbl_StatesEntity.class, tempEntity.getState()).getStateName());
                tempEntity.setCityName(DbConnect.DbCon().find(Tbl_CitiesEntity.class, tempEntity.getCity()).getCityName());
                tempEntity.setTimezoneName(
                        DbConnect.DbCon().find(Tbl_TimezonesEntity.class, tempEntity.getTimezone()).getTimeZone());
                tempEntity.setTimeZoneCode(
                        DbConnect.DbCon().find(Tbl_TimezonesEntity.class, tempEntity.getTimezone()).getTimeZoneAbbr());
                listBusinessUnits.add(tempEntity);
            }
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Business List values successfully retrieved from database");
            DbConnect.DbCon().clear();
            return listBusinessUnits;
        } catch (Exception e) {
            logger.error("failed to get hql query" + e);
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

    // Hibernate Logic to save Business Unit
    public boolean insertBUDetails(Main_Businessunits businessUnit) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().save(businessUnit);
            DbConnect.DbCon().getTransaction().commit();
            return true;
        } catch (Exception e) {
            // System.out.println("Exception: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            // db.DbCon().close();
            // db.dbSessionFactory().close();
            // System.out.println("Session status: " + DbConnect.DbCon().isOpen());
            // System.out.println("Session Factory Status: " +
            // DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }
    }

    public String updateBU(Main_Businessunits mainbs) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().update(mainbs);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Updated.....");
            return "Update Completed";
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

    /*
     * public int updateBU(Main_Businessunits mainbs) { DbConnect db = new
     * DbConnect(); try { String hql = "UPDATE Main_Businessunits as mb set " +
     *
     * "name=:unitname," + "code=:unitcode," + "DESCRIPTION =:description," +
     * "STARTDATE =:startdate," + "TIMEZONE =:timezone," + "COUNTRY =:country," +
     * "STATE =:state," + "CITY =:city," + "streetAddress1=:address1," +
     * "streetAddress2=:address2," + "streetAddress3=:address3 " + "where ID = :id";
     * Transaction tx = db.DbCon().beginTransaction(); Query query =
     * db.DbCon().createQuery(hql); query.setParameter("id", mainbs.getId());
     * query.setParameter("unitname", mainbs.getName());
     * query.setParameter("unitcode", mainbs.getCode());
     * query.setParameter("description", mainbs.getDescription());
     * query.setParameter("startdate", mainbs.getStartDate());
     * query.setParameter("country", mainbs.getCountry());
     * query.setParameter("state", mainbs.getState()); query.setParameter("city",
     * mainbs.getCity()); query.setParameter("address1",
     * mainbs.getStreetAddress1()); query.setParameter("address2",
     * mainbs.getStreetAddress2()); query.setParameter("address3",
     * mainbs.getStreetAddress3()); query.setParameter("timezone",
     * mainbs.getTimezone()); int rowCount = query.executeUpdate(); tx.commit(); //
     * System.out.println("data updated Succesfully."); return rowCount;
     *
     * } catch (Exception e) { //
     * System.out.println("failed to get execute hql query" + e); return 0; }
     * finally { //db.DbCon().close(); //db.dbSessionFactory().close(); //
     * System.out.println("Session status: " + db.DbCon().isOpen()); //
     * System.out.println("Session Factory Status: " +
     * db.dbSessionFactory().isOpen()); }
     *
     * }
     */

    // delete
    public String deleteBU(int id, int userId) {
        // DbConnect db = new DbConnect();
        // BU_RequestBean bean= new BU_RequestBean();
        try {
            String hql = "update Main_Businessunits set isactive=:isactive " + ", modifiedBy=:userId "
                    + ", modifiedDate=:date " + "where id=:id";
            DbConnect.DbCon().beginTransaction();

            String hql2 = "from Main_Departments where isactive=1 and businessUnitId=" + id;
            // String hql1 = "select name from Main_Businessunits where id=" + id;
            List<Main_Departments> respectiveDept = DbConnect.DbCon().createQuery(hql2, Main_Departments.class).list();
            String businessname = null;
            if (respectiveDept.isEmpty()) {
                businessname = DbConnect.DbCon().find(Main_Businessunits.class, id).getName();
                Query query = DbConnect.DbCon().createQuery(hql);
                query.setParameter("isactive", 0);
                query.setParameter("id", id);
                query.setParameter("date", new HrmsGetDateAndTime().GetUTCdatetimeAsString());
                query.setParameter("userId", userId);
                query.executeUpdate();
                DbConnect.DbCon().getTransaction().commit();
                return businessname;
            } else {
                logger.info("Have Department List");
                DbConnect.DbCon().getTransaction().rollback();
                return null;
            }
        } catch (Exception e) {
            logger.error("Exception:" + e);
            // System.out.println("failed to get execute query" + e);
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

    /*
     * //hql based on bu unit id
     *
     * public List<Main_Departments> getBuDu(int unitid) { // TODO Auto-generated
     * method stub DbConnect db=new DbConnect(); try { String
     * hql="from  Main_Departments as md where unitid=:unitid and isactive=1";
     * Transaction tx=db.DbCon().beginTransaction(); Query
     * query=db.DbCon().createQuery(hql); query.setParameter("unitid", unitid);
     * List<Main_Departments> listofDept=query.list(); tx.commit(); //
     * System.out.println(" department data based on business unit id or name");
     * return listofDept;
     *
     * }catch(Exception e) { // System.out.println("failed to get execute query"+e);
     * return null; } finally { //db.DbCon().close();
     * //db.dbSessionFactory().close(); // System.out.println("Session status: " +
     * db.DbCon().isOpen()); // System.out.println("Session Factory Status: " +
     * db.dbSessionFactory().isOpen()); }
     *
     *
     *
     * }
     */

}
