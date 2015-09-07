package pl.spring.demo.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testShouldFindAllBooks() {
    	// when
        List<BookTo> allBooks = bookService.findAllBooks();
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(7, allBooks.size());
    }


    @Test
    public void testShouldFindAllBooksByFullTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
        assertEquals(booksByTitle.get(0).getTitle(), title);
    }
    
    @Test
    public void testShouldFindAllBooksByFullAuthorsName() {
        // given
        final String authorsName = "Henryk Sienkiewicz";
        // when
        List<BookTo> booksByAuthor = bookService.findBooksByAuthor(authorsName);
        // then
        assertNotNull(booksByAuthor);
        assertFalse(booksByAuthor.isEmpty());
        assertEquals(booksByAuthor.get(0).getAuthors(), authorsName);
    }
    
    @Test
    public void testShouldFindAllBooksByPartialTitle() {
        // given
        final String title = "Opiu";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
        assertEquals(booksByTitle.get(0).getTitle().substring(0,4), title);
    }
    
    @Test
    public void testShouldFindAllBooksByPartialAuthorsName() {
        // given
        final String authorsName = "H";
        // when
        List<BookTo> booksByAuthor = bookService.findBooksByAuthor(authorsName);
        // then
        assertNotNull(booksByAuthor);
        assertEquals(booksByAuthor.size(),2);
        assertEquals(booksByAuthor.get(0).getAuthors().substring(0,1), authorsName);  
        assertEquals(booksByAuthor.get(1).getAuthors().substring(0,1), authorsName);
    }
    

    @Ignore
    @Test
    public void testShouldAddNewBookWithoutId(){
    	//given
    	final BookTo bookToSave = new BookTo();
    	bookToSave.setTitle("W pustyni i w puszczy");
    	bookToSave.setAuthors("Henryk Sienkiewicz");
    	List<BookTo> allBooks = bookService.findAllBooks();
    	int startSize = allBooks.size();
    	//when
    	bookService.saveBook(bookToSave);   	
    	//then
    	assertFalse(allBooks.contains(bookToSave));
    	assertNotNull(bookToSave.getId());
    	assertEquals(startSize+1, bookService.findAllBooks().size());
    }
    

    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
        final BookTo bookToSave = new BookTo();
        bookToSave.setId(22L);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }
    
    @Test
    public void testShouldMapBookToToBookEntity(){
    	//given
    	final BookTo bookToMap = new BookTo();
    	bookToMap.setId(21L);
    	bookToMap.setTitle("W pustyni i w puszczy");
    	bookToMap.setAuthors("Henryk Sienkiewicz");
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
    	BookMapper mapper = (BookMapper) context.getBean("bookMapper");
    	//when
    	BookEntity bookEntity = mapper.map(bookToMap);
    	//then
    	assertNotNull(bookEntity);
    	assertEquals(bookEntity.getId(), bookToMap.getId());
    	assertEquals(bookToMap.getTitle(), bookEntity.getTitle());
    	assertEquals(bookEntity.getAuthors().size(), 1);
    	assertEquals(bookEntity.getAuthors().get(0).getFirstName(), "Henryk");
    	assertEquals(bookEntity.getAuthors().get(0).getLastName(), "Sienkiewicz");
    	((AbstractApplicationContext) context).close(); 	
    }
    
    @Test
    public void testShouldMapBookEntityToBookTo(){
    	//given
    	final BookEntity bookToMap = new BookEntity();
    	bookToMap.setId(21L);
    	bookToMap.setTitle("W pustyni i w puszczy");
    	List<AuthorTo> authors= new ArrayList<>();
    	authors.add(new AuthorTo(1L,"Henryk","Sienkiewicz"));
    	bookToMap.setAuthors(authors);
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
    	BookMapper mapper = (BookMapper) context.getBean("bookMapper");
    	//when
    	BookTo bookTo = mapper.map(bookToMap);
    	//then
    	assertNotNull(bookTo);
    	assertEquals(bookTo.getId(), bookToMap.getId());
    	assertEquals(bookToMap.getTitle(), bookTo.getTitle());
    	assertEquals(bookTo.getAuthors(), "Henryk Sienkiewicz");
    	((AbstractApplicationContext) context).close(); 	
    }
    
   
}
