import java.util.Date;

class Transaction {
    private String transactionId;
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private Date date;
    
    public Transaction(String transactionId, Account fromAccount, Account toAccount, double amount) {
        this.transactionId = transactionId;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.date = new Date();
    }
    
    public boolean execute() {
        if (fromAccount == null || toAccount == null) {
            System.out.println("Ошибка: Счета не могут быть null");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма перевода должна быть положительной");
            return false;
        }
        
        // Проверяем достаточно ли средств перед переводом
        if (amount > fromAccount.getBalance()) {
            System.out.println("Ошибка: Недостаточно средств для перевода");
            return false;
        }
        
        // Выполняем перевод
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("Транзакция №%s: %.2f с %s на %s, Дата: %s", 
                           transactionId, amount, 
                           fromAccount.getAccountNumber(), 
                           toAccount.getAccountNumber(), 
                           date);
    }
}