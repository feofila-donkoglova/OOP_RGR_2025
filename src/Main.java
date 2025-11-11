import library.model.*;
import library.notification.EventType;
import library.strategy.*;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        // ===== 1️⃣ Library (Singleton) =====
        Library library = Library.getInstance();

        // ===== 2️⃣ Додаємо книги =====
        Book book1 = new Book("Володар Перснів", "Дж.Р.Р. Толкін", 1954, 1178);
        Book book2 = new Book("Фауст", "Йоганн Вольфганг Гете", 1808, 624);
        Book book3 = new Book("Тигролови", "Іван Багряний", 1944, 352);
        Book book4 = new Book("Сто років самотності", "Габріель Гарсія Маркес", 1967, 432);
        Book book5 = new Book("Гаррі Поттер і філософський камінь", "Джоан Роулінг", 1997, 320);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        // ===== 3️⃣ Користувачі + Advanced Observer =====
        Set<EventType> eventsEva = new HashSet<>();
        eventsEva.add(EventType.NEW_BOOK);

        Reader readerEva = new Reader("lkgfr", "Eva");
        library.registerUser(readerEva);

        Set<EventType> eventsJohn = new HashSet<>();
        eventsJohn.add(EventType.NEW_BOOK);
        eventsJohn.add(EventType.PROMOTION);

        Reader readerJohn = new Reader("rltkngl", "Ocsana");
        library.registerUser(readerJohn);

        // ===== 4️⃣ Strategy (пошук) =====
        SearchService searchService = new SearchService(new SearchByTitle());
        System.out.println("\n=== Пошук по назві: 'Гаррі' ===");
        List<Book> results = searchService.search(library.getBooks(), "Гаррі");
        results.forEach(Book::displayInfo);

        searchService.setStrategy(new SearchByAuthor());
        System.out.println("\n=== Пошук по автору: 'Маркес' ===");
        results = searchService.search(library.getBooks(), "Маркес");
        results.forEach(Book::displayInfo);

        searchService.setStrategy(new SearchByYear());
        System.out.println("\n=== Пошук по року: '1944' ===");
        results = searchService.search(library.getBooks(), "1944");
        results.forEach(Book::displayInfo);

        // ===== 5️⃣ Інтерактивне меню =====
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== МЕНЮ БІБЛІОТЕКИ ===");
            System.out.println("1. Показати всі книги");
            System.out.println("2. Позичити книгу");
            System.out.println("3. Повернути книгу");
            System.out.println("0. Вийти");
            System.out.print("Оберіть опцію: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // щоб уникнути пропуску рядка

            switch (choice) {
                case 1 -> {
                    System.out.println("\n=== Усі книги бібліотеки ===");
                    library.displayAllBooks();
                }
                case 2 -> {
                    System.out.print("Введіть назву книги для позичення: ");
                    String title = scanner.nextLine();
                    Book book = library.getBookRepository().findByTitle(title);
                    if (book != null) {
                        book.borrowBook(); // State Pattern керує станом
                    } else {
                        System.out.println("Книга не знайдена.");
                    }
                }
                case 3 -> {
                    System.out.print("Введіть назву книги для повернення: ");
                    String title = scanner.nextLine();
                    Book book = library.getBookRepository().findByTitle(title);
                    if (book != null) {
                        book.returnBook(); // State Pattern
                    } else {
                        System.out.println("Книга не знайдена.");
                    }
                }
                case 0 -> System.out.println("Вихід із системи...");
                default -> System.out.println("Невірний вибір!");
            }
        } while (choice != 0);

        scanner.close();
    }
}
