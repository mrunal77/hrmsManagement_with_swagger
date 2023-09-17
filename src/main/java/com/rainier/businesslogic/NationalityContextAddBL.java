package com.rainier.businesslogic;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;

import com.rainier.beans.CommonResponseBean;
import com.rainier.beans.EntityStatusBean;
import com.rainier.beans.LovResponseBean;
import com.rainier.beans.NationalityAddBean;
import com.rainier.dao.NationalitySiteConfigdao;
import com.rainier.dao.Impl.NationalitySiteConfigdaoImpl;
import com.rainier.entities.BenchSalesAddCandidateEntity;
import com.rainier.entities.BenchSalesAddTestimonialsEntity;
import com.rainier.entities.NationalityAddEntity;

public class NationalityContextAddBL {

	private final static Logger logger = Logger.getLogger(NationalityContextAddBL.class);
	
	
	private static final NationalitySiteConfigdao   dao= new NationalitySiteConfigdaoImpl();
	
	//Add Nationality Context  code Services
	
	public Response addNationalityContext(NationalityAddBean bean) {
		
		NationalityAddEntity entity=new NationalityAddEntity();
		CommonResponseBean response = new CommonResponseBean();
		try {
			BeanUtils.copyProperties(entity, bean);
			boolean natioAdd=dao.addNationalityContext(bean);
			if(natioAdd==true) {
				response.setMessage("NationalityContextCode Added Successfully");
				response.setStatus(true);
				return Response.ok().entity(response).build();
			}else {
				response.setMessage("NationalityContextCode Not Added Successfully");
				response.setStatus(false);
				return Response.ok().entity(response).build();
			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	
		return Response.ok().entity(response).build();

		
	}
	
		//fetch  Nationality Context  code services
	public Response  retriveAllNationalityCode() {
		List<NationalityAddEntity> entityList = dao.getAllNationalityCode();
		CommonResponseBean response = new CommonResponseBean();
		
		if (!entityList.isEmpty()) {
			response.setMessage("Nationality context code Retrived  SuccessFully");
			response.setStatus(true);
			response.setList(entityList);
			return Response.ok().entity(response).build();
		} else {
			response.setMessage("Nationality context code Retrived  UnSuccessFully");
			response.setStatus(false);
			return Response.ok().entity(response).build();
		}
		
		}
	
	//save and update Nationality Context  code Services
public Response	saveUpdateNationalityContext(NationalityAddBean bean) {
	logger.info("Save And Update ()");
	CommonResponseBean responseBean = new CommonResponseBean();
	NationalityAddEntity  entity = new NationalityAddEntity ();
	try {
		BeanUtils.copyProperties(entity, bean);
		boolean result = dao.saveUpdateNationalityContext(bean);
		if (result == true && bean.getId() > 0) {
			responseBean.setMessage(" Nationality Context code updated ");
			responseBean.setStatus(true);
		} else {
			responseBean.setMessage("Nationality Context code Saved");
			responseBean.setStatus(true);
		}
		return Response.ok().entity(responseBean).build();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
		responseBean.setMessage("Error while saving And Updating details - " + e.getMessage());
		responseBean.setStatus(false);
		return Response.status(Response.Status.OK).entity(responseBean).build();
	} catch (InvocationTargetException e) {
		e.printStackTrace();
		responseBean.setMessage("Error while saving And Updating details - " + e.getMessage());
		responseBean.setStatus(false);
		return Response.status(Response.Status.OK).entity(responseBean).build();

	}
	
}
//deleted Natinality Context code
public Response  deleteNationalityContext(int id) {
	CommonResponseBean responseBean = new CommonResponseBean();

	NationalityAddEntity entity=new NationalityAddEntity();
	
	logger.info("Deleted Nationality Context code ");
	try {
        dao.deleteNationalityContext(id);
        responseBean.setMessage(" Deleted Succesfully.");
        responseBean.setStatus(true);
		return Response.status(Response.Status.OK).entity(responseBean).build();
	} catch (Exception e) {
		logger.info("control entered into catch block Exception will come" + e);
		responseBean.setMessage("Error while Deleting  RecruiterDetails.");
		responseBean.setStatus(false);
		return Response.status(Response.Status.OK).entity(responseBean).build();
	}
	
	}

	}

