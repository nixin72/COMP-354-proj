import java.util.ArrayList;

public class Calculator_Controller implements CalculatorApp.EvaluateMathExp {
  private Calculator calculator;
  private CalculatorApp calculatorView;
  private Calculator_Model calculatorModel;

  public Calculator_Controller(Calculator calculator, CalculatorApp view) {
    this.calculator = calculator;
    calculatorView = view;
    calculatorModel = new Calculator_Model();
    calculatorView.run();
  }

  public void updateView() {
    calculatorView.update(calculatorModel.getResult());
  }

  @Override
  public void sendTokenizeMathCommand(ArrayList<String> command) {
    String tokens[] = MathExpressionParser.infixToRPN(command);
    System.out.println(tokens);
    calculatorModel.setResult(MathExpressionParser.RPNtoDouble(tokens));
    updateView();
  }

  private class Calculator_Model {
    private double result;

    public Calculator_Model() {
      result = 0.0;
    }

    protected void setResult(double result) {
      this.result = result;
    }

    protected double getResult() {
      return result;
    }
  }

  public static void main(String args[]) {
    Calculator_Controller objSC =
        new Calculator_Controller(new Calculator(), new CalculatorApp(null));
    objSC.calculatorView.setListenerEvaluateCommand(objSC);
    objSC.calculatorView.run();
  }
}
