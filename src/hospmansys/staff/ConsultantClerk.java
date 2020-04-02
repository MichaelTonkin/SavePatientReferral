
package hospmansys.staff;

import hospmansys.ReferralReport;
import hospmansys.database.DatabaseManagementSystem;
import java.util.ArrayList;
import java.util.Arrays;

public class ConsultantClerk extends Staff{
    
    private static String username = "consultantclerk";
    private static String password = "password123";
    
    private static ArrayList<ReferralReport> referralInbox = new ArrayList<ReferralReport>(
    Arrays.asList(
            new ReferralReport("Paint", "01/01/1990", "Kidney problems"), 
            new ReferralReport("Dry", "04/02/1998", "Sleeping problems"),
            new ReferralReport("Poppins", "04/02/1998", "Drink problems")
            ));
    
    public static void saveReferral(ReferralReport ref, String permission)
    {
        System.out.println(ref.getSurname() + ", " + ref.getDoB() + ", " + ref.getReferralData() + ", " + permission);
        DatabaseManagementSystem db = new DatabaseManagementSystem();
        db.connectToDB();
        db.createEntry("referral_reports", 
                "surname, dob, referral_data, permissions", 
                "\"" + ref.getSurname() + "\", \"" + ref.getDoB() + "\", \"" + ref.getReferralData() + "\", \"" + permission + "\"");
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
    
    /*
    Method: getReport
    Description: gets a referral report from the pending referral inbox.
    Parameters: int index -the index of the report to get.
    */
    public static ArrayList getReports()
    {
        return referralInbox;
    }
    
}
