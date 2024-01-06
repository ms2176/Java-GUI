import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JLabel displayLabel;
    private JTextField inputField;
    private JButton clearButton, addButton, subtractButton, multiplyButton, divideButton;

    private double accumulator = 0.0;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayLabel = new JLabel("0.0", SwingConstants.RIGHT);
        add(displayLabel, BorderLayout.NORTH);

        inputField = new JTextField(10);
        add(inputField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        addButton = new JButton("+");
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        subtractButton = new JButton("-");
        subtractButton.addActionListener(this);
        buttonPanel.add(subtractButton);

        multiplyButton = new JButton("*");
        multiplyButton.addActionListener(this);
        buttonPanel.add(multiplyButton);

        divideButton = new JButton("/");
        divideButton.addActionListener(this);
        buttonPanel.add(divideButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            accumulator = 0.0;
            displayLabel.setText("0.0");
            inputField.setText("");
        } else {
            try {
                double value = Double.parseDouble(inputField.getText());

                if (e.getSource() == addButton) {
                    accumulator += value;
                } else if (e.getSource() == subtractButton) {
                    accumulator -= value;
                } else if (e.getSource() == multiplyButton) {
                    accumulator *= value;
                } else if (e.getSource() == divideButton) {
                    if (value != 0) {
                        accumulator /= value;
                    } else {
                        displayLabel.setText("Error: Division by zero");
                        return;
                    }
                }
                displayLabel.setText(String.valueOf(accumulator));
                inputField.setText("");
            } catch (NumberFormatException ex) {
                displayLabel.setText("Error: Invalid input");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SimpleCalculator::new);
    }
}
