class Date {
    private int day, month, year;

    public Date() {
        day = 1;
        month = 1;
        year = 2001;
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        if (day < 1 || day > 31) {
            System.out.println("Invalid day");
            this.day = 1;
        }
        if (month > 12 || month < 1) {
            System.out.println("Invalid month");
            this.month = 1;
        }
    }

    public void Modify(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        if (day < 1 || day > 31) {
            System.out.println("Invalid day");
            this.day = 1;
        }
        if (month > 12 || month < 1) {
            System.out.println("Invalid month");
            this.month = 1;
        }
    }

    private String getMonth() {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            default:
                return "December";
        }
    }

    public void Display() {
        String DayFormat = Integer.toString(day);
        if (day < 10)
            DayFormat = "0" + DayFormat;
        String MonthName = getMonth();
        String YearFormat = Integer.toString((int) Math.abs(year));
        if (year < 0)
            YearFormat = YearFormat + " B.C.";
        System.out.println("\n"+MonthName + " " + DayFormat + ", " + YearFormat+"\n");
    }
}

public class Q7 {
    public static void main(String[] args) {
        Date d1 = new Date();
        d1.Display();
        d1.Modify(6, 2, 2005);
        d1.Display();
        Date d2= new Date(-6,-2,-2005);
        d2.Display();
    }
}
