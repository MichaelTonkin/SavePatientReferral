/*
Class: Staff
Description: A generalized staff object which primarily controls staff permissions
throughout the HospManSys application.
Created: 31/03/2020
Updated: 31/03/2020
Author: Michael Tonkin.
*/
package hospmansys.staff;

public class Staff {
    
    private static String username;
    private static String password;
    
    //booleans here decide what parts of the system this staff member can access.
    private boolean accessAppointmentManagement;
    private boolean accessPerformanceReports;
    private boolean accessReferrals;
        
    /*
    Method: setPermissions
    Description: used to initialise the permissions for this staff member
    thus setting what parts of the system they can access
    Parameters: Each parameter corrosponds to a part of the system.
        accessAppointmentManagement - Patient Appointment Management System.
        accessPerformanceReports - Performance Reports.
        accessReferrals - Referrals from consultant.
    */
    private void setPermission(boolean accessAppointmentManagement, boolean accessPerformanceReports, boolean accessReferrals)
    {
        this.accessAppointmentManagement = accessAppointmentManagement;
        this.accessPerformanceReports = accessPerformanceReports;
        this.accessReferrals = accessReferrals;
    }
    
    /*
    Method: getAccessAppointmentManagement
    Description: returns true if staff member can access appointment management system
    */
    public boolean getAccessAppointmentManagement()
    {
        return accessAppointmentManagement;
    }

    /*
    Method: getAccessPerformanceReports
    Description: returns true if staff member can access performance reports
    */
    public boolean getAccessPerformanceReports()
    {
        return accessPerformanceReports;
    }

    /*
    Method: getAccessReferrals
    Description: returns true if staff member can access referral reports
    */
    public boolean getAccessReferrals()
    {
        return accessReferrals;
    } 

    /*
    Method: getUsername
    Description: returns the username for this staff member
    */
    public static String getUsername()
    {
        return username;
    }
    
    /*
    Method: getPassword
    Description: returns the password for this staff member
    */
    public static String getPassword()
    {
        return password;
    }
}
