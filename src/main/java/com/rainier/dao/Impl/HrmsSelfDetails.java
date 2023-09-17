package com.rainier.dao.Impl;

import com.rainier.dao.HrmsSelfServiceDao;
import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.LeaveRequestEntity;
import com.rainier.entities.MyLeaveRequestEntity;
import com.rainier.entities.TimeSheetEntity;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import static com.rainier.dbconfiguration.DbConnect.DbCon;

import java.util.List;

public class HrmsSelfDetails implements HrmsSelfServiceDao {
    private final static Logger logger = Logger.getLogger(HrmsSelfDetails.class);

    public boolean applyLeaveRequest(MyLeaveRequestEntity leaveRequest) {
        logger.info("entered into applyLeaveRequest method of Dao implementation class");
        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            DbConnect.DbCon().save(leaveRequest);
            DbConnect.DbCon().getTransaction().commit();
            logger.info("successfully leave request raised");
            return true;
        } catch (Exception e) {
            logger.error("failed to get execute query: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return false;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status: " + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
        }
    }

    @Override
    public String myLeaveDelete(int id) {
        logger.info("entered into myLeaveDelete method of Dao implementation class");
        // DbConnect db = new DbConnect();
        try {
            String hql = "update MyLeaveRequestEntity set isActive=:isActive where id=:id";
            DbConnect.DbCon().beginTransaction();
            Query<?> query = DbConnect.DbCon().createQuery(hql);
            query.setParameter("isActive", 0);
            query.setParameter("id", id);
            int i = query.executeUpdate();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("my Leave request successfully Deleted.:");
            if (i == 0) {
                logger.info("My leave details  deleted successfully::" + i);
            } else {
                logger.error("something went wrong for deletion::");
            }
            return "Succesfully Deleted";
        } catch (Exception e) {
            logger.error("failed to get execute query: " + e);
            DbConnect.DbCon().getTransaction().rollback();
            return "failed to Delete my leave request.";
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status: " + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
        }

    }

    public void updateEmployeeLeaves(String leaveType, int userId, float days, String operation, String leaveStatus) {
        logger.info("entered into employeeLeaves method of Dao implementation class");
        // DbConnect db = new DbConnect();
        logger.info("leaveType:" + leaveType + " userId:" + userId + " days:" + days + " operation:" + operation);
        try {
            DbConnect.DbCon().beginTransaction();
            String hql = null;
            if (operation.equalsIgnoreCase("save")) {
                if (leaveType.equalsIgnoreCase("casual leave"))
                    hql = "update main_employeeleaves set used_casual_leaves=ifNull(used_casual_leaves,0)+:days where user_id=:userId";
                else if (leaveType.equalsIgnoreCase("sick leave"))
                    hql = "update main_employeeleaves set used_sick_leaves=ifNull(used_sick_leaves,0)+:days where user_id=:userId";
                Query<?> sqlquery = DbConnect.DbCon().createNativeQuery(hql);
                sqlquery.setParameter("days", days);
                sqlquery.setParameter("userId", userId);
                sqlquery.executeUpdate();
                DbConnect.DbCon().getTransaction().commit();
            } else if (operation.equalsIgnoreCase("update") && !leaveStatus.equalsIgnoreCase("Approved")) {
                if (leaveType.equalsIgnoreCase("casual leave"))
                    hql = "update main_employeeleaves set used_casual_leaves=ifNull(used_casual_leaves,0)-:days where user_id=:userId";
                else if (leaveType.equalsIgnoreCase("sick leave"))
                    hql = "update main_employeeleaves set used_sick_leaves=ifNull(used_sick_leaves,0)-:days where user_id=:userId";
                Query<?> sqlquery = DbConnect.DbCon().createNativeQuery(hql);
                sqlquery.setParameter("days", days);
                sqlquery.setParameter("userId", userId);
                sqlquery.executeUpdate();
                DbConnect.DbCon().getTransaction().commit();
            }
            logger.info("successfully updated leave");
            // return true;
        } catch (Exception e) {
            logger.error("failed to get execute query: " + e);
            // return false;
            DbConnect.DbCon().getTransaction().rollback();
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status: " + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
        }

    }

    // Reason And all data based on userid applied Leave Details.
    @Override
    public List<LeaveRequestEntity> fetchAppliedLeaveRequest(int userId) {
        // DbConnect db= new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();
            String hql = "from LeaveRequestEntity where isActive=1 and userId=:userId";
            List<LeaveRequestEntity> historyOfLeaveApplied = DbConnect.DbCon()
                    .createQuery(hql, LeaveRequestEntity.class).setParameter("userId", userId).getResultList();
            DbConnect.DbCon().getTransaction().commit();
            logger.info("Applied History retrieved Successfully.");
            return historyOfLeaveApplied;
        } catch (HibernateException e) {
            logger.error("failed to get execute query: " + e);
            e.printStackTrace();
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            DbConnect.DbCon().clear();
            // DbConnect.DbCon().close();
            // DbConnect.dbSessionFactory().close();
            logger.debug("Session status: " + DbConnect.DbCon().isOpen());
            logger.debug("Session Factory Status: " + DbConnect.dbSessionFactory().isOpen());
        }

    }
//Save TimeSheet data
	@Override
	public boolean saveTimeCard(TimeSheetEntity entity) {

		boolean flag;
		try {

			DbConnect.DbCon().beginTransaction();
			DbConnect.DbCon().saveOrUpdate(entity);
			logger.info("Timesheet Saved Successfully.");
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

	}


