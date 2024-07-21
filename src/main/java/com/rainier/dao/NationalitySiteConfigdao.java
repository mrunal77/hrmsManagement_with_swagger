package com.rainier.dao;

import com.rainier.beans.NationalityAddBean;
import com.rainier.entities.NationalityAddEntity;

import java.util.List;



public interface  NationalitySiteConfigdao {
	
	//Add  Nationality Context Code Services

	public boolean addNationalityContext(NationalityAddBean bean) ;
	
	//Fetch  Nationality Context Code Services


	public List<NationalityAddEntity> getAllNationalityCode();
	

	//Update Nationality Context Code Services

	public boolean saveUpdateNationalityContext(NationalityAddBean bean);
	
	//Delete For Nationality Context Code
	public boolean deleteNationalityContext (int id);


	
}
