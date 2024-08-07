import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Task_01 extends JFrame {
    private JTextField guessField;
    private JButton guessButton;
    private JLabel resultLabel;
    private int target;
    private int attempts;
    private int maxAttempts;

    public Task_01(int min, int max, int maxAttempts) {
        this.target = new Random().nextInt(max - min + 1) + min;
        this.maxAttempts = maxAttempts;
        this.attempts = 0;

        setTitle("Number Guessing Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel instructionLabel = new JLabel("Guess a number between " + min + " and " + max + ":");
        add(instructionLabel);

        guessField = new JTextField(10);
        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());
        add(guessButton);

        resultLabel = new JLabel("");
        add(resultLabel);
    }

    private class GuessButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                if (guess < target) {
                    resultLabel.setText("Too low! Attempts: " + attempts);
                } else if (guess > target) {
                    resultLabel.setText("Too high! Attempts: " + attempts);
                } else {
                    resultLabel.setText("Correct! Guessed in " + attempts + " attempts.");
                    showResult("You won! The number was " + target + ". Guessed in " + attempts + " attempts.");
                }
                if (attempts >= maxAttempts && guess != target) {
                    showResult("Out of attempts. The number was " + target);
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Enter a number.");
            }
        }
    }

    private void showResult(String message) {
        JFrame resultFrame = new JFrame("Result");
        resultFrame.setSize(600, 200);
        resultFrame.setLayout(new FlowLayout());
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel resultMessage = new JLabel(message);
        resultFrame.add(resultMessage);

        resultFrame.setVisible(true);
    }

    public static void main(String[] args) {
        String[] options = {"Easy (1-20)", "Medium (1-50)", "Hard (1-100)"};
        int[][] levels = {
                {1, 20, 5},
                {1, 50, 8},
                {1, 100, 10}
        };

        int level = JOptionPane.showOptionDialog(null, "Choose difficulty level:",
                "Difficulty Level", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        if (level >= 0) {
            int min = levels[level][0];
            int max = levels[level][1];
            int maxAttempts = levels[level][2];

            SwingUtilities.invokeLater(() -> {
                Task_01 game = new Task_01(min, max, maxAttempts);
                game.setVisible(true);
            });
        } else {
            System.exit(0);
        }
    }
}
