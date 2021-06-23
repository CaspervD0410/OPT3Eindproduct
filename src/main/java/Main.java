public class Main{

    public static void main(String[] args) {
        new Client("VDA");
        new Supervisor("Supervisor","password",40);
        while(Login.getInstance().isAuthenticated()) {
            Login.getInstance().getLoggedInUser().printMenu();
        }
    }

}