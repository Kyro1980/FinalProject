package finalproject;

import java.util.Objects;

/**
 * This is a template class to represent a book.
 *
 * @author Kyrylo
 */
public class Book {

    //fields that show book info   
    private String author;
    private int year;
    private int qty;
    private String bookTitle;
    private Category categories;
    private long isbn;

    /**
     * Constructor for Book
     *
     * @param author
     * @param year
     * @param qty
     * @param bookTitle
     * @param categories
     * @param isbn
     */
    public Book(String author, int year, int qty, String bookTitle, Category categories, long isbn) {

        setAuthor(author);
        setYear(year);
        setQty(qty);
        //I have changed booktitle
        setBookTitle(bookTitle);
        setCategories(categories);
        setIsbn(isbn);
    }

    /**
     * Gets Author
     *
     * @return Author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets Year
     *
     * @return Year Published
     */
    public int getYear() {
        return year;
    }

    /**
     * Gets Quantity
     * @return Number of Books
     */
    public int getQty() {
        return qty;
    }

    /**
     *
     * @return
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     *
     * @return
     */
    public Category getCategories() {
        return categories;
    }

    /**
     *
     * @return
     */
    public long getIsbn() {
        return isbn;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        if (author == null || author.trim().equals("")) {
            throw new IllegalArgumentException("Bad Author");
        }
        this.author = author;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        if (year < 1000 || year > 2016) {
            throw new IllegalArgumentException("Year has to be between 1000 and 2016");
        }

        this.year = year;

    }

    /**
     * Sets and Checks the Quantity
     * @param qty
     */
    public void setQty(int qty) {
        if (qty < 0 || qty > 100) {
            throw new IllegalArgumentException("Quantity has to be greater then zero!!");
        }

        this.qty = qty;
    }

    /**
     *
     * @param bookTitle
     */
    public void setBookTitle(String bookTitle) {
        if (bookTitle == null || bookTitle.trim().equals("")) {
            throw new IllegalArgumentException("Please enter a book title!!");
        }
        this.bookTitle = bookTitle;
    }

    /**
     *
     * @param categories
     */
    public void setCategories(Category categories) {
        this.categories = categories;
    }

    /**
     *
     * @param isbn
     */
    public void setIsbn(long isbn) {
        if ((isbn + "").length() != 13) {
            throw new IllegalArgumentException("ISBN has to be 13 digits!!");
        }
        this.isbn = isbn;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Title: " + bookTitle + " Author: " + author + " Published: " + year + " Quantity: "
                + qty + " Categories: " + categories + " ISBN: " + isbn;
    }

    /**
     *
     * @return
     */
    public String toFileString() {
        return author + "~" + year + "~" + qty + "~" + bookTitle + "~" + categories.name() + "~" + isbn + "\n";
    }

    /**
     * Hash Method
     *
     * @return HashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * Equals method to compare Author, BookTitle, and Year
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;

        if (this.isbn == other.isbn) {
            return true;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.bookTitle, other.bookTitle)) {
            return false;
        }
        return true;
    }

}
