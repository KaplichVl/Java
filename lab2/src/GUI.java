import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class GUI extends JFrame {
    private static final String TITLE = "Подсчет формул";
    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;

    private final Formula formula = new Formula();

    private Double sum = 0.0;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;

    private void addRadioButton(Box hboxFormulaType, ButtonGroup radioButtons, String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(ev -> formula.setFormulaId(formulaId));
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    private JTextField createTextField(int columns) {
        JTextField field = new JTextField("0", columns);
        field.setMaximumSize(field.getPreferredSize());
        return field;
    }

    private void setVariableContent(Box hboxVariables, JLabel label, JTextField field) {

        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(label);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(field);
        hboxVariables.add(Box.createHorizontalStrut(100));
    }

    private void setButtonsContent(Box hboxButtons, JButton button) {
        hboxButtons.add(button);
        hboxButtons.add(Box.createHorizontalStrut(15));
        hboxButtons.add(Box.createHorizontalGlue());
    }

    private void calculateResult() {
        try {
            Double x = Double.parseDouble(textFieldX.getText());
            Double y = Double.parseDouble(textFieldY.getText());
            Double z = Double.parseDouble(textFieldZ.getText());
           /* Double result;
            if (formula.getFormulaId() == 1)
                Double result = formula.calculate1(x, y, z);
            else
                Double result = formula.calculate2(x, y, z);
            textFieldResult.setText(result.toString());*/

             switch(formula.getFormulaId()) {
                 case 1 -> textFieldResult.setText(formula.calculate1(x, y, z).toString());
                 case 2 -> textFieldResult.setText(formula.calculate2(x, y, z).toString());
             };
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(GUI.this,
                    "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                    JOptionPane.WARNING_MESSAGE);

        }
    }

    private Box createBoxButtons() {
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());

        JButton buttonMR = new JButton("MR");
        buttonMR.addActionListener(ev -> textFieldResult.setText(sum.toString()));

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(ev -> sum = 0.0);

        JButton buttonM = new JButton("M+");
        buttonM.addActionListener(ev -> sum += Double.parseDouble(textFieldResult.getText()));

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(ev -> calculateResult());

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(ev -> {
            textFieldX.setText("0");
            textFieldY.setText("0");
            textFieldZ.setText("0");
            textFieldResult.setText("0");
        });

        setButtonsContent(hboxButtons, buttonMR);
        setButtonsContent(hboxButtons, buttonMC);
        setButtonsContent(hboxButtons, buttonM);
        setButtonsContent(hboxButtons, buttonCalc);
        setButtonsContent(hboxButtons, buttonReset);

        return hboxButtons;
    }

    private Box createBoxFormula() {
        ButtonGroup radioButtons = new ButtonGroup();
        Box hboxFormulaType = Box.createHorizontalBox();
        addRadioButton(hboxFormulaType, radioButtons, "Формула 1", 1);
        addRadioButton(hboxFormulaType, radioButtons, "Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        return hboxFormulaType;
    }

    private Box createBoxVariables() {
        Box hboxVariables = Box.createHorizontalBox();
        setVariableContent(hboxVariables, new JLabel("X:"), textFieldX = createTextField(10));
        setVariableContent(hboxVariables, new JLabel("Y:"), textFieldY = createTextField(10));
        setVariableContent(hboxVariables, new JLabel("Z:"), textFieldZ = createTextField(10));
        return hboxVariables;
    }

    private Box createBoxResult(){
        textFieldResult = createTextField(20);
        textFieldResult.setEditable(false);

        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(new JLabel("Результат:"));
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        return hboxResult;
    }

    private Box createBoxContent(){
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(createBoxFormula());
        contentBox.add(createBoxVariables());
        contentBox.add(createBoxResult());
        contentBox.add(createBoxButtons());
        contentBox.add(Box.createVerticalGlue());
        return contentBox;
    }

    public GUI() {
        super(TITLE);
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2);
       // setLocation(660,390);
     //   System.out.println(kit.getScreenSize());



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(createBoxContent(), BorderLayout.CENTER);
    }
}

