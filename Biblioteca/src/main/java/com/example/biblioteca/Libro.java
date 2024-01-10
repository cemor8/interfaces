package com.example.biblioteca;

public class Libro {
    private String titulo;
    private String autor;
    private String Isbn;
    private String fecha;

    public Libro(String titulo, String autor, String isbn, String fecha) {
        this.titulo = titulo;
        this.autor = autor;
        Isbn = isbn;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return Isbn;
    }

    public String getFecha() {
        return fecha;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setIsbn(String isbn) {
        Isbn = isbn;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
