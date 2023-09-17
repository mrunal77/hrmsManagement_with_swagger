package com.rainier.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity


@Table(name = "main_ethniccode")
public class EthinicCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ethniccode")
    private String ethinicCode;

    @Column(name = "ethnicname")
    private String ethinicName;

    @Column(name = "description")
    private String description;

    /*
     * @Column(name = "isactive") private int isActive;
     */
    @Column(name = "isactive")
    private byte isActive;

    @Column(name = "createddate")
    private Timestamp createdDate;

    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEthinicCode() {
        return ethinicCode;
    }

    public void setEthinicCode(String ethinicCode) {
        this.ethinicCode = ethinicCode;
    }

    public String getEthinicName() {
        return ethinicName;
    }

    public void setEthinicName(String ethinicName) {
        this.ethinicName = ethinicName;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
     * public int getIsActive() { return isActive; }
     *
     * public void setIsActive(int isActive) { this.isActive = isActive; }
     */

}
