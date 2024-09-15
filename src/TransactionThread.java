public class TransactionThread extends Thread {
    private BankAccount account;
    private double amount;
    private boolean isWithdrawal;

    public TransactionThread(BankAccount account, double amount, boolean isWithdrawal) {
        this.account = account;
        this.amount = amount;
        this.isWithdrawal = isWithdrawal;
    }

    @Override
    public synchronized void run() {
        if (isWithdrawal) {
            account.withdraw(amount);
        } else {
            account.deposit(amount);
        }
    }
}
