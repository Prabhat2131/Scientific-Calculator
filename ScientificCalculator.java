//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ScientificCalculator extends JFrame implements ActionListener {

    // Components
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton[] operatorButtons;
    private JButton clearButton;
    private JButton equalsButton;

    // Variables
    private String displayText;
    private double firstOperand;
    private String operator;

    // Operator labels
    private final String[] operatorLabels = {"/", "*", "-", "+"};

    public ScientificCalculator() {
        // Frame settings
        setTitle("Scientific Calculator");
        setSize(570, 620);
        setLocation(200, 100);;
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Display
        display = new JTextField();
        display.setBounds(50, 50, 300, 50);
        add(display);

        // Number buttons
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setBounds(50 + (i % 3) * 100, 150 + (i / 3) * 80, 80, 50);
            numberButtons[i].addActionListener(this);
            add(numberButtons[i]);
        }

        // Function buttons
        functionButtons = new JButton[4];
        String[] functionLabels = {"sqrt", "log", "sin", "cos"};
        for (int i = 0; i < 4; i++) {
            functionButtons[i] = new JButton(functionLabels[i]);
            functionButtons[i].setBounds(350, 150 + i * 80, 80, 50);
            functionButtons[i].addActionListener(this);
            add(functionButtons[i]);
        }

        // Operator buttons
        operatorButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            operatorButtons[i] = new JButton(operatorLabels[i]);
            operatorButtons[i].setBounds(450, 150 + i * 80, 50, 50);
            operatorButtons[i].addActionListener(this);
            add(operatorButtons[i]);
        }

        // Clear button
        clearButton = new JButton("C");
        clearButton.setBounds(50, 480, 80, 50);
        clearButton.addActionListener(this);
        add(clearButton);

        // Equals button
        equalsButton = new JButton("=");
        equalsButton.setBounds(150, 480, 250, 50);
        equalsButton.addActionListener(this);
        add(equalsButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getSource() == clearButton) {
            displayText = "";
        } else if (e.getSource() == equalsButton) {
            calculateResult();
        } else {
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == numberButtons[i]) {
                    displayText += i;
                    break;
                }
            }
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == functionButtons[i]) {
                    performFunction(i);
                    break;
                }
                if (e.getSource() == operatorButtons[i]) {
                    performOperation(operatorLabels[i]);
                    break;
                }
            }
        }
        display.setText(displayText);
    }

    private void calculateResult() {
        String[] parts = displayText.split(operator);
        double secondOperand = Double.parseDouble(parts[1]);
        double result =0.0;
                switch (operator) {
            case "/":
                result = firstOperand / secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "+":
                result = firstOperand + secondOperand;
                break;
        }
        displayText = String.valueOf(result);
        operator = "";
    }

    private void performFunction(int index) {
        switch (index) {
            case 0: // Square Root
                double sqrtOperand = Double.parseDouble(displayText);
                double sqrtResult = Math.sqrt(sqrtOperand);
                displayText = String.valueOf(sqrtResult);
                break;
            case 1: // Logarithm
                double logOperand = Double.parseDouble(displayText);
                double logResult = Math.log10(logOperand);
                displayText = String.valueOf(logResult);
                break;
            case 2: // Sine
                double sinOperand = Double.parseDouble(displayText);
                double sinResult = Math.sin(Math.toRadians(sinOperand));
                displayText = String.valueOf(sinResult);
                break;
            case 3: // Cosine
                double cosOperand = Double.parseDouble(displayText);
                double cosResult = Math.cos(Math.toRadians(cosOperand));
                displayText = String.valueOf(cosResult);
                break;
        }
    }

    private void performOperation(String op) {
        if (displayText.isEmpty()) {
            return;
        }

        firstOperand = Double.parseDouble(displayText);
        operator = op;
        displayText += operator;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScientificCalculator();
            }
        });
    }
}

