package Bookstore;
import static org.junit.Assert.*;
import org.junit.Test;
import java.time.*;
import java.util.Stack;

public class LibraryTest {

    @Test
    public void testAddBooks(){
        Library l = new Library();
        LibraryAdmin admin = new LibraryAdmin(l);
        admin.adminAddBook("street fighter 7", 18);
        assertEquals(l.LibraryBookCount(), 1);
    }

    @Test
    public void testRemoveBooks(){
        Library l = new Library();
        LibraryAdmin admin = new LibraryAdmin(l);
        admin.adminAddBook("street fighter 7", 18);
        admin.adminAddBook("street fighter 8", 12);
        assertEquals(l.LibraryBookCount(), 2);
        admin.adminRemoveBook("street fighter 8");
        assertEquals(l.LibraryBookCount(), 1);
        admin.adminRemoveBook("street fighter 7");
        assertEquals(l.LibraryBookCount(), 0);
    }

    @Test
    public void testRemoveInvalidBooks(){
        Library l = new Library();
        LibraryAdmin admin = new LibraryAdmin(l);
        assertFalse(admin.adminRemoveBook("street fighter 7"));
    }

    @Test
    public void testBuyBooks(){
        Library l = new Library();
        LibraryUser user = new LibraryUser(l);
        l.add_book("street fighter 7", 15);
        assertTrue(user.userBuyBook("street fighter 7", 5));
    }

    @Test
    public void testBuyInvalidBooks(){
        Library l = new Library();
        LibraryUser user = new LibraryUser(l);
        assertFalse(user.userBuyBook("street fighter 7", 5));
    }

    @Test
    public void testBuyMoreCount(){
        Library l = new Library();
        LibraryUser user = new LibraryUser(l);
        l.add_book("street fighter 7", 15);
        assertFalse(user.userBuyBook("street fighter 7", 115));
    }

    @Test
    public void testBuyEqualCount(){
        Library l = new Library();
        LibraryUser user = new LibraryUser(l);
        l.add_book("street fighter 7", 15);
        assertTrue(user.userBuyBook("street fighter 7", 15));
    }

    @Test
    public void stressTestBooks(){
        int maxBooks = 3;
        for(int multiplier = 0; multiplier < 6; multiplier++) {
            maxBooks *= 10;
            long startTime = System.nanoTime();
            Library l = new Library();
            LibraryAdmin admin = new LibraryAdmin(l);
            for(int i = 0; i < maxBooks; i++) {
                admin.adminAddBook("street fighter " + String.valueOf(i), 100);
            }
            LibraryUser user = new LibraryUser(l);
            for(int i = 0; i < maxBooks* 10; i+=10) {
                admin.adminRemoveBook("street fighter " + String.valueOf(i));
            }
            for(int j = 0; j < 2; j++) {
                for(int i = 0; i <maxBooks; i++) {
                    if(i%10 == 0) {
                        assertFalse( user.userBuyBook("street fighter " + String.valueOf(i), 50));
                    } else {
                        assertTrue(user.userBuyBook("street fighter " + String.valueOf(i), 50));
                    }
                }
            }

            for(int i = 0; i < maxBooks; i++) {
                assertFalse( user.userBuyBook("street fighter " + String.valueOf(i), 50));
            }

            for(int i = 0; i < maxBooks; i++) {
                if(i%10 == 0) {
                    assertFalse(admin.adminRemoveBook("street fighter " + String.valueOf(i)));
                } else {
                    assertTrue(admin.adminRemoveBook("street fighter " + String.valueOf(i)));
                }
            }

            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Stress Test for Book count " + maxBooks + "-> Time taken to add books " + totalTime/1e9);
        }
    }
}