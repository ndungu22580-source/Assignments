import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaQuizApp extends JFrame implements ActionListener {
    // GUI Components
    JLabel questionLabel, timerLabel, scoreLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup bg;
    JButton nextButton;
    Timer countdownTimer;
    
    // Logic Variables
    int currentQuestion = 0;
    int score = 0;
    int timeLeft = 60;

    // Question Data
    String[][] quizData = {
        {"What is the correct syntax to output 'Hello World' in Java?", "System.out.println()", "print()", "echo()", "Console.WriteLine()", "System.out.println()"},
        {"Which data type is used to create a variable that should store text?", "String", "myString", "txt", "string", "String"},
        {"How do you create a variable with the numeric value 5?", "int x = 5;", "num x = 5", "float x = 5", "x = 5", "int x = 5;"},
        {"Which method can be used to find the length of a string?", "length()", "getSize()", "len()", "low()", "length()"},
        {"Which operator is used to add together two values?", "The + sign", "The * sign", "The & sign", "The ADD sign", "The + sign"}
    };

    public JavaQuizApp() {
        // JFrame setup
        setTitle("Java Quiz");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Header Panel: Timer and Score
        JPanel headerPanel = new JPanel(new GridLayout(1, 2));
        timerLabel = new JLabel("Time Left: 60s", JLabel.CENTER);
        scoreLabel = new JLabel("Score: 0", JLabel.CENTER);
        headerPanel.add(timerLabel);
        headerPanel.add(scoreLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Center Panel: Questions and Options
        JPanel centerPanel = new JPanel(new GridLayout(6, 1));
        questionLabel = new JLabel("");
        centerPanel.add(questionLabel);

        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            bg.add(options[i]);
            centerPanel.add(options[i]);
        }
        add(centerPanel, BorderLayout.CENTER);

        // Footer Panel: Next Button
        nextButton = new JButton("Next Question");
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        // Initialize Timer
        countdownTimer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                checkAnswer();
                loadNextQuestion();
            }
        });

        startQuiz();
        setVisible(true);
    }

    private void startQuiz() {
        currentQuestion = 0;
        score = 0;
        scoreLabel.setText("Score: 0");
        loadQuestion(currentQuestion);
    }

    private void loadQuestion(int n) {
        questionLabel.setText("  " + (n + 1) + ". " + quizData[n][0]);
        for (int i = 0; i < 4; i++) {
            options[i].setText(quizData[n][i + 1]);
            options[i].setSelected(false);
        }
        bg.clearSelection();
        timeLeft = 60;
        timerLabel.setText("Time Left: 60s");
        countdownTimer.start();
    }

    private void checkAnswer() {
        String selected = "";
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selected = options[i].getText();
            }
        }
        if (selected.equals(quizData[currentQuestion][5])) {
            score++;
            scoreLabel.setText("Score: " + score);
        }
    }

    private void loadNextQuestion() {
        currentQuestion++;
        if (currentQuestion < quizData.length) {
            loadQuestion(currentQuestion);
        } else {
            countdownTimer.stop();
            showResult();
        }
    }

    private void showResult() {
        int choice = JOptionPane.showConfirmDialog(this, 
            "Quiz Over!\nYour Final Score: " + score + "/" + quizData.length + "\nWould you like to restart?", 
            "Results", JOptionPane.YES_NO_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            startQuiz();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextButton) {
            checkAnswer();
            loadNextQuestion();
        }
    }

    public static void main(String[] args) {
        new JavaQuizApp();
    }
}
