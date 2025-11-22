package library.factory;

import library.model.Book;
import library.model.EBook;
import library.utils.Utils;

public class BookFactory {

    public static Book createBook(String title, String author, String genre, int pageCount, int year) {
        String bookId = Utils.generateBookId(title, author, year, pageCount);

        return new Book.BookBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setGenre(genre)
                .setPageCount(pageCount)
                .setYear(year)
                .setBookId(bookId)
                .build();
    }

    public static EBook createEBook(String title, String author, String genre, int pageCount, int year, String downloadLink, String format) {
        String bookId = Utils.generateBookId(title, author, year, pageCount);

        return new EBook.EBookBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setGenre(genre)
                .setPageCount(pageCount)
                .setYear(year)
                .setBookId(bookId)
                .setDownloadLink(downloadLink)
                .setFormat(format)
                .build();
    }
}
