import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHandler {
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

    public DateTimeHandler(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date=date;
        this.startTime=startTime;
        this.endTime=endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public static DateTimeHandler checkDateTime(String date, String startTime, String endTime) {
        if (date==null||date.equals("")) { date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); }
        if (LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isAfter(LocalDate.now())) {
            System.out.println("Datum klopt niet.");
            return null;
        }
        if (!LocalTime.parse(endTime).isAfter(LocalTime.parse(startTime))) {
            System.out.println("Start- of eindtijd klopt niet.");
            return null;
        }
        return new DateTimeHandler(LocalDate.parse(date,DateTimeFormatter.ofPattern("dd-MM-yyyy")),LocalTime.parse(startTime),LocalTime.parse(endTime));
    }

    public double calcHours() {
        String workTime = "";

        if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            int timeRest=endTime.toSecondOfDay() - startTime.toSecondOfDay();
            workTime+=String.format("%02d",(timeRest/3600))+":"+String.format("%02d",((timeRest % 3600) / 60));
            System.out.println(workTime + " (200%)");
            int returnHour = timeRest/3600;
            int returnMinute = ((timeRest % 3600) / 60);
            return returnHour + returnMinute*(5.0/300.0);
        }
        else {
            return splitHours();
        }
    }

    public double splitHours() {
        int timeRest=0; int tempHour=0; int tempMinute=0;
        int earlyTimeDifference = LocalTime.parse("08:30").toSecondOfDay() - startTime.toSecondOfDay();
        if (earlyTimeDifference > 0) {
            tempHour += earlyTimeDifference / 3600;
            tempMinute += (earlyTimeDifference % 3600) / 60;
            timeRest-=earlyTimeDifference;
        }
        int lateTimeDifference = endTime.toSecondOfDay() - LocalTime.parse("17:00").toSecondOfDay();
        if (lateTimeDifference > 0) {
            tempHour += lateTimeDifference / 3600;
            tempMinute += (lateTimeDifference % 3600) / 60;
            timeRest-=lateTimeDifference;
        }
        timeRest += endTime.toSecondOfDay() - startTime.toSecondOfDay();
        System.out.println(String.format("%02d",(timeRest / 3600)) + ":" + String.format("%02d",((timeRest % 3600) / 60)) + " (100%), " + String.format("%02d",tempHour) + ":" + String.format("%02d",tempMinute)+" (150%)");
        tempHour = (tempHour + (timeRest / 3600));
        tempMinute = (tempMinute+((timeRest % 3600) / 60));
        return tempHour + tempMinute*(5.0/300.0);
    }
}
