package pl.spring.demo.dao;

import pl.spring.demo.annotation.GenerateId;
import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.to.BookTo;

import java.util.List;

public interface BookDao {

    List<BookTo> findAll();

    List<BookTo> findBookByTitle(String title);

    List<BookTo> findBooksByAuthor(String author);

    @NullableId
    @GenerateId
    BookTo save(BookTo book);
}
