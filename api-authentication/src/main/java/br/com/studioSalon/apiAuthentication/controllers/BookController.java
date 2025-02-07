package br.com.studioSalon.apiAuthentication.controllers;

import br.com.studioSalon.apiAuthentication.dto.book.BookResponseDTO;
import br.com.studioSalon.apiAuthentication.dto.book.BookRequestDTO;
import br.com.studioSalon.apiAuthentication.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    private final BookService service;



    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }


    @PostMapping(value = "/v1/register")
    public ResponseEntity<BookResponseDTO> create(@RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(bookRequestDTO));
    }


    @GetMapping(value = "/v1/")
    public ResponseEntity<List<BookResponseDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }


    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<BookResponseDTO> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.findById(id));
    }


    @PutMapping(value = "/v1/{id}")
    public ResponseEntity<BookResponseDTO> update(
            @PathVariable(value = "id") Long id,
            @RequestBody BookRequestDTO bookRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.update(id, bookRequestDTO)
        );
    }


    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        this.service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
