package Bookstore;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Library library=new Library();
        while (true)
        {
            boolean user=true;
            System.out.println("User or Admin (U/A):");
            Scanner sc = new Scanner(System.in);
            char c = sc.next().charAt(0);
            if(c=='A')
                user=false;
            else if(c!='U')continue;
            if(user)
            {
                String bookName;
                int bookCount= 0;
                LibraryUser libraryUser=new LibraryUser(library);
                System.out.println("Enter a book you want to buy:");
                bookName= sc.next();
                System.out.println("Enter the number of copies:");
                bookCount= sc.nextInt();
                if(libraryUser.userBuyBook(bookName,bookCount))
                {
                    System.out.println("Thank you for your purchase!");
                }
                else
                {
                    System.out.println("Purchase failed!!");
                }
            }
            else
            {
                String bookName;
                int bookCount= 0;
                LibraryAdmin libraryAdmin=new LibraryAdmin(library);
                System.out.println("Add or Remove book (A/R):");
                c = sc.next().charAt(0);
                if(c=='A')
                {
                    System.out.println("Enter a book you want to Add:");
                    bookName= sc.next();
                    System.out.println("Enter the number of copies:");
                    bookCount= sc.nextInt();
                    libraryAdmin.adminAddBook(bookName,bookCount);
                    System.out.println("Success!");
                }
                else if(c=='R')
                {
                    System.out.println("Enter a book you want to Remove:");
                    bookName= sc.next();
                    if(libraryAdmin.adminRemoveBook(bookName))
                    {
                        System.out.println("Book Removed!");
                    }
                    else
                    {
                        System.out.println("failed!!");
                    }
                }
            }
        }
    }
}
