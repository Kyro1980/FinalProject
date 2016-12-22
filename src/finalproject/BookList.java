/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.scene.control.Alert;

/**
 * Represents the Collection of Books
 *
 * @author Kyrylo
 */
public class BookList extends ArrayList<Book> {

    /**
     * Writes all of the books to file
     *
     * @throws Exception
     */
    public void writeToFile() throws Exception {

        File file = new File("src/finalproject/book.dat");

        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        for (Book b : this) {
            pw.append(b.toFileString());
        }
        pw.close();

    }

    /**
     * Checks if the book is unique
     *
     * @param book
     * @return If book is unique
     */
    public boolean isUnique(Book book) {
        for (Book b : this) {
            if (b.equals(book)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reads all of the books from the file
     *
     * @throws Exception
     */
    public void getInfoFromFile() throws Exception {

//        try {
        File file = new File("src/finalproject/book.dat");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

//            author+"~"+year+"~"+qty+"~"+bookTitle+"~"+categories.name()+"~"+isbn+"\n";
            String[] info = line.split("~");
            String author = info[0];
            int year = Integer.parseInt(info[1]);
            int qty = Integer.parseInt(info[2]);
            String bookTitle = info[3];
            Category cat = Category.valueOf(info[4]);
            long isbn = Long.parseLong(info[5]);

            Book book = new Book(author, year, qty, bookTitle, cat, isbn);
            this.add(book);

        }

//        } catch (Exception ex) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("File error!!");
//            alert.setContentText("There was an error reading from the file!!");
//        }
    }

}
