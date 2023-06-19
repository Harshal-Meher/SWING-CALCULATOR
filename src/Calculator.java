
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField textField;
    
    private double firstNumber;
    private String operator;
    
    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        textField = new JTextField(20);
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };
        
        for (String button : buttons) {
            JButton btn = new JButton(button);
            buttonPanel.add(btn);
            
            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String buttonText = btn.getText();
                    
                    if (buttonText.matches("[0-9]")) {
                        textField.setText(textField.getText() + buttonText);
                    } else if (buttonText.equals(".")) {
                        if (!textField.getText().contains(".")) {
                            textField.setText(textField.getText() + buttonText);
                        }
                    } else if (buttonText.matches("[/+*-]")) {
                        firstNumber = Double.parseDouble(textField.getText());
                        operator = buttonText;
                        textField.setText("");
                    } else if (buttonText.equals("=")) {
                        double secondNumber = Double.parseDouble(textField.getText());
                        double result = calculateResult(firstNumber, secondNumber, operator);
                        textField.setText(String.valueOf(result));
                    }
                }
            });
        }
        
        frame.add(textField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private double calculateResult(double firstNumber, double secondNumber, String operator) {
        double result = 0;
        
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculator();
            }
        });
    }
}
