@FunctionalInterface
interface Processor<T> {
    void process(T obj);
}

record Delivery(String destination, float distance, float cost) {
    public float CostPerMetr() {
        return cost / distance;
    }
}

public class Main {
    public static void main() {
        Delivery[] deliveries = new Delivery[3];
        deliveries[0] = new Delivery("Дом", 1234, 1000);
        deliveries[1] = new Delivery("Колледж", 3400, 1500);
        deliveries[2] = new Delivery("Магазин", 4500, 1600);

        Processor<Delivery> deliveryInfo = delivery -> System.out.println("Адрес назанчения: " + delivery.destination() + ", дистанция: " + delivery.distance() + "м, стоимость: " + delivery.cost() + "р, средняя цена за метр: " + delivery.CostPerMetr() + "р");

        for (Delivery delivery : deliveries) {
            deliveryInfo.process(delivery);
        }
    }
}
