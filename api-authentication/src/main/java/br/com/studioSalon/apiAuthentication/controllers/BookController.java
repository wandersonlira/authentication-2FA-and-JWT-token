package br.com.studioSalon.apiAuthentication.controllers;

import br.com.studioSalon.apiAuthentication.dto.book.BookDTO;
import br.com.studioSalon.apiAuthentication.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
public class BookController {

    @Autowired
    private BookService service;


    @PostMapping(value = "/v1/register")
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(bookDTO));
    }


    @GetMapping(value = "/v1/")
    public ResponseEntity<List<BookDTO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }


    @PutMapping(value = "/v1/{id}")
    public ResponseEntity<BookDTO> update(
            @PathVariable(value = "id") Long id,
            @RequestBody BookDTO request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.service.update(id, request)
        );
    }


    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        this.service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
