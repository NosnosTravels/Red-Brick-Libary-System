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

ppublic class Book implements BookInterface {

    private String title;
    private String author;
    private ISBN isbn;
    private BookState State;
    private BookFormat Format;

    public Book(String title, String author, ISBN isbn, BookFormat Format, BookState State) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.Format = Format;
        this.State = State;
    }
    public Book() {
        throw new UnsupportedOperationException("Not supported yet.");
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
        return "Book{" + "title=" + title + ", author=" + author + ", isbn=" + isbn + '}';
    }

    public BookState getState() {
        return State;
    }

    public BookFormat getFormat() {
        return Format;
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
        this.State = State;
    }

    public void setFormat(BookFormat Format) {
        this.Format = Format;
    }

}
