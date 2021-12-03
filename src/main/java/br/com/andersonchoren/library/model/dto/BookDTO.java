package br.com.andersonchoren.library.model.dto;

import br.com.andersonchoren.library.model.Genre;

public class BookDTO {
    private int id;
    private String name;
    private String author;
    private String publishing;
    private int yearOfPublication;
    private Genre genre;

    public BookDTO() {
    }

    public BookDTO(int id, String name, String author, String publishing, int yearOfPublication, Genre genre) {
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
