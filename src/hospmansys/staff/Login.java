
package hospmansys.staff;

public class Login {
    
    private static Login login = null;
    
    //Create a singleton out of this class so it can be used as the login controller.
    //For there should only be one instance of the login controller.
    public static Login getInstance() {

        if (login == null) {
            login = new Login();
        }

        return login;
    }
    
    /*
    Method: userLogin
    Description: controls user login
    Parameters: 
    String username - the username
    String password - the password
    Returns:
    c - if user is consultant clerk
    d - if user fails to login in with a correct username and password combination.
    */
    public static char userLogin(String username, String password)
    {
        if(username.equals(ConsultantClerk.getUsername()) && password.equals(ConsultantClerk.getPassword()))
            return 'c';
        return 'd';
    }
}
