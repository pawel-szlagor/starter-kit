package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.GenerateId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("bookDaoImpl")
public class BookDaoImpl implements BookDao {

    private final Set<BookTo> ALL_BOOKS = new HashSet<>();

    @Value("#{sequence}")
    private Sequence sequence;

    public BookDaoImpl() {
        addTestBooks();
    }

    @Override
    public List<BookTo> findAll() {
        return new ArrayList<>(ALL_BOOKS);
    }

    @Override
    public List<BookTo> findBookByTitle(String title) {
    	List<BookTo> results = 
    	ALL_BOOKS.stream()
    	.filter(book -> book.getTitle().startsWith(title))
    	.collect(Collectors.toList());
    	return results;
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
    	List<BookTo> results = 
    	ALL_BOOKS.stream()
    	.filter(book -> book.getAuthors().startsWith(author))
    	.collect(Collectors.toList());
    	return results;
    }

    @Override
    @GenerateId
    public BookTo save(BookTo book) {
        ALL_BOOKS.add(book);
        return book;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    private void addTestBooks() {
        ALL_BOOKS.add(new BookTo(1L, "Romeo i Julia", "Wiliam Szekspir"));
        ALL_BOOKS.add(new BookTo(2L, "Opium w rosole", "Hanna Ożogowska"));
        ALL_BOOKS.add(new BookTo(3L, "Przygody Odyseusza", "Jan Parandowski"));
        ALL_BOOKS.add(new BookTo(4L, "Awantura w Niekłaju", "Edmund Niziurski"));
        ALL_BOOKS.add(new BookTo(5L, "Pan Samochodzik i Fantomas", "Zbigniew Nienacki"));
        ALL_BOOKS.add(new BookTo(6L, "Zemsta", "Aleksander Fredro"));
        ALL_BOOKS.add(new BookTo(7L, "W pustyni i w puszczy", "Henryk Sienkiewicz"));
    }
}
