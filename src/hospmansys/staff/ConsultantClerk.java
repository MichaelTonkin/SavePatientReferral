
package hospmansys.staff;

import hospmansys.ReferralReport;
import hospmansys.database.DatabaseManagementSystem;
import hospmansys.database.Firewall;
import hospmansys.utils.PopupBox;
import java.util.ArrayList;
import java.util.Arrays;

public class ConsultantClerk extends Staff{
    
    private static String username = "consultantclerk";
    private static String password = "password123";
    
    //The test data for this use-case.
    private static String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sit amet arcu nec ante hendrerit bibendum. Quisque interdum, mauris et posuere sagittis, odio urna laoreet elit, non consectetur sapien purus quis eros. Nulla facilisi. In hac habitasse platea dictumst. Integer consequat scelerisque velit, vel tristique eros scelerisque et. Proin tristique, tortor et consectetur lobortis, ipsum nisi condimentum sapien, quis lacinia tortor metus sed nulla. Nulla et lacinia est. Aenean quis fermentum velit.";
    private static String loremIpsum2 = "Proin dui leo, auctor eget varius ut, semper et tellus. Curabitur pretium, magna a efficitur gravida, turpis lorem finibus tellus, eget suscipit tellus neque in justo. Nullam eget eleifend urna. Duis id ultricies libero. Proin maximus tellus interdum, egestas sapien sed, facilisis quam. Cras luctus, tortor sit amet eleifend";
    private static ArrayList<ReferralReport> referralInbox = new ArrayList<ReferralReport>(
    Arrays.asList(
            new ReferralReport("Paint", "01/01/1990", loremIpsum), 
            new ReferralReport("Dry", "04/02/1998", loremIpsum2),
            new ReferralReport("Smith", "29/03/1938", "Drink problems"),
            new ReferralReport("Poppins", "04/02/1998", "Liver problems"),
            new ReferralReport("Munn", "04/02/1998", "Did not eat their vegetables")
            ));
    
    /*
    Method: saveReferral
    Description: can be called from anywhere to save a referral report to database.
    Parameters: ReferralReport ref - the report to be saved
                Boolean permSCS - true if the Surgery Clinic System has permissions to view this report
                Boolean permPIC - true if the Patient Insurance Company has permissions to view this report
    */
    public static void saveReferral(ReferralReport ref, Boolean permSCS, Boolean permPIC)
    {
        Firewall fw = new Firewall();
        if(fw.scanData(ref.getSurname() + ", " + ref.getDoB() + ", " + ref.getReferralData() + ", " + permSCS + ", " + permPIC))
        {
            try
            {

                //first we convert the permissions booleans to something our database can understand
                int convertSCS = 0;
                int convertPIC = 0;
                if(permSCS)
                    convertSCS = 1;
                if(permPIC)
                    convertPIC = 1;

                //This is where the actual upload takes place
                DatabaseManagementSystem db = new DatabaseManagementSystem();
                db.connectToDB();
                db.createEntry("referral_reports", 
                        "surname, dob, referral_data, permSCS, permPIC", 
                        "\"" + ref.getSurname() + "\", \"" + ref.getDoB() + "\", \"" + ref.getReferralData() + "\", \"" + convertSCS + "\", \"" + convertPIC + "\"");
            }catch(Exception e)
            {
                PopupBox failbox = new PopupBox("Upload unsuccessful. Please request assistance from your system administrator.");
            }
        }
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
