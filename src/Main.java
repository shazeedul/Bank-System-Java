import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Initialize bank accounts with initial balance
        BankAccount account043 = new BankAccount("Account_043", 210000);

        // List to hold bank accounts
        ArrayList<BankAccount> accounts = new ArrayList<>();
        accounts.add(account043);

        // Create transaction threads
        ArrayList<TransactionThread> transactions = new ArrayList<>();

        // Add transactions to the list for account043
        transactions.add(new TransactionThread(account043, 36750, false));
        transactions.add(new TransactionThread(account043, 9450, true));

        BankAccount currentAccount = account043;

        while (true) {
            System.out.print(
                    "Do you want to use the current account (" + currentAccount.getAccountNumber() + ")? (yes/no): ");
            String useCurrent = scanner.next();
            if (useCurrent.equalsIgnoreCase("no")) {
                System.out.print("Enter the account number: ");
                String accountNumber = scanner.next();

                // Search for an existing account
                currentAccount = null;
                for (BankAccount account : accounts) {
                    if (account.getAccountNumber().equals(accountNumber)) {
                        currentAccount = account;
                        break;
                    }
                }

                // If no account is found, create a new one
                if (currentAccount == null) {
                    currentAccount = new BankAccount(accountNumber, 0);
                    accounts.add(currentAccount);
                    System.out.println("Created a new account: " + accountNumber + " with an initial balance of 0.");
                }
            }
            int choice;
            while (true) {
                System.out.println("\nChoose a transaction type: ");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                choice = scanner.nextInt();

                if (choice == 1 || choice == 2) {
                    break; // Valid choice
                } else {
                    System.out.println("Invalid choice! Please choose 1 for Deposit or 2 for Withdraw.");
                }
            }

            System.out.print("Enter the amount: ");
            double amount = scanner.nextDouble();

            if (choice == 1) {
                transactions.add(new TransactionThread(currentAccount, amount, false)); // Deposit
            } else if (choice == 2) {
                transactions.add(new TransactionThread(currentAccount, amount, true)); // Withdraw
            } else {
                System.out.println("Invalid choice!");
            }

            System.out.print("Do you want to perform another transaction? (yes/no): ");
            String anotherTransaction = scanner.next();
            if (anotherTransaction.equalsIgnoreCase("no")) {
                break;
            }
        }

        // Start transaction threads
        for (TransactionThread transaction : transactions) {
            transaction.run();
        }

        // Print final balances of all accounts
        for (BankAccount account : accounts) {
            System.out.println("Final balance of " + account.getAccountNumber() + ": " + account.getBalance());
        }
        // Close scanner
        scanner.close();
    }
}
