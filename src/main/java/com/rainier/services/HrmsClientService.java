package com.rainier.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rainier.beans.ClientsBean;
import com.rainier.businesslogic.ClientBU;
import com.rainier.dao.ClientDao;
import com.rainier.dao.Impl.ClientDaoImpl;
import com.rainier.dto.requestBean.ClientRequestBean;
import com.rainier.dto.responseBean.ClientResponseBean;
import com.rainier.dto.responseBean.CommonResponseBean;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders = "*")
@Path("clientService")
public class HrmsClientService {

	private static final Logger logger = Logger.getLogger(HrmsClientService.class);
	private static ClientBU clientBu = new ClientBU();
	private static ClientDao clientDao1 = new ClientDaoImpl();

	@Path("/getClientDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClientDetails(@QueryParam("clientId") int clientId) {
		logger.info("inside getClientDetails method of service class..");
		ClientResponseBean responseBean = clientBu.getClientDetails(clientId);
		return Response.status(Response.Status.OK).entity(responseBean).build();
	}

	@Path("/saveClientDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveClientDetails(ClientRequestBean clientReqBean) {
		CommonResponseBean commonResponseBean = clientDao1.saveClientDetails(clientReqBean);
		if (commonResponseBean.isStatus() != true) {
			CommonResponseBean resBean = new CommonResponseBean();
			resBean.setMessage("data not saved.. ");
			resBean.setStatus(false);
			Response.status(Response.Status.NOT_ACCEPTABLE).entity(resBean).build();
		}
		return Response.status(Response.Status.OK).entity(commonResponseBean).build();
	}

	// for All Clients List.
	@Path("/getAllClientDetails")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllClientsDetails() {
		return clientBu.getClientsData();
	}

	// update Clients Details.
	@Path("/updateClientDetails")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@JsonIgnoreProperties
	@CrossOrigin
	public Response updateClints(ClientsBean bean) {
		logger.info("inside updateClints method of service class..");
		return clientBu.updateClientDetails(bean);
	}

	// delete Clients
	@Path("/deleteClient")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteClints(@QueryParam("id") int id) {
		logger.info("inside deleteClints method of service class..");
		return clientBu.deleteClients(id);
	}

	// saving clients
	@Path("/SaveClient")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveClients(ClientsBean bean) {
		logger.info("inside saveClients method of service class..");
		return clientBu.saveClients(bean);

	}

}
