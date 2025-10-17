package library;

public class Main {
    public static void main(String[] args) {


        Library myLibrary = new Library();
        Librarian headLibrarian = new Librarian("Оксана", "secure_password");

        Book book1 = new Book("Володар Перснів", "Дж.Р.Р. Толкін", 1954, 1178);
        Book book2 = new Book("Фауст", "Йоганн Вольфганг Гете", 1808, 624);
        Book book3 = new Book("Тигролови", "Іван Багряний", 1944, 352);
        Book book4 = new Book("Сто років самотності", "Габріель Гарсія Маркес", 1967, 432);
        Book book5 = new Book("Гаррі Поттер і філософський камінь", "Джоан Роулінг", 1997, 320);
        Book book6 = new Book("Маленький принц", "Антуан де Сент-Екзюпері", 1943, 96);
        Book book7 = new Book("Портрет Доріана Грея", "Оскар Вайльд", 1890, 254);
        myLibrary.addBook(book1);
        myLibrary.addBook(book2);
        myLibrary.addBook(book3);
        myLibrary.addBook(book4);
        myLibrary.addBook(book5);
        myLibrary.addBook(book6);
        myLibrary.addBook(book7);
        book1.displayInfo();
        book1.borrowBook();
        book1.borrowBook();
        book1.returnBook();

    }

}



