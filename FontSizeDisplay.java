import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FontSizeDisplay extends JFrame implements ActionListener {
    private JLabel xLabel;
    private JLabel fontSizeLabel;
    private JButton increaseButton;
    private JButton decreaseButton;

    private int currentFontSize = 18; // Initial font size

    public FontSizeDisplay() {
        setTitle("Font Size Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        xLabel = new JLabel("X");
        xLabel.setFont(new Font("Serif", Font.PLAIN, currentFontSize));
        topPanel.add(xLabel);

        fontSizeLabel = new JLabel(String.valueOf(currentFontSize));
        topPanel.add(fontSizeLabel);

        add(topPanel, BorderLayout.NORTH);

        // Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        increaseButton = new JButton("Increase");
        increaseButton.addActionListener(this);
        bottomPanel.add(increaseButton);

        decreaseButton = new JButton("Decrease");
        decreaseButton.addActionListener(this);
        bottomPanel.add(decreaseButton);

        add(bottomPanel, BorderLayout.SOUTH);

        setSize(300, 150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == increaseButton) {
            currentFontSize++;
        } else if (e.getSource() == decreaseButton) {
            if (currentFontSize > 0) {
                currentFontSize--;
            }
        }

        updateFontSize();
    }

    private void updateFontSize() {
        xLabel.setFont(new Font("Serif", Font.PLAIN, currentFontSize));
        fontSizeLabel.setText(String.valueOf(currentFontSize));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FontSizeDisplay::new);
    }
}
