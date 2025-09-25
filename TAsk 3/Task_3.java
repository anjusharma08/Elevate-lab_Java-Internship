import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task_3 {

    // Book class
    static class Book {
        private String title;
        private String author;
        private boolean isIssued;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        public String getTitle() { return title; }
        public boolean isIssued() { return isIssued; }
        public void setIssued(boolean issued) { this.isIssued = issued; }

        @Override
        public String toString() {
            return title + " by " + author + (isIssued ? " [Issued]" : " [Available]");
        }
    }

    // User class
    static class User {
        private String name;
        private int userId;

        public User(String name, int userId) {
            this.name = name;
            this.userId = userId;
        }

        public int getUserId() { return userId; }
        @Override
        public String toString() {
            return "User: " + name + " (ID: " + userId + ")";
        }
    }

    // Library class
    static class Library {
        private List<Book> books = new ArrayList<>();
        private List<User> users = new ArrayList<>();

        public void addBook(Book book) {
            books.add(book);
            System.out.println("Added book: " + book.getTitle());
        }

        public void addUser(User user) {
            users.add(user);
            System.out.println("Added user: " + user.toString());
        }

        public void issueBook(String bookTitle, int userId) {
            Book bookToIssue = null;
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(bookTitle) && !book.isIssued()) {
                    bookToIssue = book;
                    break;
                }
            }
            if (bookToIssue != null) {
                bookToIssue.setIssued(true);
                System.out.println("Book '" + bookTitle + "' issued to user ID " + userId);
            } else {
                System.out.println("Book not available or already issued.");
            }
        }

        public void returnBook(String bookTitle) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(bookTitle) && book.isIssued()) {
                    book.setIssued(false);
                    System.out.println("Book '" + bookTitle + "' returned successfully.");
                    return;
                }
            }
            System.out.println("Book not found or not issued.");
        }

        public void showBooks() {
            System.out.println("\nLibrary Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Adding some books
        library.addBook(new Book("Java Basics", "Author A"));
        library.addBook(new Book("Python Guide", "Author B"));

        // Adding some users
        library.addUser(new User("Anju", 1));
        library.addUser(new User("Rahul", 2));

        int choice;
        do {
            System.out.println("\n1. Show Books\n2. Issue Book\n3. Return Book\n4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    library.showBooks();
                    break;
                case 2:
                    System.out.print("Enter book title to issue: ");
                    String issueTitle = sc.nextLine();
                    System.out.print("Enter user ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    library.issueBook(issueTitle, userId);
                    break;
                case 3:
                    System.out.print("Enter book title to return: ");
                    String returnTitle = sc.nextLine();
                    library.returnBook(returnTitle);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}
