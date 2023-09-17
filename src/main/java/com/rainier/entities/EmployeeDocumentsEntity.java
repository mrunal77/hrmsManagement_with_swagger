package com.rainier.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "main_employeedocuments")
public class EmployeeDocumentsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@JsonIgnore
	@Column(name = "user_id")
	private int userId;

	@Column(name = "name")
	private String documentName;

	@JsonIgnore
	@Column(name = "attachments")
	private String attachmentDocumentPath;

	@Transient
	private String documentFileName;

	@Transient
	private String documentUrl;

	@Column(name = "documentId")
	private int documentId;

	@Column(name = "fromDate")
	private Date fromDate;

	@Column(name = "toDate")
	private Date toDate;

	@Column(name = "isExpires", nullable = false)
	private Integer isExpires;

	@JsonIgnore
	@Column(name = "createdby", updatable = false)
	private int createdBy;

	@JsonIgnore
	@Column(name = "modifiedby")
	private Integer modifiedBy;

	@JsonIgnore
	@Column(name = "createddate", updatable = false)
	private Timestamp createdDate;

	@JsonIgnore
	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getAttachmentDocumentPath() {
		return attachmentDocumentPath;
	}

	public void setAttachmentDocumentPath(String attachmentDocumentPath) {
		this.attachmentDocumentPath = attachmentDocumentPath;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getDocumentFileName() {
		return documentFileName;
	}

	public void setDocumentFileName(String documentFileName) {
		this.documentFileName = documentFileName;
	}

	public String getDocumentUrl() {
		return documentUrl;
	}

	public void setDocumentUrl(String documentUrl) {
		this.documentUrl = documentUrl;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getIsExpires() {
		return isExpires;
	}

	public void setIsExpires(Integer isExpires) {
		this.isExpires = isExpires;
	}

}
