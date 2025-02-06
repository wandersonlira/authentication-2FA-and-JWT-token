package br.com.studioSalon.apiAuthentication.repositories;

import br.com.studioSalon.apiAuthentication.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
