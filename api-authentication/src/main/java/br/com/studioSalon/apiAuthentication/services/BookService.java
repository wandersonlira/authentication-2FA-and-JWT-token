package br.com.studioSalon.apiAuthentication.services;

import br.com.studioSalon.apiAuthentication.dto.book.BookDTO;
import br.com.studioSalon.apiAuthentication.model.book.Book;
import br.com.studioSalon.apiAuthentication.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;


    public BookDTO save(BookDTO bookDTO) {
        var entity = bookDTO.toEntity();
        return bookDTO.toView(repository.save(entity));
    }

    public List<BookDTO> findAll() {
        return repository.findAll().stream().map(
                b -> new BookDTO().toView(b)).collect(Collectors.toList()
        );
    }

    public Book findById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Element not found for deletion!")
                );
    }

    public BookDTO update(Long id, BookDTO request) {
        Book entity = this.findById(id);
        entity.setAuthor(request.getAuthor());
        entity.setTitle(request.getTitle());
        entity.setLaunchDate(request.getLaunchDate());
        entity.setPrice(request.getPrice());
        Book toSaved = this.repository.save(entity);
        return request.toView(toSaved);
    }

    public void delete(Long id) {
        Book entity = repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Element not found for deletion!"));
        this.repository.delete(entity);
    }

}
