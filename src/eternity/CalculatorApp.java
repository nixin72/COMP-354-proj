import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

enum InputType {
    OPERATOR,
    SPECIALACTION,
    OPERAND,
    RESULT
}

enum Location {
    EXTRABTN_FIRSTROW,
    EXTRABTN_SECONDROW,
    BASICBTN
}

public class CalculatorApp extends JFrame implements ActionListener {

    public interface EvaluateMathExp {
        public void sendTokenizeMathCommand(ArrayList<String> command);
    }

    private EvaluateMathExp listenerEvaluateCommand;

    static final Color PANELCOLOR = new Color(83, 104, 114);
    static final Color BASICFNBTNCOLOR = new Color(72, 163, 145);
    static final Color RESULTNBTNCOLOR = new Color(37, 103, 61);
    static final Color NUMERICBTNCOLOR = new Color(112, 128, 144);
    static final Color EXTRAFNBTNCOLOR = new Color(54, 69, 79);
    static final Color SPECIALBTNCOLOR = new Color(204, 42, 54);
    static final Color FONTBTNCOLOR = new Color(227, 227, 226);

    private static final Map<String, Object[]> INPUT_STRINGS = new LinkedHashMap<String, Object[]>();

    static {
        // Map<"functions", []{command_String, typeOfInput , LOCATION , tooltip}>
        INPUT_STRINGS.put("7", new Object[]{"7", InputType.OPERAND, Location.BASICBTN, "7"});
        INPUT_STRINGS.put("8", new Object[]{"8", InputType.OPERAND, Location.BASICBTN, "8"});
        INPUT_STRINGS.put("9", new Object[]{"9", InputType.OPERAND, Location.BASICBTN, "9"});
        INPUT_STRINGS.put(
                "DEL", new Object[]{"DEL", InputType.SPECIALACTION, Location.BASICBTN, "Delete"});
        INPUT_STRINGS.put(
                "AC", new Object[]{"AC", InputType.SPECIALACTION, Location.BASICBTN, "All Clear"});

        INPUT_STRINGS.put("4", new Object[]{"4", InputType.OPERAND, Location.BASICBTN, "4"});
        INPUT_STRINGS.put("5", new Object[]{"5", InputType.OPERAND, Location.BASICBTN, "5"});
        INPUT_STRINGS.put("6", new Object[]{"6", InputType.OPERAND, Location.BASICBTN, "6"});
        INPUT_STRINGS.put(
                "*", new Object[]{"*", InputType.OPERATOR, Location.BASICBTN, "Multiplication"});

        INPUT_STRINGS.put("÷", new Object[]{"÷", InputType.OPERATOR, Location.BASICBTN, "Division"});
        INPUT_STRINGS.put("1", new Object[]{"1", InputType.OPERAND, Location.BASICBTN, "1"});
        INPUT_STRINGS.put("2", new Object[]{"2", InputType.OPERAND, Location.BASICBTN, "2"});
        INPUT_STRINGS.put("3", new Object[]{"3", InputType.OPERAND, Location.BASICBTN, "3"});

        INPUT_STRINGS.put("+", new Object[]{"+", InputType.OPERATOR, Location.BASICBTN, "Addition"});
        INPUT_STRINGS.put(
                "-", new Object[]{"-", InputType.OPERATOR, Location.BASICBTN, "Subtraction"});
        INPUT_STRINGS.put("0", new Object[]{"0", InputType.OPERAND, Location.BASICBTN, "0"});
        INPUT_STRINGS.put(".", new Object[]{".", InputType.OPERAND, Location.BASICBTN, "."});

        // INPUT_STRINGS.put("+/-", new Object[]{"+/-", InputType.OPERAND, Location.BASICBTN, "Sign"});
        INPUT_STRINGS.put("π", new Object[]{"π", InputType.OPERAND, Location.BASICBTN, "PI"});
        INPUT_STRINGS.put(
                "Ans", new Object[]{"Ans", InputType.OPERATOR, Location.BASICBTN, "Last Answer"});
        INPUT_STRINGS.put("=", new Object[]{"=", InputType.RESULT, Location.BASICBTN, "="});

        INPUT_STRINGS.put(
                "sin", new Object[]{"Sin(", InputType.OPERATOR, Location.EXTRABTN_FIRSTROW, "Sin"});
        INPUT_STRINGS.put(
                "x^y", new Object[]{"^(", InputType.OPERATOR, Location.EXTRABTN_FIRSTROW, "Exponential"});
        INPUT_STRINGS.put(
                "e^x", new Object[]{"e^(", InputType.OPERATOR, Location.EXTRABTN_FIRSTROW, "Euler"});
        INPUT_STRINGS.put(
                "MAD",
                new Object[]{
                        "MAD(", InputType.OPERATOR, Location.EXTRABTN_FIRSTROW, "Mean Absolute Deviation"
                });
        INPUT_STRINGS.put(
                "sqrt",
                new Object[]{"Sqrt(", InputType.OPERATOR, Location.EXTRABTN_FIRSTROW, "Square roor"});
        INPUT_STRINGS.put(
                ",", new Object[]{",", InputType.OPERATOR, Location.EXTRABTN_FIRSTROW, "Comma"});
        INPUT_STRINGS.put(
                "(",
                new Object[]{"(", InputType.OPERATOR, Location.EXTRABTN_FIRSTROW, "left parenthesis"});

        INPUT_STRINGS.put(
                "ln", new Object[]{"ln(", InputType.OPERATOR, Location.EXTRABTN_SECONDROW, "log_e"});
        INPUT_STRINGS.put(
                "σ",
                new Object[]{"σ(", InputType.OPERATOR, Location.EXTRABTN_SECONDROW, "Standard Deviation"});
        INPUT_STRINGS.put(
                "10^x", new Object[]{"10^(", InputType.OPERATOR, Location.EXTRABTN_SECONDROW, "10^x"});
        INPUT_STRINGS.put(
                "Abs",
                new Object[]{"Abs(", InputType.OPERATOR, Location.EXTRABTN_SECONDROW, "Absolute Value"});
        INPUT_STRINGS.put(
                "x!", new Object[]{"!", InputType.OPERATOR, Location.EXTRABTN_SECONDROW, "Factorial"});
        INPUT_STRINGS.put(
                "μ", new Object[]{"μ(", InputType.OPERATOR, Location.EXTRABTN_SECONDROW, "Mean"});
        INPUT_STRINGS.put(
                ")",
                new Object[]{")", InputType.OPERATOR, Location.EXTRABTN_SECONDROW, "right parenthesis"});
    }

