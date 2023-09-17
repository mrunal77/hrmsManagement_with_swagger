package com.rainier.dao;

import com.rainier.beans.DashboardBean;
import com.rainier.beans.DashboardResponseBean;

public interface HrmsDashboardDao {
	DashboardResponseBean getDashboard(DashboardBean bean);

}
