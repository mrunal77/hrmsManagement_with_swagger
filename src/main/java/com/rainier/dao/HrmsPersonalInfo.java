package com.rainier.dao;

import com.rainier.entities.*;

import java.util.List;

public interface HrmsPersonalInfo {
	
	// saving job history.
    String saveJobHistory(JobHistoryEntity jobHistory);
	
	// update job history.
    int updateJobHistory(JobHistoryEntity jobHistory);
	
	// fetch Job history List.
    List<JobHistoryEntity> getJobHistory(int userId);
	
	// save Experience details.
    boolean saveExperience(ExperinceEntity expEntity);
	
	// fetch Experience Details.
    List<ExperinceEntity> getExpDetails(int userId);
	 
	// update Exp.
    boolean updateExp(ExperinceEntity entity);
	
	// add Education Level Code .
    boolean addEducationLevel(EducationLevelCodeEntity entity);
	
	// fetch Education Level
    List<EducationLevelCodeEntity>  getEduLevelCode();
	
	// Add Emp Education details.
    boolean addEmpEducationDetails(EmpEducationDetailsEntity entity);
	
	// Fetch Emp Education Details.
    List<EmpEducationDetailsEntity> getEmpEducationDetails(int userId);
	
	// update Emp Education details.
    boolean updateEmpEducationDetails(EmpEducationDetailsEntity entity);
	
	// Save Training_Certification Details.
    boolean saveTrainingCertification(Training_CertificationEntity entity);
	
	// fetch Training_Certification details.
    List<Training_CertificationEntity> getTrainingDetails(int userId);
	
	// save or update Disability.
    boolean saveOrUpdateDisability(DisabilityEntity entity);
	
	// fetch Disability Details.
    List<DisabilityEntity> getDisabilityDetails(int userId);
	
	// Saving Medical Claim Type for Employee....
    boolean saveOrUpdateMedicalClaim(MedicalClaimEntity entity);
	
	// fetch Medical Claim for Employee.
    List<MedicalClaimEntity> fetchMedicalClaim(int userId);
	
	// save Or Update Dependency for Employee
    boolean saveOrUpdateDependency(DependencyDetailsEntity entity);
	
	// fetching Dependency Logic for Employee.
    List<DependencyDetailsEntity> getlistOfDependent(int userId);
	
	// for saving Visa Details immigration.
    boolean saveOrUpdateVisa_Immigration(VisaAndImmigrationEntity entity);
	
	// for Fetching visa immigration Details.
    List<VisaAndImmigrationEntity> getVisaimmigrationDetails(int userId);
	
	

}
