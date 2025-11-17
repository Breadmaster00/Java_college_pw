import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Publication> publications = new ArrayList<>();
        publications.add(new Publication("Автостопом по галактике", "Дуглас Адамс", 640, "ACT"));
        publications.add(new Publication("Азы программированя", "Столяров А.В.", 750, "Электронное"));
        publications.add(new Publication("Грокаем алгоритмы", "Адитья Бхаргава", 290, "Питер Пресс"));
        publications.add(new Publication("Автоматизация рутинных задач при помощи Python", "Эл Свейгарт", 573, "И.Д. \"Вильямс\""));
        publications.add(new Publication("Web Development", "Maria Sidorova", 350, "WebBooks"));
        publications.add(new Publication("Machine Learning", "Alexey Smirnov", 600, "SciencePub"));
        publications.add(new Publication("Python for Beginners", "Elena Popova", 280, "TechPress"));
        publications.add(new Publication("Database Systems", "Ivan Ivanov", 480, "SciencePub"));
        publications.add(new Publication("Mobile Apps", "Olga Kuznetsova", 320, "WebBooks"));

        System.out.println("Сортировка по количеству страниц:");
        publications.sort(Comparator.comparingInt(Publication::getPages));
        for (Publication publication : publications) {
            System.out.println(publication);
        }

        System.out.println("\n> - - - - - - - - - - - - - - - - - - - <\n");
        
        String publisherFilter = "ACT";
        System.out.println("Книги издательства '" + publisherFilter + "':");
        List<Publication> filteredByPublisher = publications.stream()
        .filter(p -> p.getPublisher().equals(publisherFilter))
        .collect(Collectors.toList());
        filteredByPublisher.forEach(System.out::println);
        
        System.out.println("\n> - - - - - - - - - - - - - - - - - - - <\n");
        
        System.out.println("\nТоп-3 самых объёмных изданий:");
        List<Publication> top3Voluminous = publications.stream()
        .sorted((p1, p2) -> p2.getPages() - p1.getPages())
        .limit(3)
        .collect(Collectors.toList());
        top3Voluminous.forEach(System.out::println);
        
        System.out.println("\n> - - - - - - - - - - - - - - - - - - - <\n");

        System.out.println("Среднее количество страниц по издательствам:");
        Map<String, Double> avgPagesByPublisher = publications.stream()
                .collect(Collectors.groupingBy(
                        Publication::getPublisher,
                        Collectors.averagingInt(Publication::getPages)
                ));
        avgPagesByPublisher.forEach((pub, avg) ->
                System.out.printf("Издательство: %s, Среднее количество страниц: %.2f%n", pub, avg));
        
    }
}
