
package finalproject;

/**
 * This the enum that represents the categories of books
 * @author Kyrylo
 */
public enum Category {
    NONFICTION("Non-Fiction"),
    FICTION("Fiction"),
    FANTASY("Fantasy"),
    HISTORY("History");
    
    private String name;
   /**
    * Category constructor
    * @param name 
    */
    private Category(String name) {
        this.name = name;
    }
/**
 * Gets name 
 * @return name of category
 */
    public String getName() {
        return name;
    }
    
    
}

