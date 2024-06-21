import java.util.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient money.");
            return false;
        }
        balance -= amount;
        return true;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}

public class ATM {
    private BankAccount account;
    private String pin;

    public ATM(BankAccount account, String pin) {
        this.account = account;
        this.pin = pin;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        if (!authenticate(scanner)) return;

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();
            if (choice.equals("5")) {
                System.out.println("Thank you for using the ATM...!");
                break;
            }
            handleChoice(choice, scanner);
        }
    }

    private boolean authenticate(Scanner scanner) {
        System.out.print("Enter PIN: ");
        if (!scanner.nextLine().equals(pin)) {
            System.out.println("Incorrect PIN. Access denied.");
            return false;
        }
        return true;
    }

    private void displayMenu() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw Cash");
        System.out.println("3. Deposit Cash");
        System.out.println("4. Change PIN");
        System.out.println("5. Exit");
    }

    private void handleChoice(String choice, Scanner scanner) {
        switch (choice) {
            case "1" -> checkBalance();
            case "2" -> withdrawCash(scanner);
            case "3" -> depositCash(scanner);
            case "4" -> changePin(scanner);
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", account.getBalance());
    }

    private void withdrawCash(Scanner scanner) {
        double amount = getAmount(scanner, "withdraw");
        if (amount > 0 && account.withdraw(amount)) {
            System.out.printf("Successfully withdrawn: $%.2f\n", amount);
        } else {
            System.out.println("Withdrawal failed.");
        }
    }

    private void depositCash(Scanner scanner) {
        double amount = getAmount(scanner, "deposit");
        if (amount > 0) {
            account.deposit(amount);
            System.out.printf("Successfully deposited: $%.2f\n", amount);
        }
    }

    private double getAmount(Scanner scanner, String action) {
        while (true) {
            try {
                System.out.printf("Enter amount to %s: ", action);
                double amount = scanner.nextDouble();
                scanner.nextLine();
                if (amount > 0) return amount;
                System.out.println("Please enter correct amount.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private void changePin(Scanner scanner) {
        System.out.print("Enter current PIN: ");
        if (!scanner.nextLine().equals(pin)) {
            System.out.println("Incorrect current PIN. PIN change failed.");
            return;
        }

        System.out.print("Enter new PIN: ");
        String newPin = scanner.nextLine();
        System.out.print("Confirm new PIN: ");
        if (newPin.equals(scanner.nextLine())) {
            pin = newPin;
            System.out.println("PIN successfully changed.");
        } else {
            System.out.println("PINs do not match. PIN change failed.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Set up your initial PIN: ");
        String initialPin = scanner.nextLine();
        new ATM(new BankAccount(1000.00), initialPin).run();
    }
}

