package com.rainier.dao;

import java.util.List;


import com.rainier.beans.NationalityAddBean;

import com.rainier.entities.NationalityAddEntity;



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
