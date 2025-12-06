package LibraryManagementSystem.system;

import LibraryManagementSystem.model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {

    public static List<Admin> admins = new ArrayList<>();
    public static List<Member> members = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();
    public static List<IssuedRecord> records = new ArrayList<>();

    static {
        admins.add(new Admin("admin", "admin"));
        members.add(new Member("user", "user"));

        books.add(new Book("B1","Clean Code","Robert Martin"));
        books.add(new Book("B2","Effective Java","Joshua Bloch"));
        books.add(new Book("B3","Java Basics","Oracle"));
        books.add(new Book("B4","Python Crash Course","Eric Matthes"));
        books.add(new Book("B5","Introduction to Algorithms","Thomas H. Cormen"));
books.add(new Book("B6","Design Patterns","Erich Gamma"));
books.add(new Book("B7","The Pragmatic Programmer","Andrew Hunt"));
books.add(new Book("B8","Head First Design Patterns","Eric Freeman"));
books.add(new Book("B9","Artificial Intelligence: A Modern Approach","Stuart Russell"));
books.add(new Book("B10","Deep Learning","Ian Goodfellow"));
books.add(new Book("B11","Machine Learning Yearning","Andrew Ng"));
books.add(new Book("B12","Hands-On Machine Learning with Scikit-Learn & TensorFlow","Aurélien Géron"));
books.add(new Book("B13","Database System Concepts","Abraham Silberschatz"));
books.add(new Book("B14","SQL Cookbook","Anthony Molinaro"));
books.add(new Book("B15","Learning SQL","Alan Beaulieu"));
books.add(new Book("B16","NoSQL Distilled","Pramod J. Sadalage"));
books.add(new Book("B17","Computer Networking: A Top-Down Approach","James F. Kurose"));
books.add(new Book("B18","TCP/IP Illustrated","W. Richard Stevens"));
books.add(new Book("B19","Network Security Essentials","William Stallings"));
books.add(new Book("B20","Cybersecurity and Cyberwar","P.W. Singer"));
books.add(new Book("B21","Hacking: The Art of Exploitation","Jon Erickson"));
books.add(new Book("B22","Cloud Computing: Concepts, Technology & Architecture","Thomas Erl"));
books.add(new Book("B23","Architecting Cloud Computing Solutions","Kevin L. Jackson"));
books.add(new Book("B24","AWS Certified Solutions Architect Official Study Guide","Ben Piper"));
books.add(new Book("B25","Microsoft Azure Fundamentals","Jim Cheshire"));
books.add(new Book("B26","Google Cloud Platform in Action","JJ Geewax"));
books.add(new Book("B27","Data Science for Business","Foster Provost"));
books.add(new Book("B28","Python for Data Analysis","Wes McKinney"));
books.add(new Book("B29","Storytelling with Data","Cole Nussbaumer Knaflic"));
books.add(new Book("B30","R for Data Science","Hadley Wickham"));
books.add(new Book("B31","Operating System Concepts","Abraham Silberschatz"));
books.add(new Book("B32","Modern Operating Systems","Andrew S. Tanenbaum"));
books.add(new Book("B33","Compilers: Principles, Techniques, and Tools","Alfred V. Aho"));
books.add(new Book("B34","Computer Organization and Design","David A. Patterson"));
books.add(new Book("B35","Programming Pearls","Jon Bentley"));
books.add(new Book("B36","Refactoring","Martin Fowler"));
books.add(new Book("B37","Agile Software Development","Robert C. Martin"));
books.add(new Book("B38","Software Engineering","Ian Sommerville"));
books.add(new Book("B39","Introduction to the Theory of Computation","Michael Sipser"));
books.add(new Book("B40","Discrete Mathematics and Its Applications","Kenneth H. Rosen"));
    }

    // ✅ Authenticate against existing lists
    public static User login(String username, String password, String type){
        if(type.equalsIgnoreCase("ADMIN")){
            for(Admin a: admins){
                if(a.getUsername().equals(username) && a.getPassword().equals(password)) return a;
            }
        } else if(type.equalsIgnoreCase("MEMBER")){
            for(Member m: members){
                if(m.getUsername().equals(username) && m.getPassword().equals(password)) return m;
            }
        }
        return null;
    }

    public static List<Book> searchBooks(String keyword){
        List<Book> result = new ArrayList<>();
        for(Book b: books){
            if(b.getTitle().toLowerCase().contains(keyword.toLowerCase())){
                result.add(b);
            }
        }
        return result;
    }

    public static void issueBook(String bookId, Member member){
        records.add(new IssuedRecord(bookId, member.getUsername(), LocalDate.now(), LocalDate.now().plusDays(7)));
        member.addHistory("Issued: " + bookId);
    }

    public static void returnBook(String bookId, Member member){
        for(IssuedRecord r: records){
            if(r.getBookId().equals(bookId) && r.getUsername().equals(member.getUsername()) && !r.isReturned()){
                r.setReturnDate(LocalDate.now());
                member.addHistory("Returned: " + bookId + " Fine: " + r.getFine());
                return;
            }
        }
    }
}
