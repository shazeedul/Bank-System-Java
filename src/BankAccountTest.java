import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BankAccountTest {
    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Initialize a BankAccount with an initial balance of 100000
        account = new BankAccount("TestAccount_001", 100000);
    }

    @Test
    void testDeposit() {
        // Deposit 50000
        account.deposit(50000);
        assertEquals(150000, account.getBalance(), "Balance should be 150000 after deposit");
    }

    @Test
    void testWithdraw() {
        // Withdraw 30000
        account.withdraw(30000);
        assertEquals(70000, account.getBalance(), "Balance should be 70000 after withdrawal");
    }

    @Test
    void testWithdrawInsufficientBalance() {
        // Attempt to withdraw an amount greater than the balance
        account.withdraw(150000);
        assertEquals(100000, account.getBalance(), "Balance should remain unchanged due to insufficient balance");
    }

    @Test
    void testTransactionThreadDeposit() throws InterruptedException {
        // Create a deposit transaction
        TransactionThread depositThread = new TransactionThread(account, 20000, false);
        depositThread.start();
        depositThread.join(); // Wait for the thread to finish

        assertEquals(120000, account.getBalance(), "Balance should be 120000 after deposit thread");
    }

    @Test
    void testTransactionThreadWithdraw() throws InterruptedException {
        // Create a withdraw transaction
        TransactionThread withdrawThread = new TransactionThread(account, 50000, true);
        withdrawThread.start();
        withdrawThread.join(); // Wait for the thread to finish

        assertEquals(50000, account.getBalance(), "Balance should be 50000 after withdraw thread");
    }
}
