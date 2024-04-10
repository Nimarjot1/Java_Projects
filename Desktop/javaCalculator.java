import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 class CalculatorGUI extends JFrame {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decimalButton, equalButton, delButton, clrButton;
    private JPanel panel;

    private double num1, num2, result;
    private char operator;

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(400, 50));
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setEditable(false);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }

        functionButtons = new JButton[4];
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;

        decimalButton = new JButton(".");
        equalButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.add(textField);

        for (int i = 0; i < 10; i++) {
            panel.add(numberButtons[i]);
            numberButtons[i].addActionListener(new NumberButtonListener());
        }

        for (int i = 0; i < 4; i++) {
            panel.add(functionButtons[i]);
            functionButtons[i].addActionListener(new FunctionButtonListener());
        }

        panel.add(decimalButton);
        panel.add(equalButton);
        panel.add(delButton);
        panel.add(clrButton);

        decimalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(textField.getText() + ".");
            }
        });

        equalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num2 = Double.parseDouble(textField.getText());

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                }

                textField.setText(String.valueOf(result));
            }
        });

        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String currentText = textField.getText();
                textField.setText(currentText.substring(0, currentText.length() - 1));
            }
        });

        clrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
            }
        });

        add(panel);
        setVisible(true);
    }

    private class NumberButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String currentText = textField.getText();
            textField.setText(currentText + ((JButton) e.getSource()).getText());
        }
    }

    private class FunctionButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            num1 = Double.parseDouble(textField.getText());
            operator = ((JButton) e.getSource()).getText().charAt(0);
            textField.setText("");
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
