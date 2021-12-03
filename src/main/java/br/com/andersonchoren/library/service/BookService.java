package br.com.andersonchoren.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.andersonchoren.library.model.Book;
import br.com.andersonchoren.library.model.Genre;
import br.com.andersonchoren.library.repository.BookRepository;

@Service
public class BookService {
    
    //fazemos isso com o autowired pois não podemos instanciar uma interface, no caso, o BookRepository
    @Autowired //autowired instancia um objeto do tipo BookRepository para nós
    private BookRepository repository; 

    public Book insertOrUpdate(Book book) {
        return repository.save(book);
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public boolean delete(int id) {
        var book = repository.findById(id); //retorna um optional, por isso fazemos o if abaixo
        //para verificar se tem algo na variavel book

        if(book.isPresent()) {
            repository.deleteById(id);
            book = repository.findById(id);
            return book.isEmpty();
        }
        return false;
    }

    public Book findByName(String name) {
        var book = repository.findByNameContains(name);

        if(book.isPresent()) { //se ele não achar
            return book.get(); //retorna o livro encontrado
        }
        return new Book(); //retorna um livro em branco, para não retornar nada nulo
        //WEB Service nunca é bom retornarmos nulo
    }
    
    public List<Book> findByAuthor(String author) {
        return repository.findByAuthor(author); //retornar uma lista, vazia ou com livros referente ao ator
    }

    public List<Book> findByPublishing(String name) {
        return repository.findByPublishing(name); 
    }

    public List<Book> findByYearOfPublication(int year) {
        return repository.findByYearOfPublication(year);
    }

    public List<Book> findByGenre(Genre genre) {
        return repository.findByGenre(genre); 
        //a pessoa pode pesquisar os livros de várias formas, author, genre, etc
    }

    public Optional<Book> findById(int id) {
        return repository.findById(id);
    }

}
