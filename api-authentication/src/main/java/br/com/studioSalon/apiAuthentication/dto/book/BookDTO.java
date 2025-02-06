package br.com.studioSalon.apiAuthentication.dto.book;


import br.com.studioSalon.apiAuthentication.model.book.Book;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String author;
    private Date launchDate;
    private Double price;
    private String title;


    public BookDTO() {}

    public BookDTO(Long id, String author, Date launchDate, Double price, String title ) {
        this.id = id;
        this.author = author;
        this.launchDate = launchDate;
        this.price = price;
        this.title = title;
    }



    public Book toEntity() {
        return new Book(
                null, this.author, this.launchDate, this.price, this.title
        );
    }

    public BookDTO toView(Book book) {
        return new BookDTO(book.getId(), book.getAuthor(),
                book.getLaunchDate(), book.getPrice(), book.getTitle());
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
        this.launchDate = launchDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDTO bookDTO = (BookDTO) o;
        return Objects.equals(id, bookDTO.id) && Objects.equals(author, bookDTO.author) && Objects.equals(launchDate, bookDTO.launchDate) && Objects.equals(price, bookDTO.price) && Objects.equals(title, bookDTO.title);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(author);
        result = 31 * result + Objects.hashCode(launchDate);
        result = 31 * result + Objects.hashCode(price);
        result = 31 * result + Objects.hashCode(title);
        return result;
    }
}
