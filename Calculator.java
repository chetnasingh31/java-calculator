import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField display;
    double num1, num2, result;
    char operator = '\0';

    public Calculator() {
        setTitle("Simple Calculator");
        setSize(600, 400); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); 
        setLocationRelativeTo(null); 

       
        JPanel calculatorPanel = new JPanel();
        calculatorPanel.setLayout(new BorderLayout());
        calculatorPanel.setPreferredSize(new Dimension(260, 300));
        calculatorPanel.setBorder(BorderFactory.createTitledBorder("Calculator"));

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 18));
        calculatorPanel.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 16));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        calculatorPanel.add(buttonPanel, BorderLayout.CENTER);

        
        add(calculatorPanel); 

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.charAt(0) >= '0' && input.charAt(0) <= '9') {
            display.setText(display.getText() + input);
        } else if (input.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = '\0';
        } else if (input.equals("=")) {
            String text = display.getText();
            try {
                String[] parts = text.split("[" + "\\" + operator + "]");
                if (parts.length == 2) {
                    num1 = Double.parseDouble(parts[0]);
                    num2 = Double.parseDouble(parts[1]);

                    switch (operator) {
                        case '+': result = num1 + num2; break;
                        case '-': result = num1 - num2; break;
                        case '*': result = num1 * num2; break;
                        case '/': result = (num2 != 0) ? num1 / num2 : 0; break;
                    }

                    display.setText(text + "=" + result);
                }
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else {
            if (!display.getText().isEmpty() && operator == '\0') {
                operator = input.charAt(0);
                display.setText(display.getText() + operator);
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
