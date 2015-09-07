package pl.spring.demo;

import pl.spring.demo.to.IdAware;

public abstract class BookAbstract implements IdAware {
	
	private Long id;
    private String title;

    public BookAbstract() {
    }

    public BookAbstract(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
