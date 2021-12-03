package br.com.andersonchoren.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.andersonchoren.library.model.Book;
import br.com.andersonchoren.library.model.Genre;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    //no Java temos o JPQL (Java Persistence Query Language)
    
    public Optional<Book> findByNameContains(String name);
    public List<Book> findByAuthor(String author);
    public List<Book> findByYearOfPublication(int year);
    public List<Book> findByPublishing(String name);
    public List<Book> findByGenre(Genre genre);
}
