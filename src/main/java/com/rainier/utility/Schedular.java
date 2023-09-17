package com.rainier.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import org.hibernate.query.Query;

import com.rainier.dbconfiguration.DbConnect;
import com.rainier.entities.EmpPersonalDetailsEntity;

public class Schedular {
	public void callSchedular() {
		String hql=null;
		int year=0;
		int month=0;
		int dayOfMonth=0;
		Date pExpDate=null;
		Date dExpDate=null;
		Timer timer=null;
		LocalDate localdate=null;
		Query<EmpPersonalDetailsEntity> empPE=null;
		List<EmpPersonalDetailsEntity>empL=null;
		hql="from EmpPersonalDetailsEntity";
		empPE=DbConnect.DbCon().createQuery(hql,EmpPersonalDetailsEntity.class);
		empL=empPE.list();
		for(EmpPersonalDetailsEntity emp:empL) {
			pExpDate=emp.getPassportExpDate();
			dExpDate=emp.getDrivingLicenceExpDate();
		}
		
	     
			}

}
