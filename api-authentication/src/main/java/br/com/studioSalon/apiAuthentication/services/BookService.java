package br.com.studioSalon.apiAuthentication.services;

import br.com.studioSalon.apiAuthentication.dto.book.BookResponseDTO;
import br.com.studioSalon.apiAuthentication.dto.book.BookRequestDTO;
import br.com.studioSalon.apiAuthentication.model.book.Book;
import br.com.studioSalon.apiAuthentication.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repository;


    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }


    public BookResponseDTO save(BookRequestDTO bookRequestDTO) {
        var entity = bookRequestDTO.toEntity();
        return new BookResponseDTO().toView(repository.save(entity));
    }

    public List<BookResponseDTO> findAll() {
        return repository.findAll().stream().map(
                b -> new BookResponseDTO().toView(b)).collect(Collectors.toList()
        );
    }

    public BookResponseDTO findById(Long id) {
        return new BookResponseDTO().toView(this.searchById(id));
    }

    public BookResponseDTO update(Long id, BookRequestDTO bookRequestDTO) {
        Book entity = this.searchById(id);
        Book toSaved = this.repository.save(
                checksDataToUpdate(entity, bookRequestDTO)
        );
        return new BookResponseDTO().toView(toSaved);
    }

    public void delete(Long id) {
        Book entity = this.searchById(id);
        this.repository.delete(entity);
    }

    private Book searchById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException("Element not found!")
                );
    }

    private Book checksDataToUpdate(Book entity, BookRequestDTO request) {
        if (request.getAuthor() != null && !request.getAuthor().isBlank()
                && !request.getAuthor().trim().equals(entity.getAuthor())) {
            entity.setAuthor(request.getAuthor());
        }
        if (request.getTitle() != null && !request.getTitle().isBlank()
                && !request.getTitle().trim().equals(entity.getTitle())) {
            entity.setTitle(request.getTitle());
        }
        if (request.getLaunchDate() != null && !request.getLaunchDate().equals(entity.getLaunchDate())) {
            entity.setLaunchDate(request.getLaunchDate());
        }
        if (request.getPrice() != null && !request.getPrice().equals(entity.getPrice())) {
            entity.setPrice(request.getPrice());
        }
        return entity;
    }

}
