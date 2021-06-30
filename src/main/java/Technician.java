public class Technician extends Employee {
    public Technician(String name, String password, Integer contractHours) {
        super(name, password, contractHours);
    }

    @Override
    protected void extraOptions() {
    }

    @Override
    protected void executeExtra(String choice) {
        if ("2".equals(choice)) {
            printHours();
        } else {
            System.out.println("Foutieve waarde ingevoerd. Probeer opnieuw.");
        }

    }
}
