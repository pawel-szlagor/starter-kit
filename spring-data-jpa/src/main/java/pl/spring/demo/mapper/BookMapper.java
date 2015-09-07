package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookTo;

@Aspect
@Component("bookMapper")
public class BookMapper {

	public BookEntity map(BookTo bookTo) {
		if (bookTo == null)
			return null;
		else {
			String[] names = bookTo.getAuthors().split(" ");
			List<AuthorTo> authors = new ArrayList<>();
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			Sequence sequence = (Sequence) context.getBean("sequence");
			AuthorTo author = new AuthorTo(sequence.nextValue(authors), names[0], names[1]);
			authors.add(author);
			((AbstractApplicationContext) context).close();
			return new BookEntity(bookTo.getId(), bookTo.getTitle(), authors);
		}
	}

	public BookTo map(BookEntity bookEntity) {
		if (bookEntity == null)
			return null;
		else {
			StringBuffer nameBuffer = new StringBuffer();
			bookEntity.getAuthors().stream().map(author -> author.getFirstName() + " " + author.getLastName())
					.forEach(names -> nameBuffer.append(names));
			String name = new String(nameBuffer);
			return new BookTo(bookEntity.getId(), bookEntity.getTitle(), name);
		}
	}
}
