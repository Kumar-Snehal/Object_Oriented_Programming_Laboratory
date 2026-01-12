import java.util.*;

class Expression {
    private String expr;

    public Expression() {
        expr = "";
    }

    public Expression(String expr) {
        this.expr = expr;
    }

    public double eval() {
        double val = 0;
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<expr.length();i++)
        {
            
        }
        return val;
    }

    public void print() {
        System.out.println(expr);
    }

    public void printPost() {
    }

    public void printPre() {
        System.out.println(expr);
    }
}

public class Q6 {

}
