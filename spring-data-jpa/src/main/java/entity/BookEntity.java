package entity;


import java.util.List;

import pl.spring.demo.BookAbstract;
import pl.spring.demo.to.AuthorTo;

public class BookEntity extends BookAbstract {

	private List<AuthorTo> authors;

	public BookEntity() {
	    }

	public BookEntity(Long id, String title, List<AuthorTo> authors) {
	    	super(id, title);
	        this.authors = authors;
	    }

	public List<AuthorTo> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorTo> authors) {
		this.authors = authors;
	}

}
