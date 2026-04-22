/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package book;

/**
 *
 * @author M2200478
 */
public class BookIn {
    String action;
    ISBN Isbn;
    String title;
    String author;
    BookFormat format;
    BookState state;

    public BookIn() {
        this.action = action;
        this.Isbn = Isbn;
        this.title = title;
        this.author = author;
        this.format = format;
        this.state = state;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ISBN getIsbn() {
        return Isbn;
    }

    public void setIsbn(ISBN Isbn) {
        this.Isbn = Isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookFormat getFormat() {
        return format;
    }

    public BookFormat setFormat(BookFormat format) {
        this.format = format;
        return null;
    }

    public BookState getState() {
        return state;
    }

    public void setState(BookState state) {
        this.state = state;
    }
    
    
    
}
