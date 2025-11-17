public class Main {
    public static void main(String[] args) {
        Soldier[] soldiers = new Soldier[4];
        soldiers[0] = new Soldier(
            "Иванов Алексей Петрович",
            "г. Москва, ул. Ленина, д. 15",
            "Россия",
            "15.03.1995",
            "Командир отделения",
            "Старший сержант",
            true
        );
        
        soldiers[1] = new Soldier(
            "Петров Дмитрий Сергеевич",
            "г. Санкт-Петербург, ул. Пушкина, д. 42",
            "Россия",
            "22.07.1998",
            "Снайпер",
            "Сержант",
            true
        );
        
        soldiers[2] = new Soldier(
            "Сидоров Андрей Владимирович",
            "г. Новосибирск, пр. Карла Маркса, д. 78",
            "Россия",
            "10.11.1993",
            "Механик-водитель",
            "Старшина",
            false
        );
        
        soldiers[3] = new Soldier(
            "Козлова Мария Игоревна",
            "г. Екатеринбург, ул. Мира, д. 33",
            "Россия",
            "05.05.1996",
            "Военный врач",
            "Капитан",
            true
        );

        for (Soldier soldier : soldiers) {
            if (soldier.isServing()) {
                soldier.printInfo();
            }
        }
    }
}