package finalproject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Final Project Book Inventory System allows libraries to organize, catalog,
 * and manage book collections. This project keeps track of books offered in a
 * library. A Librarian can add, edit, and delete book records. Librarian can
 * also view books by category and search books by author or title.
 *
 * @author Kyrylo
 */
public class FinalProject extends Application {

    //field variables for GUI controls
    Button btnSrch, btnEdit, btnAddNew, btnSave, btnDelete, btnShowAll, btnExt, btnCnc;
    Label lblMain, lblTitle, lblAuthor, lblCtgr, lblPbl, lblQunt, lblISBN;
    TextField txtBT, txtAuth, txtPbl, txtQuan, txtISBN;
    ComboBox<Category> cmbCat;
    ListView<Book> lsv;

    //field variables used for proccesing
   
    BookList books = new BookList();

    /**
     * Starting point of JavaFX GUI
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();
        gridPane.minWidth(300);

        //set up a horizontal and vertical gap
        gridPane.setHgap(3);
        gridPane.setVgap(3);
        // set up padding
        gridPane.setPadding(new Insets(4));

        // constraints the row to be 25 % each 
        ColumnConstraints col1234 = new ColumnConstraints();
        col1234.setPercentWidth(25);
        gridPane.getColumnConstraints().addAll(col1234, col1234, col1234, col1234);

        // Creates text fields, sets up their prompts, and disbales some of them
        txtBT = new TextField();
        txtBT.setPromptText("Book Title");
        txtAuth = new TextField();
        txtAuth.setPromptText("Author");
        txtAuth.setDisable(true);
        txtPbl = new TextField();
        txtPbl.setPromptText("Year");
        txtPbl.setDisable(true);
        txtQuan = new TextField();
        txtQuan.setPromptText("qty");
        txtQuan.setDisable(true);
        txtISBN = new TextField();
        txtISBN.setPromptText("ISBN");
        txtISBN.setDisable(true);
        // Creates combo box
        cmbCat = new ComboBox<Category>();
        //Sets id
        cmbCat.setId("combo-box");

        //sets the values of the Category enum to show in the combo box
        cmbCat.setItems(FXCollections.observableArrayList(Category.values()));
        cmbCat.setValue(Category.FANTASY);
        cmbCat.setDisable(true);

        //Creates labels
        lblTitle = new Label("Book Title:");
        lblAuthor = new Label("Author:");
        lblCtgr = new Label("Categories:");
        lblPbl = new Label("Published:");
        lblQunt = new Label("Quantity:");
        lblISBN = new Label("ISBN:");
        lblMain = new Label("Book Inventory System");
        lblMain.setId("main-label");

        // creates the buttons and names them
        btnSrch = new Button("_Search");
        btnSrch.setPrefSize(100, 20);
        btnEdit = new Button("_Edit");
        btnEdit.setPrefSize(100, 20);
        btnEdit.setDisable(true);
        btnAddNew = new Button("_Add new");
        btnAddNew.setPrefSize(100, 20);
        btnSave = new Button("Sa_ve");
        btnSave.setPrefSize(100, 20);
        btnSave.setDisable(true);
        btnDelete = new Button("_Delete");
        btnDelete.setPrefSize(100, 20);
        btnDelete.setDisable(true);

        //hbox for the "Book Inventoty System" label
        HBox hboxForMainlbl = new HBox();
        hboxForMainlbl.setAlignment(Pos.CENTER);
        hboxForMainlbl.setPrefWidth(Double.MAX_VALUE);
        hboxForMainlbl.setPadding(new Insets(10, 0, 10, 0));

        hboxForMainlbl.getChildren().add(lblMain);

        //hbox for Search, Edit and Add New buttons
        HBox hboxForBtns1 = new HBox(5);

        hboxForBtns1.setAlignment(Pos.CENTER);
        hboxForBtns1.setPrefWidth(Double.MAX_VALUE);
        // adding buttons into HBox
        hboxForBtns1.getChildren().addAll(btnSrch, btnEdit, btnAddNew);

        //hbox for Save, Delete buttons
        HBox hboxForBtns2 = new HBox(5);
        hboxForBtns2.setAlignment(Pos.CENTER);
        hboxForBtns2.setPrefWidth(Double.MAX_VALUE);
        hboxForBtns2.getChildren().addAll(btnSave, btnDelete);

        GridPane.setHalignment(lblAuthor, HPos.RIGHT);
        GridPane.setHalignment(lblCtgr, HPos.RIGHT);
        GridPane.setHalignment(lblISBN, HPos.RIGHT);

        GridPane.setHalignment(lblPbl, HPos.RIGHT);
        GridPane.setHalignment(lblQunt, HPos.RIGHT);
        GridPane.setHalignment(lblTitle, HPos.RIGHT);

        // setting up positioning
        gridPane.add(hboxForMainlbl, 0, 0, 4, 1);
        gridPane.add(lblTitle, 0, 1);
        gridPane.add(txtBT, 1, 1, 3, 1);
        gridPane.add(lblAuthor, 0, 2);
        gridPane.add(txtAuth, 1, 2, 3, 1);
        gridPane.add(lblCtgr, 0, 3);
        gridPane.add(cmbCat, 1, 3, 2, 1);
        gridPane.add(lblPbl, 0, 4);
        gridPane.add(txtPbl, 1, 4);
        gridPane.add(lblQunt, 2, 4);
        gridPane.add(txtQuan, 3, 4);

        gridPane.add(lblISBN, 2, 3);
        gridPane.add(txtISBN, 3, 3);
        gridPane.add(hboxForBtns1, 0, 6, 4, 1);
        gridPane.add(hboxForBtns2, 0, 7, 4, 1);

        VBox vboxListV = new VBox(5);
        vboxListV.setPadding(new Insets(5, 5, 5, 5));
        //creates list view 
        lsv = new ListView();
        //sets padding
        lsv.setPadding(new Insets(3));
        vboxListV.getChildren().add(lsv);

        // hbox for the Exit, Show All and Cancel buttons
        HBox hboxBottom = new HBox(5);
        hboxBottom.setPadding(new Insets(5, 5, 5, 5));
        btnExt = new Button("_Exit");
        btnShowAll = new Button("Show All");
        btnCnc = new Button("_Cancel");
        btnCnc.setPrefSize(100, 20);
        btnExt.setPrefSize(100, 20);
        btnShowAll.setPrefSize(100, 20);
        hboxBottom.setAlignment(Pos.CENTER_RIGHT);
        btnCnc.setDisable(true);
        hboxBottom.getChildren().addAll(btnCnc, btnShowAll, btnExt);

        //creates the root VBox and adds everything else to it
        VBox root = new VBox();
        root.getChildren().addAll(gridPane, vboxListV, hboxBottom);

        // creates an event handler for the Add  button
        btnAddNew.setOnAction(e -> {
            //changes status of textfield and buttons so the user can enter info
            btnCnc.setDisable(false);
            btnSave.setDisable(false);

            txtAuth.setDisable(false);
            cmbCat.setDisable(false);
            txtPbl.setDisable(false);
            txtQuan.setDisable(false);

            txtISBN.setDisable(false);
            btnSrch.setDisable(true);
            btnAddNew.setDisable(true);

        });

        // creates an event handler for the Save button
        btnSave.setOnAction(e -> {
            // gets information from the fields
            String author = txtAuth.getText();
            String title = txtBT.getText();
            String published = txtPbl.getText();
            String quantity = txtQuan.getText();
            String isbn = txtISBN.getText();
            Category catSt = cmbCat.getValue();

            try {
                //checks for empty input 
                if (author.trim().equals("") || title.trim().equals("")
                        || published.trim().equals("")
                        || quantity.trim().equals("")
                        || isbn.trim().equals("")) {
                    throw new IllegalArgumentException("Please fill all of the fields!");
                }

                //parse some info into numbers
                int publInt = Integer.parseInt(published);
                int quantInt = Integer.parseInt(quantity);
                long isbnLong = Long.parseLong(isbn);

                //create a book using above info
                Book book = new Book(author, publInt, quantInt, title, catSt, isbnLong);

                //check if the book we're trying to creat already exists
                if (!books.isUnique(book)) {
                    throw new IllegalArgumentException("This book already exists!!"
                            + "\nPlease check an Author,Title,Year and ISBN.");
                }
                //if record is unique adds to books
                books.add(book);

                //reset textfields and buttons to default state
                txtBT.clear();
                txtAuth.clear();
                txtPbl.clear();
                txtQuan.clear();
                txtISBN.clear();

                btnSrch.setDisable(false);
                btnEdit.setDisable(true);
                btnSave.setDisable(true);
                btnDelete.setDisable(true);
                btnAddNew.setDisable(false);

                txtBT.setDisable(false);
                txtAuth.setDisable(true);
                txtISBN.setDisable(true);
                txtPbl.setDisable(true);
                txtQuan.setDisable(true);

            } catch (NumberFormatException ex) {
                //catches if year, quantity or ISBN is an improper numerical 
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText("Please check that year, quantity, and ISBN are proper numbers!!");
                a.setTitle("Error");
                a.showAndWait();

            } catch (IllegalArgumentException ex) {
                //generates an Alert if inputed values are improper 
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(ex.getMessage());
                a.setTitle("Error");
                a.showAndWait();

            }

        });
        // creates an event handler for the Exit button
        btnExt.setOnAction(e -> {
            // confirmation that user wants to exit
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you want to exit?", ButtonType.YES,
                    ButtonType.NO);
            alert.setTitle("Exit");
            alert.setHeaderText(null);
            Optional<ButtonType> result = alert.showAndWait();
            //
            if (result.get() == ButtonType.YES) {
                //
                try {
                    books.writeToFile();
                } catch (Exception ex) {
                    //alert is shown if we can not wright to the file 
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("File error!!");
                    alert.setContentText("There was an error writing to the file!!");
                }
                System.exit(0);
            }
        });
        // creates an event handler to the Show all button
        btnShowAll.setOnAction(e
                -> {
            //set the books arraylist to show up in the listview
            lsv.setItems(FXCollections.observableArrayList(books));

        }
        );

        // creates an event handler to the Search button
        btnSrch.setOnAction(e
                -> {

            String title = txtBT.getText();
            try {
                //checks empty input
                if (title.trim().equals("")) {
                    throw new IllegalArgumentException("Please write a Title of the book!!");
                }
                //empties the listview 
                lsv.getItems().clear();

                //repopulate items
                for (Book b : books) {
                    if (title.equals(b.getBookTitle())) {
                        lsv.getItems().add(b);
                       

                    } 
                }
                //shows alert window if book does not exist
                if (lsv.getItems().isEmpty()) {
                    Alert b = new Alert(AlertType.INFORMATION);
                    b.setContentText("No catalog results found.");
                    b.setTitle("Information");
                    b.showAndWait();

                }
                lsv.refresh();
                
                txtBT.clear();
            } catch (IllegalArgumentException ex) {
                //alert is shoswn if a book title is not entered
                Alert a = new Alert(AlertType.ERROR);
                a.setContentText(ex.getMessage());
                a.setTitle("Error");
                a.showAndWait();

            }

        }
        );
        // creates an event handler to the Cancel button
        btnCnc.setOnAction(
                (ActionEvent e) -> {
                    //clear list wiev
                    lsv.getItems().clear();
                    //
                    txtBT.clear();
                    txtAuth.clear();
                    txtPbl.clear();
                    txtQuan.clear();
                    txtISBN.clear();
                    //
                    btnSrch.setDisable(false);
                    btnEdit.setDisable(true);
                    btnSave.setDisable(true);
                    btnDelete.setDisable(true);
                    btnAddNew.setDisable(false);
                    //
                    txtBT.setDisable(false);
                    txtAuth.setDisable(true);
                    txtISBN.setDisable(true);
                    txtPbl.setDisable(true);
                    txtQuan.setDisable(true);

                    btnCnc.setDisable(true);

                }
        );
        // creates an event handler to the Delete button
        btnDelete.setOnAction(e
                -> {
            //delete the selected record
            Book b = lsv.getSelectionModel().getSelectedItem();
            // removing from an array list
            books.remove(b);
            // taking record from the listView 
            lsv.getItems().remove(b);
            lsv.refresh();
            // cleaninng up all text fields
            txtBT.clear();
            txtAuth.clear();
            txtPbl.clear();
            txtQuan.clear();
            txtISBN.clear();

        }
        );
        //creates an event handler for the Edit button
        btnEdit.setOnAction(
                (ActionEvent) -> {
                    //gets text from the text field
                    String author = txtAuth.getText();
                    String title = txtBT.getText();
                    String published = txtPbl.getText();
                    String quantity = txtQuan.getText();
                    String isbn = txtISBN.getText();
                    Category catSt = cmbCat.getValue();
                    
                    try {
                        // catchs empty input
                        if (author.trim().equals("") || title.trim().equals("")
                        || published.trim().equals("")
                        || quantity.trim().equals("")
                        || isbn.trim().equals("")) {
                            throw new IllegalArgumentException("Please fill all of the fields!");
                        }
                        // parses input
                        int publInt = Integer.parseInt(published);
                        int quantInt = Integer.parseInt(quantity);
                        long isbnLong = Long.parseLong(isbn);
                        //gets the current book and sets the  info of the book 
                        Book b = lsv.getSelectionModel().getSelectedItem();
                        b.setAuthor(author);
                        b.setBookTitle(title);
                        b.setCategories(catSt);
                        b.setIsbn(isbnLong);
                        b.setQty(quantInt);
                        b.setYear(publInt);

                        lsv.refresh();

                    } catch (NumberFormatException ex) {
                        //catches if year, quantity or ISBN is an improper numerical 
                        Alert a = new Alert(AlertType.ERROR);
                        a.setContentText("Please check that year, quantity, and ISBN are proper numbers!!");
                        a.setTitle("Error");
                        a.showAndWait();

                    } catch (IllegalArgumentException ex) {
                        //generates an Alert if inputed values are improper
                        Alert a = new Alert(AlertType.ERROR);
                        a.setContentText(ex.getMessage());
                        a.setTitle("Error");
                        a.showAndWait();

                    }

                }
        );
        // creates an event handler for mouse click on the listview
        lsv.setOnMouseClicked(e
                -> {
            // gets the selected book
            Book b = lsv.getSelectionModel().getSelectedItem();
            
            if (b != null) {
                
                txtAuth.setDisable(false);
                txtISBN.setDisable(false);
                txtPbl.setDisable(false);
                txtQuan.setDisable(false);
                cmbCat.setDisable(false);
                
                // sets text to the textField
                txtAuth.setText(b.getAuthor());
                txtBT.setText(b.getBookTitle());
                txtISBN.setText(b.getIsbn() + "");
                txtPbl.setText(b.getYear() + "");
                txtQuan.setText(b.getQty() + "");
                cmbCat.setValue(b.getCategories());

                btnEdit.setDisable(false);
                btnDelete.setDisable(false);
                btnCnc.setDisable(false);
                btnSave.setDisable(true);

                btnAddNew.setDisable(true);

            }
        }
        );
        //shows alert window
        primaryStage.setOnCloseRequest(e -> {
            //
            e.consume();
            btnExt.fire();
        });
        try {
            books.getInfoFromFile();

        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("File error!!");
            alert.setContentText("There was an error reading from the file!!");
        }
        Scene scene = new Scene(root, 350, 350);

        // add the css file
        scene.getStylesheets()
                .add(getClass().getResource("fproject_css.css").toExternalForm());
        primaryStage.setMinWidth(
                320);
        primaryStage.setMaxWidth(
                700);
        primaryStage.setMinHeight(
                350);
        primaryStage.setMaxHeight(
                550);
        primaryStage.setTitle(
                "Book Inventory System");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
