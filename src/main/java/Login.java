import java.util.Scanner;

public class Login {
    private static Login singleton;
    private Employee loggedInUser;

    private Login() {
        loggedInUser=null;
    }

    public static Login getInstance() {
        if(singleton==null) {
            singleton = new Login();
        }
        return singleton;
    }

    private boolean userExists (String name) {
        for (Employee emp : Employee.employees) {
            if (emp.getName().equals(name)) {
                loggedInUser=emp;
                return true;
            }
        }
        return false;
    }

    public boolean isAuthenticated() {
        if (loggedInUser != null) {
            return true;
        }
        else {
            Scanner in = new Scanner(System.in);
            for (int i=1;i<=3;i++) {
                System.out.println("Voer uw gebruikersnaam in: ");
                String userName = in.nextLine();
                System.out.println("Voer uw wachtwoord in: ");
                String password = in.nextLine();
                if (userExists(userName) && password.equals(loggedInUser.getPassword())) {
                    return true;
                }
                System.out.println("Gebruikersnaam of wachtwoord klopt niet. " + (3-i) + " pogingen over.");
            }
            System.out.println("Te veel foute pogingen. Probeer het later nog eens of neem contact op met een beheerder.");
            return false;
        }
    }

    public Employee getLoggedInUser() {
        return loggedInUser;
    }

    public void logOut() {
        loggedInUser=null;
        System.out.println("U bent uitgelogd. \n\n");
    }

    //Deze methode bestaat alleen zodat de tests blijven werken
    public void setLoggedInUser(Employee loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
