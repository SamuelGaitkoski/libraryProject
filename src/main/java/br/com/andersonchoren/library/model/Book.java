package br.com.andersonchoren.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //tornar o id auto incrementavel
    private int id;
    @Column(columnDefinition = "varchar(50) not null")
    private String name;
    @Column(columnDefinition = "varchar(70) not null")
    private String author;
    @Column(columnDefinition = "varchar(70) not null")
    private String publishing;
    @Column(nullable = false, length = 4)
    private int yearOfPublication;
    @Column(nullable = false, length = 1)
    private Genre genre;

    public Book() {
    }

    public Book(int id, String name, String author, String publishing, int yearOfPublication, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishing = publishing;
        this.yearOfPublication = yearOfPublication;
        this.genre = genre;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishing() {
        return this.publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public int getYearOfPublication() {
        return this.yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
