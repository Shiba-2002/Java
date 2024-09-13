import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attempts = 10;
        int score = 0;

        System.out.println("Welcome to Guess the Number Game!");
        System.out.println("I have selected a random number between " + minRange + " and " + maxRange);

        boolean playAgain = true;
        while (playAgain) {
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attemptCount = 0;
            boolean guessedCorrectly = false;

            while (!guessedCorrectly && attemptCount < attempts) {
                System.out.print("Enter your guess (between " + minRange + " and " + maxRange + "): ");
                int userGuess = scanner.nextInt();
                attemptCount++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You've guessed the correct number in " + attemptCount + " attempts.");
                    guessedCorrectly = true;
                    score++;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = scanner.next();
            if (!playChoice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Game over! Your total score is: " + score);
        scanner.close();
    }
}
