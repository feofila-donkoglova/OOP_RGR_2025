import library.exceptions.BookUnavailableException;
import library.exceptions.MaxLoanLimitExceededException;
import library.model.*;
import library.notification.EventType;
import library.strategy.*;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {


        Library library = Library.getInstance();

        Book book1 = new Book.BookBuilder().setTitle("Володар Перснів").setAuthor("Дж.Р.Р. Толкін").setYear(1954).setPageCount(1178).build();
        Book book2 = new Book.BookBuilder().setTitle("Фауст").setAuthor("Йоганн Вольфганг Гете").setYear(1808).setPageCount(624).build();
        Book book3 = new Book.BookBuilder().setTitle("Тигролови").setAuthor("Іван Багряний").setYear(1944).setPageCount(352).build();
        Book book4 = new Book.BookBuilder().setTitle("Сто років самотності").setAuthor("Габріель Гарсія Маркес").setYear(1967).setPageCount(432).build();
        Book book5 = new Book.BookBuilder().setTitle("Гаррі Поттер і філософський камінь").setAuthor("Джоан Роулінг").setYear(1997).setPageCount(320).build();

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);


        Set<EventType> eventsEva = new HashSet<>();
        eventsEva.add(EventType.NEW_BOOK);

        Reader readerEva = new Reader("Eva", "lkgfr");
        library.registerUser(readerEva);
        library.subscribe(readerEva);

        Set<EventType> eventsJohn = new HashSet<>();
        eventsJohn.add(EventType.NEW_BOOK);
        eventsJohn.add(EventType.PROMOTION);

        Reader readerJohn = new Reader("John", "rltkngl");
        library.registerUser(readerJohn);
        library.subscribe(readerJohn);

        // Strategy
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


        Scanner scanner = new Scanner(System.in);
        int choice;

        Reader activeReader = readerJohn;

        do {
            System.out.println("\n=== МЕНЮ БІБЛІОТЕКИ ===");
            System.out.println("1. Показати всі книги");
            System.out.println("2. Позичити книгу");
            System.out.println("3. Повернути книгу");
            System.out.println("0. Вийти");
            System.out.print("Оберіть опцію: ");
            choice = scanner.nextInt();
            scanner.nextLine();

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
                        try {
                            library.borrowBook(book, activeReader);
                        } catch (BookUnavailableException | MaxLoanLimitExceededException e) {
                            System.out.println("Помилка позики: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Невідома помилка: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Книга не знайдена.");
                    }
                }
                case 3 -> {
                    System.out.print("Введіть назву книги для повернення: ");
                    String title = scanner.nextLine();
                    Book book = library.getBookRepository().findByTitle(title);
                    if (book != null) {
                        library.returnBook(book, activeReader); // State Pattern
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
