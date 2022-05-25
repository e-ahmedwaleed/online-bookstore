package Bookstore;

public class LibraryAdmin {
    Library l;
    public LibraryAdmin(Library l) {
        this.l = l;
    }

    public void adminAddBook(String book, Integer count) {
        l.add_book(book, count);
    }

    public boolean adminRemoveBook(String book) {
        return l.remove_book(book);
    }
}
