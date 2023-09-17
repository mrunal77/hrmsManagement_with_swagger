package com.rainier.dbconfiguration;

import com.rainier.entities.*;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DbConnect {

    private static final Logger logger = Logger.getLogger(DbConnect.class);
    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    private static final ThreadLocal<Session> threadLocal;

    static {
        try {
            logger.info("Inside Database Configuration Class...");
            Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(User.class)
                    .addAnnotatedClass(UserRole.class).addAnnotatedClass(Privileges.class)
                    .addAnnotatedClass(Main_Businessunits.class).addAnnotatedClass(Main_Departments.class)
                    .addAnnotatedClass(EmployeeDetailsEntity.class).addAnnotatedClass(Tbl_CountriesEntity.class)
                    .addAnnotatedClass(Tbl_StatesEntity.class).addAnnotatedClass(Tbl_CitiesEntity.class)
                    .addAnnotatedClass(LeaveRequestEntity.class).addAnnotatedClass(MenMenu1.class)
                    .addAnnotatedClass(AllottedLeavesLogEntity.class).addAnnotatedClass(LeaveManagementEntity.class)
                    .addAnnotatedClass(MyLeaveRequestEntity.class).addAnnotatedClass(MailLogEntity.class)
                    .addAnnotatedClass(Tbl_TimezonesEntity.class).addAnnotatedClass(PositionEntity.class)
                    .addAnnotatedClass(EmployeeLeaveTypeEntity.class).addAnnotatedClass(Main_CountriesEntity.class)
                    .addAnnotatedClass(Main_TimezoneEntity.class).addAnnotatedClass(Main_StatesEntity.class)
                    .addAnnotatedClass(Main_citiesEntity.class).addAnnotatedClass(JobTitlesEntity.class)
                    .addAnnotatedClass(EmployeeLeaveDetailsEntity.class)
                    .addAnnotatedClass(EmployeeLeaveRequestSummaryEntity.class)
                    .addAnnotatedClass(EmpPersonalDetailsEntity.class).addAnnotatedClass(SavingTimeSheet.class)
                    .addAnnotatedClass(NationalityEntity.class).addAnnotatedClass(EthinicCodeEntity.class)
                    .addAnnotatedClass(RaceCodeEntity.class).addAnnotatedClass(LanguageEntity.class)
                    .addAnnotatedClass(MaritalStatusEntity.class).addAnnotatedClass(GenderEntity.class)
                    .addAnnotatedClass(Project.class).addAnnotatedClass(Currency.class)
                    .addAnnotatedClass(SalarycurrencyEntity.class).addAnnotatedClass(SalaryPayfrequencyEntity.class)
                    .addAnnotatedClass(SalaryAccountClassTypeEntity.class)
                    .addAnnotatedClass(SalaryBankAccountEntity.class).addAnnotatedClass(VisaTypeEntity.class)
                    .addAnnotatedClass(VisaDocumentsEntity.class).addAnnotatedClass(TmProjectTask.class)
                    .addAnnotatedClass(Task.class).addAnnotatedClass(HolidaysEntity.class)
                    .addAnnotatedClass(CommunicationInfoEntity.class).addAnnotatedClass(SkillsEntity.class)
                    .addAnnotatedClass(SalaryDetailsEntity.class).addAnnotatedClass(JobHistoryEntity.class)
                    .addAnnotatedClass(ClientsEntity.class).addAnnotatedClass(ProjectTaskEntity.class)
                    .addAnnotatedClass(ExperinceEntity.class).addAnnotatedClass(EmployeeDocumentsEntity.class)
                    .addAnnotatedClass(UserLoginLogEntity.class).addAnnotatedClass(DefaultTaskEntity.class)
                    .addAnnotatedClass(OrganizationInfoEntity.class).addAnnotatedClass(EducationLevelCodeEntity.class)
                    .addAnnotatedClass(EmpEducationDetailsEntity.class)
                    .addAnnotatedClass(Training_CertificationEntity.class).addAnnotatedClass(DisabilityEntity.class)
                    .addAnnotatedClass(MedicalClaimEntity.class).addAnnotatedClass(DependencyDetailsEntity.class)
                    .addAnnotatedClass(VisaAndImmigrationEntity.class).addAnnotatedClass(HolidayNamesEntity.class)
                    .addAnnotatedClass(BgHighestDegreeEarnedEntity.class)
                    .addAnnotatedClass(BgEmpPersonalInfoEntity.class).addAnnotatedClass(BgEmploymentHistoryEntity.class)
                    .addAnnotatedClass(BgEmpProfessionalReferneceEntity.class)
                    .addAnnotatedClass(BgEmpEmploymentGapEntity.class)
                    .addAnnotatedClass(RecruitmentOpeningsEntity.class)
                    .addAnnotatedClass(RecruitmentCandidateSignUpEntity.class)
                    .addAnnotatedClass(EmployeeCurrentAddressEntity.class)
                    .addAnnotatedClass(RecruitmentApplyNowForJobEntity.class)
                    .addAnnotatedClass(RecruitmentMailLogEntity.class)
                    .addAnnotatedClass(RecruitmentPostProfileEntity.class)
                    .addAnnotatedClass(RecruitmentInterviewScheduleEntity.class)
                    .addAnnotatedClass(RecruitmentAssignJobEntity.class)
                    .addAnnotatedClass(BenchSalesAddEmployeeEntity.class)
                    .addAnnotatedClass(BenchSalesMailLogEntity.class)
                    .addAnnotatedClass(BenchSalesRecruiterEntity.class)
                    .addAnnotatedClass(BenchSalesAddCandidateEntity.class)
                    .addAnnotatedClass(RecruitmentAssignReportEntity.class)
                    .addAnnotatedClass(BenchSalesAddTestimonialsEntity.class)
                    .addAnnotatedClass(NationalityAddEntity.class)
                    .addAnnotatedClass(TimeSheetEntity.class)
                    .addAnnotatedClass(BenchSalesVendorCandidateMappingEntity.class)
                    .addAnnotatedClass(CandidateSalesExecutiveEntity.class)
                    .addAnnotatedClass(BenchSalesVendorDetailsEntity.class)
                    .addAnnotatedClass(TimeSheetApprovalStatusEntity.class)
                    .addAnnotatedClass(EmployeeWorkStatusEntity.class)
                    .addAnnotatedClass(TimeSheetAddCalWeekMonthEntity.class);

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
            Session session = sessionFactory.getCurrentSession();
            threadLocal = new ThreadLocal<>();
        } catch (Throwable t) {
            t.printStackTrace();
            throw new ExceptionInInitializerError(t);
        }
    }

    public static Session DbCon() {
        // // System.out.println("Getting New Session.");
        Session session = threadLocal.get();
        // logger.info("Getting Session..");
        if (session == null) {
            // logger.info("Opening Null Session....");
            session = sessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }

    public static SessionFactory dbSessionFactory() {
        return sessionFactory;
    }

}
