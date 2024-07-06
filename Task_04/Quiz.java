import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz extends JFrame {
    private JLabel questionLabel;
    private JRadioButton[] options;
    private JButton submitButton, resetButton, exitButton;
    private JLabel timerLabel; // Add timer label
    private int currentQuestionIndex;
    private int score;
    private Timer timer;

    private String[] questions = {
            "Which Article of the Indian Constitution deals with the 'Right to Equality'?",
            "The Directive Principles of State Policy in the Indian Constitution were borrowed from which country's constitution?",
            "Which of the following is the largest tributary of the Indus River?",
            "In which year was the Planning Commission of India established?",
            "Who among the following was the President of the Constituent Assembly of India?"
    };
    private String[][] choices = {
            {"Article 14-18", "Article 19-22", "Article 23-24", "Article 25-28"},
            {"USA", "Ireland", "UK", "Canada"},
            {"Chenab", "Sutlej", "Jhelum", "Beas"},
            {"1947", "1950", "1952", "1965"},
            {"Dr. B.R. Ambedkar", "Dr. Rajendra Prasad", "Jawaharlal Nehru", "Sardar Vallabhbhai Patel"}
    };
    private int[] correctAnswers = {0, 1, 1, 1, 1};

    public Quiz() {
        setTitle("Quiz Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));

        Font font = new Font("Arial", Font.PLAIN, 20);

        questionLabel = new JLabel();
        questionLabel.setFont(font);
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        options = new JRadioButton[4];
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(font);
            buttonGroup.add(options[i]);
            optionsPanel.add(options[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        submitButton = new JButton("Submit");
        submitButton.setFont(font);
        bottomPanel.add(submitButton);

        resetButton = new JButton("Reset");
        resetButton.setFont(font);
        bottomPanel.add(resetButton);

        exitButton = new JButton("Exit");
        exitButton.setFont(font);
        bottomPanel.add(exitButton);

        add(bottomPanel, BorderLayout.SOUTH);

        timerLabel = new JLabel();
        timerLabel.setFont(font);
        add(timerLabel, BorderLayout.EAST); // Add the timer label

        currentQuestionIndex = 0;
        showQuestion();
        startTimer();

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                showNextQuestion();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetQuiz();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void showQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(choices[currentQuestionIndex][i]);
            options[i].setSelected(false);
        }
    }

    private void showNextQuestion() {
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            showQuestion();
            resetTimer();
        } else {
            showResult();
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && i == correctAnswers[currentQuestionIndex]) {
                score++;
            }
        }
    }

    private void showResult() {
        JOptionPane.showMessageDialog(this, "Quiz Completed!\nScore: " + score + "/" + questions.length);
        System.exit(0);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() { // Timer ticks every 1 second
            private int secondsLeft = 15; // Initial timer value

            @Override
            public void actionPerformed(ActionEvent e) {
                if (secondsLeft >= 0) {
                    timerLabel.setText("Time left: " + secondsLeft + " seconds");
                    secondsLeft--;
                } else {
                    timer.stop();
                    checkAnswer();
                    showNextQuestion();
                }
            }
        });
        timer.start();
    }

    private void resetTimer() {
        timer.stop();
        startTimer();
    }

    private void resetQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        showQuestion();
        resetTimer();
        timerLabel.setText("Time left: 15 seconds");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Quiz().setVisible(true);
            }
        });
    }
}
