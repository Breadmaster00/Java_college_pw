public class Main {
    public static void main(String[] args) {
        
        Client client1 = new Client("001", "Иван Иванов");
        Client client2 = new Client("002", "Петр Петров");
        
        Account account1 = new DepositAccount("ACC001", 1000.0, client1, 5.0);
        Account account2 = new CreditAccount("ACC002", 500.0, client1, 1000.0, 10.0);
        Account account3 = new DepositAccount("ACC003", 2000.0, client2, 3.0);
        
        client1.addAccount(account1);
        client1.addAccount(account2);
        client2.addAccount(account3);
        
        System.out.println("=== ИНИЦИАЛИЗАЦИЯ СИСТЕМЫ ===");
        System.out.println(client1);
        System.out.println(client2);
        System.out.println();
        
        System.out.println("=== ИНФОРМАЦИЯ О СЧЕТАХ ===");
        for (Account acc : client1.getAccounts()) {
            System.out.println(acc);
        }
        for (Account acc : client2.getAccounts()) {
            System.out.println(acc);
        }
        System.out.println();
        
        System.out.println("=== ОПЕРАЦИИ СО СЧЕТАМИ ===");
        
        System.out.println("Пополнение счета ACC001 на 500:");
        account1.deposit(500);
        System.out.println(account1);
        System.out.println();
        
        System.out.println("Снятие со счета ACC002 200:");
        account2.withdraw(600);
        System.out.println(account2);
        System.out.println();
        
        System.out.println("=== ТРАНЗАКЦИИ ===");
        Transaction transaction1 = new Transaction("T001", account1, account3, 300);
        boolean success = transaction1.execute();
        if (success) {
            System.out.println("Транзакция выполнена успешно:");
            System.out.println(transaction1);
            System.out.println("Баланс счета отправителя: " + account1.getBalance());
            System.out.println("Баланс счета получателя: " + account3.getBalance());
        }
        System.out.println();
        
        // Начисление процентов
        System.out.println("=== НАЧИСЛЕНИЕ ПРОЦЕНТОВ ===");
        System.out.println("До начисления:");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        
        account1.applyInterest();
        account2.applyInterest();
        account3.applyInterest();
        
        System.out.println("После начисления:");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
        System.out.println();
        
    
    }
}