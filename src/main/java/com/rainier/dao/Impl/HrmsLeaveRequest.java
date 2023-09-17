package com.rainier.dao.Impl;

import com.rainier.beans.UpdateEmployeeLeaveDetails;
import com.rainier.dao.HrmsLeaveRequestDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.*;
import org.apache.log4j.Logger;
import org.hibernate.query.Query;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.List;

public class HrmsLeaveRequest implements HrmsLeaveRequestDao {
    private final static Logger logger = Logger.getLogger(HrmsLeaveRequest.class);
    // private static final String leaveRequestId = null;
    // private static final String leaveStatus = null;
    // private static final String message = null;
    private String queryString = null;

    // listing Leave Details
    public List<LeaveRequestEntity> listOfLeaves(int uId, String leavestatus, String view) {
        logger.info("entered into listOfLeaves method of Dao implementation class");
        // DbConnect db = new DbConnect();

        if (view.equalsIgnoreCase("Employee")) {
            queryString = "From LeaveRequestEntity where isActive=1 and userId=:userId";
        } else {
            queryString = "From LeaveRequestEntity where isActive=1 and reportingManagerId=:userId and leaveStatus=:leavestatus";
        }
        try {
            DbConnect.DbCon().beginTransaction();
            Query<LeaveRequestEntity> query = DbConnect.DbCon().createQuery(queryString, LeaveRequestEntity.class);
            if (view.equalsIgnoreCase("Employee")) {
                query.setParameter("userId", uId);
            } else {
                query.setParameter("userId", uId);
                query.setParameter("leavestatus", leavestatus);
            }
            List<LeaveRequestEntity> list = query.getResultList();
            // // System.out.println("List of Leaves Collected..");
            DbConnect.DbCon().getTransaction().commit();
            // // System.out.println("Transaction Commited.");
            return list;
        } catch (Exception e) {
            logger.info("catch block of listOfLeaves method of Dao implementation class::" + e);
            DbConnect.DbCon().getTransaction().rollback();
            // // System.out.println("Hibernate Exception: " + e);
            return null;
        } finally {
            // DbConnect.DbCon().close();
            // // System.out.println("Session Open?: " + DbConnect.DbCon().isOpen());
            // DbConnect.dbSessionFactory().close();
            // // System.out.println("Session Factory Closed?: " +
            // DbConnect.dbSessionFactory().isClosed());
            DbConnect.DbCon().clear();
        }

    }

    // listing Leave Management Options
    public List<LeaveManagementEntity> listOfLeaveManagementOptions() {
        logger.info("entered into listOfLeaveManagementOptions method of Dao implementation class");

        // DbConnect db = new DbConnect();
        try {
            queryString = "From LeaveManagementEntity where isActive=1";
            DbConnect.DbCon().beginTransaction();
            Query<LeaveManagementEntity> query = DbConnect.DbCon().createQuery(queryString,
                    LeaveManagementEntity.class);
            List<LeaveManagementEntity> list = query.getResultList();
            // // System.out.println("List of Leave Management Options Collected..");
            DbConnect.DbCon().getTransaction().commit();
            // // System.out.println("Transaction Commited.");
            return list;
        } catch (Exception e) {
            logger.info("catch block of listOfLeaveManagementOptions method of Dao implementation class::" + e);
            DbConnect.DbCon().getTransaction().rollback();
            // // System.out.println("Hibernate Exception: " + e);
            return null;
        } finally {
            // DbConnect.DbCon().close();
            // // System.out.println("Session Open?: " + DbConnect.DbCon().isOpen());
            // DbConnect.dbSessionFactory().close();
            // // System.out.println("Session Factory Closed?: " +
            // DbConnect.dbSessionFactory().isClosed());
            DbConnect.DbCon().clear();
        }
    }

    // for adding no of days Leave as a login userId.

