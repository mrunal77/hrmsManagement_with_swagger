package com.rainier.services;

import com.rainier.beans.DashboardBean;
import com.rainier.businesslogic.Dashboard;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/Dashboard")
@Api("/Dashboard")
@SwaggerDefinition(tags = {@Tag(name = "Dashboard", description = "Dashboard")})
public class HrmsDashboardService {
	final static Logger logger = Logger.getLogger(HrmsDashboardService.class);
	private static Dashboard dash = new Dashboard();

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
