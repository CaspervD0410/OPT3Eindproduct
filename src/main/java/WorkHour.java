public class WorkHour {
    private final DateTimeHandler dateAndTime;
    private final Client client;
    private final String description;

    public static boolean checkWorkHour(DateTimeHandler dateAndTime, String client, String description){
        if (dateAndTime==null) {return false;}
        if (description.toLowerCase().contains("vrij")&&!client.equalsIgnoreCase("VDA")){
            System.out.println("Vrij is altijd ten laste van VDA. Uren niet opgeslagen.");
            return false;
        }
        if (Login.getInstance().getLoggedInUser() instanceof Administration &&!client.equalsIgnoreCase("VDA")) {
            System.out.println("Administratief werk is altijd ten last van VDA. Uren niet opgeslagen.");
            return false;
        }
        return saveWorkHour(dateAndTime, client, description);
    }

    private static boolean saveWorkHour(DateTimeHandler dateAndTime, String client, String description) {

        for (Client cl : Client.clients) {
            if (cl.getName().equals(client)) {
                new WorkHour(dateAndTime,cl,description);
                System.out.println("Uren opgeslagen.");
                return true;
            }
        }
        System.out.println("Klant bestaat niet.");
        return false;
    }

    public WorkHour(DateTimeHandler dateAndTime, Client client, String description) {
        this.dateAndTime=dateAndTime;
        this.client = client;
        this.description = description;
        Login.getInstance().getLoggedInUser().getWorkHours().add(this);
    }

    public void printHourLine() {
        System.out.printf("Datum: %s | Werktijd: ",dateAndTime.getDate().toString());
        dateAndTime.calcHours();
        System.out.printf(" | Beschrijving: %s%n%n",description);
    }
    public Client getClient() { return client; }
    public DateTimeHandler getDateAndTime() { return dateAndTime; }
}
