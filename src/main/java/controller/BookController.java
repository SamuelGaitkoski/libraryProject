package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.andersonchoren.library.model.dto.BookDTO;
import br.com.andersonchoren.library.service.BookService;
import br.util.BookMapping;

//só um controller, que é um controlador para a web, controlador web, o REST é o controlador para requisições 
//REST, requisições para a API, O rest controller sempre retorna um objeto JSON, com dados do que foi inserido,
//ou com inserções em JSON
//O controller vai me retornar uma página WEB, um conteúdo web, um conteúdo html, agora vou redirecionar o usuário
//para uma página web

//O thymeleaf é um template engine, que nos permite renderizar conteúdo web usando o java, precisamos dessa 
//dependência para que a rota /books nos retorne uma página web, com conteúdo html
@Controller 
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping //quando eu digitar /books na url, o método que vai ser chamado é esse
    public String formInsert(Model model) { 
        //esse objeto model serve para nos transportar objetos entre a visão e classe modelo, 
        //MVVM (Model view view model), para termos objeto model vinculado ao html, para la no front, 
        //ser vinculado o que a pessoa escrever, e o java receber isso, para ele vincular o conteúdo 
        //da caixa de texto com o conteúdo do objeto book

        BookDTO book = new BookDTO(); //exportar visualmente pro usuário um objeto DTO

        model.addAttribute("objBook", book); //fazendo tipo um map, atrelando o objeto book pela chave objBook, 
        //para pegar ele no frontend

        return "pages/register"; //aqui eu passo um caminho pela pasta templates, que é onde eu carrego meus arquivos 
        //html, dai dentro da pasta pages eu vou ter um arquivo html chamado register, que deve sempre ser 
        //usada para colocar arquivo html, e a pasta static para colocar arquivos css, isso é o padrão
        //pages pois o arquivo html register está dentro da pasta pages, que está dentro da pasta templates
    }

    //dizer que alguem vai receber isso, dai temos que fazer uma PostMapping aqui para tratar os dados que virão
    @PostMapping
    public String insert(@ModelAttribute BookDTO bookDTO) {
        //sempre retorna uma string, que é um recurso para a url, uma página html que vai dizer algo
        //Como eu passo o model no get, agora eu quero receber ele pela anotação ModelAttribute, 
        //convertendo isso para um objeto DTO, um bookDTO
        var book = bookService.insertOrUpdate(BookMapping.toBook(bookDTO)); //convertendo de bookDTO pra book

        if(book.getId() != 0) { //se book tiver um id diferente de 0
            return "pages/success"; //vai retornar a pagina pages/success
        } else {
            return "pages/error"; //vai abrir a pagina pages/error
        }
    }
}
