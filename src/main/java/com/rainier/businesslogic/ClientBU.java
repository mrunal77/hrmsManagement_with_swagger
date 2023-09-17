package com.rainier.businesslogic;

import com.rainier.beans.ClientsBean;
import com.rainier.beans.EntityStatusBean;
import com.rainier.dao.ClientDao;
import com.rainier.dao.Impl.ClientDaoImpl;
import com.rainier.dto.responseBean.ClientResponseBean;
import com.rainier.dto.responseBean.ClientsResponseBean;
import com.rainier.dto.responseBean.CommonResponseBean;
import com.rainier.entities.ClientsEntity;
import com.rainier.utility.HrmsGetDateAndTime;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ClientBU {
	private static final Logger logger = Logger.getLogger(ClientBU.class);
	private static final ClientDao clientDao = new ClientDaoImpl();

	public ClientResponseBean getClientDetails(int clientId) {
		logger.info("inside of getClientDetails method business class");
		ClientResponseBean clientResponseBean = new ClientResponseBean();
		ClientsEntity client = clientDao.getClientDetails(clientId);
		if (client != null) {
			clientResponseBean.setClientId(clientId);
			clientResponseBean.setClientName(client.getClientName());
			clientResponseBean.setCountryId(client.getCountryId());
			clientResponseBean.setAddress(client.getAddress());
			clientResponseBean.setEmailId(client.getEmail());
			clientResponseBean.setFax(client.getFax());
			clientResponseBean.setPhoneNo(client.getPhoneNo());
			clientResponseBean.setPoc(client.getPoc());
			clientResponseBean.setStateId(client.getStateId());
			clientResponseBean.setMessage("successFully got data..");
			clientResponseBean.setStatus(true);
		} else {
			clientResponseBean.setMessage("unable to fetch data..");
			clientResponseBean.setStatus(false);
		}

		return clientResponseBean;
	}

	// Client fetching logic.
	public Response getClientsData() {
		ClientsResponseBean response = new ClientsResponseBean();
		// ClientDao dao = new ClientDaoImpl();
		try {
			List<ClientsEntity> listClient = clientDao.getClients();
			if (!listClient.isEmpty()) {
				response.setMessage("Client Details are retrieved successfully.");
				response.setStatus(true);
				response.setClientList(listClient);
			} else {
				response.setMessage("No Client Details are found");
				response.setStatus(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("Error while fetching Clients details.. - " + e.getMessage());
			response.setStatus(false);
		}
		return Response.status(Response.Status.OK).entity(response).build();
	}

	// for update client Details.
	public Response updateClientDetails(ClientsBean bean) {
		ClientsResponseBean response = new ClientsResponseBean();
		ClientsEntity entityBean = new ClientsEntity();
		// ClientDao dao = new ClientDaoImpl();
		try {
			BeanUtils.copyProperties(entityBean, bean);
			entityBean.setIs_active(1);
			entityBean.setModifiedBy(bean.getCreatedBy());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			boolean clientUpdate = clientDao.updateClientInfo(entityBean);
			if (clientUpdate == true) {
				response.setMessage("Updated Successfully.");
				response.setStatus(true);
			}
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			response.setMessage("Error while Updating Clients Detail.:" + e1.getMessage());
			response.setStatus(false);

			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			response.setMessage("Error while Updating Clien Details:" + e2.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}

	}

	// Delete Clients.
	public Response deleteClients(int id) {
		EntityStatusBean responseBean = new EntityStatusBean();
		// ClientDao dao = new ClientDaoImpl();
		try {
			logger.info("deleteClient of business class::");
			// dao.deleteClients(id);
			responseBean.setMessage(clientDao.deleteClients(id) + " Deleted Succesfully.");
			responseBean.setStatus(true);
			return Response.status(Response.Status.OK).entity(responseBean).build();
		} catch (Exception e) {
			logger.info("catch block of deleteClientsf of business class::" + e);
			responseBean.setMessage("Error while Deleting Clients.");
			responseBean.setStatus(false);
			return Response.status(Response.Status.OK).entity(responseBean).build();
		}
	}

	// save clients
	public Response saveClients(ClientsBean bean) {
		CommonResponseBean response = new CommonResponseBean();
		ClientsEntity entityBean = new ClientsEntity();
		// ClientDao dao = new ClientDaoImpl();
		try {
			BeanUtils.copyProperties(entityBean, bean);
			entityBean.setCreatedBy(bean.getCreatedBy());
			entityBean.setModifiedBy(bean.getCreatedBy());
			entityBean.setCreatedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setModifiedDate(new HrmsGetDateAndTime().GetUTCdatetimeAsString());
			entityBean.setIs_active(1);
			boolean add = clientDao.saveClientsInfo(entityBean);
			if (add == true) {
				response.setMessage("Saved Successfully");
				response.setStatus(true);
			}
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			logger.error("Error while Saving Task Details:" + e1.getMessage());
			response.setMessage("Error while Saving clients Details:" + e1.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		} catch (InvocationTargetException e2) {
			e2.printStackTrace();
			logger.error("Error while Saving Task Details:" + e2.getMessage());
			response.setMessage("Error while Saving clients Details:" + e2.getMessage());
			response.setStatus(false);
			return Response.status(Response.Status.OK).entity(response).build();
		}
	}
}