import model.Book;
import model.Member;
import service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryService service = new LibraryService();

        System.out.println("Welcome to the Library Management System (MySQL Edition)!");
        while (true) {
            System.out.println("\n1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter author: ");
                    String author = sc.nextLine();
                    service.addBook(new Book(title, author));
                }
                case 2 -> {
                    List<Book> books = service.getAllBooks();
                    for (Book b : books)
                        System.out.println(b);
                }
                case 3 -> {
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    System.out.print("Enter Member ID: ");
                    int mid = sc.nextInt();
                    service.issueBook(bid, mid);
                }
                case 4 -> {
                    System.out.print("Enter Book ID: ");
                    int bid = sc.nextInt();
                    System.out.print("Enter Member ID: ");
                    int mid = sc.nextInt();
                    service.returnBook(bid, mid);
                }
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
