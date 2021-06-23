import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkHourTest {
    static Employee emp1;
    static Employee emp2;
    static Employee emp3;

    @BeforeClass
    public static void setup() {
        emp1 = new Technician("Hank","123",40);
        emp2 = new Administration("John","password",30);
        emp3 = new Technician("Marc","password123",40);
        new Client("VDA");
        new Client("Plus");
    }

    @Test
    public void showWorkHoursEquiTest() {
        Login.getInstance().setLoggedInUser(emp1);
        emp1.getWorkHours().clear();
        Client cli1 = new Client("Plus");
        //Bij deze test moet er naast de uitkomst van de Assert ook gekeken worden naar de uitgeprinte regels
        new WorkHour(DateTimeHandler.checkDateTime("08-05-2021", "07:30", "08:30"), cli1, "Werk");
        assertEquals(1.0,emp1.getWorkHours().get(0).getDateAndTime().calcHours(),0.1);
        new WorkHour(DateTimeHandler.checkDateTime("03-05-2021", "16:00", "18:00"), cli1, "Werk");
        assertEquals(2.0,emp1.getWorkHours().get(1).getDateAndTime().calcHours(),0.1);
        new WorkHour(DateTimeHandler.checkDateTime("05-05-2021", "17:00", "19:00"), cli1, "Werk");
        assertEquals(2.0,emp1.getWorkHours().get(2).getDateAndTime().calcHours(),0.1);
        new WorkHour(DateTimeHandler.checkDateTime("08-05-2021", "16:45", "17:15"), cli1, "Werk");
        assertEquals(0.5,emp1.getWorkHours().get(3).getDateAndTime().calcHours(),0.1);
        new WorkHour(DateTimeHandler.checkDateTime("04-05-2021", "08:00", "09:00"), cli1, "Werk");
        assertEquals(1.0,emp1.getWorkHours().get(4).getDateAndTime().calcHours(),0.1);
        new WorkHour(DateTimeHandler.checkDateTime("09-05-2021", "02:00", "02:15"), cli1, "Werk");
        assertEquals(0.25,emp1.getWorkHours().get(5).getDateAndTime().calcHours(),0.1);
        new WorkHour(DateTimeHandler.checkDateTime("09-05-2021", "13:00", "14:15"), cli1, "Werk");
        assertEquals(1.25,emp1.getWorkHours().get(6).getDateAndTime().calcHours(),0.1);
        new WorkHour(DateTimeHandler.checkDateTime("03-05-2021", "08:31", "16:59"), cli1, "Werk");
        assertEquals(8.47,emp1.getWorkHours().get(7).getDateAndTime().calcHours(),0.1);
        new WorkHour(DateTimeHandler.checkDateTime("05-05-2021", "08:29", "08:31"), cli1, "Werk");
        assertEquals(0.03,emp1.getWorkHours().get(8).getDateAndTime().calcHours(),0.1);
        new WorkHour(DateTimeHandler.checkDateTime("07-05-2021", "16:59", "17:01"), cli1, "Werk");
        assertEquals(0.03,emp1.getWorkHours().get(9).getDateAndTime().calcHours(),0.1);
    }

    @Test
    public void checkWorkHourMCDCTest() {
        Login.getInstance().setLoggedInUser(emp1);
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("06-05-2021", "10:00", "15:00"), "Plus", "Werk"));
        assertFalse(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("06-05-2021", "10:00", "15:00"), "glopr", "Werk"));
        assertFalse(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("05-12-2028", "10:00", "15:00"), "Plus", "Werk"));
        assertFalse(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("06-05-2021", "10:00", "08:00"), "Plus", "Werk"));
    }

    @Test
    public void checkWorkHoursPairwiseTest() {
        Login.getInstance().setLoggedInUser(emp3);
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("08-05-2021", "08:30", "09:00"), "Plus", "Werk"));
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("08-05-2021", "09:00", "09:30"), "VDA", "Vrij"));
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime(null,"09:30", "10:00"), "VDA", "Werk"));
        Login.getInstance().setLoggedInUser(emp1);
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("08-05-2021", "10:00", "10:30"), "VDA", "Werk"));
        assertFalse(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime(null,"10:30", "11:00"), "Plus", "Vrij"));
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime(null,"11:00", "11:30"), "VDA", "Werk"));
        Login.getInstance().setLoggedInUser(emp2);
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("08-05-2021", "11:30", "12:00"), "VDA", "Werk"));
        assertFalse(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime("08-05-2021","12:00","12:30"),"Plus","Werk"));
        assertTrue(WorkHour.checkWorkHour(DateTimeHandler.checkDateTime(null,"12:30","13:00"),"VDA","Vrij"));
    }
}