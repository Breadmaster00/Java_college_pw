import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static <T> Order<T> findOrderById(List<Order<T>> orders, T id) {
        for (Order<T> order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }
    public static void main(String[] args) {
        ArrayList<Order<String>> orders = new ArrayList<>();
        orders.add(new Order<>("101", "10.10.2025"));
        orders.add(new Order<>("111", "13.10.2025"));
        orders.add(new Order<>("105", "11.10.2025"));
        orders.add(new Order<>("127", "15.10.2025"));
        orders.add(new Order<>("117", "14.10.2025"));
        
        Collections.sort(orders, new Comparator<Order<String>>() {
            @Override
            public int compare(Order<String> o1, Order<String> o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        
        System.out.println("Отсортированный список");
        for (Order<String> order : orders) {
            System.out.println(order);
        }

        String searchId = "117";
        Order<String> foundOrder = findOrderById(orders, searchId);
        if (foundOrder != null) {
            System.out.println("\nНайден заказ: " + foundOrder);
        } else {
            System.out.println("\nЗаказ с ID '" + searchId + "' не найден");
        }
    }
}
