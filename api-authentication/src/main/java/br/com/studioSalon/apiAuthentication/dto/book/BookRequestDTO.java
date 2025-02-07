package br.com.studioSalon.apiAuthentication.dto.book;


import br.com.studioSalon.apiAuthentication.model.book.Book;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class BookRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String author;
    private Date launchDate;
    private Double price;
    private String title;


    public BookRequestDTO() {}

    public BookRequestDTO(String author, Date launchDate, Double price, String title ) {
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

/*    public BookRequestDTO toView(Book book) {
        return new BookRequestDTO(book.getId(), book.getAuthor(),
                book.getLaunchDate(), book.getPrice(), book.getTitle());
    }*/



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

        BookRequestDTO that = (BookRequestDTO) o;
        return Objects.equals(author, that.author) && Objects.equals(launchDate, that.launchDate) && Objects.equals(price, that.price) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(author);
        result = 31 * result + Objects.hashCode(launchDate);
        result = 31 * result + Objects.hashCode(price);
        result = 31 * result + Objects.hashCode(title);
        return result;
    }

}
