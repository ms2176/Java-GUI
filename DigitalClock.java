import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class DigitalClock extends JFrame {
    private JLabel hoursLabel;
    private JLabel minutesLabel;
    private JLabel secondsLabel;
    private JLabel amPmLabel;

    public DigitalClock() {
        setTitle("Digital Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        hoursLabel = new JLabel("00");
        customizeLabel(hoursLabel);
        add(hoursLabel);

        minutesLabel = new JLabel("00");
        customizeLabel(minutesLabel);
        add(minutesLabel);

        secondsLabel = new JLabel("00");
        customizeLabel(secondsLabel);
        add(secondsLabel);

        amPmLabel = new JLabel("AM");
        customizeLabel(amPmLabel);
        add(amPmLabel);

        setSize(300, 150);
        setVisible(true);

        updateClock(); // Initial clock update
        startClock(); // Start continuous clock updates
    }

    private void customizeLabel(JLabel label) {
        label.setFont(new Font("Serif", Font.ITALIC, 36));
        label.setForeground(Color.BLUE); // Text color
        label.setBackground(Color.WHITE); // Background color
        label.setOpaque(true); // Make the background visible
    }

    private void updateClock() {
        Calendar now = Calendar.getInstance();

        int hours = now.get(Calendar.HOUR);
        int minutes = now.get(Calendar.MINUTE);
        int seconds = now.get(Calendar.SECOND);
        int amPm = now.get(Calendar.AM_PM);

        String hoursStr = String.format("%02d", hours);
        String minutesStr = String.format("%02d", minutes);
        String secondsStr = String.format("%02d", seconds);
        String amPmStr = (amPm == Calendar.AM) ? "AM" : "PM";

        hoursLabel.setText(hoursStr);
        minutesLabel.setText(minutesStr);
        secondsLabel.setText(secondsStr);
        amPmLabel.setText(amPmStr);
    }

    private void startClock() {
        Timer timer = new Timer(1000, e -> updateClock()); // Update every second
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitalClock::new);
    }
}
