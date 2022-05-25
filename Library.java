package Bookstore;

import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    HashMap <String, Integer> books = new HashMap<>();
    public void add_book(String book, Integer count) {
        if(books.containsKey(book)) {
            books.put(book, count + books.get(book));
        } else {
            books.put(book, count);
        }
    }

    public boolean remove_book(String book) {
        if(books.containsKey(book)) {
            books.remove(book);
            return true;
        } else {
            return false;
        }
    }

    public boolean buy_book(String book, Integer count) {
        if(books.containsKey(book)) {
            if(books.get(book) < count) {
                return false;
            } else {
                books.put(book, books.get(book) - count);
                return true;
            }
        } else {
            return false;
        }
    }

    public int LibraryBookCount() {
        return books.size();
    }
}
