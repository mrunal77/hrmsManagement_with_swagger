package com.rainier.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "main_racecode")
public class RaceCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "racecode")
    private String raceCode;

    @Column(name = "racename")
    private String raceName;

    @Column(name = "description")
    private String description;

    @Column(name = "isactive")
    private byte isActive;

    @Column(name = "modifieddate")
    private Timestamp modifiedDate;

    @Column(name = "createddate")
    private Timestamp createdDate;

    /*
     * @Column(name = "isactive") private int isActive;
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaceCode() {
        return raceCode;
    }

    public void setRaceCode(String raceCode) {
        this.raceCode = raceCode;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /*
     * public int getIsActive() { return isActive; }
     *
     * public void setIsActive(int isActive) { this.isActive = isActive; }
     */
}
