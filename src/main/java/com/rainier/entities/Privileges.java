package com.rainier.entities;

import javax.persistence.*;

@Entity
@Table(name = "main_privileges")
public class Privileges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "object")
    private int obj;

    @Column(name = "role")
    private int role;

    @Column(name = "addpermission")
    private String addpermission;

    @Column(name = "editpermission")
    private String editpermission;

    @Column(name = "deletepermission")
    private String deletepermission;

    @Column(name = "viewpermission")
    private String viewpermission;

    @Column(name = "uploadattachments")
    private String uploadattachments;

    @Column(name = "viewattachments")
    private String viewattachments;

    @Column(name = "isactive")
    private int isActive;

    // @Column(name="createdby")
    // private int createdBy;
    // @Column(name="modifiedby")
    // private int modifiedBy;
    //
    // @Column(name="createddate")
    // private Timestamp createdDate;
    //
    //
    // @Column(name="modifieddate")
    // private Timestamp modifiedDate;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // public int getCreatedBy() {
    // return createdBy;
    // }
    //
    // public void setCreatedBy(int createdBy) {
    // this.createdBy = createdBy;
    // }
    //
    // public int getModifiedBy() {
    // return modifiedBy;
    // }
    //
    // public void setModifiedBy(int modifiedBy) {
    // this.modifiedBy = modifiedBy;
    // }
    //
    // public Timestamp getCreatedDate() {
    // return createdDate;
    // }
    //
    // public void setCreatedDate(Timestamp createdDate) {
    // this.createdDate = createdDate;
    // }
    //
    // public Timestamp getModifiedDate() {
    // return modifiedDate;
    // }
    //
    // public void setModifiedDate(Timestamp modifiedDate) {
    // this.modifiedDate = modifiedDate;
    // }

    public int getObj() {
        return obj;
    }

    public void setObj(int obj) {
        this.obj = obj;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAddpermission() {
        return addpermission;
    }

    public void setAddpermission(String addpermission) {
        this.addpermission = addpermission;
    }

    public String getEditpermission() {
        return editpermission;
    }

    public void setEditpermission(String editpermission) {
        this.editpermission = editpermission;
    }

    public String getDeletepermission() {
        return deletepermission;
    }

    public void setDeletepermission(String deletepermission) {
        this.deletepermission = deletepermission;
    }

    public String getViewpermission() {
        return viewpermission;
    }

    public void setViewpermission(String viewpermission) {
        this.viewpermission = viewpermission;
    }

    public String getUploadattachments() {
        return uploadattachments;
    }

    public void setUploadattachments(String uploadattachments) {
        this.uploadattachments = uploadattachments;
    }

    public String getViewattachments() {
        return viewattachments;
    }

    public void setViewattachments(String viewattachments) {
        this.viewattachments = viewattachments;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
