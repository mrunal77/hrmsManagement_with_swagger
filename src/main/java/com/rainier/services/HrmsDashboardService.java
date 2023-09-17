package com.rainier.services;

import com.rainier.beans.DashboardBean;
import com.rainier.businesslogic.Dashboard;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("/Dashboard")
public class HrmsDashboardService {
	final static Logger logger = Logger.getLogger(HrmsDashboardService.class);
	final static Dashboard dash = new Dashboard();

	@Path("/HomePage")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@CrossOrigin
	public Response getDashboard(DashboardBean bean) {
		if (bean != null) {
			return dash.getDashboardPage(bean);
		} else {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
