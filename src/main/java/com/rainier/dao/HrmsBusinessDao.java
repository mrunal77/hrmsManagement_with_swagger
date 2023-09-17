package com.rainier.dao;

import com.rainier.entities.Main_Businessunits;

import java.util.List;

public interface HrmsBusinessDao {
	List<Main_Businessunits> getBUList(/* int pageSize,int pageIndex */);

	boolean insertBUDetails(Main_Businessunits businessUnit);

	/* public int updateBU(Main_Businessunits mainbs); */

	String updateBU(Main_Businessunits mainbs);

	String deleteBU(int id, int userId);

}
