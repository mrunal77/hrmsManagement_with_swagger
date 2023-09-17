package com.rainier.dao;

import com.rainier.dto.requestBean.ClientRequestBean;
import com.rainier.dto.responseBean.CommonResponseBean;
import com.rainier.entities.ClientsEntity;

import java.util.List;

public interface ClientDao {

	ClientsEntity getClientDetails(int clientId);

	CommonResponseBean saveClientDetails(ClientRequestBean clientDetails);

	// for getting client details.
    List<ClientsEntity> getClients();

	// update client Details.
    boolean updateClientInfo(ClientsEntity entity);

	// Delete Clients .
    String deleteClients(int id);

	// Saving Client Details.
    boolean saveClientsInfo(ClientsEntity entity);
}
