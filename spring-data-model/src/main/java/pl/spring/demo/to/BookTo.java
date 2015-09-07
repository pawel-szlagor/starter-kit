package pl.spring.demo.to;

import pl.spring.demo.BookAbstract;

public class BookTo extends BookAbstract {

    private String authors;

    public BookTo() {
    }

    public BookTo(Long id, String title, String authors) {
    	super(id, title);
        this.authors = authors;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
