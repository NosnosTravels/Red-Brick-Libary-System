/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book;

import java.util.Scanner;
/**
 *
 * @author M2200478
 */

public class Book implements BookInterface {

    private String title;
    private String author;
    private ISBN isbn;
    private BookState state;
    private BookFormat format;

    public Book(String title, String author, ISBN isbn, BookFormat format, BookState state) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.format = format;
        this.state = state;
    }
    public Book() {
        
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public ISBN getISBN() {
        return this.isbn;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", author=" + author + ", isbn=" + isbn + ", State=" + state + ", Format=" + format + '}';
    }

    public BookState getState() {
        return state;
    }

    public BookFormat getFormat() {
        return format;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(ISBN isbn) {
        this.isbn = isbn;
    }

    public void setState(BookState State) {
        this.state = State;
    }

    public void setFormat(BookFormat Format) {
        this.format = Format;
    }
    
    

}
