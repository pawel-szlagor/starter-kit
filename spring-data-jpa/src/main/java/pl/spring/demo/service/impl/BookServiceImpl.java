package pl.spring.demo.service.impl;

import pl.spring.demo.annotation.GenerateId;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("bookServiceImpl")
public class BookServiceImpl implements BookService {

	@Autowired
    private BookDao bookDao;

    @Override
    public List<BookTo> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public List<BookTo> findBooksByTitle(String title) {
        return bookDao.findBookByTitle(title);
    }

    @Override
    public List<BookTo> findBooksByAuthor(String author) {
        return bookDao.findBooksByAuthor(author);
    }

    @Override
    @GenerateId
    public BookTo saveBook(BookTo book) {
        return bookDao.save(book);
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
