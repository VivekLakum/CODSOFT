import java.util.Scanner;

public class Task_02 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int subjects = 0;
        double total = 0.0;
        int maxMarks = 0;

        while (subjects <= 0) {
            System.out.print("Enter the number of subjects: ");
            if (scanner.hasNextInt()) {
                subjects = scanner.nextInt();
                if (subjects <= 0) {
                    System.out.println("Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }

        while (maxMarks <= 0) {
            System.out.print("Enter the maximum marks per subject: ");
            if (scanner.hasNextInt()) {
                maxMarks = scanner.nextInt();
                if (maxMarks <= 0) {
                    System.out.println("Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next();
            }
        }

        for (int i = 0; i < subjects; i++) {
            double marks = -1;
            while (marks < 0 || marks > maxMarks) {
                System.out.print("Enter marks for subject " + (i + 1) + " (out of " + maxMarks + "): ");
                if (scanner.hasNextDouble()) {
                    marks = scanner.nextDouble();
                    if (marks < 0 || marks > maxMarks) {
                        System.out.println("Please enter marks between 0 and " + maxMarks + ".");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
            }
            total += marks;
        }

        double percentage = (total / (subjects * maxMarks)) * 100;
        String grade;

        if (percentage >= 90) {
            grade = "A";
        } else if (percentage >= 80) {
            grade = "B";
        } else if (percentage >= 70) {
            grade = "C";
        } else if (percentage >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        System.out.println("Total marks: " + total + " (out of " + (subjects * maxMarks) + ")");
        System.out.println("Average percentage: " + percentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
