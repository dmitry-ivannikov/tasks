import java.util.Stack;

public class Calculator {
    public static String ExpressionToRPN(String expr) {
        String current = "";
        Stack<Character> stack = new Stack<Character>();

        int priority;
        for (int i = 0; i < expr.length(); i++) {
            priority = getP(expr.charAt(i));

            if (priority == 0) current += expr.charAt(i);
            if (priority == 1) stack.push(expr.charAt(i));

            if (priority > 1) {
                current += ' ';

                while (!stack.empty()) {
                    if (getP(stack.peek()) >= priority) current += stack.pop();
                    else break;

                }
                stack.push(expr.charAt(i));
            }
            if (priority == -1) {
                current += ' ';
                while (getP(stack.peek()) != 1) current += stack.pop();

                stack.pop();
            }
        }
        while (!stack.empty()) current += stack.pop();

        return current;
    }

    public static double RPNtoAnswer(String rpn) {

        String operand = new String();
        Stack<Double> stack = new Stack<Double>();

        for (int i = 0; i < rpn.length(); i++) {
            if (getP(rpn.charAt(i)) == ' ') continue;
            if (getP(rpn.charAt(i)) == 0) {
                while (rpn.charAt(i) != ' ' &&
                        getP(rpn.charAt(i)) == 0) {
                    operand += rpn.charAt(i++);
                    if (i == rpn.length()) break;
                }
                stack.push(Double.parseDouble(operand));
                operand = new String();
            }

            if (getP(rpn.charAt(i)) > 1) {
                double a = stack.pop();
                double b = stack.pop();
                if (rpn.charAt(i) == '+') {
                    stack.push(b + a);
                }
                if (rpn.charAt(i) == '-') {
                    stack.push(b - a);
                }
                if (rpn.charAt(i) == '*') {
                    stack.push(b * a);
                }
                if (rpn.charAt(i) == '/') {
                    stack.push(b / a);
                }
            }
        }

        return stack.pop();
    }

    private static int getP(char token) {
        if (token == '*' || token == '/') return 3;
        else if (token == '+' || token == '-') return 2;
        else if (token == '(') return 1;
        else if (token == ')') return -1;
        else return 0;
    }
}
