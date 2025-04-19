import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private int targetNumber;
    private int attemptsLeft = 5;

    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new GridLayout(5, 1, 10, 5));

        JLabel instructionLabel = new JLabel("Guess a number between 1 and 100:");
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        guessField = new JTextField();
        guessButton = new JButton("Submit Guess");
        feedbackLabel = new JLabel("You have 5 attempts left.", SwingConstants.CENTER);
        attemptsLabel = new JLabel("", SwingConstants.CENTER);

        add(instructionLabel);
        add(guessField);
        add(guessButton);
        add(feedbackLabel);
        add(attemptsLabel);

        generateRandomNumber();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleGuess();
            }
        });

        setVisible(true);
    }

    private void generateRandomNumber() {
        Random rand = new Random();
        targetNumber = rand.nextInt(100) + 1;
    }

    private void handleGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText().trim());
            attemptsLeft--;

            if (guess == targetNumber) {
                feedbackLabel.setText("Congratulations! You guessed it right!");
                guessButton.setEnabled(false);
            } else if (attemptsLeft == 0) {
                feedbackLabel.setText("No attempts left. The number was: " + targetNumber);
                guessButton.setEnabled(false);
            } else if (guess < targetNumber) {
                feedbackLabel.setText("Too low! Try again.");
            } else {
                feedbackLabel.setText("Too high! Try again.");
            }

            attemptsLabel.setText("Attempts left: " + attemptsLeft);
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Please enter a valid number.");
        }

        guessField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGameGUI());
    }
}
