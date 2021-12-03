package controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersonchoren.library.model.Genre;
import br.com.andersonchoren.library.model.dto.BookDTO;
import br.com.andersonchoren.library.service.BookService;
import br.util.BookMapping;

/*
    {
    "name":"Java - Como programar 8°",
    "author":"Samuel Gaitkoski",
    "publishing":"Editora QI",
    "yearOfPublication":2021,
    "Genre":5
}
*/


@RestController
@RequestMapping("/books") //isso responde pela rota /books
public class BookController {
    
    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<BookDTO> insert(@RequestBody BookDTO dto) { 
        //RequestBody pois o cliente vai preencher o parâmetro dto
        //sempre retornamos uma ResponseEntity, pois ai podemos retornar o status code da requisição, 404, 200, etc
        if(dto.getId() != 0) {
            return new ResponseEntity<>(new BookDTO(), HttpStatus.BAD_REQUEST);
        }
        
        var book = service.insertOrUpdate(BookMapping.toBook(dto));
        if(book != null) { //é diferente de nulo
            return new ResponseEntity<>(BookMapping.fromBook(book), HttpStatus.CREATED); //retorna o book como DTO
        }
        return new ResponseEntity<>(new BookDTO(), HttpStatus.NOT_FOUND); //retorna o DTO como not found
    }

    @PutMapping
    public ResponseEntity<BookDTO> update(@RequestBody BookDTO dto) {
        //o objeto que vai ser recebido, o dto, vai ser recebido pelo corpo da requisição, pelo RequestBody
        if(dto.getId() <= 0) { //se o id for inválido, menor ou igual a 0, ou menor que 1 somente
            return new ResponseEntity<>(new BookDTO(), HttpStatus.BAD_REQUEST); //requisição inválida
        }

        //vamos verificar se existe esse livro no banco mesmo
        var book = service.findById(dto.getId());

        if(book.isPresent()) { //se ele achou algum livro
            return new ResponseEntity<>(
                BookMapping.fromBook(
                    service.insertOrUpdate(
                        BookMapping.toBook
                        (dto))), HttpStatus.OK);
        }
        return new ResponseEntity<>(new BookDTO(), HttpStatus.NOT_FOUND); //se livro for nulo vai cair aqui
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
            return new ResponseEntity<>(service.findAll().stream().map((obj) -> BookMapping.fromBook(obj)).
            collect(Collectors.toList()), HttpStatus.OK);
            //o findAll do service retorna uma lista de Books, 
            //então temos que converter Books para livros DTO, fazemos isso pela função de stream map,
            //que passa cada elemento da lista para uma função anonima, pegando cada obj e passando cada um
            //para bookDTO, dai por fim para retornar uma lista coletamos cada dado e colocamos em uma lista
    } 

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        var book = service.findById(id); //ver se o livro existe

        if(book.isPresent()) {
            var isSuccess = service.delete(id);

            if(isSuccess) {
                return new ResponseEntity<>("Livro Removido com sucesso!!", HttpStatus.OK);
            }

            return new ResponseEntity<>("Não foi possível remover o livro!!", HttpStatus.NOT_FOUND);
        }
        //Se o livro não existir no banco
        return new ResponseEntity<>("Livro não localizado!!", HttpStatus.NOT_FOUND); 
    }

    @GetMapping("/author/{name}")
    public ResponseEntity<List<BookDTO>> findByAuthor(@PathVariable String name) { 
        //lambda expression obj -> obj
        return new ResponseEntity<>(service.findByAuthor(name).stream().
        map((obj) -> (BookMapping.fromBook(obj))).collect(Collectors.toList()), HttpStatus.OK);
        //dessa forma retornamos pelo método findByAuthor uma lista de bookDTO
    }

    @GetMapping("/publishing/{name}")
    public ResponseEntity<List<BookDTO>> findByPublishing(@PathVariable String name) { 
        return new ResponseEntity<>(service.findByPublishing(name).stream().
        map((obj) -> (BookMapping.fromBook(obj))).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/yearOfPublication/{year}")
    public ResponseEntity<List<BookDTO>> findByYearOfPublication(@PathVariable int year) { 
        return new ResponseEntity<>(service.findByYearOfPublication(year).stream().
        map((obj) -> (BookMapping.fromBook(obj))).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDTO>> findByGenre(@PathVariable Genre genre) { 
        return new ResponseEntity<>(service.findByGenre(genre).stream().
        map((obj) -> (BookMapping.fromBook(obj))).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<BookDTO> findByName(@PathVariable String name) {
        var book = service.findByName(name);

        if(book != null) { 
            return new ResponseEntity<>(
                BookMapping.fromBook(book), HttpStatus.OK); //fromBook é para bookDTO
        }
        return new ResponseEntity<>(new BookDTO(), HttpStatus.NOT_FOUND);
    }

}
