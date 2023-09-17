package com.rainier.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "main_empskills")

public class SkillsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "skillname")
    private String skillName;

    @Column(name = "yearsofexp")
    private int yearsOfExp;

    @Column(name = "competencylevelid")
    private Integer competencyLevelId;

    @Column(name = "year_skill_last_used")
    private Date yearSkillLastUsed;

    @Column(name = "isactive", columnDefinition = "TINYINT default 1")
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getYearsOfExp() {
        return yearsOfExp;
    }

    public void setYearsOfExp(int yearsOfExp) {
        this.yearsOfExp = yearsOfExp;
    }

    public Integer getCompetencyLevelId() {
        return competencyLevelId;
    }

    public void setCompetencyLevelId(Integer competencyLevelId) {
        this.competencyLevelId = competencyLevelId;
    }

    public Date getYearSkillLastUsed() {
        return yearSkillLastUsed;
    }

    public void setYearSkillLastUsed(Date yearSkillLastUsed) {
        this.yearSkillLastUsed = yearSkillLastUsed;
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

}
