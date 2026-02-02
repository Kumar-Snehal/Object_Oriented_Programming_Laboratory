package Lab_4;

public class Q3 {

    public static String sEmail = "";

    public static void extractEmail(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            throw new IllegalArgumentException("Supplied string empty");
        }
        String tokens[] = sentence.split(" "); // split into tokens
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].endsWith("@iitbbs.ac.in")) {
                sEmail = tokens[i];
                System.out.println("Success: " + Q3.sEmail);
                return;
            }
        }
        throw new IllegalArgumentException("No @iitbbs address in supplied string");
    }

    public static void main(String[] args) {
        try {
            Q3.extractEmail("My email is 22cs02009@iitbbs.ac.in");
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }
}
