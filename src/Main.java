import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        BookManager BookMan = new BookManager();
        BookMan.loadFromFile();
        Scanner sc = new Scanner(System.in);
        boolean isOk = true;
        while(isOk) {
            Menu();
            try {
            int opt = sc.nextInt();
            switch (opt) {
                case 0 -> {
                    BookMan.saveToFile();
                    isOk = false;
                }
                case 1 -> BookMan.printBooks(BookMan.getBooks());
                case 2 -> {
                    System.out.print("Enter book id: ");
                    int ID2 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter book name: ");
                    String name2 = sc.nextLine();
                    System.out.print("Enter book price: ");
                    double price2 = sc.nextDouble();
                    if (BookMan.add(new Book(ID2, name2, price2)))
                        System.out.println("Added successfully.");
                    else
                        System.out.println("Duplicated ID!");
                }
                case 3 -> {
                    System.out.print("Enter book id: ");
                    int ID3 = sc.nextInt();
                    sc.nextLine();
                    if (BookMan.getBookById(ID3) != null) {
                        System.out.print("Enter book name: ");
                        String name3 = sc.nextLine();
                        System.out.print("Enter book price: ");
                        double price3 = sc.nextDouble();
                        BookMan.getBookById(ID3).setName(name3);
                        BookMan.getBookById(ID3).setPrice(price3);
                        System.out.println("Updated successfully.");
                    } else
                        System.out.println("Invalid ID!");
                }
                case 4 -> {
                    System.out.print("Enter book id: ");
                    int ID4 = sc.nextInt();
                    if (BookMan.getBookById(ID4) != null){
                        BookMan.remove(BookMan.getBookById(ID4));
                        System.out.println("Deleted successfully.");
                    }
                    else
                        System.out.println("Invalid ID!");
                }
                case 5 -> {
                    System.out.print("Enter keyword: ");
                    sc.nextLine();
                    String keyword = sc.nextLine();
                    BookMan.printBooks(BookMan.searchByName(keyword));
                }
                case 6 -> {
                    BookMan.sortDescByPrice();
                    if (BookMan.getBooks().size() != 0) {
                        System.out.println("After sorting: ");
                        BookMan.printBooks(BookMan.getBooks());
                    } else
                        System.out.println("(empty)");
                }
                default -> System.out.println("Invalid option!");
            }
        }
            catch(InputMismatchException e){
                System.out.println("Error!");
                sc.nextLine();
            }
        }
    }
    public static void Menu(){
        System.out.println("-----------------------------------");
        System.out.println("1. list all books");
        System.out.println("2. add a new book");
        System.out.println("3. edit book");
        System.out.println("4. delete a book");
        System.out.println("5. search books by name");
        System.out.println("6. sort books descending by price\n");
        System.out.println("0. save & exit");
        System.out.println("-----------------------------------");
        System.out.print("Your option: ");
    }

}