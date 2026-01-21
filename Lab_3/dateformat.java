package Lab_3;

public class dateformat {
    public int day;
    public int month;
    public int year;

    dateformat() {
        day = 1;
        month = 1;
        year = 1;
    }

    public dateformat(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void display() {
        System.out.println("" + day + "/" + month + "/" + year);
    }
}
