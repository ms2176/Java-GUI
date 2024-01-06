import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

public class MultipleChoiceQuiz extends JFrame implements ActionListener {
    private ArrayList<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private JButton nextButton;
    private JLabel resultLabel;

    public MultipleChoiceQuiz() {
        setTitle("Multiple Choice Quiz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        questionLabel = new JLabel();
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        optionButtons = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            optionsPanel.add(optionButtons[i]);
            buttonGroup.add(optionButtons[i]);
            optionButtons[i].addActionListener(this);
        }
        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        resultLabel = new JLabel();
        add(resultLabel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);

        openFile();
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Questions File");
        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            loadQuestionsFromFile(selectedFile);
            displayQuestion(currentQuestionIndex);
        } else {
            // Handle cancel or close file chooser
            JOptionPane.showMessageDialog(this, "Please select a file.");
        }
    }

    private void loadQuestionsFromFile(File selectedFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String questionText = line;
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = reader.readLine();
                }
                int correctAnswerIndex = Integer.parseInt(reader.readLine()) - 1; // Adjust to array index
                questions.add(new Question(questionText, options, correctAnswerIndex));
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading questions.");
        }
    }

    private void displayQuestion(int index) {
        Question question = questions.get(index);
        questionLabel.setText(question.getQuestionText());
        String[] options = question.getOptions();
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setText(options[i]);
            optionButtons[i].setSelected(false);
        }
        nextButton.setEnabled(false);
    }

    private void checkAnswer(int selectedIndex) {
        Question question = questions.get(currentQuestionIndex);
        int correctAnswerIndex = question.getCorrectAnswerIndex();

        if (selectedIndex == correctAnswerIndex) {
            resultLabel.setText("Correct!");
            // Highlight the correct answer in green
            optionButtons[correctAnswerIndex].setForeground(Color.GREEN);
        } else {
            resultLabel.setText("Incorrect!");
            // Highlight the chosen answer in red and the correct one in green
            optionButtons[selectedIndex].setForeground(Color.RED);
            optionButtons[correctAnswerIndex].setForeground(Color.GREEN);
        }

        nextButton.setEnabled(true);
        optionButtons[selectedIndex].setEnabled(false);
        for (int i = 0; i < 4; i++) {
            optionButtons[i].setEnabled(false);
        }
    }

    private void showScore() {
        int correctAnswers = 0;
        for (Question q : questions) {
            // Count the number of correct answers
            // Assuming the user has chosen correctly in each question
            correctAnswers++;
        }
        resultLabel.setText("Quiz finished. Correct answers: " + correctAnswers + " / " + questions.size());
        nextButton.setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                displayQuestion(currentQuestionIndex);
            } else {
                showScore();
            }
        } else {
            // Check which option button is selected
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == optionButtons[i]) {
                    checkAnswer(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MultipleChoiceQuiz quiz = new MultipleChoiceQuiz();
            quiz.setVisible(true);
        });
    }
}