    private static final long serialVersionUID = 1L;

    private JTextField txfResult;
    private JTextArea txaFormula;
    private ArrayList<String> mathCommand = new ArrayList<>(10);
    private String lastAnswer = "0.0";

    public CalculatorApp(EvaluateMathExp listenerEvaluateCommand) {

        super("Calculator");
        this.setSize(700, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        URL iconURL = getClass().getResource("calLogo.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());

        FocusEvent focusEvent = new FocusEvent(this, FocusEvent.FOCUS_GAINED);
        dispatchEvent(focusEvent);

        this.listenerEvaluateCommand = listenerEvaluateCommand;

        InitializeComponent();
    }

    protected void setListenerEvaluateCommand(EvaluateMathExp listenerEvaluateCommand) {
        this.listenerEvaluateCommand = listenerEvaluateCommand;
    }

    private void InitializeComponent() {
        Border paddingMainPnl = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        JPanel jpMain = new JPanel();
        jpMain.setBorder(paddingMainPnl);
        jpMain.setLayout(new BorderLayout());
        this.add(jpMain);

        JPanel jpLable = new JPanel();
        jpMain.setBorder(paddingMainPnl);
        jpMain.add(jpLable, BorderLayout.NORTH);

        JLabel lbTitle = new JLabel("Eternity Calculator");
        jpLable.add(lbTitle);

        JPanel jpComponents = new JPanel();
        jpComponents.setBorder(paddingMainPnl);
        jpComponents.setLayout(new BoxLayout(jpComponents, BoxLayout.Y_AXIS));
        jpMain.add(jpComponents, BorderLayout.CENTER);

        JPanel jpScreen = new JPanel();
        jpScreen.setBorder(paddingMainPnl);
        jpScreen.setLayout(new GridLayout(2, 1));
        jpComponents.add(jpScreen);
        jpComponents.add(Box.createVerticalGlue());

        txaFormula = new JTextArea();
        txaFormula.setFont(new Font("Times New Roman (Headings CS)", Font.BOLD, 20));
        txaFormula.setText("0.0");
        txaFormula.setRows(3);
        txaFormula.setLineWrap(true);
        txaFormula.setWrapStyleWord(true); // nicer
        txaFormula.setEditable(false);
        JScrollPane scTextFormula =
                new JScrollPane(
                        txaFormula,
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        txfResult = new JTextField();
        txfResult.setFont(new Font("Times New Roman (Headings CS)", Font.BOLD, 35));
        txfResult.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txfResult.setEditable(false);
        txfResult.setText("0.0");

        jpScreen.add(scTextFormula);
        jpScreen.add(txfResult);

        JPanel jpExtraBtns1 = new JPanel();
        jpExtraBtns1.setLayout(new BoxLayout(jpExtraBtns1, BoxLayout.X_AXIS));
        Object[] extraBtnsTxtByOrder =
                INPUT_STRINGS.entrySet().stream()
                        .filter(x -> x.getValue()[2] == Location.EXTRABTN_FIRSTROW)
                        .map(x -> x.getKey())
                        .collect(Collectors.toList())
                        .toArray();

        for (Object entry : extraBtnsTxtByOrder) {
            String tooltipTxt = INPUT_STRINGS.get(entry)[3].toString();
            if (entry.toString().equals(",")) {
                jpExtraBtns1.add(
                        new Calculator_CustomButton(
                                entry.toString(),
                                EXTRAFNBTNCOLOR,
                                FONTBTNCOLOR,
                                14,
                                "Elephant",
                                new Dimension(70, 40),
                                tooltipTxt,
                                this));
            } else {
                jpExtraBtns1.add(
                        new Calculator_CustomButton(
                                entry.toString(),
                                EXTRAFNBTNCOLOR,
                                FONTBTNCOLOR,
                                14,
                                "Times New Roman (Headings CS)",
                                new Dimension(70, 40),
                                tooltipTxt,
                                this));
            }
            jpExtraBtns1.add(Box.createHorizontalGlue());
        }
        jpExtraBtns1.remove(jpExtraBtns1.getComponentCount() - 1);

        JPanel jpExtraBtns2 = new JPanel();
        jpExtraBtns2.setLayout(new BoxLayout(jpExtraBtns2, BoxLayout.X_AXIS));
        extraBtnsTxtByOrder =
                INPUT_STRINGS.entrySet().stream()
                        .filter(x -> x.getValue()[2] == Location.EXTRABTN_SECONDROW)
                        .map(x -> x.getKey())
                        .collect(Collectors.toList())
                        .toArray();

        for (Object entry : extraBtnsTxtByOrder) {
            String tooltipTxt = INPUT_STRINGS.get(entry)[3].toString();
            jpExtraBtns2.add(
                    new Calculator_CustomButton(
                            entry.toString(),
                            EXTRAFNBTNCOLOR,
                            FONTBTNCOLOR,
                            12,
                            "Times New Roman (Headings CS)",
                            new Dimension(70, 40),
                            tooltipTxt,
                            this));
            jpExtraBtns2.add(Box.createHorizontalGlue());
        }
        jpExtraBtns2.remove(jpExtraBtns2.getComponentCount() - 1);

        JPanel jpExtraBtns = new JPanel();
        jpExtraBtns.setBorder(paddingMainPnl);
        jpExtraBtns.setLayout(new GridLayout(2, 1));
        jpExtraBtns.add(jpExtraBtns1);
        jpExtraBtns.add(jpExtraBtns2);
        jpComponents.add(jpExtraBtns);
        jpComponents.add(Box.createVerticalGlue());

        JPanel jpBasicAnsNumericBtns = new JPanel();
        jpBasicAnsNumericBtns.setBorder(paddingMainPnl);
        jpBasicAnsNumericBtns.setLayout(new GridLayout(4, 5, 3, 3));
        jpComponents.add(jpBasicAnsNumericBtns);
        jpComponents.add(Box.createVerticalGlue());
        Map<String, Object[]> basicBtnsTxtByOrder = new LinkedHashMap<>();
        INPUT_STRINGS.entrySet().stream()
                .filter(x -> x.getValue()[2] == Location.BASICBTN)
                .forEachOrdered(x -> basicBtnsTxtByOrder.put(x.getKey(), x.getValue()));

        for (Map.Entry<String, Object[]> entry : basicBtnsTxtByOrder.entrySet()) {
            Color btnColor = Color.black;
            String btnText = entry.getKey();
            switch ((InputType) entry.getValue()[1]) {
                case OPERAND:
                    btnColor = NUMERICBTNCOLOR;
                    break;
                case OPERATOR:
                    btnColor = BASICFNBTNCOLOR;
                    break;
                case RESULT:
                    btnColor = RESULTNBTNCOLOR;
                    break;
                case SPECIALACTION:
                    btnColor = SPECIALBTNCOLOR;
                    break;
            }
            String tooltipTxt = entry.getValue()[3].toString();
            jpBasicAnsNumericBtns.add(
                    new Calculator_CustomButton(
                            btnText,
                            btnColor,
                            Color.black,
                            25,
                            "Times New Roman (Headings CS)",
                            null,
                            tooltipTxt,
                            this));
        }
        setPanelColorsRecursive(this.rootPane);
    }

    protected void update(Double result) {
        txfResult.setText(result.toString());
        lastAnswer = result.toString();
        txaFormula.setText("0.0");
        mathCommand = new ArrayList<>(10);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String strAction = e.getActionCommand();
        String strCommand = getCommandString(strAction);
        if (strCommand.equals("=")) {
            if (mathCommand.size() == 1) {
                return;
            }
            listenerEvaluateCommand.sendTokenizeMathCommand(mathCommand);
        } else if (strCommand.equals("AC")) {
            PerformClearAllAction();
        } else if (strCommand.equals("DEL")) {
            PerformDeleteAction();
        } else if (strCommand.equals("Ans")) {
            PerformLastAnswerAction();
        } else {

            tokenizeCommand(strCommand, strAction);

            if (txaFormula.getText().equals("0.0")) {
                txaFormula.setText(strCommand);
            } else {
                txaFormula.setText(txaFormula.getText() + strCommand);
            }
        }
    }

    public void tokenizeCommand(String strCommand, String strAction) {
        if (INPUT_STRINGS.get(strAction)[1] == InputType.OPERATOR) {
            if (strCommand.contains("(") && strCommand.length() > 1) {
                mathCommand.add(strCommand.substring(0, strCommand.indexOf("(")));
                mathCommand.add(strCommand.substring(strCommand.indexOf("("), strCommand.length()));
                return;
            } else {
                mathCommand.add(strCommand);
            }
        } else {
            String entry = strCommand;
            if (mathCommand.size() > 0) {
                String lastEntry = mathCommand.get(mathCommand.size() - 1);
                if (INPUT_STRINGS.containsKey(lastEntry)
                        && INPUT_STRINGS.get(lastEntry)[1] == InputType.OPERATOR) {
                    entry = strCommand;
                } else {
                    mathCommand.remove(lastEntry);
                    entry = lastEntry + strCommand;
                }
            }
            mathCommand.add(entry);
        }
    }

    public String getCommandString(String strAction) {
        if (INPUT_STRINGS.containsKey(strAction)) {
            return INPUT_STRINGS.get(strAction)[0].toString();
        }
        return "Error";
    }

    public void setPanelColorsRecursive(JComponent component) {
        if (component instanceof JPanel) {
            component.setBackground(PANELCOLOR);
            component.setForeground(PANELCOLOR);
        }
        if (component.getComponents().length == 0) {
            return;
        } else {
            for (Component cmp : component.getComponents()) {
                setPanelColorsRecursive((JComponent) cmp);
            }
        }
    }

    public void PerformClearAllAction() {
        mathCommand = new ArrayList<>(10);
        lastAnswer = "0.0";
        txaFormula.setText("0.0");
        txfResult.setText("0.0");
    }

    public void PerformDeleteAction() {
        String strView = "";
        if (mathCommand.size() > 0) {
            mathCommand.remove(mathCommand.size() - 1);
        }
        for (String text : mathCommand) {
            strView += text;
        }
        txaFormula.setText(strView);
    }

    public void PerformLastAnswerAction() {
        String strView = "";
        if (mathCommand.size() > 0) {
            return;
        } else {
            strView += lastAnswer;
            mathCommand.add(lastAnswer);
        }
        txaFormula.setText(strView);
    }

    public class Calculator_CustomButton extends JButton {

        public final Color pressedColor = new Color(14, 176, 230);

        public final Color rolloverColor = new Color(133, 68, 68);

        public Calculator_CustomButton(
                String btnText,
                Color backgroundColor,
                Color foregroundColor,
                int fontSize,
                String fontName,
                Dimension size,
                String tooltipTxt,
                ActionListener listener) {
            super(btnText);
            setBorderPainted(false);
            setFocusPainted(false);

            setContentAreaFilled(false);
            setOpaque(true);

            setBackground(backgroundColor);
            setForeground(foregroundColor);
            setFont(new Font(fontName, Font.BOLD, fontSize));
            setText(btnText);
            setToolTipText(tooltipTxt);

            if (size != null) {

                setPreferredSize(size);
            }

            addChangeListener(
                    new ChangeListener() {
                        @Override
                        public void stateChanged(ChangeEvent evt) {
                            if (getModel().isPressed()) {
                                setBackground(pressedColor);
                            } else if (getModel().isRollover()) {
                                setBackground(rolloverColor);
                            } else {
                                setBackground(backgroundColor);
                            }
                        }
                    });

            addActionListener(listener);
        }
    }

    public void run() {
        this.setVisible(true);
    }

    //    public static void main(String args[]) {
    //        CalculatorApp objSC = new CalculatorApp(null);
    //        objSC.setVisible(true);
    //    }
}
