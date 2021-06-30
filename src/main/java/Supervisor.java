import java.util.Scanner;

public class Supervisor extends Employee {

    public Supervisor(String name, String password, Integer contractHours) {
        super(name, password, contractHours);
    }

    @Override
    protected void extraOptions() {
        System.out.println("3) Gebruiker aanmaken");
    }

    @Override
    protected void executeExtra(String choice) {
        Scanner in = new Scanner(System.in);
        switch (choice) {
            case "2" : {
                System.out.println("Voer de naam van de te bekijken werknemer in");
                String name = in.nextLine();
                for (Employee emp : employees) {
                    if (emp.getName().equalsIgnoreCase(name)) {
                        emp.printHours();
                        break;
                    }
                }
                System.out.println("Werknemer kan niet gevonden worden");
            }
            case "3" : {
                System.out.println("Wat is de functie van de nieuwe werknemer? Kies uit:\n1) Technicus\n2) Administratie\n3) Leidinggevende");
                String roleChoice = in.nextLine();
                createEmployee(roleChoice);
                System.out.println("Nieuwe werknemer is aangemaakt.");
                break;
            }
            default : System.out.println("Foutieve waarde ingevoerd. Probeer opnieuw.");
        }
    }

    private void createEmployee(String roleChoice) {
        Scanner in = new Scanner(System.in);
        System.out.println("Voer de naam van de werknemer in: ");
        String name = in.nextLine();
        System.out.println("Voer het wachtwoord van de nieuwe werknemer in: ");
        String password = in.nextLine();
        System.out.println("Hoeveel uur gaat de werknemer werken?");
        int contract = in.nextInt();
        in.nextLine();
        switch (roleChoice) {
            case "1" : new Technician(name,password,contract);
            case "2" : new Administration(name,password,contract);
            case "3" : new Supervisor(name,password,contract);
        }
    }
}
