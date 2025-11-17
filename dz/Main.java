package dz;

public class Main {
    public static void main(String[] args) {
        ATMMachine atm = new ATMMachine();
        
        System.out.println("=== Тестирование банкомата ===\n");
        
        System.out.println("1. Попытка снять деньги без карты:");
        atm.requestCash(100);
        System.out.println();
        
        System.out.println("2. Вставляем карту:");
        atm.insertCard();
        System.out.println();
        
        System.out.println("3. Пытаемся снять деньги без PIN:");
        atm.requestCash(100);
        System.out.println();
        
        System.out.println("4. Вводим неверный PIN:");
        atm.enterPin(1111);
        System.out.println();
        
        System.out.println("5. Вставляем карту и вводим верный PIN:");
        atm.insertCard();
        atm.enterPin(1234);
        System.out.println();
        
        System.out.println("6. Снимаем 500 рублей:");
        atm.requestCash(500);
        System.out.println();
        
        System.out.println("7. Пытаемся снять 3000 рублей:");
        atm.insertCard();
        atm.enterPin(1234);
        atm.requestCash(3000);
        System.out.println();
        
        System.out.println("8. Снимаем оставшиеся 1500 рублей:");
        atm.insertCard();
        atm.enterPin(1234);
        atm.requestCash(1500);
        System.out.println();
        
        System.out.println("9. Пытаемся использовать пустой банкомат:");
        atm.insertCard();
    }
}
