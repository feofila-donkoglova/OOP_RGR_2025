package library.strategy;

import library.model.Book;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByYear implements SearchStrategy {
    public List<Book> search(List<Book> books, String keyword) {
        try {
            int year = Integer.parseInt(keyword);
            return books.stream()
                    .filter(book -> book.getYear() == year)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Неправильний формат року: " + keyword);
            return List.of();
        }
    }
}
