class DepositAccount extends Account {
    private double interestRate;
    
    public DepositAccount(String accountNumber, double balance, Client owner, double interestRate) {
        super(accountNumber, balance, owner);
        this.interestRate = interestRate;
    }
    
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма снятия должна быть положительной");
            return;
        }
        if (amount > balance) {
            System.out.println("Ошибка: Недостаточно средств на депозитном счете");
            return;
        }
        balance -= amount;
        System.out.println("Успешно снято: " + amount);
    }
    
    @Override
    public void applyInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Начислены проценты по депозиту: " + interest);
    }
    
    @Override
    public String toString() {
        return String.format("Депозитный счет №%s, Баланс: %.2f, Процентная ставка: %.2f%%, Владелец: %s", 
                           accountNumber, balance, interestRate, owner.getName());
    }
}