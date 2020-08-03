import java.util.*;

public class MathExpressionParser {
  // Associativity constants for operators
  private static final int LEFT_ASSOC = 0;
  private static final int RIGHT_ASSOC = 1;
  private static final int UNARYOPERATOR = 0;
  private static final int BINARYOPERATOR = 1;

  // Operators
  private static final Map<String, int[]> OPERATORS = new HashMap<String, int[]>();

  static {
    // Map<"token", []{precendence, associativity , operatorType}>
    OPERATORS.put("+", new int[] {1, LEFT_ASSOC, BINARYOPERATOR});
    OPERATORS.put("-", new int[] {1, LEFT_ASSOC, BINARYOPERATOR});
    OPERATORS.put("*", new int[] {2, LEFT_ASSOC, BINARYOPERATOR});
    OPERATORS.put("÷", new int[] {2, LEFT_ASSOC, BINARYOPERATOR});

    OPERATORS.put("Sin", new int[] {1, RIGHT_ASSOC, UNARYOPERATOR});
    OPERATORS.put("ln", new int[] {1, RIGHT_ASSOC, UNARYOPERATOR});
    OPERATORS.put("e^", new int[] {3, RIGHT_ASSOC, UNARYOPERATOR});
    OPERATORS.put("10^", new int[] {3, RIGHT_ASSOC, UNARYOPERATOR});
    OPERATORS.put("^", new int[] {3, LEFT_ASSOC, BINARYOPERATOR});
    OPERATORS.put("MAD", new int[] {1, RIGHT_ASSOC, UNARYOPERATOR});
    OPERATORS.put("Sqrt", new int[] {3, RIGHT_ASSOC, UNARYOPERATOR});
    OPERATORS.put("Abs", new int[] {1, RIGHT_ASSOC, UNARYOPERATOR});
    OPERATORS.put("!", new int[] {1, RIGHT_ASSOC, UNARYOPERATOR});
    OPERATORS.put("μ", new int[] {1, RIGHT_ASSOC, UNARYOPERATOR});
    OPERATORS.put("σ", new int[] {1, RIGHT_ASSOC, UNARYOPERATOR});
    OPERATORS.put(",", new int[] {1, LEFT_ASSOC, BINARYOPERATOR});
    OPERATORS.put("sign", new int[] {3, LEFT_ASSOC, UNARYOPERATOR});
  }

  // Test if token is an operator
  private static boolean isOperator(String token) {
    return OPERATORS.containsKey(token);
  }

  // Test associativity of operator token
  private static boolean isAssociative(String token, int type) {
    if (!isOperator(token)) {
      throw new IllegalArgumentException("Invalid token: " + token);
    }

    if (OPERATORS.get(token)[1] == type) {
      return true;
    }
    return false;
  }

  // Compare precedence of operators.
  private static final int cmpPrecedence(String token1, String token2) {
    if (!isOperator(token1) || !isOperator(token2)) {
      throw new IllegalArgumentException("Invalid tokens: " + token1 + " " + token2);
    }
    int result = OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
    return OPERATORS.get(token1)[0] - OPERATORS.get(token2)[0];
  }

  // Convert infix expression format into reverse Polish notation
  public static String[] infixToRPN(ArrayList<String> inputTokens) {
    ArrayList<String> out = new ArrayList<String>();
    Stack<String> stack = new Stack<String>();

    // For each token
    for (String token : inputTokens) {
      // If token is an operator
      if (isOperator(token)) {
        // While stack not empty AND stack top element
        // is an operator
        while (!stack.empty() && isOperator(stack.peek())) {
          if ((isAssociative(token, LEFT_ASSOC) && cmpPrecedence(token, stack.peek()) <= 0)
              || (isAssociative(token, RIGHT_ASSOC) && cmpPrecedence(token, stack.peek()) < 0)) {
            out.add(stack.pop());
            continue;
          }
          break;
        }
        // Push the new operator on the stack
        stack.push(token);
      }
      // If token is a left bracket '('
      else if (token.equals("(")) {
        stack.push(token); //
      }
      // If token is a right bracket ')'
      else if (token.equals(")")) {
        while (!stack.empty() && !stack.peek().equals("(")) {
          out.add(stack.pop());
        }
        stack.pop();
      }
      // If token is a number
      else {
        if (token.equals("π")) {
          out.add(String.valueOf(Calculator.PI()));
        } else {
          out.add(token);
        }
      }
    }
    while (!stack.empty()) {
      out.add(stack.pop());
    }
    String[] output = new String[out.size()];
    return out.toArray(output);
  }

