import library.model.Book;
import library.model.Library;
import library.Users.Reader;
import library.service.BookServise;
import library.service.BorrowService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Library libraryObject = new Library();
        BookServise headBookServise = new BookServise("Оксана", "secure_password");
        BorrowService borrowService = new BorrowService();
        BookServise bookServise = new BookServise("Оксана", "l;kmnjbhvgc");
        Reader reader = new Reader("jkbhvc67", "Eva");

        Book book1 = new Book("Володар Перснів", "Дж.Р.Р. Толкін", 1954, 1178);
        Book book2 = new Book("Фауст", "Йоганн Вольфганг Гете", 1808, 624);
        Book book3 = new Book("Тигролови", "Іван Багряний", 1944, 352);
        Book book4 = new Book("Сто років самотності", "Габріель Гарсія Маркес", 1967, 432);
        Book book5 = new Book("Гаррі Поттер і філософський камінь", "Джоан Роулінг", 1997, 320);
        Book book6 = new Book("Маленький принц", "Антуан де Сент-Екзюпері", 1943, 96);
        Book book7 = new Book("Портрет Доріана Грея", "Оскар Вайльд", 1890, 254);
        libraryObject.addBook(book1);
        libraryObject.addBook(book2);
        libraryObject.addBook(book3);
        libraryObject.addBook(book4);
        libraryObject.addBook(book5);
        libraryObject.addBook(book6);
        libraryObject.addBook(book7);
        book1.displayInfo();
        book1.borrowBook();
        book1.borrowBook();
        book1.returnBook();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== МЕНЮ БІБЛІОТЕКИ ===");
            System.out.println("1. Показати всі книги");
            System.out.println("2. Позичити книгу");
            System.out.println("3. Показати історію позичень");
            System.out.println("0. Вийти");
            System.out.print("Оберіть опцію: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // щоб уникнути пропуску рядка

            switch (choice) {
                case 1 -> libraryObject.displayAllBooks();
                case 2 -> {
                    System.out.print("Введіть назву книги: ");
                    String title = scanner.nextLine();
                    Book book = libraryObject.findBookByTitle(title);
                    if (book != null && book.isAvailable()) {
                        borrowService.borrowBook(book, reader);
                    } else {
                        System.out.println("Книга не знайдена або вже позичена.");
                    }
                }
                case 3 -> borrowService.showBorrowHistory();
                case 0 -> System.out.println("Вихід із системи...");
                default -> System.out.println("Невірний вибір!");
            }
        } while (choice != 0);

        scanner.close();
    }

}



