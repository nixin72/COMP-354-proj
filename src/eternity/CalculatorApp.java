package eternity;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.net.URL;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CalculatorApp extends JFrame implements ActionListener {

    static final Color PANELCOLOR = new Color(83, 104, 114);
    static final Color BASICFNBTNCOLOR = new Color(72, 163, 145);
    static final Color RESULTNBTNCOLOR =new Color(37, 103, 61);
    static final Color NUMERICBTNCOLOR = new Color(112, 128, 144);
    static final Color EXTRAFNBTNCOLOR = new Color(54, 69, 79);
    static final Color SPECIALBTNCOLOR = new Color(204, 42, 54);
    static final Color FONTBTNCOLOR = new Color(227, 227, 226);


    private static final long serialVersionUID = 1L;

    private JTextField txfResult;
    private JTextArea txaFormula;
    private String mathCommand = "";

    public CalculatorApp() {
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

        InitializeComponent();

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

        JLabel lbTitle = new JLabel("Scientific Calculator");
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
        txaFormula.setText("0.0");

        txfResult = new JTextField();
        txfResult.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        txfResult.setText("0.0");

        jpScreen.add(txaFormula);
        jpScreen.add(txfResult);


        JPanel jpExtraBtns1 = new JPanel();
        jpExtraBtns1.setLayout(new BoxLayout(jpExtraBtns1, BoxLayout.X_AXIS));
        String[] extraBtnsTxt1 = {"sin", "x^y", "e^x", "MAD", "sqrt", ",", "("};
        for (String txt : extraBtnsTxt1) {
            if (txt.equals(",")) {
                jpExtraBtns1.add(new Caclculator_CustomButton(txt, EXTRAFNBTNCOLOR, FONTBTNCOLOR, 14, "Elephant", new Dimension(70, 40), this));
            } else {
                jpExtraBtns1.add(new Caclculator_CustomButton(txt, EXTRAFNBTNCOLOR, FONTBTNCOLOR, 14, "Times New Roman (Headings CS)", new Dimension(70, 40), this));
            }
            jpExtraBtns1.add(Box.createHorizontalGlue());
        }
        jpExtraBtns1.remove(jpExtraBtns1.getComponentCount() - 1);


        JPanel jpExtraBtns2 = new JPanel();
        jpExtraBtns2.setLayout(new BoxLayout(jpExtraBtns2, BoxLayout.X_AXIS));
        String[] extraBtnsTxt2 = {"ln", "σ", "10^x", "Abs", "x!", "μ", ")"};
        for (String txt : extraBtnsTxt2) {
            jpExtraBtns2.add(new Caclculator_CustomButton(txt, EXTRAFNBTNCOLOR, FONTBTNCOLOR, 12, "Times New Roman (Headings CS)", new Dimension(70, 40), this));
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
        String[] basicBtnsTxt = {"N-7", "N-8", "N-9", "SP-DEL", "SP-AC", "N-4", "N-5", "N-6", "F-*", "F-÷", "N-1", "N-2", "N-3", "F-+", "F--", "F-0", "F-.", "F-+/-", "F-Ans", "R-="};
        for (String txt : basicBtnsTxt) {
            Color btnColor;
            String btnText = txt;
            if (txt.startsWith("N-")) {
                btnColor = NUMERICBTNCOLOR;
                btnText = btnText.replaceAll("N-", "");
            } else if (txt.startsWith("F-")) {
                btnColor = BASICFNBTNCOLOR;
                btnText = btnText.replaceAll("F-", "");
            } else if (txt.startsWith("SP-")) {
                btnColor = SPECIALBTNCOLOR;
                btnText = btnText.replaceAll("SP-", "");
            } else {
                btnColor = RESULTNBTNCOLOR;
                btnText = btnText.replaceAll("R-", "");
            }
            jpBasicAnsNumericBtns.add(new Caclculator_CustomButton(btnText, btnColor, Color.black, 25, "Times New Roman (Headings CS)", null, this));
        }

        setPanelColorsRecursive(this.rootPane);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String strAction = e.getActionCommand();
        mathCommand = mathCommand.concat(strAction);
        txaFormula.setText(mathCommand);
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

    public class Caclculator_CustomButton extends JButton {

        public final Color pressedColor = new Color(14, 176, 230);

        public final Color rolloverColor = new Color(133, 68, 68);

        public Caclculator_CustomButton(String btnText, Color backgroundColor, Color foregroundColor, int fontSize, String fontName, Dimension size, ActionListener listener) {
            super(btnText);
            setBorderPainted(false);
            setFocusPainted(false);

            setContentAreaFilled(false);
            setOpaque(true);

            setBackground(backgroundColor);
            setForeground(foregroundColor);
            setFont(new Font(fontName, Font.BOLD, fontSize));
            setText(btnText);

            if (size != null) {

                setPreferredSize(size);
            }

            addChangeListener(new ChangeListener() {
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

    public static void main(String args[]) {
        CalculatorApp objSC = new CalculatorApp();
        objSC.setVisible(true);
    }
}