    @Override
    public String saveNoOfDays(EmployeeLeaveTypeEntity leaveData) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().save(leaveData);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Inserted all leaveType data according to userId");
            return "Successfully saved.";
        } catch (Exception e) {
            logger.error("failed to get hql query" + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }
    }

    // for updating leave request Summary details.
    @Override
    public boolean updateEmployeeLeaveRequestSummary(MyLeaveRequestEntity leaveUpdate) {
        // TODO Auto-generated method stub
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().update(leaveUpdate);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Updated Successfully...");
            return true;
        } catch (Exception e) {
            logger.error("failed to get hql query: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }
    }

    // for update Employee Leave Summary .
    @Override
    public boolean updateEmployeeLeaveDetails(UpdateEmployeeLeaveDetails empLeaveSummary) {
        // TODO Auto-generated method stub

        logger.info("entered into updateEmployeeLeaveDetails of Dao Implementation class ");
        // DbConnect db = new DbConnect();
        try {
            // sql query to call procedure.
            DbConnect.DbCon().beginTransaction();
            StoredProcedureQuery query = DbConnect.DbCon().createStoredProcedureQuery("multiple_leaves")
                    .registerStoredProcedureParameter("leaveRequestId", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("leaveStatus", String.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("message", String.class, ParameterMode.OUT)
                    .setParameter("leaveRequestId", empLeaveSummary.getLeaveRequestId())
                    .setParameter("leaveStatus", empLeaveSummary.getLeaveStatus());
            query.execute();
            /* String message= "Status Updated Successfully"; */
            String message = (String) query.getOutputParameterValue("message");
            logger.info("Status Updated Successfully " + message);
            DbConnect.DbCon().getTransaction().commit();
            return true;
        } catch (Exception e) {
            logger.error("failed  to execute Stored procedure= " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            // // System.out.println("Session status: " + DbConnect.DbCon().isOpen());
            // // System.out.println("Session Factory Status: " +
            // DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }
    }

    // Adding Holidays Leave by admin.
    @Override
    public boolean saveOrUpdateHolidaysList(HolidayNamesEntity entity) {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().saveOrUpdate(entity);
            DbConnect.DbCon().getTransaction().commit();
            logger.info(" Holidays Added or Updated Successfully...");
            return true;
        } catch (Exception e) {
            logger.error("failed to get hql query: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }
    }

    // fetch All Official holiday List.
    public List<HolidayNamesEntity> getAllOfficialHolidays() {
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            String hql = "from HolidayNamesEntity where isactive=1";
            Query<HolidayNamesEntity> query = DbConnect.DbCon().createQuery(hql, HolidayNamesEntity.class);
            List<HolidayNamesEntity> listOfholidays = query.list();
            DbConnect.DbCon().getTransaction().commit();
            logger.info(" Official holidays Retrieved .");
            return listOfholidays;
        } catch (Exception e) {
            logger.error("failed to get hql query: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status:" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory status:" + DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }
    }

	

    /*
     * // for updating multiple leaveRequest at a time.
     *
     *
     *
     * @Override public boolean
     * updateEmployeeLeaveDetails(UpdateEmployeeLeaveDetails empLeaveSummary) { //
     * TODO Auto-generated method stub
     *
     * logger.
     * info("entered into updateEmployeeLeaveDetails of Dao Implementation class ");
     * //DbConnect db = new DbConnect(); try {
     *
     * // sql query to call procedure.
     *
     * Transaction tx = db.DbCon().beginTransaction(); String leaveRequestId = "";
     *
     * for (int i = 0; i < empLeaveSummary.getLeaveRequestId().length; i++) {
     * /*leaveRequestId =empLeaveSummary.getLeaveRequestId()[i]; // //
     * System.out.println(leaveRequestId); if (i !=
     * (empLeaveSummary.getLeaveRequestId().length-1)) leaveRequestId +=
     * empLeaveSummary.getLeaveRequestId()[i] + ","; else leaveRequestId +=
     * empLeaveSummary.getLeaveRequestId()[i]; }
     *
     * // // System.out.println("hii "); // // System.out.println(leaveRequestId);
     *
     * StoredProcedureQuery query =
     * db.DbCon().createStoredProcedureQuery("multiple_leaves")
     * .registerStoredProcedureParameter("leaveRequestId", String.class,
     * ParameterMode.IN) .registerStoredProcedureParameter("leaveStatus",
     * String.class, ParameterMode.IN) .registerStoredProcedureParameter("message",
     * String.class, ParameterMode.OUT) .setParameter("leaveRequestId",
     * leaveRequestId) .setParameter("leaveStatus",
     * empLeaveSummary.getLeaveStatus());
     *
     * query.execute(); String message= "Status Updated Successfully";
     *
     * String message = (String) query.getOutputParameterValue("message");
     * logger.info("Status Updated Successfully " + message); tx.commit(); return
     * true; } catch (Exception e) {
     * logger.error("failed  to execute Stored procedure= " + e); return false; }
     * finally { db.DbCon().close(); db.dbSessionFactory().close(); // //
     * System.out.println("Session status: " + db.DbCon().isOpen()); // //
     * System.out.println("Session Factory Status: " +
     * db.dbSessionFactory().isOpen()); }
     *
     * }
     */
}
