import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class ProgressMeter extends JFrame {
    private JLabel[] progressLabels;
    private JLabel progressPercentageLabel;

    private int currentStep = 0;

    public ProgressMeter() {
        setTitle("Progress Meter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(11, 1));

        progressLabels = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            progressLabels[i] = new JLabel();
            progressLabels[i].setOpaque(true);
            progressLabels[i].setBackground(Color.RED);
            add(progressLabels[i]);
        }

        progressPercentageLabel = new JLabel("0%");
        progressPercentageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(progressPercentageLabel);

        setSize(200, 400);
        setVisible(true);

        simulateProgress();
    }

    private void simulateProgress() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press Enter to simulate progress...");

        while (currentStep < 10) {
            scanner.nextLine(); // Wait for user to press Enter

            progressLabels[currentStep].setBackground(Color.GREEN);
            currentStep++;

            int progressPercentage = currentStep * 10;
            progressPercentageLabel.setText(progressPercentage + "%");

            if (currentStep == 10) {
                System.out.println("Progress reached 100%");
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProgressMeter());
    }
}