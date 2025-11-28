package library.strategy;

import library.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchServiceTest {

    private List<Book> books;

    @BeforeEach
    void setUp() {
        books = List.of(
                new Book.BookBuilder().setTitle("1984").setAuthor("Джордж Орвелл").setYear(1949).build(),
                new Book.BookBuilder().setTitle("Скотоферма").setAuthor("Джордж Орвелл").setYear(1945).build(),
                new Book.BookBuilder().setTitle("Кобзар").setAuthor("Тарас Шевченко").setYear(1840).build()
        );
    }

    @Test
    void testSearchByTitleStrategy() {
        SearchService searchService = new SearchService(new SearchByTitle());

        List<Book> results = searchService.search(books, "Скотоферма");
        assertEquals(1, results.size());
        assertEquals("Скотоферма", results.get(0).getTitle());

        results = searchService.search(books, "198");
        assertEquals(1, results.size(), "Пошук має бути частковим");
        assertEquals("1984", results.get(0).getTitle());
    }

    @Test
    void testSearchByAuthorStrategy() {
        SearchService searchService = new SearchService(new SearchByAuthor());

        List<Book> results = searchService.search(books, "орвелл");
        assertEquals(2, results.size(), "Пошук має бути нечутливим до регістру");

        results = searchService.search(books, "Шевченко");
        assertEquals(1, results.size());
        assertEquals("Кобзар", results.get(0).getTitle());
    }

    @Test
    void testSearchByYearStrategy() {
        SearchService searchService = new SearchService(new SearchByYear());

        List<Book> results = searchService.search(books, "1949");
        assertEquals(1, results.size());
        assertEquals("1984", results.get(0).getTitle());

        results = searchService.search(books, "194");
        assertEquals(0, results.size(), "Пошук по року має бути точним");

        // Неправильний формат
        results = searchService.search(books, "invalid");
        assertTrue(results.isEmpty());
    }

    @Test
    void testSetStrategyAtRuntime() {
        SearchService searchService = new SearchService(new SearchByTitle());

        // Починаємо з пошуку за назвою
        List<Book> results = searchService.search(books, "1984");
        assertEquals(1, results.size());

        // Змінюємо стратегію на пошук за автором
        searchService.setStrategy(new SearchByAuthor());
        results = searchService.search(books, "Шевченко");
        assertEquals(1, results.size());
        assertEquals("Кобзар", results.get(0).getTitle());
    }
}