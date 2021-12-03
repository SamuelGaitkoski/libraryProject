package br.util;

import br.com.andersonchoren.library.model.Book;
import br.com.andersonchoren.library.model.dto.BookDTO;

public class BookMapping { 
    //essa classe serve para converter um objeto BookDTO em uma entidade Book, e o contrário também
    //pois nosso BookDTO não tem as anotações que a entidade Book tem, então precisamos converter o DTO
    //O bookMapping é um método que fabrica objetos, que é um padrão de projeto, design patters
    private BookMapping(){}

    public static BookDTO fromBook(Book book) { //para BookDTO
        return new BookDTO(book.getId(), book.getName(), book.getAuthor(), book.getPublishing(), 
        book.getYearOfPublication(), book.getGenre());
    }

    public static Book toBook(BookDTO dto) { //para Book
        return new Book(dto.getId(), dto.getName(), dto.getAuthor(), dto.getPublishing(), 
        dto.getYearOfPublication(), dto.getGenre());
    }
}
