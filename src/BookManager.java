import java.io.*;
import java.util.*;

public class BookManager {
    // attribute books
        ArrayList<Book> books;

    public BookManager() {
        this.books = new ArrayList<>(0);
    }

    public ArrayList<Book> getBooks() {
        return this.books;
    }

    /**
     * loading books from file books.txt and adding into this.books
     */
    public void loadFromFile() {
        System.out.println("Loading books...");
        try {
            BufferedReader br = new BufferedReader(new FileReader("books.txt"));
            String s;
            while((s = br.readLine()) != null){
                s = s.trim();
                String[] attributes = s.split("\\s+");
                String name = "";
                for(int i = 1; i < attributes.length - 1; i++){
                    name += attributes[i] + " ";
                }
                this.books.add(new Book(Integer.parseInt(attributes[0]), name.trim(), Double.parseDouble(attributes[attributes.length - 1])));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * print books (one book per line) in required format
     */
    public void printBooks(ArrayList<Book> books) {
        if(!books.isEmpty()) {
            System.out.printf("%-5s %-45s %-10s\r\n", "ID", "Name", "Price");
            for (Book x : books) {
                System.out.println(x);
            }
        } else
            System.out.println("(empty)");
    }

    /**
     * if book.id is not duplicated, add book to this.books
     * return whether added or not
     */
    public boolean add(Book book) {
        for(Book x : this.books){
            if(book.id.equals(x.id)){
                return false;
            }
        }
        return this.books.add(book);
    }

    /**
     * return book specified by id, null if not found
     */
    public Book getBookById(int id) {
        for (Book x : this.books) {
            if (x.id == id)
                return x;
        }
        return null;
    }

    /**
     * remove book from this.books
     */
    public void remove(Book book) {
        for(Book x : this.books) {
            if(x == book){
                this.books.remove(x);
                break;
            }
        }
    }

    /**
     * update this.books to be sorted by price from high -> low
     */
    public void sortDescByPrice() {
        this.books.sort((b1, b2) -> (int) (b2.price - b1.price));
    }

    /**
     * return all books having name contains keyword (case in-sensitive)
     */
    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> matches = new ArrayList<>();
        for(Book x : this.books){
            if(x.name.toUpperCase().contains(keyword.toUpperCase()))
                matches.add(x);
        }
        return matches;
    }

    /**
     * write this.books to file books.txt in required format
     */
    public void saveToFile() {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter("books.txt"));
            for (Book x : this.books) {
                bf.write(x.toString());
                bf.newLine();
            }
            bf.close();
            System.out.println("Saving to file...");
            System.out.print("Bye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}