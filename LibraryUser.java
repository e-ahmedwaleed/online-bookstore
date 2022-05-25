package Bookstore;

public class LibraryUser {
    Library l;
    public LibraryUser(Library l) {
        this.l = l;
    }

    public boolean userBuyBook(String book, Integer count) {
        return l.buy_book(book, count);
    }
}
