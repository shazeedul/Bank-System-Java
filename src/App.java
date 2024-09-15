import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        // Initialize bank accounts with initial balance
        BankAccount account043 = new BankAccount("Account_043", 210000);

        // Create transaction threads
        ArrayList<TransactionThread> transactions = new ArrayList<>();

        transactions.add(new TransactionThread(account043, 36750, false));
        transactions.add(new TransactionThread(account043, 9450, true));

        // Start transaction threads
        for (TransactionThread transaction : transactions) {
            transaction.start();
        }

        // Wait for all transaction threads to finish
        for (TransactionThread transaction : transactions) {
            transaction.join();
        }

        // Print final balance
        System.out.println("Final balance of " + account043.getAccountNumber() + ": " + account043.getBalance());
    }
}
