package hospmansys;

public class ReferralReport {

    private String surname;
    private String dob; 
    private String referralData;
    private String referralTest;
    public ReferralReport(String surname, String dob, String referralData)
    {
        this.surname = surname;
        this.dob = dob;
        this.referralData = referralData;
    }
    
    public String getDoB()
    {
        return dob;
    }
    
    public String getSurname()
    {
     return surname;   
    }
    
    public String getReferralData()
    {
        return referralData;
    }
    
    public String printAll()
    {
        return getSurname() + " " + getDoB() + " " + getReferralData();
    }   
         
}
