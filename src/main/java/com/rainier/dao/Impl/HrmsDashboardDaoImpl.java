package com.rainier.dao.Impl;

import com.rainier.beans.DashboardBean;
import com.rainier.beans.DashboardResponseBean;
import com.rainier.dao.HrmsDashboardDao;
import com.rainier.dbconfiguration.DbConnect;
import org.apache.log4j.Logger;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

public class HrmsDashboardDaoImpl implements HrmsDashboardDao {
    private final static Logger logger = Logger.getLogger(HrmsDashboardDaoImpl.class);

    // @SuppressWarnings("unchecked")
    public DashboardResponseBean getDashboard(DashboardBean bean) {
        // TODO Auto-generated method stub

        // DbConnect db = new DbConnect();
        try {
            DbConnect.DbCon().beginTransaction();

            StoredProcedureQuery query = DbConnect.DbCon().createStoredProcedureQuery("Dashboard_Views")
                    .registerStoredProcedureParameter("roleId", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("userId", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("totalEmployees", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("activeEmployees", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("inactiveEmployees", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("toatalPendingLeaveRequests", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("selectedEmployees", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("rejectedEmployees", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("shortlistedEmp", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("myPendingLeaves", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("managerPendingLeaves", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("screeningTypes", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("ethnicCodes", Integer.class, ParameterMode.OUT)
                    .registerStoredProcedureParameter("birthday", String.class, ParameterMode.OUT)
                    .setParameter("roleId", bean.getRoleId()).setParameter("userId", bean.getUserId());
            query.execute();

            DashboardResponseBean responseBean = new DashboardResponseBean();
            Integer totalEmployees = (Integer) query.getOutputParameterValue("totalEmployees");
            Integer activeEmployees = (Integer) query.getOutputParameterValue("activeEmployees");
            Integer inactiveEmployees = (Integer) query.getOutputParameterValue("inactiveEmployees");
            Integer toatalPendingLeaveRequests = (Integer) query.getOutputParameterValue("toatalPendingLeaveRequests");
            Integer selectedEmployees = (Integer) query.getOutputParameterValue("selectedEmployees");
            Integer rejectedEmployees = (Integer) query.getOutputParameterValue("rejectedEmployees");
            Integer shortlistedEmp = (Integer) query.getOutputParameterValue("shortlistedEmp");
            Integer myPendingLeaves = (Integer) query.getOutputParameterValue("myPendingLeaves");
            Integer managerPendingLeaves = (Integer) query.getOutputParameterValue("managerPendingLeaves");
            Integer screeningTypes = (Integer) query.getOutputParameterValue("screeningTypes");
            Integer ethnicCodes = (Integer) query.getOutputParameterValue("ethnicCodes");
            String birthday = (String) query.getOutputParameterValue("birthday");
            responseBean.setActiveEmployees(activeEmployees);
            responseBean.setBirthday(birthday);
            responseBean.setEthnicCodes(ethnicCodes);
            responseBean.setInactiveEmployees(inactiveEmployees);
            responseBean.setManagerPendingLeaves(managerPendingLeaves);
            responseBean.setMyPendingLeaves(myPendingLeaves);
            responseBean.setRejectedEmployees(rejectedEmployees);
            responseBean.setScreeningTypes(screeningTypes);
            responseBean.setSelectedEmployees(selectedEmployees);
            responseBean.setShortlistedEmp(shortlistedEmp);
            responseBean.setToatalPendingLeaveRequests(toatalPendingLeaveRequests);
            responseBean.setTotalEmployees(totalEmployees);

            DbConnect.DbCon().getTransaction().commit();
            logger.info("Dashboard Result Successfully retrived.");
            return responseBean;
        } catch (Exception e) {
            logger.error("failed to Execute Stored Procedure." + e);
            DbConnect.DbCon().getTransaction().rollback();
            return null;
        } finally {
            // db.DbCon().close();
            // db.dbSessionFactory().close();
            logger.debug("Session Status" + DbConnect.DbCon().isOpen());
            logger.debug("Session factory Status:" + DbConnect.dbSessionFactory().isOpen());
            DbConnect.DbCon().clear();
        }
    }

}
