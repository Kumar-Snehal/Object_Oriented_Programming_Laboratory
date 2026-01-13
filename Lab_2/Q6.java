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
        String postfix = infixToPostfix(expr);
        Stack<Double> stk = new Stack<>();
        for (String token : postfix.split(" ")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            if (Character.isDigit(c)) {
                stk.push(Double.parseDouble(token));
            } else {
                double b = stk.pop();
                double a = stk.pop();
                switch (c) {
                    case '+':
                        stk.push(a + b);
                        break;
                    case '-':
                        stk.push(a - b);
                        break;
                    case '*':
                        stk.push(a * b);
                        break;
                    case '/':
                        stk.push(a / b);
                        break;
                }
            }
        }
        val = stk.pop();
        return val;
    }

    public void print() {
        System.out.println(expr);
    }

    public void printPost() {
        String postfix = infixToPostfix(expr);
        System.out.println(postfix);
    }

    public void printPre() {
        String postfix = infixToPostfix(expr);
        Stack<String> stk = new Stack<>();
        for (String token : postfix.split(" ")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            if (Character.isDigit(c)) {
                stk.push(token);
            } else {
                String b = stk.pop();
                String a = stk.pop();
                String temp = c + " " + a + " " + b;
                stk.push(temp);
            }
        }
        String prefix = stk.pop();
        System.out.println(prefix);
    }

    private static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stk = new Stack<>();

        int n = infix.length();
        for (int i = 0; i < n; i++) {
            char c = infix.charAt(i);

            if (Character.isWhitespace(c)) {
                continue;
            }

            if (Character.isDigit(c)) {
                StringBuilder number = new StringBuilder();
                while (i < n && Character.isDigit(infix.charAt(i))) {
                    number.append(infix.charAt(i));
                    i++;
                }
                i--;
                postfix.append(number).append(' ');
            } else {
                while (!stk.isEmpty() && precedence(stk.peek()) >= precedence(c)) {
                    postfix.append(stk.pop()).append(' ');
                }
                stk.push(c);
            }
        }

        while (!stk.isEmpty()) {
            postfix.append(stk.pop()).append(' ');
        }

        return postfix.toString().trim();
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

}

public class Q6 {
    public static void main(String[] args) {
        Expression x = new Expression("8/4+3*4-3");
        System.out.println("x = " + x.eval() + "");
        System.out.print("Infix Expression: ");
        x.print();
        System.out.print("Postfix Expression: ");
        x.printPost();
        System.out.print("Prefix Expression: ");
        x.printPre();
    }
}
