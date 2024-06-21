import java.util.Scanner;
import java.util.Random;

public class Task_01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int[][] levels = {
                {1, 20, 5},
                {1, 50, 8},
                {1, 100, 10}
        };

        System.out.println("Choose difficulty level:");
        System.out.println("1. Easy (1-20)");
        System.out.println("2. Medium (1-50)");
        System.out.println("3. Hard (1-100)");
        
        int level = 0;
        while (level < 1 || level > levels.length) {
            System.out.print("Enter difficulty level (1-3): ");
            if (scanner.hasNextInt()) {
                level = scanner.nextInt();
                if (level < 1 || level > levels.length) {
                    System.out.println("Invalid level. Choose between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Enter a number between 1 and 3.");
                scanner.next();
            }
        }

        int min = levels[level - 1][0];
        int max = levels[level - 1][1];
        int maxAttempts = levels[level - 1][2];

        int target = random.nextInt(max - min + 1) + min;
        int guess = 0;
        int attempts = 0;

        System.out.println("I have chosen a number between " + min + " and " + max + ". Try to guess it!");

        while (guess != target && attempts < maxAttempts) {
            System.out.print("Enter your guess: ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                attempts++;

                if (guess < target) {
                    System.out.println("Too low!");
                } else if (guess > target) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Correct! You guessed the number in " + attempts + " attempts.");
                }
            } else {
                System.out.println("Invalid input. Enter a number.");
                scanner.next();
            }
        }

        if (guess != target) {
            System.out.println("Out of attempts. The number was " + target);
        }

        scanner.close();
    }
}
