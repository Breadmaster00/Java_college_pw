class CreditAccount extends Account {
    private double creditLimit;
    private double interestRate;
    
    public CreditAccount(String accountNumber, double balance, Client owner, 
                        double creditLimit, double interestRate) {
        super(accountNumber, balance, owner);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }
    
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма снятия должна быть положительной");
            return;
        }
        if (amount > balance + creditLimit) {
            System.out.println("Ошибка: Превышен кредитный лимит");
            return;
        }
        balance -= amount;
        System.out.println("Успешно снято: " + amount);
    }
    
    @Override
    public void applyInterest() {
        if (balance < 0) {
            double interest = Math.abs(balance) * interestRate / 100;
            balance -= interest;
            System.out.println("Начислены проценты по кредиту: " + interest);
        }
    }
    
    @Override
    public String toString() {
        return String.format("Кредитный счет №%s, Баланс: %.2f, Кредитный лимит: %.2f, Владелец: %s", 
                           accountNumber, balance, creditLimit, owner.getName());
    }
}