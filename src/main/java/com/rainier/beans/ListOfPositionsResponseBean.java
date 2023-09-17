package com.rainier.beans;

import com.rainier.entities.PositionEntity;

import java.util.List;

public class ListOfPositionsResponseBean {
	private boolean status;
	private String message;
	private List<PositionEntity> listOfPositions;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PositionEntity> getListOfPositions() {
		return listOfPositions;
	}

	public void setListOfPositions(List<PositionEntity> listOfPositions) {
		this.listOfPositions = listOfPositions;
	}

}
