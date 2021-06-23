public class Technician extends Employee {
    public Technician(String name, String password, Integer contractHours) {
        super(name, password, contractHours);
    }

    @Override
    protected void extraOptions() {
    }

    @Override
    protected void executeChoice(String choice) {
        switch (choice) {
            case "1" : writeHours(); break;
            case "2" : printHours(); break;
            case "0" : Login.getInstance().logOut(); break;
            case "e" : System.exit(9999);
            default : System.out.println("Foutieve waarde ingevoerd. Probeer opnieuw.");
        }

    }
}
