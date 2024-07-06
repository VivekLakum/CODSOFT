import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
            return false;
        }
        balance -= amount;
        return true;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}

public class ATM extends JFrame {
    private BankAccount account;
    private String pin;
    private JTextField pinField;
    private JLabel resultLabel;

    public ATM(BankAccount account, String pin) {
        this.account = account;
        this.pin = pin;
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("ATM Simulator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        Font font = new Font("Arial", Font.PLAIN, 20);

        JLabel pinLabel = new JLabel("Enter PIN:");
        pinLabel.setFont(font);
        add(pinLabel);

        pinField = new JTextField();
        pinField.setFont(font);
        add(pinField);

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(font);
        add(enterButton);

        resultLabel = new JLabel("Result:");
        resultLabel.setFont(font);
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        add(resultLabel);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (authenticate()) {
                    showOptions();
                } else {
                    resultLabel.setText("Incorrect PIN. Try again.");
                }
            }
        });
    }

    private boolean authenticate() {
        return pinField.getText().equals(pin);
    }

    private void showOptions() {
        JFrame optionsFrame = new JFrame("ATM Options");
        optionsFrame.setSize(400, 400);
        optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        optionsFrame.setLocationRelativeTo(null);
        optionsFrame.setLayout(new GridLayout(5, 1, 10, 10));

        Font font = new Font("Arial", Font.PLAIN, 20);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setFont(font);
        optionsFrame.add(checkBalanceButton);

        JButton withdrawButton = new JButton("Withdraw Cash");
        withdrawButton.setFont(font);
        optionsFrame.add(withdrawButton);

        JButton depositButton = new JButton("Deposit Cash");
        depositButton.setFont(font);
        optionsFrame.add(depositButton);

        JButton changePinButton = new JButton("Change PIN");
        changePinButton.setFont(font);
        optionsFrame.add(changePinButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(font);
        optionsFrame.add(exitButton);

        checkBalanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkBalance();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performWithdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performDeposit();
            }
        });

        changePinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performChangePin();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank you for using the ATM.");
                System.exit(0);
            }
        });

        optionsFrame.setVisible(true);
    }

    private void checkBalance() {
        JOptionPane.showMessageDialog(this, String.format("Balance: $%.2f", account.getBalance()));
    }

    private void performWithdraw() {
        JFrame withdrawFrame = new JFrame("Withdraw Cash");
        withdrawFrame.setSize(400, 200);
        withdrawFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        withdrawFrame.setLocationRelativeTo(null);
        withdrawFrame.setLayout(new GridLayout(3, 1, 10, 10));

        Font font = new Font("Arial", Font.PLAIN, 20);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setFont(font);
        withdrawFrame.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setFont(font);
        withdrawFrame.add(amountField);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(font);
        withdrawFrame.add(withdrawButton);

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0 && account.withdraw(amount)) {
                        JOptionPane.showMessageDialog(withdrawFrame, String.format("Successfully withdrawn: $%.2f", amount));
                    } else {
                        JOptionPane.showMessageDialog(withdrawFrame, "Insufficient funds or invalid amount.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(withdrawFrame, "Invalid amount.");
                }
            }
        });

        withdrawFrame.setVisible(true);
    }

    private void performDeposit() {
        JFrame depositFrame = new JFrame("Deposit Cash");
        depositFrame.setSize(400, 200);
        depositFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        depositFrame.setLocationRelativeTo(null);
        depositFrame.setLayout(new GridLayout(3, 1, 10, 10));

        Font font = new Font("Arial", Font.PLAIN, 20);

        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setFont(font);
        depositFrame.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setFont(font);
        depositFrame.add(amountField);

        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(font);
        depositFrame.add(depositButton);

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount > 0) {
                        account.deposit(amount);
                        JOptionPane.showMessageDialog(depositFrame, String.format("Successfully deposited: $%.2f", amount));
                    } else {
                        JOptionPane.showMessageDialog(depositFrame, "Invalid amount.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(depositFrame, "Invalid amount.");
                }
            }
        });

        depositFrame.setVisible(true);
    }

    private void performChangePin() {
        JFrame changePinFrame = new JFrame("Change PIN");
        changePinFrame.setSize(400, 300);
        changePinFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changePinFrame.setLocationRelativeTo(null);
        changePinFrame.setLayout(new GridLayout(4, 1, 10, 10));

        Font font = new Font("Arial", Font.PLAIN, 20);

        JLabel newPinLabel = new JLabel("Enter New PIN:");
        newPinLabel.setFont(font);
        changePinFrame.add(newPinLabel);

        JTextField newPinField = new JTextField();
        newPinField.setFont(font);
        changePinFrame.add(newPinField);

        JLabel confirmPinLabel = new JLabel("Confirm New PIN:");
        confirmPinLabel.setFont(font);
        changePinFrame.add(confirmPinLabel);

        JTextField confirmPinField = new JTextField();
        confirmPinField.setFont(font);
        changePinFrame.add(confirmPinField);

        JButton changePinButton = new JButton("Change PIN");
        changePinButton.setFont(font);
        changePinFrame.add(changePinButton);

        changePinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (newPinField.getText().equals(confirmPinField.getText())) {
                    pin = newPinField.getText();
                    JOptionPane.showMessageDialog(changePinFrame, "PIN successfully changed.");
                } else {
                    JOptionPane.showMessageDialog(changePinFrame, "PINs do not match. PIN change failed.");
                }
            }
        });

        changePinFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                String initialPin = JOptionPane.showInputDialog("Set up your initial PIN:");
                new ATM(new BankAccount(1000.00), initialPin).setVisible(true);
            }
        });
    }
}
