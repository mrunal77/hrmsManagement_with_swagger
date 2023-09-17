package com.rainier.dao;

import com.rainier.entities.EmployeeDocumentsEntity;

import java.sql.Date;
import java.util.List;

public interface HrmsEmployeeProfessionalDetailsDao {
    // Get List Of Selected Documents
    List<EmployeeDocumentsEntity> getSelectedVisaDocuments(List<Integer> list, int userId);

    // Fetch List Of Selected Visa Documents
    String getSelectedVisaDocumentsId(int userId);

    // Profile Image Upload
    boolean uploadProfilePhoto(int userId, String uploadPath);

    // Upload Documents
    boolean uploadDocument(int userId, int documentId, String uploadPath);

    // OnBoarding Contact Info.
    //public List<EmployeeDetailsEntity> onBoardingContactInfo(String  firstName,String lastName,String email);
    boolean onBoardingContactInfo(String firstName, String lastName, String email, int userId);
}
