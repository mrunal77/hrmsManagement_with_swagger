package com.rainier.dao;

import com.rainier.dto.responseBean.ClientResponseBean;

public interface ProjectAndClientDao {

	ClientResponseBean getClientDetails(int clientId);
}
