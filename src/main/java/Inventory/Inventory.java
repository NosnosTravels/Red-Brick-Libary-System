package Inventory;

import book.Book;
import book.ISBN;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.emptyList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author M2200478
 */
public class Inventory {
    private final Map<ISBN, List<Book>> byISBN = new HashMap<>();
    private static final List<Book> EMPTY_LIST = Collections.emptyList();
            
    public void addCopy(Book b) {
//    adding a book to the hash map;
        ISBN isbn = b.getISBN(); //gets the ISBN of the book were adding
        if (byISBN.containsKey(isbn)){
            byISBN.get(isbn).add(b);
        } else {
            List<Book> newList = new ArrayList<>();
            newList.add(b);
            byISBN.put(isbn, newList);
        }
    }
    
    public int countCopies(ISBN isbn){
//  counting the books in the libary
    if (byISBN.containsKey(isbn)){
        return byISBN.get(isbn).size();
    }
        return 0;        
    }
    
    public List<Book> listCopies(ISBN isbn){
//  listing the books
    List<Book> books = byISBN.get(isbn);
    return books != null ? new ArrayList<>(books) : new ArrayList<>();
    }
    
    public boolean removeOneCopy(ISBN isbn){
//    deleting the books
        if (byISBN.containsKey(isbn)) {
            List<Book> books = byISBN.get(isbn);
            if(!books.isEmpty()) {
                //removes the first book from the list
                books.remove(0);
                
                //if the list is now empty, remove the entry from the map
                if (books.isEmpty()) {
                    byISBN.remove(isbn);
                }
            }
        }
        return false;
    }
}
