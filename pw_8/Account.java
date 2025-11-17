abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected Client owner;
    
    public Account(String accountNumber, double balance, Client owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }
    
    public abstract void applyInterest(); 
    
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма депозита должна быть положительной");
            return;
        }
        balance += amount;
        System.out.println("Успешно пополнено: " + amount);
    }
    
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма снятия должна быть положительной");
            return;
        }
        if (amount > balance) {
            System.out.println("Ошибка: Недостаточно средств на счете");
            return;
        }
        balance -= amount;
        System.out.println("Успешно снято: " + amount);
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public Client getOwner() {
        return owner;
    }
    
    @Override
    public String toString() {
        return String.format("Счет №%s, Баланс: %.2f, Владелец: %s", 
                           accountNumber, balance, owner.getName());
    }
}