  public static double RPNtoDouble(String[] tokens) {
    Stack<ArrayList<Double>> Ostack = new Stack<ArrayList<Double>>();
    ArrayList<Double> resultArr = new ArrayList(2);

    // For each token
    for (String token : tokens) {
      if (token.equals("")) {
        continue;
      }
      // If the token is a value push it onto the stack
      if (!isOperator(token)) {
        ArrayList<Double> temp = new ArrayList<Double>(10);
        temp.add(Double.valueOf(token));
        Ostack.push(temp);
      } else {

        if (OPERATORS.containsKey(token)) {
          if (OPERATORS.get(token)[2] == 0) {
            ArrayList<Double> d = Ostack.pop();
            resultArr = performUnaryOperation(token, d);
          } else {
            ArrayList<Double> d2 = Ostack.pop();
            ArrayList<Double> d1 = Ostack.pop();
            resultArr = performBinaryOperation(token, d1, d2);
          }
        }
        // Push result onto stack
        Ostack.push(resultArr);
      }
    }

    return Double.valueOf(resultArr.get(0));
  }

  public static ArrayList<Double> performUnaryOperation(String operand, ArrayList<Double> a) {
    ArrayList<Double> result = new ArrayList<>(10);
    switch (operand) {
      case "Sin":
        result.add(Calculator.sin(a.get(0)));
        break;
      case "MAD":
        result.add(Calculator.mean_absolute_deviation(a));
        break;
      case "!":
        Integer integer = (int) ((double) a.get(0));
        result.add(Calculator.factorial(integer));
        break;
      case "ln":
        result.add(Calculator.log_e_x(a.get(0)));
        break;
      case "Sqrt":
        result.add(Calculator.square_root(a.get(0)));
        break;
      case "Abs":
        result.add(Calculator.absolute_value(a.get(0)));
        break;
      case "μ":
        result.add(Calculator.mean(a));
        break;
      case "σ":
        result.add(Calculator.standard_deviation(a));
        break;
      case "e^":
        result.add(Euler.exp(a.get(0)));
        break;
      case "10^":
        result.add(Calculator.ten_to_the_x(a.get(0)));
        break;
    }
    return result;
  }

  public static ArrayList<Double> performBinaryOperation(
      String operand, ArrayList<Double> a1, ArrayList<Double> a2) {
    ArrayList<Double> result = new ArrayList<>(10);
    switch (operand) {
      case "+":
        result.add(a1.get(0) + a2.get(0));
        break;
      case "-":
        result.add(a1.get(0) - a2.get(0));
        break;
      case "*":
        result.add(a1.get(0) * a2.get(0));
        break;
      case "÷":
        result.add(a1.get(0) / a2.get(0));
        break;
      case "^":
        result.add(Calculator.power(a1.get(0), a2.get(0)));
        break;
      case ",":
        a1.add(a2.remove(0));
        result = a1;
        break;
    }
    return result;
  }

  //    public static void main(String[] args) {
  //        ArrayList<String> input = new ArrayList<>(4);
  //        input.add("4");
  //        input.add("+");
  //        input.add("2");
  //        input.add("*");
  //        input.add("3");
  //        String[] output = infixToRPN(input);
  //
  //        // Build output RPN string minus the commas
  //        for (String token : output) {
  //            System.out.print(token + " ");
  //        }
  //
  //        // Feed the RPN string to RPNtoDouble to give result
  //        Double result = RPNtoDouble(output);
  //    }
}
