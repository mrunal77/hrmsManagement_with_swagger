package com.rainier.businesslogic;

import com.rainier.beans.DashboardBean;
import com.rainier.beans.DashboardResponseBean;
import com.rainier.dao.HrmsDashboardDao;
import com.rainier.dao.Impl.HrmsDashboardDaoImpl;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import javax.ws.rs.core.Response;

public class Dashboard {
	final static Logger logger = Logger.getLogger(Dashboard.class);
	private static final HrmsDashboardDao dao = new HrmsDashboardDaoImpl();

	@SuppressWarnings("unchecked")
	public Response getDashboardPage(DashboardBean bean) {

		DashboardResponseBean dbresult = dao.getDashboard(bean);
		JSONObject jsonObject = new JSONObject();

		if (dbresult != null) {
			jsonObject.put("birthday", dbresult.getBirthday());
			jsonObject.put("Message", "Dashboard Result Successfully retreived.");
			jsonObject.put("status", true);

			if (bean.getRoleId() != 5) {
				jsonObject.put("totalEmployees", dbresult.getTotalEmployees());
				jsonObject.put("activeEmployees", dbresult.getActiveEmployees());
				jsonObject.put("inactiveEmployees", dbresult.getInactiveEmployees());
				if (bean.getRoleId() == 1 || bean.getRoleId() == 2 || bean.getRoleId() == 3 || bean.getRoleId() == 4)
					jsonObject.put("selectedEmployees", dbresult.getSelectedEmployees());
				if (bean.getRoleId() == 1 || bean.getRoleId() == 2 || bean.getRoleId() == 4)
					jsonObject.put("rejectedEmployees", dbresult.getRejectedEmployees());
				if (bean.getRoleId() == 1 || bean.getRoleId() == 8)
					jsonObject.put("ethnicCodes", dbresult.getEthnicCodes());
				if (bean.getRoleId() == 1)
					jsonObject.put("totalPendingLeaveRequests", dbresult.getToatalPendingLeaveRequests());
				if (bean.getRoleId() == 4) {
					jsonObject.put("ShortlistedEmployees", dbresult.getShortlistedEmp());
					jsonObject.put("ScreeningTypes", dbresult.getScreeningTypes());
				}
				if (bean.getRoleId() == 8) {
					jsonObject.put("employeeRatings ", dbresult.getShortlistedEmp());
					jsonObject.put("appriseManager", "Pending Employee Ratings");
				}
				if (bean.getRoleId() == 9) {
					jsonObject.put("myPendingLeaves", dbresult.getMyPendingLeaves());
					jsonObject.put("managerPendingLeaves", dbresult.getManagerPendingLeaves());
					jsonObject.put("selfApprisal", "Completed Self Apprisal");
				}

			}
			if (bean.getRoleId() == 5) {
				jsonObject.put("myPendingLeaves", dbresult.getMyPendingLeaves());
				jsonObject.put("selfApprisal", "Completed Self Apprisal");
				jsonObject.put("appriseManager", "Apprise Your Manager");

			}

			// return Response.ok().entity(jsonObject).build();
		} else {
			jsonObject.put("Message", "Dashboard Result Not retrieved.");
			jsonObject.put("status", false);
			return Response.ok().entity(jsonObject).build();
		}

		return Response.ok().entity(jsonObject).build();
	}

}
