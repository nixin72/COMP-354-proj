package eternity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.Border;

public class CalculatorApp extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;

  int intState;
  String strOperand1;
  String strOperand2;
  String strOperation;
  boolean blnDot = false;
  JTextArea jtxaDisplayBar;

  public CalculatorApp() {
    super("Calculator");
    this.setSize(700, 450);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);

    URL iconURL = getClass().getResource("/calLogo.png");
    ImageIcon icon = new ImageIcon(iconURL);
    this.setIconImage(icon.getImage());

    FocusEvent focusEvent = new FocusEvent(this, FocusEvent.FOCUS_GAINED);
    dispatchEvent(focusEvent);

    intState = 0;
    strOperand1 = "";
    strOperand2 = "";
    strOperation = "";

    // String[] btnLables =
    // {"1","2","3","4","5","6","7","8","9","0",".","=","+/-","Backspace","clear","+","-","/","*","x^y","MAD","sin","ln","10^x","e^x","σ"};

    Border paddingMainPnl = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    JPanel jpMain = new JPanel();
    jpMain.setBorder(paddingMainPnl);
    jpMain.setLayout(new BorderLayout());
    this.add(jpMain);

    Border paddingNorthPnl = BorderFactory.createEmptyBorder(5, 5, 5, 5);
    JPanel jpNorth = new JPanel();
    jpNorth.setBorder(paddingNorthPnl);
    jpNorth.setLayout(new BorderLayout());
    jpMain.add(jpNorth, BorderLayout.NORTH);

    jtxaDisplayBar = new JTextArea("0");
    jtxaDisplayBar.setSize(600, 100);
    jtxaDisplayBar.setFont(jtxaDisplayBar.getFont().deriveFont(20f));
    jtxaDisplayBar.setRows(4);
    jtxaDisplayBar.setEditable(false);
    jpNorth.add(jtxaDisplayBar, BorderLayout.NORTH);

    Border paddingNorthSouthPnl = BorderFactory.createEmptyBorder(5, 1, 0, 1);
    JPanel jpNorthSouth = new JPanel();
    jpNorthSouth.setBorder(paddingNorthSouthPnl);
    jpNorthSouth.setLayout(new GridLayout(1, 2));
    jpNorth.add(jpNorthSouth, BorderLayout.SOUTH);

    JButton jbtnBackspace = new JButton("Backspace");
    jbtnBackspace.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnBackspace.addActionListener(this);
    jpNorthSouth.add(jbtnBackspace);

    JButton jbtnClear = new JButton("Clear");
    jbtnClear.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnClear.addActionListener(this);
    jpNorthSouth.add(jbtnClear);

    Border paddingSouthPnl = BorderFactory.createEmptyBorder(1, 5, 5, 5);
    JPanel jpSouth = new JPanel();
    jpSouth.setBorder(paddingSouthPnl);
    jpSouth.setLayout(new GridLayout(4, 6));
    jpMain.add(jpSouth, BorderLayout.CENTER);

    JButton jbtn7 = new JButton("7");
    jbtn7.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtn7.addActionListener(this);
    jpSouth.add(jbtn7);
    JButton jbtn8 = new JButton("8");
    jbtn8.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtn8.addActionListener(this);
    jpSouth.add(jbtn8);
    JButton jbtn9 = new JButton("9");
    jbtn9.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtn9.addActionListener(this);
    jpSouth.add(jbtn9);
    JButton jbtnDivide = new JButton("/");
    jbtnDivide.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnDivide.addActionListener(this);
    jpSouth.add(jbtnDivide);
    JButton jbtnSin = new JButton("sin");
    jbtnSin.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnSin.addActionListener(this);
    jpSouth.add(jbtnSin);

    JButton jbtnMAD = new JButton("MAD");
    jbtnMAD.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnMAD.addActionListener(this);
    jpSouth.add(jbtnMAD);

    JButton jbtn4 = new JButton("4");
    jbtn4.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtn4.addActionListener(this);
    jpSouth.add(jbtn4);
    JButton jbtn5 = new JButton("5");
    jbtn5.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtn5.addActionListener(this);
    jpSouth.add(jbtn5);
    JButton jbtn6 = new JButton("6");
    jbtn6.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtn6.addActionListener(this);
    jpSouth.add(jbtn6);
    JButton jbtnMultiply = new JButton("*");
    jbtnMultiply.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnMultiply.addActionListener(this);
    jpSouth.add(jbtnMultiply);
    JButton jbtnXtoY = new JButton("x^y");
    jbtnXtoY.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnXtoY.addActionListener(this);
    jpSouth.add(jbtnXtoY);
    JButton jbtnStandardDeviation = new JButton("σ");
    jbtnStandardDeviation.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnStandardDeviation.addActionListener(this);
    jpSouth.add(jbtnStandardDeviation);

    JButton jbtn1 = new JButton("1");
    jbtn1.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtn1.addActionListener(this);
    jpSouth.add(jbtn1);
    JButton jbtn2 = new JButton("2");
    jbtn2.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtn2.addActionListener(this);
    jpSouth.add(jbtn2);
    JButton jbtn3 = new JButton("3");
    jbtn3.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtn3.addActionListener(this);
    jpSouth.add(jbtn3);
    JButton jbtnSubstrate = new JButton("-");
    jbtnSubstrate.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnSubstrate.addActionListener(this);
    jpSouth.add(jbtnSubstrate);
    JButton jbtnNaturalLog = new JButton("ln");
    jbtnNaturalLog.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnNaturalLog.addActionListener(this);
    jpSouth.add(jbtnNaturalLog);
    JButton jbtnEtoX = new JButton("e^x");
    jbtnEtoX.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnEtoX.addActionListener(this);
    jpSouth.add(jbtnEtoX);

    JButton jbtn0 = new JButton("0");
    jbtn0.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtn0.addActionListener(this);
    jpSouth.add(jbtn0);
    JButton jbtnFlipSign = new JButton("+/-");
    jbtnFlipSign.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnFlipSign.addActionListener(this);
    jpSouth.add(jbtnFlipSign);
    JButton jbtnDec = new JButton(".");
    jbtnDec.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnDec.addActionListener(this);
    jpSouth.add(jbtnDec);
    JButton jbtnAdd = new JButton("+");
    jbtnAdd.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnAdd.addActionListener(this);
    jpSouth.add(jbtnAdd);
    JButton jbtnEqual = new JButton("=");
    jbtnEqual.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnEqual.addActionListener(this);
    jpSouth.add(jbtnEqual);
    JButton jbtnTenToX = new JButton("10^x");
    jbtnTenToX.setFont(new Font("Arial", Font.PLAIN, 20));
    jbtnTenToX.addActionListener(this);
    jpSouth.add(jbtnTenToX);
  }

  public static void main(String args[]) {
    CalculatorApp objSC = new CalculatorApp();
    objSC.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    String strCommand = e.getActionCommand();

    if (strCommand.equals("Backspace")) {
      intState = 0;
      if (jtxaDisplayBar.getText().length() == 0) {
        jtxaDisplayBar.setText("0");
      } else {
        String strTemp = jtxaDisplayBar.getText();
        jtxaDisplayBar.setText(strTemp.substring(0, (strTemp.length() - 1)));
        strTemp = jtxaDisplayBar.getText();
        if (strTemp.length() == 0) {
          jtxaDisplayBar.setText("0");
        }
      }
      return;
    }

    if (strCommand.equals("Clear")) {
      intState = 0;
      strOperand1 = "";
      strOperand2 = "";
      strOperation = "";
      jtxaDisplayBar.setText("0");
      return;
    }

    if (strCommand.equals(".")) {
      blnDot = true;
    }

    if (strCommand.equals("+/-")) {
      if (strOperand1 == "") {
        intState = 1;
      } else {
        if (jtxaDisplayBar.getText().equals(strOperand1)) {
          intState = 1;
          Double dblTemp = -1 * Double.parseDouble(strOperand1);
          strOperand1 = "" + dblTemp;
          jtxaDisplayBar.setText(strOperand1);
          return;
        } else if (jtxaDisplayBar.getText().equals(strOperand2)) {
          intState = 3;
          Double dblTemp = -1 * Double.parseDouble(strOperand2);
          strOperand2 = "" + dblTemp;
          jtxaDisplayBar.setText(strOperand2);
          return;
        }
      }
    }

    if (strCommand.equals("sin")) {
      intState = 1;
      // TODO call sin function
      Double dblTemp = 0.0;
      strOperand1 = "" + dblTemp;
      jtxaDisplayBar.setText(strOperand1);
      return;
    }

    if (strCommand.equals("MAD")) {
      intState = 1;
      // TODO call MAD function
      Double dblTemp = 0.0;
      strOperand1 = "" + dblTemp;
      jtxaDisplayBar.setText(strOperand1);
      return;
    }

    if (strCommand.equals("x^y")) {
      intState = 1;
      // TODO call x^y function
      Double dblTemp = 0.0;
      strOperand1 = "" + dblTemp;
      jtxaDisplayBar.setText(strOperand1);
      return;
    }
    if (strCommand.equals("10^x")) {
      intState = 1;
      // TODO call 10^x function
      Double dblTemp = 0.0;
      strOperand1 = "" + dblTemp;
      jtxaDisplayBar.setText(strOperand1);
      return;
    }
    if (strCommand.equals("e^x")) {
      intState = 1;
      // TODO call e^x" function
      Double dblTemp = 0.0;
      strOperand1 = "" + dblTemp;
      jtxaDisplayBar.setText(strOperand1);
      return;
    }
    if (strCommand.equals("ln")) {
      intState = 1;
      // TODO call ln function
      Double dblTemp = 0.0;
      strOperand1 = "" + dblTemp;
      jtxaDisplayBar.setText(strOperand1);
      return;
    }

    if (strCommand.equals("σ")) {
      intState = 1;
      // TODO call σ function
      Double dblTemp = 0.0;
      strOperand1 = "" + dblTemp;
      jtxaDisplayBar.setText(strOperand1);
      return;
    }

    switch (intState) {
      case 0:
        {
          if (strCommand.equals("1")) {
            strOperand1 = "1";
            intState = 1;
            jtxaDisplayBar.setText(strOperand1);
          }
          if (strCommand.equals("2")) {
            strOperand1 = "2";
            intState = 1;
            jtxaDisplayBar.setText(strOperand1);
          }
          if (strCommand.equals("3")) {
            strOperand1 = "3";
            intState = 1;
            jtxaDisplayBar.setText(strOperand1);
          }
          if (strCommand.equals("4")) {
            strOperand1 = "4";
            intState = 1;
            jtxaDisplayBar.setText(strOperand1);
          }
          if (strCommand.equals("5")) {
            strOperand1 = "5";
            intState = 1;
            jtxaDisplayBar.setText(strOperand1);
          }
          if (strCommand.equals("6")) {
            strOperand1 = "6";
            intState = 1;
            jtxaDisplayBar.setText(strOperand1);
          }
          if (strCommand.equals("7")) {
            strOperand1 = "7";
            intState = 1;
            jtxaDisplayBar.setText(strOperand1);
          }
          if (strCommand.equals("8")) {
            strOperand1 = "8";
            intState = 1;
            jtxaDisplayBar.setText(strOperand1);
          }
          if (strCommand.equals("9")) {
            strOperand1 = "9";
            intState = 1;
            jtxaDisplayBar.setText(strOperand1);
          }
          if (blnDot) {
            strOperand1 = "0";
            intState = 1;
            jtxaDisplayBar.setText("0");
          }
          break;
        }

      case 1:
        {
          if (blnDot) {
            strOperand1 += ".";
            blnDot = false;
          }
          if (strCommand.equals("1")) {
            strOperand1 += "1";
          }
          if (strCommand.equals("2")) {
            strOperand1 += "2";
          }
          if (strCommand.equals("3")) {
            strOperand1 += "3";
          }
          if (strCommand.equals("4")) {
            strOperand1 += "4";
          }
          if (strCommand.equals("5")) {
            strOperand1 += "5";
          }
          if (strCommand.equals("6")) {
            strOperand1 += "6";
          }
          if (strCommand.equals("7")) {
            strOperand1 += "7";
          }
          if (strCommand.equals("8")) {
            strOperand1 += "8";
          }
          if (strCommand.equals("9")) {
            strOperand1 += "9";
          }
          if (strCommand.equals("0")) {
            strOperand1 += "0";
          }
          if (strCommand.equals("+")) {
            strOperation = "+";
            intState = 2;
          }
          if (strCommand.equals("-")) {
            strOperation = "-";
            intState = 2;
          }
          if (strCommand.equals("*")) {
            strOperation = "*";
            intState = 2;
          }
          if (strCommand.equals("/")) {
            strOperation = "/";
            intState = 2;
          }
          jtxaDisplayBar.setText(strOperand1);
          break;
        }

      case 2:
        {
          if (strCommand.equals("1")) {
            strOperand2 = "1";
            intState = 3;
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("2")) {
            strOperand2 = "2";
            intState = 3;
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("3")) {
            strOperand2 = "3";
            intState = 3;
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("4")) {
            strOperand2 = "4";
            intState = 3;
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("5")) {
            strOperand2 = "5";
            intState = 3;
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("6")) {
            strOperand2 = "6";
            intState = 3;
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("7")) {
            strOperand2 = "7";
            intState = 3;
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("8")) {
            strOperand2 = "8";
            intState = 3;
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("9")) {
            strOperand2 = "9";
            intState = 3;
            jtxaDisplayBar.setText(strOperand2);
          }
          if (blnDot) {
            strOperand2 = "0";
            intState = 3;
            jtxaDisplayBar.setText("0");
          }
          break;
        }

      case 3:
        {
          if (blnDot) {
            strOperand2 += ".";
            blnDot = false;
          }
          if (strCommand.equals("1")) {
            strOperand2 += "1";
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("2")) {
            strOperand2 += "2";
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("3")) {
            strOperand2 += "3";
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("4")) {
            strOperand2 += "4";
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("5")) {
            strOperand2 += "5";
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("6")) {
            strOperand2 += "6";
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("7")) {
            strOperand2 += "7";
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("8")) {
            strOperand2 += "8";
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("9")) {
            strOperand2 += "9";
            jtxaDisplayBar.setText(strOperand2);
          }
          if (strCommand.equals("0")) {
            if (Double.parseDouble(strOperand2) == 0) {
            } else {
              strOperand2 += "0";
              jtxaDisplayBar.setText(strOperand2);
            }
          }
          if (strCommand.equals("+")
              || strCommand.equals("-")
              || strCommand.equals("*")
              || strCommand.equals("/")) {
            if (strOperation.equals("+")) {
              if (strOperand2 == "") {
                strOperation = strCommand;
              } else {
                double d = Double.parseDouble(strOperand1) + Double.parseDouble(strOperand2);
                strOperand1 = "" + d;
                strOperand2 = "";
                strOperation = strCommand;
                intState = 3;
                jtxaDisplayBar.setText(strOperand1);
              }
            }
            if (strOperation.equals("-")) {
              if (strOperand2 == "") {
                strOperation = strCommand;
              } else {
                double d = Double.parseDouble(strOperand1) - Double.parseDouble(strOperand2);
                strOperand1 = "" + d;
                strOperand2 = "";
                strOperation = strCommand;
                intState = 3;
                jtxaDisplayBar.setText(strOperand1);
              }
            }
            if (strOperation.equals("*")) {
              if (strOperand2 == "") {
                strOperation = strCommand;
              } else {
                double d = Double.parseDouble(strOperand1) * Double.parseDouble(strOperand2);
                strOperand1 = "" + d;
                strOperand2 = "";
                strOperation = strCommand;
                intState = 3;
                jtxaDisplayBar.setText(strOperand1);
              }
            }
            if (strOperation.equals("/")) {
              if (strOperand2 == "") {
                strOperation = strCommand;
              } else {
                double d = Double.parseDouble(strOperand1) / Double.parseDouble(strOperand2);
                strOperand1 = "" + d;
                strOperand2 = "";
                strOperation = strCommand;
                intState = 3;
                jtxaDisplayBar.setText(strOperand1);
              }
            }
          }

          if (strCommand.equals("=")) {
            if (strOperation.equals("+")) {
              double d = Double.parseDouble(strOperand1) + Double.parseDouble(strOperand2);
              strOperand1 = "" + d;
              strOperand2 = "";
              intState = 1;
              jtxaDisplayBar.setText(strOperand1);
            }
            if (strOperation.equals("-")) {
              double d = Double.parseDouble(strOperand1) - Double.parseDouble(strOperand2);
              strOperand1 = "" + d;
              strOperand2 = "";
              intState = 1;
              jtxaDisplayBar.setText(strOperand1);
            }
            if (strOperation.equals("*")) {
              double d = Double.parseDouble(strOperand1) * Double.parseDouble(strOperand2);
              strOperand1 = "" + d;
              strOperand2 = "";
              intState = 1;
              jtxaDisplayBar.setText(strOperand1);
            }
            if (strOperation.equals("/")) {
              if (Double.parseDouble(strOperand2) == 0) {
                System.err.println("Can not divid by zero.");
              } else {
                double d = Double.parseDouble(strOperand1) / Double.parseDouble(strOperand2);
                strOperand1 = "" + d;
                strOperand2 = "";
                intState = 1;
                jtxaDisplayBar.setText(strOperand1);
              }
            }
          }
          break;
        }
      default:
        System.err.println("Into invalid state!");
    }
  }
}
