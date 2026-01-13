class Movie {
    private String title;
    private int year;
    private String ratings;

    public Movie(String title, int year, String ratings) {
        this.title = title;
        this.year = year;
        this.ratings = ratings;
    }

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
        this.ratings = "NA";
    }

    // to rate the movie, or change the rating
    public void Rate(String newRatings) {
        ratings = newRatings;
    }

    // to get the title of the movie
    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    // to correct the movie details in case of misentry
    public void correctDetails(String newTitle, int newYear) {
        title = newTitle;
        year = newYear;
    }

    // to display the movie details
    public void Display() {
        System.out.println("Title: " + title);
        System.out.println("Year: " + year);
        System.out.println("Ratings: " + ratings);
        System.out.println("");
    }
}

public class Q8 {
    public static void main(String[] args) {
        Movie movie1 = new Movie("The Lord of the Rings", 2001, "PG-13");
        Movie movie2 = new Movie("The Matrix", 1999);

        System.out.println("Movie 1 Details:");
        movie1.Display();

        System.out.println("Movie 2 Details:");
        movie2.Display();

        movie2.Rate("R");
        System.out.println("After rating Movie 2:");
        movie2.Display();

        movie1.correctDetails("The Lord of the Rings: The Fellowship of the Ring", movie1.getYear());
        System.out.println("After correcting Movie 1 details:");
        movie1.Display();
    }
}
